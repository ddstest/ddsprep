package org.talend.dataprep.api.service;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.talend.dataprep.api.service.mail.MailDetails;
import org.talend.dataprep.api.service.mail.MailToCommand;
import org.talend.dataprep.exception.TDPException;
import org.talend.dataprep.exception.error.APIErrorCodes;
import org.talend.dataprep.metrics.Timed;

import com.netflix.hystrix.HystrixCommand;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController @Api(value = "api", basePath = "/api", description = "Send feedback to Talend") public class MailServiceAPI
        extends APIService {

    @RequestMapping(value = "/api/mail", method = PUT) @ApiOperation(value = "Send feedback to Talend") @Timed public void mailTo(
            @RequestBody MailDetails mailDetails) {
        if (mailDetails.isEmpty()) {
            throw new TDPException(APIErrorCodes.UNABLE_TO_GET_MAIL_DETAILS);
        }
        try {

            final HystrixCommand<Void> sendFeedback = getCommand(MailToCommand.class, mailDetails);
            sendFeedback.execute();

        } catch (Exception e) {
            throw new TDPException(APIErrorCodes.UNABLE_TO_SEND_MAIL, e);
        }

    }

}
