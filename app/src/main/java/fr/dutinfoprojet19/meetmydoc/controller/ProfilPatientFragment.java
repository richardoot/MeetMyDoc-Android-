package fr.dutinfoprojet19.meetmydoc.controller;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import fr.dutinfoprojet19.meetmydoc.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfilPatientFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfilPatientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilPatientFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //Déclarations
        TextView m_email;
        TextView m_nomPrenom;
        TextView m_sexe; //Pas utilisé
        TextView m_dateNaiss;
        TextView m_telephone;
        TextView m_codePostal;
        TextView m_ville;
        TextView m_adresse;

    // initialisation de l'instance FirebaseAuth
        FirebaseAuth m_Auth = FirebaseAuth.getInstance();

    // Objet Base de données
        private FirebaseFirestore db = FirebaseFirestore.getInstance();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProfilPatientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfilPatientFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfilPatientFragment newInstance(String param1, String param2) {
        ProfilPatientFragment fragment = new ProfilPatientFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState/*, View view*/) {
        /*//Référencement Graphique
            m_nomPrenom = (TextView) getView().findViewById(R.id.fragment_profil_patient_prenom_nom);
            m_adresse = (TextView) getView().findViewById(R.id.fragment_profil_patient_adresse);
            m_codePostal = (TextView) getView().findViewById(R.id.fragment_profil_patient_code_postal);
            m_ville = (TextView) getView().findViewById(R.id.fragment_profil_patient_ville);
            m_email = (TextView) getView().findViewById(R.id.fragment_profil_patient_email);
            m_dateNaiss = (TextView) getView().findViewById(R.id.fragment_profil_medecin_date_naissance);
            m_telephone = (TextView) getView().findViewById(R.id.fragment_profil_patient_telephone);

        //Récupération des données en BD
        db.collection("Patient")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Récupération données", document.getId() + " => " + document.getData());
                                //document.get()
                            }
                        } else {
                            Log.d("Récupération données", "Error getting documents: ", task.getException());
                        }
                    }
                });*/

        //Demande de changement du titre
            if (mListener != null) {
                mListener.onFragmentInteractionChangeTitle("Profil");
            }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil_patient, container, false);
    }

/*    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteractionChangeTitle(String titre);
    }
}
