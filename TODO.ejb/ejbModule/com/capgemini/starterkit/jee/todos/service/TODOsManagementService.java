package com.capgemini.starterkit.jee.todos.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.capgemini.starterkit.jee.todo.entities.TODO;

/**
 * Session Bean implementation class TODOsManagementService
 */
@Stateless
@LocalBean
public class TODOsManagementService {

	@PersistenceContext(unitName = "TodoPU")
	EntityManager em;


	/**
	 * Default constructor.
	 */
	public TODOsManagementService() {
	}

	public List<TODO> findTODOs() {
		return em.createQuery("Select td From TODO td", TODO.class)
				.getResultList();
	}

	public void addTODO(TODO todo) {
	}

	public void markTODOAsDone(TODO todo) {
	}

}
