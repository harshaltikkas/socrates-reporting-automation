package com.bec.reporting.utils;

import java.util.Date;
import java.util.List;

public class Model {
	private Integer avg_per;
	private String standard_shortvalue;
	private String standard_category;
	private String standard_subcategory;
	private String standard_description;
	private String standard_id;
	private String component_title;
	private List<Integer> question_no;
	private Date minDate;
	private Date maxDate;

	public Integer getAvg_per() {
		return avg_per;
	}

	public List<Integer> getQuestion_no() {
		return question_no;
	}

	public void setQuestion_no(List<Integer> question_no) {
		this.question_no = question_no;
	}

	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}

	@Override
	public String toString() {
		return "Model [avg_per=" + avg_per + ", standard_shortvalue=" + standard_shortvalue + ", standard_category="
				+ standard_category + ", standard_subcategory=" + standard_subcategory + ", standard_description="
				+ standard_description + ", standard_id=" + standard_id + ", component_title=" + component_title
				+ ", question_no=" + question_no + ", minDate=" + minDate + ", maxDate=" + maxDate + "]";
	}

	public void setAvg_per(Integer avg_per) {
		this.avg_per = avg_per;
	}

	public String getStandard_shortvalue() {
		return standard_shortvalue;
	}

	public void setStandard_shortvalue(String standard_shortvalue) {
		this.standard_shortvalue = standard_shortvalue;
	}

	public String getStandard_category() {
		return standard_category;
	}

	public void setStandard_category(String standard_category) {
		this.standard_category = standard_category;
	}

	public String getStandard_subcategory() {
		return standard_subcategory;
	}

	public void setStandard_subcategory(String standard_subcategory) {
		this.standard_subcategory = standard_subcategory;
	}

	public String getStandard_description() {
		return standard_description;
	}

	public void setStandard_description(String standard_description) {
		this.standard_description = standard_description;
	}

	public String getStandard_id() {
		return standard_id;
	}

	public void setStandard_id(String standard_id) {
		this.standard_id = standard_id;
	}

	public String getComponent_title() {
		return component_title;
	}

	public void setComponent_title(String component_title) {
		this.component_title = component_title;
	}

}
