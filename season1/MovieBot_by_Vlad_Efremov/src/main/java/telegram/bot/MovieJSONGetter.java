package telegram.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class MovieJSONGetter implements JSONGetter {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "api_key=4ca71b493c8a5bae631c1cbad40f0809";
    private HttpURLConnection connection;


    public MovieJSONGetter() {

    }

    @Override
    public String getJSONString(String query, String queryOptions) {

        String string = generateRequest(query, queryOptions);
        try {
            string = getJSON(string);
            return string;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;


    }

    @Override
    public String getJSONString(String fullRequest)  {
        String string= null;
        try {
            string = getJSON(fullRequest);
            return string;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;


    }

    private String getJSON(String urlRequest) throws Exception {
        connection = (HttpURLConnection) new URL(urlRequest).openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        StringBuilder stringBuilder;

        if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            stringBuilder = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                stringBuilder.append(line);
            }

            connection.disconnect();

            return stringBuilder.toString();
        } else return null;
    }

    private String generateRequest(String query, String queryOptions) {
        String request = BASE_URL + query + "?" + API_KEY + "&" + queryOptions;
        return request;
    }




}
