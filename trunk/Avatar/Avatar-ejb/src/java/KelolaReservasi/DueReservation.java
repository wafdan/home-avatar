/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KelolaReservasi;

import AvatarEntity.Reservation;
import AvatarEntity.ReservationItem;
import java.util.Date;

/**
 *
 * @author kamoe
 */
public class DueReservation extends Reservation {
    ReservationItem resvItem;
    Date dueDate;

    public DueReservation(Reservation r) {
        this.reservationId = r.getReservationId();
    }

    public DueReservation(Reservation r, ReservationItem rt) {
        super(r.getReservationId(), r.getIsOnspot(), r.getNote());
        this.resvItem = rt;
        this.dueDate = rt.getPaymentLimit();
        this.username = r.getUsername();
    }
    
    public Date getDueDate() {
        return this.dueDate;
    }

    public ReservationItem getDueReservationItem() {
        return this.resvItem;
    }

    public void setDueDate(Date date) {
        this.dueDate = date;
    }

    public void setDueReservationItem(ReservationItem ri) {
        this.resvItem = ri;
    }
}
