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
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Christian
 */
@Entity
@Table(name = "venue_layout")
@IdClass(VenueLayoutPK.class)
@NamedQueries({
    @NamedQuery(name = "VenueLayout.findAll", query = "SELECT v FROM VenueLayout v"),
    @NamedQuery(name = "VenueLayout.findByVenueNo", query = "SELECT v FROM VenueLayout v WHERE v.venueNo = :venueNo"),
    @NamedQuery(name = "VenueLayout.findByLayoutNo", query = "SELECT v FROM VenueLayout v WHERE v.layoutNo = :layoutNo"),
    @NamedQuery(name = "VenueLayout.findByCapacity", query = "SELECT v FROM VenueLayout v WHERE v.capacity = :capacity")})
public class VenueLayout implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "venue_no")
    private String venueNo;
    @Id
    @Basic(optional = false)
    @Column(name = "layout_no")
    private Integer layoutNo;
    @Basic(optional = false)
    @Column(name = "capacity")
    private int capacity;
    @JoinColumn(name = "venue_no", referencedColumnName = "venue_no", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Venue venue;
    @JoinColumn(name = "layout_no", referencedColumnName = "layout_no", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Layout layout;

    public VenueLayout() {
    }

    public VenueLayout(String venueNo, int layoutNo) {
        this.venueNo = venueNo;
        this.layoutNo = layoutNo;
    }

    public VenueLayout(String venueNo, int layoutNo, int capacity) {
        this.venueNo = venueNo;
        this.layoutNo = layoutNo;
        this.capacity = capacity;
    }

    public String getVenueNo() {
        return venueNo;
    }

    public void setVenueNo(String venueNo) {
        this.venueNo = venueNo;
    }

    public Integer getLayoutNo() {
        return layoutNo;
    }

    public void setLayoutNo(int layoutNo) {
        this.layoutNo = layoutNo;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (venueNo != null ? venueNo.hashCode() : 0);
        hash += (int) layoutNo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VenueLayout)) {
            return false;
        }
        VenueLayout other = (VenueLayout) object;
        if ((this.venueNo == null && other.venueNo != null) || (this.venueNo != null && !this.venueNo.equals(other.venueNo))) {
            return false;
        }
        if (this.layoutNo != other.layoutNo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AvatarEntity.VenueLayoutPK[venueNo=" + venueNo + ", layoutNo=" + layoutNo + "]";
    }

}
