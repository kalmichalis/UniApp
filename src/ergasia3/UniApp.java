/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ergasia3;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import java.util.LinkedHashMap;
import java.util.Map;



public class UniApp {
public static void main(String[] args) {  

            //ΑΠΟ ΕΔΩ ΞΕΚΙΝΑΕΙ ΤΟ ΠΡΟΓΡΑΜΜΑ ΟΠΟΥ ΕΜΦΑΝΙΖΕΙ ΤΟ ΠΡΩΤΟ ΠΑΡΑΘΥΡΟ ΕΚΚΙΝΗΣΗΣ ΜΕ ΤΗΝ ΜΠΑΡΑ ΠΟΥ ΑΝΟΙΓΕΙ ΣΤΗΝ ΚΛΑΣΗ SplashScreen
    SwingUtilities.invokeLater(() -> {
            SplashScreen splash = new SplashScreen(); 
            
            /*ΛΟΙΠΟΝ ΕΔΩ ΕΧΟΥΝ ΜΕ ΤΗΝ ΜΑΙΝ, Η ΜΑΙΝ ΚΑΛΕΙ ΤΗΝ ΚΛΑΣΗ SPLASHSCREEN ΝΑ ΞΕΚΙΝΗΣΕΙ Η ΠΡΩΤΗ ΕΙΚΟΝΑ ΓΙΑ ΕΦΕ, 
            ΜΕΤΑ Η SPLASHSCREEN ΚΑΛΕΙ ΤΗΝ GUI ΚΑΙ Η GUI ΤΑ ΚΑΝΕΙ ΟΛΑ, 
            
            ΟΙ ΜΕΘΟΔΟΙ ΤΩΝ ΚΟΥΜΠΙΩΝ ΤΑ ΕΧΩ ΚΑΤΩ ΑΠΟ ΤΗΝ MAIN KAI STHN GUI ΤΑ ΕΧΩ ΚΑΝΕΙ IMPORT, ΑΡΑ ΕΙΤΕ ΤΙΣ ΜΕΤΑΦΕΡΟΥΜΕ ΣΤΟ GUI ΕΙΤΕ ΤΑ ΑΦΗΝΟΥΜ ΕΩΣ ΕΧΟΥΝ
            ΕΙΤΕ ΤΑ ΚΑΝΟΥΜΕ ΚΛΑΣΕΙΣ ΔΗΛΑΔΗ Η ΑΝΑΖΗΤΗΣΗ ΓΙΑ ΠΑΡΑΔΕΙΓΜΑ ΝΑ ΕΙΝΑΙ ΚΛΑΣΗ.*/

        });

            
}
    


//ΠΑΡΑΘΥΡΟ ΕΠΙΣΤΡΟΦΗΣ ΙΣΩΣ ΓΙΝΕΙ ΚΛΑΣΗ 
    
public static void displayResults(String name, Universities[] universities) {
    // Δημιουργία νέου παραθύρου για τα αποτελέσματα
    JFrame resultFrame = new JFrame("Αποτελέσματα Αναζήτησης");
    resultFrame.setSize(600, 400);
    resultFrame.setResizable(true); // Να είναι δυνατή η αλλαγή μεγέθους
    resultFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    resultFrame.setLocationRelativeTo(null);
    resultFrame.setAlwaysOnTop(true);

    // Ρύθμιση εικονιδίου παραθύρου
    URL iconUrl = UniApp.class.getClassLoader().getResource("resources/uni.png");
    if (iconUrl != null) {
        ImageIcon imageIcon = new ImageIcon(iconUrl);
        resultFrame.setIconImage(imageIcon.getImage());
    } 
    else 
    {
        System.out.println("TO eikonidio parathurou den vrethike");
    }

    // Παίζει ήχο
    SoundPlayer.playSound("resources/open_window.wav");

    // Δημιουργία του κυρίου panel για τα αποτελέσματα

    // Δημιουργία του κυρίου panel για τα αποτελέσματα
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout()); // Χρησιμοποιούμε BorderLayout για να τοποθετήσουμε το κουμπί στο κάτω μέρος

