import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by inno.estrera on 9/3/15.
 */
public class WolframURLStringEncoder {
    private static final Map<String, String> _operators = new HashMap<String, String>();
    public static final String DEFAULT_BASE_URL = "http://api.wolframalpha.com/v2/query?";
    public static final String DEFAULT_APP_ID = "R964JH-TQJX26P7Y5";
    public static final String DEFAULT_URL_OUTPUT_FORMAT = "&format=image,plaintext";

    private String _wolframUrl;
    private String _userInput;
    private String _wolframAlphaAppID;

    public WolframURLStringEncoder() {
        this(DEFAULT_APP_ID);
    }

    public WolframURLStringEncoder(final String appId) {
        _userInput = "";
        _wolframAlphaAppID = appId;
        _wolframUrl = intializeUrlWithCurrentInput();
        initializeWolframAlphaOperatorEncodings();
    }

    public String formattedUrl() {
        return _wolframUrl;
    }

    private final String intializeUrlWithCurrentInput() {
        final StringBuilder sb = new StringBuilder();
        sb.append(DEFAULT_BASE_URL);
        sb.append("appid=");
        sb.append(_wolframAlphaAppID);
        sb.append("&");
        sb.append("input=");
        sb.append(_userInput);
        sb.append(DEFAULT_URL_OUTPUT_FORMAT);
        return sb.toString();
    }

    public final void initializeWolframAlphaOperatorEncodings() {
        _operators.put("+", "%2B");
        _operators.put("/", "%2F");
        _operators.put("=", "%3D");
    }

    private String convertInputToCorrectFormat(final String userInput) {
        final StringBuilder sb = new StringBuilder();

        String[] chars = userInput.split("");
        for (String ch : chars) {
            if (_operators.containsKey(ch)) {
                sb.append(_operators.get(ch));
            } else {
                sb.append(ch);
            }
        }

        return sb.toString();
    }

    public String updateInputInUrl(final String input) {
        _userInput = convertInputToCorrectFormat(input);
        _wolframUrl = intializeUrlWithCurrentInput();
        return _wolframUrl;
    }

    public static void main(final String... args) {
        WolframURLStringEncoder test = new WolframURLStringEncoder();
        String result, testInput;

        testInput = "x=3";
        System.out.println("testInput:" + testInput);
        System.out.println("before   : " + test.formattedUrl());
        test.updateInputInUrl(testInput);
        System.out.println("after    : " + test.formattedUrl());
        System.out.println("===============");
        System.out.println("===============");
        System.out.println("===============");
        testInput = "x/3";
        System.out.println("testInput:" + testInput);
        System.out.println("before   : " + test.formattedUrl());
        test.updateInputInUrl(testInput);
        System.out.println("after    : " + test.formattedUrl());
        System.out.println("===============");
        System.out.println("===============");
        System.out.println("===============");
        testInput = "x*3";
        System.out.println("testInput:" + testInput);
        System.out.println("before   : " + test.formattedUrl());
        test.updateInputInUrl(testInput);
        System.out.println("after    : " + test.formattedUrl());
        System.out.println("===============");
        System.out.println("===============");
        System.out.println("===============");
        testInput = "x-3";
        System.out.println("testInput:" + testInput);
        System.out.println("before   : " + test.formattedUrl());
        test.updateInputInUrl(testInput);
        System.out.println("after    : " + test.formattedUrl());
        System.out.println("===============");
        System.out.println("===============");
        System.out.println("===============");
        testInput = "x+3";
        System.out.println("testInput:" + testInput);
        System.out.println("before   : " + test.formattedUrl());
        test.updateInputInUrl(testInput);
        System.out.println("after    : " + test.formattedUrl());
    }
}
