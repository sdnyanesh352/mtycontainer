package com.universal.containx.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.universal.containx.model.QuestionModel;
import com.universal.containx.repository.QuestionRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class QuestionController {
	
	@Autowired
	QuestionRepository questionRepository;
	
	@PostMapping("/addquestion")
	public void addQuestions(@RequestBody QuestionModel question) {
		
		questionRepository.save(question);
		
	}
	
	@GetMapping("/getquestions")
	public  List<QuestionModel> getQuestions() {
	return 	questionRepository.findAll();
		
	}
	
	@GetMapping("qbank/quedetails/{id}")
	public Optional<QuestionModel> getQuestionById(@PathVariable ("id") Long id) {
		return questionRepository.findById(id);
	}

}
