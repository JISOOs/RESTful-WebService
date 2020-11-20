package com.example.myrestfulservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserDaoService {

	private static int userCount = 3;

	private static List<User> users = new ArrayList<>();

	static {
		users.add(new User(1, "jisoo", new Date()));
		users.add(new User(2, "jidu", new Date()));
		users.add(new User(3, "jidoo", new Date()));

	}

	public List<User> findAll() {
		return users;
	}

	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}

	public User fineOne(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public User updateUser(User user, int id) {
		if (user.getId() == id) {
		
			users.add(user);
		}
		return user;
	}

	public User deleteById(int id) {
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();

			if (user.getId() == id) {
				users.remove(id);
				return user;
			}

		}
		return null;

	}

}
