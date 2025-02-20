/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ergasia3;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import javax.persistence.Transient;


/**
 *
 * @author dimitriospanos
 */
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
@Table(name = "UNIVERSITIES")
@NamedQueries({
    @NamedQuery(name = "Universities.findAll", query = "SELECT u FROM Universities u"),
    @NamedQuery(name = "Universities.findById", query = "SELECT u FROM Universities u WHERE u.id = :id"),
    @NamedQuery(name = "Universities.findByOriginalName", query = "SELECT u FROM Universities u WHERE u.originalName = :originalName"),
    @NamedQuery(name = "Universities.findByName", query = "SELECT u FROM Universities u WHERE u.name = :name"),
    @NamedQuery(name = "Universities.findByDomainsString", query = "SELECT u FROM Universities u WHERE u.domainsString = :domainsString"),
    @NamedQuery(name = "Universities.findByCountry", query = "SELECT u FROM Universities u WHERE u.country = :country"),
    @NamedQuery(name = "Universities.findByStateProvince", query = "SELECT u FROM Universities u WHERE u.stateProvince = :stateProvince"),
    @NamedQuery(name = "Universities.findByAlphaTwoCode", query = "SELECT u FROM Universities u WHERE u.alphaTwoCode = :alphaTwoCode"),
    @NamedQuery(name = "Universities.findByWebPagesString", query = "SELECT u FROM Universities u WHERE u.webPagesString = :webPagesString"),
    @NamedQuery(name = "Universities.findByComments", query = "SELECT u FROM Universities u WHERE u.comments = :comments"),
    @NamedQuery(name = "Universities.findByContactInfo", query = "SELECT u FROM Universities u WHERE u.contactInfo = :contactInfo"),
    @NamedQuery(name = "Universities.findByViews", query = "SELECT u FROM Universities u WHERE u.views = :views")})
public class Universities implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ORIGINAL_NAME")
    private String originalName;

    @Column(name = "NAME")
    private String name;

    @SerializedName("domains") // Αντιστοίχιση με το πεδίο "domains" του JSON
    @Transient // Αυτό το πεδίο δεν θα αποθηκευτεί στη βάση δεδομένων
    private String[] domains; // Αποθηκεύεται ως πίνακας strings

    @Column(name = "DOMAINS")
    private String domainsString; // Αποθηκεύεται ως String (όλα τα domains χωρισμένα με κόμμα)

    @Column(name = "COUNTRY")
    private String country;

    @SerializedName("state-province") // Αντιστοίχιση με το πεδίο "state-province" του JSON
    @Column(name = "STATE_PROVINCE")
    private String stateProvince;

    @SerializedName("alpha_two_code") // Αντιστοίχιση με το πεδίο "alpha_two_code" του JSON
    @Column(name = "ALPHA_TWO_CODE")
    private String alphaTwoCode;

    @SerializedName("web_pages") // Αντιστοίχιση με το πεδίο "web_pages" του JSON
    @Transient // Αυτό το πεδίο δεν θα αποθηκευτεί στη βάση δεδομένων
    private String[] webPages; // Αποθηκεύεται ως πίνακας strings

    @Column(name = "WEB_PAGES")
    private String webPagesString; // Αποθηκεύεται ως String (όλες οι ιστοσελίδες χωρισμένες με κόμμα)

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "CONTACT_INFO")
    private String contactInfo;

    @Column(name = "VIEWS")
    private Integer views;

    // Κατασκευαστές, getters και setters

    // Κατασκευαστές
    public Universities() {
        this.views = 0; // Αρχικοποίηση του views με 0
    }

    public Universities(String originalName) {
        this.originalName = originalName;
        this.views = 0; // Αρχικοποίηση του views με 0
    }

    // Getters και Setters για όλα τα πεδία

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getDomains() {
        return domains;
    }

    public void setDomains(String[] domains) {
        this.domains = domains;
        // Αποθήκευση ως String με κόμμα για τη βάση δεδομένων
        this.domainsString = String.join(",", domains);
    }

    public String getDomainsString() {
        return domainsString;
    }

    public void setDomainsString(String domainsString) {
        this.domainsString = domainsString;
        // Μετατροπή σε πίνακα για χρήση στο πρόγραμμα
        this.domains = domainsString.split(",");
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getAlphaTwoCode() {
        return alphaTwoCode;
    }

    public void setAlphaTwoCode(String alphaTwoCode) {
        this.alphaTwoCode = alphaTwoCode;
    }

    public String[] getWebPages() {
        return webPages;
    }

    public void setWebPages(String[] webPages) {
        this.webPages = webPages;
        // Αποθήκευση ως String με κόμμα για τη βάση δεδομένων
        this.webPagesString = String.join(",", webPages);
    }

    public String getWebPagesString() {
        return webPagesString;
    }

    public void setWebPagesString(String webPagesString) {
        this.webPagesString = webPagesString;
        // Μετατροπή σε πίνακα για χρήση στο πρόγραμμα
        this.webPages = webPagesString.split(",");
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Universities)) {
            return false;
        }
        Universities other = (Universities) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ergasia3.Universities[ id=" + id + ", name=" + name + ", country=" + country + " ]";
    }
}