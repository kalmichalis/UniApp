/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ergasia3;

import static ergasia3.UniApp.addUniversity;
import static ergasia3.UniApp.searchByKeyword;
import static ergasia3.UniApp.searchUniversity;

import static ergasia3.UniApp.showUniversitiesByCountry;
import static ergasia3.UniApp.updateUniversity;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.SwingConstants;


/**
 *
 * @author kalmi
 */


public class GUI extends JFrame {
    private BufferedImage backgroundImage;
    {

        
        setTitle("UniApp");// ονομασια εφαρμογης
        setSize(1312, 736);//μεγεθος παραθυρου
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// με αυτο εδω οταν παταμε το χ στο παραθυρο δεν κλεινει πρεπει να πατησουμε το ΕΞΟΔΟΣ
        setResizable(false);// δεν αλλαζει ουτε μεγιστοποιειται το μεγεθος του παραθυρου, αν θελω να το κανω το βαζω τρου
       
        
        

    
        
        
        
        
        
        //ΕΙΔΟΝΙΔΙΟ ΤΗΣ ΕΦΑΡΜΟΓΗΣ ΠΑΝΩ ΑΡΙΣΤΕΡΑ
        URL iconUrl = getClass().getClassLoader().getResource("resources/uni.png");
       if (iconUrl != null) {
    ImageIcon imageIcon = new ImageIcon(iconUrl);
    setIconImage(imageIcon.getImage());
} else {
    System.out.println("H EIKONA TOU EIKONIDIOU DEN VRETHIKE.");
}


    
        // Δημιουργία του πάνελ με το φόντο την δικη μου εικονα
        BackgroundPanel panel = new BackgroundPanel();
        panel.setLayout(new BorderLayout());
        setContentPane(panel);
        setLocationRelativeTo(null); // Κεντρική τοποθέτηση του παραθύρου κατα την εκκινηση στην οθονη του υπολογιστη
        

        
        
        // Χρήση GridBagLayout για να κεντράρουμε τα κουμπιά
        panel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Προσθήκη χώρου γύρω από τα κουμπιά
        
        
        
// Δημιουργία των κουμπιών σύμφωνα με αυτά που θέλει η εργασία ΚΑΙ ΕΙΚΟΝΕΣ ΣΤΟ ΚΑΘΕΝΑ
JButton searchButton = new RoundedButton("Αναζήτηση Πανεπιστημίου", 40,"resources/search_icon.png"); // Χρησιμοποιούμε RoundedButton με ακτίνα 40
JButton updateButton = new RoundedButton("Ενημέρωση Πληροφοριών Πανεπιστημίου", 40,"resources/updated.png");
JButton addButton = new RoundedButton("Προσθήκη Πληροφοριών Για Πανεπιστήμιο", 40,"resources/insert.png");
JButton showButton = new RoundedButton("Προβολή Πανεπιστημίων ανά Χώρα", 40,"resources/all.png");
JButton keywordSearchButton = new RoundedButton("Αναζήτηση Βάσει Keyword", 40,"resources/task_search_icon.png");
JButton statsButton = new RoundedButton("Προβολή Στατιστικών", 40,"resources/statistics.png");

//ΕΔΩ ΕΙΝΑΙ ΓΙΑ ΤΟ ΕΧΙΤ
ImageIcon exitIcon = new ImageIcon(getClass().getClassLoader().getResource("resources/exit_icon.png"));
exitIcon = new ImageIcon(exitIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)); // Ρύθμιση μεγέθους εικόνας ΕΧΙΤ
JButton exitButton = createCustomButton("");//den evala keimeno sto koympi ΕΧΙΤ  evala mono th fvtografia
exitButton.setBackground(Color.RED); // Ορισμός κόκκινου χρώματος στο φόντο
exitButton.setForeground(Color.WHITE); // Ορισμός λευκού χρώματος στο κείμενο
// Ρύθμιση εικόνας και κειμένου με την εικόνα αριστερά από το κείμενο
exitButton.setIcon(exitIcon); // Προσθήκη εικόνας στο κουμπί
exitButton.setHorizontalTextPosition(SwingConstants.RIGHT); // Τοποθέτηση κειμένου στα δεξιά της εικόνας
exitButton.setIconTextGap(10); // Απόσταση μεταξύ εικόνας και κειμένου



// Ορισμός σταθερού μεγέθους για τα κουμπιά
Dimension buttonSize = new Dimension(450, 35);
searchButton.setPreferredSize(buttonSize);
updateButton.setPreferredSize(buttonSize);
addButton.setPreferredSize(buttonSize);
showButton.setPreferredSize(buttonSize);
keywordSearchButton.setPreferredSize(buttonSize);
statsButton.setPreferredSize(buttonSize);

Dimension buttonSizeExit = new Dimension(70, 40);//ΔΙΑΣΤΑΣΗ ΓΑΙ ΤΟ ΚΟΥΜΠΙ ΕΧΙΤ
exitButton.setPreferredSize(buttonSizeExit);


gbc.weightx = 1.0; // Επιτρέπει στα κουμπιά να επεκτείνονται οριζόντια
gbc.weighty = 0.0; // Δεν επεκτείνονται κατακόρυφα

// Τοποθέτηση των κουμπιών
panel.add(searchButton, gbc);
gbc.gridy = 1; 
panel.add(updateButton, gbc);
gbc.gridy = 2; 
panel.add(addButton, gbc);
gbc.gridy = 3; 
panel.add(showButton, gbc);
gbc.gridy = 4; 
panel.add(keywordSearchButton, gbc);
gbc.gridy = 5; 
panel.add(statsButton, gbc);

// Ρύθμιση για το exitButton να παει πιο κατω
// Ρύθμιση για το exitButton να τοποθετηθεί στην κάτω δεξιά γωνία

gbc.gridy = 6; // Τοποθέτηση του exitButton πιο κάτω
gbc.anchor = GridBagConstraints.SOUTHEAST; // Ευθυγράμμιση του κουμπιού στη δεξιά γωνία
gbc.weighty = 1.0; // Αυτό το κουμπί θα πάρει περισσότερο κατακόρυφο χώρο
panel.add(exitButton, gbc);

 

