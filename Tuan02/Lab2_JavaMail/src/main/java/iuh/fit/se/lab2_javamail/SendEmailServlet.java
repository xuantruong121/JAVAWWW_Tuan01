package iuh.fit.se.lab2_javamail;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebServlet("/sendEmail")
@MultipartConfig
public class SendEmailServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String recipient = req.getParameter("recipient");
        String subject = req.getParameter("subject");
        if (subject.isEmpty())
            subject = "Default Subject";
        String body = req.getParameter("body");
        Part attachmentPart = req.getPart("attachment");

        // Cấu hình tài khoản email
        final String senderEmail = "luamoi2014@gmail.com";
        final String password = "";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);

            MimeMultipart multipart = new MimeMultipart();

            // Phần nội dung email
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);
            multipart.addBodyPart(messageBodyPart);

            // Phần tệp đính kèm
            if (attachmentPart != null && attachmentPart.getSize() > 0) {
                MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                try (InputStream inputStream = attachmentPart.getInputStream()) {
                    attachmentBodyPart.setFileName(attachmentPart.getSubmittedFileName());
                    attachmentBodyPart.setContent(inputStream, attachmentPart.getContentType());
                }
                multipart.addBodyPart(attachmentBodyPart);
            }

            message.setContent(multipart);
            Transport.send(message);

            req.setAttribute("message", "Email sent successfully!");
        } catch (MessagingException e) {
            req.setAttribute("message", "Error sending email: " + e.getMessage());
            e.printStackTrace();
        }

        req.getRequestDispatcher("/result.jsp").forward(req, resp);
    }
}