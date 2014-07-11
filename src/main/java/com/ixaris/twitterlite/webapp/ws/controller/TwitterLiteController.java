package com.ixaris.twitterlite.webapp.ws.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ixaris.twitterlite.module.bd.busobject.IMessage;
import com.ixaris.twitterlite.module.bd.errors.InvalidUserInputException;
import com.ixaris.twitterlite.module.bd.facade.MessagesAdminFacade;
import com.ixaris.twitterlite.webapp.ws.helper.MessageModel;
import com.ixaris.twitterlite.webapp.ws.helper.UIHelper;

@Controller
public class TwitterLiteController {

	public static ArrayList<String> userList = new ArrayList<String>();

	@Autowired
	private MessagesAdminFacade messageAdmin;
	
	@RequestMapping(value = "/success/", method = RequestMethod.GET)
	public ModelAndView submitForm() {

		System.out.println("return here");
		return new ModelAndView("success");
	}

	@RequestMapping(value = "/tweets/", method = RequestMethod.POST)
	public @ResponseBody String submitForm(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "message", required = true) List<String> messages) {

		try {
			messageAdmin.addMessage(username, messages);
		} catch (InvalidUserInputException e) {
			return "Internal error occurred.";
		}
		return "Success!";
	}

	@RequestMapping(value = "/messages/hashtags/", method = RequestMethod.GET)
	public @ResponseBody List<MessageModel> lookupByHashes(
			@RequestParam(value = "hashlist", required = true) List<String> hashlist,
			@RequestParam(value = "offset", required = true) Integer offset,
			@RequestParam(value = "limit", required = true) Integer limit) {

		
		List<? extends IMessage> retrievedMessages;
		try {
			retrievedMessages = messageAdmin
					.lookupMessagesByHashTags(hashlist, offset, limit);
		} catch (Exception e) {
			return null;
		}
		return UIHelper.convertMessages(retrievedMessages);
	}

	@RequestMapping(value = "/messages/mention/", method = RequestMethod.GET)
	public @ResponseBody List<MessageModel> lookupByMention(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "offset", required = true) Integer offset,
			@RequestParam(value = "limit", required = true) Integer limit) {

		List<? extends IMessage> retrievedMessages;
		try {
			retrievedMessages = messageAdmin.lookupMessagesMentioningUser(username, offset, limit);
		} catch (Exception e) {
			return null;
		}
		return UIHelper.convertMessages(retrievedMessages);
	}

	@RequestMapping(value = "/messages/user/", method = RequestMethod.GET)
	public @ResponseBody List<MessageModel> lookupByUser(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "offset", required = true) Integer offset,
			@RequestParam(value = "limit", required = true) Integer limit) {

		List<? extends IMessage> retrievedMessages;
		try {
			retrievedMessages = messageAdmin.lookupMessagesByUser(username, offset, limit);
		} catch (Exception e) {
			return null;
		}
		return UIHelper.convertMessages(retrievedMessages);
	}

	@RequestMapping(value = "/messages", method = RequestMethod.GET)
	public @ResponseBody List<MessageModel> lookupByUser(@RequestParam(value = "offset", required = true) Integer offset,
			@RequestParam(value = "limit", required = true) Integer limit) {

		List<? extends IMessage> retrievedMessages;
		try {
			retrievedMessages = messageAdmin.lookupMessages(offset, limit);
		} catch (Exception e) {
			return null;
		}
		return UIHelper.convertMessages(retrievedMessages);
	}
}