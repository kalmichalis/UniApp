/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ergasia3;

/**
 *
 * @author kalmi
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.URL;
//ΑΥΤΗ Η ΚΛΑΣΗ ΕΙΝΑΙ ΓΙΑ ΤΟ ΠΩΣ ΝΑ ΕΙΝΑΙ ΤΑ ΚΟΥΜΠΙΑ ΟΛΑ ΕΚΤΟΣ ΤΟΥ ΕΧΙΤ
public class RoundedButton extends JButton {
    private int radius;

    public RoundedButton(String text, int radius,String imagePath) {
        super(text);
        this.radius = radius;
        setFocusPainted(false); // Αφαιρούμε το focus
        setForeground(Color.WHITE); // Λευκό χρώμα για το κείμενο
        setBackground(new Color(128, 128, 0)); // Κεραμιδί χρώμα
        setBorder(new RoundedBorder(radius)); // Εφαρμογή του border
        setFont(new Font("Arial", Font.BOLD, 14)); // Ρύθμιση γραμματοσειράς
        setOpaque(true); // Ενεργοποιούμε την αδιαφάνεια
        setContentAreaFilled(false); // Αφαιρούμε το προεπιλεγμένο φόντο
        
        // Ορισμός της εικόνας και τοποθέτηση αριστερά από το κείμενο
        URL imageUrl = getClass().getClassLoader().getResource(imagePath);
        
        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl); // Φορτώνουμε την εικόνα από το URL
            Image image = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Ρύθμιση μεγέθους εικόνας σε 30x30
            setIcon(new ImageIcon(image)); // Ορισμός της εικόνας στο κουμπίsetIcon(icon);
        } else {
            System.out.println("Εικόνα δεν βρέθηκε: " + imagePath);
        }    


        // Ρύθμιση της θέσης της εικόνας να είναι αριστερά από το κείμενο
        setHorizontalAlignment(SwingConstants.LEFT); // Τοποθέτηση εικόνας αριστερά
        setIconTextGap(20); // Ρύθμιση μεγαλύτερης απόστασης μεταξύ εικόνας και κειμένου για καλύτερη ευθυγράμμιση

        // Προσθήκη MouseListener για αλλαγή χρώματος όταν περνάει το ποντίκι
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(85, 107, 47)); // Σκούρο κεραμιδί
                SoundPlayer.playSound("resources/click.wav");//ηχος αμεσως με to poy περναει πανω απο το κουμπι την ωρα π αλλαζει χρωμα
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(128, 128, 0)); // Επιστροφή στο αρχικό χρώμα
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Ελέγχουμε αν το κουμπί είναι ενεργό και αν ναι, ζωγραφίζουμε το φόντο του κουμπιού
        if (getModel().isPressed()) {
            g.setColor(new Color(178, 34, 34)); // Όταν είναι πατημένο, αλλάζει χρώμα
        } else {
            g.setColor(getBackground()); // Αλλιώς το κανονικό χρώμα
        }
        g.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius); // Στρογγυλεμένο φόντο
        super.paintComponent(g); // Ζωγραφίζουμε το κείμενο του κουμπιού
    }}