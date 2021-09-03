package com.pro.social.media.agent.components;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Message.RecipientType;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.pro.social.media.agent.controllerdto.MailDetailsDto;
import com.pro.social.media.agent.mailconfig.EmailConstant;
import com.pro.social.media.agent.multitenancy.TenantContext;
import com.pro.social.media.agent.service.MailService;
import com.pro.social.media.agent.utills.CommonConstants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SocialMediaAgentSchedulers {

	@Autowired
	@Qualifier("objMailServiceImpl")
	MailService objMailServiceImpl;

    MailDetailsDto objDetailsDto = new MailDetailsDto();
    ArrayList<String> listOfAttachmentPath   = new ArrayList<>();
	
    @Scheduled(fixedRate = 2000, initialDelay = 3000)
	public void getMailsDetailsScheduleTaskWithInitialDelay() {
    	System.out.println("getMailsDetailsScheduleTaskWithInitialDelay method executed");
		TenantContext.setCurrentTenant(CommonConstants.DEFAULT_TEANTID);
		getListOfMailIdsDetails();
	}

    //Start another ways to get inbox details
	public void listOfMailDeatis(String host, String storeType, String user, String password) {
		System.out.println("check mail method is executed");
		try {
			// create properties field
			Properties properties = new Properties();
			properties.put("mail.pop3.host", host);
			properties.put("mail.pop3.port", "995");
			properties.put("mail.pop3.starttls.enable", "true");
			Session emailSession = Session.getDefaultInstance(properties);
			// create the POP3 store object and connect with the pop server
			Store store = emailSession.getStore("pop3s");
			store.connect(host, user, password);
			// create the folder object and open it
			Folder emailFolder = store.getFolder("INBOX");
			// emailFolder.open(Folder.READ_ONLY);
			emailFolder.open(Folder.READ_WRITE);
			// retrieve the messages from the folder in an array and print it
			Message[] messages = emailFolder.getMessages();
			System.out.println("messages.length---" + messages.length);
			for (int i = 0, n = messages.length; i < n; i++) {
				Message message = messages[i];
				log.info("---------------------------------");
				log.info("Email Number----------> " + (i + 1));
				log.info("Subject---------->: " + message.getSubject());
				log.info("From-------->: " + message.getFrom()[0]);
				log.info("Text:---------->" + getTextFromMessage(message, i));
				log.info("getContentType:---------->" + message.getContentType());
				log.info("getFileName:-------->" + message.getFileName());
				log.info("getReceivedDate:-------->" + message.getReceivedDate());
				log.info("SentDate:--------->" + message.getSentDate());
				log.info("folder:--------->" + message.getFolder());
				log.info("description---------------->: " + message.getDescription());
				log.info("getAllRecipients---------------->: " + message.getAllRecipients());
				log.info("ReplyTo---------------->: " + message.getReplyTo());
				log.info("TO mail---------------->: " + message.getRecipients(RecipientType.TO));
				// String fromMailId = message.getFrom()[0].toString()
				// .substring(1, message.getFrom()[0].toString().length() - 1).split("<")[1];//
				// rk3960570@gmail.com
				processMessageBody(message);
				
				String textMsgBody = getTextFromMessage(message, i);

				DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
				
				objDetailsDto.setFrom(message.getFrom()[0].toString());
				objDetailsDto.setSubject(message.getSubject());
				objDetailsDto.setMailSentDatel(df.format(message.getSentDate()).toString());
				objDetailsDto.setListOf_To_mailId("");
				objDetailsDto.setListOf_CC_mailId("");
				objDetailsDto.setListOf_BCC_mailId("");
				objDetailsDto.setMessageBody(textMsgBody);
				objDetailsDto.setAttachmentPath(listOfAttachmentPath);
				
				log.info("listOfAttachmentPath--------->"+listOfAttachmentPath);
				
				log.info("objDetailsDtoobjDetailsDto=" + objDetailsDto);

				try {
					String rtnStatus = objMailServiceImpl.insertMailDetails(objDetailsDto, "1");
					log.info("rtnStatus--------->" + rtnStatus);
				} catch (Exception e) {
					e.printStackTrace();
					log.info("Error comming into try-block" + e.getMessage());

				}

				/// If you required multipart, Then you can uncomment.
				// log.info("fromMailId==" + fromMailId);// facebook
				// <update+kjdp_hv3k1-i@facebookmail.com

				
			}
			// close the store and folder objects
			emailFolder.close(false);
			store.close();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void m1() {

	}

	private String getTextFromMessage(Message message, int i) throws MessagingException, IOException {
		String result = "";
		if (message.isMimeType("text/plain")) {
			result = message.getContent().toString();
		} else if (message.isMimeType("multipart/*")) {
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			result = getTextFromMimeMultipart(mimeMultipart, i);
		}
		return result;
	}

	private String getTextFromMimeMultipart(MimeMultipart mimeMultipart, int k) throws MessagingException, IOException {
		String result = "";
		int count = mimeMultipart.getCount();
		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			if (bodyPart.isMimeType("text/plain")) {
				result = result + "\n" + bodyPart.getContent();
				break; // without break same text appears twice in my tests
			} else if (bodyPart.isMimeType("text/html")) {
				String html = (String) bodyPart.getContent();
				// result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
				result = html;
			} else if (bodyPart.getContent() instanceof MimeMultipart) {
				result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent(), k);
			}
			MimeBodyPart part = (MimeBodyPart) mimeMultipart.getBodyPart(i);
			String directoryName = LocalDateTime.now().toString().replaceAll(":", "");
		    StringBuilder sb = new StringBuilder(directoryName).append(k);
		    
			if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
				
				File file = new File("D:\\AttachmentFiles\\" + sb.toString());
				if (!file.exists()) {
					if (file.mkdir()) {
						log.info("Directory is created!");
					} else {
						log.info("Failed to create directory!");
					}
				}
				String destFilePath = "D:/AttachmentFiles/" + sb.toString() + "/" + part.getFileName();
				log.info("files path----->" + destFilePath);
				listOfAttachmentPath.add(destFilePath);
				
				FileOutputStream output = new FileOutputStream(destFilePath);
				InputStream input = part.getInputStream();
				byte[] buffer = new byte[4096];
				int byteRead;
				while ((byteRead = input.read(buffer)) != -1) {
					output.write(buffer, 0, byteRead);
				}
				output.close();
			}
		}
		return result;
	}
	public void processMessageBody(Message message) {
		Object content = null;
		try {
			content = message.getContent();
			// check for string
			// then check for multipart
			if (content instanceof String) {
				log.info("content inside processMessageBody" + content);
				log.info("objDetailsDto.getFrom()=" + objDetailsDto.getFrom());
				log.info("objDetailsDto.getSubject()=" + objDetailsDto.getSubject());
				log.info("objDetailsDto.getMailSentDatel()=" + objDetailsDto.getMailSentDatel());
				objDetailsDto.setMessageBody(content.toString());
				try {
					objMailServiceImpl.insertMailDetails(objDetailsDto, "1");
				} catch (Exception e) {
					e.printStackTrace();
					log.info("Error comming into try-block" + e.getMessage());

				}
			} else if (content instanceof Multipart) {
				Multipart multiPart = (Multipart) content;
				procesMultiPart(multiPart);
			} else if (content instanceof InputStream) {
				InputStream inStream = (InputStream) content;
				int ch;
				while ((ch = inStream.read()) != -1) {
					System.out.write(ch);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	public void procesMultiPart(Multipart content) {
		try {
			int multiPartCount = content.getCount();
			for (int i = 0; i < multiPartCount; i++) {
				BodyPart bodyPart = content.getBodyPart(i);
				Object obj;
				obj = bodyPart.getContent();
				if (obj instanceof String) {
					// log.info("objDetailsDto.getFrom();="+objDetailsDto.getFrom());
					// log.info("objDetailsDto.getSubject();="+objDetailsDto.getSubject());
					// log.info("objDetailsDto.getMailSentDatel();="+objDetailsDto.getMailSentDatel());
					// log.info("contentcontentcontentcontentcontentcontent"+obj);
					// objDetailsDto.setMessageBody(obj.toString());
					// try {
					// objMailServiceImpl.insertMailDetails(objDetailsDto, "1");
//	                    }
//	                    catch(Exception e) {
//	                    	e.printStackTrace();
//	                    	log.info("Error comming into try-block"+e.getMessage());
//	                    	
//	                    }
				} else if (obj instanceof Multipart) {
					procesMultiPart((Multipart) obj);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void getListOfMailIdsDetails() {
		String host = EmailConstant.host;
		String mailStoreType = EmailConstant.mailStoreType_1;
		String username = EmailConstant.username;
		String password = EmailConstant.password;
		listOfMailDeatis(host, mailStoreType, username, password);
	}

}
