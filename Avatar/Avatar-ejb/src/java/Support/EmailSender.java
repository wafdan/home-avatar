/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Support;

import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;

/**
 * @author dhana
 */
@Stateless
public class EmailSender implements EmailSenderLocal {

    //Atribut
    //SmtpServer : tergantung dari apa yang dipakai, jika di kampus maka pakai smtp.gmail.com.
    //Jika di rumah/kosan pakai speedy, smtp yang digunakan  adalah smtp.telkom.net
    private static final String smtpServer = "smtp.gmail.com";
    //private static final String smtpServer = "smtp.melsa.net.id";

    public static void sendEmail(String to, String from , String cc, String subject, String body) throws Exception {
        try {
            Properties props = System.getProperties();

            // -- Attach ke default Session, atau buat session baru --
            props.put("mail.smtp.host", smtpServer);

            Session session = Session.getDefaultInstance(props, null);

            // -- Buat message baru --
            Message msg = new MimeMessage(session);

            // -- Set FROM dan TO serta CC --
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to, false));
            msg.setRecipients(Message.RecipientType.CC,InternetAddress.parse(cc, false));

            // -- Set subject dan body text --
            msg.setSubject(subject);
            msg.setText(body);

            // -- Set informasi header --
            msg.setHeader("X-Mailer", "AVATAR Email Service");
            msg.setSentDate(new Date());

            // -- Kirim pesan --
            Transport.send(msg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*
     * Mengirim email dengan attachment
     * Referensi: http://www.kodejava.org/examples/243.html
     */
    public static void sendEmail(String to, String from , String cc, String subject, String body, String attachedFile) throws Exception {
        try {
            Properties props = System.getProperties();

            // -- Attach ke default Session, atau buat session baru --
            props.put("mail.smtp.host", smtpServer);

            Session session = Session.getDefaultInstance(props, null);

            // -- Inisialisasi: Buat message baru dan bagian-bagiannya --
            MimeMessage msg = new MimeMessage(session);
            Multipart multipart = new MimeMultipart();
            MimeBodyPart txt = new MimeBodyPart();
            MimeBodyPart att = new MimeBodyPart();

            // -- Set FROM dan TO serta CC --
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to, false));
            msg.setRecipients(Message.RecipientType.CC,InternetAddress.parse(cc, false));

            // -- Set subject dan body text --
            msg.setSubject(subject);
            txt.setText(body);

            // -- Set attachment --
            FileDataSource fileDS = new FileDataSource(attachedFile) {
                @Override
                public String getContentType() {
                    return "application/octet-stream";
                }
            };
            att.setDataHandler(new DataHandler(fileDS));
            att.setFileName(attachedFile);
            multipart.addBodyPart(txt);
            multipart.addBodyPart(att);

            // -- Set informasi header --
            msg.setHeader("X-Mailer", "AVATAR Email Service");
            msg.setSentDate(new Date());

            // -- Kirim pesan --
            msg.setContent(multipart);
            Transport.send(msg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
