/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AvatarEntity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Christian
 */
@Entity
@Table(name = "reservation")
@NamedQueries({
    @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r"),
    @NamedQuery(name = "Reservation.findByReservationId", query = "SELECT r FROM Reservation r WHERE r.reservationId = :reservationId"),
    @NamedQuery(name = "Reservation.findByIsOnspot", query = "SELECT r FROM Reservation r WHERE r.isOnspot = :isOnspot"),
    @NamedQuery(name = "Reservation.findUnpaid", query = "SELECT r FROM Reservation r WHERE r.reservationId NOT IN (SELECT p.reservationId.reservationId FROM Payment p)"),
    @NamedQuery(name = "Reservation.countUnpaid", query = "SELECT COUNT(r.reservationId) FROM Reservation r WHERE r.reservationId NOT IN (SELECT p.reservationId.reservationId FROM Payment p)"),
    @NamedQuery(name = "Reservation.findPaid", query = "SELECT r FROM Reservation r WHERE r.reservationId IN (SELECT p.reservationId.reservationId FROM Payment p)"),
    @NamedQuery(name = "Reservation.findUnverified", query = "SELECT r FROM Reservation r WHERE r.reservationId IN (SELECT p.reservationId.reservationId FROM Payment p) AND r.payment.username IS NULL"),
    @NamedQuery(name = "Reservation.countUnverified", query = "SELECT COUNT(r.reservationId) FROM Reservation r WHERE r.reservationId IN (SELECT p.reservationId.reservationId FROM Payment p) AND r.payment.username IS NULL"),
    @NamedQuery(name = "Reservation.findVerified", query = "SELECT r FROM Reservation r WHERE r.reservationId IN (SELECT p.reservationId.reservationId FROM Payment p) AND r.payment.username IS NOT NULL"),
    @NamedQuery(name = "Reservation.countVerified", query = "SELECT COUNT(r.reservationId) FROM Reservation r WHERE r.reservationId IN (SELECT p.reservationId.reservationId FROM Payment p) AND r.payment.username IS NOT NULL"),
    @NamedQuery(name = "Reservation.findReservationByName", query = "SELECT r FROM Reservation r WHERE r.username.name = :name AND r.isOnspot = :isOnspot ORDER BY r.reservationId"),
    @NamedQuery(name = "Reservation.findOnlineReservationByName", query = "SELECT r FROM Reservation r WHERE r.username.name = :name AND r.isOnspot = false ORDER BY r.reservationId"),
    @NamedQuery(name = "Reservation.findPaidOnlineReservationByName", query = "SELECT r FROM Reservation r WHERE r.username.name = :name AND r.isOnspot = false AND r.payment.paymentId IS NOT NULL ORDER BY r.reservationId")})
public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reservation_id")
    protected Integer reservationId;
    @Basic(optional = false)
    @Column(name = "is_onspot")
    private boolean isOnspot;
    @Basic(optional = false)
    @Lob
    @Column(name = "note")
    private String note;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "reservationId")
    private Payment payment;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservationId")
    private Collection<ReservationItem> reservationItemCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    private Collection<Reservation> reservationCollection;
    @JoinColumn(name = "parent", referencedColumnName = "reservation_id")
    @ManyToOne
    private Reservation parent;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne(optional = false)
    protected Customer username;

    public Reservation() {
    }

    public Reservation(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Reservation(Integer reservationId, boolean isOnspot, String note) {
        this.reservationId = reservationId;
        this.isOnspot = isOnspot;
        this.note = note;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public boolean getIsOnspot() {
        return isOnspot;
    }

    public void setIsOnspot(boolean isOnspot) {
        this.isOnspot = isOnspot;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Collection<ReservationItem> getReservationItemCollection() {
        return reservationItemCollection;
    }

    public void setReservationItemCollection(Collection<ReservationItem> reservationItemCollection) {
        this.reservationItemCollection = reservationItemCollection;
    }

    public Collection<Reservation> getReservationCollection() {
        return reservationCollection;
    }

    public void setReservationCollection(Collection<Reservation> reservationCollection) {
        this.reservationCollection = reservationCollection;
    }

    public Reservation getParent() {
        return parent;
    }

    public void setParent(Reservation parent) {
        this.parent = parent;
    }

    public Customer getUsername() {
        return username;
    }

    public void setUsername(Customer username) {
        this.username = username;
    }

    // Derived Attributes
    public double getTotalPrice() {
        double sum = 0;
        for (ReservationItem item : this.getReservationItemCollection()) {
            sum += item.getPrice();
        }
        return sum;
    }

    /*
     * Mencari timestamp reservasi dari reservation item pertama
     * return null jika reservasi kosong/tidak wajar
     */
    public Date getReservationTime() {
        Iterator<ReservationItem> iter = this.getReservationItemCollection().iterator();
        ReservationItem res = null;
        if (iter.hasNext()) {
            res = iter.next();
            return res.getReservationTime();
        } else {
            return null;
        }
    }

    public Date getReservationPaymentLimit() {
        Calendar cal = Calendar.getInstance();
        Calendar tmp = Calendar.getInstance();
        cal.setTimeInMillis(0);
        tmp.setTimeInMillis(0);
        Date limit = null;
        for (ReservationItem ri : reservationItemCollection) {
            if (ri.getPaymentLimit() != null) {
                cal.setTime(ri.getPaymentLimit());
                if (limit == null) {
                    limit = ri.getPaymentLimit();
                } else {
                    tmp.setTime(limit);
                    if (cal.before(tmp)) {
                        limit = ri.getPaymentLimit();
                    }
                }
            }
        }
        return limit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservationId != null ? reservationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.reservationId == null && other.reservationId != null) || (this.reservationId != null && !this.reservationId.equals(other.reservationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AvatarEntity.Reservation[reservationId=" + reservationId + "]";
    }

}
