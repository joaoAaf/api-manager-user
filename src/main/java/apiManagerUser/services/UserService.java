package apiManagerUser.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import apiManagerUser.domain.User;
import apiManagerUser.dto.CredentialsDTO;
import apiManagerUser.dto.UserDTO;
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
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User insert(User user) {
		user.setPass(passEnc().encode(user.getPass()));
		return repo.insert(user);
	}

	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}

	public User update(User user) {
		User newUser = findById(user.getId());
		updateData(newUser, user);
		return repo.save(newUser);
	}

	private void updateData(User newUser, User user) {
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
		newUser.setLogin(user.getLogin());
		newUser.setPass(user.getPass());
	}

	public User fromDTO(UserDTO userDto, CredentialsDTO credentialsDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail(), credentialsDto.getLogin(),
				credentialsDto.getPass());
	}

}
