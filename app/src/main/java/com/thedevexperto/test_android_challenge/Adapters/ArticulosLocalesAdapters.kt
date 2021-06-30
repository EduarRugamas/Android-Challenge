package com.thedevexperto.test_android_challenge.Adapters

import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.thedevexperto.test_android_challenge.Data.DataArticulosLocales
import com.thedevexperto.test_android_challenge.R
import com.thedevexperto.test_android_challenge.databinding.FormularioEditarArticuloLocalBinding
import com.thedevexperto.test_android_challenge.databinding.ItemArticulosLocalesBinding

class ArticulosLocalesAdapters constructor(
    private val Lista_Articulos_Locales : ArrayList<DataArticulosLocales>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemArticulosLocalesBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ArticulosLocalesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ArticulosLocalesViewHolder).bind(Lista_Articulos_Locales[position])
    }

    override fun getItemCount(): Int = Lista_Articulos_Locales.size

    inner class ArticulosLocalesViewHolder constructor( itemView: ItemArticulosLocalesBinding) : RecyclerView.ViewHolder(itemView.root){

        val binding = ItemArticulosLocalesBinding.bind(itemView.root)

        fun bind(L_articulos: DataArticulosLocales){
            val idArticulos = L_articulos.idArticulo
            binding.textTitulo.text = L_articulos.Titulo
            binding.textContenido.text = L_articulos.Contenido
            binding.textAuthor.text = L_articulos.Autor
            binding.textFechaPubli.text = L_articulos.Fecha

            binding.butonEditar.setOnClickListener {

                val builder = AlertDialog.Builder(itemView.context)
                val Vista = FormularioEditarArticuloLocalBinding.inflate(LayoutInflater.from(itemView.context))
                builder.setView(Vista.root)
                val dialog = builder.create()
                dialog.show()
                dialog.window?.setBackgroundDrawableResource(R.color.transparente)

                Vista.botonCancelar.setOnClickListener {
                    dialog.dismiss()
                }
                Vista.botonEditar.setOnClickListener {

                   val titulo_nuevo:String = Vista.editarTitulo.text.toString()
                    val contenido_nuevo:String = Vista.editarContenido.text.toString()
                    val autor_nuevo:String = Vista.editarAutor.text.toString()
                    val fecha_nuevo:String = Vista.editarFecha.text.toString()

                    if(titulo_nuevo.isEmpty() && contenido_nuevo.isEmpty() && autor_nuevo.isEmpty() && fecha_nuevo.isEmpty()){
                        Toast.makeText(itemView.context, "Los campos estan vacios", Toast.LENGTH_SHORT).show()

                    }else if (titulo_nuevo.isEmpty() || contenido_nuevo.isEmpty() || autor_nuevo.isEmpty() || fecha_nuevo.isEmpty()){
                        Toast.makeText(itemView.context, "Uno de los campos esta vacio", Toast.LENGTH_SHORT).show()
                    }else {
                        EditarArticulo(idArticulos, titulo_nuevo,contenido_nuevo,autor_nuevo,fecha_nuevo)
                        dialog.dismiss()
                    }

                }

            }

            binding.butonEliminar.setOnClickListener {
                FirebaseFirestore
                    .getInstance()
                    .collection("ArticulosLocales")
                    .document(idArticulos)
                    .delete()
                    .addOnSuccessListener {
                        Toast.makeText(itemView.context, "Articulo eliminado correctamente", Toast.LENGTH_SHORT)
                            .show()
                    }.addOnFailureListener {
                        Toast.makeText(itemView.context, "No se pudo eliminar el articulo", Toast.LENGTH_SHORT)
                            .show()
                    }


                FirebaseFirestore.getInstance().collection("ArticulosLocales").addSnapshotListener { result, error ->

                    if(error != null ) Toast.makeText(itemView.context, "No se pudo actualizar la lista", Toast.LENGTH_SHORT).show()
                    Lista_Articulos_Locales.clear()
                    result?.forEach {
                        val id = it.id
                        val titulo = it.getString("Titulo")
                        val contenido = it.getString("Contenido")
                        val autor = it.getString("Autor")
                        val fecha = it.getString("Fecha")

                        if (titulo != null && contenido != null && autor != null && fecha != null ){
                            Lista_Articulos_Locales.add(DataArticulosLocales(id, titulo,contenido,autor,fecha))
                        }
                    }
                    notifyDataSetChanged()
                }
            }
        }

         fun EditarArticulo(ID_Articulo:String, Titulo: String, Contenido:String, Autor:String, FechaPublicacion:String){

             val ArticuloEditado:HashMap<String, Any> = hashMapOf(
                 "Titulo" to  Titulo,
                 "Contenido" to Contenido,
                 "Autor" to Autor,
                 "Fecha" to FechaPublicacion

             )

             FirebaseFirestore.getInstance()
                 .collection("ArticulosLocales")
                 .document(ID_Articulo)
                 .update(ArticuloEditado)
                 .addOnSuccessListener {
                     Toast.makeText(itemView.context, "Articulo editado correctamente", Toast.LENGTH_SHORT).show()
                 }
                 .addOnFailureListener {
                     Toast.makeText(itemView.context, "No se pudo actualizar el articulo", Toast.LENGTH_SHORT).show()
                 }
         }
    }
}