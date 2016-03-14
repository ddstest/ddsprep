// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// https://github.com/Talend/data-prep/blob/master/LICENSE
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================

package org.talend.dataprep.api.service.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import java.util.Base64;

/**
 * Abstract FeedbackSender
 */
public abstract class AbstractFeedbackSender implements FeedbackSender {

    private static final String DEFAULT_RECIPIENT = "test@test.org";
    private static final String DEFAULT_SENDER = "dataprep_dev@talend.com";
    private static final String DEFAULT_SMTP_USER_NAME = "user@talend.com";

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractFeedbackSender.class);

    protected String[] recipients;
    protected String userName;
    protected String sender;

    @Autowired
    public void setRecipients(@Value("${mail.smtp.to}") String recipients) {
        try {
            this.recipients = new String(Base64.getDecoder().decode(recipients)).split(",");
        }catch(Exception exc){
            LOGGER.debug("Unable to parse given recipients used to send feedback mails, falling back to the default value {}", recipients);
            this.recipients = new String[]{DEFAULT_RECIPIENT};
        }
    }

    @Autowired
    public void setUserName(@Value("${mail.smtp.username}") String userName){
        try {
            this.userName = new String(Base64.getDecoder().decode(userName));
        }catch(Exception exc){
            LOGGER.debug("Unable to parse given smtp user name used to send feedback mails, falling back to the default value {}", userName);
            this.userName = DEFAULT_SMTP_USER_NAME;
        }
    }
    @Autowired
    public void setSender(@Value("${mail.smtp.from}") String sender){
        try {
            this.sender = new String(Base64.getDecoder().decode(sender));
        }catch(Exception exc){
            LOGGER.debug("Unable to parse given sender used to send feedback mails, falling back to the default value {}", sender);
            this.userName = DEFAULT_SENDER;
        }
    }

    public String[] getRecipients() {
        return recipients;
    }

    public String getUserName() {
        return userName;
    }

    public String getSender() {
        return sender;
    }
}