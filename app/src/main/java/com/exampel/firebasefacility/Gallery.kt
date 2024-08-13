package com.exampel.firebasefacility

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import java.io.ByteArrayOutputStream
import kotlin.random.Random


class Gallery : Fragment() {

    private lateinit var storage: FirebaseStorage
    private var SELECT_PICTURE: Int = 0
    lateinit var BSelectImage: Button
    lateinit var setonfirebase: Button
    lateinit var IVPreviewImage: ImageView

    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        BSelectImage = view.findViewById(R.id.BSelectImage)
        setonfirebase = view.findViewById(R.id.setonfirebase)
        IVPreviewImage = view.findViewById(R.id.IVPreviewImage)

        storage = Firebase.storage

        BSelectImage.setOnClickListener {
            imageChooser()
        }

        setonfirebase.setOnClickListener {
            var storageRef = storage.reference

// Create a reference to "mountains.jpg"
            var mountainsRef = storageRef.child("cloudstoragedata/Image${Random.nextInt(1000)}.jpg")

            IVPreviewImage.setDrawingCacheEnabled(true)
            IVPreviewImage.buildDrawingCache()
            val bitmap = (IVPreviewImage.getDrawable() as BitmapDrawable).bitmap
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()

            val uploadTask = mountainsRef.putBytes(data)
            uploadTask.addOnFailureListener {
                // Handle unsuccessful uploads
            }.addOnSuccessListener {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                Log.e("success", "storage: ")
            }
        }
    }

    fun imageChooser() {

        // create an instance of the
        // intent of the type image
        val i = Intent()
        i.type = "image/*"
        i.action = Intent.ACTION_GET_CONTENT

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == AppCompatActivity.RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                val selectedImageUri = data!!.data
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    IVPreviewImage.setImageURI(selectedImageUri)
                }
            }
        }
    }


}