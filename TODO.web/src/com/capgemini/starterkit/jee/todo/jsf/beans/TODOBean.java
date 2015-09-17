package com.capgemini.starterkit.jee.todo.jsf.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.capgemini.starterkit.jee.todo.entities.TODO;
import com.capgemini.starterkit.jee.todos.service.TODOsManagementService;

@SessionScoped
@ManagedBean(name = "todoBean")
public class TODOBean {

	@EJB
	TODOsManagementService todosService;
	
	public List<TODO> getTodos() {
		return todosService.findTODOs();
	}
}
