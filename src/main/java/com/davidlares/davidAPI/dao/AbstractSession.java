package com.davidlares.davidAPI.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractSession {
	
	// la clase abstracta, al heredar sea obligatorio sobreescribir los metodos
	
	@Autowired // llamamos al objeto implementado en la sesion
	private SessionFactory sessionFactory;
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
