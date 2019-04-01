package com.bec.reporting.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import com.bec.reporting.steps.Hooks;
import com.google.common.base.Verify;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatabaseConnection {
	public static Properties prop; 	
	/*
	 * API Methods Here
	 */
	
	
	/*public  ArrayList<StandardPerformanceDetails> getStandardDetails(String Strand) {
        ArrayList<StandardPerformanceDetails> details = new ArrayList<StandardPerformanceDetails>();
      
        Connection conn = null ;//=ConnectionPool.getDbConnection();
        try {
            String sql = "Select standard_shortvalue,standard_description from bec_edw_dev.content_standard_common_core where standard_category=? "; 
            		
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Strand);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	//System.out.println( rs.getString("standard_description"));
            	standarddetails.setStandard_category(Strand);
            	standarddetails.setShortvalue(rs.getString("standard_shortvalue"));
            	standarddetails.setStandard_description( rs.getString("standard_description"));
            	details.add(standarddetails);
            	List<StandardPerformanceDetails> sortedList = details.stream()
            			.sorted(Comparator.comparing(StandardPerformanceDetails::getStandard_category))
            			.collect(Collectors.toList());
            	//details.forEach(detail->System.out.println(detail));
            	sortedList.forEach(System.out::println);
        		
            	
            }
        }  catch(Exception sqlException) {
        	System.out.println(sqlException);
			sqlException.printStackTrace();
		} finally {
			try {
				// Closing ResultSet Object
				if(rsObj != null) {
					rsObj.close();
				}
				// Closing PreparedStatement Object
				if(pstmtObj != null) {
					pstmtObj.close();
				}
				// Closing Connection Object
				if(connObj != null) {
					connObj.close();
				}
			} catch(Exception sqlException) {
				sqlException.printStackTrace();
			}
		}
        return details;
    }*/
	
	/**
	 * This method is used to get the Token value from API
	 * @return
	 */
	public static String getToken() {
		log.info("Get Access token");		
		try {
			prop = FileRead.readProperties();
			String payload = "{\n" + "  \"username\": \"superadmin\",\n" + "  \"password\": \"password\",\n"
					+ "  \"realm\": \"techsupport\"\n" + "}";
			String apiUrl = prop.getProperty("apiURL") + "/auth/create-token";
			Response response = RestAssured.given().body(payload)
					.get(apiUrl);
			if (!(response.getStatusCode() == 200)) {
				log.info("Error occurred. status code : " + response.getStatusCode());
				return null;
			} else {
				return new JsonPath(response.asString()).getString("token");
			}
		} catch (Exception e) {
			UtilityMethods.processException(e);
			return null;
		}		
	}
	
	public static Integer getSchoolIDBySchoolName(String schoolName) {
		Integer schoolID=0;
		try {
			prop = FileRead.readProperties();
			String apiUrl = prop.getProperty("apiURL") + "/schools?page=0&size=10000&direction=ASC";
			Response response = RestAssured.given().header("Authorization", "Bearer " + Hooks.token).get(apiUrl);
			if (!(response.getStatusCode() == 200)) {
				log.info("Error occurred. status code : " + response.getStatusCode());
				return null;
			} else {
				List<String> schoolnameslist=response.jsonPath().getList("name");
				List<Integer> schoolidlist=response.jsonPath().getList("id");
				Verify.verify(schoolnameslist.contains(schoolName));
				schoolID=schoolidlist.get(schoolnameslist.indexOf(schoolName));
			}	
		} catch (Exception e) {
			UtilityMethods.processException(e);
			return null;
		}
		return schoolID;
	}

	public static Integer getClassIDBySchoolNameAndClassName(String schoolName,String className) {
		Integer classID=0;
		try {
			prop = FileRead.readProperties();
			String apiUrl = prop.getProperty("apiURL") + "/schools/"+getSchoolIDBySchoolName(schoolName)+"/classes?page=0&size=10000";
			Response response = RestAssured.given().header("Authorization", "Bearer " + Hooks.token).get(apiUrl);
			if (!(response.getStatusCode() == 200)) {
				log.info("Error occurred. status code : " + response.getStatusCode());
				return null;
			} else {
				List<String> classNamesList=response.jsonPath().getList("name");
				List<Integer> classIdList=response.jsonPath().getList("id");
				Verify.verify(classNamesList.contains(className));
				classID=classIdList.get(classNamesList.indexOf(className));
			}	
		} catch (Exception e) {
			UtilityMethods.processException(e);
			return null;
		}
		return classID;
	}
	
	
	
	public static List<Object> getAllStrandBySchoolAndClassName(Connection con,String schoolName,String className) {
		ArrayList<Object> strandlist = new ArrayList<Object>();		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery("Select distinct standard_category from bec_edw_dev.bu_strand_details_vw sd inner join"
					+ " bec_edw_dev.content_standard_common_core cscc on cscc.standard_id=sd.standard_id and bu_school_id="+getSchoolIDBySchoolName(schoolName)
					+ "and collective_noun_id="+getClassIDBySchoolNameAndClassName(schoolName, className)+" order by standard_category");
			while (rs.next()) {
				strandlist.add(rs.getString(1));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
		return strandlist;
	}
	
	
	public static List<Model> getStandardAvgPerListInClassContext(Connection con,Integer schoolId,Integer classId,String strandName) {
		List<Model> list=new ArrayList<Model>();	
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			String str="Select round(SUM(sd.score)/SUM(sd.max_score)*100) as avg_per,cscc.standard_shortvalue,cscc.standard_category,cscc.standard_subcategory,cscc.standard_description from  bec_edw_dev.bu_strand_details_vw sd inner join bec_edw_dev.content_standard_common_core cscc \r\n" + 
					"on cscc.standard_id=sd.standard_id and " + 
					"sd.component_title in (select distinct component_title from bec_edw_dev.bu_assessment_reporting_detail where bu_school_id="+schoolId+" and collective_noun_id="+classId+") and " + 
					"sd.bu_school_id="+schoolId+" and sd.collective_noun_id="+classId+" and " + 
					"sd.standard_id IN (select DISTINCT standard_id from bec_edw_dev.content_standard_common_core where standard_category='"+strandName+"') group by cscc.standard_shortvalue, cscc.standard_category,cscc.standard_subcategory,cscc.standard_description  order by avg_per desc";
			rs = stmt.executeQuery(str);
			while (rs.next()) {
				Model m=new Model();
				m.setAvg_per(rs.getInt(1));
				m.setStandard_shortvalue(rs.getString(2));
				m.setStandard_category(rs.getString(3));
				m.setStandard_subcategory(rs.getString(2)+": "+rs.getString(4));
				m.setStandard_description(rs.getString(5));
				list.add(m);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
	
	public static List<Model> getStandardAvgPerListInStudentContext(Connection con,Integer schoolId,Integer classId,String strandName,Integer studentId) {
		List<Model> list=new ArrayList<Model>();	
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			String str="Select round(SUM(sd.score)/SUM(sd.max_score)*100) as avg_per,cscc.standard_shortvalue,cscc.standard_category,cscc.standard_subcategory,cscc.standard_description from  bec_edw_dev.bu_strand_details_vw sd inner join bec_edw_dev.content_standard_common_core cscc \r\n" + 
					"on cscc.standard_id=sd.standard_id and " + 
					"sd.component_title in (select distinct component_title from bec_edw_dev.bu_assessment_reporting_detail where bu_school_id="+schoolId+" and collective_noun_id="+classId+" and student_id="+studentId+") and " + 
					"sd.bu_school_id="+schoolId+" and sd.collective_noun_id="+classId+" and sd.student_id="+studentId+" and " + 
					"sd.standard_id IN (select DISTINCT standard_id from bec_edw_dev.content_standard_common_core where standard_category='"+strandName+"') group by cscc.standard_shortvalue, cscc.standard_category,cscc.standard_subcategory,cscc.standard_description  order by avg_per desc";
			rs = stmt.executeQuery(str);
			while (rs.next()) {
				Model m=new Model();
				m.setAvg_per(rs.getInt(1));
				m.setStandard_shortvalue(rs.getString(2));
				m.setStandard_category(rs.getString(3));
				m.setStandard_subcategory(rs.getString(2)+": "+rs.getString(4));
				m.setStandard_description(rs.getString(5));
				list.add(m);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
	
	public static void main(String args[]) {
		
		List<Model> lm=getStandardAvgPerListInClassContext(ConnectionPool.getDBConnection(), 509446, 1213286, "Language");
		for (Model model : lm) {
			System.out.println(model.getAvg_per());
			System.out.println(model.getStandard_shortvalue());
			System.out.println(model.getStandard_category());
			System.out.println(model.getStandard_subcategory());
			System.out.println(model.getStandard_description());
		}
		
		
	}
	
	
	
}
	


