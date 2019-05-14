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
	/*public static void test(String string) {
		CourseQuery courseQuery = new CourseQuery("data/1.csv");
	    List<Class> r = courseQuery.match(string, 5);
	    for (Class c : r) {
	    	System.out.println(c);
	   	}
	}*/

	List<Class> classlist = new ArrayList<Class>();
	
	/**
	 * 得到所有的课程
	 * @return 返回所有的课程
	 */
	public List<Class> getAllClasses() {
		return classlist;
	}
	
	/**
	 * 读入csv格式的课程数据
	 * @param path 课程数据路径
	 */
	public CourseQuery() {
		try {
			List<String> paths = Arrays.asList("ele_plan_data.csv", "speciality_data.csv", "politics_data.csv","gym_data.csv","english_data.csv",
							 "trans_choice_data.csv","pub_choice_data.csv","liberal_computer_data.csv");
			for (String path : paths) {
				classlist.addAll(parseCsv("data/"+path));
			}
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
        for (Class cls : classlist) {
        	if (cls.score == 0) break;
        	r.add(cls);
        }
        return r;
	}
	
	public static List<Class> parseCsv(String path) throws Exception {
		List<Class> l = new ArrayList<Class>();
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(path), "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String line = "";
			while ((line = br.readLine()) != null) {
				if (! line.isBlank()) {
					String[] a = line.split(",");
					if (a.length == 0) continue;
					l.add(new Class(line));
				}
			}
			br.close();
		} catch (Exception e) {
		}
		return l;
	}
	
	static void testPinyinConverter() {
		PinyinConverter pc = new PinyinConverter();
		System.out.println(Integer.parseInt("90ED",16));
		System.out.println((int) '郭');
		List<String> a = pc.getAbbr("黄骏");
		for (String r : a) System.out.println(r);
	}
	
	//public static void main(String[] args)  {
	//	testPinyinConverter();
		//test();
		//testInterval();
	//}

	/**
	 * 测试时间解析是否正确，将打印类似如下的结果：
	 * <p>
	 * {@code both周3:3->4
	 * odd周1:1->2}
	 * 
	 */
	private static void testInterval() {
		CourseQuery courseQuery = new CourseQuery();
		Class c = courseQuery.getAllClasses().get(0);
		for (Interval i : c.getIntervals()) {
			System.out.println(i);
		}
	}
}
