package com.ihsan.binarchallengechapter7

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PatternMatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ihsan.binarchallengechapter7.databinding.ActivityLoginBinding
import com.ihsan.binarchallengechapter7.helper.LoginPref
import com.ihsan.binarchallengechapter7.viewmodel.MainViewModel
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val TAG = LoginActivity::class.java.simpleName
    private lateinit var loginPref: LoginPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mainViewModel = ViewModelProvider(this, defaultViewModelProviderFactory)[MainViewModel::class.java]
        loginPref = LoginPref(this)

        binding.btnLogin.setOnClickListener {
            Log.i(TAG, "btnLogin CLicked...")
            binding.progressBar.visibility = View.VISIBLE
            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()

            mainViewModel.userLogin(email, password)
        }

        binding.tvDontAlreadyAccount.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        //isSuccess true
        mainViewModel.successLogin.observe(this, {
            binding.progressBar.visibility = View.GONE
            if (it.data?.token != null) loginPref.setLoginPref(it.data.token)
            Intent(this, MainActivity::class.java).also { intent ->
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
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