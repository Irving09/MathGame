import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by inno.estrera on 9/3/15.
 */
public class WolframApiResponseXML {
    private WolframURLStringEncoder _wolframEncoder;

    public WolframApiResponseXML() {
        _wolframEncoder = new WolframURLStringEncoder();
    }

    private URL getUrlObject(final String url) {
        try {
            URL obj = new URL(url);
            return obj;
        } catch(final MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private HttpURLConnection getConnection(final URL url) {
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            return con;
        } catch(final IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private int responseCode(final String url) {
        try {
            final URL urlObject = getUrlObject(url);
            HttpURLConnection con = (HttpURLConnection) urlObject.openConnection();
            return con.getResponseCode();
        } catch(final IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private String responseMessage(final String url) {
        try {
            final URL urlObject = getUrlObject(url);
            HttpURLConnection con = (HttpURLConnection) urlObject.openConnection();
            InputStreamReader isr = new InputStreamReader(con.getInputStream());
            BufferedReader bf = new BufferedReader(isr);
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = bf.readLine()) != null) {
                response.append(inputLine);
            }
            bf.close();
            return response.toString();
        } catch(final IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Object responseContent(final String url) {
        try {
            final URL urlObject = getUrlObject(url);
            HttpURLConnection con = (HttpURLConnection) urlObject.openConnection();

            return con.getContent();
        } catch(final IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void retrieveResults(final String input) {
        String urlAsString = _wolframEncoder.updateInputInUrl(input);
        URL url = getUrlObject(urlAsString);
        HttpURLConnection connection = getConnection(url);
    }

    public static void main(final String... args) {
        WolframApiResponseXML test = new WolframApiResponseXML();
//        test.retrieveResults("Inno pot pot");
        final URL url = test.getUrlObject("http://localhost:3000");
        final int result = test.responseCode("http://localhost:3000");
        final String response = test.responseMessage("http://localhost:3000");
        final Object content = test.responseContent("http://localhost:3000");

        System.out.println("result: " + result);
        System.out.println("message: " + response);
        System.out.println("content: " + content);
    }
}
