/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KonfirmasiPembayaran;

import AvatarEntity.HallReservation;
import AvatarEntity.HallReservationJpaController;
import AvatarEntity.OtherServicesReservation;
import AvatarEntity.OtherServicesReservationJpaController;
import AvatarEntity.Payment;
import AvatarEntity.PaymentJpaController;
import AvatarEntity.ReservationItem;
import AvatarEntity.ReservationItemJpaController;
import AvatarEntity.Reservation;
import AvatarEntity.ReservationJpaController;
import AvatarEntity.RoomReservation;
import AvatarEntity.RoomReservationJpaController;
import AvatarEntity.exceptions.IllegalOrphanException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author kamoe
 */
public class KonfirmasiPembayaranController {
    ReservationJpaController rc;
    ReservationItemJpaController ric;
    RoomReservationJpaController rrc;
    HallReservationJpaController hrc;
    OtherServicesReservationJpaController osrc;
    PaymentJpaController pc;

    public KonfirmasiPembayaranController() {
        rc = new ReservationJpaController();
        ric = new ReservationItemJpaController();
        rrc = new RoomReservationJpaController();
        hrc = new HallReservationJpaController();
        osrc = new OtherServicesReservationJpaController();
        pc = new PaymentJpaController();
    }

    public List<Reservation> getReservation() {
         return rc.findReservationEntities();
    }

    public List<Reservation> getReservationByName(String name) {
         return rc.findOnlineReservationByName(name);
    }

    public List<Reservation> getUnpaidReservationByName(String name) {
         return rc.findUnpaidOnlineReservationByName(name);
    }

    public List<Reservation> getPaidReservationByName(String name) {
         return rc.findPaidOnlineReservationByName(name);
    }

    public Reservation getReservationById(int id) {
         return rc.findReservation(id);
    }

    public List<ReservationItem> getReservationItem() {
         return ric.findReservationItemEntities();
    }

    public int getPaymentStatus (Reservation r) {
        if (r.getPayment() == null) {
            return 1;
        } else if (r.getPayment().getUsername() != null) {
            return 2;
        } else {
            return 3;
        }
    }

    public void confirmPayment(String reserv_id, String account_number, String bank, String amount, Date payment_date) {
        Payment p = new Payment();
        p.setAccountNumber(account_number);
        p.setAmount(Double.parseDouble(amount));
        p.setPaymentDate(payment_date);
        p.setConfirmTime(new Date());
        p.setPaymentBank(bank);
        p.setPaymentMethod("Transfer");
        Reservation r = rc.findReservation(Integer.parseInt(reserv_id));
        p.setReservationId(r);
        try {
            pc.create(p);
            //return true;
        } catch (Exception e) {
            //return false;
        }
    }
}
