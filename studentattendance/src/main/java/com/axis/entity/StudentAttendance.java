package com.axis.entity;





import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class StudentAttendance {
	
	
	public StudentAttendance(/*int attendanceId,*/ int teacherId, int studentId, String studentName, String subject,
			String time, Date date, String status) {
		super();
		//this.attendanceId = attendanceId;
		this.teacherId = teacherId;
		this.studentId = studentId;
		this.studentName = studentName;
		this.subject = subject;
		this.time = time;
		this.date = date;
		this.status = status;
	}
	public StudentAttendance() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int attendanceId;
	private int teacherId;
	private int studentId;
	private String studentName;
	private String subject;
	private String time;
	private Date date;
	private String status;
	/**
	 * @return the attendanceId
	 */
	public int getAttendanceId() {
		return attendanceId;
	}
	/**
	 * @param attendanceId the attendanceId to set
	 */
	public void setAttendanceId(int attendanceId) {
		this.attendanceId = attendanceId;
	}
	/**
	 * @return the teacherId
	 */
	public int getTeacherId() {
		return teacherId;
	}
	/**
	 * @param teacherId the teacherId to set
	 */
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
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
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	

}
