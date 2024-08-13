package com.exampel.firebasefacility

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.Firebase
import com.google.firebase.database.database


class RealTimeDatabase : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val database = Firebase.database
        val myRef = database.getReference("mydata")

        myRef.setValue("Hello")
        myRef.setValue("How are you?")

        return inflater.inflate(R.layout.fragment_real_time_database, container, false)
    }

}