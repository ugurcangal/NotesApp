package com.cangaldev.notesapp.ui.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.cangaldev.notesapp.model.Notes
import com.cangaldev.notesapp.R
import com.cangaldev.notesapp.ViewModel.NotesViewModel
import com.cangaldev.notesapp.databinding.FragmentCreateNotesBinding
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import java.util.*



class CreateNotesFragment : Fragment() {


    lateinit var binding:FragmentCreateNotesBinding
    var priority: String = "1"

    val viewModel : NotesViewModel by viewModels()
    //ca-app-pub-3485549431455414/2899834464

    private var mInterstitialAd: InterstitialAd? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        loadInterstitial()


        binding = FragmentCreateNotesBinding.inflate(layoutInflater,container,false)

        binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)

        binding.pGreen.setOnClickListener {
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }
        binding.pYellow.setOnClickListener {
            priority = "2"
            binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }
        binding.pRed.setOnClickListener {
            priority = "3"
            binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }

        binding.btnSaveNotes.setOnClickListener {
            createNotes(it)
        }


        return binding.root
    }

    private fun loadInterstitial() {
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(requireContext(),"ca-app-pub-3485549431455414/3036399980", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
            }
        })
    }

    private fun showInter() {
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(requireActivity())
        }
    }


    private fun createNotes(it: View?) {

        val title = binding.edtTitle.text.toString()
        val subTitle = binding.edtSubtitle.text.toString()
        val notes = binding.edtNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = android.text.format.DateFormat.format("d MMMM, yyyy", d.time)

        val data = Notes(null,title = title, subTitle = subTitle,notes = notes,date = notesDate.toString(),priority)
        viewModel.addNotes(data)

        Toast.makeText(requireContext(),"Notes Created Successfully",Toast.LENGTH_LONG).show()
        showInter()
        Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragment_to_homeFragment)



    }




}