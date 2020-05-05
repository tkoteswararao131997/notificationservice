package com.bridgelabz.notificationservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.notificationservice.ServiceImpl.NotificationServiceImpl;

@RestController
public class NotificationController {
	@Autowired
	private NotificationServiceImpl notificationServiceImpl;
	@GetMapping("/regVerification/{email}")
	public String sendVerification(@PathVariable String email)
	{
		System.out.println("-------------in notification");
		String subject="verification email";
		String body="http://localhost:8080/verifyemail";
		notificationServiceImpl.sendEmail(email,subject,body);
		return "verification mail sent";
	}
	@GetMapping("/resetPwd/{email}/{token}")
	public String sendLinkForForgetPassword(@PathVariable String email,@PathVariable String token)
	{
		String body="http://localhost:4200/resetpassword/"+token;
		

		notificationServiceImpl.sendEmail(email,"Reset Password",body);
		return "reset password link sent";
	}
}
