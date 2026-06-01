package com.avisys.empmgmt.external;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.avisys.empmgmt.dto.MailData;

@FeignClient(name = "email-service",url = "http://192.168.1.16:7010/email")
public interface EmailService {
	
	@PostMapping("/schedule")
	void sendMail(MailData mailData);

}
