package fr.dutinfoprojet19.meetmydoc.controller;

import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import fr.dutinfoprojet19.meetmydoc.R;

public class InscriptionPatientActivity extends AppCompatActivity {

    // Constante
        private static final String TAG ="InscriptionPatient" ;

    // Objet d'authentification
        private FirebaseAuth mAuth;

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
        private Button m_btnInscription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_patient);

        // initialisation de l'instance FirebaseAuth
            mAuth = FirebaseAuth.getInstance();

        // Référencement graphique
            m_txtNom = (TextView) findViewById(R.id.activity_inscription_patient_txt_nom);
            m_txtPrenom = (TextView) findViewById(R.id.activity_inscription_patient_txt_prenom);
            m_txtEmail = (TextView) findViewById(R.id.activity_inscription_patient_txt_email);
            m_txtEmailConfirmer = (TextView) findViewById(R.id.activity_inscription_patient_txt_confirmer_email);
            m_txtMotDePasse = (TextView) findViewById(R.id.activity_inscription_patient_txt_mot_de_passe);
            m_txtMotDePasseConfirmer = (TextView) findViewById(R.id.activity_inscription_patient_txt_confirmer_mot_de_passe);
            m_txtEnonce = (TextView) findViewById(R.id.activity_inscription_patient_enonce);
            m_txtSexe = (TextView) findViewById(R.id.activity_inscription_patient_txt_sexe);
            m_inputNom = (EditText) findViewById(R.id.activity_inscription_patient_input_nom);
            m_inputPrenom = (EditText) findViewById(R.id.activity_inscription_patient_input_prenom);
            m_inputEmail = (EditText) findViewById(R.id.activity_inscription_patient_input_email);
            m_inputEmailConfirmer = (EditText) findViewById(R.id.activity_inscription_patient_input_confirmer_email);
            m_inputMotDePasse = (EditText) findViewById(R.id.activity_inscription_patient_input_mot_de_passe);
            m_inputMotDePasseConfirmer = (EditText) findViewById(R.id.activity_inscription_patient_input_confirmer_mot_de_passe);
            m_btnRadioFemme = (Button) findViewById(R.id.activity_inscription_patient_btn_radio_femme);
            m_btnRadioHomme = (Button) findViewById(R.id.activity_inscription_patient_btn_radio_homme);
            m_btnInscription = (Button) findViewById(R.id.activity_inscription_patient_btn_terminer);



    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    public void senreigistrerPatient(String email, String password) //faute d'orthographe!!
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //  updateUI(user);
                        }
                        else
                        {

                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(InscriptionPatientActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });

    }


    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DialogFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


}
