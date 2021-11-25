package com.ihsan.binarchallengechapter7

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.ihsan.binarchallengechapter7.databinding.ActivityUpdateUserBinding
import com.ihsan.binarchallengechapter7.helper.LoginPref
import com.ihsan.binarchallengechapter7.model.DataPut
import com.ihsan.binarchallengechapter7.model.DataPutUpdate
import com.ihsan.binarchallengechapter7.viewmodel.MainViewModel

class UpdateUserActivity : AppCompatActivity() {

    companion object {
        private val REQUEST_TAKE_PHOTO = 0
        private val REQUEST_SELECT_IMAGE_IN_ALBUM = 1
        private const val TAG = "UpdateUserActivity"
    }
    private lateinit var binding: ActivityUpdateUserBinding
    private lateinit var loginPref: LoginPref
    private lateinit var imageUri: Uri
    private var imageData: ByteArray? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainViewModel = ViewModelProvider(this, defaultViewModelProviderFactory)[MainViewModel::class.java]
        loginPref = LoginPref(this)

        mainViewModel.getUser(loginPref.getLoginPref().toString())
        mainViewModel.getUser.observe(this, {
            binding.progressBar.visibility = View.GONE
            Glide.with(this)
                .load(it.data?.photo)
                .into(binding.imgProfile)
            binding.edtUsername.setText(it.data?.username)
            binding.edtEmailuser.setText(it.data?.email)
        })

        binding.btnLoadImage.setOnClickListener {
            /*val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_SELECT_IMAGE_IN_ALBUM)*/
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, REQUEST_SELECT_IMAGE_IN_ALBUM)

        }

        binding.btnSave.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            val dataPutUpdate = DataPutUpdate()
            val editUserName = binding.edtUsername.text.toString().trim()
            val edtEmail = binding.edtEmailuser.text.toString().trim()
            dataPutUpdate.username = editUserName
            dataPutUpdate.email = edtEmail


            mainViewModel.updateUser(loginPref.getLoginPref().toString(), dataPutUpdate)
            Log.i(TAG, "onCreate token:${loginPref.getLoginPref().toString()}")
            //success upate
            mainViewModel.putUser.observe(this, {
                binding.progressBar.visibility = View.GONE
                Log.i(TAG, "Success update data:${it.data} ")
                Toast.makeText(this, "${it.data}", Toast.LENGTH_SHORT).show()
                //startActivity(Intent(this, ProfileActivity::class.java))
            })
            //error upate
            mainViewModel.errorUpdate.observe(this, {
                binding.progressBar.visibility = View.GONE
                Log.i(TAG, "Error update data:status = ${it.status} error = ${it.errors} ")
                Toast.makeText(this, "${it.errors}", Toast.LENGTH_SHORT).show()
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == REQUEST_SELECT_IMAGE_IN_ALBUM) {
            imageUri = data?.data!!
            binding.imgProfile.setImageURI(imageUri)

            val inputStream = contentResolver.openInputStream(imageUri)
            inputStream?.buffered()?.use {
                imageData = it.readBytes()
            }
        }
    }
}