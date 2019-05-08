package query;
import java.io.*;
import java.util.*;

/**
 * 课程查询器
 * <p>
 * 使用方法见test()
 * 
 * @author wyc
 * 
 */
public class CourseQuery {
	static void test() {
		CourseQuery courseQuery = new CourseQuery("data/1.csv");
		
		Scanner in = new Scanner(System.in);
	    
	    while (in.hasNext()) {
	    	String s = in.nextLine();
	    	List<Class> r = courseQuery.match(s, 5);
	    	for (Class c : r) {
	    		System.out.println(c);
	    	}
		}
	    in.close();
	}

	List<Class> classlist = null;
	
	/**
	 * 读入csv格式的课程数据
	 * @param path 课程数据路径
	 */
	public CourseQuery(String path) {
		try {
			classlist = parseCsv(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Class> match(String pattern, int num) {
		
        for (Class c : classlist) {
        	c.match(pattern);
        }
        Collections.sort(classlist);
        
        List<Class> r = new LinkedList<Class>();
        for (int i = 0; i < num; i++) {
        	r.add(classlist.get(i));
        }
        return r;
	}
	
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
//		System.out.println((int) '郭');
		List<String> a = pc.getAbbr("黄骏");
		for (String r : a) System.out.println(r);
	}
	
	public static void main(String[] args)  {
//		testPinyinConverter();
		test();
	}
}