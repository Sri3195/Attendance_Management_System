package com.axis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axis.entity.Teacher;

@Repository
public interface TeacherRepository  extends JpaRepository<Teacher, Integer>{

}
