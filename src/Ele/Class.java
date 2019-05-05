package Ele;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Class implements Comparable<Class> {
	public String info,courseID,coursename,type,credit,teacher,classnum,classunit,profession,grade,classroom,time,other;
	//info �е�˳��Ϊ �γ̺� ���� ���� ѧ�� ��ʦ ��� ���ε�λ רҵ �꼶 ʱ����� ���� ��ע  �Զ��ż��
	//�γ̺� ѧ�� ��ʦ ��� �꼶 ȫ��ƥ��
	//���� ���� ���ε�λ רҵ ʱ�� ����  ����ƥ��
	public String [] a = null,b = null,c = null;
	public int [] weight = {10,8,5,2,5,1,1,1,3,5,5,2};
	public String [] regex = new String[12];
	public int score;
	
	public Class(String s) {
		info = s;
		a = s.split(",");
		courseID = a[0];
		coursename = "";
		for (int i=0;i<a[1].length();++i) {
			coursename += a[1].charAt(i) + "?";
		}
		type = "";
		for (int i=0;i<a[2].length();++i) {
			type += a[2].charAt(i) + "?";
		}
		credit = a[3] + "ѧ��";
		if (a[4].indexOf('(') == -1) teacher = a[4];
			else teacher = a[4].substring(0,a[4].indexOf('('));
		classnum = a[5] + "��";
		classunit = "";
		for (int i=0;i<a[6].length();++i) {
			classunit += a[6].charAt(i) + "?";
		}
		profession = "";
		for (int i=0;i<a[7].length();++i) {
			profession += a[7].charAt(i) + "?";
		}
		grade = a[8] + "��";
		time = "";
		classroom = "";
		b = a[9].split("<br />");
		if (b.length != 0)
		{
			c = b[0].split(" ");
			time = "(" + c[0] + ")?";
			if (c.length > 2) {
				//����λ��
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
		other = "";
		for (int i=0;i<a[11].length();++i) {
			other += a[11].charAt(i) + "?";
		}
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
		//��ɸÿ��������ַ���s��ƥ�䣬����ƥ��ķ���
		//1.�Ƚ�ÿ�����Ÿ��������ݺ�s����ƥ�� ���ǵ���д info�п��Բ����� s��Ӧ��Ϊ����
		//�����Щ���ֱ�ƥ�����
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
		
		//2.��һ��δ��ƥ��Ĳ����Ƿ���ַ񶨴�
		//3.��һ����Ȩ�ķ������񶨵ĸ����֣��϶��Ľo���֣�
	}
	public int compareTo(Class c) {
		if (c.score == score) return 0;else
			if (c.score < score) return -1;else 
				return 1;
	}
	public String toString() {
        return info;
    }
}