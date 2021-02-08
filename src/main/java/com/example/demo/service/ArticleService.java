package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dto.Article;
import com.example.demo.dto.ResultData;
import com.example.demo.util.Util;

@Service
public class ArticleService {
	
	@Autowired
	private ArticleDao articleDao;
	

	public Article getArticle(int id) {
		return articleDao.getArticle(id);
	}
	

	public ResultData addArticle(Map<String, Object> param) {
		
		articleDao.addArticle(param);

//		int id = 1;	// 임시
		int id = Util.getAsInt(param.get("id"), 0);
		
		return new ResultData("S-1", "성공하였습니다.", "id", id);
	}

	public ResultData deleteArticle(int id) {
		
		articleDao.deleteArticle(id);

		return new ResultData("S-1", "삭제하였습니다.", "id", id);
	}

	public ResultData modifyArticle(int id, String title, String body) {
		articleDao.modifyArticle(id, title, body);

		return new ResultData("S-1", "게시물을 수정하였습니다.", "id", id);
	}
	
	public List<Article> getArticles(String searchKeywordType, String searchKeyword) {
		return articleDao.getArticles(searchKeywordType, searchKeyword);
	}
}
