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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import fr.dutinfoprojet19.meetmydoc.R;
import fr.dutinfoprojet19.meetmydoc.model.Patient;

public class InscriptionPatientActivity extends AppCompatActivity {

    // Constante
        private static final String TAG ="InscriptionPatient" ;

    // Objet d'authentification
        private FirebaseAuth mAuth;
    // Objet pour géerer la BD ( firestore)
    FirebaseFirestore db;

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

        // pour richard 2

    /**
     * Booléen  qui indique si la patient a reussi a s'inscrire
     */
    private Boolean reussiteInscription;

    // pour richard


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_patient);

        // initialisation de l'instance FirebaseAuth
            mAuth = FirebaseAuth.getInstance();

        // initialialisation de l'instance FirebaseFirestore

           db = FirebaseFirestore.getInstance();

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


            // action a effectué lorsque l'on clique su le bouton
            m_btnInscription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // recupêrer les données saisies

                    final String nom=m_inputNom.getText().toString();
                    final String prenom=m_inputPrenom.getText().toString();
                    final String email=m_inputEmail.getText().toString();
                    final String emailConfirmer=m_inputEmailConfirmer.getText().toString();
                    final String motDePasse=m_inputMotDePasse.getText().toString();
                    final String motDePasseConfirmer=m_inputMotDePasseConfirmer.getText().toString();
                    int sexe;

                    if(m_btnRadioFemme.isChecked())
                    {
                        // c'est une femme
                        sexe = 0;
                    }
                    else
                    {
                        // c'est un homme
                        sexe=1;
                    }


                    if(!verificationEmail(email, emailConfirmer))
                    {
                        Toast.makeText(InscriptionPatientActivity.this, "Les mails ne sont pas identiques ", Toast.LENGTH_SHORT).show();
                    }

                    if(!verificationEmail(email, emailConfirmer))
                    {
                        Toast.makeText(InscriptionPatientActivity.this, "Les mots de passe ne sont pas identique ", Toast.LENGTH_SHORT).show();
                    }

                    // verifier les donnees saisie par le patient pour creer le compte du patient

                    if (verificationEmail(email, emailConfirmer) &&verificationMotDePasse(motDePasse, motDePasseConfirmer))
                    {
                        // les donnnes sont valides (càd les deux mails sont pareil et les deux motDePassed sont pareil

                        senreigistrerPatient(email, motDePasse);

                        if(reussiteInscription)
                        {
                            // L'inscription s'est bien derouler donc on crée le patient en base de donnée
                            creerPatient(nom, prenom, email, sexe);

                            // on redirige le patient vers la page d'acceuil | vers son profil pour la premiere version

                            Intent profilPatientIntent = new Intent(InscriptionPatientActivity.this, ProfilPatientActivity.class);
                            startActivity(profilPatientIntent);
                        }

                    }


                }
            });

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
                            // updateUI(user);

                            // mettre a jour le statuts de l'insription
                            reussiteInscription=true;
                        }
                        else
                        {

                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(InscriptionPatientActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);

                            // mettre a jour le statuts de l'insription
                            reussiteInscription=false;

                        }

                        // ...
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
    public void creerPatient(String nom, String prenom, String email, int sexe)
    {
        Patient m_patient=new Patient(nom, prenom, email, sexe);

        // enreigister le patient en BD

        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        DocumentReference newPatientRef = db.collection("Patient").document();

        newPatientRef.set(m_patient);

        /*
        db.collection("Patient")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

                */
    }

    /**
     * Permet de vérifier les donnees saisie par le parient (mail et mot de passe)
     * @param email
     * @param emailConfirmer
     * @return - true si les données sont correct et false si inverse
     */
    public boolean verificationEmail(String email, String emailConfirmer)
    {
        return  (email.equals(emailConfirmer)) ;
    }

    /**
     *
     * @param motDepasse
     * @param motDePasseConfirmer
     * @return
     */
    public boolean verificationMotDePasse(String motDepasse, String motDePasseConfirmer)
    {
        return ((motDepasse.equals(motDePasseConfirmer)) );
    }






    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DialogFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


}
