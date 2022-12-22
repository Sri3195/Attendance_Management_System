package com.axis.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.axis.entity.StudentAttendance;

@Repository
public interface StudentAttendanceRepository extends JpaRepository<StudentAttendance, Integer>{
	
	List<StudentAttendance> findByStudentId(int studentId);
	List<StudentAttendance> findByStudentIdAndSubject(int studentId,String subject);
	List<StudentAttendance> findBySubject(String subject);
	
	List<StudentAttendance> findByDateBetween(Date startDate,Date endDate);
	List<StudentAttendance> findByDateBetweenAndSubject(Date startDate,Date endDate,String subject);
	List<StudentAttendance> findByDateBetweenAndSubjectAndStudentId(Date startDate,Date endDate,String subject,int studentId);
	List<StudentAttendance> findByDateBetweenAndSubjectAndStudentIdAndStatus(Date startDate,Date endDate,String subject,int studentId,String status);

}
