package java8_datetime;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;

/*
 * 기존 날자 관련 API(java.util.Date SimpleDateFormatter 등) 는 thread-safe 하지 않고
 * 사용자에게 잠재적인 동시성 문제가 발생할 수 있기에 기존 API 대산 JAVA 8 부터지원하는
 * 새로운 API(java.time) 를 사용할것을 권장한다!!! 
 */

public class SampleDateTime2 {

	public static void main(String[] args) {
		
		// java.time.LocalDate
		
		/*
		 * LocalDate 클래스는 위치의 날짜 정보를 가지고있다.
		 * 이 클래스는 java.util.Date 패키지의 Date 클래스와 혼동해서는 안된다.
		 * Date 클래스에서 만든 개체에는 날짜와 시간 정보가 모두 있는 반면
		 * LocalDate 클래스에서 만든 개체에는 날짜 정보만있다.
		 */
		Date date = new Date();
        LocalDate localDate = LocalDate.now();

        System.out.println(date);   // output : "Fri May 14 21:50:32 KST 2021"
        System.out.println(localDate); // output : "2021-05-14"
        System.out.println("===============================================");
        
        /*
         * LocalDate.now()를 사용하면 시스템의 현재 날짜 정보와 함께 LocalDate 인스턴스를 만들 수 있다.
         * 또한 LocalDate.of(...) 정적 메서드에 매개 변수를 제공하여 특정 날짜 정보가 있는
         * LocalDate 인스턴스를 만들 수 있다.
         */
        
        LocalDate localDateNow = LocalDate.now();
        LocalDate localDateOf = LocalDate.of(2019,2,15);
        LocalDate localDateOf2= LocalDate.of(2019, Month.FEBRUARY,15);

        System.out.println(localDateNow); //(현재 날짜) output: "2021-05-14" 
        System.out.println(localDateOf);  // output : "2019-02-15"
        System.out.println(localDateOf2); // output : "2019-02-15"
        System.out.println("===============================================");
        
        /*
         * LocalDate.parse() 메서드를 사용하여 문자열 값에서 인스턴스를 만들 수 있다.
         * 또한 플러스 또는 마이너스 방법을 사용하여 일, 월 또는 연도를 추가할 수 있다.
         * 그러나 LocalDate 클래스는 변경할 수 없으므로 변수에 새 값을 다시 할당해야 한다.
         */
        
        LocalDate localDateParsed = LocalDate.parse("2011-05-19"); // 문자열로 인스턴스 생성
        // LocalDate localDateParsed = LocalDate.of(2011,05,19); // of 라는 static method를 사용해 생성
        localDateParsed=localDateParsed.plusDays(2).minusYears(3);
        System.out.println(localDateParsed); //output: "2008-05-21"
        System.out.println("===============================================");
        
        
        
        // java.time.LocalTime
        
        /*
         * 이 클래스에는 시간 정보(시간 분 초)만 보유.
         * 인스턴스를 만드는 방법은 LocalDate 클래스와 유사
         */
        LocalTime localTimeNow = LocalTime.now();

        LocalTime localTimeOf = LocalTime.of(15,12,16);
        localTimeOf=localTimeOf.plusMinutes(33).minusHours(2);

        LocalTime localTimeParsed = LocalTime.parse("21:55");

        System.out.println(localTimeNow); // output : "18:32:42.901"
        System.out.println(localTimeOf);  // output : "13:45:16"
        System.out.println(localTimeParsed); // output : "21:55"
        System.out.println("===============================================");
        
        // java.time.LocalDateTime
        /*
         * LocalDateTime은 LocalDate 및 LocalTime 정보를 모두 저장
         * LocalDateTime.of() 메서드는 LocalDate 및 LocalTime 인스턴스를 매개 변수로 가져와서
         * 이를 사용하여 새 인스턴스를 만들 수 있다.
         */
        LocalDate localDate2 = LocalDate.now();
        LocalTime localTime2 = LocalTime.now();

        LocalDateTime localDateTimeNow =LocalDateTime.of(localDate2,localTime2);
        LocalDateTime specificLocalDateTime =LocalDateTime.of(2011,Month.JANUARY,15,15,33,22);
        
        // T는 날짜와 시간을 구분짓는 구분자이다.
        System.out.println(localDateTimeNow); // output : "2021-05-14T22:04:14.864395700"
        System.out.println(specificLocalDateTime); // output : "2011-01-15T15:33:22"
        System.out.println("===============================================");
        
        // java.time.ZoneId
        /*
         * 시스템이 물리적으로 존재하는 곳이 서로 다를 경우 위치 간에 날짜와 시간 차이가 있을 수 있다.
         * 따라서 다른 위치에서 시간을 계산하는 동안 위치의 영역 정보를 지정해야 할 수 있다.
         */
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println(zoneId);
        System.out.println("===============================================");
        
        /*
         * ZoneId.getAvailableZoneId() 방법을 사용하여 ZoneId 클래스 내부의 가능한
         * 모든 영역 ID를 확인할 수 있다.
         * 사용 가능한 모든 영역 ID를 포함하여 문자열 집합을 반환한다.
         * 
         */
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        System.out.println(availableZoneIds);
        System.out.println("===============================================");
        
        /*
         * LocalDateTime.now() 메서드도 ZoneId를 수락한다.
         * 따라서 하나의 ZoneId를 매개 변수로 제공하면 특정 영역에서 현재 날짜와 시간을 얻을 수 있다.
         */
        ZoneId romeZoneId = ZoneId.of("Europe/Rome");
        LocalDateTime localDateTime = LocalDateTime.now(romeZoneId);
        System.out.println(localDateTime);
        System.out.println("===============================================");
        
        // java.time.ZonedDateTime
        /*
         * ZonedDateTime 클래스에는 날짜, 시간 및 영역 ID가 포함된다.
         * LocalDateTime includes Local Date + LocalTime
         * ZonedDateTime includes LocalDateTime + ZoneId
         */
        ZonedDateTime currentLocationZonedDateTime = ZonedDateTime.now();
        System.out.println(currentLocationZonedDateTime);

        LocalDateTime localDateTime2 = LocalDateTime.now();
        ZoneId currentLocationZoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime2,currentLocationZoneId);
        System.out.println(zonedDateTime);
        System.out.println("===============================================");
        
