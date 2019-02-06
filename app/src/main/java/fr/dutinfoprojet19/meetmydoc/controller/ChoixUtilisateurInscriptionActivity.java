package fr.dutinfoprojet19.meetmydoc.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import fr.dutinfoprojet19.meetmydoc.R;

public class ChoixUtilisateurInscriptionActivity extends AppCompatActivity {

    // Déclaration des éléménts graphiques
        private TextView m_textChoixUtilisateurConnexion;
        private Button m_boutonMedecin;
        private Button m_boutonPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_utilisateur_inscription);

        // Référencement des éléments graphiques
            m_textChoixUtilisateurConnexion = (TextView) findViewById(R.id.activity_choix_utilisateur_inscription_choix_txt);
            m_boutonMedecin = (Button) findViewById(R.id.activity_choix_utilisateur_inscription_medecin_btn);
            m_boutonPatient = (Button) findViewById(R.id.activity_choix_utilisateur_inscription_patient_btn);

        //Définir textes
            //texte à afficher
                m_textChoixUtilisateurConnexion.setText("Quel type d'utilisateur êtes-vous?");

            //textes des boutons
                m_boutonMedecin.setText("Médecin");
                m_boutonPatient.setText("Patient");

        //Définir action du click pour
            //Bouton médecin


            //Bouton patient
    }
}
