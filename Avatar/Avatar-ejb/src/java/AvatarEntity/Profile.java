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
 * @author zulfikar
 */
@Entity
@Table(name = "profile")
@NamedQueries({
    @NamedQuery(name = "Profile.findAll", query = "SELECT p FROM Profile p"),
    @NamedQuery(name = "Profile.findById", query = "SELECT p FROM Profile p WHERE p.id = :id"),
    @NamedQuery(name = "Profile.findByHotelName", query = "SELECT p FROM Profile p WHERE p.hotelName = :hotelName"),
    @NamedQuery(name = "Profile.findByHotelAddress1", query = "SELECT p FROM Profile p WHERE p.hotelAddress1 = :hotelAddress1"),
    @NamedQuery(name = "Profile.findByHotelAddress2", query = "SELECT p FROM Profile p WHERE p.hotelAddress2 = :hotelAddress2"),
    @NamedQuery(name = "Profile.findByHotelCity", query = "SELECT p FROM Profile p WHERE p.hotelCity = :hotelCity"),
    @NamedQuery(name = "Profile.findByHotelCountry", query = "SELECT p FROM Profile p WHERE p.hotelCountry = :hotelCountry"),
    @NamedQuery(name = "Profile.findByHotelPhone", query = "SELECT p FROM Profile p WHERE p.hotelPhone = :hotelPhone")})
public class Profile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Boolean id;
    @Basic(optional = false)
    @Column(name = "hotel_name")
    private String hotelName;
    @Basic(optional = false)
    @Column(name = "hotel_address1")
    private String hotelAddress1;
    @Column(name = "hotel_address2")
    private String hotelAddress2;
    @Basic(optional = false)
    @Column(name = "hotel_city")
    private String hotelCity;
    @Basic(optional = false)
    @Column(name = "hotel_country")
    private String hotelCountry;
    @Lob
    @Column(name = "hotel_email")
    private String hotelEmail;
    @Lob
    @Column(name = "hotel_description")
    private String hotelDescription;
    @Basic(optional = false)
    @Column(name = "hotel_phone")
    private String hotelPhone;

    public Profile() {
    }

    public Profile(Boolean id) {
        this.id = id;
    }

    public Profile(Boolean id, String hotelName, String hotelAddress1, String hotelCity, String hotelCountry, String hotelPhone) {
        this.id = id;
        this.hotelName = hotelName;
        this.hotelAddress1 = hotelAddress1;
        this.hotelCity = hotelCity;
        this.hotelCountry = hotelCountry;
        this.hotelPhone = hotelPhone;
    }

    public Boolean getId() {
        return id;
    }

    public void setId(Boolean id) {
        this.id = id;
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

    public String getHotelCity() {
        return hotelCity;
    }

    public void setHotelCity(String hotelCity) {
        this.hotelCity = hotelCity;
    }

    public String getHotelCountry() {
        return hotelCountry;
    }

    public void setHotelCountry(String hotelCountry) {
        this.hotelCountry = hotelCountry;
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

    public String getHotelPhone() {
        return hotelPhone;
    }

    public void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profile)) {
            return false;
        }
        Profile other = (Profile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AvatarEntity.Profile[id=" + id + "]";
    }

}
