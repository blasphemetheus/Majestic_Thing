package cs3500.hw01.duration;

import org.junit.Test;

import cs3500.hw01.duration.CompactDuration;
import cs3500.hw01.duration.Duration;
import cs3500.hw01.duration.HmsDuration;

import static org.junit.Assert.assertEquals;

/** Tests for the format method of {@link Duration}s. 
    Add your tests to this class to assure that your format 
    method works properly.
*/
public abstract class AbstractDurationFormatTest {

  @Test
  public void formatExample1() {
    assertEquals("4 hours, 0 minutes, and 9 seconds",
                  hms(4, 0, 9)
                    .format("%h hours, %m minutes, and %s seconds"));
  }

  @Test
  public void formatExample2() {
    assertEquals("4:05:17",
                  hms(4, 5, 17).format("%h:%M:%S"));
  }

  // ADD MORE TESTS HERE
  // Your tests must only use hms(...) and sec(...) to construct new Durations
  // and must *not* directly say "new CompactDuration(...)" or
  // "new HmsDuration(...)"


  @Test (expected = IllegalArgumentException.class)
  public void testNull() {
    assertEquals("FUn", hms(9, 0, 34).format(null));
  }

  @Test
  public void formatExampleBlank() {
    assertEquals("", hms(2,3,4).format(""));
    assertEquals("", sec(90893).format(""));
  }

  @Test
  public void formatExampleTextOnly() {
    assertEquals("hello there", hms(2, 2, 2).format("hello there"));
  }

  @Test
  public void formatFun() {
    assertEquals("22%", hms(2,3,4).format("%h%h%%"));
    assertEquals("2525%", sec(90893).format("%h%h%%"));
  }

  @Test
  public void formatThrowStuff() {
    assertEquals("1 01 1 01 2 02", hms(0, 61, 2).format("%h %H %m %M %s %S"));
    assertEquals("1 01 1 01 2 02 % %", sec(3662).format("%h %H %m %M %s %S %% %%"));
  }

  @Test
  public void percent() {
    assertEquals("%%", sec(3).format("%%%%"));
    assertEquals("%% % $ %", sec(3).format("%%%% %% $ %%"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void malformedFormat() {
    assertEquals("Something", hms(0, 61, 2).format("%"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void malformedFormat2() {
    assertEquals("Something", hms(0, 61, 2).format("Blah Blah Blah %% %s % sjkdjlskjdkljlkjsdlkj"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void malformedFormat3() {
    assertEquals("Something", hms(0, 61, 2).format("Blah Blah Blah %% %s %"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void malformedFormat4() {
    assertEquals("Something", hms(40, 81, 2).format(" %"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void malformedFormat5() {
    assertEquals("Something", hms(40, 81, 2).format(" %T"));
  }

  @Test
  public void hoursTest() {
    assertEquals("1 1 01 01 blah blah",
            hms(1, 2, 0).format("%h %h %H %H blah blah"));
  }

  @Test
  public void hours() {
    assertEquals("100000 100000:", hms(100000, 0, 0).format("%h %H:"));
  }

  @Test
  public void hoursTest2() {
    assertEquals("01",
            hms(1, 2, 0).format("%H"));
  }

  @Test
  public void secondsTest() {
    assertEquals(" 0%000",
            hms(1, 2, 0).format(" %s%%%S%s"));
  }

  @Test
  public void secondsTest2() {
    assertEquals("59",
            hms(1, 2, 59).format("%S"));
  }

  @Test
  public void secondsTest3() {
    assertEquals("01",
            hms(1, 2, 61).format("%S"));
  }

  @Test
  public void durationTest() {
    assertEquals("70987",
           sec(70987).format("%t"));
  }

  @Test
  public void durationTest2() {
    assertEquals(" % 70987",
            sec(70987).format(" %% %t"));
  }

  @Test
  public void allInOne() {
    assertEquals("start 3723 % 3 03 1 01 2 02 02 01 03 3 2 1 % 3723",
            hms(1,2,3).format(
                    "start %t %% %s %S %h %H %m %M %M %H %S %s %m %h %% %t"));
  }

  @Test
  public void density() {
    assertEquals(
            "%%1%1%0%00%00%00",
            hms(0,0,1).format("%%%%%s%%%s%%%m%%%M%%%H%%%h%m"));
  }


  

  /*
    Leave this section alone: It contains two abstract methods to
    create Durations, and concrete implementations of this testing class
    will supply particular implementations of Duration to be used within 
    your tests.
   */
  /**
   * Constructs an instance of the class under test representing the duration
   * given in hours, minutes, and seconds.
   *
   * @param hours the hours in the duration
   * @param minutes the minutes in the duration
   * @param seconds the seconds in the duration
   * @return an instance of the class under test
   */
  protected abstract Duration hms(int hours, int minutes, int seconds);

  /**
   * Constructs an instance of the class under test representing the duration
   * given in seconds.
   *
   * @param inSeconds the total seconds in the duration
   * @return an instance of the class under test
   */
  protected abstract Duration sec(long inSeconds);

  public static final class HmsDurationTest extends AbstractDurationFormatTest {
    @Override
    protected Duration hms(int hours, int minutes, int seconds) {
      return new HmsDuration(hours, minutes, seconds);
    }

    @Override
    protected Duration sec(long inSeconds) {
      return new HmsDuration(inSeconds);
    }
  }

  public static final class CompactDurationTest extends AbstractDurationFormatTest {
    @Override
    protected Duration hms(int hours, int minutes, int seconds) {
      return new CompactDuration(hours, minutes, seconds);
    }

    @Override
    protected Duration sec(long inSeconds) {
      return new CompactDuration(inSeconds);
    }
  }
}
