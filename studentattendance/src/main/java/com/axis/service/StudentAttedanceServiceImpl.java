package com.axis.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axis.dto.StudentAttendanceDto;
import com.axis.entity.StudentAttendance;
import com.axis.exception.IDNotFoundException;
import com.axis.repository.StudentAttendanceRepository;
import com.axis.utils.AppConstants;
//import java.util.Date;
//import java.text.SimpleDateFormat;
@Service
public class StudentAttedanceServiceImpl implements StudentAttendanceService {
	
	@Autowired
	StudentAttendanceRepository studentAttendanceRepository;

	@Override
	public StudentAttendanceDto addAttendance(StudentAttendanceDto studentAttendanceDto) {
		//SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy");
		//Date date =new Date();
		//private final String todayDate=formatter.format(date);
		StudentAttendance studentAttendance=studentAttendanceRepository.save(convertToEntity(studentAttendanceDto));
		return convertToDto(studentAttendance);	
		}

	@Override
	public List<StudentAttendanceDto> getAllAttendance() {
		List<StudentAttendanceDto> studentAttendanceDtos=new ArrayList<>();
		List<StudentAttendance> studentAttendances=studentAttendanceRepository.findAll();
		for(StudentAttendance studentAttendance:studentAttendances) {
			studentAttendanceDtos.add(convertToDto(studentAttendance));
		}
		return studentAttendanceDtos;
	}

	@Override
	public StudentAttendanceDto updateAttendanceById(int attendanceId, StudentAttendanceDto studentAttendanceDto) {
		StudentAttendance studentAttendance=studentAttendanceRepository.findById(attendanceId).orElseThrow(()-> new IDNotFoundException(AppConstants.ATTENDANCE_ID_NOT_FOUND));
		studentAttendance.setTeacherId(studentAttendanceDto.getTeacherId());
		studentAttendance.setStudentId(studentAttendanceDto.getStudentId());
		studentAttendance.setStudentName(studentAttendanceDto.getStudentName());
		studentAttendance.setSubject(studentAttendanceDto.getSubject());
		studentAttendance.setTime(studentAttendanceDto.getTime());
		studentAttendance.setDate(studentAttendanceDto.getDate());
		studentAttendance.setStatus(studentAttendanceDto.getStatus());
		return convertToDto(studentAttendance);
	}

	@Override
	public String deleteAttendanceById(int attendanceId) {
		StudentAttendance studentAttendance=studentAttendanceRepository.findById(attendanceId).orElseThrow(()-> new IDNotFoundException(AppConstants.ATTENDANCE_ID_NOT_FOUND));
		studentAttendanceRepository.delete(studentAttendance);
		return AppConstants.ATTENDANCE_DELETE_MESSAGE;
		
	}

	@Override
	public StudentAttendanceDto getAttendanceById(int attendanceId) {
		StudentAttendance studentAttendance=studentAttendanceRepository.findById(attendanceId).orElseThrow(()-> new IDNotFoundException(AppConstants.ATTENDANCE_ID_NOT_FOUND));
		return convertToDto(studentAttendance);

		
	}
	
	private StudentAttendanceDto convertToDto(StudentAttendance studentAttendance)
	{
		StudentAttendanceDto studentAttendanceDto=new ModelMapper().map(studentAttendance,StudentAttendanceDto.class);
		return studentAttendanceDto;
	}
	
	private StudentAttendance convertToEntity(StudentAttendanceDto studentAttendanceDto)
	{
		StudentAttendance studentAttendance=new ModelMapper().map(studentAttendanceDto,StudentAttendance.class);
		return studentAttendance;
	}

}
