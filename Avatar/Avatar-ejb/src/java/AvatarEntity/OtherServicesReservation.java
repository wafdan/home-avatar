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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Christian
 */
@Entity
@Table(name = "other_services_reservation")
@NamedQueries({
    @NamedQuery(name = "OtherServicesReservation.findAll", query = "SELECT o FROM OtherServicesReservation o"),
    @NamedQuery(name = "OtherServicesReservation.findByReservationItemId", query = "SELECT o FROM OtherServicesReservation o WHERE o.reservationItemId = :reservationItemId")})
public class OtherServicesReservation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "reservation_item_id")
    private Integer reservationItemId;
    @Basic(optional = false)
    @Lob
    @Column(name = "note")
    private String note;
    @JoinColumn(name = "reservation_item_id", referencedColumnName = "reservation_item_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private ReservationItem reservationItem;
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne(optional = false)
    private OtherServices productId;

    public OtherServicesReservation() {
    }

    public OtherServicesReservation(Integer reservationItemId) {
        this.reservationItemId = reservationItemId;
    }

    public OtherServicesReservation(Integer reservationItemId, String note) {
        this.reservationItemId = reservationItemId;
        this.note = note;
    }

    public Integer getReservationItemId() {
        return reservationItemId;
    }

    public void setReservationItemId(Integer reservationItemId) {
        this.reservationItemId = reservationItemId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ReservationItem getReservationItem() {
        return reservationItem;
    }

    public void setReservationItem(ReservationItem reservationItem) {
        this.reservationItem = reservationItem;
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

}
