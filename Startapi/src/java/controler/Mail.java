package controler;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Korisnik;
import model.KorisnikDAO;
import model.Obavestenje;
import model.Vidljivost;

public class Mail {

    public static boolean posaljiKodZaProveruMaila(int kod, String mail) {
        System.out.println("assadsaddassdsadasssddddddasdaasdassd    uso u slane maila  ba  " + mail);
        boolean b = false;
        try {
            String host = "smtp.gmail.com";
            String user = "startapovi@gmail.com";
            String pass = "medenkopas";
            String to = mail;
            String from = "Startapovi@gmail.com";
            String subject = "This is confirmation number for your expertprogramming account. Please insert this number to activate your account.";
            String messageText = "kod :" + kod;
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setText(messageText);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("message send successfully");
            b = true;

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return b;
    }

    public static boolean posaljiObavestenjeMejlom(Obavestenje obavestenje) {

        boolean b = false;
        try {
//           

            String host = "smtp.gmail.com";
            String user = "startapovi@gmail.com";
            String pass = "medenkopas";
           // String to = "";
            String from = "Startapovi@gmail.com";
            String subject = obavestenje.getNaslov();
          //  short svi = (short) Vidljivost.SVI.getSifra();
//            short admin = (short)Vidljivost.ADMIN.getSifra();
//            short investitor = (short) Vidljivost.INVESTITORI.getSifra();
//            short startap = (short) Vidljivost.STARTAPI.getSifra();
//            short custom = (short) Vidljivost.CUSTOM.getSifra();
//            short korisnici = (short) Vidljivost.KORISNICI.getSifra();

            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            // InternetAddress[] address = {new InternetAddress(to)};
            //  msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setText(obavestenje.getTekst());
            ArrayList<String> adrese = new ArrayList();
            
            if (obavestenje.getVidljivost() == Vidljivost.SVI.getSifra() || obavestenje.getVidljivost() == Vidljivost.KORISNICI.getSifra()) {
                adrese = KorisnikDAO.dohvatiEmailAdreseSvihKorisnika();
 
            }
            if(obavestenje.getVidljivost() == Vidljivost.STARTAPI.getSifra()){
                adrese = KorisnikDAO.dohvatiEmailAdreseStartapova();        
            }
            if(obavestenje.getVidljivost() == Vidljivost.INVESTITORI.getSifra()){
                adrese = KorisnikDAO.dohvatiEmailAdreseInvestitora();        
            }
            if(obavestenje.getVidljivost() == Vidljivost.CUSTOM.getSifra()){
                for(Korisnik kor:obavestenje.getKorisnikCollection()){
                    adrese.add(kor.getMail());
                }
            }
            
            
            InternetAddress[] address = new InternetAddress[adrese.size()];
                for (int i = 0; i < adrese.size(); i++) {
                    if(adrese.get(i)!= null && !adrese.get(i).isEmpty()){
                          address[i] = new InternetAddress(adrese.get(i));
                    }
                  
                }
            msg.addRecipients(Message.RecipientType.TO, address);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("message send successfully");
            b = true;

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return b;
    }

}
