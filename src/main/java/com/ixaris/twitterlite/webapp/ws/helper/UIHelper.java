package com.ixaris.twitterlite.webapp.ws.helper;

import java.util.ArrayList;
import java.util.List;

import com.ixaris.twitterlite.module.bd.busobject.IMessage;

public class UIHelper {

	public static List<MessageModel> convertMessages(List<? extends IMessage> messageList) {

		List<MessageModel> sanitisedList = new ArrayList<MessageModel>();

		for (int i = 0; i < messageList.size(); i++) {

			IMessage currentMessage = messageList.get(i);

			sanitisedList.add(new MessageModel(currentMessage.getUsername(), currentMessage.getContent(),
					currentMessage.getTimestamp(), currentMessage.getMentions(), currentMessage.getHashtags()));
		}

		return sanitisedList;
	}
}
