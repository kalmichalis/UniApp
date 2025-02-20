package ergasia3;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.CategoryDataset;
import org.jfree.chart.axis.NumberAxis;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;

import org.jfree.chart.JFreeChart;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/* η παρακατω κλαση κανει το γραφημα με τα στατιστικα στοιχεια συμφωνα με το originalname του καθε πανεπιστημιου και με βαση τα views του καθενος
ειτε απο πανεπιστημιο απο api ειτε χειροκινητα αν εχω εισαγει καποιο
το προβλημα που υπαρχει στην απεικονηση ειναι αν εχω πολλα πανεπηστημια ο οριζοντιος αξονας γεμιζει προσπαθησα με ζοομ αλλα δεν γινεται κατι βελτιωνοντας την απεικονιση*/


public class UniversityStatistics {
    private final UniversitiesDAO dao;

    // Κατασκευαστής που δέχεται το DAO
    public UniversityStatistics(UniversitiesDAO dao) {
        this.dao = dao;
    }

    //Δημιουργεί το dataset από τα δεδομένα της βάσης.

    public CategoryDataset createDataset() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        // Ανακτήστε τα δεδομένα από τη βάση δεδομένων μέσω του DAO
        Map<String, Integer> statistics = dao.getStatistics();
        // Προσθέστε τα δεδομένα στο dataset
        for (Map.Entry<String, Integer> entry : statistics.entrySet()) {
            dataset.addValue(entry.getValue(), "Πανεπιστήμια-Προβολές", entry.getKey());
        }
        return dataset;
    }

    //Δημιουργεί το γράφημα.
     
    public JFreeChart createChart(final CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createLineChart(
                "Στατιστικά Δημοφιλέστερων Πανεπιστημίων",  // Τίτλος γραφήματος
                "Πανεπιστήμιο",                             // Ετικέτα άξονα X
                "Αριθμός Προβολών",                        // Ετικέτα άξονα Y
                dataset,                                    // Δεδομένα
                PlotOrientation.VERTICAL,                  // Κατακόρυφη γραφή
                true,                                       // Εμφάνιση legend
                true,                                       // Εμφάνιση tooltips
                false                                       // Χωρίς URLs
        );
        // Ρύθμιση του κατακόρυφου άξονα για ακέραιους αριθμούς
        chart.getCategoryPlot().getRangeAxis().setStandardTickUnits(
                NumberAxis.createIntegerTickUnits());  // Χρησιμοποίησε την σωστή κλάση
        // Ρύθμιση των ετικετών του άξονα X για να είναι κάθετες
        chart.getCategoryPlot().getDomainAxis().setCategoryLabelPositions(
                org.jfree.chart.axis.CategoryLabelPositions.UP_90);
        return chart;
    }


    public static void showStatistics(UniversitiesDAO dao) {
        UniversityStatistics stats = new UniversityStatistics(dao);
        // Δημιουργία του dataset και του γραφήματος
        final CategoryDataset dataset = stats.createDataset();
        final JFreeChart chart = stats.createChart(dataset);
        // Δημιουργία του panel για το γράφημα
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);  // Ενεργοποίηση zoom με το ποντίκι
        chartPanel.setRangeZoomable(true);      // Ενεργοποίηση του zoom στον άξονα X και Y
        chartPanel.setDomainZoomable(true);     // Ρύθμιση για τη δυνατότητα μετακίνησης
        chartPanel.setPreferredSize(new Dimension(1000, 800));
        // Δημιουργία του κουμπιού εκτύπωσης σε PDF
        JButton printButton = new JButton("Εκτύπωση σε PDF");
        printButton.addActionListener(e -> printChartToPDF(chart));  // Κλήση της μεθόδου εκτύπωσης
        // Δημιουργία ενός JPanel για να προσθέσουμε το κουμπί
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(chartPanel, BorderLayout.CENTER);
        panel.add(printButton, BorderLayout.SOUTH);
        // Εμφάνιση του γραφήματος με το κουμπί εκτύπωσης
        JOptionPane.showMessageDialog(null, panel, "Στατιστικά Πανεπιστημίων", JOptionPane.INFORMATION_MESSAGE);
    }
    
    //για την εκτυπωση σε pdf
    public static void printChartToPDF(JFreeChart chart) {
        // Δημιουργία ενός προσωρινού αρχείου για την εικόνα του γραφήματος
        File chartImageFile = null;
        try {
            // Δημιουργία ενός προσωρινού αρχείου για την εικόνα
            chartImageFile = File.createTempFile("chart", ".png");

            // Αποθήκευση του γραφήματος ως εικόνα PNG
            BufferedImage bufferedImage = chart.createBufferedImage(800, 600);
            ImageIO.write(bufferedImage, "PNG", chartImageFile);

            // Δημιουργία του PDF με οριζόντια διάταξη για να χωραει ο πινακς
            Document document = new Document(com.lowagie.text.PageSize.A4.rotate());  // Οριζόντια διάταξη
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("chart.pdf"));
            document.open();

            // Προσθήκη της εικόνας στο PDF
            Image pdfImage = Image.getInstance(chartImageFile.toURI().toString());

            // Ρύθμιση των διαστάσεων της εικόνας
            float scale = 0.9f;  // Κλίμακα 90% για καλυτερη απεικονσηη στο pdf
            pdfImage.scalePercent(scale * 100);  

            // Προσθήκη της εικόνας στο PDF
            document.add(pdfImage);

            // Κλείσιμο του εγγράφου
            document.close();

            // Εμφάνιση μηνύματος επιτυχίας
            JOptionPane.showMessageDialog(null, "Το γράφημα εκτυπώθηκε σε PDF!");
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Σφάλμα κατά την εκτύπωση σε PDF.");
        } finally {
            // Διαγραφή του προσωρινού αρχείου εικόνας
            if (chartImageFile != null && chartImageFile.exists()) {
                chartImageFile.delete();
            }
        }
    }
    

}