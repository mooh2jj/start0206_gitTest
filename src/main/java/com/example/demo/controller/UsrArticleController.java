package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public List<Article> showList(String serachKeywordType, String serachKeyword) {
		System.out.println("serachKeyword : "+ serachKeyword);
		
		if( serachKeywordType != null ) {
			serachKeywordType = serachKeywordType.trim();
		}
		
		if( serachKeywordType == null || serachKeywordType.length() == 0 ) {
			serachKeywordType = "titleAndBody";
		}
		
		if( serachKeyword != null && serachKeyword.length() == 0) {
			serachKeyword = null;
		}
		if( serachKeyword != null ) {
			serachKeyword = serachKeyword.trim();
		}
		return articleService.getArticles(serachKeywordType, serachKeyword);
	}
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData doAdd(@RequestParam Map<String, Object> param) {
		
		if (param.get("title") == null) {
			return new ResultData("F-1", "title을 입력해주세요.");
		}
		
		if (param.get("body") == null) {
			return new ResultData("F-1", "body를 입력해주세요.");
		}
		
		return articleService.addArticle(param);
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
		
		return articleService.modifyArticle(id, title, body);
	}
}
