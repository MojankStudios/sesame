package eu.fatalerrorcoded.sesame.util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
public class AuthServer {
    public static String targetUsername = null;
    public static String targetAuthServer = null;
    public static String targetClientToken = null;
    public static String targetAccessToken = null;

    public static void auth() {
        // Authenticate with Yggoxide
        try {
            System.out.println("Sending auth request to yggoxide server...");

            JsonObject agent = new JsonObject();
            agent.add("name", new JsonPrimitive("sesame"));
            agent.add("version", new JsonPrimitive(1));

            JsonObject payload = new JsonObject();
            payload.add("agent", agent);
            payload.add("username", new JsonPrimitive(AuthServer.targetUsername));

            // Currently we can use any password.
            payload.add("password", new JsonPrimitive("password"));
            
            var request = HttpRequest.newBuilder()
                .uri(new URI(AuthServer.targetAuthServer + "/authenticate"))
                .headers("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload.toString()))
                .build();

            var client = HttpClient.newHttpClient();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            var data = gson.fromJson(response.body(), JsonObject.class);

            targetClientToken = data.get("clientToken").getAsString();
            targetAccessToken = data.get("accessToken").getAsString();
            System.out.println("Successfully authenticated!");
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
