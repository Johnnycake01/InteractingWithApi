package com.example.dependencyinjection.utils

import android.app.Application
import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.example.dependencyinjection.R
import com.example.dependencyinjection.ui.listofposts.ShowList.Companion.instanceOfDBViewModel
import dagger.hilt.android.qualifiers.ApplicationContext


val BASE_URL = "https://jsonplaceholder.typicode.com/"
val TAG = "mainActivity"
val arrayOfImages:List<Int> = arrayListOf(
    R.drawable.foto_sushi_unsplash_small,
    R.drawable.mathilde_langevin_unsplash_small,
    R.drawable.pexels_andrea_piacquadio_small,
    R.drawable.pexels_cottonbro_4972985_small,
    R.drawable.pexels_daniel_edeke_1370750_small,
    R.drawable.pexels_guy_smiling_small,
    R.drawable.pexels_maria_orlova_4947563_small,
    R.drawable.pexels_pixabay_415829_small,
    R.drawable.pexels_sindre_strm_small,
    R.drawable.pexels_juan_n_gomez_2589653_small
)

fun checkNetwork(connectivityLiveData: ConnectivityLiveData,context: Fragment, function:()->Unit){
    connectivityLiveData.observe(context, { isAvailable ->
        when (isAvailable) {
            true -> {
                function
            }
            false -> {

            }
        }
    })
}



