/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AvatarEntity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Christian
 */
@Entity
@Table(name = "other_services_reservation")
@DiscriminatorValue("O")
@NamedQueries({
    @NamedQuery(name = "OtherServicesReservation.findAll", query = "SELECT o FROM OtherServicesReservation o"),
    @NamedQuery(name = "OtherServicesReservation.findByReservationItemId", query = "SELECT o FROM OtherServicesReservation o WHERE o.reservationItemId = :reservationItemId"),
    @NamedQuery(name = "OtherServicesReservation.findByPeriod", query = "SELECT o FROM OtherServicesReservation o WHERE o.reservationTime BETWEEN :from AND :to")})
public class OtherServicesReservation extends ReservationItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "amount")
    private int amount;
    @Basic(optional = false)
    @Lob
    @Column(name = "note")
    private String note;
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne(optional = false)
    private OtherServices productId;

    public OtherServicesReservation() {
    }

    public OtherServicesReservation(Integer reservationItemId) {
        this.reservationItemId = reservationItemId;
    }

    public OtherServicesReservation(Integer reservationItemId, Date reservationTime, double price, int amount, String note) {
        super(reservationItemId, reservationTime, price);
        this.amount = amount;
        this.note = note;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public OtherServices getProductId() {
        return productId;
    }

    public void setProductId(OtherServices productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservationItemId != null ? reservationItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OtherServicesReservation)) {
            return false;
        }
        OtherServicesReservation other = (OtherServicesReservation) object;
        if ((this.reservationItemId == null && other.reservationItemId != null) || (this.reservationItemId != null && !this.reservationItemId.equals(other.reservationItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AvatarEntity.OtherServicesReservation[reservationItemId=" + reservationItemId + "]";
    }

    @Override
    public Date getPaymentLimit() {
        return null;
    }

    @Override
    public String getDescription() {
        return this.productId.getProductType() + " @" + this.getAmount() + " " + this.productId.getPricingUnit();
    }

    @Override
    public String getDetails() {
        return null;
    }
}
