package com.substring.chat.controllers;

import java.time.LocalDateTime;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import com.substring.chat.config.AppConstants;
import com.substring.chat.entities.Message;
import com.substring.chat.entities.Room;
import com.substring.chat.payload.MessageRequest;
import com.substring.chat.repositories.RoomRepository;

@Controller
@CrossOrigin(AppConstants.FRONT_END_BASE_URL)
public class ChatController {
	
	private RoomRepository roomRepository;

	public ChatController(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}
	
	//for sending and receiving message--- yeah method hai 
	
	@MessageMapping("/sendMessage/{roomId}")// message ko eisper bheja jaayega--- like /app/sendMessage/roomId
	@SendTo("/topic/room/{roomId}")   // yeaha subscribe karega
	//iska retuntype Message mein convert ho jaayega
	public Message sendMessage(@DestinationVariable String roomId,
			@RequestBody MessageRequest request) throws Exception {//RequestBody mein jo MessageRequest hai uska ek alag class banaya hai
		
		Room room = roomRepository.findByRoomId(request.getRoomId());// room ko nikaalenge ki room already exist kar raha hai yaa nhi tabhi id se niklega
		Message message= new Message();
		message.setContent(request.getContent());
		message.setSender(request.getSender());
		message.setTimeStamp(LocalDateTime.now());
		
		if(room!=null) {
			room.getMessages().add(message);
			roomRepository.save(room);
		}else {
			throw new RuntimeException("room not found!!");
		}
		return message;
	}
	

}
