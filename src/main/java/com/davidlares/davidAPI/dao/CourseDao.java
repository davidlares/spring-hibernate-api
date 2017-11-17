package com.davidlares.davidAPI.dao;

import java.util.List;

import com.davidlares.davidAPI.model.Course;

public interface CourseDao {

	void saveCourse(Course course);
	void deleteCourseById(Long idCourse);
	void updateCourse(Course course);
	List<Course> findAllCourses();
	Course findByID(Long idCourse);
	Course findByName(String name);
	List<Course> findByIDteacher(Long idTeacher);
}
