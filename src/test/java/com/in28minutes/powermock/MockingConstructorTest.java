package com.in28minutes.powermock;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SystemUnderTest.class)
public class MockingConstructorTest {
	
	@InjectMocks
	SystemUnderTest systemUnderTest;
	
	@Mock
	ArrayList mockArrayList;
	
	@Test
	public void testMethodUsingAnArrayListConstructor() throws Exception {
		
		//mock constructor
		PowerMockito.whenNew(ArrayList.class).withAnyArguments().thenReturn(mockArrayList);
		
		when(mockArrayList.size()).thenReturn(3);
		
		int size = systemUnderTest.methodUsingAnArrayListConstructor();
		
		assertEquals(3, size);
	}

}