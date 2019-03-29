package com.bec.reporting.utils;

public class Model {
	private Integer avg_per;
	private String standard_shortvalue;
	private String standard_category;	
	private String standard_subcategory;
	private String standard_description;

	@Override
	public String toString() {
		return "Model [avg_per=" + avg_per + ", standard_shortvalue=" + standard_shortvalue + ", standard_category="
				+ standard_category + ", standard_subcategory=" + standard_subcategory + ", standard_description="
				+ standard_description + "]";
	}

	public Integer getAvg_per() {
		return avg_per;
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

}
