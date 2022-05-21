package com.example.sociopigeon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.sociopigeon.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.fab.setOnClickListener {

            val intent=Intent(this,CreatePostActivity::class.java)
            startActivity(intent)

        }

        auth = Firebase.auth
        mainBinding.signOut.setOnClickListener{
            val intent = Intent(this, SignInActivity::class.java)
            auth.signOut()
            startActivity(intent)
            finish()
        }

    }

}