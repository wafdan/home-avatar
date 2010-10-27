/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Layanan;

import AvatarEntity.Accomodation;
import AvatarEntity.AccomodationJpaController;
import AvatarEntity.Hall;
import AvatarEntity.HallJpaController;
import AvatarEntity.OtherServices;
import AvatarEntity.OtherServicesJpaController;
import AvatarEntity.Venue;
import AvatarEntity.VenueJpaController;
import java.util.List;

/**
 *
 * @author kamoe
 */
public class MelihatLayananController {
    public static Cart c = new Cart();
    AccomodationJpaController ac;
    VenueJpaController vc;
    HallJpaController hc;
    OtherServicesJpaController osc;

    public MelihatLayananController() {
        ac = new AccomodationJpaController();
        vc = new VenueJpaController();
        hc = new HallJpaController();
        osc = new OtherServicesJpaController();
    }

    public List<Accomodation> getAccomodationList() {
        return ac.findAccomodationEntities();
    }

    public Accomodation getAccomodation(String id) {
        return ac.findAccomodation(id);
    }

    public List<Venue> getVenueList() {
        return vc.findVenueEntities();
    }

    public Venue getVenue(String id) {
        return vc.findVenue(id);
    }

    public List<Hall> getHallList() {
        return hc.findHallEntities();
    }

    public Hall getHall(String id) {
        return hc.findHall(id);
    }

    public List<OtherServices> getOtherServicesList() {
        return osc.findOtherServicesEntities();
    }

    public OtherServices getOtherServices(String id) {
        return osc.findOtherServices(id);
    }
}
