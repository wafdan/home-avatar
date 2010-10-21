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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "reservation_item")
@NamedQueries({
    @NamedQuery(name = "ReservationItem.findAll", query = "SELECT r FROM ReservationItem r"),
    @NamedQuery(name = "ReservationItem.findByReservationItemId", query = "SELECT r FROM ReservationItem r WHERE r.reservationItemId = :reservationItemId"),
    @NamedQuery(name = "ReservationItem.findByReservationTime", query = "SELECT r FROM ReservationItem r WHERE r.reservationTime = :reservationTime"),
    @NamedQuery(name = "ReservationItem.findByPrice", query = "SELECT r FROM ReservationItem r WHERE r.price = :price"),
    @NamedQuery(name = "ReservationItem.findByDtype", query = "SELECT r FROM ReservationItem r WHERE r.dtype = :dtype")})
public class ReservationItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reservation_item_id")
    private Integer reservationItemId;
    @Basic(optional = false)
    @Column(name = "reservation_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reservationTime;
    @Basic(optional = false)
    @Column(name = "price")
    private double price;
    @Basic(optional = false)
    @Column(name = "DTYPE")
    private char dtype;
    @JoinColumn(name = "reservation_id", referencedColumnName = "reservation_id")
    @ManyToOne(optional = false)
    private Reservation reservationId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "reservationItem")
    private OtherServicesReservation otherServicesReservation;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "reservationItem")
    private RoomReservation roomReservation;

    public ReservationItem() {
    }

    public ReservationItem(Integer reservationItemId) {
        this.reservationItemId = reservationItemId;
    }

    public ReservationItem(Integer reservationItemId, Date reservationTime, double price, char dtype) {
        this.reservationItemId = reservationItemId;
        this.reservationTime = reservationTime;
        this.price = price;
        this.dtype = dtype;
    }

    public Integer getReservationItemId() {
        return reservationItemId;
    }

    public void setReservationItemId(Integer reservationItemId) {
        this.reservationItemId = reservationItemId;
    }

    public Date getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Date reservationTime) {
        this.reservationTime = reservationTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public char getDtype() {
        return dtype;
    }

    public void setDtype(char dtype) {
        this.dtype = dtype;
    }

    public Reservation getReservationId() {
        return reservationId;
    }

    public void setReservationId(Reservation reservationId) {
        this.reservationId = reservationId;
    }

    public OtherServicesReservation getOtherServicesReservation() {
        return otherServicesReservation;
    }

    public void setOtherServicesReservation(OtherServicesReservation otherServicesReservation) {
        this.otherServicesReservation = otherServicesReservation;
    }

    public RoomReservation getRoomReservation() {
        return roomReservation;
    }

    public void setRoomReservation(RoomReservation roomReservation) {
        this.roomReservation = roomReservation;
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
        if (!(object instanceof ReservationItem)) {
            return false;
        }
        ReservationItem other = (ReservationItem) object;
        if ((this.reservationItemId == null && other.reservationItemId != null) || (this.reservationItemId != null && !this.reservationItemId.equals(other.reservationItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AvatarEntity.ReservationItem[reservationItemId=" + reservationItemId + "]";
    }

}
