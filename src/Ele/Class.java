package Ele;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Class implements Comparable<Class> {
	public String info,courseID,coursename,type,credit,teacher,classnum,classunit,profession,grade,classroom,time,other;
	//info 中的顺序为 课程号 名字 种类 学分 老师 班号 开课单位 专业 年级 时间教室 人数 备注  以逗号间隔
	//课程号 学分 老师 班号 年级 全词匹配
	//名字 种类 开课单位 专业 时间 教室  部分匹配
	public String [] a = null,b = null,c = null;
	public int [] weight = {10,8,5,2,5,1,1,1,3,5,5,2};
	public String [] regex = new String[12];
	public int score;
	
	public Class(String s) {
		info = s;
		a = s.split(",");
		courseID = a[0];
		coursename = interleaveQ(a[1]);
		type = interleaveQ(a[2]);

		credit = a[3] + "学分";
		if (a[4].indexOf('(') == -1) teacher = a[4];
			else teacher = a[4].substring(0,a[4].indexOf('('));
		classnum = a[5] + "班";

		classunit = interleaveQ(a[6]);
		profession = interleaveQ(a[7]);
		grade = a[8] + "级";
		time = "";
		classroom = "";
		b = a[9].split("<br />");
		if (b.length != 0)
		{
			c = b[0].split(" ");
			time = "(" + c[0] + ")?";
			if (c.length > 2) {
				//教室位置
				for (int j = 0 ; j < c[2].length(); ++j ) {
					if (Character.isDigit(c[2].charAt(j))) break;
					classroom += c[2].charAt(j);
				}
			}
			for (String ss : b) {
				c = ss.split(" ");
				time += "(" + c[1].substring(0,2) + ")?" +
						"(" + c[1].substring(2,4) + ")?" +
						"(" + c[1].substring(4) + ")?" ;
			}
		}
		other = interleaveQ(a[11]);
		
		//public String info,courseID,coursename,type,credit,teacher,classnum,classunit,profession,grade,classroom,time,other;
		regex[0] = courseID;
		regex[1] = coursename;
		regex[2] = type;
		regex[3] = credit;
		regex[4] = teacher;
		regex[5] = classnum;
		regex[6] = classunit;
		regex[7] = profession;
		regex[8] = grade;
		regex[9] = classroom;
		regex[10] = time;
		regex[11] = other;
		System.out.println(other);
	}
	public void match(String s) {
		score = 0;
		//完成该课与输入字符串s的匹配，返回匹配的分数
		//1.先将每个逗号隔开的内容和s进行匹配 考虑到缩写 info中可以不连续 s中应该为连续
		//标记哪些部分被匹配完成
		for (int i = 0 ; i < 12 ; ++ i)
		{
			Pattern p = Pattern.compile(regex[i]);
			Matcher m = p.matcher(s);
			while (m.find()) {
				if (m.end() >= m.start()+1) {
					score += weight[i] * (m.end() - m.start());
					System.out.println(regex[i]);
					System.out.println(m.start());
					System.out.println(m.end());
				}
			}
		}
		
		//2.看一看未被匹配的部分是否出现否定词
		//3.给一个加权的分数（否定的给负分，肯定的給正分）
	}
	public int compareTo(Class c) {
		if (c.score == score) return 0;else
			if (c.score < score) return -1;else 
				return 1;
	}
	public String toString() {
        return info;
    }
	
	/**
	 * 将输入的字符串每个字母后面加一个问号，去掉regex中的特殊字符(包括不限于 空格和括号）
	 * @author wck
	 * @param s 如：ab c  de
	 * @return a?b?c?d?e?
	 */
	private String interleaveQ(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (Character.isSpaceChar(c) || c == '(' || c == ')' 
					|| c == '+') {
				// pass
			} else {
				sb.append(c);
				sb.append('?');
			}
		}
		return sb.toString();
	}
}
