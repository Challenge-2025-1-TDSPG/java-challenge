package br.com.fiap.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class  SmsService {
    private static final Dotenv dotenv = Dotenv.load();

    private final String ACCOUNT_SID = dotenv.get("ACCOUNT_SID");
    private final String AUTH_TOKEN = dotenv.get("AUTH_TOKEN");
    private final String FROM_NUMBER = dotenv.get("FROM_NUMBER");

    public SmsService() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public void send(String to, String mensagem) {
        Message.creator(
           new PhoneNumber(to),
           new PhoneNumber(FROM_NUMBER),
           mensagem
        ).create();
    }
}
