package com.capgemini.starterkit.jee.todos.service;

import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.security.authorization.AuthorizationException;

import com.capgemini.starterkit.jee.todo.entities.TODO;

/**
 * Session Bean implementation class TODOsManagementService
 */
@Stateless
@LocalBean
@PermitAll
public class TODOsManagementService {

	@PersistenceContext(unitName = "TodoPU")
	private EntityManager em;
	
	@Resource
	private SessionContext context;

	/**
	 * Default constructor.
	 */
	public TODOsManagementService() {
	}

	public List<TODO> findTODOs() {
		return em.createQuery("Select td From TODO td", TODO.class)
				.getResultList();
	}

	@RolesAllowed("ADMIN")
	public void addTODO(TODO todo) {
		todo.setUser(context.getCallerPrincipal().getName());
		todo.setDone(false);
		em.persist(todo);
	}

	public void markTODOAsDone(TODO todo) throws AuthorizationException {
		TODO todoDb = em.find(TODO.class, todo.getId());
		if(!(context.isCallerInRole("ADMIN") || getUserName().equals(todoDb.getUser()))) {
			throw new AuthorizationException("Operation not allowed!");
		} 
		todoDb.setDone(true);
	}
	
	public String getUserName() {
		return context.getCallerPrincipal().getName();
	}

}
