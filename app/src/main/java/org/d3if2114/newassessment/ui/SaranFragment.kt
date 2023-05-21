package org.d3if2114.newassessment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import org.d3if2114.newassessment.R
import org.d3if2114.newassessment.databinding.FragmentSaranBinding
import org.d3if2114.newassessment.model.KategoriLuas

class SaranFragment : Fragment() {
    private lateinit var binding: FragmentSaranBinding
    private val args: SaranFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentSaranBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        updateUI(args.kategori)
    }

    private fun updateUI(kategori : KategoriLuas){
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        when(kategori){
            KategoriLuas.BESAR -> {
                actionBar?.title = getString(R.string.judul_besar)
                binding.tvsaran.text = getString(R.string.saran_besar)
            }
            KategoriLuas.KECIL -> {
                actionBar?.title = getString(R.string.judul_kecil)
                binding.tvsaran.text = getString(R.string.saran_kecil)
            }
        }
    }
}