        // Ενέργειες για τα κουμπιά ΠΙΟ ΚΑΤΩ ΕΙΝΑΙ ΟΙ ΜΑΘΟΔΟΙ ΠΟΥ ΘΑ ΚΑΝΟΥΝ ΤΙΣ ΛΕΙΤΟΥΡΓΙΕΣ
        searchButton.addActionListener(event -> searchUniversity());
        updateButton.addActionListener(event -> updateUniversity());
        addButton.addActionListener(event -> addUniversity());
        showButton.addActionListener(event-> showUniversitiesByCountry());
        keywordSearchButton.addActionListener(event -> searchByKeyword());
        statsButton.addActionListener(event -> showStatistics());
        
        //ΕΠΙΒΕΒΑΙΩΣΗ ΕΞΟΔΟΥ ΑΠΟ ΤΟ ΠΡΟΓΡΑΜΜΑ 
        exitButton.addActionListener(event -> {
    int response = JOptionPane.showConfirmDialog(
            null,
            "Μήπως να το ξανασκεφτείτε; Η εφαρμογή μας είναι φανταστική!",
            "Επιβεβαίωση εξόδου",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
    );

    if (response == JOptionPane.YES_OPTION) {
        System.exit(0); // Τερματισμός της εφαρμογής
    }
});
        
    }
//ενεργοποιω τη μεθοδο στο εργασια3 για τα στατιστικα
    private void showStatistics() {
    // Κλήση της μεθόδου showStatistics της κλάσης UniversityStatistics
    UniversitiesDAO dao = new UniversitiesDAO(); // Παράδειγμα DAO, χρειάζεται να το αντικαταστήσεις με την πραγματική υλοποίηση του DAO
    UniversityStatistics.showStatistics(dao);
}
    
    
    // Η ΕΙΚΟΝΑ ΣΤΟ BACKGROUND ΤΗΣ ΕΦΑΡΜΟΓΗΣ
private class BackgroundPanel extends JPanel {
    public BackgroundPanel() {
        try {
            URL imageUrl = getClass().getClassLoader().getResource("resources/iconback.png");
            if (imageUrl != null) {
                backgroundImage = ImageIO.read(imageUrl);
                System.out.println("h eikona vrethike epitixos");
            } else {
                System.out.println("i eikona den vrethike.");
            }
        } catch (IOException e) {
            System.out.println("sflama kata ti fortosh : " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.BLACK);
            g.drawString("h eikona den einai diathesimi", 10, 20);
        }
    }
}
  
// Μέθοδος για τη δημιουργία του κουμπιου ΕΧΙΤ διαφορετικο απο τα αλλα, ΑΛΛΟΣ ΗΧΟΣ ΑΛΛΟ ΜΕΓΕΘΟΣ ΚΑΙ ΧΡΩΜΑ
private JButton createCustomButton(String text) {
    JButton button = new JButton(text);
    button.setFocusPainted(false);
    
    
    button.setForeground(Color.WHITE); // Λευκό κείμενο
    button.setFont(new Font("Arial", Font.BOLD, 14));
    button.setBorder(new RoundedBorder(20)); // 20 pixels για στρογγυλεμένες γωνίες
    
    button.setContentAreaFilled(true); // Αφαιρεί το "εφέ κουμπιού"
    button.setOpaque(true); // Ενεργοποιεί το χρώμα

    // Προσθήκη MouseListener για αλλαγή χρώματος
    button.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            button.setBackground(new Color(205, 92, 92)); // Πιο έντονο κεραμιδί
            SoundPlayer.playSound("resources/exit.wav");//ηχος αμεσως μme to perasma pano apo to koumpi exit
        }

        @Override
        public void mouseExited(MouseEvent e) {
            button.setBackground(Color.RED);
            //button.setBackground(new Color(205, 92, 92)); // Επιστροφή στο αρχικό χρώμα
        }
    });

    return button;
}


}
    

