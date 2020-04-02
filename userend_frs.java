package flightreservationportal;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.event.*;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author Abhishek Agrawal
 */
public class userend extends javax.swing.JFrame {

    /**
     * Creates new form userend
     */
    Timer t; 
    Path pathsearch = Paths.get("Booked_tickets");
    String path;

    public userend() throws IOException {
        path = pathsearch.toRealPath(LinkOption.NOFOLLOW_LINKS).toString();
        initComponents();
        
         t = new Timer(200,new ActionListener(){
            public void actionPerformed (ActionEvent e){
               
               int n = prgbar.getValue();
               int a = n + 1;
               if(a == 101){
                   jFrame1.setVisible(true);
                   t.stop();
               }
                 else{
               prgbar.setValue(a);
               }
             
            }
        });
         
         
    }
	
	String timeduration,timeduration1;
String GetDate(int n){
    String day = "";
    if(n == 1){
        day = "Monday";
    }
    if(n == 2){
        day = "Tuesday";
    }
    if(n == 3){
        day = "Wednesday";
    }
    if(n == 4){
        day = "Thursday";
    }
    if(n == 5){
        day = "Friday";
    }
    if(n == 6){
        day = "Saturday";
    }
    if(n == 0){
        day = "Sunday"; }
    return day;
}
String GetDuration(String s){
   char a = s.charAt(1);
   String b = s.substring(3, 5);
  String timedr = a + " hours " + b + " minutes ";
   return timedr;
}
String GetDuration1(String s1,String s2){
    int a =Integer.parseInt(s1.substring(1, 2)) ;
  int b = Integer.parseInt(s1.substring(3, 5));
    int c = Integer.parseInt(s2.substring(1, 2));
   int d = Integer.parseInt(s2.substring(3, 5));
   int e = a+c;
   
   
   int h = b+d;
   if(h >=60){
       e++;
       h = h - 60;
   }

             
    String timedr = e + " hours " + h + " minutes ";
    return timedr;
}
String GetMonth(int n){
    String month = "";
    if(n == 0){
        month = "January";
    }
    if(n == 1){
        month = "February";
    }
    if(n == 2){
        month = "March";
    }
    if(n == 3){
        month = "April";
    }
    if(n == 4){
        month = "May";
    }
    if(n == 5){
        month = "June";
    }
    if(n == 6){
        month = "July";
    }
    if(n == 7){
        month = "August";
    }
    if(n == 8){
        month = "September";
    }
    if(n == 9){
        month = "October";
    }
    if(n == 10){
        month = "November";
    }if(n == 11){
        month = "December";
    }
    
    return month;
}
String generatepnr(int n){
    int m = 0;
    if(n>=1 && n<=5){
        m = 5;
    }
    if(n == 6){
        m = 6;
    }
    if(n >= 7){
        m = 7;
    }
    String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
String pnr = "";
for(int i = 1;i<=m;i++){
    
    char p = alphanumeric.charAt(new Random().nextInt(alphanumeric.length()));
    pnr = pnr + ""+p;
    
}
return pnr;
}
String[] seat;
String[] checkinpassenger;
String[] ticketinfo = new String[10];
int totalpass;
String[] passengername;
String codes;
String generatedpnr;
String rtngeneratedpnr;
String forwarddate;
String returndate;
String fromcity;
String tocity;
String rtnfromcity;
String rtntocity;
int adultnumber;
int childnumber;
int infantnumber;
String cvvno;
String departuredisplaydate;
String returndisplaydate;
String userid;
String userpassword;

    private void prgbarAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_prgbarAncestorAdded
t.start();        // TODO add your handling code here:
    }//GEN-LAST:event_prgbarAncestorAdded

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

try{Class.forName("java.sql.Driver"); String c = "",d = "";
Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
Statement stmt = con.createStatement();
 userid = uid.getText();
 userpassword = new String(pwd.getPassword());
String query = "select * from user where userid = '"+userid+"';";
ResultSet rst = stmt.executeQuery(query);
while(rst.next()){
    c = rst.getString(1);
    d = rst.getString(2);
   
}
if(userpassword.equals(d) && userid.equals(c)){
    JOptionPane.showMessageDialog(null, "WELCOME ABOARD......");
    jFrame2.setVisible(true);
    jFrame1.dispose();
    
}
else{
    JOptionPane.showMessageDialog(null, "Userid and Password don't match. Please Check again...");
}

stmt.close();
con.close();
}
catch(Exception e){
    JOptionPane.showMessageDialog(null, e.getMessage());
}
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
register.setVisible(true);
jFrame1.setVisible(false);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jPanel1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel1AncestorAdded
int n = JOptionPane.showConfirmDialog(null, "Are you sure you are registered with us");
if(n == JOptionPane.YES_OPTION){
    uid.setFocusable(true);
    uid.setEnabled(true);
    pwd.setEnabled(true);
}
else{
    JOptionPane.showMessageDialog(null, "Please register with us");
}
// TODO add your handling code here:
    }//GEN-LAST:event_jPanel1AncestorAdded

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
int o = JOptionPane.showConfirmDialog(null, "Are you sure you want to register..");
if(o == JOptionPane.YES_OPTION){
try{Class.forName("java.sql.Driver");
String a ="'" +name.getText() +" "+  surname.getText()+ "'," ;
String b = "'" + ph.getText()+"',";
int c = Integer.parseInt(age.getText());
String d = "'" +((JTextField)dob.getDateEditor().getUiComponent()).getText()+ "',";
String e ="'" + sex.getText()+ "',";
String f = "'" +city.getText()+ "',";
String g = "'" +state.getText()+ "',";
String h = "'" +country.getText()+ "',";
String i = "'" +email.getText()+ "'";
String l = "'"+username.getText()+"',";
String m = "'"+new String(password.getPassword())+"',";
Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
Statement stmt = con.createStatement();
String query = "insert into userinfo values("+a+b+c+","+d+e+f+g+h+i+");";
String query1 = "insert into user values("+l+m+i+");";
int j = stmt.executeUpdate(query);
int n = stmt.executeUpdate(query1);
stmt.close();
con.close();
}
catch(Exception k){
    JOptionPane.showMessageDialog(null, k.getMessage());
}
jFrame1.setVisible(true);
register.dispose();
}
else{
    jFrame1.setVisible(true);
    register.dispose();
}



        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

jPanel4.setVisible(true);

jPanel5.setVisible(false);
jPanel6.setVisible(false);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
jPanel5.setVisible(true);

jPanel4.setVisible(false);
jPanel6.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jPanel3AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel3AncestorAdded
jPanel4.setVisible(false);
jPanel5.setVisible(false);
jPanel6.setVisible(false);

        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel3AncestorAdded

    private void rndtrpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rndtrpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rndtrpActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
