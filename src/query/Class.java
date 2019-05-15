package query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Class implements Comparable<Class> {
	public String info,courseID,coursename,type,credit,teacher,classnum,classunit,profession,grade,classroom,other;
	//info 中的顺序为 课程号 名字 种类 学分 老师 班号 开课单位 专业 年级 时间教室 人数 备注  以逗号间隔
	//课程号 学分 老师 班号 年级 全词匹配
	//名字 种类 开课单位 专业 时间 教室  部分匹配
	public String [] a = null,b = null,c = null;
	public int [] weight = {0,0,15,8,10,6,6,6,6};
	private List<String> regextime = new ArrayList<>();
	private String [] regex = new String[11];
	public int score;
	private List<String> teacherEn = null;
	private int [] havebeenpp = null; 
	/**
	 * 从@{Class.info}中解析上课时间
	 * @return day[]存周几  比如周二 周三  time[] 存时间 比如第几节课 week[] 存第几周
	 */
	public void getIntervals() {
		List<Interval> r = new LinkedList<Interval>();
		Pattern p = Pattern.compile("(?<which>.)周周(?<day>.)(?<from>\\d+)~(?<to>\\d+)节");
		Matcher m = p.matcher(info);
		while (m.find()) {
			regextime.add(m.group("from")+"节");
			regextime.add(m.group("to")+"节");
			regextime.add("周"+m.group("day"));
			regextime.add(m.group("which")+"周");
		}
	}
	
	public Class(String s,PinyinConverter pc) {
		//System.out.println(s);
		info = s;
		getIntervals();
		a = s.split(",");
		courseID = a[0];
		coursename = interleaveQ(a[1]);
		if (a[2].length() == 2)	type = a[2];  // 限选 任选
			else if (a[2].length() == 1) type = a[2]+"级";  //A B C D级
				else if (a[2].length() == 4) type = a[2].substring(2,4); else type = ""; 
		credit = a[3].charAt(0) + "学分";
		teacher = "";
		for (int i=0;i<a[4].length() && a[4].charAt(i)>=0x4E00 && a[4].charAt(i)<=0x9FA5;++i)
			teacher += a[4].charAt(i);
		teacherEn = pc.getAbbr(teacher);
		classnum = a[5] + "班";
		classunit = interleaveQ(a[6]);
		profession = interleaveQ(a[7]);
		grade = a[8] + "级";
		classroom = "";
		b = a[9].split("<br />");
		if (b.length != 0)
		{
			c = b[0].split(" ");
			if (c.length > 2) {
				//教室位置
				for (int j = 0 ; j < c[2].length(); ++j ) {
					if (Character.isDigit(c[2].charAt(j))) break;
					classroom += c[2].charAt(j);
				}
			}
		}
		other = interleaveQ(a[11]);
		//public String info,courseID,coursename,type,credit,teacher,classnum,classunit,profession,grade,classroom,time,other;
		//先匹配完全匹配的内容 在匹配不一定完全匹配的部分
		regex[0] = courseID;  //2
		regex[1] = classnum;
		regex[2] = teacher;
		regex[3] = credit;
		regex[4] = grade;
		regex[5] = classroom;
		regex[6] = type;  //8
		
		regex[7] = coursename;
		regex[8] = classunit;
		regex[9] = profession;
		regex[10] = other;
		//for (String ss:regex)
		//{
		//	System.out.println(ss);
		//}
		//System.out.printf("%s\n",teacherEn);
		//System.out.printf("Finish %s\n",s);
	}
	/**
	 * @param s 待匹配串
	 * @param regex 需要匹配的正则表达式
	 * @param bh 匹配完成后这部分被标记为bh
	 */
	public void judge(String s,String regex,int bh) throws Exception
	{
		try {
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(s);
			while (m.find()) { 
				if (bh<=12 && bh>=9 && m.end()<=m.start()+1) continue; //非完全匹配 如果出现单字匹配 则跳过
				boolean can=true;
				for (int j=m.start();j<m.end();++j)
				if (havebeenpp[j]!=0)
				{
					can=false;
					break;
				}
				if (can)
					for (int j=m.start();j<m.end();++j)
						havebeenpp[j]=bh;
			}
		} catch (Exception e) {}
	}
	/**
	 * @param s 传入需要被匹配的串
	  *  给出这个课对于这个串的分数
	 * @throws Exception 
	 */
	public void match(String s) throws Exception {
		int l=s.length();
		havebeenpp = new int [l];
		Arrays.fill(havebeenpp, 0);
		score = 0;
		//完成该课与输入字符串s的匹配，标记匹配的内容
		//先将每个逗号隔开的内容和s进行匹配
		//标记哪些部分被匹配完成
		//先看一下英文名字
		if (teacherEn!=null)
			for (String name : teacherEn) judge(s,name,13);
		for (String ti : regextime) judge(s,ti,1);
		for (int i = 0 ; i < 11 ; ++ i) judge(s,regex[i],i+2);
		//for (int i=0;i<l;++i) System.out.print(havebeenpp[i]);
		//System.out.println();
		//2.看一看未被匹配的部分是否出现否定词
		for (int i=0;i<l;++i)
			if (havebeenpp[i]==0 && s.charAt(i)=='不')
				havebeenpp[i]=-1;
		//3.给一个加权的分数（否定的给负分，肯定的給正分）
		int fu=1,j,pipei=0;
		for (int i=0;i<l;++i)
			if (havebeenpp[i]>0) pipei+=fu;
			else if (havebeenpp[i]<0) fu=-1;
		for (int i=0;i<l;++i)
		{
			if (havebeenpp[i]==-1) fu=-1;
			j=i;
			while (j+1<l && havebeenpp[j+1]==havebeenpp[i]) ++j;
			if (havebeenpp[i]<=0)
			{
				i=j;
				continue;
			}
			if (havebeenpp[i]>=2 && havebeenpp[i]<=8)
				score += fu*weight[havebeenpp[i]];
			else if (havebeenpp[i]==1)
				score += fu*(j-i+1)/2*3;
			else
			if (havebeenpp[i]==9) //匹配了名字 那么首字母匹配加分较多  长度匹配多的加分较多
			{
				int len=j-i+1;
				if (regex[7].charAt(0)==s.charAt(i)) score += fu*(len)*3; else
					score += fu*len*2;
			} else
			if (havebeenpp[i]==13) score += fu * 5;
			else
			{
				score += fu*(j-i+1);	
			}
			i=j;
		}
	}
	public int compareTo(Class c) {
		if (c.score == score) return 0;else
			if (c.score < score) return -1;else 
				return 1;
	}
	public String toString() {
        return String.valueOf(score) + info;
    }
	
	/**
	 * 将输入的字符串每个字母后面加一个问号，去掉regex中的特殊字符(包括不限于 空格和括号）
	 * @author wck
	 * @param s 如：ab c  de
	 * @return a?b?c?d?e?
	 */
	private String interleaveQ(String s) {
		s=s.toLowerCase();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (Character.isSpaceChar(c) || c == '(' || c == ')' 
					|| c == '+' || c == '.' || c == '?') {
				// pass
			} else {
				sb.append(c);
				sb.append('?');
			}
		}
		return sb.toString();
	}
}
