package com.capgemini.starterkit.j2ee.todo.jsf.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.jboss.security.authorization.AuthorizationException;

import com.capgemini.starterkit.jee.todo.entities.TODO;
import com.capgemini.starterkit.jee.todos.service.TODOsManagementService;

@SessionScoped
@ManagedBean(name = "todoBean")
public class TODOBean {

	private TODO todo;

	@EJB
	private TODOsManagementService todosService;
	
	public TODO getTodo() {
		if(todo == null) {
			todo = new TODO();
		}
		return todo;
	}
	
	public List<TODO> getTodos() {
		return todosService.findTODOs();
	}
	
	public String addNewTodo() {
		todosService.addTODO(todo);
		todo = null;
		return "list";
	}

	public void setTodoAsDone(TODO todo) {
		try {
			todosService.markTODOAsDone(todo);
		} catch (AuthorizationException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
			e.printStackTrace();
		}
	}
}
