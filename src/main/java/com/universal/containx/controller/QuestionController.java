package com.universal.containx.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universal.containx.dao.QuestionDao;
import com.universal.containx.dao.RatingRequest;
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
	
	 @RequestMapping("/ratings/{id}/{rating}")

	    public ResponseEntity<Void> postQuestionRating(@PathVariable("id") Long id,@PathVariable("rating") Integer rating ) {

	        
	        System.out.println("q id is "+ id + " Rating is "+ rating);
	        Optional<QuestionModel> question = questionRepository.findById(id);
	        if (question.isPresent()) {

	            QuestionModel questionmodel = question.get();
	            List<Integer> currentQratings = questionmodel.getQrating();
	            if (rating >= 0 ) {
	            	currentQratings.add(rating);
	            	QuestionDao dao= new QuestionDao();
	            	dao.setId(questionmodel.getId());
	            	dao.setQuestion(questionmodel.getQuestion());
	            	dao.setAnswer(questionmodel.getAnswer());
	            	dao.setCompanyname(questionmodel.getCompanyname());
	            	dao.setQrating(currentQratings);
	            	dao.setCustomCompany(questionmodel.getCustomCompany());

	                questionRepository.save(dao);
	                System.out.println("dao : "+dao);

	     

	                return ResponseEntity.ok().build();

	            } else {

	                // Handle an invalid rating index (out of bounds)

	                return ResponseEntity.badRequest().build();

	            }

	        } else {

	            // Handle the case when the question with the provided ID is not found

	            return ResponseEntity.notFound().build();

	        }


	    }

}
