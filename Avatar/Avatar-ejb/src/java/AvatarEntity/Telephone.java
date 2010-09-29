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
 * @author Christian
 */
@Entity
@Table(name = "telephone")
@NamedQueries({
    @NamedQuery(name = "Telephone.findAll", query = "SELECT t FROM Telephone t"),
    @NamedQuery(name = "Telephone.findByPhoneType", query = "SELECT t FROM Telephone t WHERE t.phoneType = :phoneType"),
    @NamedQuery(name = "Telephone.findByPhoneNumber", query = "SELECT t FROM Telephone t WHERE t.phoneNumber = :phoneNumber")})
public class Telephone implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "phone_type")
    private String phoneType;
    @Id
    @Basic(optional = false)
    @Column(name = "phone_number")
    private String phoneNumber;

    public Telephone() {
    }

    public Telephone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Telephone(String phoneNumber, String phoneType) {
        this.phoneNumber = phoneNumber;
        this.phoneType = phoneType;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Telephone)) {
            return false;
        }
        Telephone other = (Telephone) object;
        if ((this.phoneNumber == null && other.phoneNumber != null) || (this.phoneNumber != null && !this.phoneNumber.equals(other.phoneNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AvatarEntity.Telephone[phoneNumber=" + phoneNumber + "]";
    }

}
