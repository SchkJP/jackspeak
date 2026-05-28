package com.example.jackspeak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.jackspeak.dto.NewsResponse;
import com.example.jackspeak.service.NewsService;

@Controller
public class NewsController {
	
	private final NewsService newsSer;
	
	public NewsController(NewsService newsSer) {
		this.newsSer = newsSer;
	}
	
	@GetMapping("/news")
	public String showNews(Model model) {
		
//		// APIからニュース取得
//        List<Article> articles = newsSer.getTopHeadlines();
//        
//        model.addAttribute("articles", articles);
		
		NewsResponse response = newsSer.getTopHeadlines();
		
		model.addAttribute("articles", response.getArticles());
        
		return "news";
	}

}
