package com.itacademy.personal_ads.presentation.create_ad_screens

import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.itacademy.navigation.NavCommand
import com.itacademy.navigation.NavCommands
import com.itacademy.navigation.navigate
import com.itacademy.personal_ads.R
import com.itacademy.personal_ads.databinding.FragmentNamingAdBinding
import com.itacademy.personal_ads.domain.model.AdDTO
import com.itacademy.personal_ads.domain.model.StateAd
import com.itacademy.personal_ads.domain.model.TypeAd
import com.itacademy.personal_ads.presentation.PersonalAdsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NamingAdFragment : Fragment(R.layout.fragment_naming_ad) {


    private val binding by viewBinding<FragmentNamingAdBinding>()
    private val viewModel by viewModels<PersonalAdsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etTitle = binding.tiEtTitleAd
        val tilTitle = binding.tilTitleAd

        val etDescription = binding.tiEtDescriptionAd
        val tilDescription = binding.tilDescriptionAd

        val btn = binding.btnNext

        etTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if ((s?.length ?: 0) > 50) {
                    tilTitle.error = "Название не может превышать 50 символов"
                } else {
                    tilTitle.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        etDescription.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if ((s?.length ?: 0) > 500) {
                    tilDescription.error = "Название не может превышать 500 символов"
                } else {
                    tilDescription.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        btn.setOnClickListener {
            val title = etTitle.text
            val description = etDescription.text
            val titleHasNotError = !title.isNullOrEmpty() && tilTitle.error.isNullOrEmpty()
            val descriptionHasNotError = !description.isNullOrEmpty() && tilDescription.error.isNullOrEmpty()

            if (titleHasNotError && descriptionHasNotError) {
                val type = arguments?.getString("type").toString()
                if (type == StateAd.NEW.name || type == StateAd.USED.name) {
                    Log.d("dadadAda", type)
                    viewModel.postAd(
                        AdDTO(
                            100,
                            title.toString(),
                            description.toString(),
                            TypeAd.STUFF,
                            StateAd.valueOf(type)
                        )
                    )

                    navigate(
                        NavCommand(
                            NavCommands.DeepLink(
                                url = Uri.parse("waceplare://upload_images")
                            )
                        )
                    )

                } else {
                    Log.d("dadadAda", type)
                    viewModel.postAd(AdDTO(
                        100,
                        title.toString(),
                        description.toString(),
                        TypeAd.valueOf(type),
                        null
                    ))

                    navigate(
                        NavCommand(
                            NavCommands.DeepLink(
                                url = Uri.parse("waceplare://upload_images")
                            )
                        )
                    )
                }

            } else {
                Toast.makeText(context, "Исправьте ошибку!", Toast.LENGTH_LONG).show()
            }
        }

    }


}