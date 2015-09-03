import javax.naming.ldap.UnsolicitedNotification;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.MalformedInputException;

/**
 * Created by inno.estrera on 9/3/15.
 */
public class WolframURLStringEncoder {
    private URL _urlToConvert;
    public WolframURLStringEncoder(final String inputUrl) {
        try {
            _urlToConvert = new URL(inputUrl);
        } catch(final MalformedURLException e) {
            System.out.println("URL is malformed: " + inputUrl);
            e.printStackTrace();
        }
    }

    public String convertUrl() {
        StringBuilder result = new StringBuilder();
        String urlToParse = _urlToConvert.toString();
        String[] urlChars = urlToParse.split("");


        for (String ch : urlChars) {
            boolean characterIsALetter = isLetter(ch);
            if (characterIsALetter) {
                result.append(ch);
            } else {
                result.append(convertToUtf8(ch));
            }
        }
        return result.toString();
    }

    private boolean isLetter(final String s) {
        return isSingleLetter(s) && Character.isLetter(s.charAt(0));
    }

    private boolean isSingleLetter(final String s) {
        return s.length() <= 1;
    }

    public String convertToUtf8(final String s) {

        String result = null;
        if (isSingleLetter(s)) {
            try {
                result = new String(s.getBytes("UTF-8"), "ISO-8859-1");
            } catch(UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    public static void main(final String... args) {
        WolframURLStringEncoder test = new WolframURLStringEncoder("https://google.com");
        String result = test.convertToUtf8("+");
        System.out.println("result: ");
        System.out.println();
        System.out.println(result);
    }
}
