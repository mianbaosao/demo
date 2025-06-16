package org.example.suanfa.project.kuaikan.database;

import com.google.common.collect.Lists;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

@Slf4j
public class DateUtil {

  private static final String YYYYMMDDhhmmssFormat = "yyyy-MM-dd HH:mm:ss";
  // 1天的毫秒数（毫秒）
  public static final long TS_OF_ONE_DAY = 60 * 60 * 24 * 1000L;
  public static final String TIME_ZONE_GMT8 = "GMT+8";
  private static final int ONE_DAY_MINUTE_FOR_TEST = 10;

  /** 获取今天是本年的第几周. */
  public static int getWeekOfCurrentDay() {
    int value = getWeekByDateOffset(0);
    log.debug("week of current day :{}", value);
    return value;
  }

  /** 获取昨天是本年的第几周. */
  public static int getWeekOfYesterday() {
    int value = getWeekByDateOffset(-1);
    log.debug("week of yesterday:{}", value);
    return value;
  }

  /** 通过指定日期偏移量，计算对应日期是本年第几周. */
  public static int getWeekByDateOffset(int dateOffset) {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    calendar.add(Calendar.DATE, dateOffset);
    calendar.setFirstDayOfWeek(Calendar.MONDAY);
    int week = calendar.get(Calendar.WEEK_OF_YEAR);
    int month = calendar.get(Calendar.MONTH);
    // 如果月份是12月，且求出来的周数是第一周，说明该日期实质上是这一年的第53周，也是下一年的第一周
    if (month >= 11 && week <= 1) {
      week += 52;
    }
    log.debug("week of the year:{}, date offset:{}", week, dateOffset);
    return week;
  }

  /*
   * @param timestamp 毫秒
   */
  public static int getDateByTimestamp(long timestamp) {
    Date date = new Date();
    date.setTime(timestamp);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    sdf.setTimeZone(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    String dateStr = sdf.format(date);
    int value = NumberUtils.toInt(dateStr);
    log.debug("getDateByTimestamp:{}", value);
    return value;
  }

  public static String formatByIntDate(int intDate, String format) {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));