    // Δημιουργία panel για τα αποτελέσματα
    JPanel resultPanel = new JPanel();
    resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS)); // Κατακόρυφη διάταξη για τα αποτελέσματα

    JLabel resultLabel = new JLabel("Αναζητώ το πανεπιστήμιο: " + name);
    resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
    resultPanel.add(resultLabel);

    // Εφαρμογή CSS στυλ
    String cssStyles = "<style>" +
        ".university-title { font-weight: bold; font-size: 15px; font-family: 'Arial', sans-serif; }" +
        ".university-info { font-weight: normal; font-family: 'Arial', sans-serif; }" +
        "</style>";

    if (universities != null && universities.length > 0) 
    {
        for (Universities university : universities) {

            // Αύξηση των views κατά 1
            university.setViews(university.getViews() + 1);//αυξανω για τα στατιστικα

            // Δημιουργία panel για το κάθε πανεπιστήμιο
            JPanel universityPanel = new JPanel();
            universityPanel.setLayout(new BoxLayout(universityPanel, BoxLayout.Y_AXIS));
            universityPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Προσθήκη κενών γύρω από το πανεπιστήμιο

            // Δημιουργία του τίτλου του πανεπιστημίου με CSS κλάση
            JLabel universityTitle = new JLabel("<html><span class='university-title'>" + university.getName() + "</span></html>");
            universityTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
            universityPanel.add(universityTitle);

            // Δημιουργία των πληροφοριών για το πανεπιστήμιο με CSS κλάση
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

            // Προσθήκη clickable link για ιστοσελίδα του πανεπιστημίου
            JPanel webPanel = new JPanel();
            webPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            webPanel.add(new JLabel("Ιστοσελίδες: "));

            // Έλεγχος για webPages
            String webPages = university.getWebPagesString();  // Επιστρέφει string
            if (webPages != null && !webPages.isEmpty()) 
            {
                String[] webPagesArray = webPages.split(",");
                for (String webPage : webPagesArray) {
                    JLabel linkLabel = new JLabel("<html><a href='#'>" + webPage.trim() + "</a></html>");
                    linkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    linkLabel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            try {
                                Desktop.getDesktop().browse(new URI(webPage.trim()));
                            } catch (IOException | URISyntaxException ex) {
                            }
                        }
                    });
                    webPanel.add(linkLabel);
                }
            } 
            else 
            {
                webPanel.add(new JLabel("Δεν υπάρχουν καταχωρημένες ιστοσελίδες"));
            }
            infoPanel.add(webPanel);

            // Έλεγχος για domains
            String domains = university.getDomainsString();  // Επιστρέφει string
            if (domains != null && !domains.isEmpty()) 
            {
                String[] domainsArray = domains.split(",");
                for (String domain : domainsArray) {
                    infoPanel.add(new JLabel("<html><span class='university-info'>Domain: " + domain.trim() + "</span></html>"));
                }
            } 
            else 
            {
                infoPanel.add(new JLabel("<html><span class='university-info'>Domain: Δεν υπάρχουν domains</span></html>"));
            }

            // Εμφάνιση των επιπλέον πληροφοριών
            String shortDescription = university.getComments() != null ? university.getComments() : "Δεν υπάρχει περιγραφή";
            infoPanel.add(new JLabel("<html><span class='university-info'>Περιγραφή: " + shortDescription + "</span></html>"));

            String contactInfo = university.getContactInfo() != null ? university.getContactInfo() : "Δεν υπάρχουν πληροφορίες επικοινωνίας";
            infoPanel.add(new JLabel("<html><span class='university-info'>Επικοινωνία: " + contactInfo + "</span></html>"));
            
            // Προσθήκη των νέων πεδίων
            String country = university.getCountry() != null ? university.getCountry() : "Δεν υπάρχει χώρα";
            infoPanel.add(new JLabel("<html><span class='university-info'>Χώρα: " + country + "</span></html>"));

            String stateProvince = university.getStateProvince() != null ? university.getStateProvince() : "Δεν υπάρχει πόλη/περιφέρεια";
            infoPanel.add(new JLabel("<html><span class='university-info'>Πόλη/Περιφέρεια: " + stateProvince + "</span></html>"));

            String alphaTwoCode = university.getAlphaTwoCode() != null ? university.getAlphaTwoCode() : "Δεν υπάρχει κωδικός χώρας";
            infoPanel.add(new JLabel("<html><span class='university-info'>Κωδικός Χώρας (Alpha-2): " + alphaTwoCode + "</span></html>"));
            universityPanel.add(infoPanel);
            resultPanel.add(universityPanel);
            resultPanel.add(Box.createVerticalStrut(10));
        }
    } 
    else 
    {
        resultPanel.add(new JLabel("Δεν βρέθηκαν αποτελέσματα."));
    }

    // Δημιουργία scrollable panel
    JScrollPane scrollPane = new JScrollPane(resultPanel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setPreferredSize(new Dimension(580, 300)); // Καθορισμός μεγέθους 
    mainPanel.add(scrollPane, BorderLayout.CENTER);

    // Δημιουργία κουμπιού "Back"
    JButton backButton = new JButton("Back");
    backButton.addActionListener(e -> resultFrame.dispose());
    backButton.setBackground(Color.GREEN);
    backButton.setForeground(Color.BLACK);

//βαζω ηχο για το κουμπι BACK με αλλαγη χρωματος
backButton.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseEntered(MouseEvent e) {
        backButton.setBackground(new Color(85, 107, 47)); // Σκούρο κεραμιδί οσο μπορω δηλαδη
        SoundPlayer.playSound("resources/click.wav"); // Ήχος όταν περνάει το ποντίκι
    }

    @Override
    public void mouseExited(MouseEvent e) {
        backButton.setBackground(Color.GREEN); // Επιστροφή στο αρχικό χρώμα
    }
});

