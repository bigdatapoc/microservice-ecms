package com.ecms.service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.ecms.constants.MessageConstants;
import com.ecms.dao.TemplateDao;
import com.ecms.dao.UserDao;
import com.ecms.model.Event;
import com.ecms.model.Template;
import com.ecms.model.User;

/**
 * This is Service Class For Writing Business Logic.
 * 
 * @author nagpalh
 */

@Service
public class NotificationServiceImpl implements NotificationService
{

    /**
     * Object Used to send mail
     */
    @Autowired
    private JavaMailSender javamailSender;

    /**
     * This object is used for processing template in "templates" folder on class
     * path
     */
    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private TemplateDao templateDao;
    @Autowired
    private UserDao userDao;

    private static Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);


    /**
     * Asynchronous function for Sending mail using thymeleaf engines
     * 
     * @param EventDao
     * 
     * @return CompletableFuture<String> It is nothing but Just a String that we
     *         will receive from this function in future, Simply a JAVA-8 Feature.
     */

    @Async
    public CompletableFuture<String> sendMail(Event event)
    {

        log.info(MessageConstants.Enter_Notification_Service);
        String response = MessageConstants.Failure_Mail_Response;
        MimeMessage message = javamailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try
        {
            helper =
                new MimeMessageHelper(
                    message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            // helper.addAttachment("logo.jpg", new ClassPathResource("download.jpg"));
            Context context = new Context();

            List<User> mailUser = userDao.getUsers(event.getEventId());

            Template template = templateDao.getEventTemplate(event.getEventId());
            log.info("Queries done here");

            // context.setVariables(mail.getTemplateVariableMap());

            // Converting file name without extension
            String fileNameWithOutExt = FilenameUtils.removeExtension(template.getTemplateContent());
            String html = templateEngine.process(fileNameWithOutExt, context);
            helper.setTo(mailUser.iterator().next().getEmail());
            helper.setText(html, true);
            helper.setSubject(template.getSubject());
            helper.setFrom("${spring.mail.username}");
            javamailSender.send(message);
            response = MessageConstants.Success_Mail_Response;
            log.info("Successfull: " + response);

        }
        catch (MessagingException e)
        {
            response = MessageConstants.Exception_Mail_Response;
            log.info(
                "Error Message: "
                    + e.getMessage() + "\nException Class: " + e.getClass()
                    + "\nCause of Exception: " + e.getCause() + "\nStack Trace oF Exception: " + e.getStackTrace());
        }
        return CompletableFuture.completedFuture(response);
    }

}