        // java.time.Instant
        /* 특정 나라에서만 사용하는게 아니고 전세계에서 사용하는 프로그램이라면
         * 해당 지역의 시간을 저장, 표시해 주어야 한다.
         * 
         * 일부 응용 프로그램에서는 날짜와 시간 일관성을 보장하기 위해 UTC 형식으로
         * 날짜와 시간을 반환해야 할 수 있다.
         * 예를 들어 응용 프로그램이 있고 이 응용 프로그램에는 전 세계의많은 사용자가 있다면
         * 모든 사용자가 자신의 위치에 따라 응용 프로그램에서 이벤트 시간을 올바르게 확인하려면
         * UTC 형식으로 이벤트 시간을 저장하고 사용자에게 표시하면서 사용자의 영역 ID에 따라
         * 변환해야 할 수 있다.
         * 
         * java.time.Instant 클래스는 날짜와 시간을 UTC 형식으로 변환하는 데 사용.
         * 또한 ZonedDateTime.toInstant() 메서드를 사용하여 ZonedDateTime을 인스턴트로 변환할 수 있다.
         * 또는 Instant.now() 메서드를 사용하여 UTC 에 따라 현재 날짜와 시간을 얻을 수 있다.
         */
        Instant instant = Instant.now(); //output : "2021-05-14T13:24:36.824012300Z" **UTC 0
        System.out.println(instant);

        ZonedDateTime zonedDateTime2 = ZonedDateTime.now();
        System.out.println(zonedDateTime2.toInstant());
        System.out.println("===============================================");
        
