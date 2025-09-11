package tech.marcel.naissances.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import tech.marcel.naissances.Notification.MailPitClient;

@Configuration
public class ApplicationConfiguration {
    String mailClientUrl = "https://training.mails.chillo.fr";
    @Bean
    MailPitClient mailPitClient() {
        RestClient client = RestClient.create(mailClientUrl);
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory
                        .builderFor(RestClientAdapter.create(client))
                        .build();


        return httpServiceProxyFactory.createClient(MailPitClient.class);
    }
}
