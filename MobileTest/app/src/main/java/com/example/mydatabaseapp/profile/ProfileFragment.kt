package com.example.mydatabaseapp.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.mydatabaseapp.R
import com.example.mydatabaseapp.database.LoginDatabase
import com.example.mydatabaseapp.database.LoginRepository



class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ProfileHomeFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.profile_home_fragment, container, false
        )

        val application = requireNotNull(this.activity).application

        val dao = LoginDatabase.getInstance(application).loginDatabaseDao

        val repository = LoginRepository(dao)

        val factory = ProfileViewModelFactory(repository, application)

        profileViewModel = ViewModelProvider(this, factory).get(ProfileViewModel::class.java)

        binding.myViewModel = profileViewModel

        binding.lifecycleOwner = this
        
        profileViewModel.navigateto.observe(this, Observer { hasFinished->
            if (hasFinished == true){
                Log.i("MYTAG","insidi observe")
                displayUsersList()
                profileViewModel.doneNavigating()
            }
        })

        profileViewModel.userDetailsLiveData.observe(this, Observer {
            Log.i("MYTAG",it.toString()+"000000000000000000000000")
        })


        profileViewModel.errotoast.observe(this, Observer { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                profileViewModel.donetoast()
            }
        })

        profileViewModel.errotoastEmail.observe(this, Observer { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "UserName Already taken", Toast.LENGTH_SHORT).show()
                profileViewModel.donetoastEmail()
            }
        })

        return binding.root
    }

    private fun displayUsersList() {
        Log.i("MYTAG","insidisplayUsersList")
        val action = ProfileFragmentDirections.actionProfileFragmentToLoginFragment()
        NavHostFragment.findNavController(this).navigate(action)

    }

}