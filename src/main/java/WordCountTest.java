//package main.java;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WordCountTest {

	@Test
	public void testGetWordByRegEx() {
		WordCount wordCount = new WordCount();
		assertEquals("opacha", wordCount.getWordsByRegEx("  . opacha, ", WordCount.REGEX).get(0));
	}
}
