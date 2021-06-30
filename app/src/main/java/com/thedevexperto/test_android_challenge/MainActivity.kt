package com.thedevexperto.test_android_challenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.thedevexperto.test_android_challenge.Utils.MyToolbar
import com.thedevexperto.test_android_challenge.View.ArticulosAndroid
import com.thedevexperto.test_android_challenge.View.ArticulosCreados
import com.thedevexperto.test_android_challenge.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Toolbar_StatusBar()

        binding.btnVerMasArticulos.setOnClickListener {
            Navegacion(this, ArticulosAndroid::class.java)
        }
        binding.btnVerMasArticulosCreados.setOnClickListener {
            Navegacion(this, ArticulosCreados::class.java)
        }
    }


    private fun Navegacion(MyActivity: AppCompatActivity, SecondActivity: Class<*>?){
        startActivity(Intent(MyActivity, SecondActivity))
    }

    private fun Toolbar_StatusBar(){

        MyToolbar().MostrarToolbar(this, "Inicio",false)


        val status_bar : Window = window
        status_bar.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        status_bar.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        status_bar.statusBarColor = ContextCompat.getColor(baseContext, R.color.color_textos_verde)
    }
}