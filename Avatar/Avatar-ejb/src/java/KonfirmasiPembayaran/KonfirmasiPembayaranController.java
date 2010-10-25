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

    public List<Reservation> getReservationByUsername(String username) {
         return rc.findReservationEntitiesByUsername(username);
    }

}