JPanel buttonPanel = new JPanel();

buttonPanel.add(backButton);
mainPanel.add(buttonPanel, BorderLayout.SOUTH); // Τοποθέτηση του κουμπιού στο κάτω μέρος

// Προσθήκη του κύριου panel στο παράθυρο
resultFrame.add(mainPanel);
resultFrame.setVisible(true);
}
    
    
//PAIZEI TELOS

public static void searchUniversity() {
    String name;
    while (true) 
    {
        name = JOptionPane.showInputDialog(null, "Εισάγετε το όνομα του πανεπιστημίου:");
        if (name == null)
        {
            JOptionPane.showMessageDialog(null, "Η αναζήτηση ακυρώθηκε.");
            return;
        }
        if (!name.trim().isEmpty()) break;
        JOptionPane.showMessageDialog(null, "Παρακαλώ εισάγετε το όνομα ενός πανεπιστημίου.");
    }

    // Δημιουργία DAO για την αναζήτηση στη βάση
    UniversitiesDAO dao = new UniversitiesDAO();
    Universities existingUniversity = dao.findByOriginalName(name); // Έλεγχος αν υπάρχει το πανεπιστήμιο
            
    
    //εχω εχω κανει χειροκινητη αποθηκευση στη βαση πανεπηστημιο καυ ψαχνω να το βρω τοτε μπαινει στο if και το επιστρεφει, αυανει και τα views των στατιστικων
    if (existingUniversity != null) {
            // Αν υπάρχει ήδη, αυξάνουμε το views
    existingUniversity.setViews(existingUniversity.getViews() + 1);
    // Αποθηκεύουμε ξανά το πανεπιστήμιο
    dao.saveOrUpdateUniversity(existingUniversity);
        // Αν υπάρχει ήδη, απλά το επιστρέφουμε
        JOptionPane.showMessageDialog(null, "Βρέθηκε το πανεπιστήμιο στη βάση: " + existingUniversity.getName());
        
        displayResults(name, new Universities[]{existingUniversity});
        return;
    }

    // Κλήση API για αναζήτηση πανεπιστημίων που εχει εισαγει ο χρηστης με βαση το ονομα
    ApiCall apiCaller = new ApiCall();
    String urlName = name.replace(" ", "%20");
    String urlString = "http://universities.hipolabs.com/search?name=" + urlName;
    String jsonResponse = apiCaller.getJsonResponse(urlString);

    if (jsonResponse != null && !jsonResponse.isEmpty()) {
        ApiParser parser = new ApiParser();
        Universities[] universities = parser.getUniversitiesByName(jsonResponse, name);

        if (universities != null && universities.length > 0) {
            Universities[] finalUniversities = new Universities[universities.length];
            int idx = 0;

            // Ελέγχουμε για κάθε πανεπιστήμιο αν υπάρχει ήδη στη βάση
            for (Universities uni : universities) {
                Universities existingInDb = dao.findByOriginalName(uni.getName());

                if (existingInDb != null) {
                    // Αν υπάρχει, αυξάνουμε το views και το αποθηκεύουμε ξανά
                    existingInDb.setViews(existingInDb.getViews() + 1);
                    dao.saveOrUpdateUniversity(existingInDb);
                    finalUniversities[idx] = existingInDb;
                    System.out.println("VRETHIKE STH BASH: " + existingInDb.getName() + " (Views: " + existingInDb.getViews() + ")");
                } else {
                    // Αν δεν υπάρχει, το αποθηκεύουμε
                    uni.setViews(1); // Το βάζουμε στο 1 γιατί είναι η πρώτη φορά που αναζητείται
                    dao.saveOrUpdateUniversity(uni);
                    finalUniversities[idx] = uni;
                    System.out.println("APOTHIKEUTHIKE STH BASH: " + uni.getName() + " (Views: " + uni.getViews() + ")");
                }
                idx++;//αυξηηση του loop για την λιαστα της βασης
            }

            // Εμφανίζουμε τα αποτελέσματα από τη βάση ή το API ανάλογα με την κατάσταση
            displayResults(name, finalUniversities);
        } else {
            JOptionPane.showMessageDialog(null, "DEN VRETHIKAN STH BASH");
        }
    } else {
        JOptionPane.showMessageDialog(null, "H APOKRISI TOU JSON EINAI ADEIA H LATHOS.");
    }
}





