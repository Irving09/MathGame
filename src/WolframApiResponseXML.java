import org.w3c.dom.Document;
import javax.net.ssl.HttpsURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by inno.estrera on 9/3/15.
 */
public class WolframApiResponseXML {
    public static final String DEFAULT_BASE_URL = "http://api.wolframalpha.com/v2/";
    public static final String DEFAULT_APP_ID = "R964JH-TQJX26P7Y5";
    private Document _apiResponse;
    private URL _wolframAlphaURL;

    public WolframApiResponseXML() {
        try {
            _wolframAlphaURL = new URL(DEFAULT_BASE_URL);

        } catch(MalformedURLException e) {
            // http://api.wolframalpha.com/v2/query?appid=xxx&input=%60&format=image,plaintext
            System.err.println("URL is malformed: " + url);
            System.err.println(e.toString());
        }
    }



    public static void main(final String... args) {

    }
}
