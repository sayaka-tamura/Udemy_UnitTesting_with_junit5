/**
 * @author : Sayaka Tamura
 * Apr 30, 2019
 * Udemy Learn Java Unit Testing with JUnit 5
 * Section 1, Lecture 6 Practice
 */
package com.in28minutes.junit5;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class MathTest {
	int[] list = { 2, 6, 4, 1, 8 };

	@Test
	void minTest() {
		int actualValue = Math.min(5, 2);
		int expectedValue = 2;
		assertEquals(actualValue, expectedValue);
	}

	@Test
	void maxTest() {
		int actualValue = Math.max(13, 56);
		int expectedValue = 56;
		assertEquals(actualValue, expectedValue);
	}

}
