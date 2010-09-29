/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MelakukanRegistrasi;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import AvatarEntity.*;

/**
 *
 * @author Christian
 */
@Stateless
@LocalBean
public class TrialSessionBean {
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    AvatarUserFacade avatarUserAccess = new AvatarUserFacade();
    AvatarUser avatarUserItem = new AvatarUser("zulfikarh");
}
