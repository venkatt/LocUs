package com.example.locus.core;

import java.util.List;

import com.example.locus.entity.Result;
import com.example.locus.entity.User;

public interface ICore extends IObservable {
	Result register(User user);
	Result logout();
	Result refreshLocation(double lati, double longti);
	
	List<User> getUsersNearby();
	
	Result sendMessage(User user, String msg);
	Result broadcastMessage(String msg);
}
