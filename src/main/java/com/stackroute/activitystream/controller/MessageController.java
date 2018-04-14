package com.stackroute.activitystream.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stackroute.activitystream.model.Message;
import com.stackroute.activitystream.repository.MessageRepository;



/*Annotate the class with @Controller annotation.@Controller annotation is used to mark 
 * any POJO class as a controller so that Spring can recognize this class as a Controller*/
@Controller
public class MessageController {

	/*
	 * From the problem statement, we can understand that the application
	 * requires us to implement two functionalities. They are as following:
	 * 
	 * 1. display the list of existing messages from the database. Each message
	 *    should contain senderName, message, and timestamp 
	 * 2. send a message which should contain the senderName, message, and timestamp.
	 * 
	 */
	MessageRepository messageRepo = new MessageRepository();
	List<Message>list = new ArrayList<>();
	
	@RequestMapping("/")
	public String login_App(ModelMap model)
	{
		List<Message>list = messageRepo.getAllMessages();
		
		return "index";
	}
	
	
	/*Define a handler method to read the existing messages by calling the getAllMessages() method 
	 * of the MessageRepository class and add it to the ModelMap which is an implementation of Map 
	 * for use when building model data for use with views. it should map to the default URL i.e. "/" */
	
	@RequestMapping("/sendMessage")
	public String retriveData(@RequestParam("sender") String sendername, @RequestParam ("message") String message, ModelMap model)
	{
		System.out.println("inside");
		if(sendername==null || sendername.isEmpty() || message == null || message.isEmpty())
		{
			model.addAttribute("errormsg" , "Sender Name or Message cannot be empty");
			List<Message> list = messageRepo.getAllMessages();
			model.addAttribute("list" , list);
			return "index";
		}
		else
		{
			Message messageFromApp = new Message();
			messageFromApp.setSenderName(sendername);
			messageFromApp.setMessage(message);
			messageFromApp.setPostedDate();
			
			messageRepo.sendMessage(messageFromApp);
			List<Message> list = messageRepo.getAllMessages();
			System.out.println("messages are"+list);
			model.addAttribute("list", list);
			return "redirect:/";
			//return "index";
		}
		
	}
	
	
	/*Define a handler method which will read the senderName and message from request parameters and
	 * save the message by calling the sendMessage() method of MessageRepository class. Please note 
	 * that the timestamp should always be auto populated with system time and should not be accepted 
	 * from the user. Also, after saving the message, it should show the same along with existing 
	 * messages. Hence, reading messages has to be done here again and the retrieved messages object 
	 * should be sent back to the view using ModelMap.
	 * This handler method should map to the URL "/sendMessage". 
	*/
	

}
