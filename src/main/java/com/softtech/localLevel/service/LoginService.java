package com.softtech.localLevel.service;

import java.util.Date;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger LOG = LoggerFactory.getLogger(LoginService.class);

	@Value("${localLevel.token.expire.enable}")
	private String tokenExpireEnable;

	@Value("${localLevel.token.expire.after}")
	private int tokenExpireAfter;

	@Value("${localLevel.login.password.length}")
	private int passwordLength;


	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private LoginTokenRepository loginTokenRepository;

	public LoginResponseDto doLogin(LoginRequestDto loginRequestDto) {

		Login login = loginRepository.findByUsernameAndStatusNot(loginRequestDto.getUsername(), Status.DELETED);
		if (null == login) {
			throw new NotFoundException("User with the username " + loginRequestDto.getUsername() + " not found!");
		}

		if (BCrypt.checkpw(loginRequestDto.getPassword(), login.getPassword())) {
			login.setLoginStatus(LoginStatus.LOGGEDIN);
			login.setLastLogin(new Date());
			login.setDeviceId(loginRequestDto.getDeviceId());
			login = loginRepository.save(login);
			LoginToken loginToken = new LoginToken();
			loginToken.setLoginId(login.getId());
			loginToken.setToken(RandomUtils.randomString(50));
			loginToken.setTokenExpirationDateTime(DateUtils.currentDateTimePlusMinutes(tokenExpireAfter));
			loginToken.setStatus(Status.ACTIVE);
			loginToken = loginTokenRepository.save(loginToken);
			// LoginResponseDto response = getLoginResponse(login);
			LoginResponseDto response = new LoginResponseDto();
			// can send other attributes value aswell.
			response.setToken(loginToken.getToken());
			return response;

		}
		throw new LoginFailException(Constant.EMAIL_AND_PASSWORD_MISMATCH);

	}

	public boolean isValidToken(Long userId, String token) {
		if (userId.equals(0L)) {
			throw new ServiceException("userId or loginId required in header parameter.");
		}
		if (null == userId || null == token) {
			return false;
		}
		// Login user = userRepository.getOne(userId);
		LOG.debug("User id {} and token {}", userId, token);
		// Login login = loginRepository.findByIdAndToken(user.getLoginId(), token);
		LoginToken login = loginTokenRepository.findByLoginIdAndToken(userId, token);
		if (null == login) {
			return false;
		}
		LOG.debug("Login found.");
		if (tokenExpireEnable.equalsIgnoreCase(Constant.ENABLE)) {
			if (!DateUtils.isCurrentTimeBeforeThanGivenTime(login.getTokenExpirationDateTime()))
				return false;
		}
		return true;
	}

}
