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
@Table(name = "hall_reservation")
@NamedQueries({
    @NamedQuery(name = "HallReservation.findAll", query = "SELECT h FROM HallReservation h"),
    @NamedQuery(name = "HallReservation.findByReservationItemId", query = "SELECT h FROM HallReservation h WHERE h.reservationItemId = :reservationItemId"),
    @NamedQuery(name = "HallReservation.findByBeginTime", query = "SELECT h FROM HallReservation h WHERE h.beginTime = :beginTime"),
    @NamedQuery(name = "HallReservation.findByEndTime", query = "SELECT h FROM HallReservation h WHERE h.endTime = :endTime"),
    @NamedQuery(name = "HallReservation.findByUseDate", query = "SELECT h FROM HallReservation h WHERE h.useDate = :useDate"),
    @NamedQuery(name = "HallReservation.findByAttendees", query = "SELECT h FROM HallReservation h WHERE h.attendees = :attendees")})
public class HallReservation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "reservation_item_id")
    private Integer reservationItemId;
    @Basic(optional = false)
    @Column(name = "begin_time")
    @Temporal(TemporalType.TIME)
    private Date beginTime;
    @Basic(optional = false)
    @Column(name = "end_time")
    @Temporal(TemporalType.TIME)
    private Date endTime;
    @Basic(optional = false)
    @Column(name = "use_date")
    @Temporal(TemporalType.DATE)
    private Date useDate;
    @Basic(optional = false)
    @Column(name = "attendees")
    private int attendees;
    @JoinColumn(name = "venue_no", referencedColumnName = "venue_no")
    @ManyToOne
    private Venue venueNo;
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne(optional = false)
    private Hall productId;
    @JoinColumn(name = "reservation_item_id", referencedColumnName = "reservation_item_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private RoomReservation roomReservation;

    public HallReservation() {
    }

    public HallReservation(Integer reservationItemId) {
        this.reservationItemId = reservationItemId;
    }

    public HallReservation(Integer reservationItemId, Date beginTime, Date endTime, Date useDate, int attendees) {
        this.reservationItemId = reservationItemId;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.useDate = useDate;
        this.attendees = attendees;
    }

    public Integer getReservationItemId() {
        return reservationItemId;
    }

    public void setReservationItemId(Integer reservationItemId) {
        this.reservationItemId = reservationItemId;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getUseDate() {
        return useDate;
    }

    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }

    public int getAttendees() {
        return attendees;
    }

    public void setAttendees(int attendees) {
        this.attendees = attendees;
    }

    public Venue getVenueNo() {
        return venueNo;
    }

    public void setVenueNo(Venue venueNo) {
        this.venueNo = venueNo;
    }

    public Hall getProductId() {
        return productId;
    }

    public void setProductId(Hall productId) {
        this.productId = productId;
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
        if (!(object instanceof HallReservation)) {
            return false;
        }
        HallReservation other = (HallReservation) object;
        if ((this.reservationItemId == null && other.reservationItemId != null) || (this.reservationItemId != null && !this.reservationItemId.equals(other.reservationItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AvatarEntity.HallReservation[reservationItemId=" + reservationItemId + "]";
    }

}
