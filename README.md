# UniApp (Java Application)

UniApp είναι μια εφαρμογή Java που δημιουργήθηκε στα πλαίσια εκπόνησης εργασίας για το Ελληνικό Ανοιχτό Πανεπιστήμιο (ΕΑΠ) και επεξεργάζεται δεδομένα πανεπιστημίων από όλο τον κόσμο, χρησιμοποιώντας το [University Domains and Names Data List API](https://github.com/Hipo/university-domains-list-api). Παρέχει ένα γραφικό περιβάλλον χρήστη (GUI) με διάφορες λειτουργίες για αναζήτηση και διαχείριση πανεπιστημίων.

## Χαρακτηριστικά

- **Αναζήτηση πανεπιστημίου** με βάση το όνομα ή keyword
 ![syntomo-ezgif com-video-to-gif-converter (1)](https://github.com/user-attachments/assets/eedbbbed-7665-40c0-bb13-8c0051e77483)

- **Ενημέρωση πληροφοριών πανεπιστημίου**
  ![syntomo-ezgif com-video-to-gif-converter (2)](https://github.com/user-attachments/assets/9c9a23ca-f540-473e-9f7a-105f74a846c1)

- **Προσθήκη πληροφοριών** για νεο πανεπιστήμιο
  ![syntomo-ezgif com-video-to-gif-converter (3)](https://github.com/user-attachments/assets/32594f8a-cf51-4778-af7d-a667219f4cbf)

- **Προβολή πανεπιστημίων** ανά χώρα
- **Προβολή πανεπιστημίων** ανά χώρα με βάση λέξεις-κλειδιά
- **Προβολή στατιστικών** για τις αναζητήσεις
- **Εξαγωγή στατιστικών σε PDF**
![syntomo-ezgif com-video-to-gif-converter (4)](https://github.com/user-attachments/assets/bc19cf00-c546-4d6a-9380-ea3093fa9e3b)

## Τεχνολογίες που χρησιμοποιούνται

- **Java** για την υλοποίηση της εφαρμογής
- **JavaFX** για το GUI
- **JPA (EclipseLink)** για την αποθήκευση δεδομένων
- **Apache Derby** ως βάση δεδομένων
- **OkHttp** για την εκτέλεση HTTP αιτημάτων
- **Gson** για την επεξεργασία JSON δεδομένων από το API
- **JFreeChart** για την οπτικοποίηση στατιστικών 
- **OpenPDF** για την εξαγωγή στατιστικών σε PDF


## API που χρησιμοποιείται

Η εφαρμογή χρησιμοποιεί το [University Domains and Names Data List API](https://github.com/Hipo/university-domains-list-api) για την ανάκτηση δεδομένων πανεπιστημίων. Ένα παράδειγμα κλήσης API για την αναζήτηση πανεπιστημίων στην Ελλάδα είναι το εξής:

```
http://universities.hipolabs.com/search?country=greece
```

Τα δεδομένα που επιστρέφονται είναι σε μορφή JSON και επεξεργάζονται μέσω της βιβλιοθήκης Gson.

## Εκτέλεση

1. **Κλωνοποιήστε το αποθετήριο**:
   ```sh
   git clone https://github.com/your-repo/uniapp.git
   ```
2. **Ανοίξτε το έργο στο NetBeans**
3. **Εκτελέστε το αρχείο UniApp.java**

## Σημείωση

Οι απαιτούμενες βιβλιοθήκες περιλαμβάνονται τοπικά στο έργο και δεν απαιτείται επιπλέον εγκατάσταση.

