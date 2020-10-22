package com.in28mitutes.data.api;

import java.util.List;

// External Service - Lets say this comes from WunderList
public interface ToDoService {
	public List<String> retrieveTodos(String user);

	public void deleteTodo(String todo);
}