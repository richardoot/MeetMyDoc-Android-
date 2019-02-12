package fr.dutinfoprojet19.meetmydoc.model;

public class Medecin {

    // CONSTANTES

    // ATTRIBUTS
    private String m_nom;
    private String m_prenom;
    private String m_email;
    private String m_adresse;
    private String m_tel;
    private int m_nbRDVNonHonore;



    // Constructeur


    public Medecin(String nom, String prenom, String email, String adresse, String tel) {
        m_nom = nom;
        m_prenom = prenom;
        m_email = email;
        m_adresse = adresse;
        m_tel = tel;

        // initialise le nombre de rendez-vous non honore a 0
        m_nbRDVNonHonore=0;
    }


    // GETTERS

    public String getNom() {
        return m_nom;
    }

    public String getPrenom() {
        return m_prenom;
    }

    public String getEmail() {
        return m_email;
    }

    public String getAdresse() {
        return m_adresse;
    }

    public String getTel() {
        return m_tel;
    }

    public int getNbRDVNonHonore() {
        return m_nbRDVNonHonore;
    }


    //SETTERS


    public void setNom(String nom) {
        m_nom = nom;
    }

    public void setPrenom(String prenom) {
        m_prenom = prenom;
    }

    public void setEmail(String email) {
        m_email = email;
    }

    public void setAdresse(String adresse) {
        m_adresse = adresse;
    }

    public void setTel(String tel) {
        m_tel = tel;
    }

    public void setNbRDVNonHonore(int nbRDVNonHonore) {
        m_nbRDVNonHonore = nbRDVNonHonore;
    }
}
