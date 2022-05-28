package com.example.sociopigeon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sociopigeon.daos.PostDao
import com.example.sociopigeon.databinding.ActivityMainBinding
import com.example.sociopigeon.models.Post
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity(), PostAdapter.IPostAdapter {
    private lateinit var postDao: PostDao
    private lateinit var adapter: PostAdapter
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.fab.setOnClickListener {

            val intent = Intent(this, CreatePostActivity::class.java)
            startActivity(intent)

        }

        auth = Firebase.auth
        mainBinding.signOut.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            auth.signOut()
            startActivity(intent)
            finish()
        }
        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {
        postDao = PostDao()
        val postCollections = postDao.postCollections
        val query = postCollections.orderBy("Created at", Query.Direction.DESCENDING)
        val recyclerViewOptions =
            FirestoreRecyclerOptions.Builder<Post>().setQuery(query, Post::class.java).build()


        adapter = PostAdapter(recyclerViewOptions, this)
        mainBinding.recyclerView.adapter = adapter
        mainBinding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    override fun onLikeClicked(postId: String) {
        postDao.updateLikes(postId)
    }

}