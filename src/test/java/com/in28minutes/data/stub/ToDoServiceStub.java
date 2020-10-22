package com.in28minutes.data.stub;

import java.util.Arrays;
import java.util.List;

import com.in28mitutes.data.api.ToDoService;

public class ToDoServiceStub implements ToDoService{

	public List<String> retrieveTodos(String user) {
		return Arrays.asList("Learn spring MVC", "Learn spring", "Learn to dance");
	}

	public void deleteTodo(String todo) {
		// TODO Auto-generated method stub
		
	}

}
