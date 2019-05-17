package query;

/**
 * 一段时间，比如：
 * <br> 单周 星期三 10-12节
 * 
 * <p>
 * 星期一到星期天分别对应整数 1-7
 * 
 * @author wck
 *
 */
public class Interval {
	static public enum Day {
		MONDAY(1), TUESDAY(2), WEDNESDAY(3),
		THURSDAY(4), FRIDAY(5), SATURDAY(6), SUNDAY(7);
		private final int value;
		private Day(int value) {
			this.value = value;
		}
		public int getValue() {
			return value;
		}
		static public Day parse(String s) {
			switch (s.charAt(0)) {
			case '一': return MONDAY;
			case '二': return TUESDAY;
			case '三': return WEDNESDAY;
			case '四': return THURSDAY;
			case '五': return FRIDAY;
			case '六': return SATURDAY;
			case '日': return SUNDAY;
			case '1': return MONDAY;
			case '2': return TUESDAY;
			case '3': return WEDNESDAY;
			case '4': return THURSDAY;
			case '5': return FRIDAY;
			case '6': return SATURDAY;
			case '7': return SUNDAY;
			}
			return SUNDAY;
		}
		@Override
		public String toString() {
			return "周" + getValue();
		}
	}
	
	/**
	 * 单周还是双周
	 * @author wck
	 *
	 */
	static public enum WhichWeek {
		ODD, EVEN, ALL;
		
		static public WhichWeek parse(String s) {
			switch (s.charAt(0)) {
			case '单': return ODD;
			case '双': return EVEN;
			}
			return ALL;
		}
		
		@Override
		public String toString() {
			switch (this) {
			case ODD: return "odd";
			case EVEN: return "even";
			default: return "both";
			}
		}
	}
	
	private WhichWeek which;
	private Day day;
	private int from, to;
	
	public Interval(String which, String day, String from, String to) {
		this(WhichWeek.parse(which), 
				Day.parse(day), 
				Integer.parseInt(from), 
				Integer.parseInt(to));
	}
	
	public Interval(WhichWeek which, Day day, int from, int to) {
		super();
		this.which = which;
		this.day = day;
		this.from = from;
		this.to = to;
	}
	public Day getDay() {
		return day;
	}
	public int getFrom() {
		return from;
	}
	public int getTo() {
		return to;
	}
	public int getSpan() {
		return to - from + 1;
	}
	
	@Override
	public String toString() {
		return which.toString() + day + ":" + from + "->" + to;
	}
}
