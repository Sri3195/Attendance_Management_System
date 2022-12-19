package com.axis.service;

import java.util.List;

import com.axis.dto.TeacherDto;

public interface TeacherService {
	
	public TeacherDto addStudent(TeacherDto teacherDto);
	public List<TeacherDto> getAllStudents();
	public TeacherDto updateStudentByID(int teacherId,TeacherDto teacherDto);
	public String deleteStudentByID(int teacherId);
	public TeacherDto getStudentByID(int teacherId);

}
