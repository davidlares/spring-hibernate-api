package com.davidlares.davidAPI.service;

import java.util.List;

import com.davidlares.davidAPI.model.Teacher;

public interface TeacherService {

	void saveTeacher(Teacher teacher);
	void deleteTeacherById(Long idTeacher);
	void updateTeacher(Teacher teacher);
	
	List<Teacher> findAllTeachers();
	Teacher findByID(Long idTeacher);
	Teacher findByName(String name);
}
