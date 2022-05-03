package dev.rodosteam.questtime.screen.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.app.ActivityCompat.recreate
import androidx.lifecycle.ViewModelProvider
import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.databinding.FragmentSettingsBinding
import dev.rodosteam.questtime.screen.common.base.BaseFragment
import dev.rodosteam.questtime.utils.LocaleManager.getLanguageCode
import dev.rodosteam.questtime.utils.LocaleManager.getLanguagePosition
import dev.rodosteam.questtime.utils.LocaleManager.setLanguageCode
import dev.rodosteam.questtime.utils.LocaleManager.setLanguagePosition


class SettingsFragment : BaseFragment() {

    private lateinit var settingsViewModel: SettingsViewModel
    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        settingsViewModel =
            ViewModelProvider(this)[SettingsViewModel::class.java]

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val languagesSpinner : Spinner = binding.languages
        ArrayAdapter.createFromResource(requireContext(), R.array.languages, android.R.layout.simple_spinner_item).also {
            adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            languagesSpinner.adapter = adapter
        }
        languagesSpinner.setSelection(getLanguagePosition(requireContext()))
        languagesSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> changeLanguageCode("en", 0)
                    1 -> changeLanguageCode("ru", 1)
                    2 -> changeLanguageCode("de", 2)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                changeLanguageCode("en", 0)
            }

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun changeLanguageCode(languageCode: String, position: Int) {
        if (getLanguageCode(requireContext()) != languageCode) {
            setLanguageCode(requireContext(), languageCode)
            setLanguagePosition(requireContext(), position)
            recreate(mainActivity)
        }
    }
}