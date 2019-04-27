package com.bec.reporting.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import com.bec.reporting.steps.PaginationOfDropDownListSteps;
import com.google.common.base.Verify;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatabaseConnection {
	public static Properties prop; 	
	public static int districtId=509440;
	/*
	 * API Methods Here
	 */	
	
	/**
	 * This method is used to get the Token value from API
	 * @return
	 */
	public static String getToken() {
		log.info("Get Access token");	
		String token="";
		try {
			prop = FileRead.readProperties();
			String payload = "{\n" + "  \"username\": \"superadmin\",\n" + "  \"password\": \"password\",\n"
					+ "  \"realm\": \"techsupport\"\n" + "}";
			//String apiUrl = prop.getProperty("apiURL") + "/auth/create-token";
			String apiUrl ="https://atlantis-api.cf:8080/api/v1/auth/create-token";
			Response response = RestAssured.given().body(payload)
					.get(apiUrl);
			if (response.getStatusCode() != 200) {
				log.info("Error occurred. status code : " + response.getStatusCode());
				return null;
			} else {
				token=new JsonPath(response.asString()).getString("token");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return token;
	}
	
	/**
	 * This method is used to get School ID by School Name
	 * @param schoolName
	 * @return
	 */
	public static Integer getSchoolIDBySchoolName(String schoolName) {
		Integer schoolID=0;
		try {
			prop = FileRead.readProperties();
			String apiUrl = prop.getProperty("apiURL") + "/schools?page=0&size=10000&direction=ASC";
			Response response = RestAssured.given().header("Authorization", "Bearer " + PaginationOfDropDownListSteps.token).get(apiUrl);
			if (response.getStatusCode() != 200) {
				log.info("Error occurred. status code : " + response.getStatusCode());
				return null;
			} else {
				List<Object> schoolnameslist = new JsonPath(response.asString()).getList("value.name");
				List<Object> schoolidlist=new JsonPath(response.asString()).getList("value.id");
				Verify.verify(schoolnameslist.contains(schoolName));
				schoolID=Integer.parseInt(schoolidlist.get(schoolnameslist.indexOf(schoolName)).toString());
			}	
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return schoolID;
	}
	
	/**
	 * This method is used to get All School
	 * @param schoolName
	 * @return
	 */
	public static Map<String,Integer> getAllSchoolNames() {
		Map<String,Integer> schoolMap=new TreeMap<String,Integer>();
		try {
			prop = FileRead.readProperties();
			String apiUrl = prop.getProperty("apiURL") + "/schools?page=0&size=10000&direction=ASC";
			Response response = RestAssured.given().header("Authorization", "Bearer " + PaginationOfDropDownListSteps.token).get(apiUrl);
			if (response.getStatusCode() != 200) {
				log.info("Error occurred. status code : " + response.getStatusCode());
				return null;
			} else {
				List<Object> value = new JsonPath(response.asString()).getList("value.name");
				for (int i = 0; i < value.size(); i++) {
					schoolMap.put(String.valueOf(new JsonPath(response.asString()).getList("value.name").get(i)), Integer.parseInt(new JsonPath(response.asString()).getList("value.id").get(i).toString()));
				}
			}	
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return schoolMap;
	}

	/**
	 * This method is used to get All Classes by SchoolName
	 * @param schoolName
	 * @return
	 */
	public static Map<String,Integer> getAllClassesNamesBySchoolName(String schoolName) {
		Map<String,Integer> classMap=new TreeMap<String,Integer>();
		try {
			prop = FileRead.readProperties();
			String apiUrl = prop.getProperty("apiURL") + "/classes?schoolId="+getSchoolIDBySchoolName(schoolName)+"&districtId="+districtId;
			Response response = RestAssured.given().header("Authorization", "Bearer " + PaginationOfDropDownListSteps.token).get(apiUrl);
			if (response.getStatusCode() != 200) {
				log.info("Error occurred. status code : " + response.getStatusCode());
				return null;
			} else {
				List<Object> value = new JsonPath(response.asString()).getList("value.name");
				for (int i = 0; i < value.size(); i++) {
					classMap.put(String.valueOf(new JsonPath(response.asString()).getList("value.name").get(i)), Integer.parseInt(new JsonPath(response.asString()).getList("value.id").get(i).toString()));
				}
			}	
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return classMap;
	}
	
	/**
	 * This method is used to return first alpha school 
	 * @return
	 */
	public static String getFirstAlphaSchoolName() {
		String schoolName = "";
		try {
			schoolName=getAllSchoolNames().keySet().stream().findFirst().get();
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return schoolName;
	}
	
	/**
	 * This method is used to return first alpha class based on first alpha school 
	 * @return
	 */
	public static String getFirstAlphaClassNameBySchoolName(String schoolName) {
		String className = "";
		try {
			className=getAllClassesNamesBySchoolName(schoolName).keySet().stream().findFirst().get();
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return className;
	}
	
	/**
	 * This method is used to return all the student list based on school name and class name
	 * @param schoolName
	 * @param className
	 * @return
	 */
	public static Map<String,Integer> getAllStudentsByClassNameAndSchoolName(Integer schoolId,Integer classId) {
		Map<String,Integer> studentMap=new TreeMap<String,Integer>();
		try {
			prop = FileRead.readProperties();
			String apiUrl = prop.getProperty("apiURL") + "/students?schoolId="+schoolId+"&districtId="+districtId+"&classId="+classId;
			Response response = RestAssured.given().header("Authorization", "Bearer " + PaginationOfDropDownListSteps.token).get(apiUrl);
			if (response.getStatusCode() != 200) {
				log.info("Error occurred. status code : " + response.getStatusCode());
				return null;
			} else {
				List<Object> value = new JsonPath(response.asString()).getList("value.name");
				for (int i = 0; i < value.size(); i++) {
					studentMap.put(String.valueOf(new JsonPath(response.asString()).getList("value.name").get(i)), Integer.parseInt(new JsonPath(response.asString()).getList("value.id").get(i).toString()));
				}
			}	
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return studentMap;
	}
	/**
	 * This method is used to return class ID based on school name and class name
	 * @param schoolName
	 * @param className
	 * @return
	 */
	public static Integer getClassIDBySchoolNameAndClassName(String schoolName,String className) {
		Integer classID=0;
		try {
			prop = FileRead.readProperties();
			String apiUrl = prop.getProperty("apiURL") + "/classes?schoolId="+getSchoolIDBySchoolName(schoolName)+"&districtId="+districtId;
			Response response = RestAssured.given().header("Authorization", "Bearer " + PaginationOfDropDownListSteps.token).get(apiUrl);
			if (response.getStatusCode() != 200) {
				log.info("Error occurred. status code : " + response.getStatusCode());
				return null;
			} else {
				List<Object> classNamesList = new JsonPath(response.asString()).getList("value.name");
				List<Object> classIdList=new JsonPath(response.asString()).getList("value.id");
				Verify.verify(classNamesList.contains(className));
				classID=Integer.parseInt(classIdList.get(classNamesList.indexOf(className)).toString());
			}	
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return classID;
	}
	
	/**
	 * This method is used to return the first alpha student based on last name
	 * @return
	 */
	public static String getFirstAlphaLastNameStudentByAlphaClassAndSchoolName() {
		String studentName = "";
		Map<String,Integer> studentMap=new TreeMap<String,Integer>();
		try {
			String schoolName=getAllSchoolNames().keySet().stream().findFirst().get();
			String className=getAllClassesNamesBySchoolName(schoolName).keySet().stream().findFirst().get();
			int schoolId=getSchoolIDBySchoolName(schoolName);
			int classId=getClassIDBySchoolNameAndClassName(schoolName, className);			
			prop = FileRead.readProperties();
			String apiUrl = prop.getProperty("apiURL") +"/students?schoolId="+schoolId+"&districtId="+districtId+"&classId="+classId;
			Response response = RestAssured.given().header("Authorization", "Bearer " + PaginationOfDropDownListSteps.token).get(apiUrl);
			if (response.getStatusCode() != 200) {
				log.info("Error occurred. status code : " + response.getStatusCode());
				return null;
			} else {
				List<Object> value = new JsonPath(response.asString()).getList("value.name");
				for (int i = 0; i < value.size(); i++) {
					studentMap.put(String.valueOf(new JsonPath(response.asString()).getList("value.name").get(i)), Integer.parseInt(new JsonPath(response.asString()).getList("value.id").get(i).toString()));
				}
				studentName = studentMap.keySet().stream().findFirst().get()+" "+"("+studentMap.get(studentMap.keySet().toArray()[0])+" )";
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return studentName;
	}
	
	
	/**
	 * This method is used to return the student name based on student id
	 * @return
	 */
	public static String getStudentNameByStudentId(Integer schoolId,Integer classId,Integer studentId) {
		String studentName = "";
		try {
			prop = FileRead.readProperties();
			String apiUrl = prop.getProperty("apiURL") +"/students?schoolId="+schoolId+"&districtId="+districtId+"&classId="+classId;
			Response response = RestAssured.given().header("Authorization", "Bearer " + PaginationOfDropDownListSteps.token).get(apiUrl);
			if (response.getStatusCode() != 200) {
				log.info("Error occurred. status code : " + response.getStatusCode());
				return null;
			} else {
				List<Object> studentNamesList = new JsonPath(response.asString()).getList("value.name");
				List<Object> studentIdList=new JsonPath(response.asString()).getList("value.id");
				Verify.verify(studentIdList.contains(String.valueOf(studentId)));
				studentName=studentNamesList.get(studentIdList.indexOf(String.valueOf(studentId))).toString();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return studentName;
	}
	public static void main(String args[]) {
		/*List<Model> lm=getStandardAvgPerListInClassContext(ConnectionPool.getDBConnection(), 509446, 1213286, "Language");
		for (Model model : lm) {
			System.out.println(model.getAvg_per());
			System.out.println(model.getStandard_shortvalue());
			System.out.println(model.getStandard_category());
			System.out.println(model.getStandard_subcategory());
			System.out.println(model.getStandard_description());
		}*/
		//System.out.println(getSchoolIDBySchoolName("Church of England Primary School"));
		//System.out.println(getAllSchoolNames());
		//System.out.println(getAllClassesNames("Church of England Primary School"));
		//System.out.println(getClassIDBySchoolNameAndClassName("Church of England Primary School", "School V Trainer Class (PD)"));
		//System.out.println(getAllStudentsByClassNameAndSchoolName("Church of England Primary School", "School V Trainer Class (PD)"));
		//System.out.println(getFirstAlphaLastNameStudentByAlphaClassAndSchoolName());
		//System.out.println(getStandardAvgPerListInClassContext(ConnectionPool.getDBConnection(), 509446, 1213286, "Language"));
		//System.out.println(getTestScoreDetailsInClassContext(ConnectionPool.getDBConnection(), 509446, 1213286, "Grade 4 Unit 1 Assessment", "CCSS.ELA-Literacy.L.4.2"));
		System.out.println(getStudentDetailListInSPInClassByStrand(ConnectionPool.getDBConnection(), "Church of England Primary School", "School V Trainer Class (PD)", "Language"));
	}
	
	
	/****************Db Methods are here**********************/
	/**
	 * This method is used to get All Strand name based on School name and class Name
	 * @param con
	 * @param schoolName
	 * @param className
	 * @return
	 */
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
			//con.close();
		} catch (Exception e) {
			log.error(e.getMessage());
			System.out.println(e.getMessage());
		}		
		return strandlist;
	}
	
	/**
	 * This method is used to return Standard Details like average percentage of standard belong to the Strand name
	 * and based on school id and class id in class context
	 * @param con
	 * @param schoolId
	 * @param classId
	 * @param strandName
	 * @return
	 */
	public static List<Model> getStandardAvgPerListInClassContext(Connection con,Integer schoolId,Integer classId,String strandName) {
		List<Model> list=new ArrayList<Model>();	
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			String str="Select round(SUM(sd.score)/SUM(sd.max_score)*100) as avg_per,cscc.standard_shortvalue,cscc.standard_category,cscc.standard_subcategory,cscc.standard_description, cscc.standard_id from  bec_edw_dev.bu_strand_details_vw sd inner join bec_edw_dev.content_standard_common_core cscc \r\n" + 
					"on cscc.standard_id=sd.standard_id and " + 
					"sd.component_title in (select distinct component_title from bec_edw_dev.bu_assessment_reporting_detail where bu_school_id="+schoolId+" and collective_noun_id="+classId+") and " + 
					"sd.bu_school_id="+schoolId+" and sd.collective_noun_id="+classId+" and " + 
					"sd.standard_id IN (select DISTINCT standard_id from bec_edw_dev.content_standard_common_core where standard_category='"+strandName+"') group by cscc.standard_shortvalue, cscc.standard_category,cscc.standard_subcategory,cscc.standard_description,cscc.standard_id order by avg_per desc";
			rs = stmt.executeQuery(str);
			while (rs.next()) {
				Model m=new Model();
				m.setAvg_per(rs.getInt(1));
				m.setStandard_shortvalue(rs.getString(2));
				m.setStandard_category(rs.getString(3));
				m.setStandard_subcategory(rs.getString(2)+": "+rs.getString(4));
				m.setStandard_description(rs.getString(5));
				m.setStandard_id(rs.getString(6));
				list.add(m);
			}
			//con.close();
		} catch (Exception e) {
			log.error(e.getMessage());
			System.out.println(e.getMessage());
		}
		return list;
	}
	
	/**
	 * This method is used to return Standard Details like average percentage of standard belong to the Strand name
	 * and based on school id and class id in student context
	 * @param con
	 * @param schoolId
	 * @param classId
	 * @param strandName
	 * @return
	 */
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
			//con.close();
		} catch (Exception e) {
			log.error(e.getMessage());
			System.out.println(e.getMessage());
		}
		return list;
	}
	
	/***
	 * This method is used to return the avg score and component title on performance over time line chart in class context
	 * @param con
	 * @param schoolId
	 * @param classId
	 * @param component_title
	 * @param standard_id
	 * @return
	 */
	public static List<Model> getTestScoreDetailsInSPInClassContext(Connection con,Integer schoolId,Integer classId,String component_title,String standard_id) {
		List<Model> list=new ArrayList<Model>();
		List<Integer> qList=new ArrayList<>(); 
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			String questionStr = "select distinct question_no from bec_edw_dev.bu_strand_details_vw  WHERE bu_school_id = "+schoolId+" " + 
					"AND collective_noun_id = "+classId+" and component_title='"+component_title+"'  AND " + 
					"standard_id='"+standard_id+"' order by question_no ";
			rs = stmt.executeQuery(questionStr);
			while(rs.next()) {
				qList.add(rs.getInt(1));
			}
			String str="select component_title,round(SUM(score)/SUM(max_score)*100) as avg_per, min(updatedat),max(updatedat) from bec_edw_dev.bu_strand_details_vw  WHERE bu_school_id = "+schoolId+" " + 
					"AND collective_noun_id = "+classId+" and component_title='"+component_title+"'  AND" + 
					" standard_id='"+standard_id+"' group by component_title order by avg_per desc";
			rs = stmt.executeQuery(str);
			while (rs.next()) {
				Model m=new Model();
				m.setComponent_title(rs.getString(1));
				m.setAvg_per(rs.getInt(2));
				m.setMinDate(rs.getDate(3));
				m.setMaxDate(rs.getDate(4));
				m.setQuestion_no(qList);
				list.add(m);
			}
				
			//con.close();
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return list;
	}
	
	/**
	 * This method is used to fetch no. of questions, student name and student test score avg from DB for SP Context
	 * @param con
	 * @param schoolName
	 * @param className
	 * @param strandName
	 * @return
	 */
	public static List<Model> getStudentDetailListInSPInClassByStrand(Connection con, String schoolName, String className,
			String strandName) {
		List<Model> list = new ArrayList<Model>();
		int schoolId = DatabaseConnection.getSchoolIDBySchoolName(schoolName);
		int classId = DatabaseConnection.getClassIDBySchoolNameAndClassName(schoolName, className);
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			String str = "";
			str = " SELECT student_id,COUNT(DISTINCT identifier) AS no_of_question,ROUND((sum(score) / sum(max_score))*100) AS Student_Score\r\n"
					+ "FROM bec_edw_dev.bu_strand_details_vw WHERE bu_school_id = " + schoolId + "\r\n"
					+ "AND   collective_noun_id = " + classId
					+ " AND   component_title IN (SELECT DISTINCT component_title FROM bec_edw_dev.bu_assessment_reporting_detail\r\n"
					+ " WHERE bu_school_id = " + schoolId + "\r\n" + " AND   collective_noun_id = " + classId + ")\r\n"
					+ "AND standard_id IN (SELECT DISTINCT standard_id\r\n"
					+ "FROM bec_edw_dev.content_standard_common_core\r\n" + "WHERE standard_category = '" + strandName
					+ "')\r\n" + "GROUP BY student_id order by student_id limit 10";
			rs = stmt.executeQuery(str);
			while (rs.next()) {
				Model m = new Model();
				m.setNo_of_questions(rs.getInt(2));
				m.setStudent_score_avg(rs.getInt(3));
				m.setStudent_name(getStudentNameByStudentId(schoolId, classId, rs.getInt(1)));
/*				studentName = studentMap.entrySet().stream().filter(map -> sid == (map.getValue()))
						.map(map -> map.getKey()).collect(Collectors.joining(","));*/
				list.add(m);
			}
			// sorting
			list.sort((Model o1, Model o2) -> o1.getStudent_name().compareTo(o2.getStudent_name()));
			// con.close();
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return list;
	}
	
	
	/**
	 * This method is used to fetch submission date, student name and student test score avg from DB for TS Context
	 * @param con
	 * @param schoolName
	 * @param className
	 * @return
	 */
	public static List<Model> getStudentDetailListInTSInClass(Connection con, String schoolName, String className,String testName) {
		List<Model> list = new ArrayList<Model>();
		int schoolId = DatabaseConnection.getSchoolIDBySchoolName(schoolName);
		int classId = DatabaseConnection.getClassIDBySchoolNameAndClassName(schoolName, className);
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			String str="SELECT student_id,max(updatedat) as date_submitted,ROUND((sum(score) / sum(max_score))*100) AS Student_Score"+ 
						"FROM bec_edw_dev.bu_strand_details_vw WHERE bu_school_id = "+schoolId+" AND collective_noun_id ="+classId+
						" AND component_title='"+testName+"' GROUP BY student_id order by student_id limit 10";
			rs = stmt.executeQuery(str);
			while (rs.next()) {
				Model m = new Model();
				m.setMaxDate(rs.getDate(2));
				m.setStudent_score_avg(rs.getInt(3));
				m.setStudent_name(getStudentNameByStudentId(schoolId, classId, rs.getInt(1)));
				list.add(m);
			}
			// sorting
			list.sort((Model o1, Model o2) -> o1.getStudent_name().compareTo(o2.getStudent_name()));
			// con.close();
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return list;
	}

}

