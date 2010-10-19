/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Support;

import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

/**
 * @author dhana
 */
@Stateless
public class EmailSender implements EmailSenderLocal {

        //Atribut
        //SmtpServer : tergantung dari apa yang dipakai, jika di kampus maka pakai smtp.gmail.com.
	//Jika di rumah/kosan pakai speedy, smtp yang digunakan  adalah smtp.telkom.net
	private static final String smtpServer = "smtp.gmail.com";

        public static void sendEmail(String to, String from , String cc, String subject, String body) throws Exception
        {
            try
            {
                Properties props = System.getProperties();

                // -- Attach ke default Session, atau buat session baru --
                props.put("mail.smtp.host", smtpServer);

                Session session = Session.getDefaultInstance(props, null);

                // -- Buat message baru --
                Message msg = new MimeMessage(session);

                // -- Set FROM dan TO --
                msg.setFrom(new InternetAddress(from));
                msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to, false));

                // -- Set CC --
                msg.setRecipients(Message.RecipientType.CC,InternetAddress.parse(cc, false));

                // -- Set subject dan body text --
                msg.setSubject(subject);
                msg.setText(body);

                // -- Set informasi header --
                msg.setHeader("X-Mailer", "AVATAR Email Service");
                msg.setSentDate(new Date());

                // -- Kirim pesan --
                Transport.send(msg);
          }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
