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
import javax.swing.border.Border;

//ΚΛΑΣΗ ΓΙΑ ΣΤΟΓΓΥΛΕΜΕΝΕΣ ΓΩΝΙΕΣ ΣΤΑ ΚΟΥΜΠΙΑ ΟΧΙ ΤΑ DEAFAULT ΚΟΥΜΠΙΑ
//αυτη η κλαση ειναι για τις στρογυλεμενες γωνιες στα κουμπια
public class RoundedBorder implements Border {
    
        private int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;// ειναι η τιμη για το στρογυλεμα των κουμπιων, δηλ ποσα πιξελ
        }

         // Επιστρέφει τα περιθώρια που θα προστεθούν γύρω από το κουμπί
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius, radius, radius, radius); 
// Επιστρέφει τα περιθώρια για το border. Όλα τα περιθώρια (πάνω, κάτω, αριστερά, δεξιά) είναι ίσα και ισούνται με το radius.
        }

        
        
         // Δηλώνει ότι το border είναι αδιαφανές 
        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;// Μετατροπή του Graphics αντικειμένου σε Graphics2D για περισσότερη ευχέρεια στο σχέδιο.
            
            // Ρύθμιση για την απαλή σχεδίαση 
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Ορισμός του χρώματος του border ίσο με το χρώμα φόντου του κουμπιού ή του στοιχείου.
            g2.setColor(c.getBackground()); // Χρώμα border
            
            // Σχεδιάζει το στρογγυλεμένο περίγραμμα γύρω από το κουμπί με το καθορισμένο radius για τις γωνίες.
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius); // Στρογγυλό περίγραμμα
        }
    }

    