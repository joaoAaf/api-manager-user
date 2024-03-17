package apiManagerUser.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import apiManagerUser.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	User findByLogin(String login);
	 
}
