import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherApp {
    
    private static final String API_KEY = "your_api_key";  // replace with your actual API key
    private static final String WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    
    public static void main(String[] args) {
        try {
            // City for which weather is fetched
            String city = "New York"; // you can change this to any city or get user input
            
            // Construct the API request URL
            String urlString = WEATHER_API_URL + city + "&appid=" + API_KEY + "&units=metric";  // For Celsius

            // Send HTTP GET request
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            // Parse the JSON response
            JSONObject weatherJson = new JSONObject(response.toString());
            String cityName = weatherJson.getString("name");
            JSONObject main = weatherJson.getJSONObject("main");
            double temp = main.getDouble("temp");
            int humidity = main.getInt("humidity");
            
            // Get weather description
            String description = weatherJson.getJSONArray("weather").getJSONObject(0).getString("description");
            
            // Print the weather information
            System.out.println("Weather in " + cityName + ":");
            System.out.println("Temperature: " + temp + "°C");
            System.out.println("Humidity: " + humidity + "%");
            System.out.println("Description: " + description);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
