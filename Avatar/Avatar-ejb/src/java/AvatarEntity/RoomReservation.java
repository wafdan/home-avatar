/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AvatarEntity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Christian
 */
@Entity
@Table(name = "room_reservation")
@NamedQueries({
    @NamedQuery(name = "RoomReservation.findAll", query = "SELECT r FROM RoomReservation r"),
    @NamedQuery(name = "RoomReservation.findByReservationItemId", query = "SELECT r FROM RoomReservation r WHERE r.reservationItemId = :reservationItemId"),
    @NamedQuery(name = "RoomReservation.findByEntryDate", query = "SELECT r FROM RoomReservation r WHERE r.entryDate = :entryDate"),
    @NamedQuery(name = "RoomReservation.findByExitDate", query = "SELECT r FROM RoomReservation r WHERE r.exitDate = :exitDate"),
    @NamedQuery(name = "RoomReservation.findByActualEntry", query = "SELECT r FROM RoomReservation r WHERE r.actualEntry = :actualEntry"),
    @NamedQuery(name = "RoomReservation.findByActualExit", query = "SELECT r FROM RoomReservation r WHERE r.actualExit = :actualExit")})
public class RoomReservation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "reservation_item_id")
    private Integer reservationItemId;
    @Basic(optional = false)
    @Column(name = "entry_date")
    @Temporal(TemporalType.DATE)
    private Date entryDate;
    @Basic(optional = false)
    @Column(name = "exit_date")
    @Temporal(TemporalType.DATE)
    private Date exitDate;
    @Column(name = "actual_entry")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualEntry;
    @Column(name = "actual_exit")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualExit;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "roomReservation")
    private HallReservation hallReservation;
    @JoinColumn(name = "reservation_item_id", referencedColumnName = "reservation_item_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private ReservationItem reservationItem;
    @JoinColumn(name = "room_no", referencedColumnName = "room_no")
    @ManyToOne(optional = false)
    private Room roomNo;

    public RoomReservation() {
    }

    public RoomReservation(Integer reservationItemId) {
        this.reservationItemId = reservationItemId;
    }

    public RoomReservation(Integer reservationItemId, Date entryDate, Date exitDate) {
        this.reservationItemId = reservationItemId;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
    }

    public Integer getReservationItemId() {
        return reservationItemId;
    }

    public void setReservationItemId(Integer reservationItemId) {
        this.reservationItemId = reservationItemId;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getExitDate() {
        return exitDate;
    }

    public void setExitDate(Date exitDate) {
        this.exitDate = exitDate;
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

    public HallReservation getHallReservation() {
        return hallReservation;
    }

    public void setHallReservation(HallReservation hallReservation) {
        this.hallReservation = hallReservation;
    }

    public ReservationItem getReservationItem() {
        return reservationItem;
    }

    public void setReservationItem(ReservationItem reservationItem) {
        this.reservationItem = reservationItem;
    }

    public Room getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Room roomNo) {
        this.roomNo = roomNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservationItemId != null ? reservationItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoomReservation)) {
            return false;
        }
        RoomReservation other = (RoomReservation) object;
        if ((this.reservationItemId == null && other.reservationItemId != null) || (this.reservationItemId != null && !this.reservationItemId.equals(other.reservationItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AvatarEntity.RoomReservation[reservationItemId=" + reservationItemId + "]";
    }

}
