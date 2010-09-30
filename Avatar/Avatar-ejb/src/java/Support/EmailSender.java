/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Support;

import javax.ejb.Stateless;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author zulfikar
 */
@Stateless
public class EmailSender implements EmailSenderLocal {
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
   // Recipient's email ID needs to be mentioned.
      String to = "abcd@gmail.com";

      // Sender's email ID needs to be mentioned
      String from = "web@gmail.com";

      // Assuming you are sending email from localhost
      String host = "localhost";

      
   
    public static void sendEmail(String recipients, String sender , String subject, String emailContent) throws Exception
    {
        throw new Exception("Not supported yet");
    }
 
}
