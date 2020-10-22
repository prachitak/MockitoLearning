package com.in28minutes.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import com.in28mitutes.business.ToDoBusinessImpl;
import com.in28mitutes.data.api.ToDoService;

//@RunWith(MockitoJUnitRunner.class) replaced by rule
public class ToDoBusinessImplMockitoInjectMocksTest {
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Mock
	ToDoService toDoServiceMock;
	
	@InjectMocks
	ToDoBusinessImpl todoBusinessImpl;
	
	@Captor
	ArgumentCaptor<String> stringArgumentCaptor;
	
	@Test
	public void testRetrieveTodosRelatedToSpring_usingAMock() {
		
		List<String> mockTodos = Arrays.asList("Learn spring MVC", "Learn spring", "Learn to dance");
		when(todoBusinessImpl.retrieveTodosRelatedToSpring("dummy")).thenReturn(mockTodos);
		
		List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("dummy");
		
		assertEquals(2, todos.size());
		assertArrayEquals(Arrays.asList("Learn spring MVC", "Learn spring").toArray(), todos.toArray());
	}
	
	@Test
	public void testRetrieveTodosRelatedToSpring_usingBDD() {
		
		//Given
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
		when(toDoServiceMock.retrieveTodos(anyString())).thenReturn(Arrays.asList("Learn spring MVC", "Learn spring", "Learn to dance"));
		
		todoBusinessImpl.deleteTodosNotRelatedToSpring("test");
		
		verify(toDoServiceMock).deleteTodo("Learn to dance");
//		verify(toDoService, times(2)).deleteTodo("Learn to dance");
		verify(toDoServiceMock, never()).deleteTodo("Learn spring MVC");
		verify(toDoServiceMock, never()).deleteTodo("Learn spring");
		verify(toDoServiceMock, atLeastOnce()).deleteTodo("Learn to dance");
//		verify(toDoService, atLeast(2)).deleteTodo("Learn to dance");
		
		//BDD Style
		then(toDoServiceMock).should().deleteTodo("Learn to dance");
		then(toDoServiceMock).should(never()).deleteTodo("Learn spring MVC");
		then(toDoServiceMock).should(never()).deleteTodo("Learn spring");
		then(toDoServiceMock).should(atLeastOnce()).deleteTodo("Learn to dance");
	}
	
	@Test
	public void testDeleteTodosNotRelatedToSpring_ArgumentCapture_MethodCallOnce() {
		given(toDoServiceMock.retrieveTodos(anyString())).willReturn(Arrays.asList("Learn spring MVC", "Learn spring", "Learn to dance"));
		
		todoBusinessImpl.deleteTodosNotRelatedToSpring("test");
		
		//Define argument captor on specific method call
		then(toDoServiceMock).should().deleteTodo(stringArgumentCaptor.capture());
		
		//capture argument
		assertThat(stringArgumentCaptor.getValue(), is("Learn to dance"));
	}
	
	@Test
	public void testDeleteTodosNotRelatedToSpring_ArgumentCapture_MethodCall_MultipleTimes() {
		
		given(toDoServiceMock.retrieveTodos(anyString())).willReturn(Arrays.asList("Learn spring MVC", "Learn spring", "Learn to dance","Learn to paint"));
		
		todoBusinessImpl.deleteTodosNotRelatedToSpring("test");
		
		//Define argument captor on specific method call
		then(toDoServiceMock).should(times(2)).deleteTodo(stringArgumentCaptor.capture());
		
		//capture argument
		List<String> argumentsCaptured =  stringArgumentCaptor.getAllValues();
		
		assertThat(argumentsCaptured.size(), is(2));
		assertThat(argumentsCaptured, is(Arrays.asList("Learn to dance", "Learn to paint")));
	}
	
}
