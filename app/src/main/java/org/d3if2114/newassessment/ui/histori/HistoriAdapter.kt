package org.d3if2114.newassessment.ui.histori

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if2114.newassessment.R
import org.d3if2114.newassessment.databinding.ItemHistoriBinding
import org.d3if2114.newassessment.db.LuasEntity
import org.d3if2114.newassessment.model.KategoriLuas
import org.d3if2114.newassessment.model.hitungLuas
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter :
    ListAdapter<LuasEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<LuasEntity>() {
                override fun areItemsTheSame(
                    oldData: LuasEntity, newData: LuasEntity
                ): Boolean {
                    return oldData.id == newData.id
                }
                override fun areContentsTheSame(
                    oldData: LuasEntity, newData: LuasEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
            Locale("id", "ID")
        )
        fun bind(item: LuasEntity) = with(binding) {
            val hasilBmi = item.hitungLuas()
            kategoriTextView.text = hasilBmi.kategori.toString().substring(0, 1)
            val colorRes = when(hasilBmi.kategori) {
                KategoriLuas.KECIL -> R.color.kecil
                KategoriLuas.BESAR -> R.color.besar
                else -> R.color.besar
            }
            val circleBg = kategoriTextView.background as GradientDrawable
            circleBg.setColor(ContextCompat.getColor(root.context, colorRes))
            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            bmiTextView.text = root.context.getString(R.string.hasil_x,
                hasilBmi.luas, hasilBmi.kategori.toString())
        }

    }




}
