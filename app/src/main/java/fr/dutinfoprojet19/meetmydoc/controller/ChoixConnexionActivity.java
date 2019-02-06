package fr.dutinfoprojet19.meetmydoc.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fr.dutinfoprojet19.meetmydoc.R;

public class ChoixConnexionActivity extends AppCompatActivity {

    //Déclaration des éléments graphiques
        private Button m_boutonConnexion;
        private Button m_boutonInscription;
        private TextView m_textChoixConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_connexion);

        // Référencement des éléments graphiques
            m_boutonConnexion = (Button) findViewById(R.id.activity_choix_connexion_connexion_btn);
            m_boutonInscription = (Button) findViewById(R.id.activity_choix_connexion_inscription_btn);
            m_textChoixConnexion = (TextView) findViewById(R.id.activity_choix_connexion_choix_txt);

        //Définir textes
            //texte à afficher
                m_textChoixConnexion.setText("Que souhaiter vous faire?");

            //textes des boutons
                m_boutonConnexion.setText("se connecter");
                m_boutonInscription.setText("s'inscrire");

        // Définir action du click bouton connexion
            m_boutonConnexion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent choixUtilisateurConnexionIntent = new Intent(ChoixConnexionActivity.this, ChoixUtilisateurConnexionActivity.class);
                    startActivity(choixUtilisateurConnexionIntent);
                }
            });

        // Définir action du click bouton inscription
            m_boutonInscription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent choixUtilisateurInscriptionIntent = new Intent(ChoixConnexionActivity.this, ChoixUtilisateurInscriptionActivity.class);
                    startActivity(choixUtilisateurInscriptionIntent);
                }
            });

    }
}
