/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ergasia3;

import javax.persistence.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniversitiesDAO {
    
    private EntityManagerFactory emf;
    private EntityManager em;

    // Κατασκευαστής που δημιουργεί το EntityManagerFactory και το EntityManager
    public UniversitiesDAO() {
        emf = Persistence.createEntityManagerFactory("ergasia3PU");
        em = emf.createEntityManager();
    }

    // Μέθοδος για να αποθηκεύσεις ή να ενημερώσεις μια University
public void saveOrUpdateUniversity(Universities university) {
    EntityTransaction transaction = em.getTransaction();
    try {
        if (!transaction.isActive()) {
            transaction.begin();
        }

        // Λήψη του originalName και έλεγχος για null γτ μου βγαζει σφλαματα στο ιντε ενω στο GUI οχι
        String originalName = university.getOriginalName();
        if (originalName != null) {
            originalName = originalName.trim();  // Trim αν δεν είναι null
        } else {
            originalName = "";  // Αν είναι null, να ορίσουμε κενή τιμή
        }

        // Εμφάνιση του όνομα για debug
        System.out.println("Ψάχνω για το πανεπιστήμιο με όνομα: '" + originalName + "'");

        // Αναζήτηση με βάση το originalName (trim και case-insensitive)
        TypedQuery<Universities> query = em.createQuery(
            "SELECT u FROM Universities u WHERE LOWER(TRIM(u.originalName)) = LOWER(TRIM(:originalName))", Universities.class);

        query.setParameter("originalName", originalName);
        List<Universities> existingUniversities = query.getResultList();

        if (!existingUniversities.isEmpty()) {
            // Το πανεπιστήμιο υπάρχει ήδη, δεν κάνουμε καμία καταχώρηση
            System.out.println("Το πανεπιστήμιο υπάρχει ήδη στη βάση. Καμία καταχώρηση δεν θα γίνει.");
            transaction.commit();  // Κλείνουμε την συναλλαγή επιτυχώς
            return;  // Επιστροφή χωρίς να γίνει τίποτα
        } else {
            // Αν το πανεπιστήμιο δεν υπάρχει στη βάση, το αποθηκεύουμε
            System.out.println("Δεν βρέθηκε το πανεπιστήμιο. Αποθήκευση νέου...");

            // Ορισμός του views σε 1 για νέα εγγραφή
            university.setViews(1);

            em.persist(university);  // Αποθήκευση του πανεπιστημίου
            transaction.commit();  // Κλείσιμο συναλλαγής με αποδοχή των αλλαγών
        }
    } catch (Exception e) {
        // Αν υπάρχει σφάλμα, κάνουμε ρολάρισμα της συναλλαγής για να μην αφήσουμε αλλαγές στη βάση
        if (transaction.isActive()) {
            transaction.rollback();
        }
        e.printStackTrace();
        System.out.println("SFALAMA ENHMEROSHS /APOTHIKEUSHS.");
    }
}

//αυτες οι μεθοδοι ειναι για την adduniveristy για την εισαγωγη και τον εγεχο αν υπαρχει ηδη το ιδιο originalname στη βαση
public void addNewUniversity(Universities university) {
    // Έλεγχος αν υπάρχει ήδη πανεπιστήμιο με το ίδιο originalName
    if (findByOriginalName(university.getOriginalName()) != null) {
        System.out.println("To panepistimio uparxei idi sth vash.");
        return;
    }

    // Αν δεν υπάρχει, τότε αποθηκεύουμε το νέο πανεπιστήμιο
    saveOrUpdateUniversity(university);
}
public boolean existsByOriginalName(String originalName) {
    TypedQuery<Long> query = em.createQuery(
        "SELECT COUNT(u) FROM Universities u WHERE LOWER(TRIM(u.originalName)) = LOWER(TRIM(:originalName))", Long.class
    );
    query.setParameter("originalName", originalName.trim());

    return query.getSingleResult() > 0;
}


public Universities findByOriginalName(String originalName) {
    try {
        System.out.println("Anazitisi gia to panepistimio: [" + originalName + "]");
        
        TypedQuery<Universities> query = em.createQuery(
            "SELECT u FROM Universities u WHERE LOWER(TRIM(u.originalName)) = LOWER(TRIM(:originalName))", Universities.class);
        query.setParameter("originalName", originalName.trim().toLowerCase());

        Universities found = query.getSingleResult();
        System.out.println("Vrethike to : [" + found.getOriginalName() + "]");
        return found;
    } catch (NoResultException e) {
        System.out.println("Den vrethike to panepistimio me onoma: [" + originalName + "]");
        return null;
    }
}

    // Μέθοδος για διαγραφή πανεπιστημίου
    public void deleteUniversity(Universities university) {
        EntityTransaction transaction = em.getTransaction();
        try {
            if (!transaction.isActive()) {
                transaction.begin();
            }

            // Διαγραφή του πανεπιστημίου
            university = em.find(Universities.class, university.getId()); // Εξασφαλίζει ότι η οντότητα είναι συγχρονισμένη
            if (university != null) {
                em.remove(university); // Διαγραφή από τη βάση δεδομένων
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("Σφάλμα κατά τη διαγραφή του πανεπιστημίου.");
        }
    }

    
    
    public Map<String, Integer> getStatistics() {
        Map<String, Integer> statistics = new HashMap<>();
        
        // Ερώτημα για τα πανεπιστήμια και τον αριθμό των views τους
        TypedQuery<Object[]> query = em.createQuery(
            "SELECT u.originalName, u.views FROM Universities u", Object[].class);
        List<Object[]> resultList = query.getResultList();

        // Εισαγωγή των δεδομένων στον χάρτη
        for (Object[] result : resultList) {
            String universityName = (String) result[0];
            Integer views = (Integer) result[1];
            statistics.put(universityName, views);
        }

        return statistics;
    }
    
    
    // Μέθοδος για να βρεις μια University με βάση το ID
    public Universities findById(Integer id) {
        return em.find(Universities.class, id);
    }

    // Μέθοδος για να βρεις όλες τις Universities
    public List<Universities> findAll() {
        TypedQuery<Universities> query = em.createNamedQuery("Universities.findAll", Universities.class);
        return query.getResultList();
    }

    // Μέθοδος για να βρεις University με βάση το όνομα
    public List<Universities> findByName(String name) {
        TypedQuery<Universities> query = em.createNamedQuery("Universities.findByName", Universities.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    // Μέθοδος για να κλείσεις το EntityManager και το EntityManagerFactory
    public void close() {
        em.close();
        emf.close();
    }
}