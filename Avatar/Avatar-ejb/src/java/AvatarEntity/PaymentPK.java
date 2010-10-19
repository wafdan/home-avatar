/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AvatarEntity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Christian
 */
@Embeddable
public class PaymentPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "reservation_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reservationTime;
    @Basic(optional = false)
    @Column(name = "confirm_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date confirmTime;

    public PaymentPK() {
    }

    public PaymentPK(Date reservationTime, Date confirmTime) {
        this.reservationTime = reservationTime;
        this.confirmTime = confirmTime;
    }

    public Date getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Date reservationTime) {
        this.reservationTime = reservationTime;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservationTime != null ? reservationTime.hashCode() : 0);
        hash += (confirmTime != null ? confirmTime.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentPK)) {
            return false;
        }
        PaymentPK other = (PaymentPK) object;
        if ((this.reservationTime == null && other.reservationTime != null) || (this.reservationTime != null && !this.reservationTime.equals(other.reservationTime))) {
            return false;
        }
        if ((this.confirmTime == null && other.confirmTime != null) || (this.confirmTime != null && !this.confirmTime.equals(other.confirmTime))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AvatarEntity.PaymentPK[reservationTime=" + reservationTime + ", confirmTime=" + confirmTime + "]";
    }

}
