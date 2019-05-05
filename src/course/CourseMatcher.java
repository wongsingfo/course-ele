package course;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseMatcher {
	public boolean fastMatch(Course course, String key) {
		for (String p : course.properties) {
			if (fastMatch(p, characterDictFrom(key))) {
				return true;
			}
		}
		return false;
	}
	
	public double match(String property, String key) {

		return 0.0;
	}
	

	private boolean fastMatch(String property, Set<Character> dict) {
		for(int i = 0, n = property.length() ; i < n ; i++) { 
		    char c = property.charAt(i); 
		    if (dict.contains(c)) {
		    	return true;
		    }
		}
		return false;
	}

	private Set<Character> characterDictFrom(String s) {
		Set<Character> dict = new HashSet<Character>();
		for(int i = 0, n = s.length() ; i < n ; i++) { 
		    char c = s.charAt(i); 
		    dict.add(c);
		}
		return dict;
	}
}
