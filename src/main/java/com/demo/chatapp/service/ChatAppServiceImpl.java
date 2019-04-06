package com.demo.chatapp.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.chatapp.model.Friend;
import com.demo.chatapp.model.Message;
import com.demo.chatapp.model.User;

@Service
public class ChatAppServiceImpl implements ChatAppService
{
	@Autowired
	private EntityManager entityManager;

	@Transactional
	@Override
	public void add()
	{
		User user = new User();
		user.setUserFirstName("Aniket");
		user.setUserLastName("Maurya");
		user.setUsername("anikmaur");
		user.setUserEmailId("aniketmr9@gmail.com");
		user.setUserPassword("admin");
		Friend friend = new Friend();
		friend.setFriendName("Bob");
		friend.setFriendEmailAddress("bob@gmail.com");
		Friend friend2 = new Friend();
		friend2.setFriendName("Artyom");
		friend2.setFriendEmailAddress("artyom@gmail.com");
		user.getFriend().add(friend);
		user.getFriend().add(friend2);
		System.out.println(entityManager);
		entityManager.persist(user);
		entityManager.persist(friend);
		entityManager.persist(friend2);
	}

	@Transactional
	@Override
	public List<Friend> getAllFriends()
	{
		List<Friend> friends = entityManager.createQuery("from Friend").getResultList();
		return friends;
	}

	@Transactional
	@Override
	public Friend getFriend(int friendId)
	{
		Friend friend = entityManager.find(Friend.class, friendId);
		System.out.println(friend.toString());
		return friend;
	}

	@Transactional
	@Override
	public void sendMessage(Message message)
	{
		entityManager.persist(message);
	}

	@Transactional
	@Override
	public List<Message> getAllMessage()
	{
		String getAllMessagesJQL = "SELECT m FROM Message m ORDER BY m.messageId";
		List<Message> messages = entityManager.createQuery(getAllMessagesJQL).getResultList();
		return messages;
	}

	@Transactional
	@Override
	public JSONObject authenticateUser(User u)
	{
		JSONObject jsonResponse = new JSONObject();
		try
		{
			TypedQuery<User> typedQuery = entityManager.createQuery("from User WHERE username=:username", User.class);
			User user = typedQuery.setParameter("username", u.getUsername()).getSingleResult();
			System.out.println(user.toString());
			if(!user.equals(null))
			{
				if(user.getUserPassword().equals(u.getUserPassword()))
				{
					jsonResponse.put("authStatus", 1);
					jsonResponse.put("User", user);
					return jsonResponse;
				}
				else
				{
					jsonResponse.put("authStatus", 2);
					return jsonResponse;
				}
			}
			else
			{
				jsonResponse.put("authStatus", 0);
				return jsonResponse;
			}
		}
		catch (NoResultException noresult)
		{
			jsonResponse.put("authStatus", 0);
			return jsonResponse;
		}
		catch (NonUniqueResultException notUnique)
		{
			jsonResponse.put("authStatus", 3);
			return jsonResponse;
		}
	}

	@Transactional
	@Override
	public List<User> getAllUsers()
	{
		List<User> users = entityManager.createQuery("from User").getResultList();
		return users;
	}

	@Transactional
	@Override
	public void registerUser(User user)
	{
		System.out.println(user);
		entityManager.persist(user);		
	}

	@Transactional
	@Override
	public boolean addFriend(Friend friend)
	{
		TypedQuery<Friend> typedQuery = entityManager.createQuery("from Friend WHERE friendEmailAddress=:friendEmailAddress", Friend.class);
		Friend friendObj = typedQuery.setParameter("friendEmailAddress", friend.getFriendEmailAddress()).getSingleResult();		
		return false;
	}
}
