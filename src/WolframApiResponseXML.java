import org.w3c.dom.Document;
import javax.net.ssl.HttpsURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by inno.estrera on 9/3/15.
 */
public class WolframApiResponseXML {
    public static final String DEFAULT_BASE_URL = "http://api.wolframalpha.com/v2/query?";
    public static final String DEFAULT_APP_ID = "R964JH-TQJX26P7Y5";
    private Document _apiResponse;
    private String _wolframAlphaAppID;
    private URL _wolframAlphaURL;
    private String _userInput;

    public WolframApiResponseXML() {
        this(DEFAULT_APP_ID);
    }

    public WolframApiResponseXML(final String wolframAlphaAppID) {
        _wolframAlphaAppID = wolframAlphaAppID;
        _userInput = "";
        try {
            _wolframAlphaURL = new URL(DEFAULT_BASE_URL);

        } catch(MalformedURLException e) {
            // http://api.wolframalpha.com/v2/query?appid=xxx&input=%60&format=image,plaintext
            System.err.println(e.toString());
        }
    }

    public void addUserInputToUrl(final String input) {
        final StringBuilder sb = new StringBuilder();
        _userInput = input;
        sb.append(DEFAULT_BASE_URL);
        sb.append("appid=");
        sb.append(_wolframAlphaAppID);
        sb.append("&");
        sb.append("input=");

    }

    public static void main(final String... args) {

    }
}