    try {
      Date date = sdf.parse(String.valueOf(intDate));
      return format(format, date.getTime());
    } catch (ParseException e) {
      return "";
    }
  }

  /** 获取今天的日期. */
  public static int getCurrentDate() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    sdf.setTimeZone(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    String dateStr = sdf.format(new Date());
    int value = NumberUtils.toInt(dateStr);
    log.debug("current date:{}", value);
    return value;
  }

  /** 获取今天的月期. */
  public static int getCurrentMonth() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
    sdf.setTimeZone(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    String dateStr = sdf.format(new Date());
    int value = NumberUtils.toInt(dateStr);
    log.debug("current date:{}", value);
    return value;
  }

  /** 获取昨天的日期. */
  public static int getYesterdayDate() {
    int value = getDateByDateOffset(-1);
    return value;
  }

  /** 获取今天开始 dateOffset 的月期. */
  public static int getYesterdayMonth() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
    sdf.setTimeZone(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    calendar.add(Calendar.DATE, -1);
    int value = NumberUtils.toInt(sdf.format(calendar.getTime()));
    log.debug("baseDate={}, dateOffset={}, value={}", 0, -1, value);
    return value;
  }

  /** 指定 开始时间和 日期偏移量 默认days[-30,30] baseDate > 0 */
  public static List<Integer> getDateListWithBaseDateAndDays(int baseDate, int days) {
    if (days > 31 || days < -31) {
      return Lists.newArrayList();
    }
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < Math.abs(days); i++) {
      int dateByDateOffset;
      if (days >= 0) {
        dateByDateOffset = getDateByDateOffset(baseDate, i);
      } else {
        dateByDateOffset = getDateByDateOffset(baseDate, -i);
      }
      result.add(dateByDateOffset);
    }
    result = result.stream().sorted().collect(Collectors.toList());
    return result;
  }

  /** 通过指定日期偏移量，获取对应日期. */
  public static int getDateByDateOffset(int dateOffset) {
    return getDateByDateOffset(0, dateOffset);
  }

  /** 通过 */
  public static int getDateByDateOffset(int baseDate, int dateOffset) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    sdf.setTimeZone(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    if (baseDate > 0) {
      try {
        calendar.setTime(sdf.parse(String.valueOf(baseDate)));
      } catch (ParseException e) {
        log.error("parse date error. baseDate={}", baseDate);
        throw new RuntimeException(e);
      }
    }
    calendar.add(Calendar.DATE, dateOffset);

    int value = NumberUtils.toInt(sdf.format(calendar.getTime()));
    log.debug("baseDate={}, dateOffset={}, value={}", baseDate, dateOffset, value);
    return value;
  }

  /** 获取今天是哪一年. */
  public static int getYearOfCurrentDay() {
    int value = getYearByDateOffset(0);
    log.debug("year of current day:{}", value);
    return value;
  }

  /** 获取昨天是哪一年. */
  public static int getYearOfYesterday() {
    int value = getYearByDateOffset(-1);
    log.debug("year of yesterday:{}", value);
    return value;
  }

  /** 通过指定日期偏移量，获取对应日期是哪一年. */
  public static int getYearByDateOffset(int dateOffset) {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    calendar.add(Calendar.DATE, dateOffset);
    int value = calendar.get(Calendar.YEAR);
    log.debug("year:{}, date offset:{}", value, dateOffset);
    return value;
  }

  /** 获取下个月的月份数. */
  public static int getNextMonth() {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    calendar.add(Calendar.MONTH, 1);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
    sdf.setTimeZone(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    String dateStr = sdf.format(calendar.getTime());
    int value = NumberUtils.toInt(dateStr);
    return value;
  }

  /** 获取今天天零点的时间戳（单位：毫秒）. */
  public static long getStartTimestampOfToday() {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTimeInMillis();
  }

  /** 获取今天天零点的时间戳（单位：毫秒）. */
  public static long getStartTimestampOfYesterday() {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    calendar.add(Calendar.DATE, -1);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTimeInMillis();
  }

  /** 获取第二天零点的时间戳（单位：毫秒）. */
  public static long getStartTimestampOfTomorrow() {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    calendar.add(Calendar.DATE, 1);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTimeInMillis();
  }

  /** 获取当前小时开始的时间戳（单位：毫秒）. */
  public static long getStartTimestampOfThisHour() {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTimeInMillis();
  }

  /** 获取指定日期所在小时开始的时间戳（单位：毫秒）. */
  public static long getStartTimestampOfThisHourByTs(long timestamp) {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    calendar.setTimeInMillis(timestamp);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTimeInMillis();
  }

  /**
   * 获取指定时间戳所在的整十分钟数时间戳
   *
   * @return
   */
  public static long getMinuteByTs(long timestamp) {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    calendar.setTimeInMillis(timestamp);
    int minute = calendar.get(Calendar.MINUTE) / ONE_DAY_MINUTE_FOR_TEST * ONE_DAY_MINUTE_FOR_TEST;
    calendar.set(Calendar.MINUTE, minute);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTime().getTime();
  }

  /** 获取下个小时的时间戳（单位：毫秒）. */
  public static long getStartTimestampNextHour() {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    calendar.add(Calendar.HOUR, 1);
    return calendar.getTimeInMillis();
  }

  /** 获取指定时间当天0点时间戳（单位：毫秒）. */
  public static long getStartTimestampOfCurrentDayByTs(long timestamp) {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    calendar.setTimeInMillis(timestamp);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTimeInMillis();
  }

  /** 获取指定时间的第二天零点的时间戳（单位：毫秒）. */
  public static long getStartTimestampOfTomorrowByTs(long timestamp) {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    calendar.setTimeInMillis(timestamp);
    calendar.add(Calendar.DATE, 1);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTimeInMillis();
  }

  /** 获取下个月的时间戳（单位：毫秒）. */
  public static long getStartTimestampOfNextMonth() {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    calendar.add(Calendar.MONTH, 1);
    calendar.set(Calendar.DATE, 1);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    return calendar.getTimeInMillis();
  }

  /** 获取下个月的时间戳（单位：毫秒）. */
  public static long getStartTimestampOfMonthAfterNext() {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    calendar.add(Calendar.MONTH, 2);
    calendar.set(Calendar.DATE, 1);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    return calendar.getTimeInMillis();
  }

  /** 通过指定日期偏移量，计算对应日期是星期几. 对返回值判断时，请使用Calendar类提供的常量：如Calendar.MONDAY */
  public static int getDayForWeekByDateOffset(int dateOffset) {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    calendar.setFirstDayOfWeek(Calendar.MONDAY);
    calendar.add(Calendar.DATE, dateOffset);
    int day = calendar.get(Calendar.DAY_OF_WEEK);
    log.debug("day of the week:{}, date offset:{}", day, dateOffset);
    return day;
  }

  /** 获取本周一零点的时间戳（单位：毫秒）. */
  public static long getStartTimestampOfThisWeek() {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    calendar.setFirstDayOfWeek(Calendar.MONDAY);
    calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTimeInMillis();
  }

  /** 获取下周一零点的时间戳（单位：毫秒）. */
  public static long getStartTimestampOfNextWeek() {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    calendar.setFirstDayOfWeek(Calendar.MONDAY);
    calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    calendar.add(Calendar.DATE, 7);
    return calendar.getTimeInMillis();
  }

  /** 获取上周一零点的时间戳（单位：毫秒）. */
  public static long getStartTimestampOfLastWeek() {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    calendar.setFirstDayOfWeek(Calendar.MONDAY);
    calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    calendar.add(Calendar.DATE, -7);
    return calendar.getTimeInMillis();
  }

  /** 获取本月一日零点的时间戳（单位：毫秒）. */
  public static long getStartTimestampOfThisMonth() {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    return calendar.getTimeInMillis();
  }

  /** 获取上个月一日零点的时间戳（单位：毫秒）. */
  public static long getStartTimestampOfLastMonth() {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    calendar.add(Calendar.MONTH, -1);
    return calendar.getTimeInMillis();
  }

  /** 得到指定时间的当月1日时间戳 */
  public static long getCurrentMonthBegin(long dateTimeMillis) {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    Date data = new Date(dateTimeMillis);
    calendar.setTime(data);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    return calendar.getTimeInMillis();
  }

  /** 得到指定时间的上月1日时间戳 */
  public static long getPreviousMonthBegin(long dateTimeMillis) {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    Date data = new Date(dateTimeMillis);
    calendar.setTime(data);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    calendar.add(Calendar.MONTH, -1);
    return calendar.getTimeInMillis();
  }

  /** 单位：秒。 计算 {@code days} 天之后的时间戳。 */
  public static long daysLater(int days) {
    return LocalDate.now().plusDays(days).atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
  }

  /**
   * 获得自 Unix 时间戳开始至此时此刻过去的天数，本质上等于 UNIX_TIMESTAMP 的毫秒数转化为天，但是需要加上时区，否则北京时区会有 8 小时误差，一般用于 Redis
   * 每日更新的键的分片。
   *
   * <p>已过期，使用 getCurrentDate 代替。
   */
  @Deprecated
  public static long getUnixDayOffset() {
    return getCurrentDate();
  }

  public static String format(String format, long timestamp) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
    return simpleDateFormat.format(timestamp);
  }

  // timeStamp -> yyyy-MM-dd hh:mm:ss
  public static String getYyyyMmDdhhmmss(long timeStamp) {
    SimpleDateFormat format = new SimpleDateFormat(YYYYMMDDhhmmssFormat);
    return format.format(new Date(timeStamp));
  }

  // yyyy-MM-dd hh:mm:ss -> timeStamp
  public static long parseYyyyMmDdhhmmssToTimeStamp(String formattedTime) {
    log.debug("formattedTime={}", formattedTime);
    if (StringUtils.isBlank(formattedTime)) {
      return System.currentTimeMillis();
    }
    SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDhhmmssFormat);
    try {
      return sdf.parse(formattedTime).getTime();
    } catch (ParseException e) {
      log.error("parse time error. formattedTime={}", formattedTime, e);
      return System.currentTimeMillis();
    }
  }

  /** 获取一个月前（30天）0点的时间戳（单位：毫秒）. */
  public static long getStartTimeStampOfOneMonthAgo() {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    calendar.add(Calendar.DATE, -30);
    return calendar.getTimeInMillis();
  }

  /** 获取 -前 +后 offset 天 0点的时间戳（单位：毫秒）. */
  public static long getStartTimeStampOfOffset(int offset) {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    calendar.add(Calendar.DATE, offset);
    return calendar.getTimeInMillis();
  }

  public static Duration getDurationBetweenNowAndTomorrow() {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime startOfTomorrow =
        LocalDateTime.ofEpochSecond(
            DateUtil.getStartTimestampOfTomorrow() / 1000, 0, ZoneOffset.UTC);
    return Duration.between(now, startOfTomorrow);
  }

  /** 获取指定日期的时间戳（单位：毫秒）. */
  public static long getTimeStampByDate(int targetDate) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));

    try {
      Date date = sdf.parse(String.valueOf(targetDate));
      return date.getTime();
    } catch (ParseException e) {
      return 0;
    }
  }

  // 这周的开始时间
  public static int getWeekStartTime() {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    simpleDateFormat.setTimeZone(TimeZone.getTimeZone(TIME_ZONE_GMT8));

    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_GMT8));
    cal.setFirstDayOfWeek(Calendar.MONDAY);
    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    return NumberUtils.toInt(simpleDateFormat.format(cal.getTime()));
  }

  /** 获取本周开始到现在的 dateList */
  public static List<Integer> getStartOfThisWeekBetweenTodayDate(int today) {
    ArrayList<Integer> result = Lists.newArrayList();
    for (int i = 0; i < 7; i++) {
      int dateByDateOffset = getDateByDateOffset(getWeekStartTime(), i);
      result.add(dateByDateOffset);
      if (dateByDateOffset == today) {
        return result;
      }
    }
    return result;
  }

  /**
   * 获取两个日期的间隔天数
   *
   * @param date1 日期一
   * @param date2 日期二
   * @return 间隔天数
   */
  public static int durationDays(Date date1, Date date2) {
    Calendar cal1 = Calendar.getInstance();
    cal1.setTime(date1);

    Calendar cal2 = Calendar.getInstance();
    cal2.setTime(date2);
    int day1 = cal1.get(Calendar.DAY_OF_YEAR);
    int day2 = cal2.get(Calendar.DAY_OF_YEAR);

    int year1 = cal1.get(Calendar.YEAR);
    int year2 = cal2.get(Calendar.YEAR);
    // 同一年
    if (year1 != year2) {
      int timeDistance = 0;
      for (int i = year1; i < year2; i++) {
        // 闰年
        if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
          timeDistance += 366;
        } else {
          // 不是闰年
          timeDistance += 365;
        }
      }
      return Math.abs(timeDistance + (day2 - day1));
    } else {
      // 不同年
      return Math.abs(day2 - day1);
    }
  }

  /**
   * 获取指定日期加上指定天数后的凌晨0点
   *
   * @param specifiedDate 指定日期
   * @param offsetDay 指定天数
   * @return 指定日期加上指定天数后的凌晨0点
   */
  public static long getStartTimestampOfOffsetDayAfterSpecifiedDate(
      Date specifiedDate, int offsetDay) {
    long timestamp = DateUtil.getStartTimestampOfCurrentDayByTs(specifiedDate.getTime());
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(timestamp);
    calendar.add(Calendar.DATE, offsetDay);
    return calendar.getTimeInMillis();
  }

  public static long parseDateBySpecificFormat(String format, String dateStr) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
    try {
      return simpleDateFormat.parse(dateStr).getTime();
    } catch (ParseException e) {
      log.error("format exception ! format:{} dateStr:{}", format, dateStr, e);
      throw new RuntimeException(e);
    }
  }
    public static void main(String[] args) {
        System.out.println(Math.abs(1100002214 % 2));
    }
}
