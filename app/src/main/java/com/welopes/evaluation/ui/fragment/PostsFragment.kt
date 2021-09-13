package com.welopes.evaluation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.welopes.evaluation.R
import com.welopes.evaluation.databinding.FragmentPostsBinding
import com.welopes.evaluation.ui.adapter.PostListAdapter
import com.welopes.evaluation.util.Status
import com.welopes.evaluation.viewmodels.PostsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostsFragment : BaseFragment() {

    private val args: PostsFragmentArgs by navArgs()

    private lateinit var binding: FragmentPostsBinding
    private val viewModel by viewModel<PostsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setTitle(getString(R.string.posts_title_text, args.name))

        binding = FragmentPostsBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.lista.adapter = PostListAdapter()

        setObservers()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPostsByUserId(args.id.toInt())
    }

    private fun setObservers() {
        viewModel.status.observe(viewLifecycleOwner, {
            when {
                Status.LOADING == it -> {
                    binding.loading.visibility = VISIBLE
                }
                Status.SUCCESS == it -> {
                    binding.loading.visibility = GONE
                }
                else -> {
                    binding.loading.visibility = GONE
                    showError(getString(R.string.error_post_list))
                }
            }
        })
    }

}