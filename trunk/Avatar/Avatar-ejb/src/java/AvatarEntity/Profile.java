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
    @NamedQuery(name = "Profile.findById", query = "SELECT p FROM Profile p WHERE p.id = :id"),
    @NamedQuery(name = "Profile.findByHotelName", query = "SELECT p FROM Profile p WHERE p.hotelName = :hotelName"),
    @NamedQuery(name = "Profile.findByHotelAddress1", query = "SELECT p FROM Profile p WHERE p.hotelAddress1 = :hotelAddress1"),
    @NamedQuery(name = "Profile.findByHotelAddress2", query = "SELECT p FROM Profile p WHERE p.hotelAddress2 = :hotelAddress2"),
    @NamedQuery(name = "Profile.findByHotelCity", query = "SELECT p FROM Profile p WHERE p.hotelCity = :hotelCity"),
    @NamedQuery(name = "Profile.findByHotelCountry", query = "SELECT p FROM Profile p WHERE p.hotelCountry = :hotelCountry"),
    @NamedQuery(name = "Profile.findByHotelPhone", query = "SELECT p FROM Profile p WHERE p.hotelPhone = :hotelPhone"),
    @NamedQuery(name = "Profile.findByHotelFax", query = "SELECT p FROM Profile p WHERE p.hotelFax = :hotelFax"),
    @NamedQuery(name = "Profile.findByAccountNumber1", query = "SELECT p FROM Profile p WHERE p.accountNumber1 = :accountNumber1"),
    @NamedQuery(name = "Profile.findByBankName1", query = "SELECT p FROM Profile p WHERE p.bankName1 = :bankName1"),
    @NamedQuery(name = "Profile.findByAccountName1", query = "SELECT p FROM Profile p WHERE p.accountName1 = :accountName1"),
    @NamedQuery(name = "Profile.findByAccountNumber2", query = "SELECT p FROM Profile p WHERE p.accountNumber2 = :accountNumber2"),
    @NamedQuery(name = "Profile.findByBankName2", query = "SELECT p FROM Profile p WHERE p.bankName2 = :bankName2"),
    @NamedQuery(name = "Profile.findByAccountName2", query = "SELECT p FROM Profile p WHERE p.accountName2 = :accountName2"),
    @NamedQuery(name = "Profile.findByPaylimitConstant", query = "SELECT p FROM Profile p WHERE p.paylimitConstant = :paylimitConstant")})
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
    @Lob
    @Column(name = "hotel_logo")
    private String hotelLogo;
    @Basic(optional = false)
    @Column(name = "hotel_phone")
    private String hotelPhone;
    @Column(name = "hotel_fax")
    private String hotelFax;
    @Basic(optional = false)
    @Column(name = "account_number1")
    private String accountNumber1;
    @Basic(optional = false)
    @Column(name = "bank_name1")
    private String bankName1;
    @Basic(optional = false)
    @Column(name = "account_name1")
    private String accountName1;
    @Column(name = "account_number2")
    private String accountNumber2;
    @Column(name = "bank_name2")
    private String bankName2;
    @Column(name = "account_name2")
    private String accountName2;
    @Basic(optional = false)
    @Column(name = "paylimit_constant")
    private int paylimitConstant;

    public Profile() {
    }

    public Profile(Boolean id) {
        this.id = id;
    }

    public Profile(Boolean id, String hotelName, String hotelAddress1, String hotelCity, String hotelCountry, String hotelPhone, String accountNumber1, String bankName1, String accountName1, int paylimitConstant) {
        this.id = id;
        this.hotelName = hotelName;
        this.hotelAddress1 = hotelAddress1;
        this.hotelCity = hotelCity;
        this.hotelCountry = hotelCountry;
        this.hotelPhone = hotelPhone;
        this.accountNumber1 = accountNumber1;
        this.bankName1 = bankName1;
        this.accountName1 = accountName1;
        this.paylimitConstant = paylimitConstant;
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

    public String getHotelLogo() {
        return hotelLogo;
    }

    public void setHotelLogo(String hotelLogo) {
        this.hotelLogo = hotelLogo;
    }

    public String getHotelPhone() {
        return hotelPhone;
    }

    public void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }

    public String getHotelFax() {
        return hotelFax;
    }

    public void setHotelFax(String hotelFax) {
        this.hotelFax = hotelFax;
    }

    public String getAccountNumber1() {
        return accountNumber1;
    }

    public void setAccountNumber1(String accountNumber1) {
        this.accountNumber1 = accountNumber1;
    }

    public String getBankName1() {
        return bankName1;
    }

    public void setBankName1(String bankName1) {
        this.bankName1 = bankName1;
    }

    public String getAccountName1() {
        return accountName1;
    }

    public void setAccountName1(String accountName1) {
        this.accountName1 = accountName1;
    }

    public String getAccountNumber2() {
        return accountNumber2;
    }

    public void setAccountNumber2(String accountNumber2) {
        this.accountNumber2 = accountNumber2;
    }

    public String getBankName2() {
        return bankName2;
    }

    public void setBankName2(String bankName2) {
        this.bankName2 = bankName2;
    }

    public String getAccountName2() {
        return accountName2;
    }

    public void setAccountName2(String accountName2) {
        this.accountName2 = accountName2;
    }

    public int getPaylimitConstant() {
        return paylimitConstant;
    }

    public void setPaylimitConstant(int paylimitConstant) {
        this.paylimitConstant = paylimitConstant;
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
