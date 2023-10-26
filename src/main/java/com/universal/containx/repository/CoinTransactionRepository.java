package com.universal.containx.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.universal.containx.model.CoinTransaction;
import com.universal.containx.model.User;

public interface CoinTransactionRepository extends CrudRepository<CoinTransaction, Long> {

	List<CoinTransaction> findByUser(User user);

}
