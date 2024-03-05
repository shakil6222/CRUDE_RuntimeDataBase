package com.example.crude_runtimedatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

class AddUserActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    lateinit var addName: EditText
    lateinit var addEmail: EditText
    lateinit var addPassword: EditText
    lateinit var addButtton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

            addName = findViewById(R.id.nameEdit_EditText)
            addEmail = findViewById(R.id.emailedit_EditText)
            addPassword = findViewById(R.id.passwordedit_EditText)
            addButtton = findViewById(R.id.addBtn_Button)

        addButtton.setOnClickListener {
            val sName = addName.text.toString()
            val sEmall = addEmail.text.toString()
            val aPassword = addPassword.text.toString()
            val uId = UUID.randomUUID().toString()
            val map = hashMapOf(
                "id" to uId,
                "name" to sName,
                "email" to sEmall,
                "password" to aPassword
            )
            db.collection("users").document(uId).set(map)
                .addOnSuccessListener {
                    Toast.makeText(this, "Add data Success", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                }
                .addOnFailureListener {
                    Toast.makeText(this, "add user Failure", Toast.LENGTH_SHORT).show()
                }
        }
    }
}