package fr.dutinfoprojet19.meetmydoc.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
                m_boutonMedecin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent inscriptionMedecinIntent = new Intent(ChoixUtilisateurInscriptionActivity.this, InscriptionMedecinActivity.class);
                        startActivity(inscriptionMedecinIntent);
                    }
                });

            //Bouton patient
                m_boutonPatient.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent inscriptionPatientIntent = new Intent(ChoixUtilisateurInscriptionActivity.this, InscriptionPatientActivity.class);
                        startActivity(inscriptionPatientIntent);
                    }
                });
    }
}
