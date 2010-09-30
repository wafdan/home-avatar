/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Support;

import javax.ejb.Stateless;

/**
 *
 * @author zulfikar
 */
@Stateless
public class EmailSender implements EmailSenderLocal {
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public static void sendEmail(String recipients, String sender , String subject, String emailContent) throws Exception
    {
        throw new Exception("Not supported yet");
    }
 
}
