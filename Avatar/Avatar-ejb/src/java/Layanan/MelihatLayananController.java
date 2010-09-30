/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Layanan;

import AvatarEntity.Accomodation;
import AvatarEntity.Hall;
import AvatarEntity.OtherServices;
import AvatarEntity.Venue;
import AvatarEntity.AccomodationJpaController;
import AvatarEntity.HallJpaController;
import AvatarEntity.OtherServicesJpaController;
import AvatarEntity.VenueJpaController;
import java.util.List;

/**
 *
 * @author kamoe
 */
public class MelihatLayananController {
    int n = 5;
    AccomodationJpaController ajc = new AccomodationJpaController();
    HallJpaController hjc = new HallJpaController();
    OtherServicesJpaController osjc = new OtherServicesJpaController();
    VenueJpaController vjc = new VenueJpaController();

    List getAccomodationList(int page) {
        return ajc.findAccomodationEntities(n,(page-1)*n);
    }

    List getHallList(int page) {
        return hjc.findHallEntities(n, (page-1)*n);
    }

    List getPublishedOtherServicesList(int page) {
        return osjc.findPublishedOtherServicesEntities(n, (page-1)*n);
    }

    List getVenueList(int page) {
        return vjc.findVenueEntities(n, (page-1)*n);
    }
}
