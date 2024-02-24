package com.example.mediscript

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import com.google.firebase.auth.FirebaseAuth
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

class MainActivity : AppCompatActivity() {

    lateinit var someActivityResultLauncher : ActivityResultLauncher<Intent>
    lateinit var camera : Button
    lateinit var gallery: Button
    lateinit var remind : Button
    lateinit var auth :FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // camera=findViewById(R.id.)
        //gallery=findViewById(R.id.)
        //remind=findViewById(R.id.)
        var currentUser=auth.currentUser
        if(currentUser == null){
            var intent= Intent(this,LoginActivity :: class.java)
            startActivity(intent)
            finish()
        }
        camera.setOnClickListener{
            var intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(intent.resolveActivity(packageManager) == null){
                startActivityForResult(intent,123)

            }else{
                //var msg=it.exception?.message?:"Error Occured"
                Toast.makeText(this, "Error Occured", Toast.LENGTH_SHORT).show()
            }
        }
        gallery.setOnClickListener{

        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 123 && resultCode== RESULT_OK){
            val extras=data?.extras
            val bitmap=extras!!.get("data") as Bitmap
            detectTextfromCamera(bitmap)

        }

    }

    private fun detectTextfromCamera(bitmap: Bitmap) {
        var recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
        val image = InputImage.fromBitmap(bitmap, 0)
        recognizer.process(image)
            .addOnSuccessListener { visionText ->
                // Handle success
                val extractedText=visionText.text.toString()
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                Toast.makeText(this, "Error Occured", Toast.LENGTH_SHORT).show()
            }

    }
}