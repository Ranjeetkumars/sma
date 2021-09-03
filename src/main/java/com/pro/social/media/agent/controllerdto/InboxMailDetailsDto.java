package com.pro.social.media.agent.controllerdto;

public class InboxMailDetailsDto {

	
	private  String fromMailIds;
	private  String subject;
	private  String mailBody;
	private  String sentDate;
	
	public String getFromMailIds() {
		return fromMailIds;
	}
	public void setFromMailIds(String fromMailIds) {
		this.fromMailIds = fromMailIds;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMailBody() {
		return mailBody;
	}
	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}
	public String getSentDate() {
		return sentDate;
	}
	public void setSentDate(String sentDate) {
		this.sentDate = sentDate;
	}

	@Override
	public String toString() {
		return "InboxMailDetailsDto [fromMailIds=" + fromMailIds + ", subject=" + subject + ", mailBody=" + mailBody
				+ ", sentDate=" + sentDate + "]";
	}
	
}
