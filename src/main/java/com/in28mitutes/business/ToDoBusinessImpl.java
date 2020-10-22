package com.in28mitutes.business;

import java.util.ArrayList;
import java.util.List;

import com.in28mitutes.data.api.ToDoService;

public class ToDoBusinessImpl {
	private ToDoService toDoService;

	public ToDoBusinessImpl(ToDoService toDoService) {
		this.toDoService = toDoService;
	}

	public List<String> retrieveTodosRelatedToSpring(String user) {
		List<String> filteredTodos = new ArrayList<String>();
		List<String> allTodos = toDoService.retrieveTodos(user);
		for (String todo : allTodos) {
			if (todo.contains("spring")) {
				filteredTodos.add(todo);
			}
		}
		return filteredTodos;
	}
	
	public void deleteTodosNotRelatedToSpring(String user) {
		List<String> allTodos = toDoService.retrieveTodos(user);
		for (String todo : allTodos) {
			if (!todo.contains("spring")) {
				toDoService.deleteTodo(todo);
			}
		}
	}
}