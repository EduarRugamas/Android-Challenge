package com.thedevexperto.test_android_challenge.View


import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.thedevexperto.test_android_challenge.Adapters.ArticulosLocalesAdapters
import com.thedevexperto.test_android_challenge.Data.DataArticulosLocales
import com.thedevexperto.test_android_challenge.R
import com.thedevexperto.test_android_challenge.Utils.MyToolbar
import com.thedevexperto.test_android_challenge.databinding.ActivityArticulosCreadosBinding
import com.thedevexperto.test_android_challenge.databinding.FormularioArticulosLocalesBinding

class ArticulosCreados : AppCompatActivity() {


    private val lista_articulos_locales: ArrayList<DataArticulosLocales> = ArrayList()
    private val AdapterArticulos = ArticulosLocalesAdapters(lista_articulos_locales)
    private lateinit var binding: ActivityArticulosCreadosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticulosCreadosBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        activity para CRUD de articulos locales

            Toolbar()


            binding.recyArticulosLocales.layoutManager = LinearLayoutManager(this)
            binding.recyArticulosLocales.adapter = AdapterArticulos
            ObtenerArticulosLocales()




    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.agregar_articulo_local, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.agregar_articulo){
            FormularioArticulosLocales()
        }

        return super.onOptionsItemSelected(item)
    }



    private fun GuardarArticuloLocal(Titulo:String, Contenido:String, Autor:String, FechaPublicacion:String  ){

            val dataArticuloLocal = hashMapOf(
                "Titulo" to  Titulo,
                "Contenido" to Contenido,
                "Autor" to Autor,
                "Fecha" to FechaPublicacion
            )

        FirebaseFirestore.getInstance()
            .collection("ArticulosLocales")
            .add(dataArticuloLocal)
            .addOnCompleteListener { result ->
                Toast.makeText(this,"Articulo Registrado Correctamente", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(this,"No Se Pudo Registrar El Articulo", Toast.LENGTH_LONG).show()
                Log.d("Error", it.message.toString())
            }

    }

    private fun FormularioArticulosLocales(){

        val builder = AlertDialog.Builder(this)
        val Vista = FormularioArticulosLocalesBinding.inflate(layoutInflater)
        builder.setView(Vista.root)
        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(R.color.transparente)

        Vista.botonCancelar.setOnClickListener {
            dialog.dismiss()
        }

        Vista.botonGuardar.setOnClickListener {

           val titulo =  Vista.editTitulo.text.toString()
            val contenido = Vista.editContenido.text.toString()
            val autor = Vista.editAutor.text.toString()
            val fecha_publi = Vista.editFecha.text.toString()

            GuardarArticuloLocal(titulo,contenido,autor,fecha_publi)

            dialog.dismiss()
        }


    }

    private fun ObtenerArticulosLocales(){

        FirebaseFirestore.getInstance()
            .collection("ArticulosLocales")
            .get().addOnSuccessListener { result ->
                if (result != null){
                    lista_articulos_locales.clear()
                    for (articulos in result){
                        val id = articulos.id
                        val titulo = articulos.getString("Titulo")
                        val contenido = articulos.getString("Contenido")
                        val autor = articulos.getString("Autor")
                        val fecha = articulos.getString("Fecha")

                        if (titulo != null && contenido != null && autor != null && fecha != null ){
                            lista_articulos_locales.add(DataArticulosLocales(id, titulo,contenido,autor,fecha))
                        }
                    }
                    AdapterArticulos.notifyDataSetChanged()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "No se pudo obtener los datos!!...", Toast.LENGTH_SHORT).show()
                Log.d("Error", it.toString())
            }

        ActualizarRecyclerView()

    }

    private fun Toolbar(){
        MyToolbar().MostrarToolbar(this,"Articulos Locales", true)

        val status_bar : Window = window
        status_bar.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        status_bar.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        status_bar.statusBarColor = ContextCompat.getColor(baseContext, R.color.color_textos_verde)
    }

    private fun ActualizarRecyclerView(){
        FirebaseFirestore.getInstance().collection("ArticulosLocales").addSnapshotListener { result, error ->

            if(error != null ) Toast.makeText(this, "No se pudo actualizar la lista", Toast.LENGTH_SHORT).show()
            lista_articulos_locales.clear()
            result?.forEach {
                val id = it.id
                val titulo = it.getString("Titulo")
                val contenido = it.getString("Contenido")
                val autor = it.getString("Autor")
                val fecha = it.getString("Fecha")

                if (titulo != null && contenido != null && autor != null && fecha != null ){
                    lista_articulos_locales.add(DataArticulosLocales(id, titulo,contenido,autor,fecha))
                }
            }

            AdapterArticulos.notifyDataSetChanged()
        }
    }





}