package com.example.sociopigeon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.sociopigeon.daos.PostDao

class CreatePostActivity : AppCompatActivity() {
    private lateinit var postDao:PostDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
        var pButton=findViewById<Button>(R.id.postButton)
        var pInput=findViewById<EditText>(R.id.postInput)
        postDao=PostDao()

        pButton.setOnClickListener(){
           val input=pInput.text.toString().trim()
            if(input.isNotEmpty()){
                postDao.addPost(input)
                finish()

            }

        }
    }
}