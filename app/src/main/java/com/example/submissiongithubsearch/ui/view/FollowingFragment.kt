package com.example.submissiongithubsearch.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissiongithubsearch.R
import com.example.submissiongithubsearch.data.response.ResponseFollow
import com.example.submissiongithubsearch.databinding.FragmentFollowersBinding
import com.example.submissiongithubsearch.databinding.FragmentFollowingBinding
import com.example.submissiongithubsearch.ui.adapter.FollowListAdapter
import com.example.submissiongithubsearch.ui.viewmodel.DetailViewModel

class FollowingFragment : Fragment() {
    private var _binding: FragmentFollowingBinding? = null
    private val binding get()= _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        val view = binding.root
        showLoading(false)
        val detailViewModel = ViewModelProvider(requireActivity())[DetailViewModel::class.java]
        arguments?.getString("username")?.let { detailViewModel.findFollowing(it) }
        detailViewModel.following.observe(viewLifecycleOwner){
            setGithubData(it)
        }
        detailViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }
        val layoutManager = LinearLayoutManager(context)
        binding.rvFollowing.layoutManager = layoutManager
        return view
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.pbFollowing.visibility = View.VISIBLE
            binding.rvFollowing.visibility = View.GONE
        } else {
            binding.pbFollowing.visibility = View.GONE
            binding.rvFollowing.visibility = View.VISIBLE
        }
    }

    private fun setGithubData(response: List<ResponseFollow>?){
        val adapter = FollowListAdapter(requireContext())
        adapter.submitList(response)
        binding.rvFollowing.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FollowersFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}