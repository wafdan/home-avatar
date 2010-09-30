/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KelolaPengguna;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Stateless;
import java.security.SecureRandom;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author zulfikar
 */
@Stateless
public class MengelolaPenggunaController implements MengelolaPenggunaControllerLocal {
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public String generateStaffUsername(String fullname, String EmploymentID)
    {
        String[] nama=fullname.split(" ");
        return nama[0]+EmploymentID.substring(0, 2);
    }

    public String generateStaffUsername(String fullname, String EmploymentID, int subset)
    {
        String[] nama=fullname.split(" ");
        return nama[0]+EmploymentID.substring(0, subset);
    }

    public String generatePassword()
    {
        return new BigInteger(55, new SecureRandom()).toString(32);
    }

    public String generateCustomerUsername(String fullname)
    {
        int indexHurufTengah=fullname.length()/2;
        char pertama=fullname.charAt(0);
        char kedua=fullname.charAt(indexHurufTengah);
        int ASCIIpertama=(int) pertama;
        int ASCIIkedua=(int) kedua;

        String[] splittedName=fullname.split(" ");
        return splittedName[0]+Integer.toString(ASCIIpertama)+Integer.toString(ASCIIkedua);
    }

    private static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while(two_halfs++ < 1);
        }
        return buf.toString();
    }

    public String getHashedPassword(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException  {
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] md5hash = new byte[32];
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        md5hash = md.digest();
        return convertToHex(md5hash);
    }
 
}
