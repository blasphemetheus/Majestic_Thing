package cs3500.hw01.publication;

/**
 * Represents bibliographic information for a Webpage.
 */
public class Webpage implements Publication {
  private final String title;
  private final String url;
  private final String retrieved;

  /**
   * Constructs a Webpage.
   *
   * @param title the title of the Webpage
   * @param url the url of the Webpage
   * @param retrieved the date this Webpage was retrieved
   */
  public Webpage(String title, String url, String retrieved) {
    this.title = title;
    this.url = url;
    this.retrieved = retrieved;
  }

  @Override
  public String citeApa() {
    return "" + title + ". Retrieved " + retrieved + ", from " + url + ".";
  }

  @Override
  public String citeMla() {
    return "\"" + title + ".\" Web. " + retrieved + " <" + url + ">.";
  }
}
