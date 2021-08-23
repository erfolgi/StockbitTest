package com.stockbit.hiring.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.stockbit.hiring.R
import com.stockbit.hiring.databinding.ActivityLoginBinding
import com.stockbit.hiring.ui.watchlist.WatchlistActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        val toolbar = findViewById<Toolbar>(R.id.toolbar_top)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        findViewById<TextView>(R.id.toolbar_title).text = "Masuk"
        findViewById<ImageButton>(R.id.toolbar_back).setOnClickListener { finish() }

        mBinding.btLogin.setOnClickListener {
            var valid = true
            var errorMsg=""
            if(mBinding.etUsername.text.toString()==""){
                valid=false
                errorMsg += "The username field is required\n"
            }
            if(mBinding.etPassword.text.toString()==""){
                valid=false
                errorMsg += "The password field is required\n"
            }

            if(valid){
                startActivity(Intent(this,WatchlistActivity::class.java))
            }else{
                Toast.makeText(this,errorMsg, Toast.LENGTH_LONG).show()
            }

        }


    }
}