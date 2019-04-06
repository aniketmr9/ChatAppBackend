package com.demo.chatapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "message")
public class Message
{
	@Id
	@GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
      name = "sequence-generator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
        @Parameter(name = "sequence_name", value = "message_id_sequence"),
        @Parameter(name = "initial_value", value = "1"),
        @Parameter(name = "increment_size", value = "1")
        }
    )
	@Column(name = "message_id")
	private int messageId;
	
	@Column(name = "sender_mail_address")
	private String senderMailAddress;
	
	@Column(name = "receiver_mail_address")
	private String receiverMailAddress;
	
	@Column(name = "message_body")
	private String messageBody;

	public int getMessageId()
	{
		return messageId;
	}

	public void setMessageId(int messageId)
	{
		this.messageId = messageId;
	}

	public String getSenderMailAddress()
	{
		return senderMailAddress;
	}

	public void setSenderMailAddress(String senderMailAddress)
	{
		this.senderMailAddress = senderMailAddress;
	}

	public String getReceiverMailAddress()
	{
		return receiverMailAddress;
	}

	public void setReceiverMailAddress(String receiverMailAddress)
	{
		this.receiverMailAddress = receiverMailAddress;
	}

	public String getMessageBody()
	{
		return messageBody;
	}

	public void setMessageBody(String messageBody)
	{
		this.messageBody = messageBody;
	}

	@Override
	public String toString()
	{
		return "Message [messageId=" + messageId + ", senderMailAddress=" + senderMailAddress + ", receiverMailAddress="
				+ receiverMailAddress + ", messageBody=" + messageBody + "]";
	}		
}
