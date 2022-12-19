package com.axis.service;

import java.util.List;

import com.axis.dto.StudentAttendanceDto;

public interface StudentAttendanceService {
	
	public StudentAttendanceDto addAttendance(StudentAttendanceDto studentAttendanceDto);
	public List<StudentAttendanceDto> getAllAttendance();
	public StudentAttendanceDto updateAttendanceById(int attendanceId,StudentAttendanceDto studentAttendanceDto);
	public String deleteAttendanceById(int attendanceId);
	public StudentAttendanceDto getAttendanceById(int attendanceId);

}
