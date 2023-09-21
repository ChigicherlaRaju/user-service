package com.user.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.dto.DepartmentDto;
import com.user.dto.ResponseDto;
import com.user.dto.UserDto;
import com.user.model.User;
import com.user.repository.UserRepository;
import com.user.util.AppConstants;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final RestTemplate restTemplate;

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public ResponseDto getUser(Long userId) {

		ResponseDto responseDto = new ResponseDto();
		User user = userRepository.findById(userId).get();
		UserDto userDto = mapToUser(user);

		ResponseEntity<DepartmentDto> responseEntity = restTemplate
				.getForEntity(AppConstants.DEPARTMENT_URL + user.getDepartmentId(), DepartmentDto.class);

		DepartmentDto departmentDto = responseEntity.getBody();

		System.out.println(responseEntity.getStatusCode());

		responseDto.setUser(userDto);
		responseDto.setDepartment(departmentDto);

		return responseDto;
	}

	private UserDto mapToUser(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setEmail(user.getEmail());
		return userDto;
	}

}