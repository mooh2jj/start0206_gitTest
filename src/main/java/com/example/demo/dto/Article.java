package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data				// getter/setter & toString
@AllArgsConstructor	// 생성자
@NoArgsConstructor	// 디폴트 생성자
public class Article {

	private int id;
	private String regDate;
	private String updateDate;
	private String title;
	private String body;
	
	
}
