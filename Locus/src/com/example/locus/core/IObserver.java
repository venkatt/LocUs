package com.example.locus.core;

import com.example.locus.entity.User;

public interface IObserver {
	public void onReceiveMessage(User src, String msg);
	public void onReceiveUserProfile(User user);
}
