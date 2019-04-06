package com.demo.chatapp.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.chatapp.model.Friend;
import com.demo.chatapp.model.Message;
import com.demo.chatapp.model.User;
import com.demo.chatapp.service.ChatAppService;

@CrossOrigin
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/home")
public class ChatAppRestController
{
	@Autowired
	private ChatAppService chatAppService;
	
	@RequestMapping("/test")
	public void test()
	{
		chatAppService.add();
	}
	
	@RequestMapping(value="/authenticate", method = RequestMethod.POST)
	public JSONObject authenticate(@RequestBody User user)
	{
		System.out.println(user.toString());
		JSONObject jsonResponse = new JSONObject();
		jsonResponse = chatAppService.authenticateUser(user);
		return jsonResponse;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public boolean register(@RequestBody User user)
	{
		chatAppService.registerUser(user);
		return true;
	}
	
	@RequestMapping("/friends")
	public List<Friend> getFriends()
	{
		return chatAppService.getAllFriends();
	}
	
	@RequestMapping("/users")
	public List<User> getUsers()
	{
		return chatAppService.getAllUsers();
	}
	
	@RequestMapping(value = "/add_friend", method = RequestMethod.POST)
	public boolean addNewFriend(@RequestBody Friend friend)
	{
		return chatAppService.addFriend(friend);
	}
	
	@RequestMapping("{id}")
	public Friend getFriend(@PathVariable("id") int friendId) {
		return chatAppService.getFriend(friendId);
	}
	
	@RequestMapping(value="/send",method = RequestMethod.POST)
	public void loginUser(@RequestBody Message message)
	{
		chatAppService.sendMessage(message);			
	}
	
	@RequestMapping("/messages")
	public List<Message> getMessages()
	{
		return chatAppService.getAllMessage();
	}
}
