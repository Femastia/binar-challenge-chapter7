package com.ihsan.binarchallengechapter7

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ihsan.binarchallengechapter7.databinding.ActivityLoginBinding
import com.ihsan.binarchallengechapter7.viewmodel.MainViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val TAG = LoginActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mainViewModel =
            ViewModelProvider(this, defaultViewModelProviderFactory)[MainViewModel::class.java]

        binding.btnLogin.setOnClickListener {
            Log.i(TAG, "btnLogin CLicked...")
            val username = binding.edtUsername.text.toString().trim()
            val email = binding.edtPassword.text.toString().trim()
            mainViewModel.userLogin(username, email)
            binding.progressBar.visibility = View.VISIBLE
        }

        //isSuccess true
        mainViewModel.successLogin.observe(this, {
            binding.progressBar.visibility = View.GONE
            Toast.makeText(this, "${it.data?.username}", Toast.LENGTH_SHORT).show()
        })
        //isSuccess false
        mainViewModel.unSuccessLogin.observe(this, {
            binding.progressBar.visibility = View.GONE
            Toast.makeText(this, it.errors, Toast.LENGTH_SHORT).show()
        })
        //isBadRequest; Input User salah format
        mainViewModel.responseBadRequest.observe(this, {
            binding.progressBar.visibility = View.GONE
            Toast.makeText(
                this,
                "Contoh Email yang Benar:\n${it.errors?.body?.email?.example}\nContoh password yg Benar:\n${it.errors?.body?.password?.example}",
                Toast.LENGTH_LONG
            ).show()
        })
    }
}