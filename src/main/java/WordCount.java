//package main.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WordCount {

	public static final String REGEX = "[a-zA-Z]+";

	public static void main(String[] args) throws IOException {
		
		int countOfPrintLines = Integer.MAX_VALUE;

		if (args.length < 1) {
			System.out.println("Incorrect input!");
			return;
		} else if (args.length > 1) {
			countOfPrintLines = Integer.parseInt(args[1]);
		}
		
		WordCount wordCount = new WordCount();
		
		wordCount.sortAndPrint(wordCount.getCountedMap(new File(args[0])), countOfPrintLines);
	}

	public List<String> getWordsByRegEx(String input, String regex) {

		List<String> words = new ArrayList<>();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);

		while (matcher.find()) {
			String word = matcher.group(0);
			words.add(word);
		}

		return words;
	}

	public Map<String, Integer> getCountedMap(File file) throws IOException {

		Map<String, Integer> map = new HashMap<>();// Files.lines(file.toPath()).collect(Collectors.toMap())

		Files.lines(file.toPath()).forEach((line) -> {
			for (String word : getWordsByRegEx(line, REGEX)) {
				if (map.containsKey(word)) {
					map.put(word, map.get(word) + 1);
				} else {
					map.put(word, 1);
				}
			}
		});

		return map;
	}

	public void sortAndPrint(Map<String, Integer> map, int count) {

		List<Map.Entry<String, Integer>> sortedList = map.entrySet().stream().sorted((e1, e2) -> {
			if (e1.getValue().intValue() < e2.getValue().intValue()) {
				return 1;
			} else if (e1.getValue().intValue() > e2.getValue().intValue()) {
				return -1;
			} else {
				return e1.getKey().compareTo(e2.getKey());
			}
		}).collect(Collectors.toList());

		if(count > sortedList.size()) count = sortedList.size();
		
		for (int i = 0; i < count; i++) {
			System.out.println(sortedList.get(i).getKey() + "=" + sortedList.get(i).getValue());
		}
	}
}
