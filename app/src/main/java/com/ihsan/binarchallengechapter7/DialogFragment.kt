package com.ihsan.binarchallengechapter7

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ihsan.binarchallengechapter7.databinding.FragmentDialogBinding
import com.ihsan.binarchallengechapter7.helper.SetBackgroundChoice

class DialogFragment(
    private val mode: String,
    private val name: String,
    private val result: String): DialogFragment() {

    private lateinit var binding: FragmentDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.btnResumeGame.setOnClickListener {
            val setBgChoice = SetBackgroundChoice(requireContext() as GameActivity)
            setBgChoice.resetBackgroundChoice()
            startActivity(requireActivity().intent)
            dismiss()
        }
        binding.btnBackMenu.setOnClickListener {
            val intent = Intent(requireActivity(), MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra(MainActivity.KEY_NAME_FROM_DIALOG, name)
            startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()
        binding.tvMode.text = mode
        binding.tvResultGame.text = "$name\n$result"
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
}