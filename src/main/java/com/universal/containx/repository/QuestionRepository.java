package com.universal.containx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.universal.containx.dao.QuestionDao;
import com.universal.containx.model.QuestionModel;
@Repository
public interface QuestionRepository  extends    JpaRepository<QuestionModel, Long>  {

	void save(QuestionDao dao);

}
