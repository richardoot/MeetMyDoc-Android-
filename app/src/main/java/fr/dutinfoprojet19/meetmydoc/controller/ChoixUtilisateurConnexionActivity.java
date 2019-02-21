package fr.dutinfoprojet19.meetmydoc.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fr.dutinfoprojet19.meetmydoc.R;




public class ChoixUtilisateurConnexionActivity extends AppCompatActivity {

    // CONSTANTES
    public static final String  TYPE="typeUtilisateur";
    public static final int  CHOIX_MEDECIN=1;
    public static final int  CHOIX_PATIENT=0;

    // Déclaration des éléménts graphiques
        private TextView m_textChoixUtilisateurConnexion;
        private Button m_boutonMedecin;
        private Button m_boutonPatient;
        private Button m_boutonAdministrateur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_utilisateur_connexion);

        // Référencement des éléments graphiques
            m_textChoixUtilisateurConnexion = (TextView) findViewById(R.id.activity_choix_utilisateur_choix_txt);
            m_boutonMedecin = (Button) findViewById(R.id.activity_choix_utilisateur_connexion_medecin_btn);
            m_boutonPatient = (Button) findViewById(R.id.activity_choix_utilisateur_connexion_patient_btn);
            m_boutonAdministrateur = (Button) findViewById(R.id.activity_choix_utilisateur_connexion_administrateur_btn);

        //Définir textes
            //texte à afficher
                m_textChoixUtilisateurConnexion.setText("Quel type d'utilisateur êtes-vous?");

            //textes des boutons
                m_boutonMedecin.setText("Médecin");
                m_boutonPatient.setText("Patient");
                m_boutonAdministrateur.setText("Administrateur");

        //Désabilité bouton administrateur (car non disponible)
            m_boutonAdministrateur.setEnabled(false);

        //Définir action du click pour
            //Bouton médecin
                m_boutonMedecin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent choixUtilisateurMedecinIntent = new Intent(ChoixUtilisateurConnexionActivity.this, ConnexionActivity.class);
                        // envoyer un truc pour dire que c'est un médecin (putetra)
                        choixUtilisateurMedecinIntent.putExtra(TYPE, CHOIX_MEDECIN);
                        startActivity(choixUtilisateurMedecinIntent);
                    }
                });

            //Bouton patient
                m_boutonPatient.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent choixUtilisateurPatientIntent = new Intent(ChoixUtilisateurConnexionActivity.this, ConnexionActivity.class);
                        // envoyer un truc pour dire que c'est un médecin (putetra)
                        choixUtilisateurPatientIntent.putExtra(TYPE, CHOIX_PATIENT);
                        startActivity(choixUtilisateurPatientIntent);
                    }
                });
    }
}
