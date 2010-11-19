/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Support;

import java.util.Date;

/**
 *
 * @author Christian
 */
public class FormValidator {

    public static boolean validateName(String str) {
        return str.matches("[a-zA-Z ]+");
    }

    public static boolean validatePhone(String str) {
        return str.matches("\\+\\d+");
    }

    public static boolean validateEmail(String str) {
        return str.matches("\\S+@\\S+");
    }

    public static boolean isBefore(Date date1, Date date2) {
        int tahun1 = date1.getYear();
        int tahun2 = date2.getYear();

        int bulan1 = date1.getYear();
        int bulan2 = date2.getYear();

        int hari1 = date1.getDate();
        int hari2 = date2.getDate();

        if (tahun1 < tahun2) {
            return true;
        } else if (tahun1 > tahun2) {
            return false;
        } else {
            if (bulan1 < bulan2) {
                return true;
            } else if (bulan1 > bulan2) {
                return false;
            } else {
                if (hari1 < hari2) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }
}
