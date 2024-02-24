package com.example.mediscript

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var logMail : EditText
    lateinit var logPass : EditText
    lateinit var login : Button
    lateinit var toSign : TextView
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth= FirebaseAuth.getInstance()
        logMail=findViewById(R.id.)
        logPass=findViewById(R,id.)
        login=findViewById(R,id.)
        toSign=findViewById(R.id.)
        var currentUser=auth.currentUser
        if(currentUser != null){
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        login.setOnClickListener{
            var mail = logMail.text.toString()
            var pass = logPass.text.toString()
            if( mail.isEmpty()  || pass.isEmpty() ){
                Toast.makeText(this, "Please enter your Email and Password", Toast.LENGTH_SHORT).show()
            }else{
                auth.signInWithEmailAndPassword(mail,pass)
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            var intent =Intent(this,MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            var msg=it.exception?.message?:"Error Occured"
                            Toast.makeText(this, "msg", Toast.LENGTH_SHORT).show()
                        }
                    }
            }

        }
        toSign.setOnClickListener{
            var intent =Intent(this,SignActivity::class.java)
            startActivity(intent)
        }


    }
}