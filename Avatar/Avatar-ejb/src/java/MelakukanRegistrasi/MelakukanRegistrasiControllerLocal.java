/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MelakukanRegistrasi;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Local;

/**
 *
 * @author Christian
 */
@Local
public interface MelakukanRegistrasiControllerLocal {
    public String generateUsername(String fullname, String EmploymentID);
    public String generatePassword();
    public String getHashedPassword(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException;
}
