package com.davidlares.davidAPI.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.davidlares.davidAPI.service.CourseService;
import com.davidlares.davidAPI.util.CustomErrorType;
import com.davidlares.davidAPI.model.Course;

@Controller
@RequestMapping("/v1")
public class CourseController {
	@Autowired
	private CourseService _courseService;
	
	//GET
	@RequestMapping(value="/courses", method = RequestMethod.GET, headers ="Accept=application/json")
	public ResponseEntity<List<Course>> getCourses(@RequestParam(value="name", required = false) String name, @RequestParam(value="id_teacher", required = false) Long id_teacher){
		List<Course> courses = new ArrayList<Course>();
		if(id_teacher != null) {
			courses = _courseService.findByIDteacher(id_teacher);
			if(courses.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
		}
		
		if(name != null) {
			Course course = _courseService.findByName(name);
			if(course == null) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
			courses.add(course);
		}
		
		if(name == null && id_teacher == null) {
			courses = _courseService.findAllCourses();
			if(courses.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
		}
		
		return new ResponseEntity<List<Course>>(courses,HttpStatus.OK);
	}
	
	// GET BY ID
	@RequestMapping(value="/courses/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Course> getCourseById(@PathVariable("id") Long id){
		Course course = _courseService.findByID(id);
		if(course == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}
	
	// Create
	@RequestMapping(value="/courses", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> createCourse(@RequestBody Course course, UriComponentsBuilder ucBuilder){
		if(_courseService.findByName(course.getName()) != null) {
			return new ResponseEntity(new CustomErrorType("Unable to create, A course with name " 
					+ course.getName() + "already exist"), HttpStatus.CONFLICT);
		}
		
		_courseService.saveCourse(course);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/v1/courses/{id}").buildAndExpand(course.getIdCourse()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	// Update
	@RequestMapping(value="/courses/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<?> updateCourses(@PathVariable("id") Long id, @RequestBody Course course){
		Course currentCourse = _courseService.findByID(id);
		if(currentCourse == null) {
			return new ResponseEntity(new CustomErrorType("Unable to update, Social Media with id " + id+ " not found"), HttpStatus.NOT_FOUND);
		}
		
		currentCourse.setName(course.getName());
		currentCourse.setThemes(course.getThemes());
		currentCourse.setProject(course.getProject());
		
		_courseService.updateCourse(currentCourse);
		return new ResponseEntity<Course>(currentCourse, HttpStatus.OK);
	}
	
	// Delete
	@RequestMapping(value = "/courses/{id}", method = RequestMethod.DELETE, headers ="Accept=application/json")
	public ResponseEntity<?> deleteCourse(@PathVariable("id") Long id){
		
		System.out.println("Course ID received: " + id);
		Course course = _courseService.findByID(id);
		
		if(course == null) {
			System.out.println("Unable to delete, course with id not found. " + id);
			return new ResponseEntity(new CustomErrorType("Unable to delet, course with id" + id + " not found"), HttpStatus.NOT_FOUND);
		}
		
		_courseService.deleteCourseById(id);
		return new ResponseEntity<Course>(HttpStatus.NO_CONTENT);
	}
	
}
