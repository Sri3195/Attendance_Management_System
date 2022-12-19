package com.axis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axis.dto.TeacherDto;
import com.axis.entity.Teacher;
import com.axis.service.TeacherService;

@RestController
@RequestMapping("/api/v1/TEACHER")
public class TeacherController {
	
	@Autowired
	TeacherService teacherService;
	
	@PostMapping("/teacher")
	public ResponseEntity<TeacherDto> addStudent(@RequestBody TeacherDto teacherDto){
		TeacherDto teacherDto2=teacherService.addStudent(teacherDto);
		return new ResponseEntity<TeacherDto>(teacherDto2,HttpStatus.OK);
		
	}
	
	@GetMapping("/teachers")
	public ResponseEntity<List<TeacherDto>> getAllStudents(){
		List<TeacherDto> teacherDtos= teacherService.getAllStudents();
		return new ResponseEntity<List<TeacherDto>>(teacherDtos,HttpStatus.OK);
	}
	
	@PutMapping("/teacher/{teacherId}")
	public ResponseEntity<TeacherDto> updateStudentById(@PathVariable int teacherId,@RequestBody TeacherDto teacherDto){
		TeacherDto teacherDto2=teacherService.updateStudentByID(teacherId, teacherDto);
		return new ResponseEntity<TeacherDto>(teacherDto2,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/teacher/{teacherId}")
	public ResponseEntity<String> deleteStudentById(@PathVariable int teacherId){
		String message=teacherService.deleteStudentByID(teacherId);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	@GetMapping("/teacher/{teacherId}")
	public ResponseEntity<TeacherDto> getStudentById(@PathVariable int teacherId)
	{
		TeacherDto teacherDto=teacherService.getStudentByID(teacherId);
		return new ResponseEntity<TeacherDto>(teacherDto,HttpStatus.OK);
	}
}
