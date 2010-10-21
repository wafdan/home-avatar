/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AvatarEntity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Christian
 */
public class VenueLayoutPK implements Serializable {
    private String venueNo;
    private Integer layoutNo;

    public VenueLayoutPK() {
    }

    public VenueLayoutPK(String venueNo, Integer layoutNo) {
        this.venueNo = venueNo;
        this.layoutNo = layoutNo;
    }

    public String getVenueNo() {
        return venueNo;
    }

    public void setVenueNo(String venueNo) {
        this.venueNo = venueNo;
    }

    public int getLayoutNo() {
        return layoutNo;
    }

    public void setLayoutNo(int layoutNo) {
        this.layoutNo = layoutNo;
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
        if (!(object instanceof VenueLayoutPK)) {
            return false;
        }
        VenueLayoutPK other = (VenueLayoutPK) object;
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