        // java.time.period
        /*
         * period 클래스는 LocalDate의 특정 기간을 나타낸다(연도, 월, 일).
         * 날짜 간의 차이를 계산하는 데 사용할 수 있다.
         * 또한 특정 기간(연도, 월, 일)을 매개 변수로 제공하여 LocalDate.plus
         * (또는 LocalDate.minus() 메서드를 사용할 수 있다.
         * 
         * LocalTime 에는 사용할 수 없다!!!!! 이땐 duration 사용
         */
        LocalDate localDate3 = LocalDate.of(2019, Month.APRIL,15);
        LocalDate localDateNow3 = LocalDate.now();
        System.out.println(Period.between(localDate3,localDateNow3));
        //output: "P2Y29D" P-Period 2Y-> 2 Years 29D->29 Days
        // So there is 2 years and 29 days of difference between today and 2019-04-15

        Period oneYearPeriod = Period.ofYears(1);
        LocalDate oneYearAfter = LocalDate.now().plus(oneYearPeriod);
        System.out.println(oneYearAfter);
        System.out.println("===============================================");
        
        // java.time.duration
        /*
         * 특정 시간 간격(시간, 분, 초)을 지정하는 데 사용. LocalTime 클래스를 사용
         */
        LocalTime localTimeOf4 = LocalTime.of(22,34);
        LocalTime localTimeNow4 = LocalTime.now();

        Duration duration = Duration.between(localTimeNow4,localTimeOf4);
        System.out.println(duration); //output : "PT12H19M6.438S"
        // "PT12H19M6.438S" -> Difference between 2 LocalTime = 12 Hours 19 Minutes 6.438 Seconds
        System.out.println("===============================================");
        
        // java.time.temporal.ChronoUnit
        /*
         * 표준 날짜 기간 단위 집합의 열거
         * 로컬 데이트, 로컬 타임, 또는 로컬 데이트 시간을 조작하는 데 사용할 수 있다.
         */
        LocalDate localDateNow5 = LocalDate.now();
        LocalDate localDateNextWeek = localDateNow5.plus(1, ChronoUnit.WEEKS);
        LocalDate localDate2MonthsLater = localDateNow5.plus(2,ChronoUnit.YEARS);
        System.out.println(localDateNow5);
        //output: the date of today
        System.out.println(localDateNextWeek);
        //output : the date of next week
        System.out.println(localDate2MonthsLater);
        //output : the date of 2 months later

        LocalTime localTime6 = LocalTime.of(22,15);
        LocalTime localTime7 = LocalTime.of(15,12);
        System.out.println(ChronoUnit.MINUTES.between(localTime6,localTime7));
        //output : "-423" there is -423 minutes of difference between localTimes.
        System.out.println("===============================================");
        
        // 로마와 한국간 시간 차이를 분으로 구한다.
        LocalDateTime localDateTimeSystem = LocalDateTime.now();

        ZoneId zoneIdRome = ZoneId.of("Europe/Rome");
        LocalDateTime localDateTimeRome = LocalDateTime.now(zoneIdRome);

        System.out.println(ChronoUnit.MINUTES.between(localDateTimeRome,localDateTimeSystem));
        // 420 minutes of difference between Rome And Seoul. 한국과 로마는 7시간 차이
        System.out.println("===============================================");
        
        //  java.time.format.DateTimeFormatter
        LocalDate localDate8 = LocalDate.now();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        DateTimeFormatter dateTimeFormatter1= DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter dateTimeFormatter2= DateTimeFormatter.ofPattern("EEE-dd/MM/yyyy");

        System.out.println(localDate8);
        //output : "2021-04-18" default format of the LocalDate
        System.out.println(dateTimeFormatter.format(localDate8));
        //output : "18 04 2021" customized format of the LocalDate ("dd MM yyyy")
        System.out.println(dateTimeFormatter1.format(localDate8));
        //output : "18/04/2021" customized format of the LocalDate ("dd/MM/yyyy")
        System.out.println(dateTimeFormatter2.format(localDate8));
        //output : "Paz-18/04/2021" customized format of the LocalDate ("EEE-dd/MM/yyyy")

        LocalTime localTime8 = LocalTime.now();
        DateTimeFormatter dateTimeFormatter3 = DateTimeFormatter.ofPattern("hh--mm--ss");
        System.out.println(dateTimeFormatter3.format(localTime8));
        //output : "10--48--15" customized format of the LocalTime ("hh--mm--ss")
	}
}