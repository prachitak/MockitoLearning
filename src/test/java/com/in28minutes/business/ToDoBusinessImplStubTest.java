package com.in28minutes.business;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.in28minutes.data.stub.ToDoServiceStub;
import com.in28mitutes.business.ToDoBusinessImpl;
import com.in28mitutes.data.api.ToDoService;

public class ToDoBusinessImplStubTest {
	
	@Test
	public void testRetrieveTodosRelatedToSpring_usingAStub() {
		ToDoService toDoServiceStub = new ToDoServiceStub() ;
		ToDoBusinessImpl todoBusinessImpl = new ToDoBusinessImpl(toDoServiceStub);
		
		List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("dummy");
		
		assertEquals(2, todos.size());
		assertArrayEquals(Arrays.asList("Learn spring MVC", "Learn spring").toArray(), todos.toArray());
	}
	
}
