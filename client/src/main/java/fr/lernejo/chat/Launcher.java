package fr.lernejo.chat;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class Launcher {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(Launcher.class);
        RabbitTemplate rabbitTemplate = appContext.getBean(RabbitTemplate.class);
        rabbitTemplate.setRoutingKey("chat_messages");

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            System.out.println("Input a message, we will sent it for you (q for quit)");
            userInput = scanner.nextLine();
            if (userInput.equals("q")) {
                System.out.println("Bye");
                System.exit(0);
            } else {
                rabbitTemplate.convertAndSend("chat_messages", userInput);
            }
        }
    }
}
