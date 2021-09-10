package com.tribo_mkt.evaluation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tribo_mkt.evaluation.R
import com.tribo_mkt.evaluation.databinding.FragmentMainBinding
import com.tribo_mkt.evaluation.databinding.FragmentPostsBinding

class PostsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPostsBinding.inflate(inflater, container, false)

        return binding.root
    }

}