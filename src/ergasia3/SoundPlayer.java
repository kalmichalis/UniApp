/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ergasia3;

/**
 *
 * @author kalmi
 */
import javax.sound.sampled.*;
import java.net.URL;
//ΑΥΤΗ Η ΚΛΑΣΗ ΕΙΝΑΙ ΓΙΑ ΤΟΥΣ ΗΧΟΥΣ
public class SoundPlayer {

    public static void playSound(String resourceName) {
        try {
            // Φόρτωση του αρχείου ήχου μέσω του ClassLoader
            URL soundUrl = SoundPlayer.class.getClassLoader().getResource(resourceName);
            if (soundUrl != null) {
                // Δημιουργία του audio stream και αναπαραγωγή
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundUrl);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
            } else {
                System.out.println("Το αρχείο ήχου " + resourceName + " δεν βρέθηκε!");
            }
        } catch (Exception e) {
            System.err.println("Σφάλμα κατά την αναπαραγωγή του ήχου: " + e.getMessage());
            e.printStackTrace();
        }
    }
}