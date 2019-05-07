package Ele;
import java.io.*;
import java.util.*;

public class Main {
	public static List<Class> parseCsv(String path) throws Exception {
		List<Class> l = new ArrayList<Class>();
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = "";
		while ((line = br.readLine()) != null) {
			if (! line.isBlank()) {
				String[] a = line.split(",");
				if (a.length == 0) continue;
				l.add(new Class(line));
			}
		}
		br.close();
		return l;
	}
	
	static void testPinyinConverter() {
		PinyinConverter pc = new PinyinConverter();
//		System.out.println(Integer.parseInt("90ED",16));
//		System.out.println((int) '¹ù');
		List<String> a = pc.getAbbr("»Æ¿¥");
		for (String r : a) System.out.println(r);
	}
	
	static void test() {
		List<Class> classlist = null;
		try {
			classlist = parseCsv("1.csv");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        for (Class c : classlist) {
        	c.match(s);
        }
        Collections.sort(classlist);
        for (int i = 0 ; i < 5 ; ++i) {
        	System.out.println(classlist.get(i));
        	System.out.println(classlist.get(i).score);
        }
        in.close();
	}
	
	public static void main(String[] args)  {
//		testPinyinConverter();
		test();
	}
}