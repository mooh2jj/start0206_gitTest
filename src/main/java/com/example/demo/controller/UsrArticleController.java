package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Article;
import com.example.demo.dto.ResultData;
import com.example.demo.service.ArticleService;
import com.example.demo.util.Util;

@Controller
public class UsrArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	private int articlesLastId;
	private List<Article> articles;
	
	
	// http://localhost:8024/
	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public Article showDetail(int id, String name) {

		Article article = articleService.getArticle(id);
		
		return article;
	}
	
	
	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList() {
		return articleService.getArticles();
	}
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData doAdd(String title, String body) {
		
		
		if (title != null) {
			return new ResultData("F-1", "title을 입력해주세요.");
		}
		
		if (body != null) {
			return new ResultData("F-1", "body를 입력해주세요.");
		}
		
		return articleService.add(title, body);
	}
	
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(int id) {
		
		boolean deleteArticleRs = deleteArticle(id);
		Article article = articleService.getArticle(id);
		
		if (article == null) {
			return new ResultData("F-1", "해당 게시물은 존재하지 않습니다.");
		}
		
//		rs.put("id", id);
//		
//		return rs;
		return articleService.deleteArticle(id);
	}

	private boolean deleteArticle(int id) {

		for( Article article : articles ) {
			
			if( article.getId() == id) {
				articles.remove(article);
				
				return true; // break
			}
		}
		return false; // 고유값은 false
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(int id, String title, String body) {
		
		Article article = articleService.getArticle(id);
		
		if (article == null) {
			return new ResultData("F-1", "해당 게시물은 존재하지 않습니다.");
		}
		
		return articleService.modify(id, title, body);
	}
}
