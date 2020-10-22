package com.in28minutes.powermock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
//Initialize class containing static method
@PrepareForTest({UtilityClass.class, OtherUtilityClass.class})
public class MockingStaticMethodTest {
	
	@Mock
	Dependency dependency;
	
	@InjectMocks
	SystemUnderTest systemUnderTest;
	
	@Test
	public void testMethodCallingAStaticMethod() {
		
		when(dependency.retrieveAllStats()).thenReturn(Arrays.asList(1,2,3));
		
		//Initialize class containing static method
		PowerMockito.mockStatic(UtilityClass.class);
		//mock static method
		when(UtilityClass.staticMethod(6)).thenReturn(150);
		
		//Initialize class containing static method
		PowerMockito.mockStatic(OtherUtilityClass.class);
		//mock static method
		when(OtherUtilityClass.staticMethod(6)).thenReturn(150);
		
		int result = systemUnderTest.methodCallingAStaticMethod(); 
		assertEquals(150, result);
		
		//verify static method call
		PowerMockito.verifyStatic();
		UtilityClass.staticMethod(6);
		
		//verify static method call
		PowerMockito.verifyStatic();
		OtherUtilityClass.staticMethod(6);
		
	}


}
