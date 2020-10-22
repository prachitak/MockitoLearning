package com.in28minutes.business;

import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class HamcrestMatchersTest {
	
	@Test
	public void testHamcrestMatchers() {
		
		//For List
		List<Integer> scores = Arrays.asList(99,100,101,105);
		
		assertThat(scores, hasSize(4));
		assertThat(scores, hasItems(99,100));
		assertThat(scores, everyItem(lessThan(110)));
		assertThat(scores, everyItem(greaterThan(90)));
		
		//String
		assertThat("", isEmptyString());
		assertThat(null, isEmptyOrNullString());
		
		//Arrays
		Integer[] marks = {4,1,3,2};
		assertThat(marks, arrayWithSize(4));
		assertThat(marks, arrayContaining(4,1,3,2));
		assertThat(marks, arrayContainingInAnyOrder(1,4,2,3));
		
	}

}
