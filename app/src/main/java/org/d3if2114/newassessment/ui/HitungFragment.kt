package org.d3if2114.newassessment.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if2114.newassessment.R
import org.d3if2114.newassessment.databinding.FragmentHitungBinding
import org.d3if2114.newassessment.db.LuasDb
import org.d3if2114.newassessment.model.HasilLuas
import org.d3if2114.newassessment.model.KategoriLuas
import org.d3if2114.newassessment.ui.hitung.HitungViewModelFactory


class HitungFragment : Fragment() {
    private lateinit var binding: FragmentHitungBinding

    private val viewModel: HitungViewModel by lazy {
        val db = LuasDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener { hitungBmi() }
        binding.saranButton.setOnClickListener { viewModel.mulaiNavigasi() }
        viewModel.getHasilLuas().observe(requireActivity(), { showResult(it) })
        viewModel.getNavigasi().observe(viewLifecycleOwner, {
            if (it == null) return@observe
            findNavController().navigate(HitungFragmentDirections
                .actionHitungFragmentToSaranFragment(it))
            viewModel.selesaiNavigasi()
        })
    }

    private fun hitungBmi() {
        val panjang = binding.panjangEdt.text.toString()
        if (TextUtils.isEmpty(panjang)) {
            Toast.makeText(context, R.string.panjang_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val lebar = binding.lebarEdt.text.toString()
        if (TextUtils.isEmpty(lebar)) {
            Toast.makeText(context, R.string.lebar_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val panjangCm = panjang.toFloat()
        val lebarCm = lebar.toFloat()
        val luas = panjangCm * lebarCm
        val kategori = getKategori(luas)
        viewModel.hitungLuas(panjangCm, lebarCm)
    }

    private fun getKategori(luas: Float): KategoriLuas {
        return when {
            luas > 100 -> KategoriLuas.BESAR
            else -> KategoriLuas.KECIL
        }
    }

    private fun getKategoriLabel(kategori: KategoriLuas): String {
        val stringRes = when (kategori) {
            KategoriLuas.KECIL -> R.string.kecil
            KategoriLuas.BESAR -> R.string.besar
        }
        return getString(stringRes)
    }

    private fun showResult(result: HasilLuas?) {
        if (result == null) return
        binding.luasTextView.text = getString(R.string.bmi_x, result.luas)
        binding.kategoriTextView.text =
            getString(R.string.kategori_x, getKategoriLabel(result.kategori))
        binding.saranButton.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_about){
            findNavController().navigate(R.id.action_hitungFragment_to_aboutFragment)
            return true
        }else if (item.itemId == R.id.menu_histori){
            findNavController().navigate(R.id.action_hitungFragment_to_historiFragment)
        }
        return super.onOptionsItemSelected(item)
    }
}

