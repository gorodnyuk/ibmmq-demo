package uk.gorodny.ibmmqdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gorodny.ibmmqdemo.model.User;

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
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
