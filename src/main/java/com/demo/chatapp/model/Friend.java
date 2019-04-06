package com.demo.chatapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "friends")
public class Friend
{
	@Id
	@GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
      name = "sequence-generator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
        @Parameter(name = "sequence_name", value = "friend_id_sequence"),
        @Parameter(name = "initial_value", value = "1"),
        @Parameter(name = "increment_size", value = "1")
        }
    )
	@Column(name = "friend_id")
	private int friendId;
	
	@Column(name = "friend_name", nullable = false)
	private String friendName;
	
	@Column(name = "friend_email_id", unique = true, nullable = false)
	private String friendEmailAddress;

	@ManyToOne
	private User user;

	public int getFriendId()
	{
		return friendId;
	}

	public void setFriendId(int friendId)
	{
		this.friendId = friendId;
	}

	public String getFriendName()
	{
		return friendName;
	}

	public void setFriendName(String friendName)
	{
		this.friendName = friendName;
	}

	public String getFriendEmailAddress()
	{
		return friendEmailAddress;
	}

	public void setFriendEmailAddress(String friendEmailAddress)
	{
		this.friendEmailAddress = friendEmailAddress;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	@Override
	public String toString()
	{
		return "Friend [friendId=" + friendId + ", friendName=" + friendName + ", friendEmailAddress="
				+ friendEmailAddress + ", user=" + user + "]";
	}
	
}
