package com.demo.chatapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "user")
public class User implements Serializable
{
	@Id
	@GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
      name = "sequence-generator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
        @Parameter(name = "sequence_name", value = "user_id_sequence"),
        @Parameter(name = "initial_value", value = "1"),
        @Parameter(name = "increment_size", value = "1")
        }
    )
	@Column(name = "user_id")
	private int userId;	
	
	@Column(name = "user_first_name", unique = true)
	private String userFirstName;
	
	@Column(name = "user_last_name", nullable = false)
	private String userLastName;	
	
	@Column(name = "user_name", nullable = false)
	private String username;
	
	@Column(name = "user_email_id", unique = true)
	private String userEmailId;
	
	@Column(name = "user_password", nullable = false)
	private String userPassword;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_friend",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "friend_id")
			)	
	private Collection<Friend> friends = new ArrayList<>();

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getUserFirstName()
	{
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName)
	{
		this.userFirstName = userFirstName;
	}

	public String getUserLastName()
	{
		return userLastName;
	}

	public void setUserLastName(String userLastName)
	{
		this.userLastName = userLastName;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getUserEmailId()
	{
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId)
	{
		this.userEmailId = userEmailId;
	}

	public String getUserPassword()
	{
		return userPassword;
	}

	public void setUserPassword(String userPassword)
	{
		this.userPassword = userPassword;
	}

	public Collection<Friend> getFriend()
	{
		return friends;
	}

	public void setFriend(Collection<Friend> friends)
	{
		this.friends = friends;
	}

	@Override
	public String toString()
	{
		return "User [userId=" + userId + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName
				+ ", username=" + username + ", userEmailId=" + userEmailId + ", userPassword=" + userPassword
				+ ", friend=" + friends + "]";
	}
	
	
}
