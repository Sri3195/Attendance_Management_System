package com.axis.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axis.dto.StudentAttendanceDto;
import com.axis.service.StudentAttendanceService;

@RestController
@RequestMapping("api/v1/ATTENDANCE")
public class StudentAttendanceController {
	
	@Autowired
	StudentAttendanceService studentAttendanceService;
	
	
	
	@PostMapping("/attendance")
	//localhost:8003/ATTENDANCE/attendance
	public ResponseEntity<StudentAttendanceDto> addAttendance(@RequestBody StudentAttendanceDto studentAttendanceDto){
		StudentAttendanceDto studentAttendanceDto2=studentAttendanceService.addAttendance(studentAttendanceDto);
		return new ResponseEntity<StudentAttendanceDto>(studentAttendanceDto2,HttpStatus.OK);
		
	}
	
	@GetMapping("/attendances")
	public ResponseEntity<List<StudentAttendanceDto>> getAllAttendance(){
		List<StudentAttendanceDto> studentAttendanceDtos= studentAttendanceService.getAllAttendance();
		return new ResponseEntity<List<StudentAttendanceDto>>(studentAttendanceDtos,HttpStatus.OK);
	}
	
	@PutMapping("/attendance")
	public ResponseEntity<StudentAttendanceDto> updateAttendanceById(@RequestParam int attendanceId,@RequestBody StudentAttendanceDto studentAttendanceDto){
		StudentAttendanceDto studentAttendanceDto2=studentAttendanceService.updateAttendanceById(attendanceId, studentAttendanceDto);
		return new ResponseEntity<StudentAttendanceDto>(studentAttendanceDto2,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/attendance")
	public ResponseEntity<String> deleteStudentById(@RequestParam int attendanceId){
		String message=studentAttendanceService.deleteAttendanceById(attendanceId);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	@GetMapping("/attendanceId")
	public ResponseEntity<StudentAttendanceDto> getAttendanceById(@RequestParam int attendanceId)
	{
		StudentAttendanceDto studentAttendanceDto=studentAttendanceService.getAttendanceById(attendanceId);
		return new ResponseEntity<StudentAttendanceDto>(studentAttendanceDto,HttpStatus.OK);
	}
	@GetMapping("/student-attendance")
	public ResponseEntity<List<StudentAttendanceDto>> getAttendanceByStudentId(@RequestParam int studentId){
		List<StudentAttendanceDto> studentAttendanceDtos=studentAttendanceService.getAttendanceByStudentId(studentId);
		return new ResponseEntity<List<StudentAttendanceDto>>(studentAttendanceDtos,HttpStatus.OK);
		}

	@GetMapping("/student-subject-attendance")
	//localhost:8003/api/v1/ATTENDANCE/attendace/student-subject?studentId=220001&subject=Maths
	public ResponseEntity<List<StudentAttendanceDto>> getAttendanceByStudentIdAndSubject(@RequestParam int studentId,@RequestParam String subject){
		List<StudentAttendanceDto> studentAttendanceDtos=studentAttendanceService.getAttendanceByStudentIdandSubject(studentId,subject);
		return new ResponseEntity<List<StudentAttendanceDto>>(studentAttendanceDtos,HttpStatus.OK);
		}
	
	@GetMapping("/subject-attendance")
	public ResponseEntity<List<StudentAttendanceDto>> getAttendanceBySubject(@RequestParam String subject){
		List<StudentAttendanceDto> studentAttendanceDtos=studentAttendanceService.getAttendanceBySubject(subject);
		return new ResponseEntity<List<StudentAttendanceDto>>(studentAttendanceDtos,HttpStatus.OK);
		}
	@GetMapping("/month-attendance")
	public ResponseEntity<List<StudentAttendanceDto>> getAttendanceByDates(@RequestParam Date startDate,@RequestParam Date endDate){
		List<StudentAttendanceDto> studentAttendanceDtos=studentAttendanceService.getAttendanceByDates(startDate, endDate);
		return new ResponseEntity<List<StudentAttendanceDto>>(studentAttendanceDtos,HttpStatus.OK);
		}
	@GetMapping("/subject-month-attendance")
	public ResponseEntity<List<StudentAttendanceDto>> getAttendanceForMonthBySubject(@RequestParam Date startDate,@RequestParam Date endDate,@RequestParam String subject){
		List<StudentAttendanceDto> studentAttendanceDtos=studentAttendanceService.getAttendanceForMonthBySubject(startDate, endDate,subject);
		return new ResponseEntity<List<StudentAttendanceDto>>(studentAttendanceDtos,HttpStatus.OK);
		}
	@GetMapping("/student-subject-month-attendance")
	public ResponseEntity<List<StudentAttendanceDto>> getAttendanceForMonthBySubjectForStudent(@RequestParam Date startDate,@RequestParam Date endDate,@RequestParam String subject,@RequestParam int studentId){
		List<StudentAttendanceDto> studentAttendanceDtos=studentAttendanceService.getAttendanceForMonthBySubjectForStudent(startDate, endDate,subject,studentId);
		return new ResponseEntity<List<StudentAttendanceDto>>(studentAttendanceDtos,HttpStatus.OK);
		}
	@GetMapping("/present-student-subject-month-attendance")
	public ResponseEntity<List<StudentAttendanceDto>> getAttendanceForMonthBySubjectForStudentAndStatus(@RequestParam Date startDate,@RequestParam Date endDate,@RequestParam String subject,@RequestParam int studentId,@RequestParam String status){
		List<StudentAttendanceDto> studentAttendanceDtos=studentAttendanceService.getPresentAttendanceForMonthBySubjectForStudent(startDate, endDate,subject,studentId,status);
		return new ResponseEntity<List<StudentAttendanceDto>>(studentAttendanceDtos,HttpStatus.OK);
		}
	@GetMapping("/calculatePercentage")
	public ResponseEntity<Map<Integer,Double>> calculateStudentPercentage(@RequestParam Date startDate,@RequestParam Date endDate,@RequestParam String subject,@RequestParam int studentId){
		Map<Integer,Double> result=studentAttendanceService.calculateStudentPercentage(startDate, endDate, subject, studentId);
		return new ResponseEntity<Map<Integer,Double>>(result,HttpStatus.OK);
	}
	@GetMapping("/calculateAllPercentage")
	public ResponseEntity<Map<Integer,Double>> calculateAllPercentage(@RequestParam Date startDate,@RequestParam Date endDate,@RequestParam String subject){
		Map<Integer,Double> result=studentAttendanceService.calculateAllPercentage(startDate, endDate, subject);
		return new ResponseEntity<Map<Integer,Double>>(result,HttpStatus.OK);
	}
}
