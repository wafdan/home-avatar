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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author kamoe
 */
@Entity
@Table(name = "room_reservation")
@NamedQueries({
    @NamedQuery(name = "RoomReservation.findAll", query = "SELECT r FROM RoomReservation r"),
    @NamedQuery(name = "RoomReservation.findByReservationTime", query = "SELECT r FROM RoomReservation r WHERE r.reservationTime = :reservationTime"),
    @NamedQuery(name = "RoomReservation.findByProductId", query = "SELECT r FROM RoomReservation r WHERE r.productId = :productId"),
    @NamedQuery(name = "RoomReservation.findByEntryDate", query = "SELECT r FROM RoomReservation r WHERE r.entryDate = :entryDate"),
    @NamedQuery(name = "RoomReservation.findByExitDate", query = "SELECT r FROM RoomReservation r WHERE r.exitDate = :exitDate")})
public class RoomReservation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "reservation_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reservationTime;
    @Basic(optional = false)
    @Column(name = "product_id")
    private String productId;
    @Basic(optional = false)
    @Column(name = "entry_date")
    @Temporal(TemporalType.DATE)
    private Date entryDate;
    @Basic(optional = false)
    @Column(name = "exit_date")
    @Temporal(TemporalType.DATE)
    private Date exitDate;

    public RoomReservation() {
    }

    public RoomReservation(Date reservationTime) {
        this.reservationTime = reservationTime;
    }

    public RoomReservation(Date reservationTime, String productId, Date entryDate, Date exitDate) {
        this.reservationTime = reservationTime;
        this.productId = productId;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
    }

    public Date getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Date reservationTime) {
        this.reservationTime = reservationTime;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservationTime != null ? reservationTime.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoomReservation)) {
            return false;
        }
        RoomReservation other = (RoomReservation) object;
        if ((this.reservationTime == null && other.reservationTime != null) || (this.reservationTime != null && !this.reservationTime.equals(other.reservationTime))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AvatarEntity.RoomReservation[reservationTime=" + reservationTime + "]";
    }

}
