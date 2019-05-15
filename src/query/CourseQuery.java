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
	public List<Class> classlist = new ArrayList<Class>();
	public static PinyinConverter pc = new PinyinConverter();
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
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param pattern 用户输入的串的内容
	 * @return 排名后的列表
	 * @throws Exception 
	 */
	public List<Class> match(String pattern){
		pattern=pattern.toLowerCase();
		try
		{
	        for (Class c : classlist) {
	        	c.match(pattern);
	        }
		} catch (Exception e)
		{
			e.printStackTrace();
		}
        Collections.sort(classlist);
        List<Class> r = new LinkedList<Class>();
        for (Class cls : classlist) {
        	if (cls.score < 0) break;
        	r.add(cls);
        }
        return r;
	}
	/**
	 * 
	 * @param path 需要解析的csv的路径
	 * @return 返回这个csv中解析出来的内容
	 * @throws Exception
	 */
	public static List<Class> parseCsv(String path) {
		List<Class> l = new ArrayList<Class>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			br.readLine();
			br.readLine();
			String line = "";
			while ((line = br.readLine()) != null) {
					if (line.isBlank()) continue;
					String[] a = line.split(",");
					if (a.length == 0) continue;
					l.add(new Class(line,pc));
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}
}