jPanel4.setVisible(false);
jPanel5.setVisible(false);
jPanel6.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
 String seatpnr = checkinpnr.getText();
 int n = 0;
 
 
 outer: {inner: {try{
     Class.forName("java.sql.Driver");
     Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
     Statement stmt = con.createStatement();
     String query = "select passno from fwdbooking where pnr = '"+seatpnr+"';";
     ResultSet rst = stmt.executeQuery(query);
     if(rst.next()){
         n = rst.getInt(1);
         break outer;
     }
     else{
         break inner;
     }
 }
catch(Exception e){
    JOptionPane.showMessageDialog(null, e.getMessage());
}}
 try{
     Class.forName("java.sql.Driver");
     Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
     Statement stmt = con.createStatement();
     String query = "select passno from rtnbooking where rtnpnr = '"+seatpnr+"';";
     ResultSet rst = stmt.executeQuery(query);
     if(rst.next()){
         n = rst.getInt(1);
     }
     else{
         JOptionPane.showMessageDialog(null, "PNR doesnot exist");
     }
 }
catch(Exception e){
    JOptionPane.showMessageDialog(null, e.getMessage());
}}
seat = new String[n];
int i = 0;
seatinner: {
    if(A1.isSelected()==true && A1.isEnabled()==true){
    seat[i] = A1.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
     if(A2.isSelected()==true&& A2.isEnabled()==true){
    seat[i] = A2.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
      if(A3.isSelected()==true&& A3.isEnabled()==true){
    seat[i] = A3.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
       if(A4.isSelected()==true&& A4.isEnabled()==true){
    seat[i] = A4.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
        if(A5.isSelected()==true&& A5.isEnabled()==true){
    seat[i] = A5.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
         if(A6.isSelected()==true&& A6.isEnabled()==true){
    seat[i] = A6.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
          if(A7.isSelected()==true&& A7.isEnabled()==true){
    seat[i] = A7.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
           if(A8.isSelected()==true&& A8.isEnabled()==true){
    seat[i] = A8.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
            if(A9.isSelected()==true&& A9.isEnabled()==true){
    seat[i] = A9.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
             if(A10.isSelected()==true&& A10.isEnabled()==true){
    seat[i] = A10.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
              if(A11.isSelected()==true&& A11.isEnabled()==true){
    seat[i] = A11.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
               if(A12.isSelected()==true&& A12.isEnabled()==true){
    seat[i] = A12.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                if(A13.isSelected()==true&& A13.isEnabled()==true){
    seat[i] = A13.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                 if(A14.isSelected()==true&& A14.isEnabled()==true){
    seat[i] = A14.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                  if(A15.isSelected()==true&& A15.isEnabled()==true){
    seat[i] = A15.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                   if(A16.isSelected()==true&& A16.isEnabled()==true){
    seat[i] = A16.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                    if(A17.isSelected()==true&& A17.isEnabled()==true){
    seat[i] = A17.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                     if(A18.isSelected()==true&& A18.isEnabled()==true){
    seat[i] = A18.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(B1.isSelected()==true&& B1.isEnabled()==true){
    seat[i] = B1.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(B2.isSelected()==true&& B2.isEnabled()==true){
    seat[i] = B2.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(B3.isSelected()==true&& B3.isEnabled()==true){
    seat[i] = B3.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(B4.isSelected()==true&& B4.isEnabled()==true){
    seat[i] = B4.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(B5.isSelected()==true&& B5.isEnabled()==true){
    seat[i] = B5.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(B6.isSelected()==true&& B6.isEnabled()==true){
    seat[i] = B6.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(B7.isSelected()==true&& B7.isEnabled()==true){
    seat[i] = B7.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(B8.isSelected()==true&& B8.isEnabled()==true){
    seat[i] = B8.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(B9.isSelected()==true&& B9.isEnabled()==true){
    seat[i] = B9.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(B10.isSelected()==true&& B10.isEnabled()==true){
    seat[i] = B10.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(B11.isSelected()==true&& B11.isEnabled()==true){
    seat[i] = B11.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(B12.isSelected()==true&& B12.isEnabled()==true){
    seat[i] = B12.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(B13.isSelected()==true&& B13.isEnabled()==true){
    seat[i] = B13.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(B14.isSelected()==true&& B14.isEnabled()==true){
    seat[i] = B14.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(B15.isSelected()==true&& B15.isEnabled()==true){
    seat[i] = B15.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(B16.isSelected()==true&& B16.isEnabled()==true){
    seat[i] = B16.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(B17.isSelected()==true&& B17.isEnabled()==true){
    seat[i] = B17.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(B18.isSelected()==true&& B18.isEnabled()==true){
    seat[i] = B18.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(C1.isSelected()==true&& C1.isEnabled()==true){
    seat[i] = C1.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(C2.isSelected()==true&& C2.isEnabled()==true){
    seat[i] = C2.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(C3.isSelected()==true&& C3.isEnabled()==true){
    seat[i] = C3.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(C4.isSelected()==true&& C4.isEnabled()==true){
    seat[i] = C4.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(C5.isSelected()==true&& C5.isEnabled()==true){
    seat[i] = C5.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(C6.isSelected()==true&& C6.isEnabled()==true){
    seat[i] = C6.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(C7.isSelected()==true&& C7.isEnabled()==true){
    seat[i] = C7.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(C8.isSelected()==true&& C8.isEnabled()==true){
    seat[i] = C8.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(C9.isSelected()==true&& C9.isEnabled()==true){
    seat[i] = C9.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(C10.isSelected()==true&& C10.isEnabled()==true){
    seat[i] = C10.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(C11.isSelected()==true&& C11.isEnabled()==true){
    seat[i] = C11.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(C12.isSelected()==true&& C12.isEnabled()==true){
    seat[i] = C12.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(C13.isSelected()==true&& C13.isEnabled()==true){
    seat[i] = C13.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(C14.isSelected()==true&& C14.isEnabled()==true){
    seat[i] = C14.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(C15.isSelected()==true&& C15.isEnabled()==true){
    seat[i] = C15.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(C16.isSelected()==true&& C16.isEnabled()==true){
    seat[i] = C16.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(C17.isSelected()==true&& C17.isEnabled()==true){
    seat[i] = C17.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(C18.isSelected()==true&& C18.isEnabled()==true){
    seat[i] = C18.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(D1.isSelected()==true&& D1.isEnabled()==true){
    seat[i] = D1.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(D2.isSelected()==true&& D2.isEnabled()==true){
    seat[i] = D2.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(D3.isSelected()==true&& D3.isEnabled()==true){
    seat[i] = D3.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(D4.isSelected()==true&& D4.isEnabled()==true){
    seat[i] = D4.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(D5.isSelected()==true&& D5.isEnabled()==true){
    seat[i] = D5.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(D6.isSelected()==true&& D6.isEnabled()==true){
    seat[i] = D6.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(D7.isSelected()==true&& D7.isEnabled()==true){
    seat[i] = D7.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(D8.isSelected()==true&& D8.isEnabled()==true){
    seat[i] = D8.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(D9.isSelected()==true&& D9.isEnabled()==true){
    seat[i] = D9.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(D10.isSelected()==true&& D10.isEnabled()==true){
    seat[i] = D10.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(D11.isSelected()==true&& D11.isEnabled()==true){
    seat[i] = D11.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(D12.isSelected()==true&& D12.isEnabled()==true){
    seat[i] = D12.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(D13.isSelected()==true&& D13.isEnabled()==true){
    seat[i] = D13.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(D14.isSelected()==true&& D14.isEnabled()==true){
    seat[i] = D14.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(D15.isSelected()==true&& D15.isEnabled()==true){
    seat[i] = D15.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(D16.isSelected()==true&& D16.isEnabled()==true){
    seat[i] = D16.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(D17.isSelected()==true&& D17.isEnabled()==true){
    seat[i] = D17.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(D18.isSelected()==true&& D18.isEnabled()==true){
    seat[i] = D18.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(E1.isSelected()==true&& E1.isEnabled()==true){
    seat[i] = E1.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(E2.isSelected()==true&& E2.isEnabled()==true){
    seat[i] = E2.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(E3.isSelected()==true&& E3.isEnabled()==true){
    seat[i] = E3.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(E4.isSelected()==true&& E4.isEnabled()==true){
    seat[i] = E4.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(E5.isSelected()==true&& E5.isEnabled()==true){
    seat[i] = E5.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(E6.isSelected()==true&& E6.isEnabled()==true){
    seat[i] = E6.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(E7.isSelected()==true&& E7.isEnabled()==true){
    seat[i] = E7.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(E8.isSelected()==true&& E8.isEnabled()==true){
    seat[i] = E8.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(E9.isSelected()==true&& E9.isEnabled()==true){
    seat[i] = E9.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(E10.isSelected()==true&& E10.isEnabled()==true){
    seat[i] = E10.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(E11.isSelected()==true&& E11.isEnabled()==true){
    seat[i] = E11.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(E12.isSelected()==true&& E12.isEnabled()==true){
    seat[i] = E12.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(E13.isSelected()==true&& E13.isEnabled()==true){
    seat[i] = E13.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(E14.isSelected()==true&& E14.isEnabled()==true){
    seat[i] = E14.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(E15.isSelected()==true&& E15.isEnabled()==true){
    seat[i] = E15.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(E16.isSelected()==true&& E16.isEnabled()==true){
    seat[i] = E16.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(E17.isSelected()==true&& E17.isEnabled()==true){
    seat[i] = E17.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(E18.isSelected()==true&& E18.isEnabled()==true){
    seat[i] = E18.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(F1.isSelected()==true&& F1.isEnabled()==true){
    seat[i] = F1.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(F2.isSelected()==true&& F2.isEnabled()==true){
    seat[i] = F2.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(F3.isSelected()==true&& F3.isEnabled()==true){
    seat[i] = F3.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(F4.isSelected()==true&& F4.isEnabled()==true){
    seat[i] = F4.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(F5.isSelected()==true&& F5.isEnabled()==true){
    seat[i] = F5.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(F6.isSelected()==true&& F6.isEnabled()==true){
    seat[i] = F6.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(F7.isSelected()==true&& F7.isEnabled()==true){
    seat[i] = F7.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(F8.isSelected()==true&& F8.isEnabled()==true){
    seat[i] = F8.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(F9.isSelected()==true&& F9.isEnabled()==true){
    seat[i] = F9.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(F10.isSelected()==true&& F10.isEnabled()==true){
    seat[i] = F10.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(F11.isSelected()==true&& F11.isEnabled()==true){
    seat[i] = F11.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(F12.isSelected()==true&& F12.isEnabled()==true){
    seat[i] = F12.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(F13.isSelected()==true&& F13.isEnabled()==true){
    seat[i] = F13.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(F14.isSelected()==true&& F14.isEnabled()==true){
    seat[i] = F14.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(F15.isSelected()==true&& F15.isEnabled()==true){
    seat[i] = F15.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(F16.isSelected()==true&& F16.isEnabled()==true){
    seat[i] = F16.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(F17.isSelected()==true&& F17.isEnabled()==true){
    seat[i] = F17.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                      if(F18.isSelected()==true&& F18.isEnabled()==true){
    seat[i] = F18.getName();
    i++;
    if(i>=n){
        break seatinner;
    }
    }
                                              
    
    
}

checkinpassenger = new String[n];
outer: {inner: {try{
    Class.forName("java.sql.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
    Statement stmt = con.createStatement();
    String query = "select passengername from fwdflightinfo where pnr = '"+seatpnr+"';";
    ResultSet rst = stmt.executeQuery(query);
    int k = 0;
    if(rst.next()){
        checkinpassenger[k] = rst.getString(1);
        k++;
        while(rst.next()){
            checkinpassenger[k] = rst.getString(1);
            k++;
        }
        
    }
    else{
        break inner;
    }
}
catch(Exception e){
    JOptionPane.showMessageDialog(null, e.getMessage());
}
try{
    Class.forName("java.sql.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
    Statement stmt = con.createStatement();
    for(int m = 0;m<n;m++){
        String query = "update fwdflightinfo set seats = '"+seat[m]+"' where pnr = '"+seatpnr+"' and passengername = '"+checkinpassenger[m]+"';";
        stmt.executeUpdate(query);
    }
    
}
catch(Exception e){
    JOptionPane.showMessageDialog(null, e.getMessage());
}


Document doc = null;
try{
        Class.forName("java.sql.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
        Statement stmt = con.createStatement();
        String query = "select passengername,ifnull(seats,'not yet selected') from fwdflightinfo where pnr = '"+seatpnr+"';";
        ResultSet rst = stmt.executeQuery(query);
        doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(path + "\\"+seatpnr+"ticket.pdf"));
        doc.open();
        Path pathsearch1 = Paths.get("src\\flightreservationportal\\f15.png");
        String path1 = pathsearch1.toRealPath(LinkOption.NOFOLLOW_LINKS).toString();
        doc.add(Image.getInstance(path1));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        PdfPTable tbl = new PdfPTable(2);
        PdfPCell c1 = new PdfPCell(new Phrase("Passengers"));
        tbl.addCell(c1);
        c1 = new PdfPCell(new Phrase("Seat"));
        tbl.addCell(c1);
        tbl.setHeaderRows(1);
        while(rst.next()){
            tbl.addCell(rst.getString(1));
            tbl.addCell(rst.getString(2));
        }
        doc.add(tbl);
        
        con.close();
        stmt.close();
        rst.close();
        
        
        
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
try{
     Class.forName("java.sql.Driver");
        Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
        Statement stmt1 = con1.createStatement();
        String query1 = "select * from fwdbooking where pnr = '"+seatpnr+"';";
        ResultSet rst1 = stmt1.executeQuery(query1);
        
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        
        
        PdfPTable tbl1 = new PdfPTable(6);
        PdfPCell c11 = new PdfPCell(new Phrase("Flight Number"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("Timings"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("From "));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("To"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("Date of Journey"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("PNR"));
        tbl1.addCell(c11);
        
        while(rst1.next()){
            tbl1.addCell(rst1.getString(2));
            tbl1.addCell(rst1.getString(7));
            tbl1.addCell(rst1.getString(3));
            tbl1.addCell(rst1.getString(4));
            tbl1.addCell(rst1.getString(5));
            tbl1.addCell(rst1.getString(1));
        }
        doc.add(tbl1);
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph("If you have not yet selected your favourite seats then go to our CHECK - IN WINDOW and select your favourite seat for free ."));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph("Thank you for choosing "+"UNITED AIRWAYS !"));
        
        
        doc.close();
        
        con1.close();
        stmt1.close();
        rst1.close();
}
catch(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
break outer;
}
try{
    Class.forName("java.sql.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
    Statement stmt = con.createStatement();
    String query = "select passengername from rtnflightinfo where rtnpnr = '"+seatpnr+"';";
    ResultSet rst = stmt.executeQuery(query);
    int k = 0;
    if(rst.next()){
        checkinpassenger[k] = rst.getString(1);
        k++;
        while(rst.next()){
            checkinpassenger[k] = rst.getString(1);
            k++;
        }
        
    }
    else{
        JOptionPane.showMessageDialog(null, "Passenger not found");
    }
}
catch(Exception e){
    JOptionPane.showMessageDialog(null, e.getMessage());
}
try{
    Class.forName("java.sql.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
    Statement stmt = con.createStatement();
    for(int m = 0;m<n;m++){
        String query = "update rtnflightinfo set seats = '"+seat[m]+"' where rtnpnr = '"+seatpnr+"' and passengername = '"+checkinpassenger[m]+"';";
        stmt.executeUpdate(query);
    }
    
}
catch(Exception e){
    JOptionPane.showMessageDialog(null, e.getMessage());
}


Document doc = null;
try{
        Class.forName("java.sql.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
        Statement stmt = con.createStatement();
        String query = "select passengername,ifnull(seats,'not yet selected') from rtnflightinfo where rtnpnr = '"+seatpnr+"';";
        ResultSet rst = stmt.executeQuery(query);
        doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(path + "\\"+seatpnr+"ticket.pdf"));
        doc.open();
                Path pathsearch1 = Paths.get("src\\flightreservationportal\\f15.png");
        String path1 = pathsearch1.toRealPath(LinkOption.NOFOLLOW_LINKS).toString();
        doc.add(Image.getInstance(path1));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        PdfPTable tbl = new PdfPTable(2);
        PdfPCell c1 = new PdfPCell(new Phrase("Passengers"));
        tbl.addCell(c1);
        c1 = new PdfPCell(new Phrase("Seat"));
        tbl.addCell(c1);
        tbl.setHeaderRows(1);
        while(rst.next()){
            tbl.addCell(rst.getString(1));
            tbl.addCell(rst.getString(2));
        }
        doc.add(tbl);
        
        con.close();
        stmt.close();
        rst.close();
        
        
        
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
try{
     Class.forName("java.sql.Driver");
        Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
        Statement stmt1 = con1.createStatement();
        String query1 = "select * from rtnbooking where rtnpnr = '"+seatpnr+"';";
        ResultSet rst1 = stmt1.executeQuery(query1);
        
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        
        
        PdfPTable tbl1 = new PdfPTable(6);
        PdfPCell c11 = new PdfPCell(new Phrase("Flight Number"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("Timings"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("From "));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("To"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("Date of Journey"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("PNR"));
        tbl1.addCell(c11);
        
        while(rst1.next()){
            tbl1.addCell(rst1.getString(2));
            tbl1.addCell(rst1.getString(7));
            tbl1.addCell(rst1.getString(3));
            tbl1.addCell(rst1.getString(4));
            tbl1.addCell(rst1.getString(5));
            tbl1.addCell(rst1.getString(1));
        }
        doc.add(tbl1);

        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph("Thank you for choosing "+"UNITED AIRWAYS !"));
        
        
        doc.close();
        
        con1.close();
        stmt1.close();
        rst1.close();
}
catch(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
}
try{
    Class.forName("java.sql.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
    Statement stmt = con.createStatement();
    String query = "select email from user where userid = '"+userid+"' and password = '"+userpassword+"';";
    ResultSet rst = stmt.executeQuery(query);
    String email = "";
    while(rst.next()){
     email = rst.getString(1);
    }
    
    Properties p = new Properties();
p.put("mail.smtp.host", "smtp.gmail.com");
p.put("mail.smtp.auth", "true");
p.put("mail.smtp.starttls.enable", "true");
p.put("mail.smtp.port", "587");

Session s = Session.getDefaultInstance(p, new javax.mail.Authenticator() {

protected PasswordAuthentication getPasswordAuthentication(){
    return new PasswordAuthentication("unitedairways3101@gmail.com","ip_project1");
}

});

MimeMessage m = new MimeMessage(s);
    m.setFrom(new InternetAddress("unitedairways3101@gmail.com"));
    m.addRecipients(Message.RecipientType.TO,email);
    m.setSubject("Thank you for choosing us.");
    Multipart mc = new MimeMultipart();
    MimeBodyPart mb = new MimeBodyPart();
    mb.attachFile(path + "\\"+seatpnr+"ticket.pdf");
    MimeBodyPart mb1 = new MimeBodyPart();
    mb1.setText("Your ticket is updated. This is your ticket.");
    mc.addBodyPart(mb);
    mc.addBodyPart(mb1);
    m.setContent(mc);
    m.saveChanges();
    Transport.send(m);
}
catch(Exception e){
    JOptionPane.showMessageDialog(null, e.getMessage());
}
checkin.setVisible(false);
jFrame2.setVisible(true);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
int ad,ch,inf,price,totalpax,alt=0,rtnalt=0;
String frm,to,dptdt,rtndt,dptday,rtnday,fltno,departuretime,arrivaltime,dptdtalt,rtndtalt;
jRadioButton1.setVisible(false);
jRadioButton2.setVisible(false);
jRadioButton3.setVisible(false);
jRadioButton4.setVisible(false);
jRadioButton5.setVisible(false);
jRadioButton6.setVisible(false);

if(adult.isSelected()==true){
    ad =Integer.parseInt(adultno.getValue().toString());
}
else{
    ad = 0;
}
if(children.isSelected()==true){
    ch =Integer.parseInt(childno.getValue().toString());
}
else{
    ch = 0;
}
if(infant.isSelected()){
    inf =Integer.parseInt(infantno.getValue().toString());
}
else{
    inf = 0;
}
adultnumber = ad;
childnumber = ch;
infantnumber = inf;
totalpax = ad+ch+inf;
totalpass = totalpax;
if(totalpax == 0){
    JOptionPane.showMessageDialog(null, "Please select number of passengers.");
}
frm = fromtf.getText();
fromcity = frm;
to = totf.getText();
tocity = to;
dptdt = ((JTextField) departdate.getDateEditor().getUiComponent()).getText();
departuredisplaydate = dptdt;
departdate.getDateEditor().setDateFormatString("yyyy-MM-dd");
dptdtalt = ((JTextField) departdate.getDateEditor().getUiComponent()).getText();
forwarddate = dptdtalt;
departdate.getDateEditor().setDateFormatString("dd MMM ,yy");

int a = departdate.getDate().getDay();

dptday = GetDate(a);



if(oneway.isSelected()==true){
    bookflight.setSize(1346, 1000);
    bookflight.setVisible(true);
    bookflight.setLocationRelativeTo(null);
    try{
        Class.forName("java.sql.Driver");
        Connection conalt = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
        Statement stmtalt = conalt.createStatement();
        String queryalt = "select datediff('"+dptdtalt+"',curdate());";
        ResultSet rstalt = stmtalt.executeQuery(queryalt);
        while(rstalt.next()){
         alt = rstalt.getInt(1);
        }
        conalt.close();
        stmtalt.close();
        rstalt.close();
    }
    catch(Exception e1){
        JOptionPane.showMessageDialog(null, e1.getMessage());
    }
    try{
        
        
        Class.forName("java.sql.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
         
        Statement stmt = con.createStatement();
       
        String query = "select *,timediff(arrival,departure) as 'duration' from fd where City1 = '"+frm+"' and City2 = '"+to+"' and Day = '"+dptday+"' and arrival>departure;";
        
        String query1 ="select *,timediff(arrival,'00:00'),timediff('24:00',departure) from fd where City1 = '"+frm+"' and City2 = '"+to+"' and Day = '"+dptday+"' and arrival<departure;" ;
        
        ResultSet rst = stmt.executeQuery(query);
        
        
        
        
        jLabel29.setText(dptdt);
        jLabel30.setText(frm + "-->" + to);
        jLabel31.setText("Passengers:" + totalpax);
        
        
        
        JLabel label[] = {jLabel32,jLabel33,jLabel34,jLabel35,jLabel36,jLabel37,jLabel38,jLabel39,jLabel40,jLabel41,jLabel42,jLabel43};
        JRadioButton radiobutton[] ={jRadioButton1,jRadioButton2,jRadioButton3}; 
        
        int i = 0;
        int j = 0;
        
        
        while(rst.next()){
            fltno = rst.getString(2);
            departuretime = rst.getString(6);
            arrivaltime = rst.getString(7);
            price = rst.getInt(8);
            if(alt >= 15){
                price = price - 1500;
            }
            if(alt > 5 && alt<15){
                price = price - 750;
            }
            String time = rst.getString(9);
            timeduration = GetDuration(time);
            
            label[0+i].setText(fltno);
            label[1+i].setText(departuretime + "-" + arrivaltime);
            label[2+i].setText(timeduration);
            label[3+i].setText(""+price);
            radiobutton[j].setVisible(true);
            i = i +4;
            j++;
            
        }
        rst.close();
        stmt.close();
        con.close();
        
        Class.forName("java.sql.Driver");
        Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
        Statement stmt1 = con1.createStatement();
        ResultSet rst1 = stmt1.executeQuery(query1);
        while(rst1.next()){
            fltno = rst1.getString(2);
            departuretime = rst1.getString(6);
            arrivaltime = rst1.getString(7);
            price = rst1.getInt(8);
            if(alt >= 15){
                price = price - 1500;
            }
            if(alt > 5 && alt<15){
                price = price - 750;
            }
            timeduration = GetDuration1(rst1.getString(9),rst1.getString(10));
            label[0+i].setText(fltno);
            label[1+i].setText(departuretime + "-" + arrivaltime);
            label[2+i].setText(timeduration);
            label[3+i].setText(""+price);
            radiobutton[j].setVisible(true);
            
            
            
        }
        con1.close();
        stmt1.close();
        rst1.close();
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
}
if(rndtrp.isSelected()==true){
rtndt = ((JTextField) rtndate.getDateEditor().getUiComponent()).getText();
returndisplaydate = rtndt;
rtndate.getDateEditor().setDateFormatString("yyyy-MM-dd");
rtndtalt = ((JTextField) rtndate.getDateEditor().getUiComponent()).getText();
returndate = rtndtalt;
rtnfromcity = to;
rtntocity = frm;
rtndate.getDateEditor().setDateFormatString("dd MMM ,yy");
int b = rtndate.getDate().getDay();
rtnday = GetDate(b);
bookflight.setSize(1346, 1000);
bookflight.setVisible(true);
bookflight.setLocationRelativeTo(null);
    try{
        Class.forName("java.sql.Driver");
        Connection conalt = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
        Statement stmtalt = conalt.createStatement();
        String queryalt = "select datediff('"+dptdtalt+"',curdate());";
        ResultSet rstalt = stmtalt.executeQuery(queryalt);
        while(rstalt.next()){
         alt = rstalt.getInt(1);
        }
        
        conalt.close();
        stmtalt.close();
        rstalt.close();
        

    }
    catch(Exception e1){
        JOptionPane.showMessageDialog(null, e1.getMessage());
    }
    
    
    try{
       
        
        Class.forName("java.sql.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
         
        Statement stmt = con.createStatement();
       
        String query = "select *,timediff(arrival,departure) as 'duration' from fd where City1 = '"+frm+"' and City2 = '"+to+"' and Day = '"+dptday+"' and arrival>departure;";
        String query1 ="select *,timediff(arrival,'00:00'),timediff('24:00',departure) from fd where City1 = '"+frm+"' and City2 = '"+to+"' and Day = '"+dptday+"' and arrival<departure;" ;
        
        ResultSet rst = stmt.executeQuery(query);
        
        
        
        
        jLabel29.setText(dptdt);
        jLabel30.setText(frm + "-->" + to);
        jLabel31.setText("Passengers:" + totalpax);
       
        
        JLabel label[] = {jLabel32,jLabel33,jLabel34,jLabel35,jLabel36,jLabel37,jLabel38,jLabel39,jLabel40,jLabel41,jLabel42,jLabel43};
        JRadioButton radiobutton[] ={jRadioButton1,jRadioButton2,jRadioButton3}; 
        
        int i = 0;
        int j = 0;
        
        
        while(rst.next()){
            fltno = rst.getString(2);
            departuretime = rst.getString(6);
            arrivaltime = rst.getString(7);
            price = rst.getInt(8);
            if(alt >= 15){
                price = price - 1500;
            }
            if(alt > 5 && alt<15){
                price = price - 750;
            }
            String time = rst.getString(9);
            timeduration = GetDuration(time);
            
            label[0+i].setText(fltno);
            label[1+i].setText(departuretime + "-" + arrivaltime);
            label[2+i].setText(timeduration);
            label[3+i].setText(""+price);
            radiobutton[j].setVisible(true);
            i = i +4;
            j++;
            
        }
        rst.close();
        stmt.close();
        con.close();
        
        Class.forName("java.sql.Driver");
        Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
        Statement stmt1 = con1.createStatement();
        ResultSet rst1 = stmt1.executeQuery(query1);
        while(rst1.next()){
            fltno = rst1.getString(2);
            departuretime = rst1.getString(6);
            arrivaltime = rst1.getString(7);
            price = rst1.getInt(8);
            if(alt >= 15){
                price = price - 1500;
            }
            if(alt > 5 && alt<15){
                price = price - 750;
            }
            timeduration = GetDuration1(rst1.getString(9),rst1.getString(10));
            label[0+i].setText(fltno);
            label[1+i].setText(departuretime + "-" + arrivaltime);
            label[2+i].setText(timeduration);
            label[3+i].setText(""+price);
            radiobutton[j].setVisible(true);
            i = i +4;
            j++;
            
            
        }
        con1.close();
        stmt1.close();
        rst1.close();
        
       
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
    try{
         Class.forName("java.sql.Driver");
        Connection rtnconalt = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
        Statement rtnstmtalt = rtnconalt.createStatement();
        String rtnqueryalt = "select datediff('"+rtndtalt+"',curdate());";
        
        ResultSet rtnrstalt = rtnstmtalt.executeQuery(rtnqueryalt);
        while(rtnrstalt.next()){
        rtnalt = rtnrstalt.getInt(1);
        }
       
        rtnconalt.close();
        rtnstmtalt.close();
        rtnrstalt.close();
    }
    catch(Exception e2){
        JOptionPane.showMessageDialog(null, e2.getMessage());
    }
    
    try{
        String rtnquery = "select *,timediff(arrival,departure) as 'duration' from fd where City1 = '"+to+"' and City2 = '"+frm+"' and Day = '"+rtnday+"' and arrival>departure;";
        String rtnquery1 ="select *,timediff(arrival,'00:00'),timediff('24:00',departure) from fd where City1 = '"+to+"' and City2 = '"+frm+"' and Day = '"+rtnday+"' and arrival<departure;" ;
        
        jLabel56.setText(rtndt);
        jLabel57.setText(to + "-->" + frm);
        jLabel58.setText("Passengers:" + totalpax);
        
         Class.forName("java.sql.Driver");
        Connection rtncon = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
        Statement rtnstmt = rtncon.createStatement();
        ResultSet rtnrst = rtnstmt.executeQuery(rtnquery);
        int k = 0;
        int l = 0;
        JLabel rtnlabel[] = {jLabel44,jLabel45,jLabel46,jLabel47,jLabel48,jLabel49,jLabel50,jLabel51,jLabel52,jLabel53,jLabel54,jLabel55};
        JRadioButton rtnradiobutton[] = {jRadioButton4,jRadioButton5,jRadioButton6};
        while(rtnrst.next()){
            fltno = rtnrst.getString(2);
            departuretime = rtnrst.getString(6);
            arrivaltime = rtnrst.getString(7);
            price = rtnrst.getInt(8);
            if(rtnalt >= 15){
                price = price - 1500;
            }
            if(rtnalt > 5 && rtnalt<15){
                price = price - 750;
            }
            timeduration = GetDuration(rtnrst.getString(9));
            rtnlabel[0+k].setText(fltno);
            rtnlabel[1+k].setText(departuretime + "-" + arrivaltime);
            rtnlabel[2+k].setText(timeduration);
            rtnlabel[3+k].setText(""+price);
            rtnradiobutton[l].setVisible(true);
             k = k +4;
            l++;
            
            
        }
        rtncon.close();
        rtnstmt.close();
        rtnrst.close();
        
        Class.forName("java.sql.Driver");
        Connection rtncon1 = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
        Statement rtnstmt1 = rtncon1.createStatement();
        ResultSet rtnrst1 = rtnstmt1.executeQuery(rtnquery1);
        while(rtnrst1.next()){
            fltno = rtnrst1.getString(2);
            departuretime = rtnrst1.getString(6);
            arrivaltime = rtnrst1.getString(7);
            price = rtnrst1.getInt(8);
            if(rtnalt >= 15){
                price = price - 1500;
            }
            if(rtnalt > 5 && rtnalt<15){
                price = price - 750;
            }
            timeduration = GetDuration1(rtnrst1.getString(9),rtnrst1.getString(10));
            rtnlabel[0+k].setText(fltno);
            rtnlabel[1+k].setText(departuretime + "-" + arrivaltime);
            rtnlabel[2+k].setText(timeduration);
            rtnlabel[3+k].setText(""+price);
            rtnradiobutton[l].setVisible(true);
            k = k +4;
            l++;
            
            
        }
        rtncon1.close();
        rtnstmt1.close();
        rtnrst1.close();
    }
    catch(Exception e3){
        JOptionPane.showMessageDialog(null, e3.getMessage());
    }
}


        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void stateselectStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_stateselectStateChanged
if(frombtn.isSelected()==true){
    String strtf = (String) stateselect.getValue();
    fromtf.setText(strtf);
}
if(tobtn.isSelected()==true){
    String strtf = (String) stateselect.getValue();
    totf.setText(strtf);
}

        // TODO add your handling code here:
    }//GEN-LAST:event_stateselectStateChanged

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
String str1 = fromtf.getText();
String str2 = totf.getText();
fromtf.setText(str2);
totf.setText(str1);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void onewayStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_onewayStateChanged
if(oneway.isSelected()==true){
    departdate.setEnabled(true);
}
else{
    departdate.setEnabled(false);
}
        // TODO add your handling code here:
    }//GEN-LAST:event_onewayStateChanged

    private void rndtrpStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rndtrpStateChanged
if(rndtrp.isSelected()==true){
    departdate.setEnabled(true);
    rtndate.setEnabled(true);
}
else{
    departdate.setEnabled(false);
    rtndate.setEnabled(false);
    
}

        // TODO add your handling code here:
    }//GEN-LAST:event_rndtrpStateChanged

    private void frombtnStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_frombtnStateChanged
if(frombtn.isSelected()==true){
    String strtf = (String) stateselect.getValue();
    fromtf.setText(strtf);
    
}
        // TODO add your handling code here:
    }//GEN-LAST:event_frombtnStateChanged

    private void tobtnStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tobtnStateChanged
if(tobtn.isSelected()==true){
    String strtf = (String) stateselect.getValue();
    totf.setText(strtf);
    
}
        // TODO add your handling code here:
    }//GEN-LAST:event_tobtnStateChanged

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
  if(jRadioButton1.isSelected()==true){
    Component cmp[] = jPanel8.getComponents();
    int n = cmp.length;
    for(int i = 0;i<n;i++){
        if(cmp[i] instanceof JLabel){
           ticketinfo[i] = ((JLabel)cmp[i]).getText();
           
        }
    }
}
else{
    Component cmp[] = jPanel8.getComponents();
    int n = cmp.length;
    for(int i = 0;i<n;i++){
        if(cmp[i] instanceof JLabel){
           ticketinfo[i] = "";
            
        }
    }
}      // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
 if(jRadioButton2.isSelected()==true){
    Component cmp[] = jPanel9.getComponents();
    int n = cmp.length;
    for(int i = 0;i<n;i++){
        if(cmp[i] instanceof JLabel){
           ticketinfo[i] = ((JLabel)cmp[i]).getText();
            
        }
    }
}
else{
    Component cmp[] = jPanel9.getComponents();
    int n = cmp.length;
    for(int i = 0;i<n;i++){
        if(cmp[i] instanceof JLabel){
           ticketinfo[i] = "";
            
        }
    }
}       // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
 if(jRadioButton3.isSelected()==true){
    Component cmp[] = jPanel10.getComponents();
    int n = cmp.length;
    for(int i = 0;i<n;i++){
        if(cmp[i] instanceof JLabel){
           ticketinfo[i] = ((JLabel)cmp[i]).getText();
            
        }
    }
}
else{
    Component cmp[] = jPanel10.getComponents();
    int n = cmp.length;
    for(int i = 0;i<n;i++){
        if(cmp[i] instanceof JLabel){
           ticketinfo[i] = "";
            
        }
    }
}       // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
 if(jRadioButton4.isSelected()==true){
    Component cmp[] = jPanel11.getComponents();
    int n = cmp.length;
    for(int i = 0;i<n;i++){
        if(cmp[i] instanceof JLabel){
           ticketinfo[i+4] = ((JLabel)cmp[i]).getText();
            
        }
    }
}
else{
    Component cmp[] = jPanel11.getComponents();
    int n = cmp.length;
    for(int i = 0;i<n;i++){
        if(cmp[i] instanceof JLabel){
           ticketinfo[i+4] = "";
           
        }
    }
}       // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
 if(jRadioButton5.isSelected()==true){
    Component cmp[] = jPanel12.getComponents();
    int n = cmp.length;
    for(int i = 0;i<n;i++){
        if(cmp[i] instanceof JLabel){
           ticketinfo[i+4] = ((JLabel)cmp[i]).getText();
           
        }
    }
}
else{
    Component cmp[] = jPanel12.getComponents();
    int n = cmp.length;
    for(int i = 0;i<n;i++){
        if(cmp[i] instanceof JLabel){
           ticketinfo[i+4] = "";
           
        }
    }
}       // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton6ActionPerformed
 if(jRadioButton6.isSelected()==true){
    Component cmp[] = jPanel13.getComponents();
    int n = cmp.length;
    for(int i = 0;i<n;i++){
        if(cmp[i] instanceof JLabel){
           ticketinfo[i+4] = ((JLabel)cmp[i]).getText();
           
        }
    }
}
else{
    Component cmp[] = jPanel13.getComponents();
    int n = cmp.length;
    for(int i = 0;i<n;i++){
        if(cmp[i] instanceof JLabel){
           ticketinfo[i+4] = "";
           
        }
    }
}       // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton6ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
addtraveller.setVisible(true);
addtraveller.setLocationRelativeTo(null);
bookflight.setVisible(false);
bookflight.setLocationRelativeTo(null);
for(int i = 1;i<=totalpass;i++){
    JLabel label = new JLabel();
    label.setText("Passenger " + i);
    int j = i-1;
    label.setBounds(50, 20+50*j, 100, 40);
    passno.add(label);
    passno.validate();
    passno.repaint();
    
    JRadioButton radiomr = new JRadioButton();
    JRadioButton radiomrs = new JRadioButton();
    JRadioButton radiomiss = new JRadioButton();
    radiomr.setText("Mr.");
    radiomr.setBounds(15, 20+50*j, 50, 25);
  
    radiomrs.setText("Mrs.");
    radiomrs.setBounds(75, 20+50*j, 55, 25);
    
    radiomiss.setText("Miss.");
    radiomiss.setBounds(135, 20+50*j, 60, 25);
    
    ButtonGroup btn = new ButtonGroup();
    btn.add(radiomr);
    btn.add(radiomrs);
    btn.add(radiomiss);
    passname.add(radiomr);
    passname.add(radiomrs);
    passname.add(radiomiss);
    passname.validate();
    passname.repaint();
    
    JTextField textfield = new JTextField();
    textfield.setBounds(215, 20+50*j,250, 25);
    
    passname.add(textfield);
    passname.validate();
    passname.repaint();
    
}
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
passengername = new String[totalpass];
for(int i = 1;i<=totalpass;i++){
    String initials = "";
    String passenger = "";
int j = i-1;
Component cmp1 = passname.getComponentAt(15, 20+50*j);
Component cmp2 = passname.getComponentAt(75, 20+50*j);
Component cmp3 = passname.getComponentAt(135, 20+50*j);
Component cmp4 = passname.getComponentAt(215, 20+50*j);


inner: {if(cmp1 instanceof JRadioButton){
    
   boolean radiobutton = ((JRadioButton)cmp1).isSelected();
   
   if(radiobutton == true){
       initials = ((JRadioButton)cmp1).getText();
       break inner;
       
   }
   else{
       initials = "";
   }
}
if(cmp2 instanceof JRadioButton){
   boolean radiobutton = ((JRadioButton)cmp2).isSelected();
   
   if(radiobutton == true){
       initials = ((JRadioButton)cmp2).getText();
       break inner;
   }
   else{
       initials = "";
   }
}
if(cmp3 instanceof JRadioButton){
   boolean radiobutton = ((JRadioButton)cmp3).isSelected();
   
   if(radiobutton == true){
      initials = ((JRadioButton)cmp3).getText();
      break inner;
   }
   else{
       initials = "";
   }
}}
if(cmp4 instanceof JTextField){
    passenger = ((JTextField)cmp4).getText();
    
}


String info = initials + " " + passenger;

passengername[j] = info;

}
transaction.setVisible(true);
transaction.setLocationRelativeTo(null);
addtraveller.setVisible(false);
addtraveller.setLocationRelativeTo(null);
if(oneway.isSelected()==true){
    double d = 0;
    int amount = Integer.parseInt(ticketinfo[3]);
    int netamount = adultnumber*amount + childnumber*amount + infantnumber*1500;
    if(armedbtn.isSelected()==true){
        d = 0.05;
    }
    if(seniorbtn.isSelected()==true){
        d = 0.08;
    }
    if(stnbtn.isSelected()==true){
        d = 0.15;
    }
    if(schltrip.isSelected()==true){
        d = 0.25;
    }
    amt.setText(""+netamount*(1-d));
}
if(rndtrp.isSelected()==true){
    double d = 0;
    int amount = Integer.parseInt(ticketinfo[3]);
    int rtnamount = Integer.parseInt(ticketinfo[7]);
    int netamount = adultnumber*(amount+rtnamount) + childnumber*(amount+rtnamount) + infantnumber*3000;
        if(armedbtn.isSelected()==true){
        d = 0.05;
    }
    if(seniorbtn.isSelected()==true){
        d = 0.08;
    }
    if(stnbtn.isSelected()==true){
        d = 0.15;
    }
    if(schltrip.isSelected()==true){
        d = 0.25;
    }
    amt.setText(""+netamount*(1-d));
}
// TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void mopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mopActionPerformed

int n = mop.getSelectedIndex();

if(n == 0 || n == 1){
dcorcc.setVisible(true);
qrpanel.setSize(new Dimension(304,202));
 jPanel16.setVisible(false);
 jPanel17.setVisible(false);
}
if(n == 2){
    dcorcc.setVisible(false);
jPanel17.setVisible(false);
    
    JRadioButton rd1 = new JRadioButton();
    JRadioButton rd2 = new JRadioButton();
    
    ButtonGroup btn = new ButtonGroup();
    rd1.setBounds(200, 0, 100, 50);
    rd2.setBounds(200, 60, 100, 50);
    rd1.setText("Axis Bank");
    rd2.setText("SBI Bank");
    rd1.addActionListener(new java.awt.event.ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            
            if(rd1.isSelected()==true){
                String url = "https://www.axisbank.com/bank-smart/internet-banking";
                try {
                    java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
                } catch (IOException ex) {
                    Logger.getLogger(userend.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    });
    rd2.addActionListener(new java.awt.event.ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            
            if(rd2.isSelected()==true){
                String url = "https://retail.onlinesbi.com/personal/";
                try {
                    java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
                } catch (IOException ex) {
                    Logger.getLogger(userend.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    });
    rd1.setVisible(true);
    rd2.setVisible(true);
    btn.add(rd2);
    btn.add(rd1);
    
    jPanel16.add(rd1);
    jPanel16.add(rd2);
    jPanel16.validate();
    jPanel16.repaint();
    jPanel16.setVisible(true);
}
if(n == 3){
    try {
        dcorcc.setVisible(false);
        jPanel16.setVisible(false);
        JOptionPane.showMessageDialog(null, "Make sure that you have the amount in your wallet.");
        
        
        JLabel label = new JLabel();
        label.setBounds(0, 0, 200, 200);
        jPanel17.add(label);
        jPanel17.validate();
        jPanel17.repaint();
        jPanel17.setVisible(true);
        Path pathsearch1 = Paths.get("src\\flightreservationportal\\abhiqr.jpg");
        String path1 = pathsearch1.toRealPath(LinkOption.NOFOLLOW_LINKS).toString();
        
        label.setIcon(new ImageIcon(path1));
    } catch (IOException ex) {
        Logger.getLogger(userend.class.getName()).log(Level.SEVERE, null, ex);
    }
}
        // TODO add your handling code here:
    }//GEN-LAST:event_mopActionPerformed

    private void jPanel15AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel15AncestorAdded
dcorcc.setVisible(false);
qrpanel.setVisible(false);
 jPanel16.setVisible(false);
jPanel17.setVisible(false);// TODO add your handling code here:
    }//GEN-LAST:event_jPanel15AncestorAdded

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
qrpanel.setVisible(true);
String a = card1.getText();
String b = card2.getText();
String c = card3.getText();
String d = card4.getText();
String cardnumber = a + "" + b + "" + c + "" + d ;
String expirymonth = GetMonth(expmonth.getMonth());
int year = expyear.getYear();
cvvno = new String(cvv.getPassword());
try{
    Class.forName("java.sql.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
    Statement stmt = con.createStatement();
    String query = "insert into userdcorcc values('"+cardnumber+"','"+expirymonth+"',"+year+",'"+cvvno+"');";
    int rst = stmt.executeUpdate(query);
}
catch(Exception e){
    JOptionPane.showMessageDialog(null, e.getMessage());
}

Random r = new Random();
int r1 = r.nextInt(9-1+1)+1;
int r2 = r.nextInt(9-1+1)+1;
int r3 = r.nextInt(9-1+1)+1;
int r4 = r.nextInt(9-1+1)+1;
codes = r1 + "" +r2 + ""+r3+""+r4;

ByteArrayOutputStream out = QRCode.from(codes).to(ImageType.JPG).stream();

byte[] bit = out.toByteArray();
qrlabel.setIcon(new ImageIcon(""));
qrlabel.setIcon(new ImageIcon(bit));


// TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void payActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payActionPerformed
String check = codetf.getText();
if(check.equals(codes)){
    try{
        if(oneway.isSelected()==true){
            generatedpnr = generatepnr(totalpass);
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
            Statement stmt = con.createStatement();
            String query = "delete from fwdbooking where curdate()>doj;";
            stmt.executeUpdate(query);
          
            String entryquery="insert into fwdbooking values('"+generatedpnr+"','"+ticketinfo[0]+"','"+fromcity+"','"+tocity+"','"+forwarddate+"',"+totalpass+",'"+ticketinfo[1]+"');";
            stmt.executeUpdate(entryquery);
            for(int i = 0;i<totalpass;i++){
            String entryqueryalt = "insert into fwdflightinfo(pnr,passengername) values('"+generatedpnr+"','"+passengername[i]+"');";
            stmt.executeUpdate(entryqueryalt);
            }
            String delquery = "delete from userdcorcc where cvv = '"+cvvno+"';";
        stmt.executeUpdate(delquery);
        }
        if(rndtrp.isSelected()==true){
            generatedpnr = generatepnr(totalpass);
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
            Statement stmt = con.createStatement();
            String query = "delete from fwdbooking where curdate()>doj;";
            stmt.executeUpdate(query);
          
            String entryquery="insert into fwdbooking values('"+generatedpnr+"','"+ticketinfo[0]+"','"+fromcity+"','"+tocity+"','"+forwarddate+"',"+totalpass+",'"+ticketinfo[1]+"');";
            stmt.executeUpdate(entryquery);
            for(int i = 0;i<totalpass;i++){
            String entryqueryalt = "insert into fwdflightinfo(pnr,passengername) values('"+generatedpnr+"','"+passengername[i]+"');";
            stmt.executeUpdate(entryqueryalt);
            }
            
            rtngeneratedpnr = generatepnr(totalpass);
            String rtnquery = "delete from rtnbooking where curdate()>rtndoj;";
            stmt.executeUpdate(rtnquery);
          
            String rtnentryquery="insert into rtnbooking values('"+rtngeneratedpnr+"','"+ticketinfo[4]+"','"+rtnfromcity+"','"+rtntocity+"','"+returndate+"',"+totalpass+",'"+ticketinfo[5]+"');";
            stmt.executeUpdate(rtnentryquery);
            for(int i = 0;i<totalpass;i++){
            String entryqueryalt = "insert into rtnflightinfo(rtnpnr,passengername) values('"+rtngeneratedpnr+"','"+passengername[i]+"');";
            stmt.executeUpdate(entryqueryalt);
            }
            String delquery = "delete from userdcorcc where cvv = '"+cvvno+"';";
        stmt.executeUpdate(delquery);
        }
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
}
else{
    JOptionPane.showMessageDialog(null, "code entered is wrong. Again click the CONTINUE button to regenerate new code...");
    codes= "";
    
    try{
        Class.forName("java.sql.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
        Statement stmt = con.createStatement();
        String delquery = "delete from userdcorcc where cvv = '"+cvvno+"';";
        stmt.executeUpdate(delquery);
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
   
}
JOptionPane.showMessageDialog(null, "Thank you for choosing us. Your tickets are booked , we will send your ticket on your mail and also you can save it from here.");


if(oneway.isSelected()==true){
    Document doc = null;
try{
        Class.forName("java.sql.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
        Statement stmt = con.createStatement();
        String query = "select passengername,ifnull(seats,'not yet selected') from fwdflightinfo where pnr = '"+generatedpnr+"';";
        ResultSet rst = stmt.executeQuery(query);
        doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(path + "\\"+generatedpnr+"ticket.pdf"));
        doc.open();
                Path pathsearch1 = Paths.get("src\\flightreservationportal\\f15.png");
        String path1 = pathsearch1.toRealPath(LinkOption.NOFOLLOW_LINKS).toString();
        doc.add(Image.getInstance(path1));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        PdfPTable tbl = new PdfPTable(2);
        PdfPCell c1 = new PdfPCell(new Phrase("Passengers"));
        tbl.addCell(c1);
        c1 = new PdfPCell(new Phrase("Seat"));
        tbl.addCell(c1);
        tbl.setHeaderRows(1);
        while(rst.next()){
            tbl.addCell(rst.getString(1));
            tbl.addCell(rst.getString(2));
        }
        doc.add(tbl);
        
        con.close();
        stmt.close();
        rst.close();
        
        
        
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
try{
     Class.forName("java.sql.Driver");
        Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
        Statement stmt1 = con1.createStatement();
        String query1 = "select * from fwdbooking where pnr = '"+generatedpnr+"';";
        ResultSet rst1 = stmt1.executeQuery(query1);
        
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        
        
        PdfPTable tbl1 = new PdfPTable(6);
        PdfPCell c11 = new PdfPCell(new Phrase("Flight Number"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("Timings"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("From "));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("To"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("Date of Journey"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("PNR"));
        tbl1.addCell(c11);
        
        while(rst1.next()){
            tbl1.addCell(rst1.getString(2));
            tbl1.addCell(rst1.getString(7));
            tbl1.addCell(rst1.getString(3));
            tbl1.addCell(rst1.getString(4));
            tbl1.addCell(departuredisplaydate);
            tbl1.addCell(rst1.getString(1));
        }
        doc.add(tbl1);
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph("If you have not yet selected your favourite seats then go to our CHECK - IN WINDOW and select your favourite seat for free ."));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph("Thank you for choosing "+"UNITED AIRWAYS !"));
        
        
        doc.close();
        
        con1.close();
        stmt1.close();
        rst1.close();
        
}
catch(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
try{
    Class.forName("java.sql.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
    Statement stmt = con.createStatement();
    String query = "select email from user where userid = '"+userid+"' and password = '"+userpassword+"';";
    ResultSet rst = stmt.executeQuery(query);
    String email = "";
    while(rst.next()){
     email = rst.getString(1);
    }
    
    Properties p = new Properties();
p.put("mail.smtp.host", "smtp.gmail.com");
p.put("mail.smtp.auth", "true");
p.put("mail.smtp.starttls.enable", "true");
p.put("mail.smtp.port", "587");

Session s = Session.getDefaultInstance(p, new javax.mail.Authenticator() {

protected PasswordAuthentication getPasswordAuthentication(){
    return new PasswordAuthentication("unitedairways3101@gmail.com","ip_project1");
}

});

MimeMessage m = new MimeMessage(s);
    m.setFrom(new InternetAddress("unitedairways3101@gmail.com"));
    m.addRecipients(Message.RecipientType.TO,email);
    m.setSubject("Thank you for choosing us.");
    Multipart mc = new MimeMultipart();
    MimeBodyPart mb = new MimeBodyPart();
    mb.attachFile(path + "\\"+generatedpnr+"ticket.pdf");
    MimeBodyPart mb1 = new MimeBodyPart();
    mb1.setText("Your ticket is booked. This is your ticket.");
    mc.addBodyPart(mb);
    mc.addBodyPart(mb1);
    m.setContent(mc);
    m.saveChanges();
    Transport.send(m);
}
catch(Exception e){
    JOptionPane.showMessageDialog(null, e.getMessage());
}
ByteArrayOutputStream out = QRCode.from(generatedpnr).to(ImageType.JPG).stream();
byte bit[]=out.toByteArray();
qrdisplay.setIcon(new ImageIcon(bit));

}

if(rndtrp.isSelected()==true){
Document doc = null;
try{
        Class.forName("java.sql.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
        Statement stmt = con.createStatement();
        String query = "select passengername,ifnull(seats,'not yet selected') from fwdflightinfo where pnr = '"+generatedpnr+"';";
        ResultSet rst = stmt.executeQuery(query);
        doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(path + "\\"+generatedpnr+"ticket.pdf"));
        doc.open();
                Path pathsearch1 = Paths.get("src\\flightreservationportal\\f15.png");
        String path1 = pathsearch1.toRealPath(LinkOption.NOFOLLOW_LINKS).toString();
        doc.add(Image.getInstance(path1));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        PdfPTable tbl = new PdfPTable(2);
        PdfPCell c1 = new PdfPCell(new Phrase("Passengers"));
        tbl.addCell(c1);
        c1 = new PdfPCell(new Phrase("Seat"));
        tbl.addCell(c1);
        tbl.setHeaderRows(1);
        while(rst.next()){
            tbl.addCell(rst.getString(1));
            tbl.addCell(rst.getString(2));
        }
        doc.add(tbl);
        
        con.close();
        stmt.close();
        rst.close();
        
        
        
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
try{
     Class.forName("java.sql.Driver");
        Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
        Statement stmt1 = con1.createStatement();
        String query1 = "select * from fwdbooking where pnr = '"+generatedpnr+"';";
        ResultSet rst1 = stmt1.executeQuery(query1);
        
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        
        
        PdfPTable tbl1 = new PdfPTable(6);
        PdfPCell c11 = new PdfPCell(new Phrase("Flight Number"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("Timings"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("From "));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("To"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("Date of Journey"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("PNR"));
        tbl1.addCell(c11);
        
        while(rst1.next()){
            tbl1.addCell(rst1.getString(2));
            tbl1.addCell(rst1.getString(7));
            tbl1.addCell(rst1.getString(3));
            tbl1.addCell(rst1.getString(4));
            tbl1.addCell(departuredisplaydate);
            tbl1.addCell(rst1.getString(1));
        }
        doc.add(tbl1);
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph("If you have not yet selected your favourite seats then go to our CHECK - IN WINDOW and select your favourite seat for free ."));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph("Thank you for choosing "+"UNITED AIRWAYS !"));
        
        
        doc.close();
        con1.close();
        stmt1.close();
        rst1.close();
        
}
catch(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
Document doc1 = null;
try{
        Class.forName("java.sql.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
        Statement stmt = con.createStatement();
        String query = "select passengername,ifnull(seats,'not yet selected') from rtnflightinfo where rtnpnr = '"+rtngeneratedpnr+"';";
        ResultSet rst = stmt.executeQuery(query);
        doc1 = new Document();
        PdfWriter.getInstance(doc1, new FileOutputStream(path + "\\"+rtngeneratedpnr+"ticket.pdf"));
        doc1.open();
                Path pathsearch1 = Paths.get("src\\flightreservationportal\\f15.png");
        String path1 = pathsearch1.toRealPath(LinkOption.NOFOLLOW_LINKS).toString();
        doc1.add(Image.getInstance(path1));
        doc1.add(new Paragraph(" "));
        doc1.add(new Paragraph(" "));
        doc1.add(new Paragraph(" "));
        PdfPTable tbl = new PdfPTable(2);
        PdfPCell c1 = new PdfPCell(new Phrase("Passengers"));
        tbl.addCell(c1);
        c1 = new PdfPCell(new Phrase("Seat"));
        tbl.addCell(c1);
        tbl.setHeaderRows(1);
        while(rst.next()){
            tbl.addCell(rst.getString(1));
            tbl.addCell(rst.getString(2));
        }
        doc1.add(tbl);
        
        con.close();
        stmt.close();
        rst.close();
        
        
        
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
try{
     Class.forName("java.sql.Driver");
        Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
        Statement stmt1 = con1.createStatement();
        String query1 = "select * from rtnbooking where rtnpnr = '"+rtngeneratedpnr+"';";
        ResultSet rst1 = stmt1.executeQuery(query1);
        
        doc1.add(new Paragraph(" "));
        doc1.add(new Paragraph(" "));
        
        
        PdfPTable tbl1 = new PdfPTable(6);
        PdfPCell c11 = new PdfPCell(new Phrase("Flight Number"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("Timings"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("From "));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("To"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("Date of Journey"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("PNR"));
        tbl1.addCell(c11);
        
        while(rst1.next()){
            tbl1.addCell(rst1.getString(2));
            tbl1.addCell(rst1.getString(7));
            tbl1.addCell(rst1.getString(3));
            tbl1.addCell(rst1.getString(4));
            tbl1.addCell(returndisplaydate);
            tbl1.addCell(rst1.getString(1));
        }
        doc1.add(tbl1);
        doc1.add(new Paragraph(" "));
        doc1.add(new Paragraph(" "));
        doc1.add(new Paragraph("If you have not yet selected your favourite seats then go to our CHECK - IN WINDOW and select your favourite seat for free ."));
        doc1.add(new Paragraph(" "));
        doc1.add(new Paragraph(" "));
        doc1.add(new Paragraph("Thank you for choosing "+"UNITED AIRWAYS !"));
        
        
        doc1.close();
        con1.close();
        stmt1.close();
        rst1.close();
        
}
catch(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
try{
    Class.forName("java.sql.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
    Statement stmt = con.createStatement();
    String query = "select email from user where userid = '"+userid+"' and password = '"+userpassword+"';";
    ResultSet rst = stmt.executeQuery(query);
    String email = "";
    while(rst.next()){
     email = rst.getString(1);
    }
    
    Properties p = new Properties();
p.put("mail.smtp.host", "smtp.gmail.com");
p.put("mail.smtp.auth", "true");
p.put("mail.smtp.starttls.enable", "true");
p.put("mail.smtp.port", "587");

Session s = Session.getDefaultInstance(p, new javax.mail.Authenticator() {

protected PasswordAuthentication getPasswordAuthentication(){
    return new PasswordAuthentication("unitedairways3101@gmail.com","ip_project1");
}

});

MimeMessage m = new MimeMessage(s);
    m.setFrom(new InternetAddress("unitedairways3101@gmail.com"));
    m.addRecipients(Message.RecipientType.TO,email);
    m.setSubject("Thank you for choosing us.");
    Multipart mc = new MimeMultipart();
    MimeBodyPart mb = new MimeBodyPart();
    mb.attachFile(path + "\\"+generatedpnr+"ticket.pdf");
    MimeBodyPart rtnmb = new MimeBodyPart();
    rtnmb.attachFile(path + "\\"+rtngeneratedpnr+"ticket.pdf");
    MimeBodyPart mb1 = new MimeBodyPart();
    mb1.setText("Your ticket is booked. This is your ticket.");
    mc.addBodyPart(mb);
    mc.addBodyPart(mb1);
    mc.addBodyPart(rtnmb);
    m.setContent(mc);
    m.saveChanges();
    Transport.send(m);
}
catch(Exception e){
    JOptionPane.showMessageDialog(null, e.getMessage());
}

ByteArrayOutputStream out2 = QRCode.from(generatedpnr).to(ImageType.JPG).stream();
byte bit2[]=out2.toByteArray();
qrdisplay.setIcon(new ImageIcon(bit2));
ByteArrayOutputStream out1 = QRCode.from(rtngeneratedpnr).to(ImageType.JPG).stream();
byte bit1[]=out1.toByteArray();
qrdisplay1.setIcon(new ImageIcon(bit1));
}
jDialog1.setVisible(true);
jDialog1.setLocationRelativeTo(null);

        // TODO add your handling code here:
    }//GEN-LAST:event_payActionPerformed

    private void payaltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payaltActionPerformed
try{
        if(oneway.isSelected()==true){
            generatedpnr = generatepnr(totalpass);
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
            Statement stmt = con.createStatement();
            String query = "delete from fwdbooking where curdate()>doj;";
            stmt.executeUpdate(query);
          
            String entryquery="insert into fwdbooking values('"+generatedpnr+"','"+ticketinfo[0]+"','"+fromcity+"','"+tocity+"','"+forwarddate+"',"+totalpass+",'"+ticketinfo[1]+"');";
            stmt.executeUpdate(entryquery);
            for(int i = 0;i<totalpass;i++){
            String entryqueryalt = "insert into fwdflightinfo(pnr,passengername) values('"+generatedpnr+"','"+passengername[i]+"');";
            stmt.executeUpdate(entryqueryalt);
            }
            String delquery = "delete from userdcorcc where cvv = '"+cvvno+"';";
        stmt.executeUpdate(delquery);
        }
        if(rndtrp.isSelected()==true){
            generatedpnr = generatepnr(totalpass);
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
            Statement stmt = con.createStatement();
            String query = "delete from fwdbooking where curdate()>doj;";
            stmt.executeUpdate(query);
          
            String entryquery="insert into fwdbooking values('"+generatedpnr+"','"+ticketinfo[0]+"','"+fromcity+"','"+tocity+"','"+forwarddate+"',"+totalpass+",'"+ticketinfo[1]+"');";
            stmt.executeUpdate(entryquery);
            for(int i = 0;i<totalpass;i++){
            String entryqueryalt = "insert into fwdflightinfo(pnr,passengername) values('"+generatedpnr+"','"+passengername[i]+"');";
            stmt.executeUpdate(entryqueryalt);
            }
            
            rtngeneratedpnr = generatepnr(totalpass);
            String rtnquery = "delete from rtnbooking where curdate()>rtndoj;";
            stmt.executeUpdate(rtnquery);
          
            String rtnentryquery="insert into rtnbooking values('"+rtngeneratedpnr+"','"+ticketinfo[4]+"','"+rtnfromcity+"','"+rtntocity+"','"+returndate+"',"+totalpass+",'"+ticketinfo[5]+"');";
            stmt.executeUpdate(rtnentryquery);
            for(int i = 0;i<totalpass;i++){
            String entryqueryalt = "insert into rtnflightinfo(rtnpnr,passengername) values('"+rtngeneratedpnr+"','"+passengername[i]+"');";
            stmt.executeUpdate(entryqueryalt);
            }
            String delquery = "delete from userdcorcc where cvv = '"+cvvno+"';";
        stmt.executeUpdate(delquery);
        }
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
JOptionPane.showMessageDialog(null, "Thank you for choosing us. Your tickets are booked , we will send your ticket on your mail");

if(oneway.isSelected()==true){
    Document doc = null;
try{
        Class.forName("java.sql.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
        Statement stmt = con.createStatement();
        String query = "select passengername,ifnull(seats,'not yet selected') from fwdflightinfo where pnr = '"+generatedpnr+"';";
        ResultSet rst = stmt.executeQuery(query);
        doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(path + "\\"+generatedpnr+"ticket.pdf"));
        doc.open();
                Path pathsearch1 = Paths.get("src\\flightreservationportal\\f15.png");
        String path1 = pathsearch1.toRealPath(LinkOption.NOFOLLOW_LINKS).toString();
        doc.add(Image.getInstance(path1));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        PdfPTable tbl = new PdfPTable(2);
        PdfPCell c1 = new PdfPCell(new Phrase("Passengers"));
        tbl.addCell(c1);
        c1 = new PdfPCell(new Phrase("Seat"));
        tbl.addCell(c1);
        tbl.setHeaderRows(1);
        while(rst.next()){
            tbl.addCell(rst.getString(1));
            tbl.addCell(rst.getString(2));
        }
        doc.add(tbl);
        
        con.close();
        stmt.close();
        rst.close();
        
        
        
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
try{
     Class.forName("java.sql.Driver");
        Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
        Statement stmt1 = con1.createStatement();
        String query1 = "select * from fwdbooking where pnr = '"+generatedpnr+"';";
        ResultSet rst1 = stmt1.executeQuery(query1);
        
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        
        
        PdfPTable tbl1 = new PdfPTable(6);
        PdfPCell c11 = new PdfPCell(new Phrase("Flight Number"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("Timings"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("From "));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("To"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("Date of Journey"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("PNR"));
        tbl1.addCell(c11);
        
        while(rst1.next()){
            tbl1.addCell(rst1.getString(2));
            tbl1.addCell(rst1.getString(7));
            tbl1.addCell(rst1.getString(3));
            tbl1.addCell(rst1.getString(4));
            tbl1.addCell(departuredisplaydate);
            tbl1.addCell(rst1.getString(1));
        }
        doc.add(tbl1);
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph("If you have not yet selected your favourite seats then go to our CHECK - IN WINDOW and select your favourite seat for free ."));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph("Thank you for choosing "+"UNITED AIRWAYS !"));
        
        
        doc.close();
        con1.close();
        stmt1.close();
        rst1.close();
        
}
catch(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
try{
    Class.forName("java.sql.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
    Statement stmt = con.createStatement();
    String query = "select email from user where userid = '"+userid+"' and password = '"+userpassword+"';";
    ResultSet rst = stmt.executeQuery(query);
    String email = "";
    while(rst.next()){
     email = rst.getString(1);
    }
    
    Properties p = new Properties();
p.put("mail.smtp.host", "smtp.gmail.com");
p.put("mail.smtp.auth", "true");
p.put("mail.smtp.starttls.enable", "true");
p.put("mail.smtp.port", "587");

Session s = Session.getDefaultInstance(p, new javax.mail.Authenticator() {

protected PasswordAuthentication getPasswordAuthentication(){
    return new PasswordAuthentication("unitedairways3101@gmail.com","ip_project1");
}

});

MimeMessage m = new MimeMessage(s);
    m.setFrom(new InternetAddress("unitedairways3101@gmail.com"));
    m.addRecipients(Message.RecipientType.TO,email);
    m.setSubject("Thank you for choosing us.");
    Multipart mc = new MimeMultipart();
    MimeBodyPart mb = new MimeBodyPart();
    mb.attachFile(path + "\\"+generatedpnr+"ticket.pdf");
    MimeBodyPart mb1 = new MimeBodyPart();
    mb1.setText("Your ticket is booked. This is your ticket.");
    mc.addBodyPart(mb);
    mc.addBodyPart(mb1);
    m.setContent(mc);
    m.saveChanges();
    Transport.send(m);
}
catch(Exception e){
    JOptionPane.showMessageDialog(null, e.getMessage());
}

ByteArrayOutputStream out = QRCode.from(generatedpnr).to(ImageType.JPG).stream();
byte bit[]=out.toByteArray();
qrdisplay.setIcon(new ImageIcon(bit));
}
if(rndtrp.isSelected()==true){
Document doc = null;
try{
        Class.forName("java.sql.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
        Statement stmt = con.createStatement();
        String query = "select passengername,ifnull(seats,'not yet selected') from fwdflightinfo where pnr = '"+generatedpnr+"';";
        ResultSet rst = stmt.executeQuery(query);
        doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(path + "\\"+generatedpnr+"ticket.pdf"));
        doc.open();
                Path pathsearch1 = Paths.get("src\\flightreservationportal\\f15.png");
        String path1 = pathsearch1.toRealPath(LinkOption.NOFOLLOW_LINKS).toString();
        doc.add(Image.getInstance(path1));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        PdfPTable tbl = new PdfPTable(2);
        PdfPCell c1 = new PdfPCell(new Phrase("Passengers"));
        tbl.addCell(c1);
        c1 = new PdfPCell(new Phrase("Seat"));
        tbl.addCell(c1);
        tbl.setHeaderRows(1);
        while(rst.next()){
            tbl.addCell(rst.getString(1));
            tbl.addCell(rst.getString(2));
        }
        doc.add(tbl);
        
        con.close();
        stmt.close();
        rst.close();
        
        
        
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
try{
     Class.forName("java.sql.Driver");
        Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
        Statement stmt1 = con1.createStatement();
        String query1 = "select * from fwdbooking where pnr = '"+generatedpnr+"';";
        ResultSet rst1 = stmt1.executeQuery(query1);
        
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        
        
        PdfPTable tbl1 = new PdfPTable(6);
        PdfPCell c11 = new PdfPCell(new Phrase("Flight Number"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("Timings"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("From "));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("To"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("Date of Journey"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("PNR"));
        tbl1.addCell(c11);
        
        while(rst1.next()){
            tbl1.addCell(rst1.getString(2));
            tbl1.addCell(rst1.getString(7));
            tbl1.addCell(rst1.getString(3));
            tbl1.addCell(rst1.getString(4));
            tbl1.addCell(departuredisplaydate);
            tbl1.addCell(rst1.getString(1));
        }
        doc.add(tbl1);
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph("If you have not yet selected your favourite seats then go to our CHECK - IN WINDOW and select your favourite seat for free ."));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph("Thank you for choosing "+"UNITED AIRWAYS !"));
        
        
        doc.close();
        con1.close();
        stmt1.close();
        rst1.close();
        
}
catch(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
Document doc1 = null;
try{
        Class.forName("java.sql.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
        Statement stmt = con.createStatement();
        String query = "select passengername,ifnull(seats,'not yet selected') from rtnflightinfo where rtnpnr = '"+rtngeneratedpnr+"';";
        ResultSet rst = stmt.executeQuery(query);
        doc1 = new Document();
        PdfWriter.getInstance(doc1, new FileOutputStream(path + "\\"+rtngeneratedpnr+"ticket.pdf"));
        doc1.open();
                Path pathsearch1 = Paths.get("src\\flightreservationportal\\f15.png");
        String path1 = pathsearch1.toRealPath(LinkOption.NOFOLLOW_LINKS).toString();
        doc1.add(Image.getInstance(path1));
        doc1.add(new Paragraph(" "));
        doc1.add(new Paragraph(" "));
        doc1.add(new Paragraph(" "));
        PdfPTable tbl = new PdfPTable(2);
        PdfPCell c1 = new PdfPCell(new Phrase("Passengers"));
        tbl.addCell(c1);
        c1 = new PdfPCell(new Phrase("Seat"));
        tbl.addCell(c1);
        tbl.setHeaderRows(1);
        while(rst.next()){
            tbl.addCell(rst.getString(1));
            tbl.addCell(rst.getString(2));
        }
        doc1.add(tbl);
        
        con.close();
        stmt.close();
        rst.close();
        
        
        
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
try{
     Class.forName("java.sql.Driver");
        Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
        Statement stmt1 = con1.createStatement();
        String query1 = "select * from rtnbooking where rtnpnr = '"+rtngeneratedpnr+"';";
        ResultSet rst1 = stmt1.executeQuery(query1);
        
        doc1.add(new Paragraph(" "));
        doc1.add(new Paragraph(" "));
        
        
        PdfPTable tbl1 = new PdfPTable(6);
        PdfPCell c11 = new PdfPCell(new Phrase("Flight Number"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("Timings"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("From "));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("To"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("Date of Journey"));
        tbl1.addCell(c11);
        c11 = new PdfPCell(new Phrase("PNR"));
        tbl1.addCell(c11);
        
        while(rst1.next()){
            tbl1.addCell(rst1.getString(2));
            tbl1.addCell(rst1.getString(7));
            tbl1.addCell(rst1.getString(3));
            tbl1.addCell(rst1.getString(4));
            tbl1.addCell(returndisplaydate);
            tbl1.addCell(rst1.getString(1));
        }
        doc1.add(tbl1);
        doc1.add(new Paragraph(" "));
        doc1.add(new Paragraph(" "));
        doc1.add(new Paragraph("If you have not yet selected your favourite seats then go to our CHECK - IN WINDOW and select your favourite seat for free ."));
        doc1.add(new Paragraph(" "));
        doc1.add(new Paragraph(" "));
        doc1.add(new Paragraph("Thank you for choosing "+"UNITED AIRWAYS !"));
        
        
        doc1.close();
        con1.close();
        stmt1.close();
        rst1.close();
        
}
catch(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
try{
    Class.forName("java.sql.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
    Statement stmt = con.createStatement();
    String query = "select email from user where userid = '"+userid+"' and password = '"+userpassword+"';";
    ResultSet rst = stmt.executeQuery(query);
    String email = "";
    while(rst.next()){
     email = rst.getString(1);
    }
    Properties p = new Properties();
p.put("mail.smtp.host", "smtp.gmail.com");
p.put("mail.smtp.auth", "true");
p.put("mail.smtp.starttls.enable", "true");
p.put("mail.smtp.port", "587");

Session s = Session.getDefaultInstance(p, new javax.mail.Authenticator() {

protected PasswordAuthentication getPasswordAuthentication(){
    return new PasswordAuthentication("unitedairways3101@gmail.com","ip_project1");
}

});

MimeMessage m = new MimeMessage(s);
    m.setFrom(new InternetAddress("unitedairways3101@gmail.com"));
    m.addRecipients(Message.RecipientType.TO,email);
    m.setSubject("Thank you for choosing us.");
    Multipart mc = new MimeMultipart();
    MimeBodyPart mb = new MimeBodyPart();
    mb.attachFile(path + "\\"+generatedpnr+"ticket.pdf");
    MimeBodyPart rtnmb = new MimeBodyPart();
    rtnmb.attachFile(path + "\\"+rtngeneratedpnr+"ticket.pdf");
    MimeBodyPart mb1 = new MimeBodyPart();
    mb1.setText("Your ticket is booked. This is your ticket.");
    mc.addBodyPart(mb);
    mc.addBodyPart(mb1);
    mc.addBodyPart(rtnmb);
    m.setContent(mc);
    m.saveChanges();
    Transport.send(m);
}
catch(Exception e){
    JOptionPane.showMessageDialog(null, e.getMessage());
}

ByteArrayOutputStream out2 = QRCode.from(generatedpnr).to(ImageType.JPG).stream();
byte bit2[]=out2.toByteArray();
qrdisplay.setIcon(new ImageIcon(bit2));
ByteArrayOutputStream out1 = QRCode.from(rtngeneratedpnr).to(ImageType.JPG).stream();
byte bit1[]=out1.toByteArray();
qrdisplay1.setIcon(new ImageIcon(bit1));
}
System.exit(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_payaltActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
payalt.doClick();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
findbooking.setVisible(true);
findbooking.setLocationRelativeTo(null);
infotfpanel.removeAll();
infolabelpanel.removeAll();
infotfpanel.validate();
infotfpanel.repaint();
infolabelpanel.validate();
infolabelpanel.repaint();
String findbookingpnr = findpnrtf.getText().trim();   
outer: {inner : {try{
    Class.forName("java.sql.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
    Statement stmt = con.createStatement();
    String query = "select * from fwdbooking natural join fwdflightinfo where pnr = '"+findbookingpnr+"';";
    
    ResultSet rst = stmt.executeQuery(query);
    ResultSetMetaData rsmd = rst.getMetaData();
    int n = rsmd.getColumnCount();
    if(rst.next()){
        for(int i = 1;i<=n-1;i++){
            JLabel label = new JLabel();
            JTextField tf = new JTextField();
            int j = i-1;
            label.setBounds(25,50*j,200,40);
            label.setText(rsmd.getColumnName(i));
            tf.setBounds(25,50*j,200,40);
            tf.setText(rst.getString(i));
            infolabelpanel.add(label);
            infolabelpanel.validate();
            infolabelpanel.repaint();
            infotfpanel.add(tf);
            infotfpanel.validate();
            infotfpanel.repaint();
        }

        
        while(rst.next()){
           
            JTextField tf = new JTextField();
            
            tf.setBounds(25,50*(n-1),200,40);
            
            tf.setText(rst.getString(8));
            
            infotfpanel.add(tf);
            infotfpanel.validate();
            infotfpanel.repaint();
           
            n++;
        }
        break outer;
    }
    else{
        break inner;
    }
}
catch(Exception e){
    JOptionPane.showMessageDialog(null, e.getMessage());
}}
try{
    Class.forName("java.sql.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
    Statement stmt = con.createStatement();
    String query = "select * from rtnbooking natural join rtnflightinfo where rtnpnr = '"+findbookingpnr+"';";
    
    ResultSet rst = stmt.executeQuery(query);
    ResultSetMetaData rsmd = rst.getMetaData();
    int n = rsmd.getColumnCount();
    if(rst.next()){
        for(int i = 1;i<=n-1;i++){
            JLabel label = new JLabel();
            JTextField tf = new JTextField();
            int j = i-1;
            label.setBounds(25,50*j,200,40);
            label.setText(rsmd.getColumnName(i));
            tf.setBounds(25,50*j,200,40);
            tf.setText(rst.getString(i));
            infolabelpanel.add(label);
            infolabelpanel.validate();
            infolabelpanel.repaint();
            infotfpanel.add(tf);
            infotfpanel.validate();
            infotfpanel.repaint();
        }

        
        while(rst.next()){
           
            JTextField tf = new JTextField();
            
            tf.setBounds(25,50*(n-1),200,40);
            
            tf.setText(rst.getString(8));
            
            infotfpanel.add(tf);
            infotfpanel.validate();
            infotfpanel.repaint();
           
            n++;
        }
    }
    else{
        JOptionPane.showMessageDialog(null, "pnr not available");
    }
}
catch(Exception e){
    JOptionPane.showMessageDialog(null, e.getMessage());
}}

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
checkin.setVisible(true);
String seatpnr = checkinpnr.getText();
String fltno = "";
String doj = "";

outer: {inner: {try{
     Class.forName("java.sql.Driver");
     Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
     Statement stmt = con.createStatement();
     String query = "select fltno,doj from fwdbooking where pnr = '"+seatpnr+"';";
     ResultSet rst = stmt.executeQuery(query);
     if(rst.next()){
         fltno = rst.getString(1);
         doj = rst.getString(2);
         break outer;
     }
     else{
         break inner;
     }
 }
catch(Exception e){
    JOptionPane.showMessageDialog(null, e.getMessage());
    checkin.setVisible(false);
    checkin.dispose();
}


}
 try{
     Class.forName("java.sql.Driver");
     Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
     Statement stmt = con.createStatement();
     String query = "select rtnfltno,rtndoj from rtnbooking where rtnpnr = '"+seatpnr+"';";
     ResultSet rst = stmt.executeQuery(query);
     if(rst.next()){
         fltno = rst.getString(1);
         
         doj = rst.getString(2);
         break outer;
     }
     else{
         JOptionPane.showMessageDialog(null, "PNR doesnot exist");
     }
 }
catch(Exception e){
    JOptionPane.showMessageDialog(null, e.getMessage());
}


}

try{
     Class.forName("java.sql.Driver");
     Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
     Statement stmt = con.createStatement();
     String query = "select ifnull(seats,'not yet selected') from fwdbooking natural join fwdflightinfo where fltno = '"+fltno+"' and doj = '"+doj+"';";
     ResultSet rst = stmt.executeQuery(query);
       while(rst.next()){
               String seatno = rst.getString(1);
           
         if(seatno == "not yet selected"){
             
         }
         else{
             Component cmp[] = jPanel18.getComponents();
             int n = cmp.length;
             
                for(int i = 0;i<n;i++){
                    
                 if(cmp[i] instanceof JCheckBox){
                     String name = ((JCheckBox) cmp[i]).getName();
                     
                     if(name.equals(seatno)){
                         ((JCheckBox) cmp[i]).setSelected(true);
                         ((JCheckBox) cmp[i]).setEnabled(false);
                     }
                     else{
                         
                     }
                 }
                 else{
                     
                 }
             }
         }
     }
    
    
}
catch(Exception e){
     JOptionPane.showMessageDialog(null, e.getMessage());
}
try{
     Class.forName("java.sql.Driver");
     Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flight","root","opjs");
     Statement stmt = con.createStatement();
     String query = "select ifnull(seats,'not yet selected') from rtnbooking natural join rtnflightinfo where rtnfltno = '"+fltno+"' and rtndoj = '"+doj+"';";
     
     ResultSet rst = stmt.executeQuery(query);
       while(rst.next()){
               String seatno = rst.getString(1);
           
         if(seatno == "not yet selected"){
             
         }
         else{
             Component cmp[] = jPanel18.getComponents();
             int n = cmp.length;
             
                for(int i = 0;i<n;i++){
                    
                 if(cmp[i] instanceof JCheckBox){
                     String name = ((JCheckBox) cmp[i]).getName();
                     
                     if(name.equals(seatno)){
                         ((JCheckBox) cmp[i]).setSelected(true);
                         ((JCheckBox) cmp[i]).setEnabled(false);
                     }
                     else{
                         
                     }
                 }
                 else{
                     
                 }
             }
         }
     }
      
    
    
}
catch(Exception e){
     JOptionPane.showMessageDialog(null, e.getMessage());
}
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
  String url = "file:///C:/Users/Abhishek%20Agrawal/Documents/NetBeansProjects/flight%20reservation%20system/About%20Us.html";
        try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
            // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(userend.class.getName()).log(Level.SEVERE, null, ex);
        }      // TODO add your handling code here:
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
  String url = "file:///C:/Users/Abhishek%20Agrawal/Documents/NetBeansProjects/flight%20reservation%20system/Credits.html";
        try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
            // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(userend.class.getName()).log(Level.SEVERE, null, ex);
        }      // TODO add your handling code here:
    }//GEN-LAST:event_jMenu4MouseClicked

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
  String url = "file:///C:/Users/Abhishek%20Agrawal/Documents/NetBeansProjects/flight%20reservation%20system/Contact%20Us.html";
        try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
            // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(userend.class.getName()).log(Level.SEVERE, null, ex);
        }      // TODO add your handling code here:
    }//GEN-LAST:event_jMenu5MouseClicked

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
System.exit(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
 System.exit(0);       // TODO add your handling code here:
    }//GEN-LAST:event_jButton17ActionPerformed
