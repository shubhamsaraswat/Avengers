package com.shubham.activitylifecycle

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    lateinit var etphone : EditText
    lateinit var etpassword: EditText
    lateinit var btnlogin:Button
    lateinit var txtforgotpassword: TextView
    lateinit var txtregister: TextView
    lateinit var sharedPreferences: SharedPreferences
    var validmobilenumber="9758872740"
    var validpassword=arrayOf("shubham123","shubham321","shubham231")

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferences=getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        val isLoggedIn=sharedPreferences.getBoolean("isLoggedIn",false)
        setContentView(R.layout.activity_login)
        if(isLoggedIn){
            val intent=Intent(this@LoginActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        title=getString(R.string.login)
        etphone=findViewById(R.id.etphone)
        etpassword=findViewById(R.id.etpassword)
        btnlogin=findViewById(R.id.btnlogin)
        txtforgotpassword=findViewById(R.id.txtforgotpassword)
        txtregister=findViewById(R.id.txtregister)
        btnlogin.setOnClickListener {
            val mobilenumber=etphone.text.toString()
            val password=etpassword.text.toString()
            if(validmobilenumber==mobilenumber && (validpassword.contains(password))) {
                savePreference()
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(
                    this@LoginActivity,
                    "Incorrect Information",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

    }

    override fun onPause() {
        super.onPause()
        finish()
    }
    fun savePreference(){
        sharedPreferences.edit().putBoolean("isLoggedIn",true).apply()
    }
}