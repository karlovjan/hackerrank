package algorithms;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TimeAMPMConversionTest {

    /**
     * https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
     *
     */


    private String timeConvertFromAMPMFormat(String ampmformatTime){

        return LocalTime.parse(ampmformatTime, DateTimeFormatter.ofPattern("hh:mm:ssa")).format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    @Test
    void midnightTest() {

        assertEquals("00:01:00", timeConvertFromAMPMFormat("12:01:00AM"));
    }

    @Test
    void noonTimeTest() {
        //poledne
        assertEquals("12:01:00", timeConvertFromAMPMFormat("12:01:00PM"));
    }

    @Test
    void afternoonTimeTest() {
        //odpoledne
        assertEquals("19:05:45", timeConvertFromAMPMFormat("07:05:45PM"));
    }

    @Test
    void afternoonTimeTest2() {
        //vecer
        assertEquals("23:25:45", timeConvertFromAMPMFormat("11:25:45PM"));
    }
}
