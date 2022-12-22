package com.axis.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.axis.dto.StudentAttendanceDto;

public interface StudentAttendanceService {
	
	public StudentAttendanceDto addAttendance(StudentAttendanceDto studentAttendanceDto);
	public List<StudentAttendanceDto> getAllAttendance();
	public StudentAttendanceDto updateAttendanceById(int attendanceId,StudentAttendanceDto studentAttendanceDto);
	public String deleteAttendanceById(int attendanceId);
	public StudentAttendanceDto getAttendanceById(int attendanceId);
	public List<StudentAttendanceDto> getAttendanceByStudentId(int studentId);
	public List<StudentAttendanceDto> getAttendanceByStudentIdandSubject(int studentId,String subject);
	public List<StudentAttendanceDto> getAttendanceBySubject(String subject);
	public List<StudentAttendanceDto> getAttendanceByDates(Date startDate,Date endDate);  
	public List<StudentAttendanceDto> getAttendanceForMonthBySubject(Date startDate,Date endDate,String subject);
	public List<StudentAttendanceDto> getAttendanceForMonthBySubjectForStudent(Date startDate,Date endDate,String subject,int studentId);
	public List<StudentAttendanceDto> getPresentAttendanceForMonthBySubjectForStudent(Date startDate,Date endDate,String subject,int studentId,String status);
	
	public Map<Integer,Double> calculateStudentPercentage(Date startDate,Date endDate,String subject,int studentId);
	public Map<Integer,Double> calculateAllPercentage(Date startDate,Date endDate,String subject);


}
