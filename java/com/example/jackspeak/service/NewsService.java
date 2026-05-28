package com.example.jackspeak.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.jackspeak.dto.NewsResponse;

@Service
public class NewsService {

    @Value("${api_key}")
	private String API_KEY;

    public NewsResponse getTopHeadlines() {

        String url =
                "https://newsapi.org/v2/top-headlines?country=us&category=technology&apiKey="
                        + API_KEY;

        RestTemplate restTemplate = new RestTemplate();

        NewsResponse response =
                restTemplate.getForObject(url, NewsResponse.class);

        return response;
    }
}