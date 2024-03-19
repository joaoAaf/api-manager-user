package apiManagerUser.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import apiManagerUser.domain.User;
import apiManagerUser.dto.UserMod;
import apiManagerUser.dto.UserPost;
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

	public User findByEmail(String email) {
		return repo.findByEmail(email).get();
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

	private void updateData(User oldUser, User newUser) {
		if (newUser.getName() != oldUser.getName() && newUser.getName() != null) {
			oldUser.setName(newUser.getName());
		}
		if (newUser.getEmail() != oldUser.getEmail() && newUser.getEmail() != null) {
			oldUser.setEmail(newUser.getEmail());
		}
		if (newUser.getPass() != oldUser.getPass() && newUser.getPass() != null) {
			oldUser.setPass(passEnc().encode(newUser.getPass()));
		}
	}

	public User fromDTO(UserMod userDto) {
		return new User(userDto.getName(), userDto.getEmail(), userDto.getPass());
	}

	public User fromDTO(UserPost userDto) {
		return new User(userDto.getName(), userDto.getEmail(), userDto.getPass());
	}

}
