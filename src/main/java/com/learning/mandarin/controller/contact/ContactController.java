package com.learning.mandarin.controller.contact;


import com.learning.mandarin.controller.announce.dto.AnnouncementDto;
import com.learning.mandarin.controller.announce.dto.AnnouncementOutputDto;
import com.learning.mandarin.controller.contact.dto.ContactDto;
import com.learning.mandarin.domain.Announcement;
import com.learning.mandarin.domain.AnnouncementRepository;
import com.learning.mandarin.exception.ServiceUnavailableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {

    @Autowired
    private MailSender mailSender;

    @Value("${organaizer.mail.address}")
    private String mailAddress;

    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void getAllData(@RequestBody ContactDto dto) throws ServiceUnavailableException{
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mailAddress);
        //msg.setCc("送信先メールアドレス2", "送信先メールアドレス3");
        //msg.setBcc("送信先メールアドレス4");
        msg.setSubject("台湾華語問い合わせ:" + dto.title);
        String contents = "名前：" + dto.name + "\n" + "会社/団体名：" + dto.company + "\n" +
                "Email：" + dto.email + "\n" + "内容：\n" + dto.inquiry + "\n";
        msg.setText(contents);
        // メール送信
        try {
            mailSender.send(msg);
        } catch (Exception e) {
            log.error("ContactController.getAllData", e);
            throw new ServiceUnavailableException();
        }
    }
}
