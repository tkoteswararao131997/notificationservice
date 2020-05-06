package com.bridgelabz.notificationservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.notificationservice.ServiceImpl.NotificationServiceImpl;

@RestController
public class NotificationController {
	@Autowired
	private NotificationServiceImpl notificationServiceImpl;
	@Autowired
	private RestTemplate restTemplate;
	@GetMapping("/regVerification/{email}")
	public String sendVerification(@PathVariable String email)
	{
		System.out.println("-------------in notification");
		String subject="verification email";
		String token=restTemplate.exchange("http://localhost:8090/gettokenbyemail/"+email,HttpMethod.GET,null, String.class).getBody();
		String body="http://localhost:8080/verifyemail/"+token;
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
