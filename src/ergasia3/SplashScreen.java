/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ergasia3;

/**
 *
 * @author kalmi
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.net.URL;



public class SplashScreen{

    {
        // Δημιουργία και εμφάνιση του Splash Screen
        JFrame splashScreen = new JFrame();
        splashScreen.setUndecorated(true); // Αφαίρεση των κουμπιών παραθύρου
        splashScreen.setSize(1312, 736); // Μέγεθος του παραθύρου
        splashScreen.setLocationRelativeTo(null); // Κεντραρισμένο στην οθόνη
        URL image = SplashScreen.class.getClassLoader().getResource("resources/eap.png"); // Χρήση του ClassLoader

        if (image != null) {
            System.out.println("H EIKONA SPLASH SCREEN FORTOTHIKE EPITIXVS");
            ImageIcon icon = new ImageIcon(image);
            Image scaledImage = icon.getImage().getScaledInstance(1312, 736, Image.SCALE_SMOOTH); // ΙΔΙΟ ΜΕΓΕΘΟΣ
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            // Δημιουργία JLabel με την κλιμακωμένη εικόνα
            JLabel splashLabel = new JLabel(scaledIcon);

            // Δημιουργία progress bar
            JProgressBar progressBar = new JProgressBar();
            progressBar.setMinimum(0);
            progressBar.setMaximum(100);
            progressBar.setStringPainted(true);
            progressBar.setForeground(new Color(50, 205, 50)); // Χρώμα της μπάρας

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(splashLabel, BorderLayout.CENTER);
            panel.add(progressBar, BorderLayout.SOUTH); // Μπάρα στο κάτω μέρος

            // Timer για την πρόοδο της Progress Bar
            Timer progressTimer = new Timer(30, new ActionListener() {
                int progress = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    progress += 1;
                    progressBar.setValue(progress);
                    if (progress >= 100) {
                        ((Timer) e.getSource()).stop(); // Σταμάτημα του Timer όταν η μπάρα γεμίσει
                    }
                }
            });
            progressTimer.start();

            splashScreen.add(panel);
        } else {
            System.out.println("H EIKONA TOU SPLASH DEN VRETHIKE");
        }
        splashScreen.setVisible(true);
        SoundPlayer.playSound("resources/start_sound.wav"); // Ήχος αμέσως με την έναρξη του splash screen

        // Χρήση Timer για καθυστέρηση στο κλείσιμο του Splash Screen
        Timer timer = new Timer(3000, e -> {
            splashScreen.dispose(); // Κλείσιμο του Splash Screen

            // Δημιουργία και εμφάνιση του κύριου παραθύρου
            GUI UniAPP = new GUI();
            
            UniAPP.setVisible(true); // Το κύριο παράθυρο της εφαρμογής σε ορατή κατάσταση
            System.out.println("SPLASH SCREEN KLEINEI.");
        });

        timer.setRepeats(false); // Καθορίζει ότι ο Timer δεν θα επαναλαμβάνεται
        timer.start(); // Ξεκινά τον Timer
    }
}


