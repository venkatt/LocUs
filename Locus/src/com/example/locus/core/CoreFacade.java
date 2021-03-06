package com.example.locus.core;

import java.util.List;

import com.example.locus.entity.Result;
import com.example.locus.entity.User;

public class CoreFacade implements ICore {
	private static CoreFacade instance = null;
	
	private ICore coreImpl;
	
	private CoreFacade(){
		coreImpl = new FakeCoreImpl();
	}
	
	public static CoreFacade getInstance(){
		if (instance == null){
			instance = new CoreFacade();
		}
		
		return instance;
	}

	@Override
	public Result refreshLocation(double lati, double longti) {
		return coreImpl.refreshLocation(lati, longti);
	}

	@Override
	public List<User> getUsersNearby() {
		return coreImpl.getUsersNearby();
	}

	@Override
	public Result sendMessage(User user, String msg) {
		return coreImpl.sendMessage(user, msg);
	}

	@Override
	public Result broadcastMessage(String msg) {
		return coreImpl.broadcastMessage(msg);
	}

	@Override
	public Result addObserver(IObserver obs) {
		return coreImpl.addObserver(obs);
	}

	@Override
	public Result register(User user) {
		return coreImpl.register(user);
	}

	@Override
	public Result logout() {
		return coreImpl.logout();
	}
}
