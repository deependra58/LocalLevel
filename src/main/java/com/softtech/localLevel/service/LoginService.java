package com.softtech.localLevel.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import com.softech.localLevel.exception.LoginFailException;
import com.softech.localLevel.exception.NotFoundException;
import com.softtech.localLevel.dto.LoginRequestDto;
import com.softtech.localLevel.dto.LoginResponseDto;
import com.softtech.localLevel.model.Login;
import com.softtech.localLevel.model.LoginToken;
import com.softtech.localLevel.repository.LoginRepository;
import com.softtech.localLevel.repository.LoginTokenRepository;
import com.softtech.localLevel.util.BCrypt;
import com.softtech.localLevel.util.Constant;
import com.softtech.localLevel.util.DateUtils;
import com.softtech.localLevel.util.LoginStatus;
import com.softtech.localLevel.util.RandomUtils;
import com.softtech.localLevel.util.Status;

@Service
public class LoginService {
	
	@Value("${localLevel.token.expire.after}")
	private int tokenExpireAfter;
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired 
	private LoginTokenRepository loginTokenRepository;

	public LoginResponseDto doLogin(LoginRequestDto loginRequestDto) {
		
		Login login=loginRepository.findByUsernameAndStatusNot(loginRequestDto.getUsername(),Status.DELETED);
		if(null==login) {
			throw new NotFoundException("User with the username "+loginRequestDto.getUsername()+" not found!");
		}
		
		if(BCrypt.checkpw(loginRequestDto.getPassword(), login.getPassword())) {
			login.setLoginStatus(LoginStatus.LOGGEDIN);
			login.setLastLogin(new Date());
			login.setDeviceId(loginRequestDto.getDeviceId());
			login=loginRepository.save(login);
			LoginToken loginToken=new LoginToken();
			loginToken.setLoginId(login.getId());
			loginToken.setToken(RandomUtils.randomString(50));
			loginToken.setTokenExpirationDateTime(DateUtils.currentDateTimePlusMinutes(tokenExpireAfter));
			loginToken.setStatus(Status.ACTIVE);
			loginToken = loginTokenRepository.save(loginToken);
		//	LoginResponseDto response = getLoginResponse(login);
			LoginResponseDto response=new LoginResponseDto();
			//can send other attributes value aswell.
			response.setToken(loginToken.getToken());
			return response;
			
			
		}
		throw new LoginFailException(Constant.EMAIL_AND_PASSWORD_MISMATCH);
		
		
	}

}
