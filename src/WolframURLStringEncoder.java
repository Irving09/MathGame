import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by inno.estrera on 9/3/15.
 */
public class WolframURLStringEncoder {
    private URL _urlToConvert;
    private static final Map<String, String> _operators = new HashMap<String, String>();
    private String _convertedUrl;
    public static final String DEFAULT_BASE_URL = "http://api.wolframalpha.com/v2/query?";
    public static final String DEFAULT_APP_ID = "R964JH-TQJX26P7Y5";

    public WolframURLStringEncoder(final String inputUrl) {
        initializeUrlObject(inputUrl);
        initializeWolframAlphaOperatorEncodings();
    }

    private final void initializeUrlObject(final String inputUrl) {
        try {
            _urlToConvert = new URL(inputUrl);
        } catch(final MalformedURLException e) {
            System.out.println("URL is malformed: " + inputUrl);
            e.printStackTrace();
        }
    }

    public final void initializeWolframAlphaOperatorEncodings() {
        _operators.put("+", "%2B");
        _operators.put("/", "%2F");
        _operators.put("=", "%3D");
    }

    public String convertUrl() {
        final StringBuilder result = new StringBuilder();
        final String urlToParse = _urlToConvert.toString();
        final String[] urlChars = urlToParse.split("");

        for (final String ch : urlChars) {
            if (_operators.containsKey(ch)) {
                result.append(_operators.get(ch));
            } else {
                result.append(ch);
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

    public String convertOperator(final String operator) {
        return _operators.get(operator);
    }

    public static void main(final String... args) {
        final String url = "http://api.wolframalpha.com/v2/query?appid=xxx&input=(x%2B3)%2F5%3D25&format=image,plaintext";
        WolframURLStringEncoder test = new WolframURLStringEncoder(url);
        final String result = test.convertUrl();
        System.out.println("result:");
        System.out.println(result);
    }
}
