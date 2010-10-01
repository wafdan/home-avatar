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
@Table(name = "reservation")
@NamedQueries({
    @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r"),
    @NamedQuery(name = "Reservation.findByReservationTime", query = "SELECT r FROM Reservation r WHERE r.reservationTime = :reservationTime"),
    @NamedQuery(name = "Reservation.findByUsername", query = "SELECT r FROM Reservation r WHERE r.username = :username"),
    @NamedQuery(name = "Reservation.findByStaUsername", query = "SELECT r FROM Reservation r WHERE r.staUsername = :staUsername")})
public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "reservation_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reservationTime;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Column(name = "sta_username")
    private String staUsername;

    public Reservation() {
    }

    public Reservation(Date reservationTime) {
        this.reservationTime = reservationTime;
    }

    public Reservation(Date reservationTime, String username) {
        this.reservationTime = reservationTime;
        this.username = username;
    }

    public Date getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Date reservationTime) {
        this.reservationTime = reservationTime;
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
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.reservationTime == null && other.reservationTime != null) || (this.reservationTime != null && !this.reservationTime.equals(other.reservationTime))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AvatarEntity.Reservation[reservationTime=" + reservationTime + "]";
    }

}
