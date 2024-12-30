package com.substring.chat.entities;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "rooms")  // Correct MongoDB collection annotation
public class Room{

    @Id
    private String id;  // MongoDB's unique identifier
    private String roomId;
    private List<Message> messages = new ArrayList<>();  // Initialize the List

    // No-argument constructor (Equivalent to @NoArgsConstructor)
    public Room() {
    }

    // All-arguments constructor (Equivalent to @AllArgsConstructor)
    public Room(String id, String roomId, List<Message> messages) {
        this.id = id;
        this.roomId = roomId;
        this.messages = messages;
    }
 
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
