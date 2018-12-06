package com.finvendor.modelpojo.staticpojo.admindashboard;

/**
 * 
 * @author ayush
 *
 */
public class ResearchReportFor implements Comparable<ResearchReportFor> {
	private int id;
	private String name;

	public ResearchReportFor(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(ResearchReportFor o) {
		return this.getName().compareTo(o.getName());
	}
}
