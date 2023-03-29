package com.itacademy.waceplare.ui.fragment

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.itacademy.waceplare.R
import com.itacademy.waceplare.databinding.FragmentMessagesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessagesFragment : Fragment(R.layout.fragment_messages) {
    private val binding by viewBinding<FragmentMessagesBinding>()
}