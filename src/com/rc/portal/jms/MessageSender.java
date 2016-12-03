package com.rc.portal.jms;

import java.util.Iterator;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * @Title: MessageSender.java
 * @Description:
 * @author yinbinhome@163.com
 * @date 2011-11-22 下午05:12:10
 * @version V1.0
 */

public class MessageSender {
	// ~~~ jmsTemplate
	public JmsTemplate jmsTemplate;

	/**
	 * 说明:发送对象消息
	 * 
	 * @param messageModel
	 */
//	public void sendMessage(MessageModel messageModel) {
//
//		jmsTemplate.convertAndSend(messageModel);
//	}

	/**
	 * 说明:发送Map对象消息
	 * 
	 * @param messageModel
	 */
	public void sendMapMessage(final Map<String,String> mapModel) {

		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				MapMessage msg = session.createMapMessage();
				Iterator it = mapModel.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry pairs = (Map.Entry) it.next();
					msg.setString(pairs.getKey().toString(), pairs.getValue().toString());		
				}
				return msg;
			}

		});
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
}
