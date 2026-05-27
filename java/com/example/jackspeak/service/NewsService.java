package com.example.jackspeak.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.jackspeak.dto.Article;

@Service
public class NewsService {

    public List<Article> getTopHeadlines() {

        RestTemplate restTemplate = new RestTemplate();

        String url =
            "https://newsapi.org/v2/top-headlines?country=us&apiKey=API_KEY";

        String response =
            restTemplate.getForObject(url, String.class);

        System.out.println(response);

        return new ArrayList<>();
    }
}