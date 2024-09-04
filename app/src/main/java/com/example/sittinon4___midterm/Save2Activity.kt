package com.example.sittinon4___midterm

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sittinon4___midterm.ui.SaveActivity
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject

class Save2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_save3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val searchQuery = intent.getStringExtra("searchQuery")
        if (searchQuery != null) {
            val url = "http://10.13.1.164:3000/get/houses/"+searchQuery
            val okHttpClient = OkHttpClient()

            val request: Request = Request.Builder()
                .url(url)
                .get()
                .build()
            val response = okHttpClient.newCall(request).execute()
            if(response.isSuccessful) {
                val obj = JSONObject(response.body!!.string())
                val status = obj["status"].toString()
                if (status == "true") {
                    val area = obj["area"].toString()
                    val bedrooms = obj["bedrooms"].toString()
                    val bathrooms = obj["bathrooms"].toString()
                    val price = obj["price"].toString()
                    val condi = obj["condi"].toString()
                    val type = obj["type"].toString()
                    val yearBuilt = obj["year_built"].toString()
                    val parkingSpaces = obj["parking_spaces"].toString()
                    val address = obj["address"].toString()

                    findViewById<TextView>(R.id.textView3).text = area
                    findViewById<TextView>(R.id.textView5).text = bedrooms
                    findViewById<TextView>(R.id.textView7).text = bathrooms
                    findViewById<TextView>(R.id.textView9).text = price
                    findViewById<TextView>(R.id.textView12).text = condi
                    findViewById<TextView>(R.id.textView17).text = type
                    findViewById<TextView>(R.id.textView16).text = yearBuilt
                    findViewById<TextView>(R.id.textView19).text = parkingSpaces
                    findViewById<TextView>(R.id.textView21).text = address

                } else {
                    val message = obj["message"].toString()
                    Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
                }
            }
        }





        val backButton = findViewById<Button>(R.id.Backbutton)
        backButton.setOnClickListener {
            val intent = Intent(this, Searching::class.java)
            startActivity(intent)
        }
    }
}