package com.example.enqueuepractice.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.enqueuepractice.adapter.PokemonListAdapter
import com.example.enqueuepractice.R
import com.example.enqueuepractice.services.PokeApi
import com.example.enqueuepractice.model.PokemonRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates


const val BASE_URL = "https://pokeapi.co/api/v2/"
const val TAG = "mainActivity"
class MainActivity : AppCompatActivity() {
    //declaration of variable
    private lateinit var rv: RecyclerView
    private lateinit var rvAdapter: PokemonListAdapter
    private var offset by Delegates.notNull<Int>()
    private lateinit var errorText: TextView
    private lateinit var reloadButton: AppCompatButton
    private lateinit var checkButton: AppCompatButton
    private lateinit var editBox:EditText
    private var limit = 500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG,"oncreate")
        //assign variables to views and instantiate other variable
        editBox = findViewById(R.id.edBox)
        checkButton =findViewById(R.id.ckButton)
        rv = findViewById(R.id.recyclerView)
        errorText = findViewById(R.id.tvNetworkError)
        reloadButton = findViewById(R.id.btReload)
        rvAdapter = PokemonListAdapter(this)
        offset = 0
        Log.d(TAG,"defalut limit =$limit and offset= $offset")
        //adapter setting
        rv.adapter = rvAdapter
        rv.setHasFixedSize(true)
        rv.layoutManager = GridLayoutManager(this, 3)

        //onclick of reload button
        reloadButton.setOnClickListener {

            fetchPokemonData(limit,offset)
            reloadButton.visibility = View.GONE
            errorText.visibility = View.GONE
        }

        checkButton.setOnClickListener {

            if (editBox.text.isNotBlank()){
                limit = editBox.text.toString().toInt()
            }
            Log.d(TAG,"button clicked limit =$limit and offset= $offset")
            fetchPokemonData(limit, offset)

        }

        fetchPokemonData(limit,offset)//function call to get image from api

    }

    //function to get image from api
    private fun fetchPokemonData(limited:Int, offset: Int) {

        val pokeApi = PokeApi.RetrofitBuild()
        val pokemonRequestCall = pokeApi.getPokemon(limited, offset)

        pokemonRequestCall.enqueue(object : Callback<PokemonRequest> {
            override fun onResponse( //on successful network call
                call: Call<PokemonRequest>,
                response: Response<PokemonRequest>
            ) {
                val responseBody = response.body()
                //assert responseBody not null
                if (responseBody != null) {

                    //get list of result
                    val arrayOfResponse = responseBody.results

                    //assign list of result to adapter of recycler view to enable adapter
                    // display each information on appropriate view
                    rvAdapter.additionalListOfPokemon(arrayOfResponse)
                }
            }

            //on failure
            override fun onFailure(call: Call<PokemonRequest>, t: Throwable) {
                //on failure display error message
                reloadButton.visibility = View.VISIBLE
                errorText.visibility = View.VISIBLE
            }

        })
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