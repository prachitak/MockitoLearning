package com.in28minutes.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.in28mitutes.business.ToDoBusinessImpl;
import com.in28mitutes.data.api.ToDoService;

public class ToDoBusinessImplMockTest {
	
	@Test
	public void testRetrieveTodosRelatedToSpring_usingAMock() {
		
		ToDoService toDoServiceMock = mock(ToDoService.class);
		ToDoBusinessImpl todoBusinessImpl = new ToDoBusinessImpl(toDoServiceMock);
		
		List<String> mockTodos = Arrays.asList("Learn spring MVC", "Learn spring", "Learn to dance");
		when(todoBusinessImpl.retrieveTodosRelatedToSpring("dummy")).thenReturn(mockTodos);
		
		List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("dummy");
		
		assertEquals(2, todos.size());
		assertArrayEquals(Arrays.asList("Learn spring MVC", "Learn spring").toArray(), todos.toArray());
	}
	
	@Test
	public void testRetrieveTodosRelatedToSpring_usingBDD() {
		
		//Given
		ToDoService toDoServiceMock = mock(ToDoService.class);
		ToDoBusinessImpl todoBusinessImpl = new ToDoBusinessImpl(toDoServiceMock);
		
		List<String> mockTodos = Arrays.asList("Learn spring MVC", "Learn spring", "Learn to dance");
		given(todoBusinessImpl.retrieveTodosRelatedToSpring("dummy")).willReturn(mockTodos);
		
		//When
		List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("dummy");
		
		//Then
		assertThat(todos.size(), is(2));
		assertArrayEquals(Arrays.asList("Learn spring MVC", "Learn spring").toArray(), todos.toArray());
	}
	
	@Test
	public void testDeleteTodosNotRelatedToSpring_VerifyMethodCalls() {
		ToDoService toDoService = mock(ToDoService.class);
		ToDoBusinessImpl toDoBusinessImpl = new ToDoBusinessImpl(toDoService);
		
		when(toDoService.retrieveTodos(anyString())).thenReturn(Arrays.asList("Learn spring MVC", "Learn spring", "Learn to dance"));
		
		toDoBusinessImpl.deleteTodosNotRelatedToSpring("test");
		
		verify(toDoService).deleteTodo("Learn to dance");
//		verify(toDoService, times(2)).deleteTodo("Learn to dance");
		verify(toDoService, never()).deleteTodo("Learn spring MVC");
		verify(toDoService, never()).deleteTodo("Learn spring");
		verify(toDoService, atLeastOnce()).deleteTodo("Learn to dance");
//		verify(toDoService, atLeast(2)).deleteTodo("Learn to dance");
		
		//BDD Style
		then(toDoService).should().deleteTodo("Learn to dance");
		then(toDoService).should(never()).deleteTodo("Learn spring MVC");
		then(toDoService).should(never()).deleteTodo("Learn spring");
		then(toDoService).should(atLeastOnce()).deleteTodo("Learn to dance");
	}
	
	@Test
	public void testDeleteTodosNotRelatedToSpring_ArgumentCapture_MethodCallOnce() {
		ToDoService toDoService = mock(ToDoService.class);
		ToDoBusinessImpl toDoBusinessImpl = new ToDoBusinessImpl(toDoService);
		
		given(toDoService.retrieveTodos(anyString())).willReturn(Arrays.asList("Learn spring MVC", "Learn spring", "Learn to dance"));
		
		//Declare argument captor
		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
		
		toDoBusinessImpl.deleteTodosNotRelatedToSpring("test");
		
		//Define argument captor on specific method call
		then(toDoService).should().deleteTodo(stringArgumentCaptor.capture());
		
		//capture argument
		assertThat(stringArgumentCaptor.getValue(), is("Learn to dance"));
	}
	
	@Test
	public void testDeleteTodosNotRelatedToSpring_ArgumentCapture_MethodCall_MultipleTimes() {
		ToDoService toDoService = mock(ToDoService.class);
		ToDoBusinessImpl toDoBusinessImpl = new ToDoBusinessImpl(toDoService);
		
		given(toDoService.retrieveTodos(anyString())).willReturn(Arrays.asList("Learn spring MVC", "Learn spring", "Learn to dance","Learn to paint"));
		
		//Declare argument captor
		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
		
		toDoBusinessImpl.deleteTodosNotRelatedToSpring("test");
		
		//Define argument captor on specific method call
		then(toDoService).should(times(2)).deleteTodo(stringArgumentCaptor.capture());
		
		//capture argument
		List<String> argumentsCaptured =  stringArgumentCaptor.getAllValues();
		
		assertThat(argumentsCaptured.size(), is(2));
		assertThat(argumentsCaptured, is(Arrays.asList("Learn to dance", "Learn to paint")));
	}
	
}
