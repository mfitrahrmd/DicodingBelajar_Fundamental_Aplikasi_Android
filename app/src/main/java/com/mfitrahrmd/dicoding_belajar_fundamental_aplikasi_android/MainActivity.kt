package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.databinding.AboutBinding
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.databinding.ActivityMainBinding
import com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android.databinding.ContentBinding

class ContentF : Fragment() {
    private lateinit var _binding: ContentBinding
    private var _onClick: OnClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ContentBinding.inflate(inflater, container, false)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("debug",_onClick.toString())
        _binding.tvName.text = "Content"
        _binding.btnMove.setOnClickListener(_onClick)
    }

    fun setOnClickListener(l: OnClickListener) {
        _onClick = l
    }
}

class AboutF : Fragment() {
    companion object {
        val EXTRA_TEXT = "extra_text"
    }

    private lateinit var _binding: AboutBinding
    private var _text: String? = null
    internal var optionDialogListener: OptionDialogFragment.OnOptionDialogListener = object : OptionDialogFragment.OnOptionDialogListener {
        override fun onOptionChosen(text: String?) {
            Toast.makeText(requireActivity(), text, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AboutBinding.inflate(inflater, container, false)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null) {
            _text = savedInstanceState.getString(EXTRA_TEXT)
        }

        _text = arguments?.getString(EXTRA_TEXT)

        with(_binding) {
            tvText.text = _text
            btnShowDialog.setOnClickListener {
                val optionDialogFragment = OptionDialogFragment()
                val fragmentManager = childFragmentManager

                optionDialogFragment.show(fragmentManager, OptionDialogFragment::class.java.simpleName)
            }
        }
    }
}

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        _binding.tvText.text = "Hello World!"
        val cf = ContentF()
        val af = AboutF()
        cf.setOnClickListener {
            Log.d("debug", "ok")
            af.arguments = Bundle().also {
                it.putString(AboutF.EXTRA_TEXT, "About Fragment")
            }
            supportFragmentManager
                .beginTransaction()
                .replace(_binding.flContent.id, af)
                .addToBackStack(null)
                .commit()
        }
        supportFragmentManager
            .beginTransaction()
            .add(_binding.flContent.id, cf, ContentF::class.java.simpleName)
            .commit()

        setContentView(_binding.root)
    }
}