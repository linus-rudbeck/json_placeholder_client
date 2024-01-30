package se.distansakademin.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import se.distansakademin.models.Post;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ApiService {
    private static final String API_URL = "https://jsonplaceholder.typicode.com/posts";

    public static List<Post> getPosts() throws IOException, URISyntaxException {
        var postsJson = getPostsJson();

        var gson = new Gson();

        Type postListType = new TypeToken<List<Post>>(){}.getType();
        return gson.fromJson(postsJson, postListType);
    }

    private static String getPostsJson() throws URISyntaxException, IOException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_URL))
                .version(HttpClient.Version.HTTP_2)
                .GET()
                .build();

        var clientBuilder =  HttpClient.newBuilder();

        try(var client = clientBuilder.build()){
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
