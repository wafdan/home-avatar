/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Pemesanan;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.CreateException;
import javax.ejb.Stateful;

/**
 *
 * @author zulfikar
 */
@Stateful
public class ShoppingCart implements ShoppingCartRemote, ShoppingCartLocal {
    public ArrayList<HallSessionInfo> hallCart;
    public ArrayList<RoomSessionInfo> roomCart;

    public ShoppingCartLocal create() throws CreateException {
        return null;
    }

    public void ejbCreate() throws CreateException {
        hallCart=new ArrayList<HallSessionInfo>();
        roomCart=new ArrayList<RoomSessionInfo>();
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    

    

    
}
