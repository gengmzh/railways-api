package org.railways.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.TestCase;

/**
 * @author gmz
 * @time 2012-6-8
 */
public class PatternTest extends TestCase {

	public void test_pattern() {
		Pattern p = Pattern.compile("java[\\s\\S]+?is");
		Matcher m = p.matcher("java is beautiful.\njava is OO.java is hard.");
		while (m.find()) {
			System.out.println("group: " + m.group());
		}
	}

}
