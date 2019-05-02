/**
 * @author : Sayaka Tamura
 * Apr 30, 2019
 * Udemy Learn Java Unit Testing with JUnit 5
 * Section 1, Lecture 6
 */
package com.in28minutes.junit5;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
// JUnit 5 import
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

class StringTest {

	@BeforeAll // Use static for @BeforeAll
	static void beforeAll() {
		System.out.println("Initialize connection to database");
	}

	@AfterAll // Use static for @BeforeAll
	static void afterAll() {
		System.out.println("Close connection to database");
	}

	@BeforeEach // it executes before each unit testing, in JUnit4: @Before
	void beforeEach(TestInfo info) {
		System.out.println("Initialize Test Data for " + info.getDisplayName());
	}

	@AfterEach // it executes after each unit testing, in JUnit4: @After
	void afterEach(TestInfo info) { // TestInfo param works only at JUnit5
		System.out.println("Clean Up the Test Data for " + info.getDisplayName());
	}

	@Test // Annotation for implementing Junit Jupiter Test class
	void length_basic() {
		int actualLength = "ABCD".length();
		int expectLength = 4;
		assertEquals(expectLength, actualLength); // Compare these 2 values if it's the same

		// -- Order of Unit testing --
		// Write Test Code
		// Invoke method (ex.) square(4)
		// Checks in place = 4*4=16 => Assertions: language that used to check
	}

	// This is how to test exception
	@Test
	@DisplayName("When length is null, throw an exception") // @DisplayName gives a name to a test
	void length_exception() {
		String str = null;
		assertThrows(NullPointerException.class, () -> {
			str.length();
		});
		// 1st parameter: exception type, 2nd parameter: code what you want to execute
	}

	@Test
	void toUpperCase_basic() {
		String str = "ABCD";
		String result = str.toUpperCase();

		// Check the parameter is not null
		assertNotNull(result);
		// Check the parameter is null
		// assertNull(result);

		assertEquals("ABCD", result);

	}

	@Test
	void contains_basic() {
		// String str = "abcdefgh";
		// String result = str.contains(str);
		// assertEquals(false, result);
		// assertFalse(str.contains("ijk")); // same as assertEquals(false, result);
		assertFalse("abcdefgh".contains("ijk"));
		// assertTrue //opposite from assertFalse()
	}

	@Test
	void split_basic() {
		String str = "abc def ghi";
		String actualResult[] = str.split(" ");
		// can put it as parameter instead of using expectedResult
		// String[] expectedResult = new String[] { "abc", "def", "ghi" };

		assertArrayEquals(new String[] { "abc", "def", "ghi" }, actualResult);
	}

}
