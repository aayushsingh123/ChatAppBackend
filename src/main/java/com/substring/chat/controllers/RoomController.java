package com.substring.chat.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.substring.chat.config.AppConstants;
import com.substring.chat.entities.Message;
import com.substring.chat.entities.Room;
import com.substring.chat.repositories.RoomRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")  // Fixed API version
@CrossOrigin(AppConstants.FRONT_END_BASE_URL)
public class RoomController {

    private  RoomRepository roomRepository;
    // Constructor Injection for RoomRepository
    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    // Create room
    @PostMapping
    // <?> ka matlab mutiple type ka value bhejna
    public ResponseEntity<?> createRoom(@RequestBody String roomId) {  // Assuming roomId is sent in the request body
        if (roomRepository.findByRoomId(roomId) != null) {
            // room already there
            return ResponseEntity.badRequest().body("Room already exists!");
        }

        // Create new room
        Room room = new Room();
        room.setRoomId(roomId);
      Room savedRoom=  roomRepository.save(room);  // Save the new room in DB
        return ResponseEntity.status(HttpStatus.CREATED).body(room);
    }

    // Get room: join
    @GetMapping("/{roomId}")
    public ResponseEntity<?> joinRoom(@PathVariable String roomId) {
        Room room = roomRepository.findByRoomId(roomId);

        if (room == null) {
            return ResponseEntity.badRequest().body("Room not found");  // Room not found
        }

        return ResponseEntity.ok(room);  // Return the found room
    }

    // Get messages of the room
    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String roomId,
                                                     @RequestParam(value = "page", defaultValue = "0", required = false)
                                                     int page, @RequestParam(value = "size" ,defaultValue = "20", required = false)
            int size) {
        Room room = roomRepository.findByRoomId(roomId);

        if (room == null) {
            return ResponseEntity.badRequest().build();  // Room not found
        }
//get messages
        //get pagination
      List<Message> messages=  room.getMessages();
        int start=Math.max(0,messages.size()-(page+1)*size);
        int end= Math.min(messages.size(),start+size);
        List<Message> paginatedMessages = messages.subList(start,end);
        return ResponseEntity.ok(paginatedMessages);  // Return messages of the room
    }
}
