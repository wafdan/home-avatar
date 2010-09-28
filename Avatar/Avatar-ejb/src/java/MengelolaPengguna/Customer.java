/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MengelolaPengguna;

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
@Table(name = "customer")
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findByUsername", query = "SELECT c FROM Customer c WHERE c.username = :username"),
    @NamedQuery(name = "Customer.findByIdentityType", query = "SELECT c FROM Customer c WHERE c.identityType = :identityType"),
    @NamedQuery(name = "Customer.findByIdentityNumber", query = "SELECT c FROM Customer c WHERE c.identityNumber = :identityNumber"),
    @NamedQuery(name = "Customer.findByAddress1", query = "SELECT c FROM Customer c WHERE c.address1 = :address1"),
    @NamedQuery(name = "Customer.findByAddress2", query = "SELECT c FROM Customer c WHERE c.address2 = :address2"),
    @NamedQuery(name = "Customer.findByCityId", query = "SELECT c FROM Customer c WHERE c.cityId = :cityId"),
    @NamedQuery(name = "Customer.findByTelephone", query = "SELECT c FROM Customer c WHERE c.telephone = :telephone")})
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "identity_type")
    private String identityType;
    @Basic(optional = false)
    @Column(name = "identity_number")
    private String identityNumber;
    @Basic(optional = false)
    @Column(name = "address1")
    private String address1;
    @Column(name = "address2")
    private String address2;
    @Basic(optional = false)
    @Column(name = "city_id")
    private int cityId;
    @Column(name = "telephone")
    private String telephone;

    public Customer() {
    }

    public Customer(String username) {
        this.username = username;
    }

    public Customer(String username, String identityType, String identityNumber, String address1, int cityId) {
        this.username = username;
        this.identityType = identityType;
        this.identityNumber = identityNumber;
        this.address1 = address1;
        this.cityId = cityId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MengelolaPengguna.Customer[username=" + username + "]";
    }

}
