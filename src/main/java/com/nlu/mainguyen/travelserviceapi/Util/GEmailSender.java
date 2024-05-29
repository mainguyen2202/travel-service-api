package com.nlu.mainguyen.travelserviceapi.Util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class GEmailSender {

    public boolean sendEmail(String to, String from, String subject, String text) {
        boolean flag = false;

        //logic
        //smtp properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.host", "smtp.gmail.com");

        // String username = "web";
        String email = "trucmainguyen02@gmail.com";
        String password = "pbpfganrroiylmuq";


        //session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress(from));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return flag;
    }
    /*
    public static void main(String[] args) {

        GEmailSender gEmailSender = new GEmailSender();
        String to = "20130321@st.hcmuaf.edu.vn";
        String from = "trucmainguyen02@gmail.com";
        String subject = "Second: Sending email using GMail";
        String text = "This is a example email send using gmail and java program with out less secure app";
        boolean b = gEmailSender.sendEmail(to, from, subject, text);
        if (b) {
            System.out.println(" Email is sent successfully");
        } else {
            System.out.println(" There is problem in sending email");
        }

    }
    */

}