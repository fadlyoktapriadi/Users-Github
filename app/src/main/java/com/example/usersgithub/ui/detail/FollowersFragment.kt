package com.example.usersgithub.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usersgithub.databinding.FragmentFollowersBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.core.data.Result
import com.example.usersgithub.ui.main.UserAdapter


class FollowersFragment : Fragment() {

    private lateinit var binding: FragmentFollowersBinding

    private val detailViewModel: DetailViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = requireActivity().intent.extras?.getString("username").toString()
        binding = FragmentFollowersBinding.bind(view)

        binding.rvFollowers.layoutManager = LinearLayoutManager(requireActivity())
        setupData(username)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowersBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setupData(username: String) {
        detailViewModel.getFollowers(username).observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val adapter = UserAdapter()
                    adapter.submitList(it.data)
                    binding.rvFollowers.adapter = adapter
                }

                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }
}