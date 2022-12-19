package com.axis.service;

import java.util.ArrayList;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axis.dto.TeacherDto;
import com.axis.entity.Teacher;
import com.axis.exception.IDNotFoundException;
import com.axis.exception.InvalidIdException;
import com.axis.repository.TeacherRepository;
import com.axis.utils.AppConstants;

@Service
public class TeacherServiceImpl implements TeacherService{

	@Autowired
	TeacherRepository teacherRepository;
	
	
	@Override
	public TeacherDto addStudent(TeacherDto teacherDto) {
		int id=teacherDto.getTeacherId();
		int length=checkLength(id);
		boolean flag=checkStart(id);
		if(length == 5 && flag==true) {
			Teacher teacher=teacherRepository.save(convertToEntity(teacherDto));
			return convertToDto(teacher);
			
		}
		else {
			throw new InvalidIdException(AppConstants.INVALID_ID);
		}
		//return convertToDto(teacher);
		
	}

	@Override
	public List<TeacherDto> getAllStudents() {
		List<TeacherDto> teacherDtos=new ArrayList<>();
		List<Teacher> teachers=teacherRepository.findAll();
		for(Teacher teacher:teachers) {
			teacherDtos.add(convertToDto(teacher));
		}
		return teacherDtos;
	}

	@Override
	public TeacherDto updateStudentByID(int teacherId, TeacherDto teacherDto) {
		Teacher teacher=teacherRepository.findById(teacherId)
				.orElseThrow(()->new IDNotFoundException(AppConstants.ID_NOT_FOUND));
			teacher.setTeacherId(teacherDto.getTeacherId());
			teacher.setTeacherName(teacherDto.getTeacherName());
			teacherRepository.save(teacher);
			return convertToDto(teacher);
	}

	@Override
	public String deleteStudentByID(int teacherId) {
		Teacher teacher=teacherRepository.findById(teacherId).orElseThrow((()->new IDNotFoundException(AppConstants.ID_NOT_FOUND)));
		teacherRepository.delete(teacher);
		return AppConstants.DELETE_MESSAGE;
	}

	@Override
	public TeacherDto getStudentByID(int teacherId) {
		
		Teacher teacher=teacherRepository.findById(teacherId).orElseThrow(()-> new IDNotFoundException(AppConstants.ID_NOT_FOUND));
		return convertToDto(teacher);
		
	}
	
	private TeacherDto convertToDto(Teacher teacher)
	{
		TeacherDto teacherDto=new ModelMapper().map(teacher,TeacherDto.class);
		return teacherDto;
	}
	
	private Teacher convertToEntity(TeacherDto teacherDto)
	{
		Teacher teacher=new ModelMapper().map(teacherDto,Teacher.class);
		return teacher;
	}
	
	private int checkLength(int teacherId) {
		int n=teacherId;
		int count=0;
		while(n!=0) {
			n=n/10;
			count=count+1;
		}
		return count;
	}
	
	private boolean checkStart(int teacherId) {
		String id=Integer.toString(teacherId);
		boolean result=id.startsWith("22");
		return result;
	}

}
