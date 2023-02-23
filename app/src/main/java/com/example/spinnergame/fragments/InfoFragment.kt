package com.example.spinnergame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spinnergame.adapter.ResultAdapter
import com.example.spinnergame.databinding.FragmentInfoBinding
import com.example.spinnergame.viewmodel.ResultViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoFragment : Fragment() {
val adapter:ResultAdapter= ResultAdapter()
lateinit var binding:FragmentInfoBinding
val viewModel:ResultViewModel by activityViewModels ()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentInfoBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    initAdapter()
    }

    private fun initAdapter() {
        val rv=binding.resultRv

        rv.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        rv.adapter=adapter



        viewModel.allToResult.observe(viewLifecycleOwner){
            adapter.differ.submitList(it)

        }

viewModel.sumData.observe(viewLifecycleOwner){
    binding.commonCountTxt.text=it.toString()

}



    }


}