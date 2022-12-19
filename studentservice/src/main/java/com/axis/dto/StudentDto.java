package com.axis.dto;

import jakarta.persistence.Id;

public class StudentDto {
	
	

	public StudentDto(int studentId, String studentName, String section) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.section = section;
	}
	public StudentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	private int studentId;
	private String studentName;
	private String section;
	/**
	 * @return the studentId
	 */
	public int getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	/**
	 * @return the studentName
	 */
	public String getStudentName() {
		return studentName;
	}
	/**
	 * @param studentName the studentName to set
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	/**
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	/**
	 * @param section the section to set
	 */
	public void setSection(String section) {
		this.section = section;
	}
	
}
