package com.korniushin.eshop.model.dao.interfaces;

import com.korniushin.eshop.model.Mail;

public interface MailService {

    void sendTextMail(Mail mail);

    void sendTemplateMail(Mail mail);

    void sendMailToUs (String senderName, String senderMail, String subject, String message);
}