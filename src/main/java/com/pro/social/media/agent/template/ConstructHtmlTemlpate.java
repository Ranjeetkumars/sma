package com.pro.social.media.agent.template;

import lombok.extern.slf4j.Slf4j;

/**
 * This ConstructHtmlTemlpate class is developed for to construct html teplate
 * using either StringBuilder or StringBuffer
 * 
 * @author Ranjeet kr. Sinha
 * @since 20Nov 2019
 *
 */ //         /EMAIL/helprequestform.html
@Slf4j
public class ConstructHtmlTemlpate {

	public static String sendTemplate() {
		log.info("sendTemplate method is executed inside ConstructHtmlTemlpate");
		StringBuffer sbf = new StringBuffer("<div style=\"color: rgb(51, 51, 51);"
				+ " font-family: &quot;Lucida Grande&quot;" + ", Verdana, Arial, Helvetica, sans-serif;"
				+ " font-size: 11px;\">" + "<b>Dear Patron,<br><br>The Information which you are shared is "
				+ "not sufficient to give any" + " emergency response. So&nbsp;Please click on the&nbsp;below"
				+ " link and fill the needed</b></div><div style=\"color: rgb(51, 51, 51);"
				+ " font-family: &quot;Lucida Grande&quot;, Verdana, Arial, Helvetica, sans-serif;"
				+ " font-size: 11px;\"><b>&nbsp;information to help you.</b></div><div style=\"color:"
				+ " rgb(51, 51, 51); font-family: &quot;Lucida Grande&quot;, Verdana, Arial, Helvetica,"
				+ " sans-serif; font-size: 11px;\"><b><br>link :-&nbsp;</b><a "
				+ "href=\"http://192.168.1.105:2706/EMAIL/helprequestform.html\">http://127.0.0.0:2706/EMAIL/helprequestform.html</a><b><br></b><h5>"
				+ "<b style=\"font-family: &quot;Lucida Grande&quot;, Verdana, Arial, Helvetica, sans-serif; font-size: 11px;\">"
				+ "Thanks&Regards,</b><br></h5><b>APEMS.</b></div>");
		return sbf.toString();
	}

}
