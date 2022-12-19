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

import com.axis.dto.StudentAttendanceDto;
import com.axis.service.StudentAttendanceService;

@RestController
@RequestMapping("api/v1/ATTENDANCE")
public class StudentAttendanceController {
	
	@Autowired
	StudentAttendanceService studentAttendanceService;
	
	@PostMapping("/attendance")
	public ResponseEntity<StudentAttendanceDto> addAttendance(@RequestBody StudentAttendanceDto studentAttendanceDto){
		StudentAttendanceDto studentAttendanceDto2=studentAttendanceService.addAttendance(studentAttendanceDto);
		return new ResponseEntity<StudentAttendanceDto>(studentAttendanceDto2,HttpStatus.OK);
		
	}
	
	@GetMapping("/attendances")
	public ResponseEntity<List<StudentAttendanceDto>> getAllAttendance(){
		List<StudentAttendanceDto> studentAttendanceDtos= studentAttendanceService.getAllAttendance();
		return new ResponseEntity<List<StudentAttendanceDto>>(studentAttendanceDtos,HttpStatus.OK);
	}
	
	@PutMapping("/attendance/{attendanceId}")
	public ResponseEntity<StudentAttendanceDto> updateAttendanceById(@PathVariable int attendanceId,@RequestBody StudentAttendanceDto studentAttendanceDto){
		StudentAttendanceDto studentAttendanceDto2=studentAttendanceService.updateAttendanceById(attendanceId, studentAttendanceDto);
		return new ResponseEntity<StudentAttendanceDto>(studentAttendanceDto2,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/attendance/{attendanceId}")
	public ResponseEntity<String> deleteStudentById(@PathVariable int attendanceId){
		String message=studentAttendanceService.deleteAttendanceById(attendanceId);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	@GetMapping("/attendance/{attendanceId}")
	public ResponseEntity<StudentAttendanceDto> getAttendanceById(@PathVariable int attendanceId)
	{
		StudentAttendanceDto studentAttendanceDto=studentAttendanceService.getAttendanceById(attendanceId);
		return new ResponseEntity<StudentAttendanceDto>(studentAttendanceDto,HttpStatus.OK);
	}

}
