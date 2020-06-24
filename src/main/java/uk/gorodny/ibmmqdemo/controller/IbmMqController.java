package uk.gorodny.ibmmqdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gorodny.ibmmqdemo.model.User;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/ibmmq")
public class IbmMqController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping("/produce/{firstName}/{lastName}")
    public ResponseEntity<User> send(@PathVariable("firstName") String firstName,
                                     @PathVariable("lastName") String lastName) {
        User user = new User().setFirstName(firstName).setLastName(lastName);
        jmsTemplate.convertAndSend("ORDER.REQUEST", user);
        User receivedUser = null;
        try {
            receivedUser = receiveUserAsync().get(5, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        if (receivedUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(receivedUser, HttpStatus.OK);
    }

    @Async
    private CompletableFuture<User> receiveUserAsync() {
        User user = (User) jmsTemplate.receiveAndConvert("ORDER.REQUEST");
        return CompletableFuture.completedFuture(user);
    }
}
