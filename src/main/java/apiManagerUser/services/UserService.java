package apiManagerUser.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import apiManagerUser.domain.User;
import apiManagerUser.dto.UserMod;
import apiManagerUser.dto.UserPost;
import apiManagerUser.dto.UserView;
import apiManagerUser.repository.UserRepository;
import apiManagerUser.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	private BCryptPasswordEncoder passEnc() {
		return new BCryptPasswordEncoder();
	}

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(String id) {
		Optional<User> user = repo.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Usuário não existe"));
	}

	public boolean isEmail(String email) {
		return repo.findByEmail(email).isPresent();
	}

	public User insert(User user) {
		user.setPass(passEnc().encode(user.getPass()));
		return repo.insert(user);
	}

	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}

	public User update(User newUser) {
		User user = findById(newUser.getId());
		updateData(user, newUser);
		return repo.save(user);
	}

	private boolean validateUpdate(String attOldUser, String attNewUser) {
		return attNewUser != attOldUser && attNewUser!= null && attNewUser!= "";
	}
	
	private void updateData(User oldUser, User newUser) {
		if (validateUpdate(oldUser.getName(), newUser.getName())) {
			oldUser.setName(newUser.getName());
		}
		if (validateUpdate(oldUser.getEmail(), newUser.getEmail())) {
			oldUser.setEmail(newUser.getEmail());
		}
		if (validateUpdate(oldUser.getPass(), newUser.getPass())) {
			oldUser.setPass(passEnc().encode(newUser.getPass()));
		}
	}

	public User fromDTO(UserMod userDto) {
		return new User(userDto.name(), userDto.email(), userDto.pass());
	}

	public User fromDTO(UserPost userDto) {
		return new User(userDto.name(), userDto.email(), userDto.pass());
	}

	public UserView showUser(User user) {
		return new UserView(user.getId(), user.getName(), user.getEmail());
	}

}
