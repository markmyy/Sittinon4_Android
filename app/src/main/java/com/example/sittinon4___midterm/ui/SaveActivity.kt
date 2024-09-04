package com.example.sittinon4___midterm.ui

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sittinon4___midterm.R
import com.example.sittinon4___midterm.Save2Activity
import com.example.sittinon4___midterm.Searching
import com.google.gson.JsonObject
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class SaveActivity : AppCompatActivity() {
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_save)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val goButton = findViewById<Button>(R.id.Gobutton)
        goButton.setOnClickListener {
            saveHouseData()
        }

    }

    private fun saveHouseData() {
        // รับข้อมูลจาก EditText
        val area = findViewById<EditText>(R.id.editTextarea).text.toString()
        val bedrooms = findViewById<EditText>(R.id.editTextbedrooms).text.toString()
        val bathrooms = findViewById<EditText>(R.id.editTextbathrooms).text.toString()
        val price = findViewById<EditText>(R.id.editTextprice).text.toString()
        val condi = findViewById<EditText>(R.id.editTextcondi).text.toString()
        val type = findViewById<EditText>(R.id.editTexttype).text.toString()
        val yearBuilt = findViewById<EditText>(R.id.editTextyear_built).text.toString()
        val parkingSpaces = findViewById<EditText>(R.id.editTextparking_spaces).text.toString()
        val address = findViewById<EditText>(R.id.editTextadd).text.toString()
        val imageUrl = findViewById<ImageView>(R.id.imageView)

        // สร้าง JSON Object สำหรับส่งข้อมูล
        val url = "http://10.13.1.164:3000/houses"
        val okHttpClient = OkHttpClient()
        val formBody: RequestBody = FormBody.Builder()
            .add("area",area)
            .add("bedrooms",bedrooms)
            .add("bathrooms",bathrooms)
            .add("price",price)
            .add("condi",condi)
            .add("type",type)
            .add("year_built",yearBuilt)
            .add("parking_spaces",parkingSpaces)
            .add("address",address)
            .build()
        val request: Request = Request.Builder()
            .url(url)
            .post(formBody)
            .build()
        val response = okHttpClient.newCall(request).execute()
        if(response.isSuccessful) {
            val obj = JSONObject(response.body!!.string())
            val status = obj["status"].toString()
            if (status == "true") {
                intent = Intent(this, Searching::class.java)
                startActivity(intent)
                finish()
            } else {
                val message = obj["message"].toString()
                Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
            }
        }
    }
}
