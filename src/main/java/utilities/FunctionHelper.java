package utilities;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public final class FunctionHelper {

    private FunctionHelper() {

    }

    public static int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(9999);
    }

    public static void sleepInSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getCurrentDay() {
        DateTime nowUTC = new DateTime(DateTimeZone.UTC);
        int day = nowUTC.getDayOfMonth();
        if (day < 10) {
            String dayValue = "0" + day;
            return dayValue;
        }
        return String.valueOf(day);
    }

    public static String getCurrentMonth() {
        DateTime now = new DateTime(DateTimeZone.UTC);
        int month = now.getMonthOfYear();
        if (month < 10) {
            String monthValue = "0" + month;
            return monthValue;
        }
        return String.valueOf(month);
    }

    public static String getCurrentYear() {
        DateTime now = new DateTime(DateTimeZone.UTC);
        return String.valueOf(now.getYear());
    }

    public static String getToday() {
        String today = getCurrentMonth() + "/" + getCurrentDay() + "/" + getCurrentYear();
        return today;
    }

    public static String getTodayByFormat(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }
}
