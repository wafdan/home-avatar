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
    AccomodationJpaController ajc;
    HallJpaController hjc;
    OtherServicesJpaController osjc;
    VenueJpaController vjc;
    public static Cart c = new Cart();
    
    public MelihatLayananController() {
        ajc = new AccomodationJpaController();
        hjc = new HallJpaController();
        osjc = new OtherServicesJpaController();
        vjc = new VenueJpaController();
        System.out.println(c.count());
    }

    public List<Accomodation> getAccomodationList() {
        return ajc.findAccomodationEntities();
    }

    public List<Hall> getHallList() {
        return hjc.findHallEntities();
    }

    public List<OtherServices> getPublishedOtherServicesList() {
        return osjc.findPublishedOtherServices();
    }

    public List<Venue> getVenueList() {
        return vjc.findVenueEntities();
    }

    public Accomodation getAccomodation(String id) {
        return ajc.findAccomodation(id);
    }

    public Hall getHall(String id) {
        return hjc.findHall(id);
    }

    public OtherServices getOtherServices(String id) {
        return osjc.findOtherServices(id);
    }

    public Venue getVenue(String id) {
        return vjc.findVenue(id);
    }
}
