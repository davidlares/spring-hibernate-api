package com.davidlares.davidAPI.dao;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.davidlares.davidAPI.model.Teacher;
import com.davidlares.davidAPI.model.TeacherSocialMedia;

@Repository
@Transactional // etiqueta de Hibernate (se ejecute todo transacionalmente)
public class TeacherDaoImpl extends AbstractSession implements TeacherDao {

	public void saveTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		getSession().persist(teacher);
	}

	public void deleteTeacherById(Long idTeacher) {
		// TODO Auto-generated method stub
		Teacher teacher = findByID(idTeacher);
		if(teacher != null) {
			Iterator<TeacherSocialMedia>  i = teacher.getTeacherSocialMedias().iterator();
			while(i.hasNext()) {
				TeacherSocialMedia teacherSocialMedia = i.next();
				i.remove();
				getSession().delete(teacherSocialMedia);
			}
			teacher.getTeacherSocialMedias().clear();
			getSession().delete(teacher);
		}
	}

	public void updateTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		getSession().update(teacher);
	}

	public List<Teacher> findAllTeachers() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Teacher").list();
	}

	public Teacher findByID(Long idTeacher) {
		// TODO Auto-generated method stub
		return (Teacher) getSession().get(Teacher.class, idTeacher);
	}

	public Teacher findByName(String name) {
		// se debe castear el objeto a buscar (Teacher)
		return (Teacher) getSession()
		.createQuery("from teacher where name = :name")
		.setParameter("name", name)
		.uniqueResult();
	}

}
