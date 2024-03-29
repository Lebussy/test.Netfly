import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&date=1999-08-28")).GET().build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // params the json body and trims the braces
        String json = response.body();
        json = json.substring(1, json.length() - 2);

        // splits the params into a String[] and puts them in Arraylist
        String[] jsonParams = json.split(",");
        List<String> jsonList = new ArrayList<>(List.of(jsonParams));
        // removes trailing and leading ""s
        jsonList.replaceAll(s -> s.substring(1, s.length() - 2));

        // maps the values in json to key value pairs
        Map<String, String> paramsMap = new HashMap<>();
        jsonList.forEach(s -> {
            String[] temp = s.split("\":\"");
            if (temp.length > 1){
                paramsMap.put(temp[0], temp[1]);
            }
        });






    }
}
