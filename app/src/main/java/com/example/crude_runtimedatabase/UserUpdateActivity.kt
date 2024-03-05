package com.example.crude_runtimedatabase

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class UserUpdateActivity : AppCompatActivity() {

    private var db = FirebaseFirestore.getInstance()
    lateinit var uNameEdit :EditText
    lateinit var uEmailEdit :EditText
    lateinit var uPasswordEdit :EditText
    lateinit var updateBtn :Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_update)

        val id = intent.extras?.getString("key_id")
        val name = intent.extras?.getString("key_name")
        val email = intent.extras?.getString("key_email")

        updateBtn = findViewById(R.id.updateBtn_Button)
        uNameEdit= findViewById(R.id.unameEdit_EditText)
        uEmailEdit = findViewById(R.id.uemailedit_EditText)
        uPasswordEdit = findViewById(R.id.upasswordedit_EditText)

        uNameEdit.setText(name)
        uEmailEdit.setText(email)

        updateBtn.setOnClickListener {
            val sName = uNameEdit.text.toString()
            val sEmail = uEmailEdit.text.toString()

            val map  = mapOf(
                "name" to sName,
                "email" to sEmail
            )
            db.collection("users").document("$id").update(map).addOnSuccessListener {
                Toast.makeText(this,"User Update Success",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,MainActivity::class.java))

            }
                .addOnFailureListener {
                    Toast.makeText(this,"User Update fail",Toast.LENGTH_SHORT).show()
                }

        }




    }
}