package com.universal.containx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.universal.containx.model.Company;
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{

}
