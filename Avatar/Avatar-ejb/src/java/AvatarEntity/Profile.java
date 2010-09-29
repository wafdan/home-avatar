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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Christian
 */
@Entity
@Table(name = "profile")
@NamedQueries({
    @NamedQuery(name = "Profile.findAll", query = "SELECT p FROM Profile p"),
    @NamedQuery(name = "Profile.findByHotelName", query = "SELECT p FROM Profile p WHERE p.hotelName = :hotelName"),
    @NamedQuery(name = "Profile.findByHotelAddress1", query = "SELECT p FROM Profile p WHERE p.hotelAddress1 = :hotelAddress1"),
    @NamedQuery(name = "Profile.findByHotelAddress2", query = "SELECT p FROM Profile p WHERE p.hotelAddress2 = :hotelAddress2")})
public class Profile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "hotel_name")
    private String hotelName;
    @Basic(optional = false)
    @Column(name = "hotel_address1")
    private String hotelAddress1;
    @Column(name = "hotel_address2")
    private String hotelAddress2;
    @Lob
    @Column(name = "hotel_email")
    private String hotelEmail;
    @Lob
    @Column(name = "hotel_description")
    private String hotelDescription;
    @Lob
    @Column(name = "hotel_logo")
    private String hotelLogo;

    public Profile() {
    }

    public Profile(String hotelName) {
        this.hotelName = hotelName;
    }

    public Profile(String hotelName, String hotelAddress1) {
        this.hotelName = hotelName;
        this.hotelAddress1 = hotelAddress1;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress1() {
        return hotelAddress1;
    }

    public void setHotelAddress1(String hotelAddress1) {
        this.hotelAddress1 = hotelAddress1;
    }

    public String getHotelAddress2() {
        return hotelAddress2;
    }

    public void setHotelAddress2(String hotelAddress2) {
        this.hotelAddress2 = hotelAddress2;
    }

    public String getHotelEmail() {
        return hotelEmail;
    }

    public void setHotelEmail(String hotelEmail) {
        this.hotelEmail = hotelEmail;
    }

    public String getHotelDescription() {
        return hotelDescription;
    }

    public void setHotelDescription(String hotelDescription) {
        this.hotelDescription = hotelDescription;
    }

    public String getHotelLogo() {
        return hotelLogo;
    }

    public void setHotelLogo(String hotelLogo) {
        this.hotelLogo = hotelLogo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hotelName != null ? hotelName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profile)) {
            return false;
        }
        Profile other = (Profile) object;
        if ((this.hotelName == null && other.hotelName != null) || (this.hotelName != null && !this.hotelName.equals(other.hotelName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AvatarEntity.Profile[hotelName=" + hotelName + "]";
    }

}
