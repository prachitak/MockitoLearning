package com.in28mitutes.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SpyTest {
	
	@Test
	public void test_WithoutSpy() {
		List arrayListMock = mock(ArrayList.class);
		assertEquals(0, arrayListMock.size());
		
		when(arrayListMock.size()).thenReturn(5);
		assertEquals(5, arrayListMock.size());
		
		//do not change the output of size() method. 
		//since mock do not have any real business logic from the class they are created
		when(arrayListMock.size()).thenReturn(5);
		assertEquals(5, arrayListMock.size());
	}
	
	@Test
	public void test_WithSpy() {
		List arrayListSpy = spy(ArrayList.class);
		assertEquals(0, arrayListSpy.size());
		
		//change the output of size() method. 
		//since spy has all the business logic from the class they are created
		arrayListSpy.add("A");
		assertEquals(1, arrayListSpy.size());
		
		arrayListSpy.remove("A");
		assertEquals(0, arrayListSpy.size());
		
		//keep track of real actions
		verify(arrayListSpy).add("A");
		verify(arrayListSpy, never()).clear();
	}

}
