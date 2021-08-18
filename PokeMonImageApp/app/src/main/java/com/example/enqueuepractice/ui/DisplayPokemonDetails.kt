package com.example.enqueuepractice.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.enqueuepractice.R
import com.example.enqueuepractice.model.Characteristics
import com.example.enqueuepractice.services.PokeApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class  DisplayPokemonDetails : AppCompatActivity() {
    //declaration of variable
    private lateinit var position:String
    private lateinit var img:ImageView
    private lateinit var nameField:TextView
    private lateinit var ability:TextView
    private lateinit var moves:TextView
    private lateinit var stats:TextView
    private lateinit var species:TextView
    private lateinit var num:String
    private lateinit var onError:LinearLayout
    private lateinit var reloadButton2: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_pokemon_details)
        //assign variables to views and instantiate other variable
        img = findViewById(R.id.ivPokemon)
        nameField = findViewById(R.id.tvPokeName)
        ability = findViewById(R.id.tvPokeAbility)
        moves = findViewById(R.id.tvMoves)
        stats = findViewById(R.id.tvStats)
        species = findViewById(R.id.tvSpecies)
        onError = findViewById(R.id.loError)
        reloadButton2 = findViewById(R.id.btReload2)


        //get values from intent
        position = intent.getStringExtra("positionAdapter")!!
        num = intent.getStringExtra("position")!!

        //function call to get image from server
        getImage()

        //function call to get details of pokemon from api
        fetchCharacterData()

        reloadButton2.setOnClickListener {
            getImage()
         fetchCharacterData()
            onError.visibility = View.GONE
        }
    }

    //function to get image from server
    private fun getImage() {
        Glide.with(this)
            .load("https://img.pokemondb.net/artwork/large/$position.jpg")
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(img)
    }

    //function to fetch character details from api
    private fun fetchCharacterData() {
       val pokeApi = PokeApi.RetrofitBuild()
        //dynamically change url link to server
        val callReference = pokeApi.getPokemonChar("pokemon/${num.toInt() + 1}/")
        callReference.enqueue(object: Callback<Characteristics> {
            override fun onResponse(//on succesful connection to server
                call: Call<Characteristics>,
                response: Response<Characteristics>
            ) {
                val responseBody = response.body()
                if (responseBody != null){
                    //get name of pokemon
                nameField.text = responseBody.name
                    var string = ""
                    responseBody.abilities.indices.forEach { i ->
                        string += responseBody.abilities[i].ability.name
                        if (i < responseBody.abilities.size-1){
                            string += " ,  "
                        }
                    }
                    //get abilities of pokemon
                    ability.text = string
                    var move = ""
                    for(i in responseBody.moves.indices){
                        move += responseBody.moves[i].move.name
                        if (i < responseBody.moves.size-1){
                            move += " ,  "
                        }
                    }
                    //get moves of pokemon
                    moves.text = move
                    var stat = ""
                    for(i in responseBody.stats.indices){
                        stat += responseBody.stats[i].stat.name
                        if (i < responseBody.stats.size-1){
                            stat += " ,  "
                        }
                    }
                    //get stat of pokemon
                    stats.text = stat
                    species.text = responseBody.species.name
                }

            }

            override fun onFailure(call: Call<Characteristics>, t: Throwable) {
                onError.visibility = View.VISIBLE //on failure display error message
            }


        })
    }
}

//https://img.pokemondb.net/artwork/large/$position.jpg
//https://i.ibb.co/DgdM9rQ/pokemon-PNG127.png
//https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$position.png