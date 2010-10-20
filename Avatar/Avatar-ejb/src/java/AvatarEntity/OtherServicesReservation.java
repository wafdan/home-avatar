/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AvatarEntity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Christian
 */
@Entity
@Table(name = "other_services_reservation")
@NamedQueries({
    @NamedQuery(name = "OtherServicesReservation.findAll", query = "SELECT o FROM OtherServicesReservation o"),
    @NamedQuery(name = "OtherServicesReservation.findByReservationItemId", query = "SELECT o FROM OtherServicesReservation o WHERE o.reservationItemId = :reservationItemId"),
    @NamedQuery(name = "OtherServicesReservation.findByProductId", query = "SELECT o FROM OtherServicesReservation o WHERE o.productId = :productId")})
public class OtherServicesReservation extends ReservationItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "product_id")
    private String productId;
    @Basic(optional = false)
    @Lob
    @Column(name = "note")
    private String note;

    public OtherServicesReservation() {
    }

    public OtherServicesReservation(Integer reservationItemId) {
        this.reservationItemId = reservationItemId;
    }

    public OtherServicesReservation(Date reservationTime, String username, Integer reservationItemId, String productId, String note) {
        super(reservationTime, username);
        //this.reservationItemId = reservationItemId;
        this.productId = productId;
        this.note = note;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

}
