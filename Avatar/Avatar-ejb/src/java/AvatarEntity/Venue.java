/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AvatarEntity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author kamoe
 */
@Entity
@Table(name = "venue")
@NamedQueries({
    @NamedQuery(name = "Venue.findAll", query = "SELECT v FROM Venue v"),
    @NamedQuery(name = "Venue.findByVenueNo", query = "SELECT v FROM Venue v WHERE v.venueNo = :venueNo"),
    @NamedQuery(name = "Venue.findByVenueName", query = "SELECT v FROM Venue v WHERE v.venueName = :venueName"),
    @NamedQuery(name = "Venue.findByCapacity", query = "SELECT v FROM Venue v WHERE v.capacity = :capacity")})
public class Venue implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "venue_no")
    private String venueNo;
    @Column(name = "venue_name")
    private String venueName;
    @Basic(optional = false)
    @Column(name = "capacity")
    private int capacity;

    public Venue() {
    }

    public Venue(String venueNo) {
        this.venueNo = venueNo;
    }

    public Venue(String venueNo, int capacity) {
        this.venueNo = venueNo;
        this.capacity = capacity;
    }

    public String getVenueNo() {
        return venueNo;
    }

    public void setVenueNo(String venueNo) {
        this.venueNo = venueNo;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (venueNo != null ? venueNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venue)) {
            return false;
        }
        Venue other = (Venue) object;
        if ((this.venueNo == null && other.venueNo != null) || (this.venueNo != null && !this.venueNo.equals(other.venueNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AvatarEntity.Venue[venueNo=" + venueNo + "]";
    }

}
