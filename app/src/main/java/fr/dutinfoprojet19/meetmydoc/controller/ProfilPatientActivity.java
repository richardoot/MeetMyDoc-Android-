package fr.dutinfoprojet19.meetmydoc.controller;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import fr.dutinfoprojet19.meetmydoc.R;
import fr.dutinfoprojet19.meetmydoc.model.Patient;

public class ProfilPatientActivity extends AppCompatActivity {

    // Constante
    private static final String TAG ="ProfilPatient" ;

    // Objet d'authentification
    private FirebaseAuth mAuth;
    // Objet pour géerer la BD ( firestore)
    FirebaseFirestore db;

    // utilisateur connecter

    FirebaseUser m_patientConnecte;

    // le patient

    Patient m_patient;


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

        // initialisation du Auth
        mAuth = FirebaseAuth.getInstance();

        // obtenir le patient qui est connecter

        m_patientConnecte=mAuth.getCurrentUser();

        // obtenir son mail pour pouvoir recuperer les infos concernant le patient

        String mailPatient= m_patientConnecte.getEmail();


        //faire requette en BD pour recuperer les infos du patient

            // Recuperer la collection du patient

             CollectionReference patientRef = db.collection("Patient");

            //Query queryMail= patientRef.whereEqualTo("email", "mailPatient");


        db.collection("Patient")
                .whereEqualTo("email", mailPatient)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if (task.isSuccessful())
                        {
                            for (QueryDocumentSnapshot document : task.getResult())
                            {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                // traiter demande
                                //m_patient=
                            }
                        }
                        else
                        {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }
}
