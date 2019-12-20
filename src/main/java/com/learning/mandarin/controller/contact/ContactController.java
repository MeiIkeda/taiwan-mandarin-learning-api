package com.learning.mandarin.controller.contact;

import com.sendgrid.*;
import com.learning.mandarin.controller.contact.dto.ContactDto;
import com.learning.mandarin.exception.ServiceUnavailableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {

    @Autowired
    private SendGrid sendGrid;

    @Value("${organaizer.mail.address}")
    private String mailAddress;

    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void sendMail(@RequestBody ContactDto dto) throws IOException {

        Email from = new Email(dto.email);
        String subject = "台湾華語問い合わせ:" + dto.title;
        Email to = new Email(mailAddress);
        Content content = new Content("text/plain",
                "名前：" + dto.name + "\n" + "会社/団体名：" + dto.company + "\n" +
                        "Email：" + dto.email + "\n" + "内容：\n" + dto.inquiry + "\n");

        Mail mail = new Mail(from, subject, to, content);

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);

            // SendGridからのレスポンスが200/202以外なら例外
            if (!(response.getStatusCode() == 200 || response.getStatusCode() == 202))  {
                throw new IOException("SendGrid error");
            }
        } catch (IOException ex) {
            log.error("ContactController.sendMail", ex);
            throw new IOException();
        }
    }
}
