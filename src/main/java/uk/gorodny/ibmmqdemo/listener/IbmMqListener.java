package uk.gorodny.ibmmqdemo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import uk.gorodny.ibmmqdemo.model.User;

@Component
@Slf4j
public class IbmMqListener {

    @JmsListener(destination = "ORDER.REQUEST")
    public void consume(User message) {
        log.info("Consume: " + message);
    }

}
