package com.example.crude_runtimedatabase

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity(),setOnUserrClickListener{

   lateinit var userList:ListView
   lateinit var floatingActBtn :FloatingActionButton
   private lateinit var userAdapter: UserListAdapter
   val db = FirebaseFirestore.getInstance()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        floatingActBtn = findViewById(R.id.addBtn_flottingActBtn)

        userList = findViewById(R.id.user_ListView)

        val userArray = ArrayList<UserModel>()

        userAdapter = UserListAdapter(userArray,this,this)
        userList.adapter = userAdapter

        showUsers()


        floatingActBtn.setOnClickListener {
            startActivity(Intent(this,AddUserActivity::class.java))
        }
    }
    fun showUsers(){
        db.collection("users").get()
            .addOnSuccessListener {
                val data = it.toObjects(UserModel::class.java)

                userAdapter = UserListAdapter(data,this,this)

                userList.adapter = userAdapter
                Toast.makeText(this, "User Added $userList", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "users Added failed", Toast.LENGTH_SHORT).show()
            }
    }


    override fun onUpdateClick(user:UserModel) {
        val intent = Intent(this,UserUpdateActivity::class.java)
        intent.putExtra("key_id",user.id.toString())
        intent.putExtra("key_name",user.name.toString())
        intent.putExtra("key_email",user.pass.toString())
        startActivity(intent)
    }

    override fun onDeleteClick(user: UserModel) {
         deleteUser(user.id.toString())
            }
        fun deleteUser(id:String){
            db.collection("users").document(id).delete()
                .addOnSuccessListener {
                    Toast.makeText(this, "users deleted success", Toast.LENGTH_SHORT).show()
                    showUsers()
                }

                .addOnFailureListener {
                    Toast.makeText(this, "users deleted failed", Toast.LENGTH_SHORT).show()
                }

        }
    }



