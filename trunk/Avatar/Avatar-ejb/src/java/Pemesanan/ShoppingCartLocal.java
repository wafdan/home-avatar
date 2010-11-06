/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Pemesanan;

import javax.ejb.CreateException;
import javax.ejb.Local;

/**
 *
 * @author zulfikar
 */
@Local
public interface ShoppingCartLocal {

    ShoppingCartLocal create() throws CreateException;
    
}
