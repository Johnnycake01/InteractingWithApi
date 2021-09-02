package com.example.enqueuepractice.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.enqueuepractice.adapter.PokemonListAdapter
import com.example.enqueuepractice.R
import com.example.enqueuepractice.connectivity.ConnectivityLiveData
import com.example.enqueuepractice.utility.TAG


class MainActivity : AppCompatActivity() {
    //declaration of variable
    private lateinit var rv: RecyclerView
    private lateinit var rvAdapter: PokemonListAdapter
    private var offset = 0
    private lateinit var errorText: TextView
    private lateinit var reloadButton: AppCompatButton
    private lateinit var checkButton: AppCompatButton
    private lateinit var editBox:EditText
    private var limit = 500
    private lateinit var connectivityLiveData: ConnectivityLiveData
    private lateinit var progress:ProgressBar
    private lateinit var errorIcon:View



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        connectivityLiveData = ConnectivityLiveData(application)
        //assign variables to views and instantiate other variable
        observeFromLiveData(limit,offset)//function call to get image from api

        editBox = findViewById(R.id.edBox)
        checkButton =findViewById(R.id.ckButton)
        rv = findViewById(R.id.recyclerView)
        errorText = findViewById(R.id.tvNetworkError)
        progress = findViewById(R.id.progressing)
        errorIcon = findViewById(R.id.networkFailedIcon)



        rvAdapter = PokemonListAdapter(this)


        //adapter setting
        rv.adapter = rvAdapter
        rv.setHasFixedSize(true)
        rv.layoutManager = GridLayoutManager(this, 3)

        connectivityLiveData.observe(this, Observer { isAvailable ->
            //2
            when (isAvailable) {
                true -> {
                    errorIcon.visibility = View.GONE
                    errorText.visibility = View.GONE

                   // progressImage.visibility = View.GONE
                    observeFromLiveData(limit,offset)//function call to get image from api
                }
                false -> {
                    progress.visibility = View.GONE
                    errorIcon.visibility = View.VISIBLE
                    errorText.visibility = View.VISIBLE
                }
            }
        })




        checkButton.setOnClickListener {

            if (editBox.text.isNotBlank()){
                limit = editBox.text.toString().toInt()
            }

            observeFromLiveData(limit, offset)

        }
    }

    //function to get image from api
  private  fun observeFromLiveData(limit:Int,offset:Int){
      val instanceOfRetrofitViewModelBuilder = ViewModelProvider(this)[RetrofitViewModelBuilder::class.java]
        instanceOfRetrofitViewModelBuilder.fetchPokemonData(limit,offset)
        instanceOfRetrofitViewModelBuilder.instanceOfMutableLiveData.observe(this){
            if (it != null){
                //get list of result
                val arrayOfResponse = it.results
                //assign list of result to adapter of recycler view to enable adapter
                // display each information on appropriate view
                rvAdapter.additionalListOfPokemon(arrayOfResponse)
                progress.visibility = View.GONE

            }else{
                progress.visibility = View.GONE
                errorIcon.visibility = View.VISIBLE
                errorText.visibility = View.VISIBLE

            }
        }

  }



}





//        GlobalScope.launch(Dispatchers.IO) {
//            val responses = api.getPokemonSus()
//           if (responses.isSuccessful){
//               Log.d("MainActivty", "onCreate: success")
//
//               val responseBody = responses.body()
//               if (responseBody != null){
//                   val arrayOfResponse = responseBody.result
//                   for (i in arrayOfResponse.indices){
//                       Log.d(TAG, "i.name")
//                       Log.d(TAG, "i.url")
//                       //Toast.makeText(this@MainActivity,"hello world",Toast.LENGTH_SHORT).show()
//                   }
//               }
//               withContext(Dispatchers.Main){
//                   Toast.makeText(this@MainActivity,"error ",Toast.LENGTH_SHORT).show()
//            }
//
//           }else{
//               Log.d("MainActivty", "onCreate: failed")
//
//           }
//
//        }