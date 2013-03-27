package com.example.locus.dht;

import java.util.List;

import com.example.locus.entity.User;

public interface IDHT {
	void put(User user);
	List<User> getUsersByKey(User user);
	void delete(User user);
}
