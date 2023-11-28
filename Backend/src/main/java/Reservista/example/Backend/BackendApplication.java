package Reservista.example.Backend;

import Reservista.example.Backend.MailComponent.Mail;
import Reservista.example.Backend.MailComponent.MailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		System.out.println("heeloo");
		Mail mail=new Mail();
		mail.setSubject("TESTING");
		mail.setTo("mariam.gerges1188@gmail.com");
		mail.setBody("testing send with correct email");
		MailService mailservice=new MailService();
		int statusCode=mailservice.sendMail(mail);
		System.out.println(statusCode);

//		SpringApplication.run(BackendApplication.class, args);
	}

}
