import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

class Main
{
  public static void main(String[] args)
  throws IOException
  {
    // bio	

    String page = GetFileContent("../data/profile.txt");

    int bioIndex = page.indexOf("f4\"");
    if (page.charAt(bioIndex + 5) != 'h')
    {
      int endBio = page.indexOf("<", bioIndex + 11);
      System.out.println(" - " + page.substring(bioIndex + 11, endBio));
    }

    // location
    
    ExtractData("../data/profile.txt", "Home location:", "\"", 15, false);

    // followers

    System.out.print("\nfollowers:\n");

    String tag = "k\" data-hovercard-type=\"user\" data-hovercard-url=\"/users/";
    ExtractData("../data/followers.txt", tag, "/", 57, true);

    // following

    System.out.print("\nfollowing:\n");

    tag = "k\" data-hovercard-type=\"user\" data-hovercard-url=\"/users/";
    ExtractData("../data/following.txt", tag, "/", 57, true);

    // repos

    System.out.print("\nrepos:\n");

    tag = "<a href=\"/" + args[0] + "/";
    ExtractData("../data/repos.txt", tag, "\"", args[0].length() + 11, true);

    // stars

    System.out.print("\nstars:\n");

    tag = "d-inline-block mb-1";
    ExtractData("../data/stars.txt", tag, "\"", 45, true);
  }

  static void ExtractData(String _page, String tag, String endTag, int inc, boolean msg)
  throws IOException
  {
    String page = GetFileContent(_page);

	if (page.indexOf(tag) == -1)
		System.out.print(msg ? "  none\n" : "");

    for (
      int tagIndex = page.indexOf(tag);
      tagIndex != -1;
      tagIndex = page.indexOf(tag, tagIndex + 1)
    ) {
      int endIndex = page.indexOf(endTag, tagIndex + inc);
      System.out.println(" - " + page.substring(tagIndex + inc, endIndex));
    }
  }

  static String GetFileContent(String fileName)
  throws IOException
  {
    BufferedReader file = new BufferedReader(new FileReader(fileName));
    String page = "", fileLine = file.readLine();
    while (fileLine != null)
    {
      page += fileLine;
      fileLine = file.readLine();
    }

    return page;
  }
}
