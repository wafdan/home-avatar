/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pemesanan;

import AvatarEntity.Hall;
import AvatarEntity.Room;
import java.util.Date;
import javax.ejb.CreateException;
import javax.ejb.Local;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;

/**
 *
 * @author zulfikar
 */
@Local
public interface CartSessionBeanLocal {

    public ArrayList<HallSessionInfo> getHallCart();
    public ArrayList<RoomSessionInfo> getRoomCart();
    public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException;
    public void ejbRemove() throws EJBException, RemoteException;
    public void ejbActivate() throws EJBException, RemoteException;
    public void ejbPassivate() throws EJBException, RemoteException;
    public int getRoomCount();
    public int getHallCount();
    void businessMethod();
    public void addHallCartElement(String product_id, Date useDate, short total, int capacity, int layout_id);
    public void addRoomCartElement(String roomType, Date checkInDate, Date checkOutDate, short totalRoom);
    public List<Room> getListRoom();
    public List<Hall> getListHall();
    public void setListHall(List<Hall> input);
    CartSessionBeanLocal create() throws CreateException;
    void clearCart();
}
