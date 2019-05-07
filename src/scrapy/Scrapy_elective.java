package scrapy;
import org.openqa.selenium.chrome.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.io.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *  实现登陆、获取数据等
 * 
 * @author sht
 *
 */
public class Scrapy_elective {
	
	// get elective menu
	public static List<WebElement> get_menu(WebDriver driver){
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='titlelink1']")));
		List<WebElement> choice = driver.findElements(By.xpath("//*[@class='titlelink1']"));
		return choice;
	}
	
	// get elective plan
	public static void get_elective_plan(WebDriver driver,List<WebElement> choice) {
		choice.get(0).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		String html = driver.getPageSource();
		try {
			String file_name = "/Users/mac/Desktop/19春/java/data.csv";
			File data_reserve = new File(file_name);
			BufferedWriter bw = new BufferedWriter(new FileWriter(data_reserve,false));
			bw.write('\ufeff');
		
			// extract info with regex
			Pattern pattern_1 = Pattern.compile("<tr class=\"datagrid-.*?\">([\\s\\S]*?)</tr>");
			Pattern pattern_2 = Pattern.compile("<td class=\"datagrid\"[\\s\\S]*?<span>([\\s\\S]*?)</span>");
			Matcher matcher = pattern_1.matcher(html);
			while(matcher.find()) {
				bw.newLine();
				String section_html = matcher.group();
				System.out.println(section_html);
				Matcher section_matcher = pattern_2.matcher(section_html);
				while(section_matcher.find()) {
					bw.write(section_matcher.group(1).replace(',', ' ') + ",");
				}
				
			}
			bw.close();
			
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// add courses 
	public static void add_course_speciality(WebDriver driver,List<WebElement> choice) {
		choice.get(0).click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody//div[@style='float:left;padding:2px 0px 0px 1px;']/a")));
		driver.findElement(By.xpath("//tbody//div[@style='float:left;padding:2px 0px 0px 1px;']/a")).click();
		driver.findElement(By.xpath("//table//form[@id='qyForm']//input[@id='speciality']")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		((JavascriptExecutor)driver).executeScript("document.getElementById(\"deptID\").getElementsByTagName(\"option\").item(0).value='ALL';");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//table//form[@id='qyForm']//input[@id='b_query']")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		try {
			String filename = "/Users/mac/Desktop/19春/java/speciality_data.csv";
			File data_reserve = new File(filename);
			BufferedWriter bw = new BufferedWriter(new FileWriter(data_reserve,false));
			bw.write('\ufeff');
			
			Pattern pattern_1 = Pattern.compile("<tr class=\"datagrid-.*?\">([\\s\\S]*?)</tr>");
			Pattern pattern_2 = Pattern.compile("<td class=\"datagrid\"[\\s\\S]*?<span>([\\s\\S]*?)</span>");
			Pattern pattern_3 = Pattern.compile("Next</a>");
			Pattern pattern_4 = Pattern.compile("Previous</a>");
			boolean Next = true;
			while(Next) {
				String html = driver.getPageSource();
				Matcher matcher = pattern_1.matcher(html);
				while(matcher.find()) {
					bw.newLine();
					String section_html = matcher.group();
					//System.out.println(section_html);
					Matcher section_matcher = pattern_2.matcher(section_html);
					while(section_matcher.find()) {
						bw.write(section_matcher.group(1).replace(',', ' ') + ",");
					}
				}
				Matcher NextMatcher = pattern_3.matcher(html);
				if(NextMatcher.find()) {
					Matcher PreMatcher = pattern_4.matcher(html);
					if(PreMatcher.find()) {
						driver.findElements(By.xpath("//table[@class='datagrid']//td[@align='right']/a")).get(2).click();
					}
					else driver.findElements(By.xpath("//table[@class='datagrid']//td[@align='right']/a")).get(0).click();
					driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				}
				else Next = false;
			}
			bw.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void add_course_trans_choice(WebDriver driver,List<WebElement> choice) {
		choice.get(0).click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody//div[@style='float:left;padding:2px 0px 0px 1px;']/a")));
		driver.findElement(By.xpath("//tbody//div[@style='float:left;padding:2px 0px 0px 1px;']/a")).click();
		driver.findElement(By.xpath("//table//form[@id='qyForm']//input[@id='trans_choice']")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		((JavascriptExecutor)driver).executeScript("document.getElementById(\"deptID\").getElementsByTagName(\"option\").item(0).value='ALL';");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//table//form[@id='qyForm']//input[@id='b_query']")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		try {
			String filename = "/Users/mac/Desktop/19春/java/trans_choice_data.csv";
			File data_reserve = new File(filename);
			BufferedWriter bw = new BufferedWriter(new FileWriter(data_reserve,false));
			bw.write('\ufeff');
			
			Pattern pattern_1 = Pattern.compile("<tr class=\"datagrid-.*?\">([\\s\\S]*?)</tr>");
			Pattern pattern_2 = Pattern.compile("<td class=\"datagrid\"[\\s\\S]*?<span>([\\s\\S]*?)</span>");
			Pattern pattern_3 = Pattern.compile("Next</a>");
			Pattern pattern_4 = Pattern.compile("Previous</a>");
			boolean Next = true;
			while(Next) {
				String html = driver.getPageSource();
				Matcher matcher = pattern_1.matcher(html);
				while(matcher.find()) {
					bw.newLine();
					String section_html = matcher.group();
					//System.out.println(section_html);
					Matcher section_matcher = pattern_2.matcher(section_html);
					while(section_matcher.find()) {
						bw.write(section_matcher.group(1).replace(',', ' ') + ",");
					}
				}
				Matcher NextMatcher = pattern_3.matcher(html);
				if(NextMatcher.find()) {
					Matcher PreMatcher = pattern_4.matcher(html);
					if(PreMatcher.find()) {
						driver.findElements(By.xpath("//table[@class='datagrid']//td[@align='right']/a")).get(2).click();
					}
					else driver.findElements(By.xpath("//table[@class='datagrid']//td[@align='right']/a")).get(0).click();
					driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				}
				else Next = false;
			}
			bw.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void add_course_pub_choice(WebDriver driver,List<WebElement> choice) {
		choice.get(0).click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody//div[@style='float:left;padding:2px 0px 0px 1px;']/a")));
		driver.findElement(By.xpath("//tbody//div[@style='float:left;padding:2px 0px 0px 1px;']/a")).click();
		driver.findElement(By.xpath("//table//form[@id='qyForm']//input[@id='pub_choice']")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		((JavascriptExecutor)driver).executeScript("document.getElementById(\"deptID\").getElementsByTagName(\"option\").item(0).value='ALL';");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//table//form[@id='qyForm']//input[@id='b_query']")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		try {
			String filename = "/Users/mac/Desktop/19春/java/pub_choice_data.csv";
			File data_reserve = new File(filename);
			BufferedWriter bw = new BufferedWriter(new FileWriter(data_reserve,false));
			bw.write('\ufeff');
			
			Pattern pattern_1 = Pattern.compile("<tr class=\"datagrid-.*?\">([\\s\\S]*?)</tr>");
			Pattern pattern_2 = Pattern.compile("<td class=\"datagrid\"[\\s\\S]*?<span>([\\s\\S]*?)</span>");
			Pattern pattern_3 = Pattern.compile("Next</a>");
			Pattern pattern_4 = Pattern.compile("Previous</a>");
			boolean Next = true;
			while(Next) {
				String html = driver.getPageSource();
				Matcher matcher = pattern_1.matcher(html);
				while(matcher.find()) {
					bw.newLine();
					String section_html = matcher.group();
					//System.out.println(section_html);
					Matcher section_matcher = pattern_2.matcher(section_html);
					while(section_matcher.find()) {
						bw.write(section_matcher.group(1).replace(',', ' ') + ",");
					}
				}
				Matcher NextMatcher = pattern_3.matcher(html);
				if(NextMatcher.find()) {
					Matcher PreMatcher = pattern_4.matcher(html);
					if(PreMatcher.find()) {
						driver.findElements(By.xpath("//table[@class='datagrid']//td[@align='right']/a")).get(2).click();
					}
					else driver.findElements(By.xpath("//table[@class='datagrid']//td[@align='right']/a")).get(0).click();
					driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				}
				else Next = false;
			}
			bw.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void add_course_politics(WebDriver driver,List<WebElement> choice) {
		choice.get(0).click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody//div[@style='float:left;padding:2px 0px 0px 1px;']/a")));
		driver.findElement(By.xpath("//tbody//div[@style='float:left;padding:2px 0px 0px 1px;']/a")).click();
		driver.findElement(By.xpath("//table//form[@id='qyForm']//input[@id='politics']")).click();
		driver.findElement(By.xpath("//table//form[@id='qyForm']//input[@id='b_query']")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		try {
			String filename = "/Users/mac/Desktop/19春/java/politics_data.csv";
			File data_reserve = new File(filename);
			BufferedWriter bw = new BufferedWriter(new FileWriter(data_reserve,false));
			bw.write('\ufeff');
			
			Pattern pattern_1 = Pattern.compile("<tr class=\"datagrid-.*?\">([\\s\\S]*?)</tr>");
			Pattern pattern_2 = Pattern.compile("<td class=\"datagrid\"[\\s\\S]*?<span>([\\s\\S]*?)</span>");
			Pattern pattern_3 = Pattern.compile("Next</a>");
			Pattern pattern_4 = Pattern.compile("Previous</a>");
			boolean Next = true;
			while(Next) {
				String html = driver.getPageSource();
				Matcher matcher = pattern_1.matcher(html);
				while(matcher.find()) {
					bw.newLine();
					String section_html = matcher.group();
					//System.out.println(section_html);
					Matcher section_matcher = pattern_2.matcher(section_html);
					while(section_matcher.find()) {
						bw.write(section_matcher.group(1).replace(',', ' ') + ",");
					}
				}
				Matcher NextMatcher = pattern_3.matcher(html);
				if(NextMatcher.find()) {
					Matcher PreMatcher = pattern_4.matcher(html);
					if(PreMatcher.find()) {
						driver.findElements(By.xpath("//table[@class='datagrid']//td[@align='right']/a")).get(2).click();
					}
					else driver.findElements(By.xpath("//table[@class='datagrid']//td[@align='right']/a")).get(0).click();
					driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				}
				else Next = false;
			}
			bw.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void add_course_gym(WebDriver driver,List<WebElement> choice) {
		choice.get(0).click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody//div[@style='float:left;padding:2px 0px 0px 1px;']/a")));
		driver.findElement(By.xpath("//tbody//div[@style='float:left;padding:2px 0px 0px 1px;']/a")).click();
		driver.findElement(By.xpath("//table//form[@id='qyForm']//input[@id='gym']")).click();
		driver.findElement(By.xpath("//table//form[@id='qyForm']//input[@id='b_query']")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		try {
			String filename = "/Users/mac/Desktop/19春/java/gym_data.csv";
			File data_reserve = new File(filename);
			BufferedWriter bw = new BufferedWriter(new FileWriter(data_reserve,false));
			bw.write('\ufeff');
			
			Pattern pattern_1 = Pattern.compile("<tr class=\"datagrid-.*?\">([\\s\\S]*?)</tr>");
			Pattern pattern_2 = Pattern.compile("<td class=\"datagrid\"[\\s\\S]*?<span>([\\s\\S]*?)</span>");
			Pattern pattern_3 = Pattern.compile("Next</a>");
			Pattern pattern_4 = Pattern.compile("Previous</a>");
			boolean Next = true;
			while(Next) {
				String html = driver.getPageSource();
				Matcher matcher = pattern_1.matcher(html);
				while(matcher.find()) {
					bw.newLine();
					String section_html = matcher.group();
					//System.out.println(section_html);
					Matcher section_matcher = pattern_2.matcher(section_html);
					while(section_matcher.find()) {
						bw.write(section_matcher.group(1).replace(',', ' ') + ",");
					}
				}
				Matcher NextMatcher = pattern_3.matcher(html);
				if(NextMatcher.find()) {
					Matcher PreMatcher = pattern_4.matcher(html);
					if(PreMatcher.find()) {
						driver.findElements(By.xpath("//table[@class='datagrid']//td[@align='right']/a")).get(2).click();
					}
					else driver.findElements(By.xpath("//table[@class='datagrid']//td[@align='right']/a")).get(0).click();
					driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				}
				else Next = false;
			}
			bw.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void add_course_liberal_computer(WebDriver driver,List<WebElement> choice) {
		choice.get(0).click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody//div[@style='float:left;padding:2px 0px 0px 1px;']/a")));
		driver.findElement(By.xpath("//tbody//div[@style='float:left;padding:2px 0px 0px 1px;']/a")).click();
		driver.findElement(By.xpath("//table//form[@id='qyForm']//input[@id='liberal_computer']")).click();
		driver.findElement(By.xpath("//table//form[@id='qyForm']//input[@id='b_query']")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		try {
			String filename = "/Users/mac/Desktop/19春/java/liberal_computer_data.csv";
			File data_reserve = new File(filename);
			BufferedWriter bw = new BufferedWriter(new FileWriter(data_reserve,false));
			bw.write('\ufeff');
			
			Pattern pattern_1 = Pattern.compile("<tr class=\"datagrid-.*?\">([\\s\\S]*?)</tr>");
			Pattern pattern_2 = Pattern.compile("<td class=\"datagrid\"[\\s\\S]*?<span>([\\s\\S]*?)</span>");
			Pattern pattern_3 = Pattern.compile("Next</a>");
			Pattern pattern_4 = Pattern.compile("Previous</a>");
			boolean Next = true;
			while(Next) {
				String html = driver.getPageSource();
				Matcher matcher = pattern_1.matcher(html);
				while(matcher.find()) {
					bw.newLine();
					String section_html = matcher.group();
					//System.out.println(section_html);
					Matcher section_matcher = pattern_2.matcher(section_html);
					while(section_matcher.find()) {
						bw.write(section_matcher.group(1).replace(',', ' ') + ",");
					}
				}
				Matcher NextMatcher = pattern_3.matcher(html);
				if(NextMatcher.find()) {
					Matcher PreMatcher = pattern_4.matcher(html);
					if(PreMatcher.find()) {
						driver.findElements(By.xpath("//table[@class='datagrid']//td[@align='right']/a")).get(2).click();
					}
					else driver.findElements(By.xpath("//table[@class='datagrid']//td[@align='right']/a")).get(0).click();
					driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				}
				else Next = false;
			}
			bw.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void add_course_english(WebDriver driver,List<WebElement> choice) {
		choice.get(0).click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody//div[@style='float:left;padding:2px 0px 0px 1px;']/a")));
		driver.findElement(By.xpath("//tbody//div[@style='float:left;padding:2px 0px 0px 1px;']/a")).click();
		driver.findElement(By.xpath("//table//form[@id='qyForm']//input[@id='english']")).click();
		driver.findElement(By.xpath("//table//form[@id='qyForm']//input[@id='b_query']")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		try {
			String filename = "/Users/mac/Desktop/19春/java/english_data.csv";
			File data_reserve = new File(filename);
			BufferedWriter bw = new BufferedWriter(new FileWriter(data_reserve,false));
			bw.write('\ufeff');
			
			Pattern pattern_1 = Pattern.compile("<tr class=\"datagrid-.*?\">([\\s\\S]*?)</tr>");
			Pattern pattern_2 = Pattern.compile("<td class=\"datagrid\"[\\s\\S]*?<span>([\\s\\S]*?)</span>");
			Pattern pattern_3 = Pattern.compile("Next</a>");
			Pattern pattern_4 = Pattern.compile("Previous</a>");
			boolean Next = true;
			while(Next) {
				String html = driver.getPageSource();
				Matcher matcher = pattern_1.matcher(html);
				while(matcher.find()) {
					bw.newLine();
					String section_html = matcher.group();
					//System.out.println(section_html);
					Matcher section_matcher = pattern_2.matcher(section_html);
					while(section_matcher.find()) {
						bw.write(section_matcher.group(1).replace(',', ' ') + ",");
					}
				}
				Matcher NextMatcher = pattern_3.matcher(html);
				if(NextMatcher.find()) {
					Matcher PreMatcher = pattern_4.matcher(html);
					if(PreMatcher.find()) {
						driver.findElements(By.xpath("//table[@class='datagrid']//td[@align='right']/a")).get(2).click();
					}
					else driver.findElements(By.xpath("//table[@class='datagrid']//td[@align='right']/a")).get(0).click();
					driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				}
				else Next = false;
			}
			bw.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void add_course_plan(WebDriver driver,List<WebElement> choice) {
		choice.get(0).click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody//div[@style='float:left;padding:2px 0px 0px 1px;']/a")));
		driver.findElement(By.xpath("//tbody//div[@style='float:left;padding:2px 0px 0px 1px;']/a")).click();
		
		//培养方案
		driver.findElement(By.xpath("//table//form[@id='qyForm']//input[@id='education_plan_bk']")).click();
		driver.findElement(By.xpath("//table//form[@id='qyForm']//input[@id='b_query']")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		try {
			String filename = "/Users/mac/Desktop/19春/java/ele_plan_data.csv";
			File data_reserve = new File(filename);
			BufferedWriter bw = new BufferedWriter(new FileWriter(data_reserve,false));
			bw.write('\ufeff');
			
			Pattern pattern_1 = Pattern.compile("<tr class=\"datagrid-.*?\">([\\s\\S]*?)</tr>");
			Pattern pattern_2 = Pattern.compile("<td class=\"datagrid\"[\\s\\S]*?<span>([\\s\\S]*?)</span>");
			Pattern pattern_3 = Pattern.compile("Next</a>");
			Pattern pattern_4 = Pattern.compile("Previous</a>");
			boolean Next = true;
			while(Next) {
				String html = driver.getPageSource();
				Matcher matcher = pattern_1.matcher(html);
				while(matcher.find()) {
					bw.newLine();
					String section_html = matcher.group();
					//System.out.println(section_html);
					Matcher section_matcher = pattern_2.matcher(section_html);
					while(section_matcher.find()) {
						bw.write(section_matcher.group(1).replace(',', ' ') + ",");
					}
				}
				Matcher NextMatcher = pattern_3.matcher(html);
				if(NextMatcher.find()) {
					Matcher PreMatcher = pattern_4.matcher(html);
					if(PreMatcher.find()) {
						driver.findElements(By.xpath("//table[@class='datagrid']//td[@align='right']/a")).get(2).click();
					}
					else driver.findElements(By.xpath("//table[@class='datagrid']//td[@align='right']/a")).get(0).click();
				}
				else Next = false;
			}
			bw.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	// log_in
	public static WebDriver log_in(String user_name,String password) {
		ChromeOptions chromeoptions = new ChromeOptions();
		chromeoptions.addArguments("--headless");
		WebDriver driver = new ChromeDriver(chromeoptions);
		driver.get("http://elective.pku.edu.cn");
		WebElement _user_name = driver.findElement(By.id("user_name"));
		_user_name.sendKeys(user_name);
		WebElement _password = driver.findElement(By.id("password"));
		_password.sendKeys(password);
		WebElement submit = driver.findElement(By.id("logon_button"));
		submit.click();
		return driver;
	}
	
	public static List<elec_result> get_elec_result(WebDriver driver,List<WebElement> choice) {
		List<elec_result> result = new ArrayList<elec_result>();
		Pattern pattern_1 = Pattern.compile("(<tr class=\"course[\\s\\S]*?</tr>)");
		Pattern pattern_2 = Pattern.compile("<span>([\\s\\S]*?)</span>");
		Pattern pattern_3 = Pattern.compile("([\\s\\S]*?)<br />\\(([\\s\\S]*?)\\)<br />([\\s\\S]*?)<br />([\\s\\S]*)");
		int row = 0,column = 0;
		choice.get(2).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		String html = driver.getPageSource().trim();
		Matcher matcher = pattern_1.matcher(html);
		while(matcher.find()) {
			String content = matcher.group(1).trim();
			column = 0;
			Matcher matcher_1 = pattern_2.matcher(content);
			while(matcher_1.find()) {
				String info = matcher_1.group(1).trim();
				Matcher matcher_2 = pattern_3.matcher(info);
				if(matcher_2.find()) {
					elec_result course = 
							new elec_result(row,column,matcher_2.group(1),matcher_2.group(2),matcher_2.group(3),matcher_2.group(4));
					result.add(course);
				}
				column ++;
			}
			row ++;
		}
		return result;
	}
	public static void main(String[] args) {
		
		WebDriver driver = log_in("1700012862","SHt991122");
		 
		List<WebElement> menu = get_menu(driver);
		//get_elective_plan(driver,menu);
		add_course_speciality(driver,menu);
		add_course_trans_choice(driver,get_menu(driver));
		add_course_pub_choice(driver,get_menu(driver));
		add_course_politics(driver,get_menu(driver));
		add_course_gym(driver,get_menu(driver));
		add_course_liberal_computer(driver,get_menu(driver));
		add_course_english(driver,get_menu(driver));
		add_course_plan(driver,get_menu(driver));
		
		//List<elec_result>result = get_elec_result(driver,menu);
		// test result
		
//		for(elec_result i:result) {
//			System.out.printf("row is %d and column is %d\n name is %s and classroom is %s\n other_info is %s and %s\n",i.row,i.column,i.name,i.classroom,i.week_form,i.exam);
//		}
		
		try{
			Thread.sleep(1000 * 10);
		}
		catch(InterruptedException e) {
		}
		driver.quit();
	}
}
