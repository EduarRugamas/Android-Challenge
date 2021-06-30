package com.thedevexperto.test_android_challenge.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.thedevexperto.test_android_challenge.Adapters.ArticulosAndroidAdapter
import com.thedevexperto.test_android_challenge.Data.Article
import com.thedevexperto.test_android_challenge.Data.DataArticulosAndroid
import com.thedevexperto.test_android_challenge.R
import com.thedevexperto.test_android_challenge.Services.ApiService
import com.thedevexperto.test_android_challenge.Utils.MyToolbar
import com.thedevexperto.test_android_challenge.databinding.ActivityArticulosAndroidBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ArticulosAndroid : AppCompatActivity() {

    private lateinit var binding: ActivityArticulosAndroidBinding
    private val lista_articulos = mutableListOf<Article>()
    private lateinit var adaperArticulos : ArticulosAndroidAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticulosAndroidBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // activity para el consumo de apis con retrofit

        Toolbar()
        recyclerview()
        LlamadaApiEspanishEnglish("es")

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_selector_lenguaje, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.T_ingles){
            LlamadaApiEspanishEnglish("en")
        }else if (item.itemId == R.id.T_espanol){
            LlamadaApiEspanishEnglish("es")
        }

        return super.onOptionsItemSelected(item)
    }

    private fun Toolbar(){
        MyToolbar().MostrarToolbar(this,"Articulos Android", true)
        val status_bar : Window = window
        status_bar.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        status_bar.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        status_bar.statusBarColor = ContextCompat.getColor(baseContext, R.color.color_textos_verde)
    }

    private fun ObtenerRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun recyclerview(){
        adaperArticulos = ArticulosAndroidAdapter(lista_articulos)
        binding.rcArticulosAndroid.layoutManager = LinearLayoutManager(this)
        binding.rcArticulosAndroid.adapter = adaperArticulos
    }

    private fun LlamadaApiEspanishEnglish(Lenguaje:String){
        CoroutineScope(Dispatchers.IO).launch {
            val llamada = ObtenerRetrofit().create(ApiService::class.java)
                .ObtenerArticulos("everything?q=android&sortBy=popularity&language=$Lenguaje&apiKey=d0775d8552df451a870fbfe2b17a93c7")

            val Respuesta = llamada.body()

            runOnUiThread {
                if (llamada.isSuccessful){
                    //Mostrar Recyclerview
                    val listado = Respuesta?.articles
                    lista_articulos.clear()
                    lista_articulos.addAll(listado!!)
                    adaperArticulos.notifyDataSetChanged()
                }else {
                    //datos para el error
                    Toast.makeText(this@ArticulosAndroid, "No se puedo obtener los articulos", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }
}

