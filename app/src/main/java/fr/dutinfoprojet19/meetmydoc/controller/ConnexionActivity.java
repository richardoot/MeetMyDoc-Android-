package fr.dutinfoprojet19.meetmydoc.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
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

public class ConnexionActivity extends AppCompatActivity {

    private static final String TAG ="Connexion" ;
    private FirebaseAuth mAuth;

    // Variable de referencement graphique

    private AutoCompleteTextView m_email;
    private EditText m_password;
    private Button m_buttonConnecter;

    // Autres varibales

    private int typeUtilisateur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        // initialisation de l'objet FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // referencement des objet
            m_email = (AutoCompleteTextView) findViewById(R.id.activity_connexion_email);
            m_password = (EditText) findViewById(R.id.activity_connexion_password);
            m_buttonConnecter = (Button) findViewById(R.id.activity_connexion_sign_in_button);


            //recuperer le type d'utilisateur qui veut se connecter

            Intent intent = getIntent();

            if (intent != null){

                if (intent.hasExtra("typeUtilisateur")){ // vérifie qu'une valeur est associée à la clé “typeUtilisateur”
                    typeUtilisateur = intent.getIntExtra("typeUtilisateur", -1); // on récupère la valeur associée à la clé
                    Log.d(TAG, "typeutilisateur: "+ typeUtilisateur);
                }
            }

        // se connecter

            // clicks sur le bouton (gerer le click du bouton= recuperer les données)
                m_buttonConnecter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //recuperer les parametre
                        String mailSaisie = m_email.getText().toString();
                        String passwordSaisie =m_password.getText().toString();

                        //Vérifier si c'est un patient ou un medecin selon le bouton appuyer avant


                        if(typeUtilisateur==0)
                        {
                            //patient
                            // appeler se connecter patient
                            seConnecterPatient(mailSaisie, passwordSaisie);
                        }
                        else
                        {
                            //medecin

                            // appler se connecter medecin
                            seConnecterMedecin(mailSaisie, passwordSaisie);
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


    public void seConnecterPatient(String email, String password)
    {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);

                            //Rediriger ver le profil patient voici le code :
                            Intent menuPatientIntent = new Intent(ConnexionActivity.this , MenuPatientActivity.class);
                            startActivity(menuPatientIntent);

                            Toast.makeText(ConnexionActivity.this, "Authentication reussi.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(ConnexionActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });

    }

    public void seConnecterMedecin(String email, String password)
    {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);


                            //Rediriger vers le menu medecin
                                Intent menuMedecinIntent = new Intent(ConnexionActivity.this, MenuMedecinActivity.class);
                                startActivity(menuMedecinIntent);


                            Toast.makeText(ConnexionActivity.this, "Authentication reussi.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(ConnexionActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });

    }


  /*  private void updateUI(FirebaseUser user) {

        if (user != null) {

            ((TextView) findViewById(R.id.textSignInStatus)).setText(

                    "User ID: " + user.getUid());

        } else {

            ((TextView) findViewById(R.id.textSignInStatus)).setText(

                    "Error: sign in failed.");

        }

    }*/

}
