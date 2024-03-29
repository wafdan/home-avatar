/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MelakukanRegistrasi;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Random;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.ejb.Stateless;

import Support.EncMd5;

/**
 *
 * @author Christian
 */
@Stateless
public class MelakukanRegistrasiController implements MelakukanRegistrasiControllerLocal {

    public String generateUsername(String fullname, String email) {
        //throw new UnsupportedOperationException("Not supported yet.");
        int i; /* traversal */
        int temp; /* temporer untuk simpan penjumlahan angka */
        //String enddigit; /* digit-digit akhir username */
        Random rand = new Random();
        String[] arrnama=fullname.split(" ");
        // digit suffix pertama dibangkitkan dari nama depan
        /*temp = 0;
        for (i = 0; i < arrnama[0].length(); i++) {
            temp += (int) arrnama[0].charAt(i); // jadikan urutan ASCII
        } // akhir: temp berisi jumlah
        enddigit = ""+ ((temp % 9) + 1);

        // digit suffix ke-2 dan ke-3 dibangkitkan dari email
        temp = 0;
        for (i = 0; i < email.length(); i++) {
            temp += (int) email.charAt(i); // jadikan urutan ASCII
        } // akhir: temp berisi jumlah
        enddigit = enddigit + String.format("%02d", temp % 100);

        // digit suffix ke-4 dibangkitkan dari tanggal hari ini
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String dateString = dateFormat.format(date);
        temp = 0;
        for (i = 0; i < dateString.length(); i++) {
            temp += (int) dateString.charAt(i); // jadikan urutan ASCII
        } // akhir: temp berisi jumlah
        enddigit = enddigit + (temp % 10);*/
        if (arrnama.length > 1) {
            return arrnama[0].toLowerCase()+"."+(arrnama[1].substring(0, 1)).toLowerCase()+String.format("%04d", rand.nextInt(10000));
        } else {
            return arrnama[0].toLowerCase()+String.format("%04d", rand.nextInt(10000));
        }
    }

    public String generatePassword() {
        return new BigInteger(24, new SecureRandom()).toString(16);
    }

    public String getHashedPassword(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return EncMd5.MD5(text);
    }
}
