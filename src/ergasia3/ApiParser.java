/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ergasia3;

/**
 *
 * @author kalmi
 */
import com.google.gson.Gson;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ApiParser {

    
    
       
public Universities[] getUniversitiesByName(String json, String name) {
    try {
        System.out.println("Received JSON: " + json);

        // Δημιουργία αντικειμένου gson για να γίνει η αποπαρσινγκ του json
        Gson gson = new Gson();

        // Μετατροπή JSON σε πίνακα Universities[]
        Universities[] universitiesArray = gson.fromJson(json, Universities[].class);

        // Εκτύπωση του raw JSON και του parsed array για debugging
        System.out.println("Ektyposh JSON: " + json);
        System.out.println("Parsed Universities Array: " + Arrays.toString(universitiesArray));

        //debugging γτ δεν μ εμφανιζει οτι βαζω μονος μ στη βαση στην αναζητηση
                

        
        
        // Φιλτράρισμα των πανεπιστημίων με βάση το όνομα
        List<Universities> filteredUniversities = Arrays.stream(universitiesArray)
                .filter(university -> {
                    // Εκτύπωση για debugging
                    System.out.println("Checking: " + university.getName());
                    // Συγκρίνουμε το όνομα με  case-insensitive
                    return university.getName().toLowerCase().contains(name.toLowerCase());
                })
                .collect(Collectors.toList());

        // Μετατροπή των πινάκων webPages και domains σε συμβολοσειρές
        for (Universities university : filteredUniversities) {
            // Αποθήκευση του originalName από το name του API
            university.setOriginalName(university.getName());

            // Μετατροπή του πίνακα webPages σε String γτ ειχαμε θεμα στην αποθηκευση 
            if (university.getWebPages() != null && university.getWebPages().length > 0) {
                university.setWebPagesString(String.join(",", university.getWebPages()));
            } else {
                university.setWebPagesString("No web pages available");
            }

            // Μετατροπή του πίνακα domains σε String
            if (university.getDomains() != null && university.getDomains().length > 0) {
                university.setDomainsString(String.join(",", university.getDomains()));
            } else {
                university.setDomainsString("No domains available");
            }
        }

        // Εκτύπωση των φιλτραρισμένων αποτελεσμάτων για debugging
        System.out.println("Filtered Universities: " + filteredUniversities);

        // Επιστροφή του φιλτραρισμένου πίνακα
        return filteredUniversities.toArray(new Universities[0]);
    } catch (Exception e) {
        // Εκτύπωση σφάλματος αν υπάρχει πρόβλημα
        System.out.println("Error parsing JSON: " + e.getMessage());
        e.printStackTrace();
        return new Universities[0]; // Επιστρέφει έναν κενό πίνακα σε περίπτωση σφάλματος
    }
}
    
    
       public Universities[] getUniversitiesByCountry(String json, String country) {
    try {
        Gson gson = new Gson();
        Universities[] universitiesArray = gson.fromJson(json, Universities[].class);

        // Εκτύπωση του raw JSON και του parsed array για debugging
        System.out.println("Raw JSON: " + json);
        System.out.println("Parsed Universities Array: " + Arrays.toString(universitiesArray));

        // Φιλτράρισμα των πανεπιστημίων με βάση τη χώρα
        List<Universities> filteredUniversities = Arrays.stream(universitiesArray)
                .filter(university -> {
                    // Εκτύπωση για debugging
                    System.out.println("Checking: " + university.getName());
                    // Συγκρίνουμε τη χώρα με equals (case-insensitive)
                    return university.getCountry().equalsIgnoreCase(country);
                })
                .collect(Collectors.toList());

        // Μετατροπή των πινάκων webPages και domains σε συμβολοσειρές
        for (Universities university : filteredUniversities) {
            // Αρχικοποίηση του originalName με το όνομα που επιστρέφει το API
            university.setOriginalName(university.getName());

            // Μετατροπή του πίνακα webPages σε String
            if (university.getWebPages() != null && university.getWebPages().length > 0) {
                university.setWebPagesString(String.join(",", university.getWebPages()));
            } else {
                university.setWebPagesString("No web pages available");
            }

            // Μετατροπή του πίνακα domains σε String
            if (university.getDomains() != null && university.getDomains().length > 0) {
                university.setDomainsString(String.join(",", university.getDomains()));
            } else {
                university.setDomainsString("No domains available");
            }
                        


            // Αρχικοποίηση του originalName με την τιμή του name ωστε να πειραζω το name και να κραταω παντα το original name
            if (university.getOriginalName() == null) {
                university.setOriginalName(university.getName());
            }
        }

        // Εκτύπωση των  αποτελεσμάτων για debugging
        System.out.println("Filtered Universities: " + filteredUniversities);

        // Επιστροφή του  πίνακα
        return filteredUniversities.toArray(new Universities[0]);
    } catch (Exception e) {
        // Εκτύπωση σφάλματος αν υπάρχει 
        System.out.println("Error parsing JSON: " + e.getMessage());
        e.printStackTrace();
        return new Universities[0]; // Επιστρέφει έναν κενό πίνακα σε περίπτωση σφάλματος
    }
}

       
       public Universities[] getUniversitiesByNameAndCountry(String json, String name, String country) {
    try {
        // Δημιουργία αντικειμένου gson για να γίνει η αποπαρσινγκ του JSON
        Gson gson = new Gson();

        // Μετατροπή JSON σε πίνακα Universities[]
        Universities[] universitiesArray = gson.fromJson(json, Universities[].class);

        // Εκτύπωση του raw JSON και του parsedarray για debugging
        System.out.println("Raw JSON: " + json);
        System.out.println("Parsed Universities Array: " + Arrays.toString(universitiesArray));

        // Φιλτράρισμα των πανεπιστημίων με βάση τη χώρα και το όνομα
        List<Universities> filteredUniversities = Arrays.stream(universitiesArray)
                .filter(university -> {
                    // Εκτύπωση για debugging
                    System.out.println("Checking: " + university.getName());
                    // Συγκρίνουμε τη χώρα με caseinsensitive
                    boolean matchesCountry = university.getCountry().equalsIgnoreCase(country);
                    // Συγκρίνουμε το όνομα με contains case-insensitive
                    boolean matchesName = university.getName().toLowerCase().contains(name.toLowerCase());

                    // Αν το πανεπιστήμιο πληροί και τις δύο συνθήκες, το προσθέτουμε
                    return matchesCountry && matchesName;
                })
                .collect(Collectors.toList());

        // Μετατροπή των πινάκων webPages και domains σε συμβολοσειρές
        for (Universities university : filteredUniversities) {
            // Αποθήκευση του originalName από το name του API
            university.setOriginalName(university.getName());

            // Μετατροπή του πίνακα webPages σε String
            if (university.getWebPages() != null && university.getWebPages().length > 0) {
                university.setWebPagesString(String.join(",", university.getWebPages()));
            } else {
                university.setWebPagesString("No web pages available");
            }

            // Μετατροπή του πίνακα domains σε String
            if (university.getDomains() != null && university.getDomains().length > 0) {
                university.setDomainsString(String.join(",", university.getDomains()));
            } else {
                university.setDomainsString("No domains available");
            }
        }

        // Εκτύπωση των  αποτελεσμάτων για debugging
        System.out.println("Filtered Universities: " + filteredUniversities);

        // Επιστροφή του  πίνακα
        return filteredUniversities.toArray(new Universities[0]);
    } catch (Exception e) {
        // Εκτύπωση σφάλματος αν υπάρχει 
        System.out.println("Error parsing JSON: " + e.getMessage());
        e.printStackTrace();
        return new Universities[0]; // Επιστρέφει έναν κενό πίνακα σε περίπτωση σφάλματος
    }
}
       
       
       
}