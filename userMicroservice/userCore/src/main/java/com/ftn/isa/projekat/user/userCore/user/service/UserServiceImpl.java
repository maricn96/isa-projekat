package com.ftn.isa.projekat.user.userCore.user.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.ftn.isa.projekat.user.userApi.dto.UserCredentialsDTO;
import com.ftn.isa.projekat.user.userApi.dto.UserDTO;
import com.ftn.isa.projekat.user.userApi.dto.UserForRegistrationDTO;
import com.ftn.isa.projekat.user.userCore.converter.DTOUserConverter;
import com.ftn.isa.projekat.user.userCore.converter.DTOUserRoleConverter;
import com.ftn.isa.projekat.user.userCore.user.model.User;
import com.ftn.isa.projekat.user.userCore.user.repository.UserRepository;
import com.ftn.isa.projekat.user.userCore.userRole.model.UserRole;
import com.ftn.isa.projekat.user.userCore.userRole.repository.UserRoleRepository;

@Component
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserRoleRepository roleRepository;
	
	@Autowired
	DTOUserConverter userConverter;
	@Autowired
	DTOUserRoleConverter roleConverter;
	
	//Class for helping us with sending emails
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	//Class which loads information from application.properties file.
	
	@Autowired
	private Environment env;

	
	
	
	@Override
	public UserDTO findOneById(Long id) {
		
		Optional <User> user = userRepository.findById(id);
		
		
		if (user.isPresent()) {
			
			return userConverter.convertToDTO(user.get());
		
		}
		else {
			
			return new UserDTO();
			
		}
		
	}

	@Override
	public List<UserDTO> findAll() {

		Optional< List<User> > list = Optional.of(userRepository.findAll());
		ArrayList< UserDTO > usersDTO = new ArrayList< UserDTO >();
		
		if ( list.isPresent() ) {
			
			for ( User user : list.get()) {
				
				usersDTO.add(userConverter.convertToDTO(user));
				
			}
			
			return usersDTO;
			
		}
		
		return Collections.emptyList();
		
	}

	@Override
	public UserDTO save(UserDTO userToSave) {
		
		userRepository.save(userConverter.convertFromDTO(userToSave));
		
		return userToSave;
	}

	@Override
	public UserDTO deleteById(Long id) {
		
		Optional<User> userToDelete = userRepository.findById(id);
		
		if( userToDelete.isPresent() ) {
		
			userRepository.deleteById(id);
			
			return userConverter.convertToDTO(userToDelete.get());
		
		}
		
		return new UserDTO();
		
		
	}

	@Override
	public UserDTO changeUser(Long id, UserDTO user) {
		
		Optional<User> userForChange = userRepository.findById(id);
		
		if( userForChange.isPresent() && user!=null) {
			
			
				
			userForChange.get().setCity(user.getCity());
			userForChange.get().setEmail(user.getEmail());
			userForChange.get().setName(user.getName());
			userForChange.get().setPassport(user.getPassport());
			userForChange.get().setSurname(user.getSurname());
			userForChange.get().setTelephoneNumber(user.getTelephoneNumber());
			
			userRepository.save(userForChange.get());
			
			user.setId(userForChange.get().getId());
			
			return user;
			
			
			
		}
		
		return new UserDTO();
	}

	@Override
	public List<UserDTO> getallFriends(Long id) {
		
		Optional<List<User>> friends = userRepository.getAllFriends(id);
		
		if(friends.isPresent()) {
			
			ArrayList<UserDTO> friendsDTO = new ArrayList<UserDTO>();
			
			for(User user : friends.get()) {
				
				friendsDTO.add(userConverter.convertToDTO(user));
				
			}
			
			
			return friendsDTO;
			
		}
		
		
		return Collections.emptyList();
	}

	@Override
	public UserForRegistrationDTO registerUser(UserForRegistrationDTO dto) {
		
		/*
		 * First we need to see if user with same email
		 * is already existing in database. If it does, we are giving back empty object with information on email with error
		 * info
		 * 
		 *  */
		Optional<User> foundUserByEmail = userRepository.findOneByEmail(dto.getEmail());

		if(foundUserByEmail.isPresent()) {
			UserForRegistrationDTO foundUser = new UserForRegistrationDTO();
			foundUser.setEmail("Email exits");
			
			return foundUser;
		}
		
		
		//if there is not user with same email, we are saving this one
		User userForSave = new User();
		
		userForSave.setActive(false);
		userForSave.setCity(dto.getCity());
		userForSave.setEmail(dto.getEmail());
		userForSave.setName(dto.getName());
		userForSave.setPassport(dto.getPassport());
		userForSave.setPassword(dto.getPassword());
		userForSave.setSurname(dto.getSurname());
		userForSave.setTelephoneNumber(dto.getTelephoneNumber());
		
		Optional<UserRole> role = roleRepository.findById((long) 1);
		
		if(role.isPresent())
			userForSave.setRole(role.get());
		
		User savedUser= userRepository.save(userForSave);
		
		sendEmail(savedUser);
		
		dto.setId(savedUser.getId());
		
		
		
		return dto;
	}
	
	@Async
	public void sendEmail(User savedUser) {
		
		//now we are sending activation link to the email address of registered user
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(savedUser.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Activation link for TRAVEL WITH RSK");
		mail.setText("Hello " + savedUser.getName() + ",\n\n Thank you for registration in our website. \n Please click on this link for activation of your account: "
				+ " http://localhost:8096/api/user/user/activate/" + savedUser.getId() + "  ");
		javaMailSender.send(mail);
		
		
	}

	@Override
	public UserDTO activateUser(Long id) {
		
		Optional<User> foundUser = userRepository.findById(id);
		
		if(foundUser.isPresent()) {
			
			foundUser.get().setActive(true);
			
			userRepository.save(foundUser.get());
			
			return userConverter.convertToDTO(foundUser.get());
			
		}
		
		return new UserDTO();
		
	}

	@Override
	public List<UserDTO> findUsersByRole(Long id) {
		
		Optional<List<User>> users = userRepository.findAllByRoleId(id);
		
		if(users.isPresent()) {
			
			ArrayList<UserDTO> usersDTO = new ArrayList<UserDTO>();
			
			for(User user : users.get()) {
				
				usersDTO.add(userConverter.convertToDTO(user));
				
			}
			
			return usersDTO;
			
		}
		
		return Collections.emptyList();
	}

	@Override
	public UserDTO changeRoleOfUser(Long userId, Long roleId) {
		
		Optional<User> foundUser = userRepository.findById(userId);
		
		if(foundUser.isPresent()) {
			
			Optional<UserRole> userRole = roleRepository.findById(roleId);
			
			if(userRole.isPresent()) {
							
				foundUser.get().setRole(userRole.get());

				userRepository.save(foundUser.get());
				return userConverter.convertToDTO(foundUser.get());
				
			}
			foundUser.get().setActive(true);
			
			
			
			
		}
		
		return new UserDTO();
		
	}

	@Override
	public List<UserDTO> getAllFriendRequests(Long id) {
		
		Optional<List<User>> friendRequests = userRepository.getAllFriendRequests(id);
		
		if(friendRequests.isPresent()) {
			
			ArrayList<UserDTO> friendsDTO = new ArrayList<UserDTO>();
			
			for(User user : friendRequests.get()) {
				
				friendsDTO.add(userConverter.convertToDTO(user));
				
			}
			
			
			return friendsDTO;
			
		}
		
		
		return Collections.emptyList();
	}

	@Override
	public UserDTO loginUser(UserCredentialsDTO userCredentials) {
		
		Optional<User> user = userRepository.findByEmailAndPassword(userCredentials.getEmail(),userCredentials.getPassword());
		
		if(user.isPresent()) {
			
			if(user.get().isActive()) {
				
				return userConverter.convertToDTO(user.get());
				
			}
			
			
		}
		
		return new UserDTO();
		
	}

}
