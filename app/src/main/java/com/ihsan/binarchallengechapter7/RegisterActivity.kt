package com.ihsan.binarchallengechapter7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ihsan.binarchallengechapter7.databinding.ActivityRegisterBinding
import com.ihsan.binarchallengechapter7.viewmodel.MainViewModel

class RegisterActivity : AppCompatActivity() {

    private val TAG = RegisterActivity::class.java.simpleName
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this, defaultViewModelProviderFactory)[MainViewModel::class.java]

        binding.btnSignUp.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            val email = binding.edtEmailuser.text.toString().trim()
            val username = binding.edtUsername.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.edtEmailuser.error = "Penulisan Email Salah!"
                binding.edtEmailuser.requestFocus()
                binding.progressBar.visibility = View.GONE
                return@setOnClickListener
            }

            if (username.isEmpty() || username.length < 6){
                binding.edtUsername.error = "Tidak boleh kurang 6 karakter!"
                binding.edtUsername.requestFocus()
                binding.progressBar.visibility = View.GONE
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.edtPassword.error = "Password tidak boleh kosong!"
                binding.edtPassword.requestFocus()
                binding.progressBar.visibility = View.GONE
                return@setOnClickListener
            }

            viewModel.register(email, username, password)
        }

        //isSuccess true
        viewModel.successRegis.observe(this, {
            binding.progressBar.visibility = View.GONE
            Toast.makeText(this, "${it.data?.username} Terdaftar!", Toast.LENGTH_SHORT).show()
            Intent(this, LoginActivity::class.java).also { intent ->
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        })

        //isSuccess false
        viewModel.unSuccessRegis.observe(this, {
            binding.progressBar.visibility = View.GONE
            Toast.makeText(this, "Email atau username sudah ada\n\n${it.errors}", Toast.LENGTH_LONG).show()
        })
    }
}