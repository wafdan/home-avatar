/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AvatarEntity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.IdClass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Christian
 */
public class RoomUsagePK implements Serializable {
    /*@Basic(optional = false)
    @Column(name = "reservation_time")
    @Temporal(TemporalType.TIMESTAMP)*/
    private Date reservationTime;
    /*@Basic(optional = false)
    @Column(name = "room_no")*/
    private String roomNo;

    public RoomUsagePK() {
    }

    public RoomUsagePK(Date reservationTime, String roomNo) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservationTime != null ? reservationTime.hashCode() : 0);
        hash += (roomNo != null ? roomNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoomUsagePK)) {
            return false;
        }
        RoomUsagePK other = (RoomUsagePK) object;
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
