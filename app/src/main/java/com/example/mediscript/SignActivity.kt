package com.example.mediscript

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.*
import com.google.firebase.auth.FirebaseAuth

class SignActivity : AppCompatActivity() {

    lateinit var signMail : EditText
    lateinit var signPass : EditText
    lateinit var signPassConfirm : EditText
    lateinit var toLogin: TextView
    lateinit var sign : Button
    lateinit var name : EditText

    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        signMail=findViewById(R.id.signMail)
        signPass=findViewById(R.id.signPass)
        toLogin=findViewById(R.id.backToLogin)
        signPassConfirm=findViewById(R.id.signConPass)
        sign=findViewById(R.id.signButton)
        name=findViewById(R.id.signName)
        auth=FirebaseAuth.getInstance()

        sign.setOnClickListener{
            var mail=signMail.text.toString()
            var pass=signPass.text.toString()
            var passConfirn=signPassConfirm.text.toString()

            if(mail.isEmpty() || pass.isEmpty() || passConfirn.isEmpty()){
                Toast.makeText(this, "Please enter Email and Password", LENGTH_SHORT).show()
            }else if( !pass.equals(passConfirn)){
                    makeText(this, "Password do not match", LENGTH_SHORT).show()
            }else{
                auth.createUserWithEmailAndPassword(mail,pass)
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            var intent= Intent(this,LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            var msg=it.exception?.message?:"Error occured"
                            Toast.makeText(this, "msg", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
            toLogin.setOnClickListener{
                var intent=Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }





    }
}