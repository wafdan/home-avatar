/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Support;

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
}
