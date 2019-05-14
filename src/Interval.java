package query;

/**
 * һ��ʱ�䣬���磺
 * <br> ���� ������ 10-12��
 * 
 * <p>
 * ����һ��������ֱ��Ӧ���� 1-7
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
			case 'һ': return MONDAY;
			case '��': return TUESDAY;
			case '��': return WEDNESDAY;
			case '��': return THURSDAY;
			case '��': return FRIDAY;
			case '��': return SATURDAY;
			case '��': return SUNDAY;
			}
			return SUNDAY;
		}
		@Override
		public String toString() {
			return "��" + getValue();
		}
	}
	
	/**
	 * ���ܻ���˫��
	 * @author wck
	 *
	 */
	static public enum WhichWeek {
		ODD, EVEN, ALL;
		
		static public WhichWeek parse(String s) {
			switch (s.charAt(0)) {
			case '��': return ODD;
			case '˫': return EVEN;
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
	
	@Override
	public String toString() {
		return which.toString() + day + ":" + from + "->" + to;
	}
}