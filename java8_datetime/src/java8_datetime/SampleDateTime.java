package java8_datetime;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

/*
 * 기존 날자 관련 API(java.util.Date SimpleDateFormatter 등) 는 thread-safe 하지 않고
 * 사용자에게 잠재적인 동시성 문제가 발생할 수 있기에 기존 API 대산 JAVA 8 부터지원하는
 * 새로운 API(java.time) 를 사용할것을 권장한다!!! 
 */
public class SampleDateTime {

	public static void main(String[] args) {
		// 예전 방식
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.MONTH, 1);
	    Date time = cal.getTime();
	    System.out.println("예전방식의 출력 : " + time);

	    // JAVA 8
	    LocalDate localDate = LocalDate.now().plusMonths(1);
	    System.out.println("JAVA8방식의 출력 : " + localDate);
	    
	    LocalDate localDatesample = LocalDate.of(2020, Month.JANUARY, 1);
	    LocalDate localDate1 = LocalDate.of(2020, 1, 1);
	    LocalDate localDate2 = LocalDate.now();
	    
	    System.out.println("=======================================================");
	    System.out.println("localDatesample : " + localDatesample);
	    System.out.println("localDate1 : " + localDate1);
	    System.out.println("localDate2 : " + localDate2);
	    System.out.println("=======================================================");

	    LocalTime localTime = LocalTime.of(12, 30);
	    LocalTime localTime1 = LocalTime.of(12, 30, 59);// with seconds
	    LocalTime localTime2 = LocalTime.of(12, 30, 59, 1000);// with seconds and nanoSeconds
	    LocalTime localTime3 = LocalTime.ofSecondOfDay(1234);
	    LocalTime localTime4 = LocalTime.ofNanoOfDay(1000);
	    LocalTime localTime5 = LocalTime.now();
	    
	    System.out.println("=======================================================");
	    System.out.println("localTime : " + localTime);
	    System.out.println("localTime1 : " + localTime1);
	    System.out.println("localTime2 : " + localTime2);
	    System.out.println("localTime3 : " + localTime3);
	    System.out.println("localTime4 : " + localTime4);
	    System.out.println("localTime5 : " + localTime5);
	    System.out.println("=======================================================");
	    
	    

	    LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
	    LocalDateTime.of(2020, 1, 1, 12, 30);
	    LocalDateTime.of(2020, 1, 1, 12, 30, 59);
	    LocalDateTime.of(2020, Month.JANUARY, 1, 12, 30, 59);
	    LocalDateTime.of(2020, Month.JANUARY, 1, 12, 30, 59, 100);
	    
	    System.out.println("=======================================================");
	    System.out.println("localDateTime : " + localDateTime);
	    System.out.println("=======================================================");
	    
	    LocalDate localDateNow = LocalDate.now();
	    LocalDate localDatePlusMonth = localDateNow.plusMonths(1);
	    LocalDate localDatePlusDays = localDateNow.plusDays(1);
	    LocalDate localDatePlusYear = localDateNow.plusYears(1);
	    LocalDate localDatePlusWeeks = localDateNow.plusWeeks(1);
	    
	    System.out.println("=======================================================");
	    System.out.println(localDateNow);
	    System.out.println(localDatePlusMonth);
	    System.out.println(localDatePlusDays);
	    System.out.println(localDatePlusYear);
	    System.out.println(localDatePlusWeeks);
	    System.out.println("=======================================================");
	    
	    LocalTime localTimeNow = LocalTime.now();
	    LocalTime localTimeMinusHr = localTimeNow.minusHours(1);
	    LocalTime localTimeMinusMin = localTimeNow.minusMinutes(10);
	    LocalTime localTimeMinusSec = localTimeNow.minusSeconds(50);
	    LocalTime localTimeMinusNano = localTimeNow.minusNanos(122);
	    
	    System.out.println("=======================================================");
	    System.out.println(localTimeNow);
	    System.out.println(localTimeMinusHr);
	    System.out.println(localTimeMinusMin);
	    System.out.println(localTimeMinusSec);
	    System.out.println(localTimeMinusNano);
	    System.out.println("=======================================================");
	    
	    // 체인방식
	    LocalDateTime localDateTimeChain = LocalDateTime.of(LocalDate.now(), LocalTime.now())
	    												.plusDays(1)
	    												.minusHours(2)
	    												.plusMonths(3);
	    System.out.println("=======================================================");
	    System.out.println(localDateTimeChain);
	    System.out.println("=======================================================");
	    
	    System.out.println("=======================================================");
	    LocalDate localDateSample = LocalDate.of(2020, Month.JANUARY, 1);
	    Period period = Period.of(1, 1, 1);
	    serviceMyBike(localDateSample, period); // after 1 year, 1 day and 1 month
	    serviceMyBike(localDateSample, Period.of(0, 0, 1));//after 1 day
	    serviceMyBike(localDateSample, Period.ofDays(30));
	    serviceMyBike(localDateSample, Period.ofMonths(3));
	    serviceMyBike(localDateSample, Period.ofYears(1));
	    System.out.println("=======================================================");
	    
	    System.out.println("=======================================================");
	    LocalTime localTimeSample = LocalTime.of(12, 30);
	    Duration duration = Duration.ofHours(1);
	    remindMe(localTimeSample,duration);
	    remindMe(localTimeSample,Duration.ofMinutes(20));
	    remindMe(localTimeSample,Duration.ofMillis(100));
	    remindMe(localTimeSample,Duration.ofSeconds(30));
	    System.out.println("=======================================================");
	    
	    System.out.println("=======================================================");
	    LocalDateTime localDateTimeSample = LocalDateTime.of(LocalDate.now(), LocalTime.now());
	    Period period2 = Period.of(1, 1, 1);
	    Duration duration2 = Duration.ofHours(1);
	    setAppointment(localDateTimeSample, period2, duration2);
	    System.out.println("=======================================================");
	    
	    // 다양한 Static Method 제공
	    Period.of(1, 1, 1);
	    Period.ofDays(1);
	    Period.ofYears(1);
	    Period.ofMonths(2);
	    Period.ofWeeks(2);

	    Duration.ofHours(1);
	    Duration.ofMinutes(10);
	    Duration.ofSeconds(23);
	    Duration.ofMillis(2323);
	    Duration.ofNanos(45);
	    
	}

	public static void serviceMyBike(LocalDate localDate, Period period) {
	    LocalDate localDatePlusPeriod = localDate.plus(period);
	    System.out.println(localDatePlusPeriod);
	}
	
	public static void remindMe(LocalTime localTime, Duration duration) {
	    LocalTime localTime1 = localTime.plus(duration);
	    System.out.println("Reminder will start on : " + localTime1);
	}
	
	public static void setAppointment(LocalDateTime localDateTime, Period period, Duration duration) {
	    LocalDateTime plusPeriod = localDateTime.plus(period);
	    LocalDateTime plusDuration = plusPeriod.plus(duration);
	    System.out.println("Appointment will be at : " + plusDuration);

	}
}