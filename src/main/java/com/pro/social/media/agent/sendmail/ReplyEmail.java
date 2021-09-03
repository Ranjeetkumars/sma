package com.pro.social.media.agent.sendmail;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.pro.social.media.agent.controllerdto.ReplyMailDto;
import com.pro.social.media.agent.mailconfig.EmailConstant;
import com.pro.social.media.agent.service.MailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReplyEmail {

	@Autowired
	@Qualifier("objMailServiceImpl")
	MailService objMailServiceImpl;

	public ReplyEmail(String repliedText, String mailSuject, String replyTo) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", EmailConstant.emailSMTPserver);
		props.put("mail.smtp.port", EmailConstant.emailSMTPPort);
		try {
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			Store mailStore = session.getStore(EmailConstant.mailStoreType);
			mailStore.connect(EmailConstant.emailSMTPserver, EmailConstant.username, EmailConstant.password);
			Folder folder = mailStore.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);
			Message[] messages = folder.getMessages();
			System.out.println("Total Message - " + messages.length);
			Message mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(EmailConstant.username));
			// mimeMessage.setText(repliedText);// if you wants to send planText then you
			// can uncomment
			mimeMessage.setContent(repliedText, "text/html");
			StringBuilder sb = new StringBuilder("Reg").append(": ").append(mailSuject);
			mimeMessage.setSubject(sb.toString());
			System.out.println("Message.RecipientType.TO---->" + Message.RecipientType.TO);
			InternetAddress[] addresses = InternetAddress.parse(replyTo);
			mimeMessage.addRecipients(Message.RecipientType.TO, addresses);
			Transport.send(mimeMessage);
			System.out.println("Email message " + "replied successfully.");
			
			insertReplydMail(replyTo,sb.toString(),repliedText);
			folder.close(false);
			mailStore.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error in replying email.");
		}
	}

	private class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(EmailConstant.username, EmailConstant.password);
		}
	}
	
	//replyTo,sb.toString(),repliedText
	public  void insertReplydMail(String replyTo , String subject ,String repliedText) {
		System.out.println("replyTo--->"+replyTo);
		System.out.println("subject--->"+subject);
		System.out.println("repliedText--->"+replyTo);
		System.out.println("@@@@@@@@@@@@@@@@@insertReplydMail method is executed@@@@@@@@@@@@@@@@@@@@@@@@");
		
		ReplyMailDto objReplyMailDto = new ReplyMailDto();
		
		objReplyMailDto.setFromMailId(replyTo);
		objReplyMailDto.setSubject(subject);
		objReplyMailDto.setReplayMailBody(repliedText);
		
		System.out.println("objReplyMailDto---->"+objReplyMailDto.toString());
//		try {
//			System.out.println("inside try - block");
//			String rtnReplyStatus = objMailServiceImpl.insertReplyMail(objReplyMailDto,"1");
//			log.info("return status--->" + rtnReplyStatus);
//		} catch (Exception e) {
//			log.info("Exception comming into try-block="+e.getMessage());
//		}
	}
}
