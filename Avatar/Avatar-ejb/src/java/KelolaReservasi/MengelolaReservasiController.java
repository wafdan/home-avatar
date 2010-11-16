/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KelolaReservasi;

import AvatarEntity.HallReservation;
import AvatarEntity.HallReservationJpaController;
import AvatarEntity.OtherServicesReservationJpaController;
import AvatarEntity.Profile;
import AvatarEntity.ProfileJpaController;
import AvatarEntity.Reservation;
import AvatarEntity.ReservationItem;
import AvatarEntity.ReservationItemJpaController;
import AvatarEntity.ReservationJpaController;
import AvatarEntity.RoomReservation;
import AvatarEntity.RoomReservationJpaController;
import AvatarEntity.exceptions.IllegalOrphanException;
import AvatarEntity.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
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
    ProfileJpaController pc;
    Profile p;

    public MengelolaReservasiController() {
        rc = new ReservationJpaController();
        ric = new ReservationItemJpaController();
        roc = new RoomReservationJpaController();
        hc = new HallReservationJpaController();
        oc = new OtherServicesReservationJpaController();
        pc = new ProfileJpaController();
        p = pc.findProfile(Boolean.TRUE);
    }

    public List<Reservation> getReservation() {
        return rc.findUnpaidReservationEntities();
    }

    public List<Reservation> getReservationToWarn() {
        List<Reservation> lres = new ArrayList<Reservation>();
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0); today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0); today.set(Calendar.MILLISECOND, 0);
        Calendar limit = Calendar.getInstance(); limit.setTimeInMillis(0);
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
        Object[] ris = rc.findReservation(reservationId).getReservationItemCollection().toArray();
        for (int index=0;index<ris.length;index++) {
            ric.destroy(((ReservationItem) ris[index]).getReservationItemId());
        }
        rc.destroy(reservationId);
    }

    public Collection<ReservationItem> getReservationItemById(Integer id) {
        return rc.findReservation(id).getReservationItemCollection();
    }

    public Date getExpiredDate(Integer reservationId) {
        Reservation cur = rc.findReservation(reservationId);
        Iterator<ReservationItem> rii = cur.getReservationItemCollection().iterator();
        int index = 0;
        Date earliestDate = null;
        while (rii.hasNext()) {
            ReservationItem curRes = rii.next();
            if (curRes instanceof RoomReservation) {
                if (index == 0) {
                    earliestDate = ((RoomReservation) curRes).getEntryDate();
                } else if (earliestDate.after(((RoomReservation) curRes).getEntryDate())) {
                    earliestDate = ((RoomReservation) curRes).getEntryDate();
                }
            } else if (curRes instanceof HallReservation) {
                if (index == 0) {
                    earliestDate = ((HallReservation) curRes).getUseDate();
                } else if (earliestDate.after(((HallReservation) curRes).getUseDate())) {
                    earliestDate = ((HallReservation) curRes).getUseDate();
                }
            }
            index++;
        }
        return earliestDate;
    }
}
