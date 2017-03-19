package cs3500.hw01.publication;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests the Webpage class.
 */
public class WebpageTest {

  @Test
  public void testEmpty() {
    Publication emptyPage = new Webpage("", "", "");
    assertEquals(". Retrieved , from .", emptyPage.citeApa());
    assertNotEquals(emptyPage.citeApa(),emptyPage.citeMla());
  }

  @Test
  public void testEmptyMLA() {
    Publication emptyPage = new Webpage("", "", "");
    assertEquals("\".\" Web.  <>.", emptyPage.citeMla());
  }

  @Test
  public void testNonsense() {
    Publication nonsensePage = new Webpage("Widdershins", "Snuffluff",
            "Nine Days to Mondays...");
    assertEquals("Widdershins. Retrieved Nine Days to Mondays..., from Snuffluff.",
            nonsensePage.citeApa());
  }

  @Test
  public void testNonsenseMLA() {
    Publication nonsensePage = new Webpage("Widdershins", "Snuffluff",
            "Nine Days to Mondays...");
    assertEquals("\"Widdershins.\" Web. Nine Days to Mondays... <Snuffluff>.",
            nonsensePage.citeMla());
  }

  @Test
  public void testRealExample() {
    Publication realPage = new Webpage("1", "2", "3");
    assertEquals("1. Retrieved 3, from 2.", realPage.citeApa());
    assertEquals("\"1.\" Web. 3 <2>.", realPage.citeMla());
  }

  @Test
  public void testRealExampleMLA() {
    Publication realPage = new Webpage("1", "2", "3");
    assertEquals("\"1.\" Web. 3 <2>.", realPage.citeMla());
  }

  @Test
  public void testSilly() {
    Publication sillyPage = new Webpage("FIVESTAR", "2", "Silly");
    assertEquals("FIVESTAR. Retrieved Silly, from 2.", sillyPage.citeApa());
  }

  @Test
  public void testSillyMLA() {
    Publication sillyPage = new Webpage("FIVESTAR", "2", "Silly");
    assertEquals("\"FIVESTAR.\" Web. Silly <2>.", sillyPage.citeMla());
  }
}