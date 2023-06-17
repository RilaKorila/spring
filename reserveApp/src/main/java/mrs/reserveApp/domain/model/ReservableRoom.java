package mrs.reserveApp.domain.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "reservable_room")
public class ReservableRoom implements Serializable {
    @EmbeddedId
    private ReservableRoomId reservableRoomId;

    @ManyToOne
    @JoinColumn(name = "room_id", insertable = false, updatable = false)
    @MapsId("roomId")
    private MeetingRoom meetingRoom;

    public ReservableRoom(ReservableRoomId reservableRoomId){
        this.reservableRoomId = reservableRoomId;
    }

    public ReservableRoom(){
    }

    public MeetingRoom getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(MeetingRoom meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public ReservableRoomId getReservableRoomId() {
        return reservableRoomId;
    }

    public void setReservableRoomId(ReservableRoomId reservableRoomId) {
        this.reservableRoomId = reservableRoomId;
    }
}
