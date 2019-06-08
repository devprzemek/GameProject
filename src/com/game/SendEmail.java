package com.game;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Klasa odpowiedzialna za wysyłanie wiadomości email
 */
public class SendEmail extends JDialog implements ActionListener {

    private static final String HOST = "smtp.gmail.com";
    private static final int PORT = 465;

    // Adres email osby która wysyła maila
    private static String FROM = null;

    // Hasło do konta osoby która wysyła maila
    private static String PASSWORD = null;

    // Adres email osoby do której wysyłany jest mail
    private static final String TO = "salamonikp@gmail.com";

    // Temat wiadomości
    private static final String SUBJECT = "[GRA - Kontakt]";

    // Treść wiadomości
    private static String CONTENT = "";

    public void sendMessage() throws MessagingException {

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.auth", "true");

        // Inicjalizacja sesji
        Session mailSession = Session.getDefaultInstance(props);

        mailSession.setDebug(true);

        // Tworzenie wiadomości email
        MimeMessage message = new MimeMessage(mailSession);
        message.setSubject(SUBJECT);
        message.setContent(CONTENT, "text/plain; charset=ISO-8859-2");
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(TO));

        Transport transport = mailSession.getTransport();
        transport.connect(HOST, PORT, FROM, PASSWORD);

        // wysłanie wiadomości
        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));

        transport.close();
    }


    private JLabel lEmailAddress, lPassword, lText, lMessage;
    private JTextField tEmailAddress;
    JTextArea tMessage;
    private JPasswordField pPassword;
    private JButton bSend, bCancel;

    public SendEmail(JFrame owner){
        super(owner, "Wysyłanie wiadomości", true);
        setSize(400,400);
        setLayout(null);
        setLocation(585,250);

        String text = "<html> Wprowadź potrzebne dane oraz treść wiadomości. <html>";
        lText = new JLabel(text, JLabel.CENTER);
        lText.setBounds(40,20, 300,50);
        add(lText);

        lEmailAddress = new JLabel("Adres email");
        lEmailAddress.setBounds(50,80, 100,20);
        add(lEmailAddress);

        tEmailAddress = new JTextField();
        tEmailAddress.setBounds(160,80, 150,20);
        add(tEmailAddress);

        lPassword = new JLabel("Hasło");
        lPassword.setBounds(50,130, 100,20);
        add(lPassword);

        pPassword = new JPasswordField();
        pPassword.setBounds(160,130, 150,20);
        add(pPassword);

        lMessage = new JLabel("Treść wiadomości");
        lMessage.setBounds(50,180, 150,20);
        add(lMessage);

        tMessage = new JTextArea();
        tMessage.setBounds(160,180, 150,100);
        tMessage.setLineWrap(true);
        tMessage.setWrapStyleWord(true);
        add(tMessage);

        bSend = new JButton("Wyślij");
        bSend.setBounds(100, 300, 80,50);
        bSend.addActionListener(this);
        add(bSend);

        bCancel = new JButton("Wyjście");
        bCancel.setBounds(200, 300, 80,50);
        bCancel.addActionListener(this);
        add(bCancel);

        setVisible(true);
        setFocus();
    }

    public void setFocus(){
            tEmailAddress.requestFocusInWindow();
        }

    @Override
    public void actionPerformed(ActionEvent e1) {
        Object source = e1.getSource();

        this.FROM = tEmailAddress.getText();
        this.PASSWORD = new String(pPassword.getPassword());
        this.CONTENT = tMessage.getText();

        if(source == bSend){
            try {
                sendMessage();
                JOptionPane.showMessageDialog(this, "Wiadomość została wysłana pomyślnie!", "", JOptionPane.INFORMATION_MESSAGE);

            } catch (MessagingException e2) {
                e2.printStackTrace();
                JOptionPane.showMessageDialog(this, "Wiadomość nie została wysłana pomyślnie!", "", JOptionPane.INFORMATION_MESSAGE);

            }
        }
        setVisible(false);
    }
}
