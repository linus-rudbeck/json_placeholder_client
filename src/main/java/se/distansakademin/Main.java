package se.distansakademin;

import se.distansakademin.services.ApiService;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        var posts = ApiService.getPosts();

        for (var post : posts){
            System.out.println(post);
        }
    }
}