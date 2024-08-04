package com.korniushin.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mail {
    //тема
    private String subject;
    //сообщение
    private String message;
    // адрес
    private String toEmail;
    //файл
    private String pathToAttachment;

}
