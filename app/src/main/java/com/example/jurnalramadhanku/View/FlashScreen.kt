package com.example.jurnalramadhanku.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.jurnalramadhanku.MainActivity
import com.example.jurnalramadhanku.R
import com.example.jurnalramadhanku.View.Home.HomeActivity

class FlashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_screen)


        Handler().postDelayed(Runnable {

            startActivity(Intent(this, MainActivity::class.java))
            finish()

        }, 5000)
        title = ""


    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }
}
