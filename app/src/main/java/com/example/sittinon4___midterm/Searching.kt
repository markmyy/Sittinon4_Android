package com.example.sittinon4___midterm

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sittinon4___midterm.ui.SaveActivity

class Searching : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_searching)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val editTextSearch = findViewById<EditText>(R.id.editTextSearch)
        val InputButton = findViewById<Button>(R.id.Inputbtn)
        InputButton.setOnClickListener {
            val intent = Intent(this, SaveActivity::class.java)
            startActivity(intent)
        }
        val Searchbutton = findViewById<Button>(R.id.Searchbutton)
        Searchbutton.setOnClickListener {
            val intent = Intent(this, Save2Activity::class.java)
            intent.putExtra("searchQuery", editTextSearch.text.toString())
            startActivity(intent)
        }
    }
}