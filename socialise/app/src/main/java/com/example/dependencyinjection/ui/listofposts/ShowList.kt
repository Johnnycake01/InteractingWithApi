package com.example.dependencyinjection.ui.listofposts

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dependencyinjection.R
import com.example.dependencyinjection.databinding.FragmentShowListBinding
import com.example.dependencyinjection.model.UserViewModel
import com.example.dependencyinjection.model.data.UserPost
import com.example.dependencyinjection.model.database.PostDBViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import com.example.dependencyinjection.utils.ConnectivityLiveData
import com.example.dependencyinjection.utils.checkNetwork


@AndroidEntryPoint
class ShowList : Fragment(R.layout.fragment_show_list) {
    private lateinit var binding: FragmentShowListBinding
    private val userViewModel: UserViewModel by viewModels()
    companion object{
        lateinit var instanceOfDBViewModel: PostDBViewModel
        lateinit var adapterInstance:ListOfPostAdapter
    }
//private lateinit var connectivityLiveData: ConnectivityLiveData

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShowListBinding.bind(view)
       // connectivityLiveData = ConnectivityLiveData()
        adapterInstance = ListOfPostAdapter()
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        setHasOptionsMenu(true)
        binding.rvListOfPost.layoutManager = LinearLayoutManager(requireContext())
        instanceOfDBViewModel = ViewModelProvider(this).get(PostDBViewModel::class.java)

        binding.rvUserImage.setBackgroundResource(R.drawable.mathilde_langevin_unsplash_small)
        binding.editTextNewPost.setOnClickListener {

         findNavController().navigate(R.id.action_showList_to_addNewPostFragment)

        }

        //checkNetwork(connectivityLiveData,this) { userViewModel.fetchUserDataFromApi() }
        userViewModel.fetchUserDataFromApi()
        userViewModel.data.observe(viewLifecycleOwner,{
            instanceOfDBViewModel.insertNewPost(it)
        })

        userViewModel.fetchUserDetails()
        userViewModel.userDetail.observe(viewLifecycleOwner,{
            instanceOfDBViewModel.insertAllUser(it)
        })

        instanceOfDBViewModel.readAllData.observe(viewLifecycleOwner,{
            if (it != null){
                adapterInstance.addAllItemToList(it)
                binding.rvListOfPost.adapter = adapterInstance

            }
        })


        instanceOfDBViewModel.readAllUserDetail.observe(viewLifecycleOwner,{
            if (it != null){
                adapterInstance.addAllUserDetailsToList(it)
                binding.rvListOfPost.adapter = adapterInstance

            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as AppCompatActivity?)!!.menuInflater.inflate(R.menu.search_menu,menu)
        val searchIcon = menu.findItem(R.id.searchView)
        val searchViewAction = searchIcon.actionView as SearchView
        searchViewAction.isSubmitButtonEnabled = true

        searchViewAction.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null){
                    if (newText.isNotBlank() || newText.isNotEmpty()){
                       instanceOfDBViewModel.readAllData.observe(viewLifecycleOwner,{
                          // Log.d(TAG, "onQueryTextChange: $it")
                           if (it != null){
                               val temp:MutableList<UserPost> = mutableListOf()
                               for (i in it){
                                   if (i.body.contains(newText)){
                                       temp.add(i)
                                   }
                               }
                               adapterInstance.addAllItemToList(temp)
                               binding.rvListOfPost.adapter = adapterInstance
                               temp.clear()

                           }
                       })
                    }else{
                        instanceOfDBViewModel.readAllData.observe(viewLifecycleOwner,{

                            if (it != null){
                                adapterInstance.addAllItemToList(it)
                                binding.rvListOfPost.adapter = adapterInstance

                            }
                        })
                    }
                }
               return true
            }

        })
    }
}