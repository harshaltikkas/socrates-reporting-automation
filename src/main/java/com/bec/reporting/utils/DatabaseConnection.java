/*
 * Copyright Benchmark Education Company
 *
 * (C) Copyright BEC - All rights reserved.
 *
 * NOTICE:  All information contained herein or attendant here to is,
 *          and remains, the property of Benchmark.  Many of the
 *          intellectual and technical concepts contained herein are
 *          proprietary to Benchmark. Any dissemination of this
 *          information or reproduction of this material is strictly
 *          forbidden unless prior written permission is obtained
 *          from Benchmark.
 *
 * ------------------------------------------------------------------------
 *
 * ========================================================================
 * Revision History
 * ========================================================================
 * DATE				: PROGRAMMER  : DESCRIPTION
 * ========================================================================
 * MAR 10 2019		: BEC         : CREATED.
 * ------------------------------------------------------------------------
 *
 * ========================================================================
 */
package com.bec.reporting.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatabaseConnection {

	public static Map<String, Integer> schoolAvgRange = new HashMap<>();
	public static Map<String, Integer> districtAvgRange = new HashMap<>();
	public static Connection conn = ConnectionPool.getDBConnection();

	/**
	 * This method is used to get District ID based on school Id
	 * 
	 * @param con
	 * @param schoolId
	 * @return
	 */
	public static Integer getDistrictIdBySchoolId(Connection con, Integer schoolId) {
		Integer districtId = 0;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery(
					"select distinct(bu_district_id) FROM bec_edw.bu_assessment_reporting_detail WHERE bu_school_id = "
							+ schoolId + "");
			if (rs.next()) {
				districtId = rs.getInt(1);
			}
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		return districtId;
	}

	public static String getComponentCode(Connection con, Integer districtId, String sdate, String edate,
			String testName) {
		String compo_Code = "";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery(
					"SELECT DISTINCT component_code,component_title,submitted_at FROM bec_edw.bu_assessment_reporting_detail WHERE bu_district_id = "
							+ districtId + " " + "AND submitted_at BETWEEN '" + sdate + "' AND '" + edate
							+ "' AND component_title='" + testName + "' ORDER BY submitted_at DESC limit 1");
			if (rs.next()) {
				compo_Code = rs.getString(1);
			}
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		return compo_Code;
	}

	public static String getLatestComponentCode(Connection con, Integer districtId, String sdate, String edate) {
		String compo_Code = "";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery(
					"SELECT DISTINCT component_code,component_title,submitted_at FROM bec_edw.bu_assessment_reporting_detail WHERE bu_district_id = "
							+ districtId + " " + "AND submitted_at BETWEEN '" + sdate + "' AND '" + edate
							+ "' ORDER BY submitted_at DESC limit 1");
			if (rs.next()) {
				compo_Code = rs.getString(1);
			}
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		return compo_Code;
	}

	public static void main(String args[]) {
		// getDistrictTestScoreAvgInSTA(conn);
		getDistrictWiseViewReportInSTA(conn, "Benchmark Advance G1 U1 W1 Assessment");
		/*
		 * List<Model> lm = getStandardAvgPerListInClassContext(conn, 509446, 1213286,
		 * "Language"); for (Model model : lm) { System.out.println(model.getAvg_per());
		 * System.out.println(model.getStandard_shortvalue());
		 * System.out.println(model.getStandard_category());
		 * System.out.println(model.getStandard_subcategory());
		 * System.out.println(model.getStandard_description()); }
		 */
		// System.out.println(getViewTexonomy(conn, 509446, 1213286));
		// System.out.println(getSchoolIDBySchoolName("Church of England Primary
		// School"));
		// System.out.println(getAllSchoolNames());
		// System.out.println(getAllClassesNames("Church of England Primary School"));
		// System.out.println(getClassIDBySchoolNameAndClassName("Church of England
		// Primary School", "School V Trainer Class (PD)"));
		// System.out.println(getAllStudentsByClassNameAndSchoolName("Church of England
		// Primary School", "School V Trainer Class (PD)"));
		// System.out.println(getFirstAlphaLastNameStudentByAlphaClassAndSchoolName());
		// System.out.println(getStandardAvgPerListInClassContext(ConnectionPool.getDBConnection(),
		// 509446, 1213286, "Language"));
		// System.out.println(getTestScoreDetailsInClassContext(ConnectionPool.getDBConnection(),
		// 509446, 1213286, "Grade 4 Unit 1 Assessment", "CCSS.ELA-Literacy.L.4.2"));
		/*
		 * System.out.println(getStudentDetailListInSPInClassByStrand(ConnectionPool.
		 * getDBConnection(), 509446, 1213286, "Language"));
		 */
		// System.out.println(getSchoolAvgInTSInClass(DatabaseConnection.conn, 1130544,
		// "Grade 2 Unit 3 Assessment"));
	}

	/**************** Db Methods are here **********************/
	/**
	 * OK This method is used to get All Strand name based on School name and class
	 * Name
	 * 
	 * @param con
	 * @param schoolName
	 * @param className
	 * @return
	 */
	public static List<Object> getAllStrandBySchoolIdAndClassId(Connection con, Integer schoolId, Integer classId) {
		ArrayList<Object> strandlist = new ArrayList<Object>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery("Select distinct standard_category from bec_edw.bu_strand_details_vw sd inner join"
					+ " bec_edw.content_standard_common_core cscc on cscc.standard_id=sd.standard_id and bu_school_id="
					+ schoolId + "and collective_noun_id=" + classId + " order by standard_category");
			while (rs.next()) {
				strandlist.add(rs.getString(1));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return strandlist;
	}

	/**
	 * TODO -- Need to revisit this query to calculate avg This method is used to
	 * return Standard Details like average percentage of standard belong to the
	 * Strand name and based on school id and class id in class context
	 * 
	 * @param con
	 * @param schoolId
	 * @param classId
	 * @param strandName
	 * @return
	 */
	public static List<Model> getStandardAvgPerListInClassContext(Connection con, Integer schoolId, Integer classId,
			String strandName) {
		List<Model> list = new ArrayList<Model>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			String str = "Select round(SUM(sd.score)/SUM(sd.max_score)*100) as avg_per,cscc.standard_shortvalue,cscc.standard_category,cscc.standard_subcategory,cscc.standard_description, cscc.standard_id from  bec_edw.bu_strand_details_vw sd inner join bec_edw.content_standard_common_core cscc \r\n"
					+ "on cscc.standard_id=sd.standard_id and "
					+ "sd.component_title in (select distinct component_title from bec_edw.bu_assessment_reporting_detail where bu_school_id="
					+ schoolId + " and collective_noun_id=" + classId + ") and " + "sd.bu_school_id=" + schoolId
					+ " and sd.collective_noun_id=" + classId + " and "
					+ "sd.standard_id IN (select DISTINCT standard_id from bec_edw.content_standard_common_core where standard_category='"
					+ strandName
					+ "') group by cscc.standard_shortvalue, cscc.standard_category,cscc.standard_subcategory,cscc.standard_description,cscc.standard_id order by avg_per desc";
			rs = stmt.executeQuery(str);
			while (rs.next()) {
				Model m = new Model();
				m.setAvg_per(rs.getInt(1));
				m.setStandard_shortvalue(rs.getString(2));
				m.setStandard_category(rs.getString(3));
				m.setStandard_subcategory(rs.getString(2) + ": " + rs.getString(4));
				m.setStandard_description(rs.getString(5));
				m.setStandard_id(rs.getString(6));
				list.add(m);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return list;
	}

	/**
	 * TODO - Need to revisit this query This method is used to return Standard
	 * Details like average percentage of standard belong to the Strand name and
	 * based on school id and class id in student context
	 * 
	 * @param con
	 * @param schoolId
	 * @param classId
	 * @param strandName
	 * @return
	 */
	public static List<Model> getStandardAvgPerListInStudentContext(Connection con, Integer schoolId, Integer classId,
			String strandName, Integer studentId) {
		List<Model> list = new ArrayList<Model>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			String str = "Select round(SUM(sd.score)/SUM(sd.max_score)*100) as avg_per,cscc.standard_shortvalue,cscc.standard_category,cscc.standard_subcategory,cscc.standard_description from  bec_edw.bu_strand_details_vw sd inner join bec_edw.content_standard_common_core cscc \r\n"
					+ "on cscc.standard_id=sd.standard_id and "
					+ "sd.component_title in (select distinct component_title from bec_edw.bu_assessment_reporting_detail where bu_school_id="
					+ schoolId + " and collective_noun_id=" + classId + " and student_id=" + studentId + ") and "
					+ "sd.bu_school_id=" + schoolId + " and sd.collective_noun_id=" + classId + " and sd.student_id="
					+ studentId + " and "
					+ "sd.standard_id IN (select DISTINCT standard_id from bec_edw.content_standard_common_core where standard_category='"
					+ strandName
					+ "') group by cscc.standard_shortvalue, cscc.standard_category,cscc.standard_subcategory,cscc.standard_description  order by avg_per desc";
			rs = stmt.executeQuery(str);
			while (rs.next()) {
				Model m = new Model();
				m.setAvg_per(rs.getInt(1));
				m.setStandard_shortvalue(rs.getString(2));
				m.setStandard_category(rs.getString(3));
				m.setStandard_subcategory(rs.getString(2) + ": " + rs.getString(4));
				m.setStandard_description(rs.getString(5));
				list.add(m);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return list;
	}

	/***
	 * This query is used in line chart in SP in class context, will return
	 * avg-score and also question_no, on tooltip of avg_Score This method is used
	 * to return the avg score and component title on performance over time line
	 * chart in class context
	 * 
	 * @param con
	 * @param schoolId
	 * @param classId
	 * @param component_title
	 * @param standard_id
	 * @return
	 */
	public static List<Model> getTestScoreDetailsInSPInClassContext(Connection con, Integer schoolId, Integer classId,
			String component_title, String standard_id) {
		List<Model> list = new ArrayList<Model>();
		List<Integer> qList = new ArrayList<>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			String questionStr = "select distinct question_no from bec_edw.bu_strand_details_vw  WHERE bu_school_id = "
					+ schoolId + " " + "AND collective_noun_id = " + classId + " and component_title='"
					+ component_title + "'  AND " + "standard_id='" + standard_id + "' order by question_no ";
			rs = stmt.executeQuery(questionStr);
			while (rs.next()) {
				qList.add(rs.getInt(1));
			}
			String str = "select component_title,round(SUM(score)/SUM(max_score)*100) as avg_per, min(updatedat),max(updatedat) from bec_edw.bu_strand_details_vw  WHERE bu_school_id = "
					+ schoolId + " " + "AND collective_noun_id = " + classId + " and component_title='"
					+ component_title + "'  AND" + " standard_id='" + standard_id
					+ "' group by component_title order by avg_per desc";
			rs = stmt.executeQuery(str);
			while (rs.next()) {
				Model m = new Model();
				m.setComponent_title(rs.getString(1));
				m.setAvg_per(rs.getInt(2));
				m.setMinDate(rs.getDate(3));
				m.setMaxDate(rs.getDate(4));
				m.setQuestion_no(qList);
				list.add(m);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return list;
	}

	/**
	 * TODO- Need to revisit the query This method is used to fetch no. of
	 * questions, student name and student test score avg from DB for SP Context in
	 * class context
	 * 
	 * @param con
	 * @param schoolName
	 * @param className
	 * @param strandName
	 * @return
	 */
	public static List<Model> getStudentDetailListInSPInClassByStrand(Connection con, Integer schoolId, Integer classId,
			String strandName) {
		List<Model> list = new ArrayList<Model>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			String str = "";
			str = " SELECT student_id,COUNT(DISTINCT identifier) AS no_of_question,ROUND((sum(score) / sum(max_score))*100) AS Student_Score\r\n"
					+ "FROM bec_edw.bu_strand_details_vw WHERE bu_school_id = " + schoolId + "\r\n"
					+ "AND   collective_noun_id = " + classId
					+ " AND   component_title IN (SELECT DISTINCT component_title FROM bec_edw.bu_assessment_reporting_detail\r\n"
					+ " WHERE bu_school_id = " + schoolId + "\r\n" + " AND   collective_noun_id = " + classId + ")\r\n"
					+ "AND standard_id IN (SELECT DISTINCT standard_id\r\n"
					+ "FROM bec_edw.content_standard_common_core\r\n" + "WHERE standard_category = '" + strandName
					+ "')\r\n" + "GROUP BY student_id order by student_id limit 10";
			rs = stmt.executeQuery(str);
			while (rs.next()) {
				Model m = new Model();
				m.setNo_of_questions(rs.getInt(2));
				m.setStudent_score_avg(rs.getInt(3));
				m.setStudent_name(API_Connection.getStudentNameByStudentId(schoolId, classId, rs.getInt(1)));
				list.add(m);
			}
			// sorting
			list.sort((Model o1, Model o2) -> o1.getStudent_name().compareTo(o2.getStudent_name()));
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return list;
	}

	/**
	 * This method is used to fetch Strand avg from DB for SP Context in class
	 * context
	 * 
	 * @param con
	 * @param schoolName
	 * @param className
	 * @return
	 */
	public static Integer getStrandAvgInSPInClass(Connection con, Integer schoolId, Integer classId, String standard) {
		Integer avg = 0;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			int districtId = getDistrictIdBySchoolId(DatabaseConnection.conn, schoolId);
			String str = "SELECT ROUND((SUM(score)/SUM(max_score))*100) AS strand_Avg FROM (SELECT "
					+ "bu_district_id,bu_school_id,collective_noun_id,student_id,component_code,component_title,identifier,"
					+ "max(cast(updated_at as datetime)) as updated_at,max(max_score) max_score,score,"
					+ "standards,dense_rank() over(partition by bu_district_id,bu_school_id,collective_noun_id,student_id,component_code,component_title,identifier order by max(cast(updated_at as datetime)) desc) rnk "
					+ "FROM bec_edw.bu_assessment_reporting_detail where LTRIM(RTRIM(identifier))<>'' "
					+ "and bu_district_id = " + districtId + " AND   bu_school_id = " + schoolId
					+ " AND collective_noun_id = " + classId + " GROUP BY bu_district_id,"
					+ "bu_school_id,student_id,collective_noun_id,component_code,component_title,identifier,score,standards) main "
					+ "JOIN (SELECT gen_num FROM bec_edw.string_splitter_vw WHERE gen_num BETWEEN 1 AND (SELECT MAX(REGEXP_COUNT (standards,'\\,') + 1)"
					+ " FROM bec_edw.bu_assessment_reporting_detail_vw WHERE bu_district_id = " + districtId
					+ " AND bu_school_id = " + schoolId + " " + " AND collective_noun_id = " + classId
					+ ")) s ON 1 = 1 WHERE main.rnk=1 and LTRIM(RTRIM(SPLIT_PART(LTRIM(RTRIM(standards)),',',s.gen_num)))<>'' "
					+ " AND LTRIM(RTRIM(SPLIT_PART(LTRIM(RTRIM(standards)),',',s.gen_num))) like '%." + standard
					+ ".%'";
			rs = stmt.executeQuery(str);
			if (rs.next()) {
				avg = rs.getInt(1);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return avg;
	}

	/**
	 * This method is used to get student test score avg from DB for TS Context in
	 * Student context
	 * 
	 * @param con
	 * @param schoolName
	 * @param className
	 * @return
	 */
	public static Integer getStudentScoreAvgInTSInStudentContext(Connection con, Integer schoolId, Integer classId,
			Integer studentId, String componentTitle) {
		Integer avg = 0, ctr = 0;
		float sum_of_ts = 0, sum_of_max_score = 0;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			String str = "SELECT * from (SELECT bu_district_id,bu_school_id,collective_noun_id,student_id,component_code,component_title,test_score,"
					+ "updated_at,max_score,identifier,dense_rank() OVER (PARTITION BY student_id,component_code,identifier ORDER BY response_identifier DESC) rnk,"
					+ "dense_rank() OVER (PARTITION BY bu_district_id,bu_school_id,collective_noun_id,student_id,component_code,component_title,identifier ORDER BY CAST(updated_at AS datetime) DESC) rnk_latest_dt "
					+ "FROM bec_edw.bu_assessment_reporting_detail WHERE bu_district_id = "
					+ getDistrictIdBySchoolId(DatabaseConnection.conn, schoolId) + " AND bu_school_id = " + schoolId
					+ " AND collective_noun_id = " + classId + " AND student_id = " + studentId + ") X "
					+ "where rnk = 1 AND rnk_latest_dt = 1 AND component_title='" + componentTitle
					+ "' ORDER BY updated_at,identifier";
			rs = stmt.executeQuery(str);
			while (rs.next()) {
				ctr++;
				sum_of_ts += rs.getInt(7);
				sum_of_max_score += rs.getInt(9);
			}
			avg = Math.round(((sum_of_ts / ctr) / sum_of_max_score) * 100);
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return avg;
	}

	/**
	 * TODO-Testing Need to revisit query This method is used to fetch submission
	 * date, student name and student test score avg from DB for TS Context in class
	 * context
	 * 
	 * @param con
	 * @param schoolName
	 * @param className
	 * @return
	 */
	public static List<Model> getStudentDetailListInTSInClass(Connection con, Integer schoolId, Integer classId,
			String componentTitle) {
		List<Model> list = new ArrayList<Model>();
		Integer avg = 0, ctr = 0, studentId, sid = 0;
		Date submittedDate = null;
		float sum_of_ts = 0, sum_of_max_score = 0, actualSum = 0;
		Map<String, Integer> studentMap = new HashMap<>();
		try {
			boolean rowFound = false;
			studentMap = API_Connection.getAllStudentsByClassNameAndSchoolName(schoolId, classId);
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			for (Map.Entry<String, Integer> entry : studentMap.entrySet()) {
				studentId = entry.getValue();
				String str = "SELECT * from (SELECT bu_district_id,bu_school_id,collective_noun_id,student_id,component_code,component_title,test_score,"
						+ "updated_at,max_score,identifier,dense_rank() OVER (PARTITION BY student_id,component_code,identifier ORDER BY response_identifier DESC) rnk,"
						+ "dense_rank() OVER (PARTITION BY bu_district_id,bu_school_id,collective_noun_id,student_id,component_code,component_title,identifier ORDER BY CAST(updated_at AS datetime) DESC) rnk_latest_dt "
						+ "FROM bec_edw.bu_assessment_reporting_detail WHERE bu_district_id = "
						+ getDistrictIdBySchoolId(DatabaseConnection.conn, schoolId) + " AND bu_school_id = " + schoolId
						+ " AND collective_noun_id = " + classId + " AND student_id = " + studentId + ") X "
						+ "where rnk = 1 AND rnk_latest_dt = 1 AND component_title='" + componentTitle
						+ "' ORDER BY updated_at,identifier";
				rs = stmt.executeQuery(str);

				while (rs.next()) {
					ctr++;
					sum_of_ts += rs.getInt(7);
					sum_of_max_score += rs.getInt(9);
					sid = rs.getInt(4);
					submittedDate = rs.getDate(8);
					rowFound = true;
				}
				if (rowFound) {
					actualSum = (sum_of_ts / ctr);
					Model m = new Model();
					m.setStudent_name(API_Connection.getStudentNameByStudentId(schoolId, classId, sid));
					m.setMaxDate(submittedDate);
					avg = Math.round((actualSum / sum_of_max_score) * 100);
					m.setStudent_score_avg(avg);
					list.add(m);
					ctr = 0;
					sum_of_ts = 0;
					sum_of_max_score = 0;
					rowFound = false;
				}
			}
			// sorting
			list.sort((Model o1, Model o2) -> o1.getStudent_name().compareTo(o2.getStudent_name()));
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return list;
	}

	/**
	 * This method is used to get test score avg from DB for TS Context in class
	 * context
	 * 
	 * @param con
	 * @param schoolName
	 * @param className
	 * @return
	 */
	public static Integer getTestScoreAvgInTSInClassContext(Connection con, Integer schoolId, Integer classId,
			String componentTitle) {
		Integer avg = 0, ctr = 0, studentId;
		boolean rowFound = false;
		float sum_of_ts = 0, sum_of_max_score = 0, actualSum = 0;
		Map<String, Integer> studentMap = new HashMap<>();
		try {
			studentMap = API_Connection.getAllStudentsByClassNameAndSchoolName(schoolId, classId);
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			for (Map.Entry<String, Integer> entry : studentMap.entrySet()) {
				studentId = entry.getValue();
				String str = "SELECT * from (SELECT bu_district_id,bu_school_id,collective_noun_id,student_id,component_code,component_title,test_score,"
						+ "updated_at,max_score,identifier,dense_rank() OVER (PARTITION BY student_id,component_code,identifier ORDER BY response_identifier DESC) rnk,"
						+ "dense_rank() OVER (PARTITION BY bu_district_id,bu_school_id,collective_noun_id,student_id,component_code,component_title,identifier ORDER BY CAST(updated_at AS datetime) DESC) rnk_latest_dt "
						+ "FROM bec_edw.bu_assessment_reporting_detail WHERE bu_district_id = "
						+ getDistrictIdBySchoolId(DatabaseConnection.conn, schoolId) + " AND bu_school_id = " + schoolId
						+ " AND collective_noun_id = " + classId + " AND student_id = " + studentId + ") X "
						+ "where rnk = 1 AND rnk_latest_dt = 1 AND component_title='" + componentTitle
						+ "' ORDER BY updated_at,identifier";
				rs = stmt.executeQuery(str);
				while (rs.next()) {
					ctr++;
					sum_of_ts += rs.getInt(7);
					rowFound = true;
				}
				if (rowFound) {
					actualSum += (sum_of_ts / ctr);
					ctr = 0;
					sum_of_ts = 0;
					rowFound = false;
				}
			}
			String str = "SELECT * from (SELECT bu_district_id,bu_school_id,collective_noun_id,student_id,component_code,component_title,test_score,"
					+ "updated_at,max_score,identifier,dense_rank() OVER (PARTITION BY student_id,component_code,identifier ORDER BY response_identifier DESC) rnk,"
					+ "dense_rank() OVER (PARTITION BY bu_district_id,bu_school_id,collective_noun_id,student_id,component_code,component_title,identifier ORDER BY CAST(updated_at AS datetime) DESC) rnk_latest_dt "
					+ "FROM bec_edw.bu_assessment_reporting_detail WHERE bu_district_id = "
					+ getDistrictIdBySchoolId(DatabaseConnection.conn, schoolId) + " AND bu_school_id = " + schoolId
					+ " AND collective_noun_id = " + classId + ") X "
					+ "where rnk = 1 AND rnk_latest_dt = 1 AND component_title='" + componentTitle
					+ "' ORDER BY updated_at,identifier";
			rs = stmt.executeQuery(str);
			while (rs.next()) {
				ctr++;
				sum_of_max_score += rs.getInt(9);
			}
			avg = Math.round(((actualSum) / sum_of_max_score) * 100);
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return avg;
	}

	/**
	 * This method is used to calculate school score avg from DB for TS Context in
	 * class context
	 * 
	 * @param con
	 * @param schoolName
	 * @param className
	 * @return
	 */
	public static Map<Integer, Integer> getSchoolAvgInTSInClass(Connection con, Integer schoolId,
			String componentTitle) {
		Map<Integer, Integer> schoolResult = new HashMap<>();
		Set<Integer> studentSet = new HashSet<>();
		Integer studentId, resultCount = 0;
		float avg = 0, sum_of_ts = 0, sum_of_max_score = 0, studentAvg = 0;
		Map<Integer, Float> duplicateMapforMaxScore = new HashMap<>();
		Map<Integer, Float> duplicateMapforTestScore = new HashMap<>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			String str = "SELECT * from (SELECT bu_district_id,bu_school_id,collective_noun_id,student_id,component_code,component_title,test_score,"
					+ "updated_at,max_score,identifier,dense_rank() OVER (PARTITION BY student_id,component_code,identifier ORDER BY response_identifier DESC) rnk,"
					+ "dense_rank() OVER (PARTITION BY bu_district_id,bu_school_id,collective_noun_id,student_id,component_code,component_title,identifier ORDER BY CAST(updated_at AS datetime) DESC) rnk_latest_dt "
					+ "FROM bec_edw.bu_assessment_reporting_detail WHERE bu_district_id = "
					+ getDistrictIdBySchoolId(con, schoolId) + " AND bu_school_id = " + schoolId + " ) X "
					+ "where rnk = 1 AND rnk_latest_dt = 1 AND component_title='" + componentTitle
					+ "' ORDER BY updated_at,identifier";
			rs = stmt.executeQuery(str);
			while (rs.next()) {
				sum_of_max_score += rs.getInt(9);
				studentId = rs.getInt(4);
				if (studentSet.contains(studentId)) {
					duplicateMapforMaxScore.put(studentId, duplicateMapforMaxScore.get(studentId) + rs.getInt(9));
					continue;
				} else {
					resultCount++;
					studentSet.add(studentId);
					sum_of_ts += rs.getInt(7);
					duplicateMapforTestScore.put(studentId, (float) rs.getInt(7));
					duplicateMapforMaxScore.put(studentId, (float) rs.getInt(9));
				}
			}

			avg = ((sum_of_ts / sum_of_max_score) * 100);
			schoolResult.put(Math.round(avg), resultCount);
			float ts = 0;
			int fcount = 0, fiftycount = 0, sixtycount = 0, eightycount = 0;
			for (Map.Entry<Integer, Float> entry : duplicateMapforMaxScore.entrySet()) {
				ts = duplicateMapforTestScore.get(entry.getKey());
				studentAvg = Math.round(ts / entry.getValue() * 100);
				if (studentAvg < 40) {
					fcount++;
				} else if (studentAvg >= 40 && studentAvg <= 59) {
					fiftycount++;
				} else if (studentAvg >= 60 && studentAvg <= 79) {
					sixtycount++;
				} else {
					eightycount++;
				}
			}
			schoolAvgRange.put("<40", fcount);
			schoolAvgRange.put("40-59", fiftycount);
			schoolAvgRange.put("60-79", sixtycount);
			schoolAvgRange.put("80>", eightycount);

		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return schoolResult;
	}

	/**
	 * This method is used to calculate District score avg from DB for TS Context in
	 * class context
	 * 
	 * @param con
	 * @param schoolName
	 * @param className
	 * @return
	 */
	public static Map<Integer, Integer> getDistrictAvgInTSInClass(Connection con, Integer districtId,
			String componentTitle) {
		Map<Integer, Integer> districtResult = new HashMap<>();
		Set<Integer> studentSet = new HashSet<>();
		Integer studentId, resultCount = 0;
		float avg = 0, sum_of_ts = 0, sum_of_max_score = 0, studentAvg = 0;
		Map<Integer, Float> duplicateMapforMaxScore = new HashMap<>();
		Map<Integer, Float> duplicateMapforTestScore = new HashMap<>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			String str = "SELECT * from (SELECT bu_district_id,bu_school_id,collective_noun_id,student_id,component_code,component_title,test_score,"
					+ "updated_at,max_score,identifier,dense_rank() OVER (PARTITION BY student_id,component_code,identifier ORDER BY response_identifier DESC) rnk,"
					+ "dense_rank() OVER (PARTITION BY bu_district_id,bu_school_id,collective_noun_id,student_id,component_code,component_title,identifier ORDER BY CAST(updated_at AS datetime) DESC) rnk_latest_dt "
					+ "FROM bec_edw.bu_assessment_reporting_detail WHERE bu_district_id = " + districtId + " ) X "
					+ "where rnk = 1 AND rnk_latest_dt = 1 AND component_title='" + componentTitle
					+ "' ORDER BY updated_at,identifier";
			rs = stmt.executeQuery(str);
			while (rs.next()) {
				sum_of_max_score += rs.getInt(9);
				studentId = rs.getInt(4);
				if (studentSet.contains(studentId)) {
					duplicateMapforMaxScore.put(studentId, duplicateMapforMaxScore.get(studentId) + rs.getInt(9));
					continue;
				} else {
					resultCount++;
					studentSet.add(studentId);
					sum_of_ts += rs.getInt(7);
					duplicateMapforTestScore.put(studentId, (float) rs.getInt(7));
					duplicateMapforMaxScore.put(studentId, (float) rs.getInt(9));
				}
			}

			avg = ((sum_of_ts / sum_of_max_score) * 100);
			districtResult.put(Math.round(avg), resultCount);
			float ts = 0;
			int fcount = 0, fiftycount = 0, sixtycount = 0, eightycount = 0;
			for (Map.Entry<Integer, Float> entry : duplicateMapforMaxScore.entrySet()) {
				ts = duplicateMapforTestScore.get(entry.getKey());
				studentAvg = Math.round(ts / entry.getValue() * 100);
				if (studentAvg < 40) {
					fcount++;
				} else if (studentAvg >= 40 && studentAvg <= 59) {
					fiftycount++;
				} else if (studentAvg >= 60 && studentAvg <= 79) {
					sixtycount++;
				} else {
					eightycount++;
				}
			}
			districtAvgRange.put("<40", fcount);
			districtAvgRange.put("40-59", fiftycount);
			districtAvgRange.put("60-79", sixtycount);
			districtAvgRange.put("80>", eightycount);
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return districtResult;
	}

	/**
	 * This method is used to return the View Texonomy text from DB
	 * 
	 * @param con
	 * @param schoolId
	 * @param classId
	 * @return
	 */
	public static String getViewTexonomy(Connection con, Integer schoolId, Integer classId) {
		String taxonomy = "", viewText = "";
		int size = 0, ccss_count = 0, caccss_count = 0;
		List<String> ls = new ArrayList<String>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			String str = "Select cscc.standard_id from bec_edw.bu_strand_details_vw sd inner join bec_edw.content_standard_common_core cscc \r\n"
					+ "on cscc.standard_id=sd.standard_id and "
					+ "sd.component_title in (select distinct component_title from bec_edw.bu_assessment_reporting_detail where bu_school_id="
					+ schoolId + " and collective_noun_id=" + classId + ") and " + "sd.bu_school_id=" + schoolId
					+ " and sd.collective_noun_id=" + classId + " group by cscc.standard_id";
			rs = stmt.executeQuery(str);
			while (rs.next()) {
				taxonomy = rs.getString(1);
				ls.add(taxonomy.substring(0, taxonomy.indexOf(".")));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		size = ls.size();
		for (String string : ls) {
			if (string.equals("CCSS")) {
				ccss_count++;
			}
			if (string.equals("CaCCSS")) {
				caccss_count++;
			}
		}

		if (size == ccss_count) {
			viewText = "CCSS English Language Arts";
		} else if (size == caccss_count) {
			viewText = "CaCCSS English Language Arts";
		}
		return viewText;
	}

	/**
	 * This method is used to fetch Grade List from DB for school and class
	 * 
	 * @param con
	 * @param schoolId
	 * @param classId
	 * @return
	 */
	public static List<String> getGradeTexonomy(Connection con, Integer schoolId, Integer classId) {
		String taxonomy = "";
		List<String> ls = new ArrayList<String>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			String str = "SELECT distinct(sd.grade) FROM bec_edw.bu_assessment_reporting_detail sd where sd.bu_school_id = "
					+ schoolId + " and sd.collective_noun_id=" + classId;
			rs = stmt.executeQuery(str);
			while (rs.next()) {
				taxonomy = rs.getString(1);
				ls.add(convert(taxonomy).replace("_", " "));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return ls;
	}

	/**
	 * This method is used to fetch Grade Text from DB for Student
	 * 
	 * @param con
	 * @param schoolId
	 * @param classId
	 * @param studentId
	 * @return
	 */
	public static String getGradeTexonomyForStudent(Connection con, Integer schoolId, Integer classId,
			Integer studentId) {
		String gradeTaxonomy = "";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			String str = "SELECT distinct(sd.grade) FROM bec_edw.bu_assessment_reporting_detail sd where sd.bu_school_id = "
					+ schoolId + " and sd.collective_noun_id=" + classId + " AND sd.student_id=" + studentId;
			rs = stmt.executeQuery(str);
			while (rs.next()) {
				gradeTaxonomy = rs.getString(1);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return gradeTaxonomy;
	}

	static String convert(String str) {

		// Create a char array of given String
		char ch[] = str.toCharArray();
		for (int i = 0; i < str.length(); i++) {

			// If first character of a word is found
			if (i == 0 && ch[i] != ' ' || ch[i] != ' ' && ch[i - 1] == ' ') {

				// If it is in lower-case
				if (ch[i] >= 'a' && ch[i] <= 'z') {

					// Convert into Upper-case
					ch[i] = (char) (ch[i] - 'a' + 'A');
				}
			}

			// If apart from first character
			// Any one is in Upper-case
			else if (ch[i] >= 'A' && ch[i] <= 'Z')

				// Convert into Lower-Case
				ch[i] = (char) (ch[i] + 'a' - 'A');
		}

		// Convert the char array to equivalent String
		String st = new String(ch);
		return st;
	}

	// <==============TODO=========New Queries=====================>
	/**
	 * This method is used to calculate District Test score avg from DB for STA
	 * Context
	 * 
	 * @param con
	 * @return
	 */
	public static String currentFY;

	public static Integer getDistrictTestScoreAvgInSTA(Connection con) {
		Integer avg = 0;
		Set<Integer> studentSet = new HashSet<>();
		Integer studentId;
		float sum_of_ts = 0, sum_of_max_score = 0;

		int distId = API_Connection.getDistrictId();
		String sdate = API_Connection.getDistrictWiseLatestTermDateRangeUsingAPI(distId).get(0);
		String edate = API_Connection.getDistrictWiseLatestTermDateRangeUsingAPI(distId).get(1);
		currentFY = API_Connection.getDistrictWiseLatestTermDateRangeUsingAPI(distId).get(2);
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			String str = "SELECT * FROM (SELECT bu_district_id,bu_school_id,collective_noun_id,student_id,component_code,bu_assignment_id,component_title,test_score,submitted_at,max_score,identifier,DENSE_RANK() OVER (PARTITION BY student_id,component_code,bu_assignment_id,identifier ORDER BY response_identifier DESC) rnk,DENSE_RANK() OVER (PARTITION BY bu_district_id,bu_school_id,collective_noun_id,student_id,component_code,identifier ORDER BY updated_at DESC) latest_rnk "
					+ "FROM bec_edw.bu_assessment_reporting_detail Where bu_district_id = " + distId
					+ " AND component_code = '" + DatabaseConnection.getLatestComponentCode(con, distId, sdate, edate)
					+ "' " + "AND submitted_at BETWEEN '" + sdate + "' AND '" + edate
					+ "' GROUP BY bu_district_id,  bu_school_id,  collective_noun_id,  student_id,  updated_at,  component_code,  bu_assignment_id,  component_title,  test_score,  submitted_at,  max_score,  identifier,  response_identifier) X "
					+ "WHERE rnk = 1 AND   latest_rnk = 1 ORDER BY submitted_at, identifier";

			rs = stmt.executeQuery(str);
			while (rs.next()) {
				sum_of_max_score += rs.getInt(10);
				studentId = rs.getInt(4);
				if (studentSet.contains(studentId)) {
					continue;
				} else {
					studentSet.add(studentId);
					sum_of_ts += rs.getInt(8);
				}
			}

			avg = Math.round((sum_of_ts / sum_of_max_score) * 100);
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return avg;
	}

	public static Integer getDistrictTestScoreAvgInSTAWithSpecificTestName(Connection con, String testName) {
		Integer avg = 0;
		Set<Integer> studentSet = new HashSet<>();
		Integer studentId;
		float sum_of_ts = 0, sum_of_max_score = 0;

		int distId = API_Connection.getDistrictId();
		String sdate = API_Connection.getDistrictWiseLatestTermDateRangeUsingAPI(distId).get(0);
		String edate = API_Connection.getDistrictWiseLatestTermDateRangeUsingAPI(distId).get(1);
		currentFY = API_Connection.getDistrictWiseLatestTermDateRangeUsingAPI(distId).get(2);
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			String str = "SELECT * FROM (SELECT bu_district_id,bu_school_id,collective_noun_id,student_id,component_code,bu_assignment_id,component_title,test_score,submitted_at,max_score,identifier,DENSE_RANK() OVER (PARTITION BY student_id,component_code,bu_assignment_id,identifier ORDER BY response_identifier DESC) rnk,DENSE_RANK() OVER (PARTITION BY bu_district_id,bu_school_id,collective_noun_id,student_id,component_code,identifier ORDER BY updated_at DESC) latest_rnk "
					+ "FROM bec_edw.bu_assessment_reporting_detail Where bu_district_id = " + distId
					+ " AND component_code = '"
					+ DatabaseConnection.getComponentCode(con, distId, sdate, edate, testName) + "' "
					+ "AND submitted_at BETWEEN '" + sdate + "' AND '" + edate
					+ "' GROUP BY bu_district_id,  bu_school_id,  collective_noun_id,  student_id,  updated_at,  component_code,  bu_assignment_id,  component_title,  test_score,  submitted_at,  max_score,  identifier,  response_identifier) X "
					+ "WHERE rnk = 1 AND   latest_rnk = 1 ORDER BY submitted_at, identifier";

			rs = stmt.executeQuery(str);
			while (rs.next()) {
				sum_of_max_score += rs.getInt(10);
				studentId = rs.getInt(4);
				if (studentSet.contains(studentId)) {
					continue;
				} else {
					studentSet.add(studentId);
					sum_of_ts += rs.getInt(8);
				}
			}

			avg = Math.round((sum_of_ts / sum_of_max_score) * 100);
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return avg;
	}

	public static Map<Integer, Integer> getDistrictWiseQuestionAndTestScoreAvgInSTAWithSpecificTestName(Connection con,
			String testName) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		String ident;
		int distId = API_Connection.getDistrictId();
		String sdate = API_Connection.getDistrictWiseLatestTermDateRangeUsingAPI(distId).get(0);
		String edate = API_Connection.getDistrictWiseLatestTermDateRangeUsingAPI(distId).get(1);

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			String str = "SELECT identifier,SUM(score) score,SUM(max_score) max_score FROM (SELECT standard, bu_school_id,collective_noun_id,student_id,grade,identifier,bu_assignment_id,SUM(score) score,MAX(max_score)max_score FROM"
					+ " (SELECT SPLIT_PART(standards,',',s.gen_num) AS standard,bu_school_id,collective_noun_id,student_id,grade,identifier,response_identifier,bu_assignment_id,score,max_score FROM (SELECT student_id,collective_noun_id,bu_school_id,identifier,grade,bu_assignment_id,response_identifier,score,max_score,standards,standards_cnt,DENSE_RANK() OVER (PARTITION BY bu_district_id,bu_school_id,collective_noun_id,student_id,component_code,identifier,response_identifier ORDER BY updated_at DESC) rnk "
					+ " FROM bec_edw.bu_assessment_reporting_detail WHERE bu_district_id = " + distId
					+ " AND   component_code = '"
					+ DatabaseConnection.getComponentCode(con, distId, sdate, edate, testName) + "' "
					+ " AND   submitted_at BETWEEN '" + sdate + "' AND '" + edate
					+ "' GROUP BY bu_district_id,  bu_school_id,  student_id,  grade,  bu_assignment_id,  collective_noun_id,  component_code,  score,  max_score,  updated_at,  identifier,  response_identifier,  submitted_at,  standards,  standards_cnt) main,    bec_edw.string_splitter s     WHERE main.rnk = 1     AND   s.gen_num <= main.standards_cnt     AND   SPLIT_PART(standards,',',s.gen_num) <> '') "
					+ " GROUP BY  standard,bu_school_id,collective_noun_id,student_id,grade,identifier,bu_assignment_id) GROUP BY identifier order by identifier limit 10";

			rs = stmt.executeQuery(str);
			while (rs.next()) {
				ident = rs.getString(1);
				map.put(Integer.parseInt(ident.substring(ident.length() - 3)),
						Math.round((rs.getFloat(2) / rs.getFloat(3)) * 100));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return map;
	}

	public static List<String> getDistrictWiseTestScoreAvgInRawFormatInSTAWithSpecificTestName(Connection con,
			String testName) {
		List<String> list = new LinkedList<String>();
		String ident;
		Float avg = 0.0f;
		Set<Integer> studentSet = new HashSet<>();
		Set<String> identifierSet = new HashSet<>();
		Integer studentId;
		float sum_of_ts = 0;
		int question_count = 0, ts_count = 0;
		int distId = API_Connection.getDistrictId();
		String sdate = API_Connection.getDistrictWiseLatestTermDateRangeUsingAPI(distId).get(0);
		String edate = API_Connection.getDistrictWiseLatestTermDateRangeUsingAPI(distId).get(1);

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			String str = "SELECT * FROM (SELECT bu_district_id,bu_school_id,collective_noun_id,student_id,component_code,bu_assignment_id,component_title,test_score,submitted_at,max_score,identifier,DENSE_RANK() OVER (PARTITION BY student_id,component_code,bu_assignment_id,identifier ORDER BY response_identifier DESC) rnk,DENSE_RANK() OVER (PARTITION BY bu_district_id,bu_school_id,collective_noun_id,student_id,component_code,identifier ORDER BY updated_at DESC) latest_rnk "
					+ "FROM bec_edw.bu_assessment_reporting_detail Where bu_district_id = " + distId
					+ " AND component_code = '"
					+ DatabaseConnection.getComponentCode(con, distId, sdate, edate, testName) + "' "
					+ "AND submitted_at BETWEEN '" + sdate + "' AND '" + edate
					+ "' GROUP BY bu_district_id,  bu_school_id,  collective_noun_id,  student_id,  updated_at,  component_code,  bu_assignment_id,  component_title,  test_score,  submitted_at,  max_score,  identifier,  response_identifier) X "
					+ "WHERE rnk = 1 AND   latest_rnk = 1 ORDER BY submitted_at, identifier";

			rs = stmt.executeQuery(str);
			while (rs.next()) {
				ident = rs.getString(11);
				studentId = rs.getInt(4);
				if (identifierSet.contains(ident)) {
				} else {
					identifierSet.add(ident);
					question_count++;
				}

				if (studentSet.contains(studentId)) {
				} else {
					studentSet.add(studentId);
					sum_of_ts += rs.getInt(8);
					ts_count++;
				}
			}

			avg = (sum_of_ts / ts_count);

			list.add(new DecimalFormat("##.##").format(avg) + "/" + question_count);
			question_count = 0;
			ts_count = 0;
			identifierSet.clear();
			Map<String, String> temp_Map = new TreeMap<String, String>();
			Integer sum_of_score = 0;
			String str1 = "SELECT standard, bu_school_id,collective_noun_id,student_id,grade,identifier,bu_assignment_id,SUM(score) score,MAX(max_score)max_score FROM"
					+ " (SELECT SPLIT_PART(standards,',',s.gen_num) AS standard,bu_school_id,collective_noun_id,student_id,grade,identifier,response_identifier,bu_assignment_id,score,max_score FROM (SELECT student_id,collective_noun_id,bu_school_id,identifier,grade,bu_assignment_id,response_identifier,score,max_score,standards,standards_cnt,DENSE_RANK() OVER (PARTITION BY bu_district_id,bu_school_id,collective_noun_id,student_id,component_code,identifier,response_identifier ORDER BY updated_at DESC) rnk "
					+ " FROM bec_edw.bu_assessment_reporting_detail WHERE bu_district_id = " + distId
					+ " AND   component_code = '"
					+ DatabaseConnection.getComponentCode(con, distId, sdate, edate, testName) + "' "
					+ " AND   submitted_at BETWEEN '" + sdate + "' AND '" + edate
					+ "' GROUP BY bu_district_id,  bu_school_id,  student_id,  grade,  bu_assignment_id,  collective_noun_id,  component_code,  score,  max_score,  updated_at,  identifier,  response_identifier,  submitted_at,  standards,  standards_cnt) main,    bec_edw.string_splitter s     WHERE main.rnk = 1     AND   s.gen_num <= main.standards_cnt     AND   SPLIT_PART(standards,',',s.gen_num) <> '') "
					+ " GROUP BY  standard,bu_school_id,collective_noun_id,student_id,grade,identifier,bu_assignment_id";

			rs = stmt.executeQuery(str1);
			String score = "", count = "";
			while (rs.next()) {
				ident = rs.getString(6);
				if (temp_Map.containsKey(ident)) {
					sum_of_score = rs.getInt(8);
					score = temp_Map.get(ident).substring(0, temp_Map.get(ident).indexOf("_"));
					count = temp_Map.get(ident).substring(temp_Map.get(ident).indexOf("_") + 1);
					temp_Map.put(ident, (Integer.parseInt(score) + sum_of_score) + "_" + (Integer.parseInt(count) + 1));
				} else {
					sum_of_score = rs.getInt(8);
					temp_Map.put(ident, sum_of_score + "_" + 1);
				}

			}

			for (Map.Entry<String, String> tm : temp_Map.entrySet()) {
				list.add(new DecimalFormat("##.##")
						.format(Float.parseFloat(tm.getValue().substring(0, tm.getValue().indexOf("_")))
								/ Float.parseFloat(tm.getValue().substring(tm.getValue().indexOf("_") + 1)))
						+ "/" + 1);
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return list;
	}

	/**
	 * This method is used to calculate the avg in District column using question in
	 * STA
	 * 
	 * @param con
	 * @return
	 */
	public static Map<Integer, Integer> getDistrictWiseQuestionAndTestScoreAvgInSTA(Connection con) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		String ident;
		int distId = API_Connection.getDistrictId();
		String sdate = API_Connection.getDistrictWiseLatestTermDateRangeUsingAPI(distId).get(0);
		String edate = API_Connection.getDistrictWiseLatestTermDateRangeUsingAPI(distId).get(1);

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			String str = "SELECT identifier,SUM(score) score,SUM(max_score) max_score FROM (SELECT standard, bu_school_id,collective_noun_id,student_id,grade,identifier,bu_assignment_id,SUM(score) score,MAX(max_score)max_score FROM"
					+ " (SELECT SPLIT_PART(standards,',',s.gen_num) AS standard,bu_school_id,collective_noun_id,student_id,grade,identifier,response_identifier,bu_assignment_id,score,max_score FROM (SELECT student_id,collective_noun_id,bu_school_id,identifier,grade,bu_assignment_id,response_identifier,score,max_score,standards,standards_cnt,DENSE_RANK() OVER (PARTITION BY bu_district_id,bu_school_id,collective_noun_id,student_id,component_code,identifier,response_identifier ORDER BY updated_at DESC) rnk "
					+ " FROM bec_edw.bu_assessment_reporting_detail WHERE bu_district_id = " + distId
					+ " AND   component_code = '" + DatabaseConnection.getLatestComponentCode(con, distId, sdate, edate)
					+ "' " + " AND   submitted_at BETWEEN '" + sdate + "' AND '" + edate
					+ "' GROUP BY bu_district_id,  bu_school_id,  student_id,  grade,  bu_assignment_id,  collective_noun_id,  component_code,  score,  max_score,  updated_at,  identifier,  response_identifier,  submitted_at,  standards,  standards_cnt) main,    bec_edw.string_splitter s     WHERE main.rnk = 1     AND   s.gen_num <= main.standards_cnt     AND   SPLIT_PART(standards,',',s.gen_num) <> '') "
					+ " GROUP BY  standard,bu_school_id,collective_noun_id,student_id,grade,identifier,bu_assignment_id) GROUP BY identifier order by identifier limit 10";

			rs = stmt.executeQuery(str);
			while (rs.next()) {
				ident = rs.getString(1);
				map.put(Integer.parseInt(ident.substring(ident.length() - 3)),
						Math.round((rs.getFloat(2) / rs.getFloat(3)) * 100));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
		return map;
	}

	/**
	 * This method is used to return viewWiseSTAinDistrictLevel
	 * 
	 * @param con
	 * @return
	 */
	public static Map<String, String> getDistrictWiseViewReportInSTA(Connection con, String testName) {
		Map<String, String> final_map = new TreeMap<String, String>();
		String standard, identifier;

		int distId = API_Connection.getDistrictId();
		String sdate = API_Connection.getDistrictWiseLatestTermDateRangeUsingAPI(distId).get(0);
		String edate = API_Connection.getDistrictWiseLatestTermDateRangeUsingAPI(distId).get(1);

		Map<String, String> temp_Map = new TreeMap<String, String>();
		List<Integer> ident = new ArrayList<Integer>();
		List<Integer> unique_ident = new ArrayList<Integer>();
		float sum_of_score = 0, sum_of_max_score = 0;

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;

			String str = "SELECT standard,bu_school_id,collective_noun_id,student_id,grade,identifier,bu_assignment_id,SUM(score) score,MAX(max_score) max_score "
					+ "FROM (SELECT SPLIT_PART(standards,',',s.gen_num) AS standard,bu_school_id,collective_noun_id,student_id,grade,identifier,response_identifier,bu_assignment_id,score,max_score "
					+ "FROM (SELECT student_id,collective_noun_id,bu_school_id,identifier,grade,bu_assignment_id,response_identifier,score,max_score,standards,standards_cnt,DENSE_RANK() OVER (PARTITION BY bu_district_id,bu_school_id,collective_noun_id,student_id,component_code,identifier,response_identifier ORDER BY updated_at DESC) rnk     FROM bec_edw.bu_assessment_reporting_detail     WHERE bu_district_id = "
					+ distId + " AND   component_code = '"
					+ DatabaseConnection.getComponentCode(con, distId, sdate, edate, testName) + "' "
					+ " AND   submitted_at BETWEEN '" + sdate + "' AND '" + edate
					+ "' GROUP BY bu_district_id,bu_school_id,student_id,grade,bu_assignment_id,collective_noun_id,component_code,score,max_score,updated_at,identifier,response_identifier,submitted_at,standards,standards_cnt) main,  bec_edw.string_splitter s"
					+ " WHERE main.rnk = 1 AND   s.gen_num <= main.standards_cnt AND   SPLIT_PART(standards,',',s.gen_num) <> '') GROUP BY standard,bu_school_id,collective_noun_id,student_id,grade,identifier,bu_assignment_id order by standard,identifier";

			rs = stmt.executeQuery(str);

			while (rs.next()) {
				standard = rs.getString(1);
				identifier = rs.getString(6);
				if (temp_Map.containsKey(standard)) {
					sum_of_score += rs.getFloat(8);
					sum_of_max_score += rs.getFloat(9);
					ident.add(Integer.parseInt(identifier.substring(identifier.length() - 3)));
					unique_ident = ident.stream().distinct().collect(Collectors.toList());
					temp_Map.put(standard, unique_ident + "_" + sum_of_score + "_" + sum_of_max_score);
				} else {
					sum_of_score = rs.getFloat(8);
					sum_of_max_score = rs.getFloat(9);
					ident.clear();
					ident.add(Integer.parseInt(identifier.substring(identifier.length() - 3)));
					temp_Map.put(standard, ident + "_" + sum_of_score + "_" + sum_of_max_score);
				}
			}

			// Get keys and values
			String ids[] = new String[temp_Map.size()];
			Float sc[] = new Float[temp_Map.size()], msc[] = new Float[temp_Map.size()];
			String questions[] = new String[temp_Map.size()];
			int x = 0;
			for (Map.Entry<String, String> entry : temp_Map.entrySet()) {
				ids[x] = entry.getKey();
				questions[x] = entry.getValue().substring(0, entry.getValue().indexOf("_"));
				sc[x] = Float.parseFloat(entry.getValue().substring(entry.getValue().indexOf("_") + 1,
						entry.getValue().lastIndexOf("_")));
				msc[x] = Float.parseFloat(entry.getValue().substring(entry.getValue().lastIndexOf("_") + 1));
				x++;
			}

			final_map = API_Connection.getStandardsInfo_Short_Value_UsingAPI(ids, sc, msc, questions);

		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}

		return final_map;
	}

}
