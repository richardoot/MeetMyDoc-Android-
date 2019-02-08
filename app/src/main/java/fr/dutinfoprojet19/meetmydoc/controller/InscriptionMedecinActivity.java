package fr.dutinfoprojet19.meetmydoc.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fr.dutinfoprojet19.meetmydoc.R;

public class InscriptionMedecinActivity extends AppCompatActivity {

    // Déclaration des éléments graphiques
        private TextView m_txtNom;
        private TextView m_txtPrenom;
        private TextView m_txtEmail;
        private TextView m_txtEmailConfirmer;
        private TextView m_txtMotDePasse;
        private TextView m_txtMotDePasseConfirmer;
        private TextView m_txtEnonce;
        private TextView m_txtSexe;
        private EditText m_inputNom;
        private EditText m_inputPrenom;
        private EditText m_inputEmail;
        private EditText m_inputEmailConfirmer;
        private EditText m_inputMotDePasse;
        private EditText m_inputMotDePasseConfirmer;
        private Button m_btnRadioFemme;
        private Button m_btnRadioHomme;
        private Button m_btnDate;
        private Button m_btnInscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_medecin);

        // Référencement graphique
            m_txtNom = (TextView) findViewById(R.id.activity_inscription_medecin_txt_nom);
            m_txtPrenom = (TextView) findViewById(R.id.activity_inscription_medecin_txt_prenom);
            m_txtEmail = (TextView) findViewById(R.id.activity_inscription_medecin_txt_email);
            m_txtEmailConfirmer = (TextView) findViewById(R.id.activity_inscription_medecin_txt_confirmer_email);
            m_txtMotDePasse = (TextView) findViewById(R.id.activity_inscription_medecin_txt_mot_de_passe);
            m_txtMotDePasseConfirmer = (TextView) findViewById(R.id.activity_inscription_medecin_txt_confirmer_mot_de_passe);
            m_txtEnonce = (TextView) findViewById(R.id.activity_inscription_medecin_enonce);
            m_txtSexe = (TextView) findViewById(R.id.activity_inscription_medecin_txt_sexe);
            m_inputNom = (EditText) findViewById(R.id.activity_inscription_medecin_input_nom);
            m_inputPrenom = (EditText) findViewById(R.id.activity_inscription_medecin_input_prenom);
            m_inputEmail = (EditText) findViewById(R.id.activity_inscription_medecin_input_email);
            m_inputEmailConfirmer = (EditText) findViewById(R.id.activity_inscription_medecin_input_confirmer_email);
            m_inputMotDePasse = (EditText) findViewById(R.id.activity_inscription_medecin_input_mot_de_passe);
            m_inputMotDePasseConfirmer = (EditText) findViewById(R.id.activity_inscription_medecin_input_confirmer_mot_de_passe);
            m_btnRadioFemme = (Button) findViewById(R.id.activity_inscription_medecin_btn_radio_femme);
            m_btnRadioHomme = (Button) findViewById(R.id.activity_inscription_medecin_btn_radio_homme);
            m_btnDate = (Button) findViewById(R.id.activity_inscription_medecin_date_btn);
            m_btnInscription = (Button) findViewById(R.id.activity_inscription_medecin_btn_terminer);

    }
}
