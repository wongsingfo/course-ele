package Ele;
import java.io.*;
import java.util.*;

public class Main {
	public static List<Class> parseCsv(String path) throws Exception {
		List<Class> l = new ArrayList<Class>();
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = "";
		while ((line = br.readLine()) != null) {
			String[] a = line.split(",");
			if (a.length == 0) continue;
			l.add(new Class(line));
		}
		br.close();
		return l;
	}
	public static void main(String[] args) throws Exception {
		List<Class> classlist = parseCsv("1.csv");
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
}