/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MelakukanRegistrasi;

import java.security.SecureRandom;
import java.math.*;
/**
 *
 * @author Christian
 */
public class TrialMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(new BigInteger(24, new SecureRandom()).toString(16));
    }
}
