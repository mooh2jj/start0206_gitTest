package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Article;
import com.example.demo.dto.ResultData;
import com.example.demo.service.ArticleService;

@Controller
public class UsrArticleController {
	
	@Autowired
	private ArticleService articleService;
		
	// http://localhost:8024/
	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public Article showDetail(int id, String name) {

		Article article = articleService.getArticle(id);
		
		return article;
	}
	
	
	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList(String serachKeyword) {
		System.out.println("serachKeyword : "+ serachKeyword);
		
		if( serachKeyword != null && serachKeyword.length() == 0) {
			serachKeyword = null;
		}
		if( serachKeyword != null ) {
			serachKeyword = serachKeyword.trim();
		}
		return articleService.getArticles(serachKeyword);
	}
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData doAdd(String title, String body) {
		
		if (title == null) {
			return new ResultData("F-1", "title을 입력해주세요.");
		}
		
		if (body == null) {
			return new ResultData("F-1", "body를 입력해주세요.");
		}
		
		return articleService.add(title, body);
	}
	
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(Integer id) {
		
		if(id == null) {
			
			return new ResultData("F-1", "id를 입력해주세요.");
		}
		Article article = articleService.getArticle(id);
		
		if (article == null) {
			return new ResultData("F-1", "해당 게시물은 존재하지 않습니다.");
		}
		
		return articleService.deleteArticle(id);
	}


	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(Integer id, String title, String body) {
		// int(기본타입) : 안넣으면 오류남! -> Integer(레퍼런스 타입)으로 변화!
		if (id == null) {
			return new ResultData("F-1", "id를 입력해주세요.");
		}
		
		if (title == null) {
			return new ResultData("F-1", "title을 입력해주세요.");
		}
		
		if (body == null) {
			return new ResultData("F-1", "body를 입력해주세요.");
		}
		
		Article article = articleService.getArticle(id);
		
		return articleService.modify(id, title, body);
	}
}
