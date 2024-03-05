package com.example.crude_runtimedatabase

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects

class UserListAdapter(val userModelList:List<UserModel>,private val context: Context,val onUserClickListe:setOnUserrClickListener):BaseAdapter() {
    override fun getCount(): Int {
       return userModelList.size
    }

    override fun getItem(position: Int): Any {
        return userModelList[position]
    }

    override fun getItemId(position: Int): Long {
       return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = LayoutInflater.from(context).inflate(R.layout.user_list_item,parent,false)

        val name = view.findViewById<TextView>(R.id.userName_TextView)
        val email = view.findViewById<TextView>(R.id.email_TextView)
        val password  = view.findViewById<TextView>(R.id.password_TextView)
        val delete = view.findViewById<ImageView>(R.id.delete_imageView)
        val updatee = view.findViewById<ImageView>(R.id.update_imageView)

        name.text = userModelList[position].name
        email.text = userModelList[position].email
        password.text = userModelList[position].pass.toString()

        updatee.setOnClickListener {
      onUserClickListe.onUpdateClick(userModelList[position])
        }
        delete.setOnClickListener {
            val dilog = AlertDialog.Builder(context)
            dilog.setTitle("Delete")
            dilog.setMessage("Are you want to exit")
            dilog.setPositiveButton("ok"){ dilog,which->
                onUserClickListe.onDeleteClick(userModelList[position])

            }
            dilog.setNegativeButton("cancled"){
                    dilog, which ->

                dilog.dismiss()
        }

            val alert = dilog.create()
            alert.show()


        }
        return view
    }
}
interface setOnUserrClickListener{
    fun onUpdateClick(user:UserModel)
    fun onDeleteClick(user:UserModel)
}