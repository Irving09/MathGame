import jdk.nashorn.internal.objects.NativeJava;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
//import javax.xml.parsers.*;

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

    public String responseAsString(final String url) {
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
        }
        return null;
    }

    public Document responseAsDocument(final String url) {
        final String result = responseAsString(url);
        return convertStringXMLToDocument(result);
    }

    public Document convertStringXMLToDocument(final String xml) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource();
            inputSource.setCharacterStream(new StringReader(xml));
            return builder.parse(inputSource);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
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

    public String retrieveResultsAsString(final String input) {
        String urlAsString = _wolframEncoder.updateInputInUrl(input);
        return responseAsString(urlAsString);
    }

    public Document retrieveResultsAsDocument(final String input) {
        String urlAsString = _wolframEncoder.updateInputInUrl(input);
        return responseAsDocument(urlAsString);
    }

    public List<String> parseSolutionFromXml(final Document xmlDocument) {
        final List<String> results = new ArrayList<String>();

        NodeList list = xmlDocument.getElementsByTagName("pod");
        String tobeAdded;
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element el = (Element) node;
                tobeAdded = el.getTextContent().trim();
                if (tobeAdded.length() >= 1) {
                    results.add(tobeAdded);
                }
            }
        }
        return results;
    }

    public List<String> getSolutionFromWolfram(final String input) {
        final Document xmlDocument = retrieveResultsAsDocument(input);
        return parseSolutionFromXml(xmlDocument);
    }

    public void printDocument(final Document doc) {
        TransformerFactory tf = TransformerFactory.newInstance();
        try {
            System.out.println("======================");
            System.out.println("======================");
            System.out.println("======================");
            Transformer transformer = tf.newTransformer();
            transformer.transform(new DOMSource(doc), new StreamResult(new OutputStreamWriter(System.out, "UTF-8")));
            System.out.println("======================");
            System.out.println("======================");
            System.out.println("======================");
        } catch(final Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(final String... args) {
        WolframApiResponseXML test = new WolframApiResponseXML();
        final String input = "x^2+2x+1=234";
        final List<String> results = test.getSolutionFromWolfram(input);
        System.out.println(results);
    }
}
