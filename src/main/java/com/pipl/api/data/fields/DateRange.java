package com.pipl.api.data.fields;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import com.google.gson.annotations.Expose;

/**
 * A time interval represented as a range of two dates. DateRange objects are
 * used inside DOB, Job and Education objects.
 */
public class DateRange implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	private Date start;
	@Expose
	private Date end;

	/**
	 * `start` and `end` are <code>Date</code> objects, both are required.
	 * <p/>
	 * For creating a DateRange object for an exact date (like if exact
	 * date-of-birth is known) just pass the same value for `start` and `end`.
	 * 
	 * @param start
	 *            start Date
	 * @param end
	 *            end Date
	 */
	public DateRange(Date start, Date end) {
		if (start.before(end)) {
			setStart(start);
			setEnd(end);
		} else {
			setStart(end);
			setEnd(start);
		}
	}

	private DateRange(Builder builder) {
		this(builder.start, builder.end);
	}

	public static class Builder {
		private Date start;
		private Date end;

		public Builder start(Date start) {
			this.start = start;
			return this;
		}

		public Builder end(Date end) {
			this.end = end;
			return this;
		}

		public DateRange build() {
			return new DateRange(this);
		}
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}

	public boolean equals(DateRange obj) {
		return this.start.equals(obj.start) && this.end.equals(obj.end);
	}

	/**
	 * @return True if the object holds an exact date (start=end), False
	 *         otherwise.
	 */
	public boolean isExact() {
		return start.equals(end);
	}

	/**
	 * @return The middle of the date range (a datetime.date object). return
	 *         self.start + (self.end - self.start) / 2
	 */
	public Date middle() {
		Long t1 = start.getTime();
		Long t2 = end.getTime();
		return new Date(t1 + (t2 - t1) / 2);
	}

	/**
	 * @return A tuple of two ints - the year of the start date and the year of
	 *         the end date.
	 */
	public ArrayList<Integer> yearsRange() {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(start);
		c2.setTime(end);
		return new ArrayList<Integer>(Arrays.asList(c1.get(Calendar.YEAR),
				c2.get(Calendar.YEAR)));
	}

	/**
	 * Transform a range of years (two ints) to a DateRange object.
	 * 
	 * @param startYear
	 *            startYear
	 * @param endYear
	 *            endYear
	 * @return <code>DateRange</code> object
	 */
	public static DateRange fromYearsRange(int startYear, int endYear) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(startYear, Calendar.JANUARY, 1);
		Date start = calendar.getTime();
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(endYear, Calendar.DECEMBER, 31);
		Date end = calendar1.getTime();
		return new DateRange(start, end);
	}

}