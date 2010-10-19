/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AvatarEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Christian
 */
@Entity
@Table(name = "room_reservation")
@SecondaryTable(name = "reservation")
@NamedQueries({
    @NamedQuery(name = "RoomReservation.findAll", query = "SELECT r FROM RoomReservation r"),
    @NamedQuery(name = "RoomReservation.findByReservationTime", query = "SELECT r FROM RoomReservation r WHERE r.reservationTime = :reservationTime"),
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
    @Column(name = "entry_date")
    @Temporal(TemporalType.DATE)
    private Date entryDate;
    @Basic(optional = false)
    @Column(name = "exit_date")
    @Temporal(TemporalType.DATE)
    private Date exitDate;
    @OneToMany(mappedBy = "...")
    private List<RoomUsage> roomUsages;
    @Basic(optional = false)
    @Column(table = "reservation", name = "username")
    private String username;
    @Column(table = "reservation", name = "sta_username")
    private String staUsername;

    public RoomReservation() {
    }

    public RoomReservation(Date reservationTime) {
        this.reservationTime = reservationTime;
    }

    public RoomReservation(Date reservationTime, Date entryDate, Date exitDate, String username) {
        this.reservationTime = reservationTime;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        this.username = username;
    }

    public Date getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Date reservationTime) {
        this.reservationTime = reservationTime;
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
