package com.frcoding.contactsmanagerrecap

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.frcoding.contactsmanagerrecap.databinding.ActivityMainBinding
import com.frcoding.contactsmanagerrecap.room.User
import com.frcoding.contactsmanagerrecap.viewUI.MyRecyclerViewAdapter
import com.frcoding.contactsmanagerrecap.viewmodel.UserViewModel
import com.frcoding.contactsmanagerrecap.viewmodel.UserViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel

    override val kodein by closestKodein()
    private val factory: UserViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //Room database
//        val dao = UserDatabase.getInstance(application).userDAO
//        val repository = UserRepository(dao)
//        val factory = UserViewModelFactory(repository)

        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)

        binding.userViewModel = userViewModel

        binding.lifecycleOwner =this

        initRecyclerView()

    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        displayUsersList()
    }

    private fun displayUsersList() {
        userViewModel.users.observe(this, Observer {
            binding.recyclerView.adapter = MyRecyclerViewAdapter(
                it, {selectedItem: User -> listItemClicked(selectedItem)}
            )
        })
    }

    private fun listItemClicked(selectedItem: User) {
        Toast.makeText(this, "Selected name is ${selectedItem.name}", Toast.LENGTH_SHORT).show()

        userViewModel.initUpdateAndDelete(selectedItem)
    }
}