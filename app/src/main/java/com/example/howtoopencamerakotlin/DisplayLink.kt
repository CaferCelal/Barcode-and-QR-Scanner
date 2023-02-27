package com.example.howtoopencamerakotlin

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class DisplayLink : AppCompatActivity() {
    val pattern = "^(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})([/\\w .-]*)*/?\$".toRegex()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_link)
        val message = intent.getStringExtra("myMessage")
        val text =findViewById<TextView>(R.id.code)
        if(isUrl(message.toString())){
            findViewById<TextView>(R.id.title).setText("Your Link:")
            findViewById<Button>(R.id.goLinkBtn).visibility = View.VISIBLE
            findViewById<Button>(R.id.goLinkBtn).setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(message)
                startActivity(intent)
            }
        }
        else{
            findViewById<TextView>(R.id.title).setText("Your Code:")
        }
        text.setText(message)


    }
    fun isUrl(str: String): Boolean {
        return pattern.matches(str)
    }
}