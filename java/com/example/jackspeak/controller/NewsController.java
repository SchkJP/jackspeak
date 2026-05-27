package com.example.jackspeak.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.jackspeak.dto.Article;
import com.example.jackspeak.service.NewsService;

@Controller
public class NewsController {
	
	private final NewsService newsSer;
	
	public NewsController(NewsService newsSer) {
		this.newsSer = newsSer;
	}
	
	@GetMapping("/news")
	public String news(Model model) {
		
		// APIからニュース取得
        List<Article> articles = newsSer.getTopHeadlines();
        
        model.addAttribute("articles", articles);
        
		return "news";
	}

}
