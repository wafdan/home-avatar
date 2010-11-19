/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KelolaPembayaran;

import javax.ejb.Local;

/**
 *
 * @author Christian
 */
@Local
public interface ReceiptGeneratorLocal {

    public String generateDocument(AvatarEntity.Reservation reservation);

}
