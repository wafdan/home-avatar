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

    public void deleteReservation (Integer reservationId) throws IllegalOrphanException, NonexistentEntityException {
        Reservation res = rc.findReservation(reservationId);
        Iterator<ReservationItem> i = res.getReservationItemCollection().iterator();
        while (i.hasNext()) {
            ReservationItem curRes = i.next();
            /*if (curRes instanceof RoomReservation) {
                roc.destroy(curRes.getReservationItemId());
            } else if (curRes instanceof HallReservation) {
                hc.destroy(curRes.getReservationItemId());
            } else if (curRes instanceof OtherServicesReservation) {
                oc.destroy(curRes.getReservationItemId());
            }*/
            ric.destroy(curRes.getReservationItemId());
        }
        rc.destroy(reservationId);
    }
}
