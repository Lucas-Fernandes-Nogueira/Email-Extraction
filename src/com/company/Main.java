package com.company;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Iterator;


public class Main {

	public static void main(String[] args) throws IOException {
		HashMap<String, Integer> domains = new HashMap<String, Integer>();
		BufferedReader reader = new BufferedReader(new FileReader("sample.txt"));
		String line;
		String regex = "[^?(<=\\s)][\\w.'_%+-]+@([a-zA-Z-\\d\\.]+[a-zA-Z-\\d])(?=(\\s)|$)";
		int counter = 0;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher;
		while ((line = reader.readLine()) != null) {
			matcher = pattern.matcher(line);
			while (matcher.find()) {
				String domain = matcher.group(1);
				if (domains.containsKey(domain)) {
					domains.put(domain, domains.get(domain) + 1);
				} else domains.put(domain, 1);
			}
		}
		reader.close();
		LinkedHashMap<String, Integer> sortedDomains = new LinkedHashMap<String, Integer>();
		sortedDomains = sortHashMapByValues(domains);

		for(String key : sortedDomains.keySet()){
			System.out.println("domain:" + key + " frequency: " + Integer.toString(sortedDomains.get(key)));
		}

	}
	private static LinkedHashMap<String, Integer> sortHashMapByValues(
			HashMap<String, Integer> passedMap) {
		List<String> mapKeys = new ArrayList<String>(passedMap.keySet());
		List<Integer> mapValues = new ArrayList<Integer>(passedMap.values());
		Collections.sort(mapValues);
		Collections.sort(mapKeys);

		Collections.reverse(mapValues);
		Collections.reverse(mapKeys);

		LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();

		Iterator<Integer> valueIt = mapValues.iterator();

		for (Integer value : mapValues) {
			for (String key : mapKeys) {
				Integer comp1 = passedMap.get(key);
				if (comp1.equals(value)) {
					sortedMap.put(key, value);
					break;
				}
			}
		}
		return sortedMap;
	}
}
