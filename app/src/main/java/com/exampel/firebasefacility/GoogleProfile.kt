package com.exampel.firebasefacility

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth


class GoogleProfile : Fragment() {

    lateinit var ivImage: ImageView
    lateinit var tvName: TextView
    lateinit var btLogout: Button
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_google_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Assign variable
        ivImage = view.findViewById(R.id.iv_image)
        tvName = view.findViewById(R.id.tv_name)
        btLogout = view.findViewById(R.id.bt_logout)

        // Initialize firebase auth
        firebaseAuth = FirebaseAuth.getInstance()

        // Initialize firebase user
        val firebaseUser = firebaseAuth.currentUser

        if (firebaseUser != null) {
            // When firebase user is not equal to null set image on image view
            Glide.with(requireActivity()).load(firebaseUser.photoUrl).into(ivImage)
            // set name on text view
            tvName.text = firebaseUser.displayName
        }


        googleSignInClient = GoogleSignIn.getClient(requireActivity(), GoogleSignInOptions.DEFAULT_SIGN_IN)


        btLogout.setOnClickListener {
            // Sign out from google
            googleSignInClient.signOut().addOnCompleteListener { task ->
                // Check condition
                if (task.isSuccessful) {
                    // When task is successful sign out from firebase
                    firebaseAuth.signOut()
                    // Display Toast
                    Toast.makeText(requireActivity(), "Logout successful", Toast.LENGTH_SHORT).show()
                    // Finish activity
                    requireActivity().finish()
                }
            }
        }
    }


}