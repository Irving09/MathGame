import java.net.HttpURLConnection;

/**
 * Created by inno.estrera on 9/3/15.
 */
public class WolframApiResponseXML {
    private String _wolframInput;

    public WolframApiResponseXML() {
        _wolframInput = "";
    }

    public WolframApiResponseXML(final String input) {
        // TODO
        _wolframInput = input;
    }

    public String getInput() {
        return _wolframInput;
    }

    public static void main(final String... args) {

    }
}
