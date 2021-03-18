package com.example.m_health.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.m_health.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var newusername: EditText? = null
    private var enterEmailAddress:EditText? = null
    private var enterPasswords:EditText? = null
    private var enterConfirmPassword: EditText? = null
    private var lgnRegister:Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        setContentView(R.layout.activity_register)

        newusername = findViewById(R.id.new_username)
        enterEmailAddress = findViewById(R.id.enter_Emailaddress)
        enterPasswords = findViewById(R.id.enter_passwords)
        enterConfirmPassword = findViewById(R.id.enter_confirm_password)
        lgnRegister = findViewById(R.id.lgn_Register)

        login_button.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        btn_account.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        lgn_Register!!.setOnClickListener{
            registerNewUser()

        }
    }

    private fun registerNewUser() {
        val email = enter_Emailaddress.text.toString().trim()
        val password = enter_passwords.text.toString().trim()
        val confirmPassword = enter_confirm_password.text.toString().trim()
        val user = new_username.text.toString().trim()


            if (TextUtils.isEmpty(user)) {
                new_username.error = "Enter UserName!"
                return
            }
            else if (TextUtils.isEmpty(email)){
                enter_Emailaddress.error = "Enter Your Email!"
                return
            }
            else if(TextUtils.isEmpty(password)){
                enter_passwords.error = "Enter Your Password!"
            }
            else if(TextUtils.isEmpty(confirmPassword)){
                enter_confirm_password.error = "Confirm Password!"
                return
            }
            else if(password != confirmPassword){
                enter_confirm_password.error = "Passwords do not Match!"
                return
            }
//            else if(password.length > 4){
//                enter_passwords.error = "Password should be more than 4!"
//                return
//            }
//            else if(isValidEmail(email)){
//                enter_Emailaddress.error = "Enter valid Email"
//                return
//            }
        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext,"Successfully Registered",Toast.LENGTH_LONG).show()
                    var intent = Intent(applicationContext, DashboardActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(applicationContext,"Sign UP Failed!",Toast.LENGTH_LONG).show()

                }
            }

        }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(this.toString()) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}









