package com.godbearing.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.godbearing.dao.EnquiryDao;
import com.godbearing.dao.MessageDao;
import com.godbearing.dao.SubscriberDao;
import com.godbearing.dto.EnquiryDto;
import com.godbearing.dto.MessageDto;
import com.godbearing.dto.Subscriber;
import com.godbearing.service.MailService;

@RestController
public class EnquiryController {
	
	@Autowired
	EnquiryDao enqDao;
	
	@Autowired
	SubscriberDao subDao;
	
	@Autowired
	MessageDao msgDao;
	
	@Autowired
	MailService mail;
	
	@GetMapping("/getAllEnquiry")
	public List<EnquiryDto> getAllEnquiry(){
		List<EnquiryDto> list = new ArrayList<EnquiryDto>();
		this.enqDao.findAll().forEach(e -> list.add(e));
		return list;
	}
	
	@GetMapping("/getAllMessage")
	public List<MessageDto> getAllMessage(){
		List<MessageDto> list = new ArrayList<MessageDto>();
		this.msgDao.findAll().forEach(e -> list.add(e));
		return list;
	}
	
	@PostMapping("/sendEnquiry")
	public EnquiryDto sendEnquiry(@RequestBody EnquiryDto dto) {
		
		String text = "Dear Admin, \n \n "+dto.getName()+" has enquired about "+dto.getQty()+" "
						+dto.getEnquiryFor()+".\nPlease connect with him on "+dto.getContact()+" or email him "+
						"on "+dto.getEmail()+". \n\n Comment By "+dto.getName()+":\n"+dto.getComment()+"\n\n"+
						"Thanks"
						;
		if(dto.isSubscriber()) {
			this.subscribe(dto.getEmail());
		}
		
		mail.sendSimpleMessage("stormy.neel@gmail.com", "Enquiry about "+dto.getEnquiryFor(), text);
		return this.enqDao.save(dto);
	}
	
	@PostMapping("/sendMessage")
	public MessageDto sendMessage(@RequestBody MessageDto dto) {
		
		String text = "Dear Admin, \n \n "+dto.getName()+" has sent you a message.\nPlease connect with him on email "+
						dto.getEmail()+". \n\n Message By "+dto.getName()+"\n"+dto.getMessage()+"\n\n"+
						"Thanks"
						;
		
		
		mail.sendSimpleMessage("stormy.neel@gmail.com", dto.getSubject(), text);
		return this.msgDao.save(dto);
	}
	
	@PostMapping("/subscribe")
	public String subscribe(@RequestBody String email) {
		Subscriber sub = new Subscriber();
		sub.setEmail(email);
		this.subDao.save(sub);
		return "{\"msg\":\"Success\"}";
	}
	
}
