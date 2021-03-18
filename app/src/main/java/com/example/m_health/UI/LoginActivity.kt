package com.example.m_health.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.m_health.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private  var enterEmail: EditText? = null
    private  var enterPassword: EditText? = null
    private var loginButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()

        setContentView(R.layout.activity_login)
        enterEmail = findViewById(R.id.enter_Email)
        enterPassword = findViewById(R.id.enter_password)
        loginButton =  findViewById(R.id.btn_Login)

        btn_Login.setOnClickListener {
            loginUser()
        }

        btn_Register.setOnClickListener {
            var intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_forgot_password.setOnClickListener {
            var intent = Intent(applicationContext, ForgotPasswordActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun loginUser(){
        val email = enter_Email.text.toString().trim()
        val password = enter_password.text.toString().trim()

        if (TextUtils.isEmpty(email)){
            enter_Email.error = "Enter your Email!"
            return
        } else if (TextUtils.isEmpty(password)){
            enter_password.error = "Enter your Password!"
            return
        }

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext,"Successfully Logged In",Toast.LENGTH_LONG).show()
                    var intent = Intent(applicationContext, DashboardActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(applicationContext,"Login Failed!",Toast.LENGTH_LONG).show()
                }
            }
    }
}