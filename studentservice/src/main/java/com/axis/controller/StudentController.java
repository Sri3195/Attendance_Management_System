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

import com.axis.dto.StudentDto;
import com.axis.entity.Student;
import com.axis.service.StudentService;

@RestController
@RequestMapping("/api/v1/STUDENT")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@PostMapping("/student")
	public ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto studentDto){
		StudentDto studentDto2=studentService.addStudent(studentDto);
		return new ResponseEntity<StudentDto>(studentDto2,HttpStatus.OK);
		
	}
	
	@GetMapping("/students")
	public ResponseEntity<List<StudentDto>> getAllStudents(){
		List<StudentDto> studentDtos= studentService.getAllStudents();
		return new ResponseEntity<List<StudentDto>>(studentDtos,HttpStatus.OK);
	}
	
	@PutMapping("/student/{studentId}")
	public ResponseEntity<StudentDto> updateStudentById(@PathVariable int studentId,@RequestBody StudentDto studentDto){
		StudentDto studentDto2=studentService.updateStudentByID(studentId, studentDto);
		return new ResponseEntity<StudentDto>(studentDto2,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/student/{studentId}")
	public ResponseEntity<String> deleteStudentById(@PathVariable int studentId){
		String message=studentService.deleteStudentByID(studentId);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	@GetMapping("/student/{studentId}")
	public ResponseEntity<StudentDto> getStudentById(@PathVariable int studentId)
	{
		StudentDto studentDto=studentService.getStudentByID(studentId);
		return new ResponseEntity<StudentDto>(studentDto,HttpStatus.OK);
	}
}
