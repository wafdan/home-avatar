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
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author kamoe
 */
@Entity
@Table(name = "hall_reservation")
@SecondaryTable(name = "reservation")
@NamedQueries({
    @NamedQuery(name = "HallReservation.findAll", query = "SELECT h FROM HallReservation h"),
    @NamedQuery(name = "HallReservation.findByReservationTime", query = "SELECT h FROM HallReservation h WHERE h.reservationTime = :reservationTime"),
    @NamedQuery(name = "HallReservation.findByProductId", query = "SELECT h FROM HallReservation h WHERE h.productId = :productId"),
    @NamedQuery(name = "HallReservation.findByBeginTime", query = "SELECT h FROM HallReservation h WHERE h.beginTime = :beginTime"),
    @NamedQuery(name = "HallReservation.findByEndTime", query = "SELECT h FROM HallReservation h WHERE h.endTime = :endTime"),
    @NamedQuery(name = "HallReservation.findByUseDate", query = "SELECT h FROM HallReservation h WHERE h.useDate = :useDate"),
    @NamedQuery(name = "HallReservation.findByAttendees", query = "SELECT h FROM HallReservation h WHERE h.attendees = :attendees"),
    @NamedQuery(name = "HallReservation.findByVenueNo", query = "SELECT h FROM HallReservation h WHERE h.venueNo = :venueNo")})
public class HallReservation implements Serializable {
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
    @Column(name = "venue_no")
    private String venueNo;
    @Basic(optional = false)
    @Column(table = "reservation", name = "username")
    private String username;
    @Column(table = "reservation", name = "sta_username")
    private String staUsername;

    public HallReservation() {
    }

    public HallReservation(Date reservationTime) {
        this.reservationTime = reservationTime;
    }

    public HallReservation(Date reservationTime, String productId, Date beginTime, Date endTime, Date useDate, int attendees, String username) {
        this.reservationTime = reservationTime;
        this.productId = productId;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.useDate = useDate;
        this.attendees = attendees;
        this.username = username;
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

    public String getVenueNo() {
        return venueNo;
    }

    public void setVenueNo(String venueNo) {
        this.venueNo = venueNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStaUsername() {
        return staUsername;
    }

    public void setStaUsername(String staUsername) {
        this.staUsername = staUsername;
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
        if (!(object instanceof HallReservation)) {
            return false;
        }
        HallReservation other = (HallReservation) object;
        if ((this.reservationTime == null && other.reservationTime != null) || (this.reservationTime != null && !this.reservationTime.equals(other.reservationTime))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AvatarEntity.HallReservation[reservationTime=" + reservationTime + "]";
    }

}
