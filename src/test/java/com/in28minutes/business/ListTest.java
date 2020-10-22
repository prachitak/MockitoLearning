package com.in28minutes.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

public class ListTest {
	
	@Test
	public void letsMockListSize() {
		List mockList = mock(List.class);
		when(mockList.size()).thenReturn(2);
		
		assertEquals(2, mockList.size());
		assertEquals(2, mockList.size());
		assertEquals(2, mockList.size());
	}
	
	@Test
	public void letsMockListSizeWithMultipleReturnValues() {
		List mockList = mock(List.class);
		when(mockList.size()).thenReturn(2).thenReturn(3);
		
		assertEquals(2, mockList.size());
		assertEquals(3, mockList.size());
		assertEquals(3, mockList.size());
		assertEquals(3, mockList.size());
		assertEquals(3, mockList.size());
	}
	
	@Test
	public void letsMockListGet() {
		List<String> mockList = mock(List.class);
		when(mockList.get(0)).thenReturn("abc");
		
		assertEquals("abc", mockList.get(0));
		assertNull(mockList.get(1));
	}
	
	@Test
	public void letsMockListGetWithAny() {
		List mockList = mock(List.class);
		when(mockList.get(anyInt())).thenReturn("abc");
		
		assertEquals("abc", mockList.get(0));
		assertEquals("abc", mockList.get(1));
	}
	
	@Test(expected = RuntimeException.class)
	public void letsMockListThrowAnException() {
		List mockList = mock(List.class);
		when(mockList.get(anyInt())).thenThrow(new RuntimeException("something"));
		mockList.get(0);
	}
	
	@Test
	public void letsMockListGet_BDD() {
		//Given
		List<String> mockList = mock(List.class);
		given(mockList.get(0)).willReturn("abc");
		
		//When
		String str = mockList.get(0);
		
		//Then
		assertThat(str, is("abc"));
	}
	
}
