/**
 * @author : Sayaka Tamura
 * Apr 30, 2019
 * Udemy Learn Java Unit Testing with JUnit 5
 * Section 1, Lecture 6
 */
package com.in28minutes.junit5;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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

	@Test
	void length_greater_than_zero() {
		assertTrue("ABCD".length() > 0);
		assertTrue("ABC".length() > 0);
		assertTrue("A".length() > 0);
		assertTrue("DEF".length() > 0);
	}

	/** Parameterized Test */
	@ParameterizedTest
	@ValueSource(strings = { "ABCD", "ABC", "A", "DEF" })
	void length_greater_than_zero_using_parameterized_test(String str) {
		assertTrue(str.length() > 0);
	}

	@ParameterizedTest(name = "{0} toUpperCase is {1}")
	@CsvSource(value = { "abcd,ABCD", "abc,ABC", "'',''", "abcdefg,ABCDEFG" })
	void uppercase(String word, String capitalizeWord) {
		assertEquals(capitalizeWord, word.toUpperCase());
	}

	@ParameterizedTest(name = "{0} length is {1}")
	@CsvSource(value = { "abcd,4", "abc,3", "'',0", "abcdefg,7" })
	void length(String word, int expectedLength) {
		assertEquals(expectedLength, word.length());
	}

	// This is how to test exception
	@Test
	// @DisplayName gives a name to a test
	@DisplayName("When length is null, throw an exception")
	void length_exception() {
		String str = null;
		assertThrows(NullPointerException.class, () -> {
			str.length();
		});
		// 1st parameter: exception type, 2nd parameter: code what you want to execute
	}

	@Test
	void performanceTest() {
		assertTimeout(Duration.ofSeconds(5), () -> {
			for (int i = 0; i < 100000; i++) {
				int j = i;
				System.out.println(j);
			}
		});
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
	@RepeatedTest(10) // Repeat tests as much as you want
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
