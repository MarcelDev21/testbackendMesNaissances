package tech.marcel.naissances.Notification;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

@Component
public class EmailService {

    public EmailService(MailPitClient mailPitClient) {
        this.mailPitClient = mailPitClient;
    }

    private final MailPitClient mailPitClient;

    public void EnvoieMail(Map<String, String> parametre){
        String messageConstruit = this.ConstruireMessage(parametre);
        Map<String, Object> emailParameters = Map.of(
                "Subjet", "Code d'activation",
                "HTML", messageConstruit,
                "TEXT", messageConstruit,
                "from", Map.of(
                        "email", "marc@gmail.com",
                        "Name","Marc"
                ),
                "to", List.of(
                        Map.of(
                                "email",parametre.get("email"),
                                "name", parametre.get("name")
                        )
                )
        );
        this.mailPitClient.Send(emailParameters);
    }


    public String ConstruireMessage(Map<String, String> parametre){
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(this.getClass(), "/templates");
        configuration.setObjectWrapper(new DefaultObjectWrapper());


        try {
            Template template = configuration.getTemplate(parametre.get("template"));
            StringWriter stringWriter = new StringWriter();
            Map<String, String> templateModal = Map.of(
                    "name", parametre.get("name"),
                    "code", parametre.get("code")
            );
            template.process(templateModal,stringWriter);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}