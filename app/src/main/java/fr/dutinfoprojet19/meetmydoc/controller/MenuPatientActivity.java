package fr.dutinfoprojet19.meetmydoc.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import fr.dutinfoprojet19.meetmydoc.R;


public class MenuPatientActivity extends AppCompatActivity
        implements
        ProfilPatientFragment.OnFragmentInteractionListener,
        NavigationView.OnNavigationItemSelectedListener {

    //Déclaration
        private FirebaseAuth m_Auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_patient);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_patient, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
            int id = item.getItemId();
            Fragment fragment = null;

        //Déterminer le fragment qui est appelé
            if (id == R.id.activity_menu_patient_drawer_prd_rdv) {
                // Permet de prendre un RDV
            } else if (id == R.id.activity_menu_patient_drawer_voir_rdv) {

            } else if (id == R.id.activity_menu_patient_drawer_voir_profil) {
                fragment = new ProfilPatientFragment();
            } else if (id == R.id.activity_menu_patient_drawer_medecin_favoris) {

            } else if (id == R.id.activity_menu_patient_drawer_voir_dossier) {

            } else if (id == R.id.activity_menu_patient_drawer_partager_dossier) {

            } else if (id == R.id.activity_menu_patient_drawer_aide) {

            } else if (id == R.id.activity_menu_patient_drawer_parametre) {

            } else if (id == R.id.activity_menu_patient_drawer_deconnexion) {

                if(m_Auth.getCurrentUser() != null){
                    //Voir sur la console s'il y a quelqu'un de connecté
                    Log.d("Avant Deconnexion", "Il est connecté");

                    //Déconnecter l'utilisateur courrant
                    m_Auth.signOut();

                    if(m_Auth.getCurrentUser() == null){
                        //Voir sur la console si la personne c'est déconnecté
                        Log.d("Après Deconnexion", "Il est plus connecté");
                    }

                    //Rédiriger l'utilisateur vers la première activitée
                        Intent choixConnexionIntent = new Intent(MenuPatientActivity.this, ChoixConnexionActivity.class);
                        startActivity(choixConnexionIntent);


                }
            }


        //
            if(fragment != null){
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.mainFrame_patient, fragment);
                ft.commit();
            }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteractionChangeTitle(String titre) {
        getSupportActionBar().setTitle(titre);
    }
}
