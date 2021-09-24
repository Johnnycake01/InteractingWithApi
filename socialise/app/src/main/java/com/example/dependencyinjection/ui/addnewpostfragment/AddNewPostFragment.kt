package com.example.dependencyinjection.ui.addnewpostfragment


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dependencyinjection.R
import com.example.dependencyinjection.databinding.FragmentAddNewPostBinding
import com.example.dependencyinjection.model.data.UserPost
import com.example.dependencyinjection.ui.listofposts.ShowList

class AddNewPostFragment : Fragment(R.layout.fragment_add_new_post) {

private lateinit var binding:FragmentAddNewPostBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddNewPostBinding.bind(view)
        binding.post.setOnClickListener {
            if (binding.postBody.text.isNotEmpty()){
                val rnd = (1..10).random()
                val user = UserPost(
                    userId = rnd,
                    id = ShowList.adapterInstance.itemCount + 1,
                    title = "title",
                    body = binding.postBody.text.toString()
                )
                ShowList.instanceOfDBViewModel.insertSingleUserPost(user)
                findNavController().navigate(R.id.action_addNewPostFragment_to_showList)
            }
        }
    }

}