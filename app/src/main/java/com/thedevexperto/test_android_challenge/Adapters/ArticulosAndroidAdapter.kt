package com.thedevexperto.test_android_challenge.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thedevexperto.test_android_challenge.Data.Article
import com.thedevexperto.test_android_challenge.Data.DataArticulosAndroid
import com.thedevexperto.test_android_challenge.R
import com.thedevexperto.test_android_challenge.databinding.CustomAlertDetallesArticulosBinding
import com.thedevexperto.test_android_challenge.databinding.FormularioArticulosLocalesBinding
import com.thedevexperto.test_android_challenge.databinding.ItemArticulosBinding

class ArticulosAndroidAdapter constructor(
    private val Lista_articulos_Android : MutableList<Article>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_articulos, parent, false)

        return ArticulosViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ArticulosViewHolder).bind(Lista_articulos_Android[position])
    }

    override fun getItemCount(): Int = Lista_articulos_Android.size

    inner class ArticulosViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = ItemArticulosBinding.bind(itemView)
        fun bind(LArticulos: Article){
            Glide.with(itemView.context).load(LArticulos.urlToImage).into(binding.imagenApi)

            binding.textTitulo.text = LArticulos.title
            binding.textFechaPubli.text = LArticulos.publishedAt

            binding.btnVerMasArticulosAndroid.setOnClickListener {
                val builder = AlertDialog.Builder(itemView.context)
                val Vista = CustomAlertDetallesArticulosBinding.inflate(LayoutInflater.from(itemView.context))
                builder.setView(Vista.root)
                val dialog = builder.create()
                dialog.show()
                dialog.window?.setBackgroundDrawableResource(R.color.transparente)

                Vista.alertTextTitulo.text = LArticulos.title
                Glide.with(itemView.context).load(LArticulos.urlToImage).into(Vista.alertImagenApi)
                Vista.alertTextContenido.text = LArticulos.content
                Vista.alertTextFechaPubli.text = LArticulos.publishedAt
                Vista.alertTextAutor.text = LArticulos.author

            }
        }
    }
}