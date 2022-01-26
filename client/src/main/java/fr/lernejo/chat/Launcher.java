package fr.lernejo.chat;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Launcher {
    public static void main(String[] args) {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            System.out.println("Input a message, we will sent it for you (q for quit)");
            userInput = scanner.nextLine();
            if (userInput.equals("q")) {
                System.exit(0);
            } else {
                rabbitTemplate.convertAndSend("chat_messages", userInput);
            }
        }
    }
}
