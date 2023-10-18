package com.universal.containx.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="questions")
public class QuestionModel {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String question;
	private String answer;
	private String companyname;
	private int [] rating;
	private String customCompany;
	

}
