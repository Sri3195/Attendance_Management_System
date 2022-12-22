package com.axis.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	@Override
	public List<StudentAttendanceDto> getAttendanceByStudentId(int studentId){
		List<StudentAttendance> studentAttendance=studentAttendanceRepository.findByStudentId(studentId);
		List<StudentAttendanceDto> studentAttendanceDtos=new ArrayList<>();
		for(StudentAttendance studentattend:studentAttendance) {
			studentAttendanceDtos.add(convertToDto(studentattend));
		}
		return studentAttendanceDtos;
	}

	@Override
	public List<StudentAttendanceDto> getAttendanceByStudentIdandSubject(int studentId,String subject){
		List<StudentAttendance> studentAttendance=studentAttendanceRepository.findByStudentIdAndSubject(studentId,subject);
		List<StudentAttendanceDto> studentAttendanceDtos=new ArrayList<>();
		for(StudentAttendance studentattend:studentAttendance) {
			studentAttendanceDtos.add(convertToDto(studentattend));
		}
		return studentAttendanceDtos;
	}
	
	@Override
	public List<StudentAttendanceDto> getAttendanceBySubject(String subject){
		List<StudentAttendance> studentAttendance=studentAttendanceRepository.findBySubject(subject);
		List<StudentAttendanceDto> studentAttendanceDtos=new ArrayList<>();
		for(StudentAttendance studentattend:studentAttendance) {
			studentAttendanceDtos.add(convertToDto(studentattend));
		}
		return studentAttendanceDtos;
		
	}
	
	@Override
	public List<StudentAttendanceDto> getAttendanceByDates(Date startDate,Date endDate){
		List<StudentAttendance> studentAttendance=studentAttendanceRepository.findByDateBetween(startDate, endDate);
		List<StudentAttendanceDto> studentAttendanceDtos=new ArrayList<>();
		for(StudentAttendance studentattend:studentAttendance) {
			studentAttendanceDtos.add(convertToDto(studentattend));
		}
		return studentAttendanceDtos;
	}
	
	@Override
	public List<StudentAttendanceDto> getAttendanceForMonthBySubject(Date startDate,Date endDate,String subject)
	{
		List<StudentAttendance> studentAttendance=studentAttendanceRepository.findByDateBetweenAndSubject(startDate, endDate,subject);
		List<StudentAttendanceDto> studentAttendanceDtos=new ArrayList<>();
		for(StudentAttendance studentattend:studentAttendance) {
			studentAttendanceDtos.add(convertToDto(studentattend));
		}
		return studentAttendanceDtos;
	}

	@Override
	public List<StudentAttendanceDto> getAttendanceForMonthBySubjectForStudent(Date startDate,Date endDate,String subject,int studentId)
	{
		List<StudentAttendance> studentAttendance=studentAttendanceRepository.findByDateBetweenAndSubjectAndStudentId(startDate, endDate,subject,studentId);
		List<StudentAttendanceDto> studentAttendanceDtos=new ArrayList<>();
		for(StudentAttendance studentattend:studentAttendance) {
			studentAttendanceDtos.add(convertToDto(studentattend));
		}
		return studentAttendanceDtos;
	}
	@Override
	public List<StudentAttendanceDto> getPresentAttendanceForMonthBySubjectForStudent(Date startDate,Date endDate,String subject,int studentId,String status){
		List<StudentAttendance> studentAttendance=studentAttendanceRepository.findByDateBetweenAndSubjectAndStudentIdAndStatus(startDate, endDate,subject,studentId,status);
		List<StudentAttendanceDto> studentAttendanceDtos=new ArrayList<>();
		for(StudentAttendance studentattend:studentAttendance) {
			studentAttendanceDtos.add(convertToDto(studentattend));
		}
		return studentAttendanceDtos;
	}


	public Map<Integer,Double> calculateStudentPercentage(Date startDate,Date endDate,String subject,int studentId){
		List<StudentAttendance> total=studentAttendanceRepository.findByDateBetweenAndSubjectAndStudentId(startDate, endDate, subject, studentId);
		String status="Present";
		List<StudentAttendance> noOf=studentAttendanceRepository.findByDateBetweenAndSubjectAndStudentIdAndStatus(startDate, endDate, subject, studentId,status);
		
		int totalNoOfDays=total.size();
		int noOfDaysPresent=noOf.size();
		double percentage=((noOfDaysPresent*100)/totalNoOfDays);
		Map<Integer,Double> result=new HashMap<Integer,Double>();
		result.put(studentId, percentage);
		return result;
	}

	public Map<Integer,Double> calculateAllPercentage(Date startDate,Date endDate,String subject){
		List<StudentAttendance> alllist=studentAttendanceRepository.findByDateBetweenAndSubject(startDate, endDate, subject);
		Set<Integer> allIds=new HashSet<Integer>();
		Map<Integer,Double> result=new HashMap<Integer,Double>();
		for(StudentAttendance row:alllist) {
			int id=row.getStudentId();
			allIds.add(id);
		}
		for(Integer ID:allIds) {
			List<StudentAttendance> total=studentAttendanceRepository.findByDateBetweenAndSubjectAndStudentId(startDate, endDate, subject, ID);
			String status="Present";
			List<StudentAttendance> noOf=studentAttendanceRepository.findByDateBetweenAndSubjectAndStudentIdAndStatus(startDate, endDate, subject, ID,status);
			
			int totalNoOfDays=total.size();
			int noOfDaysPresent=noOf.size();
			double percentage=((noOfDaysPresent*100)/totalNoOfDays);
			result.put(ID, percentage);
		}
		return result;
		
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
