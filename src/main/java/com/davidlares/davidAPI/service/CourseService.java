package com.davidlares.davidAPI.service;

import java.util.List;

import com.davidlares.davidAPI.model.Course;

public interface CourseService {

	void saveCourse(Course course);
	void deleteCourseById(Long idCourse);
	void updateCourse(Course course);
	List<Course> findAllCourses();
	Course findByID(Long idCourse);
	Course findByName(String name);
	List<Course> findByIDteacher(Long idTeacher);

}
