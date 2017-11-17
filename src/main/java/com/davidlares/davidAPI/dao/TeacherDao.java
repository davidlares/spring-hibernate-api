
package com.davidlares.davidAPI.dao;

import java.util.List;

import com.davidlares.davidAPI.model.Teacher;

public interface TeacherDao {

	void saveTeacher(Teacher teacher);
	void deleteTeacherById(Long idTeacher);
	void updateTeacher(Teacher teacher);
	
	List<Teacher> findAllTeachers();
	Teacher findByID(Long idTeacher);
	Teacher findByName(String name);
}
