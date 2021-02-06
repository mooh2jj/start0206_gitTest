package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Article;
import com.example.demo.util.Util;

@Controller
public class UsrArticleController {
	
	private int articlesLastId;
	private List<Article> articles;
	
	public UsrArticleController() {
		
		// 맴버변수 초기화
		articlesLastId = 0;
		articles = new ArrayList<>();
		
		// 게시물 2개 생성
//		articles.add(new Article(1, "2021-01-25", "title1","body1"));
//		articles.add(new Article(2, "2021-01-25", "title2","body2"));
		articles.add(new Article(++articlesLastId, "2021-01-25", "2021-01-25","title1","body1"));	// 바로 1이됨! 앞에 ++하면!
		articles.add(new Article(++articlesLastId, "2021-01-25", "2021-01-25","title2","body2"));
		
	}
	
	// http://localhost:8024/
	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public Article showDetail(int id, String name) {
//		Article article1 = new Article(1, "2021-01-25", "title1","body1");
//		Article article2 = new Article(2, "2021-01-25", "title2","body2");
		
//		Article article = null;
		
//		if(id == 1) {
//			article = article1;
//		}else if(id ==2) {
//			article = article2;
//		}
		
		// @ResponseBody =>  무조건 JSON 방식으로 간다
//		return article;
		return articles.get(id - 1);
	}
	
	
//	@RequestMapping("/usr/article/list")
//	@ResponseBody
//	public String showList() {
//		return "hi12";
//	}
	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList() {
		return articles;
	}
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Map<String, Object> doAdd(String title, String body) {
		
//		SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date time = new Date();        
//
//		String regDate = Format.format(time);
//		String regDate = getNowDateStr();
		String regDate = Util.getNowDateStr();
		String updateDate = regDate;
		articles.add(new Article(++articlesLastId, regDate, updateDate, title, body));
		
//		Map<String, Object> rs = new HashMap<>();
//		rs.put("resultCode", "S-1");
//		rs.put("msg", "성공하였습니다.");
//		rs.put("id", articlesLastId);
//		
//		return rs;
		
		return Util.mapOf("resultCode", "S-1", "msg", "성공하였습니다.", "id", articlesLastId);
	}
	
//	private String getNowDateStr() { ==> 'Util'로 뺀다!! 다른 project에도 쓰일 수 있게!!
//		
//		SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date time = new Date();        
//
//		return Format.format(time);
//	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public Map<String, Object> doDelete(int id) {
		
//		articles.remove(id-1);
		boolean deleteArticleRs = deleteArticle(id);
		
		Map<String, Object> rs = new HashMap<>();
		
//		if( deleteArticleRs ) {
//			rs.put("resultCode", "S-1");
//			rs.put("msg", "성공하였습니다.");
//			
//		}else {
//			rs.put("resultCode", "F-1");
//			rs.put("msg", "해당 게시물은 존재하지 않습니다.");
//		}
		
		if (deleteArticleRs == false) {
			return Util.mapOf("resultCode", "F-1", "msg", "해당 게시물은 존재하지 않습니다.");
		}
		
//		rs.put("id", id);
//		
//		return rs;
		return Util.mapOf("resultCode", "S-1", "msg", "성공하였습니다.", "id", id);
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
	public Map<String, Object> doModify(int id, String title, String body) {
		
		Article selArticle = null;
		
		for(Article article : articles ) {
			if(article.getId() == id) {
				selArticle = article;
				break;
			}
		}
		
		Map<String, Object> rs = new HashMap<>();
		
		if( selArticle == null) {
//			rs.put("resultCode", "F-1");
//			rs.put("msg", String.format("%d번 게시물은 존재하지 않습니다.", id));
//			return rs;
			return Util.mapOf("resultCode", "F-1", "msg", String.format("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		selArticle.setUpdateDate(Util.getNowDateStr());	// 갱신한날짜는 갱신한 현재날짜!
		selArticle.setTitle(title);
		selArticle.setBody(body);
		
//		rs.put("resultCode", "S-1");
//		rs.put("msg", String.format("%d번 게시물이 수정되었습니다.", id));
//		rs.put("id", id);
//		
//		return rs;
		
		return Util.mapOf("resultCode", "S-1", "msg", String.format("%d번 게시물이 수정되었습니다.", id), "id", id);
	}
}
