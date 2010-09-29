/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MelakukanRegistrasi;

import AvatarEntity.Staff;
import AvatarEntity.StaffJpaController;
import AvatarEntity.exceptions.PreexistingEntityException;
import Support.EncMd5;

/**
 *
 * @author Christian
 */
public class TrialMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PreexistingEntityException, Exception {
        Staff st = new Staff("chrhad081", "Christian Hadiwinoto", EncMd5.MD5("chrhad081"), "chrhad07188@students.itb.ac.id", "198912152010091001", (short) 2);
        StaffJpaController sjc = new StaffJpaController();
        sjc.create(st);
    }

}
