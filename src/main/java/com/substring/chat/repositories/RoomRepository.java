package com.substring.chat.repositories;



import org.springframework.data.mongodb.repository.MongoRepository;
 // Correct import for Room class
import com.substring.chat.entities.Room;

public interface RoomRepository extends MongoRepository<Room, String> {

    //get room using id
    Room findByRoomId(String roomId);
  

}
//Niche samjhoo
/*

findByRoomId:

Yeh ek custom query method hai.
Spring Data MongoDB automatically is query ko generate karega, kyunki method ka naam database field (roomId) ke naam ke basis par hai.
Parameter:

String roomId: Yeh roomId field ka value hai jiske basis par query chalani hai.
Return Type:

Room: Yeh Room object return karega jo MongoDB collection mein given roomId ke saath match karega.
Kaise kaam karega?
Jab aap findByRoomId("someRoomId") call karte ho, toh Spring Data MongoDB Room collection mein ek document search karega jiska roomId match kare.
Agar match milta hai, toh wo Room object ko return karega.
Agar match nahi milta, toh null return karega.

*/