package com.medical.service;

import static com.medical.util.MedicalClaimConstants.CREDENTIALS_EMPTY;
import static com.medical.util.MedicalClaimConstants.LOGIN_FAILURE;
import static com.medical.util.MedicalClaimConstants.LOGIN_SUCCESS;
import static com.medical.util.MedicalClaimConstants.LOGIN_SUCCESS_CODE;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.dto.LoginDTO;
import com.medical.dto.LoginResponseDTO;
import com.medical.entity.User;
import com.medical.exception.MedicalClaimException;
import com.medical.repository.UserRepository;

/**
 * @author shiva
 *
 */
@Service
public class LoginServiceImpl implements LoginService {

	private static final Logger lOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	/**
	 * @param LoginDTO
	 * @throws BookLendingException
	 * @return LoginResponseDTO This method check whether the credentials are
	 *         correct or in correct
	 */
	@Override
	public LoginResponseDTO getUserDetails(LoginDTO loginDTO) {

		lOGGER.info("Inside LoginServiceImpl");

		String userName = loginDTO.getUserEmail();
		String password = loginDTO.getPassword();

		lOGGER.info("userName:{} Password:{}", userName, password);

		LoginResponseDTO loginResponseDTO = null;

		if (userName.equals("") || password.equals("")) {

			throw new MedicalClaimException(CREDENTIALS_EMPTY);

		}

		else {
			Optional<User> userList = userRepository.findByUserEmailAndPassword(userName, password);

			if (!(userList.isPresent())) {
				throw new MedicalClaimException(LOGIN_FAILURE);

			}

			else {

				loginResponseDTO = new LoginResponseDTO();
				User user = userList.get();
				loginResponseDTO.setUserName(user.getUserName());
				loginResponseDTO.setUserId(user.getUserId());
				loginResponseDTO.setMessage(LOGIN_SUCCESS);
				loginResponseDTO.setStatusCode(LOGIN_SUCCESS_CODE);

			}

		}

		return loginResponseDTO;
	}

}
