package fr.dutinfoprojet19.meetmydoc.model;

public class Patient {

    // CONSTANTES

    // ATTRIBUTS
    private String m_nom;
    private String m_prenom;
    private String m_email;
    private int m_sexe; // 0 si femme et 1 homme
    private int m_nbRDVAnnules;



    // Constructeur

    public Patient(){}

    public Patient(String nom, String prenom, String email, int sexe) {
        m_nom = nom;
        m_prenom = prenom;
        m_email = email;
        m_sexe = sexe;

        // initialise le nombre de rendez-vous annulle a 0
        m_nbRDVAnnules=0;
    }


    // getters

    public String getNom() {
        return m_nom;
    }

    public String getPrenom() {
        return m_prenom;
    }

    public String getEmail() {
        return m_email;
    }

    public int getSexe() {
        return m_sexe;
    }

    public int getNbRDVAnnules() {
        return m_nbRDVAnnules;
    }


    //setters


    public void setNom(String nom) {
        m_nom = nom;
    }

    public void setPrenom(String prenom) {
        m_prenom = prenom;
    }

    public void setEmail(String email) {
        m_email = email;
    }

    public void setSexe(int sexe) {
        m_sexe = sexe;
    }

    public void setNbRDVAnnules(int nbRDVAnnules) {
        m_nbRDVAnnules = nbRDVAnnules;
    }
}



