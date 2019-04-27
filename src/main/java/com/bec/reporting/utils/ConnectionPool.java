package com.bec.reporting.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectionPool {

	static ArrayList<Connection> connections = null;
	static ConnectionPool instance = null;

	/**
	 * This method is used to Remove All Existing Connections
	 */
	public synchronized void removeAllConnections() {
		try {
			if (connections == null) {
				return;
			}
			int sz = connections.size();
			for (int i = 0; i < sz; i++) {
				Connection c = connections.get(i);
				c.close();
			}
			connections.clear();
			connections = null;
		} catch (SQLException sqlE) {
			System.out.println(sqlE);
		}
	}
	
	/**
	 * This method is used to get Instance of ConnectionPool
	 * @return
	 */
	public static synchronized ConnectionPool getInstance() {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}

	/**
	 * This method is used to initialize & Establish the DB Connection
	 */
	public synchronized void initialize() {
		if (connections == null) {
			try {
				Properties pro = FileRead.readProperties();
				String userName = pro.getProperty("username");
				String password = pro.getProperty("password");
				String url = pro.getProperty("url");
				int ic = Integer.parseInt(pro.getProperty("maxconnections"));
				Class.forName(pro.getProperty("drivername")).newInstance();
				connections = new ArrayList<Connection>();
				int count = 0;
				while (count < ic) {
					Connection c = DriverManager.getConnection(url, userName, password);
					connections.add(c);
					count++;
				}
			} catch (Exception e) {
				log.error("Cannot connect to database server");
			}
		}
	}
	
	/**
	 * This method is used to getConnetion for Unique User
	 * @return
	 */
	public synchronized Connection getConnection() {
		Connection c = null;
		if (connections == null) {
			return null;
		}
		while (true) {
			if (connections.size() > 0) {
				c = connections.get(0);
				connections.remove(0);
				break;
			} else {
				try {
					wait();
				} catch (Exception e) {
					log.error("Problem with wait while synchronizing connection");
				}
			}
		}
		return c;
	}

	/**
	 * This method is used to put Connection in after completing Operation.
	 * @param c
	 */
	public synchronized void putConnection(Connection c) {
		connections.add(c);
		notifyAll();
	}
	
	/**
	 * This is utility method to getDB Connection,which call only once while launcing browser
	 * @return
	 */
	 public static Connection getDBConnection()
	    {
	    	Connection connObj = null;
	    	 ConnectionPool jdbcObj = ConnectionPool.getInstance();
	    	 jdbcObj.initialize();
				connObj = jdbcObj.getConnection();
				log.info("DB Connection done successfully");
				return connObj;
	    }

}
