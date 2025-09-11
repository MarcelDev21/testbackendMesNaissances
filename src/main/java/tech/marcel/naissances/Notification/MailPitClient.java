package tech.marcel.naissances.Notification;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

import java.util.Map;

public interface MailPitClient {

   /* @PostExchange("/api/v1/send")
    void Send(@RequestBody  Map<String, Object> data);*/

    //@PostExchange("/api/v1/send")
    //void Send(@RequestBody Map<String, Object> data);

    @PostExchange("/api/v1/send")
    void Send(@RequestBody Map<String, Object> data);
}
