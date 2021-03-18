package com.example.m_health.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.m_health.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        mAuth = FirebaseAuth.getInstance()



        btn_getLink.setOnClickListener {
            val email = forgot_email.text.toString().trim()

            if (TextUtils.isEmpty(email)){
                Toast.makeText(applicationContext, "Enter your email", Toast.LENGTH_LONG).show()
            } else {
                mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this@ForgotPasswordActivity,
                                "Check email to reset your password!", Toast.LENGTH_LONG).show()
                                

                        } else {
                            Toast.makeText(this@ForgotPasswordActivity,
                                "Failed to send reset password email!", Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }
        login_btn.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}