package fr.dutinfoprojet19.meetmydoc.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import fr.dutinfoprojet19.meetmydoc.R;
import fr.dutinfoprojet19.meetmydoc.model.Patient;

public class InscriptionPatientActivity extends AppCompatActivity {


    // Constante
        private static final String TAG ="InscriptionPatient" ;

    // Objet d'authentification
        private FirebaseAuth m_Auth;

    // Objet Base de données
        //private FirebaseFirestore m_db = FirebaseFirestore.getInstance();

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
        private RadioButton m_btnRadioFemme;
        private RadioButton m_btnRadioHomme;
        private Button m_btnInscription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_patient);
// initialisation de l'instance FirebaseAuth
        m_Auth = FirebaseAuth.getInstance();

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
        m_btnRadioFemme = (RadioButton) findViewById(R.id.activity_inscription_patient_btn_radio_femme);
        m_btnRadioHomme = (RadioButton) findViewById(R.id.activity_inscription_patient_btn_radio_homme);
        m_btnInscription = (Button) findViewById(R.id.activity_inscription_patient_btn_terminer);



        //Lorsqu'on click sur terminer inscription
        m_btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer email et mdp
                String saisieEmail = m_inputEmail.getText().toString();
                String saisieEmailConfirmer = m_inputEmailConfirmer.getText().toString();
                String saisiePassword = m_inputMotDePasse.getText().toString();
                String saisiePasswordConfirmer = m_inputMotDePasseConfirmer.getText().toString();

                //Récupérer les autres informations
                String saisieNom = m_inputNom.getText().toString();
                String saisiePrenom = m_inputPrenom.getText().toString();

                // Effectuer l'inscription
                //Vérifier la cohérence de tous les éléments
                if(!saisieNom.matches("") &&  !saisiePrenom.matches("") && (m_btnRadioFemme.isChecked() || m_btnRadioHomme.isChecked())
                        && (!saisieEmail.matches("") || !saisieEmailConfirmer.matches("")) && (!saisiePassword.matches("") || !saisiePassword.matches(""))) {

                    if (saisieEmail.equals(saisieEmailConfirmer)) {
                        if (saisiePassword.equals(saisiePasswordConfirmer)) {
                            //Ajout des éléments en base de données


                            //Réaliser l'inscription du patient
                                sInscrire(saisieEmail, saisiePassword);
                        } else {
                            Toast.makeText(InscriptionPatientActivity.this, "Les 2 mots de passe ne sont pas identiques", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(InscriptionPatientActivity.this, "Les 2 emails ne sont pas identiques", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(InscriptionPatientActivity.this, "Il y a des informations manquantes", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = m_Auth.getCurrentUser();
        //updateUI(currentUser);
    }

    public void sInscrire(String email, String password) {

        m_Auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = m_Auth.getCurrentUser();

                            //Rediriger vers la page de connexion
                            Intent connexionIntent = new Intent(InscriptionPatientActivity.this, ConnexionActivity.class);
                            startActivity(connexionIntent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(InscriptionPatientActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                    }
        });

    }

    /**
     *  Sert à créer le patient avec le service de fireStore( en base de donnée)
     *  et au services d'authentification lors de son incription
     * @param nom - le nom du patient
     * @param prenom -le prenom du patient
     * @param email - l'email du patient
     * @param sexe - égale 0 si c'est un femme et 1 si c'est un homme
     */
    public void creerPatient(String nom, String prenom, String email, Integer sexe)
    {
        Patient m_patient=new Patient(nom, prenom, email, sexe);

        // enreigister le patient en BD



    }

    /**
     * Permet de vérifier les donnees saisie par le parient (mail et mot de passe)
     * @param email
     * @param emailConfirmer
     * @param motDepasse
     * @param motDePasseConfirmer
     * @return - true si les données sont correct et false si inverse
     */
    public boolean verificationDonnee(String email, String emailConfirmer, String motDepasse, String motDePasseConfirmer)
    {

        return ( (email.equals(emailConfirmer)) && (motDepasse.equals(motDePasseConfirmer)));

    }




    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DialogFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


}
