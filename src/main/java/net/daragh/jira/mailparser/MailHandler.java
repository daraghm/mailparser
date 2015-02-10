package net.daragh.jira.mailparser;

/**
 * Created by daragh on 10/02/15.
 */

import com.atlassian.jira.service.util.handler.MessageHandler;
import com.atlassian.jira.service.util.handler.MessageHandlerContext;
import com.atlassian.jira.service.util.handler.MessageHandlerErrorCollector;

import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;
import javax.mail.Message;
import javax.mail.MessagingException;

public class MailHandler implements MessageHandler {
    //Hold our settings in settings and don't use the built in Jira "context"
    public HashMap<String, String> settings = new HashMap<String, String>();

    //local_mail_domains = tlds that we allow to bypass.  if not in this then
    //create the user as "external user"
    public HashSet<String> local_mail_domains;

    //private final IssueKeyValidator issueKeyValidator;
    //private final MessageUserProcessor messageUserProcessor;

    private String issueKey;
    @Override
    public void init(Map<String, String> params, MessageHandlerErrorCollector monitor) {
        log("Init: MailHandler params: (" + params + ")");
        paramSet(params, Keys.KEY_project);
        paramSet(params, Keys.KEY_issuetype);
        paramSet(params, Keys.KEY_stripquotes, "true");
        //paramSet(params, Keys.KEY_local_mail_domains);//duplicated below?
        if (paramSet(params, Keys.KEY_local_mail_domains))
        {
            //local_mail_domains = MailHandlerUtil.mailDomains(params.get(Keys.KEY_local_mail_domains));//TODO do we need this here?
            log("local_mail_domains: " + params.get(Keys.KEY_local_mail_domains));
            log("local_mail_domains parsed to " + local_mail_domains.toString());
        }
        //paramSet(params, "default_reporter");
        paramSet(params, Keys.KEY_external_reporter);
        paramSet(params, Keys.KEY_external_reporter_field);
        paramSet(params, Keys.KEY_bulk, "forward");
        //todo: create_user? what if we don't want to create users even from our pool of allowed domains?

        log("MailHandler Settings: (" + settings + ")");
    }

    @Override
    public boolean handleMessage(Message message, MessageHandlerContext context) throws MessagingException {

        return true;
    }

    private void log(String l){
        System.out.println(l);
    }

    //insert a parameter into our Settings HashMap.  Search the parameters given based on String
    //don't raise exception but warn.  If you call this you expect it to be there.
    //TODO: keep passing the map but could pass an array and recurse through using the array
    private boolean paramSet(Map<String, String> params, String var) {
        boolean found = false;
        if (params.containsKey(var)) {
            settings.put(var, params.get(var));
            found = true;
        }
        else {
            log("Project Parameter setting (" + var + ") not specified this plugin may not work");
        }
        return found;
    }

    //as above but if missing add default.
    private boolean paramSet(Map<String, String> params, String var, String defaultVar)
    {
        if (params.containsKey(var))
        {
            settings.put(var, params.get(var));
        }
        else
        {
            settings.put(var, defaultVar);
        }
        return true;
    }


}