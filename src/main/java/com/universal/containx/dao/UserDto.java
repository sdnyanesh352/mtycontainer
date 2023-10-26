package com.universal.containx.dao;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
	    private String email;
	    private String password;
	    private String firstName;
	    private String lastName;
	    private String mobileNumber;
	    private String ref;
	    private String refto;
	    private String username;
	    private Integer coins=0;
	    private boolean isAdmin;

	    public String getFullName() {
	        return firstName != null ? firstName.concat(" ").concat(lastName) : "";
	    }

}
