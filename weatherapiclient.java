import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherApiClient {

    // Replace with your OpenWeatherMap API Key
    private static final String API_KEY = "YOUR_API_KEY_HERE";
    
    public static void main(String[] args) {
        String city = "Mumbai";  // You can also take input from user
        String urlString = String.format(
            "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric",
            city, API_KEY);

        try {
            // Send GET request
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read response
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String input;

            while ((input = in.readLine()) != null) {
                response.append(input);
            }
            in.close();

            // Parse JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONObject main = jsonResponse.getJSONObject("main");
            JSONObject weather = jsonResponse.getJSONArray("weather").getJSONObject(0);

            System.out.println("\n--- Weather Report ---");
            System.out.println("City: " + jsonResponse.getString("name"));
            System.out.println("Temperature: " + main.getDouble("temp") + " °C");
            System.out.println("Humidity: " + main.getInt("humidity") + " %");
            System.out.println("Description: " + weather.getString("description"));
            System.out.println("----------------------");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
