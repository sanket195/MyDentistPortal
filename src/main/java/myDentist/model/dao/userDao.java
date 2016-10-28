package myDentist.model.dao;

import java.util.List;

import myDentist.model.User;

public interface userDao {
	
	User getUser (Integer userId);
	
	List<User> getUsers();	
	
	User saveUser(User user);

	User getUserByUsername(String username);
}
