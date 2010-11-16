/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KelolaReservasi;

import AvatarEntity.HallReservation;
import AvatarEntity.HallReservationJpaController;
import AvatarEntity.OtherServicesReservation;
import AvatarEntity.OtherServicesReservationJpaController;
import AvatarEntity.Reservation;
import AvatarEntity.ReservationItem;
import AvatarEntity.ReservationItemJpaController;
import AvatarEntity.ReservationJpaController;
import AvatarEntity.RoomReservation;
import AvatarEntity.RoomReservationJpaController;
import AvatarEntity.exceptions.IllegalOrphanException;
import AvatarEntity.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.management.timer.Timer;

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

    /*public List<DueReservation> getDuePaymentReservation() {
        Date current = new Date();
        List<Reservation> resv = rc.findUnpaidReservationEntities();
        List<DueReservation> due = new ArrayList<DueReservation>();
        Iterator<Reservation> i = resv.iterator();
        while (i.hasNext()) {
            Date earliestDate = null;
            ReservationItem dueResvItem = null;
            Reservation cur = i.next();
            Iterator<ReservationItem> rii = cur.getReservationItemCollection().iterator();
            int index = 0;
            while (rii.hasNext()) {
                ReservationItem curRes = rii.next();
                if (index == 0) {
                    dueResvItem = curRes;
                } else if (earliestDate.after(((RoomReservation) curRes).getEntryDate())) {
                    dueResvItem = curRes;
                }
                index++;
            }
            if (cur != null) {
                if (earliestDate.getTime() - current.getTime() <= 3*Timer.ONE_DAY) {
                    DueReservation dueResv = new DueReservation(cur,dueResvItem);
                    due.add(dueResv);
                }
            }
        }
        return due;
    }*/

    public DueReservation getDueReservation(Integer reservationId) {
        Reservation cur = rc.findReservation(reservationId);
        Iterator<ReservationItem> rii = cur.getReservationItemCollection().iterator();
        int index = 0;
        Date current = new Date();
        Date earliestDate = null;
        ReservationItem dueResvItem = null;
        while (rii.hasNext()) {
            ReservationItem curRes = rii.next();
            if (curRes instanceof RoomReservation) {
                if (index == 0) {
                    earliestDate = ((RoomReservation) curRes).getEntryDate();
                    dueResvItem = curRes;
                } else if (earliestDate.after(((RoomReservation) curRes).getEntryDate())) {
                    earliestDate = ((RoomReservation) curRes).getEntryDate();
                    dueResvItem = curRes;
                }
            } else if (curRes instanceof HallReservation) {
                if (index == 0) {
                    earliestDate = ((HallReservation) curRes).getUseDate();
                    dueResvItem = curRes;
                } else if (earliestDate.after(((HallReservation) curRes).getUseDate())) {
                    earliestDate = ((HallReservation) curRes).getUseDate();
                    dueResvItem = curRes;
                }
            }
            index++;
        }
        DueReservation res = null;
        if (cur != null) {
            if (earliestDate.getTime() - current.getTime() <= 3*Timer.ONE_DAY) {
                res = new DueReservation(cur,dueResvItem);
            }
        }
        return res;
    }

    public void deleteReservation (Integer reservationId) throws IllegalOrphanException, NonexistentEntityException {
        Reservation res = rc.findReservation(reservationId);
        Object[] ris = res.getReservationItemCollection().toArray();
        for (int i=0; i<ris.length; i++) {
            ric.destroy(((ReservationItem) ris[i]).getReservationItemId());
        }
        rc.destroy(reservationId);
    }

    public Collection<ReservationItem> getReservationItemById(Integer id) {
        return rc.findReservation(id).getReservationItemCollection();
    }
}
