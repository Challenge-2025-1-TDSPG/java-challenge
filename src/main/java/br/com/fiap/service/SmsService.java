package br.com.fiap.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class  SmsService {

    private final String accountSid = System.getenv("ACCOUNT_SID");
    private final String authToken = System.getenv("AUTH_TOKEN");
    private final String fromNumber = System.getenv("FROM_NUMBER");

    public SmsService() {
        if (accountSid == null || authToken == null) {
            throw new IllegalStateException("As variáveis de ambiente ACCOUNT_SID e AUTH_TOKEN não estão definidas no arquivo .env.");
        }

        Twilio.init(accountSid, authToken);
    }

    public void send(String to, String mensagem) {
        Message.creator(
           new PhoneNumber(to),
           new PhoneNumber(fromNumber),
           mensagem
        ).create();
    }
}
