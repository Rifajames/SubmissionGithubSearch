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
import com.example.submissiongithubsearch.ui.adapter.FollowListAdapter
import com.example.submissiongithubsearch.ui.viewmodel.DetailViewModel

class FollowersFragment : Fragment() {
    private var _binding: FragmentFollowersBinding? = null
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
        _binding = FragmentFollowersBinding.inflate(inflater, container, false)
        val view = binding.root
        showLoading(false)
        val detailViewModel = ViewModelProvider(requireActivity())[DetailViewModel::class.java]
        arguments?.getString("username")?.let { detailViewModel.findFollowers(it) }
        detailViewModel.followers.observe(viewLifecycleOwner){
            setGithubData(it)
        }
        detailViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }
        val layoutManager = LinearLayoutManager(context)
        binding.rvFollowers.layoutManager = layoutManager
        return view
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.pbFollowers.visibility = View.VISIBLE
            binding.rvFollowers.visibility = View.GONE
        } else {
            binding.pbFollowers.visibility = View.GONE
            binding.rvFollowers.visibility = View.VISIBLE
        }
    }

    private fun setGithubData(response: List<ResponseFollow>?){
        val adapter = FollowListAdapter(requireContext())
        adapter.setFollowList(response)
        binding.rvFollowers.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FollowersFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}