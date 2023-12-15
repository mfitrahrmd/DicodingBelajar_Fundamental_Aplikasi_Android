package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.databinding.FragmentOptionDialogBinding

class OptionDialogFragment : DialogFragment() {
    private lateinit var _binding: FragmentOptionDialogBinding
    private var optionDialogListener: OnOptionDialogListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOptionDialogBinding.inflate(inflater, container, false)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(_binding) {
            btnChoose.setOnClickListener {
                if (rgOptions.checkedRadioButtonId != -1) {
                    val coach = when(rgOptions.checkedRadioButtonId) {
                        R.id.rb_saf -> rbSaf.text.toString().trim()
                        R.id.rb_mou -> rbMou.text.toString().trim()
                        R.id.rb_lvg -> rbLvg.text.toString().trim()
                        R.id.rb_moyes -> rbMoyes.text.toString().trim()
                        else -> null
                    }
                    optionDialogListener?.onOptionChosen(coach)
                    dialog?.dismiss()
                }
            }

            btnClose.setOnClickListener {
                dialog?.cancel()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val fragment = parentFragment
        if (fragment is AboutF) {
            optionDialogListener = fragment.optionDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        optionDialogListener = null
    }

    interface OnOptionDialogListener {
        fun onOptionChosen(text: String?)
    }
}