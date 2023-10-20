package com.universal.containx.dao;

import java.util.List;

import lombok.Data;

@Data
public class QuestionDao {
	private int id;
	private String question;
	private String answer;
	private String companyname;
	private int [] rating;
	private String customCompany;
	private List<Integer> qrating;

}
