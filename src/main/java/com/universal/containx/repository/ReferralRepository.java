package com.universal.containx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.universal.containx.model.Referral;

public interface ReferralRepository extends JpaRepository<Referral, Long> {
    List<Referral> findByReferrerId(long referrerId);


}