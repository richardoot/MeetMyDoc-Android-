package fr.dutinfoprojet19.meetmydoc.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import fr.dutinfoprojet19.meetmydoc.R;

public class ProfilPatientActivity extends AppCompatActivity {

    // Objet d'authentification
    private FirebaseAuth mAuth;
    // Objet pour géerer la BD ( firestore)
    FirebaseFirestore db;

    // Déclaration des éléments graphiques
    private TextView m_txtPrenomNom;
    private TextView m_txtdateNaissance;
    private TextView m_txtEmail;
    private TextView m_txtTelephone;
    private TextView m_txtAdresse;
    private TextView m_txtComplement;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_patient);

        // Référencement graphique
        m_txtPrenomNom = (TextView) findViewById(R.id.activity_profil_medecin_prenom_nom);
        m_txtdateNaissance = (TextView) findViewById(R.id.activity_profil_medecin_date_naissance);
        m_txtEmail = (TextView) findViewById(R.id.activity_profil_patient_email);
        m_txtTelephone = (TextView) findViewById(R.id.activity_profil_patient_telephone);
        m_txtAdresse = (TextView) findViewById(R.id.activity_profil_patient_adresse);
        m_txtComplement = (TextView) findViewById(R.id.activity_profil_patient_complement);


        // obtenir le patient qui est connecter
        // getUser()


        //faire requette en BD pour recuperer les infos du patient

        // afficher les infos
    }
}
