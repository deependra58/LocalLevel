package com.softtech.localLevel.service;

import java.io.File;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softech.localLevel.exception.AlreadyExistException;
import com.softech.localLevel.request.UserCreationRequest;
import com.softtech.localLevel.model.Login;
import com.softtech.localLevel.model.User;
import com.softtech.localLevel.repository.LoginRepository;
import com.softtech.localLevel.repository.UserRepository;
import com.softtech.localLevel.util.BCrypt;
import com.softtech.localLevel.util.FileUtil;
import com.softtech.localLevel.util.LoginStatus;
import com.softtech.localLevel.util.Status;

@Service
public class UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LoginRepository loginRepository;

	public User createUser(Long userId, UserCreationRequest userCreationRequest) {
		Login logins= loginRepository.findByUsernameAndStatusNot(userCreationRequest.getUsername(), Status.DELETED);
		if (logins != null) {
			throw new AlreadyExistException("User with username " + userCreationRequest.getUsername()
					+ " already exist. Try with other username.");
		}

		User user = userRepository.findByEmailAndStatusNot(userCreationRequest.getEmail(), Status.DELETED);
		if (user != null) {

			throw new AlreadyExistException("User with email " + userCreationRequest.getEmail()
					+ " already exist. Try with other email.");
		}
		File file=null;
		try {
		user=new User();
		user.setFirstName(userCreationRequest.getFirstName());
		user.setMiddleName(userCreationRequest.getMiddleName());
		user.setLastName(userCreationRequest.getLastName());
		user.setAddress(userCreationRequest.getAddress());
		user.setEmail(userCreationRequest.getEmail());
		user.setMobileNumber(userCreationRequest.getMobileNumber());
		user.setStatus(Status.ACTIVE);
		if (userCreationRequest.getProfilePicture() != null) {
			file = FileUtil.write(
					String.valueOf(new Date().getTime()).concat(".").concat("png"),
					userCreationRequest.getProfilePicture());
			LOG.debug("path {}", file.getAbsolutePath());
			if (file != null)
				user.setProfilePicture(file.getAbsolutePath());
		}
		
		Login login=new Login();
		login.setLoginStatus(LoginStatus.LOGOUT);  //default login
		login.setUsername(userCreationRequest.getUsername());
		login.setUserRole(userCreationRequest.getUserRole());
		String pw=BCrypt.hashpw(userCreationRequest.getPassword(), BCrypt.gensalt());
		System.out.println("passoword:"+pw);
		login.setPassword(pw);
		login.setStatus(Status.ACTIVE);
		login.setCreatedDate(new Date());
		login.setCreatedBy(userId);
		LOG.debug("Adding user...");
		loginRepository.save(login);
		user.setCreatedBy(userId);
		user.setCreatedDate(new Date());
		user.setLoginId(login.getId());
		userRepository.save(user);
		LOG.debug("User Added...");
		}
		catch (Exception e) {
			e.printStackTrace();
			if (file != null) {
				file.delete();
			}
		
	
		}
		
		return user;
	}
}
		

	
