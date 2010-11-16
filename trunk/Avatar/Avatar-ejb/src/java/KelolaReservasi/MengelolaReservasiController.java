/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KelolaReservasi;

import AvatarEntity.HallReservationJpaController;
import AvatarEntity.OtherServicesReservationJpaController;
import AvatarEntity.Reservation;
import AvatarEntity.ReservationItem;
import AvatarEntity.ReservationItemJpaController;
import AvatarEntity.ReservationJpaController;
import AvatarEntity.RoomReservationJpaController;
import AvatarEntity.exceptions.IllegalOrphanException;
import AvatarEntity.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author kamoe
 */
public class MengelolaReservasiController {
    ReservationJpaController rc;
    ReservationItemJpaController ric;
    RoomReservationJpaController roc;
    HallReservationJpaController hc;
    OtherServicesReservationJpaController oc;

    public MengelolaReservasiController() {
        rc = new ReservationJpaController();
        ric = new ReservationItemJpaController();
        roc = new RoomReservationJpaController();
        hc = new HallReservationJpaController();
        oc = new OtherServicesReservationJpaController();
    }

    public List<Reservation> getReservation() {
        return rc.findUnpaidReservationEntities();
    }

    public List<Reservation> getReservationToWarn() {
        List<Reservation> lres = new ArrayList<Reservation>();
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0); today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0); today.set(Calendar.MILLISECOND, 0);
        Calendar limit = null;
        for (Reservation res : rc.findUnpaidReservationEntities()) {
            if (res.getReservationPaymentLimit() != null) {
                limit.setTime(res.getReservationPaymentLimit());
                if (limit.compareTo(today) <= 0) lres.add(res);
            }
        }
        return lres;
    }

    public Reservation getReservation(Integer id) {
        return rc.findReservation(id);
    }

    public void deleteReservation (Integer reservationId) throws IllegalOrphanException, NonexistentEntityException {
        rc.destroy(reservationId);
    }

    public Collection<ReservationItem> getReservationItemById(Integer id) {
        return rc.findReservation(id).getReservationItemCollection();
    }
}
