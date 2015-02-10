package net.daragh.jira.mailparser;

/**
 * Created by daragh on 10/02/15.
 */

import com.atlassian.jira.service.util.handler.MessageHandler;
import com.atlassian.jira.service.util.handler.MessageHandlerContext;
import com.atlassian.jira.service.util.handler.MessageHandlerErrorCollector;

import java.util.Map;
import javax.mail.Message;
import javax.mail.MessagingException;

public class MailHandler implements MessageHandler {
    private String issueKey;
    @Override
    public void init(Map<String, String> params, MessageHandlerErrorCollector monitor) {
    }

    @Override
    public boolean handleMessage(Message message, MessageHandlerContext context) throws MessagingException {
        return true;
    }
}