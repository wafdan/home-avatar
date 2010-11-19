/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AvatarEntity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Christian
 */
@Entity
@Table(name = "venue")
@NamedQueries({
    @NamedQuery(name = "Venue.findAll", query = "SELECT v FROM Venue v"),
    @NamedQuery(name = "Venue.findByVenueNo", query = "SELECT v FROM Venue v WHERE v.venueNo = :venueNo"),
    @NamedQuery(name = "Venue.findByVenueName", query = "SELECT v FROM Venue v WHERE v.venueName = :venueName"),
    @NamedQuery(name = "Venue.findUnused", query = "SELECT v FROM Venue v WHERE v.venueNo NOT IN (SELECT h.venueNo.venueNo FROM HallReservation h WHERE h.useDate = :useDate) AND v.venueNo IN (SELECT ve.venueNo FROM VenueLayout ve WHERE ve.layoutNo = :layoutNo AND ve.capacity >= :capacity)"),
    @NamedQuery(name = "Venue.findUnusedEx", query = "SELECT v FROM Venue v WHERE v.venueNo NOT IN (SELECT h.venueNo.venueNo FROM HallReservation h WHERE h.reservationItemId <> :reservationItemId AND h.useDate = :useDate) AND v.venueNo IN (SELECT ve.venueNo FROM VenueLayout ve WHERE ve.layoutNo = :layoutNo AND ve.capacity >= :capacity)")})
public class Venue implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "venue_no")
    private String venueNo;
    @Basic(optional = false)
    @Column(name = "venue_name")
    private String venueName;
    @Lob
    @Column(name = "description")
    private String description;
    @Lob
    @Column(name = "image")
    private String image;
    @OneToMany(mappedBy = "venueNo")
    private Collection<HallReservation> hallReservationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venue")
    private Collection<VenueLayout> venueLayoutCollection;

    public Venue() {
    }

    public Venue(String venueNo) {
        this.venueNo = venueNo;
    }

    public Venue(String venueNo, String venueName) {
        this.venueNo = venueNo;
        this.venueName = venueName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Collection<HallReservation> getHallReservationCollection() {
        return hallReservationCollection;
    }

    public void setHallReservationCollection(Collection<HallReservation> hallReservationCollection) {
        this.hallReservationCollection = hallReservationCollection;
    }

    public Collection<VenueLayout> getVenueLayoutCollection() {
        return venueLayoutCollection;
    }

    public void setVenueLayoutCollection(Collection<VenueLayout> venueLayoutCollection) {
        this.venueLayoutCollection = venueLayoutCollection;
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
