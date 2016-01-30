package org.thehecklers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by markheckler on 1/19/2016.
 */
@Component
public class DataHandler extends TextWebSocketHandler {
    private List<WebSocketSession> sessionList = new ArrayList<>();

    @Autowired
    private ReadingRepository repo;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionList.add(session);
        System.out.println("Data connection established from " + session.toString() + ", TIME: " + new Date().toString());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Reading reading = mapper.readValue(message.getPayload(), Reading.class);

            repo.save(reading);
            System.out.println("New reading: " + reading.toString());

            sessionList.stream().filter(s -> s != session).forEach(s -> {
                try {
                    s.sendMessage(message);
                } catch (IOException e) {
                    System.out.println("Exception re-sending data message: " + e.getLocalizedMessage());
                }
            });
        } catch (Exception e) {
            System.out.println("Exception: " + e.getLocalizedMessage());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionList.remove(session);
        System.out.println("Data connection closed by " + session.toString() + ", TIME: " + new Date().toString());
    }
}
