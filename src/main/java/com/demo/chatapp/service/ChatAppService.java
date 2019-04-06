package com.demo.chatapp.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.demo.chatapp.model.Friend;
import com.demo.chatapp.model.Message;
import com.demo.chatapp.model.User;

public interface ChatAppService
{
	public void add();
	public JSONObject authenticateUser(User user);
	public void registerUser(User user);
	public List<User> getAllUsers();
	public boolean addFriend(Friend friend);
	public List<Friend> getAllFriends();
	public Friend getFriend(int friendId);
	public void sendMessage(Message message);
	public List<Message> getAllMessage();
}
