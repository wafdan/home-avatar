/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AvatarEntity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Christian
 */
@Entity
@Table(name = "room_usage")
@IdClass(RoomUsagePK.class)
@NamedQueries({
    @NamedQuery(name = "RoomUsage.findAll", query = "SELECT r FROM RoomUsage r"),
    @NamedQuery(name = "RoomUsage.findByReservationTime", query = "SELECT r FROM RoomUsage r WHERE r.roomUsagePK.reservationTime = :reservationTime"),
    @NamedQuery(name = "RoomUsage.findByRoomNo", query = "SELECT r FROM RoomUsage r WHERE r.roomUsagePK.roomNo = :roomNo"),
    @NamedQuery(name = "RoomUsage.findByActualEntry", query = "SELECT r FROM RoomUsage r WHERE r.actualEntry = :actualEntry"),
    @NamedQuery(name = "RoomUsage.findByActualExit", query = "SELECT r FROM RoomUsage r WHERE r.actualExit = :actualExit")})
public class RoomUsage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "reservation_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reservationTime;
    @Id
    @Basic(optional = false)
    @Column(name = "room_no")
    private String roomNo;
    @Column(name = "actual_entry")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualEntry;
    @Column(name = "actual_exit")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualExit;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "reservation_time", referencedColumnName="reservation_time")
    private RoomReservation roomReservation;
    @ManyToOne
    @PrimaryKeyJoinColumn(name="room_no", referencedColumnName="room_no")
    private Room room;

    public RoomUsage() {
    }

    public RoomUsage(Date reservationTime, String roomNo) {
        this.reservationTime = reservationTime;
        this.roomNo = roomNo;
    }

    public Date getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Date reservationTime) {
        this.reservationTime = reservationTime;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public Date getActualEntry() {
        return actualEntry;
    }

    public void setActualEntry(Date actualEntry) {
        this.actualEntry = actualEntry;
    }

    public Date getActualExit() {
        return actualExit;
    }

    public void setActualExit(Date actualExit) {
        this.actualExit = actualExit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservationTime != null ? reservationTime.hashCode() : 0);
        hash += (roomNo != null ? roomNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RoomUsage)) {
            return false;
        }
        RoomUsage other = (RoomUsage) object;
        if ((this.reservationTime == null && other.reservationTime != null) || (this.reservationTime != null && !this.reservationTime.equals(other.reservationTime))) {
            return false;
        }
        if ((this.roomNo == null && other.roomNo != null) || (this.roomNo != null && !this.roomNo.equals(other.roomNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AvatarEntity.RoomUsagePK[reservationTime=" + reservationTime + ", roomNo=" + roomNo + "]";
    }

}
