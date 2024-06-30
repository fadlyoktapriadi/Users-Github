package com.example.usersgithub.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usersgithub.databinding.FragmentFollowingBinding
import com.example.usersgithub.data.Result
import com.example.usersgithub.factory.ViewModelFactory

class FollowingFragment() : Fragment() {

    private lateinit var binding: FragmentFollowingBinding

    private val viewModel by viewModels<DetailViewModel> {
        ViewModelFactory.getInstance(requireActivity().application)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = requireActivity().intent.extras?.getString("login").toString()
        binding = FragmentFollowingBinding.bind(view)

        binding.rvFollowing.layoutManager = LinearLayoutManager(requireActivity())

        setupData(username)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setupData(username: String) {
        viewModel.getFollowing(username).observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val adapter = FollowAdapter()
                    adapter.submitList(it.data)
                    binding.rvFollowing.adapter = adapter
                }

                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }


}