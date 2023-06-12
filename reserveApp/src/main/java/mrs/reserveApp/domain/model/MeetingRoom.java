package mrs.reserveApp.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "meeting_room")
public class MeetingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String roomId;

    String roomName;

    public MeetingRoom(String roomId, String roomName){
        this.roomId = roomId;
        this.roomName = roomName;
    }

    public MeetingRoom(){
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
