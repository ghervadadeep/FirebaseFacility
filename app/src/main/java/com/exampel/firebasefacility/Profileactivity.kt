package com.exampel.firebasefacility

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class Profileactivity : AppCompatActivity() {

    lateinit var ivImage1: ImageView
    lateinit var tvName1: TextView
    lateinit var btLogout1: Button
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profileactivity)


        ivImage1 = findViewById(R.id.iv_image1)
        tvName1 = findViewById(R.id.tv_name1)
        btLogout1 = findViewById(R.id.bt_logout1)


        // Initialize firebase auth
        firebaseAuth = FirebaseAuth.getInstance()

        // Initialize firebase user
        val firebaseUser = firebaseAuth.currentUser

        if (firebaseUser != null) {
            // When firebase user is not equal to null set image on image view
            Glide.with(this).load(firebaseUser.photoUrl).into(ivImage1)
            // set name on text view
            tvName1.text = firebaseUser.displayName
        }


        googleSignInClient = GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_SIGN_IN)


        btLogout1.setOnClickListener {
            // Sign out from google
            googleSignInClient.signOut().addOnCompleteListener { task ->
                // Check condition
                if (task.isSuccessful) {
                    // When task is successful sign out from firebase
                    firebaseAuth.signOut()
                    // Display Toast
                    Toast.makeText(this, "Logout successful", Toast.LENGTH_LONG).show()
//                    Toast.makeText((this@Profileactivity, "Logout successful", Toast.LENGTH_SHORT).show()
                    // Finish activity
                    finish()
                }
            }
        }
    }
}