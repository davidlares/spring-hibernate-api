package com.davidlares.davidAPI.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidlares.davidAPI.dao.TeacherDao;
import com.davidlares.davidAPI.model.Teacher;

@Service("TeacherService")
@Transactional
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherDao _TeacherDao;
	@Override
	public void saveTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		_TeacherDao.saveTeacher(teacher);
	}

	@Override
	public void deleteTeacherById(Long idTeacher) {
		// TODO Auto-generated method stub
		_TeacherDao.deleteTeacherById(idTeacher);
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		_TeacherDao.updateTeacher(teacher);
	}

	@Override
	public List<Teacher> findAllTeachers() {
		// TODO Auto-generated method stub
		return _TeacherDao.findAllTeachers();
	}

	@Override
	public Teacher findByID(Long idTeacher) {
		// TODO Auto-generated method stub
		return _TeacherDao.findByID(idTeacher);
	}

	@Override
	public Teacher findByName(String name) {
		// TODO Auto-generated method stub
		return _TeacherDao.findByName(name);
	}

}
