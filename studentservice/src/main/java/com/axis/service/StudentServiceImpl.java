package com.axis.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axis.dto.StudentDto;
import com.axis.entity.Student;
import com.axis.exception.IDNotFoundException;
import com.axis.exception.InvalidIdException;
import com.axis.repository.StudentRepository;
import com.axis.utils.AppConstants;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentRepository studentRepository;
	
	
	@Override
	public StudentDto addStudent(StudentDto studentDto) {
		int id=studentDto.getStudentId();
		int length=checkLength(id);
		boolean flag=checkStart(id);
		if(length ==6 && flag==true) {
			Student student=studentRepository.save(convertToEntity(studentDto));
			return convertToDto(student);
		}
		else {
			throw new InvalidIdException(AppConstants.INVALID_ID);
		}
		
	}

	@Override
	public List<StudentDto> getAllStudents() {
		List<StudentDto> studentDtos=new ArrayList<>();
		List<Student> students=studentRepository.findAll();
		for(Student student:students) {
			studentDtos.add(convertToDto(student));
		}
		return studentDtos;
	}

	@Override
	public StudentDto updateStudentByID(int studentId, StudentDto studentDto) {
		Student student=studentRepository.findById(studentId)
				.orElseThrow(()->new IDNotFoundException(AppConstants.ID_NOT_FOUND));
			student.setStudentId(studentDto.getStudentId());
			student.setStudentName(studentDto.getStudentName());
			student.setSection(studentDto.getSection());
			studentRepository.save(student);
			return convertToDto(student);
	}

	@Override
	public String deleteStudentByID(int studentId) {
		Student student=studentRepository.findById(studentId).orElseThrow((()->new IDNotFoundException(AppConstants.ID_NOT_FOUND)));
		studentRepository.delete(student);
		return AppConstants.DELETE_MESSAGE;
	}

	@Override
	public StudentDto getStudentByID(int studentId) {
		
		Student student=studentRepository.findById(studentId).orElseThrow(()-> new IDNotFoundException(AppConstants.ID_NOT_FOUND));
		return convertToDto(student);
		
	}
	
	private StudentDto convertToDto(Student student)
	{
		StudentDto studentDto=new ModelMapper().map(student,StudentDto.class);
		return studentDto;
	}
	
	private Student convertToEntity(StudentDto studentDto)
	{
		Student student=new ModelMapper().map(studentDto,Student.class);
		return student;
	}
	
	private int checkLength(int studentId) {
		int n=studentId;
		int count=0;
		while(n!=0) {
			n=n/10;
			count=count+1;
		}
		return count;
	}
	
	private boolean checkStart(int studentId) {
		String id=Integer.toString(studentId);
		boolean result=id.startsWith("22");
		return result;
	}
	
	

}
