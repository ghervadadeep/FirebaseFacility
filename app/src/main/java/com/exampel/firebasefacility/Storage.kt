package com.exampel.firebasefacility

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage

class Storage : Fragment() {

    private lateinit var storage: FirebaseStorage

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        storage = Firebase.storage

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_storage, container, false)
    }


}