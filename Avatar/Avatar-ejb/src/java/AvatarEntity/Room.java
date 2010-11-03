/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AvatarEntity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Christian
 */
@Entity
@Table(name = "room")
@NamedQueries({
    @NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r"),
    @NamedQuery(name = "Room.findByRoomNo", query = "SELECT r FROM Room r WHERE r.roomNo = :roomNo"),
    @NamedQuery(name = "Room.findByRoomName", query = "SELECT r FROM Room r WHERE r.roomName = :roomName"),
    @NamedQuery(name = "Room.findByFloor", query = "SELECT r FROM Room r WHERE r.floor = :floor"),
    @NamedQuery(name = "Room.findUnused", query = "SELECT r FROM Room r WHERE r.productId.productId = :productId AND r.roomNo NOT IN (SELECT rr.roomNo.roomNo FROM RoomReservation rr WHERE (rr.entryDate < :entryDate AND :entryDate < rr.exitDate) OR (rr.entryDate < :exitDate AND :exitDate < rr.exitDate))")})
public class Room implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "room_no")
    private String roomNo;
    @Column(name = "room_name")
    private String roomName;
    @Basic(optional = false)
    @Column(name = "floor")
    private int floor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomNo")
    private Collection<RoomReservation> roomReservationCollection;
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne(optional = false)
    private Accomodation productId;

    public Room() {
    }

    public Room(String roomNo) {
        this.roomNo = roomNo;
    }

    public Room(String roomNo, int floor) {
        this.roomNo = roomNo;
        this.floor = floor;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Collection<RoomReservation> getRoomReservationCollection() {
        return roomReservationCollection;
    }

    public void setRoomReservationCollection(Collection<RoomReservation> roomReservationCollection) {
        this.roomReservationCollection = roomReservationCollection;
    }

    public Accomodation getProductId() {
        return productId;
    }

    public void setProductId(Accomodation productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomNo != null ? roomNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Room)) {
            return false;
        }
        Room other = (Room) object;
        if ((this.roomNo == null && other.roomNo != null) || (this.roomNo != null && !this.roomNo.equals(other.roomNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AvatarEntity.Room[roomNo=" + roomNo + "]";
    }

}