//ΘΕΛΕΙ DATA BASE


/*αυτη η μεθοδος κανει update στα ηδη καταχορημενα πανεπηστημια που ειναι στη βαση
με ΠΑΡΑΔΟΧΗ ότι θα μπορει να κανει μονο στα πεδια Ονομα, Περιοχη, Σχολια και Στοιχεια Επικοινωνιας*/
public static void updateUniversity() {
    String name = JOptionPane.showInputDialog(null, "Εισάγετε το OriginalName του πανεπιστημίου που θέλετε να ενημερώσετε ήδη υπάρχοντα στοιχεία:");
    if (name == null || name.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Δεν εισάγατε όνομα πανεπιστημίου.");
        return;
    }

    UniversitiesDAO dao = new UniversitiesDAO();
    Universities university = dao.findByOriginalName(name.trim());
    if (university == null) {
        JOptionPane.showMessageDialog(null, "Το πανεπιστήμιο δεν βρέθηκε.");
        return;
    }

    
    Map<String, String> existingValues = new LinkedHashMap<>();
    Map<String, JTextField> inputFields = new LinkedHashMap<>();

    
       
            existingValues.put("Όνομα", university.getName());
            inputFields.put("Όνομα", new JTextField(university.getName()));
        

        
            existingValues.put("Πόλη", university.getStateProvince());
            inputFields.put("Πόλη", new JTextField(university.getStateProvince()));
        

       
            existingValues.put("Σχόλια", university.getComments());
            inputFields.put("Σχόλια", new JTextField(university.getComments()));
        

       
            existingValues.put("Στοιχεία Επικοινωνίας", university.getContactInfo());
            inputFields.put("Στοιχεία Επικοινωνίας", new JTextField(university.getContactInfo()));
        


    // Δημιουργία παραθυρο με 2 στήλες (υπάρχουσα τιμή - νέα τιμή)
    JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

    for (Map.Entry<String, JTextField> entry : inputFields.entrySet()) {
        String fieldLabel = entry.getKey();
        JTextField inputField = entry.getValue();

        panel.add(new JLabel(fieldLabel + ": ")); // Ετικέτα αριστερά
        panel.add(new JLabel(existingValues.get(fieldLabel))); // Υπάρχουσα τιμή
        panel.add(new JLabel("Νέα τιμή: ")); // Ετικέτα για νέο πεδίο
        panel.add(inputField); // Πεδίο εισαγωγής δεξιά
    }

    // Δημιουργία κουμπιού για τη διαγραφή
    JButton deleteButton = new JButton("Διαγραφή");
    deleteButton.addActionListener(e -> {
        int confirm1 = JOptionPane.showConfirmDialog(null, "Είστε σίγουροι ότι θέλετε να διαγράψετε το πανεπιστήμιο;", "Επιβεβαίωση Διαγραφής", JOptionPane.YES_NO_OPTION);
        if (confirm1 == JOptionPane.YES_OPTION) {
            int confirm2 = JOptionPane.showConfirmDialog(null, "Είστε απόλυτα σίγουροι; Αυτή η ενέργεια δεν μπορεί να αναιρεθεί.", "Τελική Επιβεβαίωση Διαγραφής", JOptionPane.YES_NO_OPTION);
            if (confirm2 == JOptionPane.YES_OPTION) {
                dao.deleteUniversity(university);  // Κλήση για διαγραφή
                JOptionPane.showMessageDialog(null, "Το πανεπιστήμιο διαγράφηκε επιτυχώς.");

                // Κλείσιμο του παραθύρου
                Window window = SwingUtilities.getWindowAncestor(panel);
                if (window != null) {
                    window.dispose(); // Κλείνει το παράθυρο
                }
            }
        }
    });

    // Δημιουργία ενός νέου πάνελ για το κουμπί διαγραφής
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Τοποθετούμε το κουμπί δεξιά
    buttonPanel.add(deleteButton);

    // Προσθήκη του κουμπι στο κύριο panel
    panel.add(new JLabel()); // Κενό κελί για να μην χαλάσει η διάταξη
    panel.add(buttonPanel);

    // Εμφάνιση παραθύρου με τα πεδία και το κουμπί διαγραφής
    int result = JOptionPane.showConfirmDialog(null, panel, "Ενημέρωση: " + university.getOriginalName(), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (result == JOptionPane.OK_OPTION) {
        // Ενημέρωση των συμπληρωμένων πεδίων
        if (inputFields.containsKey("Όνομα")) university.setName(inputFields.get("Όνομα").getText());
        if (inputFields.containsKey("Πόλη")) university.setStateProvince(inputFields.get("Πόλη").getText());
        if (inputFields.containsKey("Σχόλια")) university.setComments(inputFields.get("Σχόλια").getText());
        if (inputFields.containsKey("Στοιχεία Επικοινωνίας")) university.setContactInfo(inputFields.get("Στοιχεία Επικοινωνίας").getText());

        // Αποθήκευση στη βάση
        dao.saveOrUpdateUniversity(university);
        JOptionPane.showMessageDialog(null, "Οι αλλαγές αποθηκεύτηκαν επιτυχώς.");
    }
}


public static void addUniversity() {
    // Δημιουργία του αντικειμένου για την αλληλεπίδραση με τη βάση δεδομένων
    UniversitiesDAO dao = new UniversitiesDAO();

    // Χάρτης για τα πεδία εισαγωγής και τις αντίστοιχες περιγραφές τους
    Map<String, JTextField> inputFields = new LinkedHashMap<>();
    inputFields.put("Όνομα Πανεπιστημίου (π.χ. Πανεπιστήμιο Αθηνών) - ΥΠΟΧΡΕΩΤΙΚΟ", new JTextField(20)); // Όνομα του πανεπιστημίου
    inputFields.put("Domains (π.χ. eap1.gr, eap2.gr)", new JTextField(20)); // Τομέας / Domains
    inputFields.put("Χώρα (π.χ. Ελλάδα) - ΥΠΟΧΡΕΩΤΙΚΟ", new JTextField(20)); // Χώρα (Υποχρεωτικό)
    inputFields.put("Πόλη / Περιφέρεια (π.χ. Αθήνα)", new JTextField(20)); // Πόλη ή Περιφέρεια
    inputFields.put("Κωδικός Χώρας (Alpha-2) (π.χ. GR) - ΥΠΟΧΡΕΩΤΙΚΟ", new JTextField(2)); // Κωδικός χώρας (Υποχρεωτικό)
    inputFields.put("Ιστότοποι (π.χ. https://www.eap.gr)", new JTextField(20)); // Ιστότοποι
    inputFields.put("Στοιχεία Επικοινωνίας", new JTextField(20)); // Στοιχεία Επικοινωνίας
    inputFields.put("Σχόλια", new JTextField(20)); // Σχόλια
    
    
    // Δημιουργία ενός JPanel για να προσθέσουμε τα πεδία εισαγωγής
    JPanel panel = new JPanel(new GridLayout(inputFields.size(), 2, 10, 10));

    // Προσθήκη ετικετών και πεδίων εισαγωγής στο panel
    for (Map.Entry<String, JTextField> entry : inputFields.entrySet()) {
        panel.add(new JLabel(entry.getKey() + ": "));
        panel.add(entry.getValue());
    }

    // Εμφάνιση του παραθύρου για την εισαγωγή δεδομένων
    int result = JOptionPane.showConfirmDialog(null, panel, "Εισαγωγή Πανεπιστημίου", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    // Εάν ο χρήστης πατήσει OK
    if (result == JOptionPane.OK_OPTION) 
    {
        // Ανάκτηση του ονόματος του πανεπιστημίου και έλεγχος αν είναι κενό
        JTextField universityNameField = inputFields.get("Όνομα Πανεπιστημίου (π.χ. Πανεπιστήμιο Αθηνών) - ΥΠΟΧΡΕΩΤΙΚΟ");
        if (universityNameField == null || universityNameField.getText().trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, "Το όνομα του πανεπιστημίου δεν μπορεί να είναι κενό.", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String universityName = universityNameField.getText().trim();

        // Έλεγχος για τα υποχρεωτικά πεδία: Χώρα και Κωδικός Χώρας
        JTextField countryField = inputFields.get("Χώρα (π.χ. Ελλάδα) - ΥΠΟΧΡΕΩΤΙΚΟ");
        JTextField countryCodeField = inputFields.get("Κωδικός Χώρας (Alpha-2) (π.χ. GR) - ΥΠΟΧΡΕΩΤΙΚΟ");
        if (countryField == null || countryCodeField == null || 
            countryField.getText().trim().isEmpty() || countryCodeField.getText().trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, "Τα πεδία 'Χώρα' και 'Κωδικός Χώρας' είναι υποχρεωτικά.", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Έλεγχος για έγκυρο κωδικό χώρας (Alpha-2), που πρέπει να έχει 2 χαρακτήρες
        String alphaTwoCode = countryCodeField.getText().trim();
        if (alphaTwoCode.length() != 2 || !alphaTwoCode.matches("[A-Za-z]{2}")) {
            JOptionPane.showMessageDialog(null, "Ο κωδικός χώρας πρέπει να είναι 2 λατινικοί χαρακτήρες.", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Έλεγχος αν υπάρχει ήδη το πανεπιστήμιο με το ίδιο originalName στη βάση δεδομένων
        if (dao.existsByOriginalName(universityName)) 
        {
            // Αν υπάρχει, ζητάμε από τον χρήστη να εισάγει διαφορετικό όνομα
            JTextField newNameField = new JTextField(20);
            JPanel renamePanel = new JPanel(new GridLayout(2, 1));
            renamePanel.add(new JLabel("Το πανεπιστήμιο υπάρχει ήδη! Παρακαλώ εισάγετε διαφορετικό Name:"));
            renamePanel.add(newNameField);

            // Εμφανίζουμε το παράθυρο για την αλλαγή ονόματος
            int renameResult = JOptionPane.showConfirmDialog(null, renamePanel, "Προειδοποίηση", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

            // Αν ο χρήστης δεν εισάγει νέο όνομα ή ακυρώσει, εμφανίζεται μήνυμα ακύρωσης
            if (renameResult != JOptionPane.OK_OPTION || newNameField.getText().trim().isEmpty()) 
            {
                JOptionPane.showMessageDialog(null, "Η εισαγωγή ακυρώθηκε.", "Ακύρωση", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Αν έχει εισάγει νέο όνομα, το αλλάζουμε
            universityName = newNameField.getText().trim();
        }

        // Δημιουργία αντικειμένου για το νέο πανεπιστήμιο
        Universities newUniversity = new Universities();
        newUniversity.setName(universityName);  // Όνομα του πανεπιστημίου
        newUniversity.setOriginalName(inputFields.get("Όνομα Πανεπιστημίου (π.χ. Πανεπιστήμιο Αθηνών) - ΥΠΟΧΡΕΩΤΙΚΟ").getText().trim());  // Το originalName παραμένει το ίδιο
        newUniversity.setCountry(countryField.getText().trim());  // Χώρα του πανεπιστημίου
        newUniversity.setStateProvince(inputFields.get("Πόλη / Περιφέρεια (π.χ. Αθήνα)").getText().trim());  // Πόλη / Περιφέρεια
        newUniversity.setAlphaTwoCode(alphaTwoCode);  // Κωδικός χώρας Alpha-2

        // Επεξεργασία και αποθήκευση των domains
        String[] domains = Arrays.stream(inputFields.get("Domains (π.χ. eap1.gr, eap2.gr)").getText().trim().split(","))
                                 .map(String::trim)
                                 .distinct()
                                 .toArray(String[]::new);
        newUniversity.setDomains(domains);

        // Επεξεργασία και αποθήκευση των ιστοσελίδων
        String[] webPages = Arrays.stream(inputFields.get("Ιστότοποι (π.χ. https://www.eap.gr)").getText().trim().split(","))
                                  .map(String::trim)
                                  .distinct()
                                  .toArray(String[]::new);
        newUniversity.setWebPages(webPages);

        // Έλεγχος αν όλοι οι ιστότοποι είναι έγκυροι URLs
        for (String webPage : webPages) 
        {
            // Αν βρούμε άκυρο URL, εμφανίζεται μήνυμα σφάλματος και τερματίζουμε
            if (!webPage.isEmpty() && !webPage.matches("^(https?|ftp)://[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(:[0-9]+)?(/.*)?$")) 
            {
                JOptionPane.showMessageDialog(null, "Ο ιστότοπος '" + webPage + "' δεν είναι έγκυρο URL.", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

                // Προσθήκη των νέων πεδίων που θελω να εχω
        if (inputFields.containsKey("Σχόλια")) newUniversity.setComments(inputFields.get("Σχόλια").getText().trim());
        if (inputFields.containsKey("Στοιχεία Επικοινωνίας")) newUniversity.setContactInfo(inputFields.get("Στοιχεία Επικοινωνίας").getText().trim());
        
        //debugging γιατι δεν αποθηκευει τπτ στη βαση
        // Εμφάνιση των δεδομένων του πανεπιστημίου πριν την αποθήκευση
    System.out.println("Όνομα Πανεπιστημίου: " + newUniversity.getName());
    System.out.println("Original Name: " + newUniversity.getOriginalName());
    System.out.println("Χώρα: " + newUniversity.getCountry());
    System.out.println("Πόλη / Περιφέρεια: " + newUniversity.getStateProvince());
    System.out.println("Κωδικός Χώρας (Alpha-2): " + newUniversity.getAlphaTwoCode());
    System.out.println("Domains: " + String.join(", ", newUniversity.getDomains()));
    System.out.println("Ιστότοποι: " + String.join(", ", newUniversity.getWebPages()));
    System.out.println("Στοιχεία Επικοινωνίας: " + newUniversity.getContactInfo());
    System.out.println("Σχόλια: " + newUniversity.getComments());
        // Εισαγωγή του νέου πανεπιστημίου στη βάση δεδομένων
        dao.addNewUniversity(newUniversity);

        // Εμφάνιση επιτυχίας ή σφάλματος αναλόγως με την ύπαρξη του πανεπιστημίου στη βάση με βαση το original ονομα του
        if (dao.existsByOriginalName(universityName)) 
        {
            JOptionPane.showMessageDialog(null, "Το πανεπιστήμιο προστέθηκε επιτυχώς!", "Επιτυχία", JOptionPane.INFORMATION_MESSAGE);
        } 
        else 
        {
            JOptionPane.showMessageDialog(null, "Αποτυχία αποθήκευσης στη βάση.", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
        }
    }
}





    //PAIZEI TELOS EPISTREFEI ΤΑ ΠΑΝΕΠΗΣΤΗΜΙΑ ΟΤΑΝ ΕΙΣΑΓΩ ΤΟ ΟΝΟΜΑ ΤΗΣ ΧΩΡΑΣ
  
public static void showUniversitiesByCountry() {
    String country = JOptionPane.showInputDialog(null, "Εισάγετε τη χώρα για προβολή των πανεπιστημίων:");
    if (country != null && !country.trim().isEmpty()) {
        ApiCall apiCaller = new ApiCall();
        String jsonResponse = apiCaller.getJsonResponse("http://universities.hipolabs.com/search?country=" + country);
        ApiParser parser = new ApiParser();
        Universities[] universities = parser.getUniversitiesByCountry(jsonResponse, country);

        // Αποθήκευση των αποτελεσμάτων στη βάση δεδομένων
        UniversitiesDAO dao = new UniversitiesDAO();
        for (Universities university : universities) {
            dao.saveOrUpdateUniversity(university);
        }

        // Εμφάνιση των αποτελεσμάτων σε νέο παράθυρο
        displayUniversityList(country, universities);
    } else {
        JOptionPane.showMessageDialog(null, "Η χώρα είναι κενή.");
    }
}
   
   

    
    
    

public static void searchByKeyword() {
    // Εισαγωγή χώρας από τον χρήστη
    String country = JOptionPane.showInputDialog(null, "Εισάγετε τη χώρα:");
    if (country == null || country.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Δεν εισάγατε χώρα.");
        return;
    }

    // Εισαγωγή keyword από τον χρήστη
    String keyword = JOptionPane.showInputDialog(null, "Εισάγετε το keyword ή τη φράση για αναζήτηση:");
    if (keyword == null || keyword.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Δεν εισάγατε keyword.");
        return;
    }

    // Κλήση API για αναζήτηση πανεπιστημίων στη συγκεκριμένη χώρα
    ApiCall apiCaller = new ApiCall();
    String urlCountry = country.replace(" ", "%20");
    String urlKeyword = keyword.replace(" ", "%20");
    String urlString = "http://universities.hipolabs.com/search?country=" + urlCountry + "&name=" + urlKeyword;
    String jsonResponse = apiCaller.getJsonResponse(urlString);

    if (jsonResponse != null && !jsonResponse.isEmpty()) {
        ApiParser parser = new ApiParser();
        Universities[] universities = parser.getUniversitiesByNameAndCountry(jsonResponse, keyword, country);

        if (universities != null && universities.length > 0) {
            // Αποθήκευση των αποτελεσμάτων στη βάση δεδομένων
            UniversitiesDAO dao = new UniversitiesDAO();
            for (Universities university : universities) {
                dao.saveOrUpdateUniversity(university);
            }

            // Εμφάνιση των αποτελεσμάτων σε νέο παράθυρο
            displayUniversityList(country, universities);
        } else {
            // Αν δεν βρεθεί κανένα πανεπιστήμιο
            JOptionPane.showMessageDialog(null, "Δεν βρέθηκαν πανεπιστήμια στη χώρα " + country + " με το keyword '" + keyword + "'.");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Η απόκριση JSON είναι άδεια ή μη έγκυρη.");
    }
}


 


    
  
  
  
  //ΠΑΡΑΘΥΡΟ ΛΙΣΤΑ ΜΕ ΤΑ ΠΑΝΕΠΗΣΤΗΜΙΑ ΩΣΤΕ ΝΑ ΤΑ ΕΠΙΛΕΓΩ ΚΑΙ ΝΑ Μ ΕΜΦΑΝΙΖΕΙ ΣΤΟΙΧΕΙΑ ΚΑΙ ΝΑ ΑΥΞΑΝΕΙ ΤΟΝ ΑΡΙΘΜΟ ΓΙΑ ΣΤΑΤΙΣΤΙΚΑ
 public static void displayUniversityList(String country, Universities[] universities) {
    if (universities == null || universities.length == 0) {
        JOptionPane.showMessageDialog(null, "Δεν βρέθηκαν πανεπιστήμια για τη χώρα: " + country);
        return;
    }

    // Δημιουργία νέου παραθύρου
    JFrame listFrame = new JFrame("Πανεπιστήμια στη χώρα: " + country);
    listFrame.setSize(600, 400);
    listFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    listFrame.setLocationRelativeTo(null);
    
    listFrame.setResizable(true); // Να είναι δυνατή η αλλαγή μεγέθους

    listFrame.setAlwaysOnTop(true); // να ειναι παντα μπροστα στο προσκηνιο
    
    
        // Ρύθμιση εικονιδίου παραθύρου
    URL iconUrl = UniApp.class.getClassLoader().getResource("resources/uni.png");
    if (iconUrl != null) {
        ImageIcon imageIcon = new ImageIcon(iconUrl);
        listFrame.setIconImage(imageIcon.getImage());
    } else {
        System.out.println("TO eikonidio parathurou den vrethike");
    }
    // Παίζει ήχο
    SoundPlayer.playSound("resources/open_window.wav");
    // Κύριο panel
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());

    // Panel για τη λίστα των πανεπιστημίων
    JPanel listPanel = new JPanel();
    listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

    // Εμφάνιση κάθε πανεπιστημίου
    for (Universities university : universities) {
        // Δημιουργία panel για το κάθε πανεπιστήμιο
        JPanel universityPanel = new JPanel();
        universityPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        universityPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Εμφάνιση του ονόματος του πανεπιστημίου
        JLabel nameLabel = new JLabel(university.getName());
        universityPanel.add(nameLabel);

        // Κουμπί "Details"
        JButton detailsButton = new JButton("Details");
        detailsButton.addActionListener(e -> {
            // Καλεί το παράθυρο με τις λεπτομέρειες του πανεπιστημίου
            displayUniversityDetails(university);
        });
        universityPanel.add(detailsButton);

        // Προσθήκη του universityPanel στο listPanel
        listPanel.add(universityPanel);
    }

    // Προσθήκη του listPanel σε ένα JScrollPane
    JScrollPane scrollPane = new JScrollPane(listPanel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    mainPanel.add(scrollPane, BorderLayout.CENTER);

    // Προσθήκη του mainPanel στο παράθυρο
    listFrame.add(mainPanel);
    listFrame.setVisible(true);
}
    
  //τα εμφανιζει απο τη βαση
public static void displayUniversityDetails(Universities university) {
    // Φέρε τα πιο πρόσφατα δεδομένα από τη βάση δεδομένων
    UniversitiesDAO dao = new UniversitiesDAO();
    Universities updatedUniversity = dao.findByOriginalName(university.getOriginalName());

    if (updatedUniversity != null) {
        // Αύξηση του views κατά 1
        updatedUniversity.setViews(updatedUniversity.getViews() + 1);

        // Αποθήκευση της αλλαγής στη βάση δεδομένων
        dao.saveOrUpdateUniversity(updatedUniversity);

        // Εμφάνιση των λεπτομερειών
        Universities[] universities = new Universities[]{updatedUniversity};
        displayResults(updatedUniversity.getName(), universities);
    } else {
        JOptionPane.showMessageDialog(null, "Δεν βρέθηκαν λεπτομέρειες για το πανεπιστήμιο.");
    }
}
}
