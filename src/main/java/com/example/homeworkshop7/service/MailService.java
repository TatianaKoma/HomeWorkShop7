package com.example.homeworkshop7.service;

public interface MailService {
    void sendRegistrationEmail(String email, String subject, String name, String content);
}
