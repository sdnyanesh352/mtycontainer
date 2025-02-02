package com.universal.containx.service;


import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.universal.containx.dao.LoginRequest;
import com.universal.containx.dao.UserDto;
import com.universal.containx.model.CoinTransaction;
import com.universal.containx.model.LoginHistory;
import com.universal.containx.model.Referral;
import com.universal.containx.model.TransactionType;
import com.universal.containx.model.User;
import com.universal.containx.repository.CoinTransactionRepository;
import com.universal.containx.repository.ReferralRepository;
import com.universal.containx.repository.UserRepository;
@Component
public class UserServiceImpl implements UserService{
	
private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private static final Integer COINS_PER_REFERRAL = 10;
	@Autowired
	UserRepository userRepository;
	 @Autowired
	    private CoinTransactionRepository coinTransactionRepository;

	    @Autowired
	    private ReferralRepository referralRepository;
	/*
	 * @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
	 */

	@Override
	public UserDto signup(UserDto userDto) throws Exception {
		User user = userRepository.findByEmail(userDto.getEmail());
		if(user==null) {
			user = new User()
                    .setEmail(userDto.getEmail())
                    .setPassword(userDto.getPassword())
                    .setFirstName(userDto.getFirstName())
                    .setLastName(userDto.getLastName())
                    .setCoins(userDto.getCoins())
                    .setUsername(userDto.getUsername())
                    .setMobileNumber(userDto.getMobileNumber());
            userRepository.save(user);
            if (userDto.getCoins() > 0) {
                CoinTransaction coinTransaction = new CoinTransaction()
                        .setUser(user)
                        .setAmount(userDto.getCoins())
                        .setTransactionType(TransactionType.REFERRAL_BONUS)
                        .setCreatedDate(Date.from(Instant.now()));
                coinTransactionRepository.save(coinTransaction);
                logger.info("Saved CoinTransaction successfully");

                user.setCoins(user.getCoins() + userDto.getCoins());
                userRepository.save(user);
            }
			
		}
		else {
			throw new Exception("USER is already registered");
		}
		// TODO Auto-generated method stub
		return null;
	}
	
    public void setReferralEntity(UserDto userDto) throws Exception {
        User referrer = userRepository.findByUsername(userDto.getRef());
        if (referrer != null) {
            Referral referral = new Referral();
            referral .setReferralCode(userDto.getRef().toLowerCase());
            referral.setEmail(userDto.getEmail());
            referral.setStatus("pending");
            referral.setReferrer(referrer);
            referral.setReferredAt(LocalDateTime.now());
            referralRepository.save(referral);

            referrer.getReferrals().add(referral);
            referrer.setCoins(referrer.getCoins() + COINS_PER_REFERRAL);
            userRepository.save(referrer);

            userDto.setCoins(COINS_PER_REFERRAL);

            CoinTransaction coinTransaction = new CoinTransaction()
                    .setUser(referrer)
                    .setAmount(COINS_PER_REFERRAL)
                    .setTransactionType(TransactionType.REFERRAL_BONUS)
                    .setCreatedDate(Date.from(Instant.now()));
            coinTransactionRepository.save(coinTransaction);

            referrer.setCoins(referrer.getCoins() + COINS_PER_REFERRAL);
            userRepository.save(referrer);
        }
    }


	@Override
	public UserDto findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto updateProfile(UserDto userDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto changePassword(UserDto userDto, String newPassword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User FindByusername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public void SetLoginHistory() {
		// TODO Auto-generated method stub
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 System.out.println("calling from Authentication spring "+ authentication.toString());
		 String currentUserEmail = authentication.getName();
		 User currentUser = userRepository.findByEmail(currentUserEmail);
		    // create new login history
		 if (currentUser != null) {
			 LoginHistory loginHistory = new LoginHistory();
			    loginHistory.setLoginTime(LocalDateTime.now().minusDays(0));
			    loginHistory.setUserHistory(currentUser);
			    //currentUser.setLastLogin(Instant.now());
			    currentUser.addLoginHistory(loginHistory);
			    userRepository.save(currentUser);
		 }
		    
		   

		
	
		
	}

	@Override
	public String login(LoginRequest request) {
	    User currentUser = userRepository.findByEmail(request.getEmail());
	    if(request.getLoginType().equals("googleAuth")) {
	    	LoginHistory loginHistory = new LoginHistory();
	        loginHistory.setLoginTime(LocalDateTime.now().minusDays(0));
	        loginHistory.setUserHistory(currentUser);
	        currentUser.addLoginHistory(loginHistory);
	        userRepository.save(currentUser);
	 
	        return "Login Successful!";
	    	
	    }else {
	        if (currentUser.getPassword().equals(request.getPassword())) {
		        LoginHistory loginHistory = new LoginHistory();
		        loginHistory.setLoginTime(LocalDateTime.now().minusDays(0));
		        loginHistory.setUserHistory(currentUser);
		        currentUser.addLoginHistory(loginHistory);
		        userRepository.save(currentUser);
		 
		        return "Login Successful!";
		    }
	    	
	    }
	 
	    System.out.println("Stored user is " + currentUser.toString());
	 
	    if (currentUser.getPassword() == null) {
	        // Handle the case when the user's password is null
	        return "Password not set for the user";
	    }
	 
	    System.out.println("password is " + currentUser.getPassword());
	 
	
	 
	    // Handle the case when login fails
	    return "Login Failed";
	}
}
