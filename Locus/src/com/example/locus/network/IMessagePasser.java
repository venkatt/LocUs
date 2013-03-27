package com.example.locus.network;

import java.util.List;

import com.example.locus.core.IObservable;
import com.example.locus.entity.Result;
import com.example.locus.entity.User;

public interface IMessagePasser extends IObservable {
	Result sendMessage(User src, User target, String msg);
	Result broadcast(User src, List<User> targets, String msg);
	Result startReceive();
}
