package com.example.dependencyinjection.ui.addcommentfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dependencyinjection.R
import com.example.dependencyinjection.databinding.FragmentAddNewCommentBlankBinding
import com.example.dependencyinjection.model.UserViewModel
import com.example.dependencyinjection.model.data.Comments
import com.example.dependencyinjection.model.database.PostDBViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNewCommentBlankFragment : Fragment(R.layout.fragment_add_new_comment_blank) {
    private lateinit var binding:FragmentAddNewCommentBlankBinding
    private val viewModelInstance:UserViewModel by viewModels()
    private lateinit var dbViewModel: PostDBViewModel
    private lateinit var adapter: AddNewCommentRvAdapter



    private val args by navArgs<AddNewCommentBlankFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddNewCommentBlankBinding.bind(view)
        dbViewModel = ViewModelProvider(this).get(PostDBViewModel::class.java)
        binding.tvUserPostBodyFrom.text = args.userPostObject.body
        binding.tvUserIdFrom.text = args.userDetailObject.name
        binding.rvUserImageFrom.setBackgroundResource(args.image)

        binding.listOfCommentRecyclerViewFrom.layoutManager = LinearLayoutManager(requireContext())
        adapter = AddNewCommentRvAdapter()
        dbViewModel.readAllComments(args.userPostObject.userId).observe(viewLifecycleOwner,{
            adapter.addAllCommentsToList(it)
            binding.listOfCommentRecyclerViewFrom.adapter = adapter
        })
        binding.addCommentBT.setOnClickListener {
            if (binding.addCommentEditBox.text.isNotEmpty()||
                binding.addCommentEditBox.text.isNotBlank()){
                val rnd = (600..10000).random()
                  val  newComment = Comments(
                        postId = args.userPostObject.userId,
                        id =  rnd ,
                        name = "Oyesina Johnson",
                        body = binding.addCommentEditBox.text.toString()
                    )
                dbViewModel.insertSingleCommentToDatabase(newComment)
                adapter.addtoList(newComment)
                binding.addCommentEditBox.text.clear()
            }
        }



        viewModelInstance.fetchAllComments()
        viewModelInstance.commentValue.observe(viewLifecycleOwner,{
           // Log.d(TAG, "onViewCreated: $it")
            dbViewModel.insertAllCommentsToDB(it)
        })
        //binding.commentCountFrom.text = adapter.itemCount.toString()
        binding.likeCountFrom.text = (0..20).random().toString()
    }
}