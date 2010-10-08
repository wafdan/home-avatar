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
 * @author zulfikar
 */
@Entity
@Table(name = "hotel_info")
@NamedQueries({
    @NamedQuery(name = "HotelInfo.findAll", query = "SELECT h FROM HotelInfo h"),
    @NamedQuery(name = "HotelInfo.findById", query = "SELECT h FROM HotelInfo h WHERE h.id = :id"),
    @NamedQuery(name = "HotelInfo.findByHotelname", query = "SELECT h FROM HotelInfo h WHERE h.hotelname = :hotelname"),
    @NamedQuery(name = "HotelInfo.findByDescription", query = "SELECT h FROM HotelInfo h WHERE h.description = :description"),
    @NamedQuery(name = "HotelInfo.findByLogourl", query = "SELECT h FROM HotelInfo h WHERE h.logourl = :logourl")})
public class HotelInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @Column(name = "hotelname")
    private String hotelname;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "logourl")
    private String logourl;

    public HotelInfo() {
    }

    public HotelInfo(Short id) {
        this.id = id;
    }

    public HotelInfo(Short id, String hotelname, String description, String logourl) {
        this.id = id;
        this.hotelname = hotelname;
        this.description = description;
        this.logourl = logourl;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getHotelname() {
        return hotelname;
    }

    public void setHotelname(String hotelname) {
        this.hotelname = hotelname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogourl() {
        return logourl;
    }

    public void setLogourl(String logourl) {
        this.logourl = logourl;
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
        if (!(object instanceof HotelInfo)) {
            return false;
        }
        HotelInfo other = (HotelInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AvatarEntity.HotelInfo[id=" + id + "]";
    }

}
