package query;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 人名首字母
 * 
 * @author wck
 *
 */
class PinyinConverter {
	/**
	 * 返回所有可能的缩写 （因为有多音字，所以有可能有多种缩写）
	 * @param name "黄骏"
	 * @return {"hj"}
	 */
	List<String> getAbbr(String name) {
		AbbrDFS ab = new AbbrDFS(name);
		return ab.getAbbr();
	}

	final String datafile = "data/unicode_to_hanyu_pinyin.txt";
	private Map<Integer, Character[]> data;
	
	PinyinConverter() {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(datafile));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		data = new HashMap<Integer, Character[]>();
		
		
		try {
			for (String line = in.readLine(); line != null; line = in.readLine()) {
				// String[] s = line.strip().split(" ");
				String[] s = line.trim().split(" ");
				int s0 = Integer.parseInt(s[0],16); 
				
				Set<Character> set = new HashSet<Character>();
				for (int i = 1; i < s.length; i++) {
					set.add(s[i].charAt(0));
				}
				Character[] caps = (Character[]) set.toArray(new Character[set.size()]);
				
				data.put(s0, caps);
			}
			
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private class AbbrDFS {
		int[] namei;
		private char[] abbr;
		
		AbbrDFS(String name) {
			int n = name.length();
			namei = new int[n];
			abbr = new char[n];
			for (int i = 0; i < n; i++) {
				namei[i] = (int) name.charAt(i);
			}
		}
		
		private List<String> r;
		public List<String> getAbbr() {
			r = new LinkedList<String>();
			dfs(0);
			return r;
		}
		
		private void dfs(int i) {
			if (i == namei.length) {
				String s = String.valueOf(abbr);
				r.add(s);
			} else {
				Character[] ac = data.get(namei[i]);
				for (char c : ac) {
					abbr[i] = c;
					dfs(i + 1);
				}
			}
		}
	}
	
}
