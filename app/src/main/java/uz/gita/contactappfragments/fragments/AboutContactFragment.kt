package uz.gita.contactappfragments.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import uz.gita.contactappfragments.MainActivity
import uz.gita.contactappfragments.R
import uz.gita.contactappfragments.adapters.PhoneAdapter
import uz.gita.contactappfragments.appdatabase.entities.ContactEntity
import uz.gita.contactappfragments.appdatabase.entities.PhoneNumberEntity
import uz.gita.contactappfragments.viewmodels.AboutContactViewModel
import uz.gita.contactappfragments.viewmodels.impl.AboutContactViewModelImpl

class AboutContactFragment:Fragment(R.layout.fragment_about_contact) {
    private val viewModel: AboutContactViewModel by lazy { ViewModelProvider(this) [AboutContactViewModelImpl::class.java] }

    private val adapter:PhoneAdapter by lazy { PhoneAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val backButton: ImageView = view.findViewById(R.id.back)
        val textName:TextView =view.findViewById(R.id.fullName)
        val image:ImageView =view.findViewById(R.id.imageA)
        val contactEntity=arguments?.getSerializable("contact") as ContactEntity
        val  list:RecyclerView=view.findViewById(R.id.list)
        val  btn:FloatingActionButton=view.findViewById(R.id.addBtn)
        list.adapter=adapter

        viewModel.id=contactEntity.id
        viewModel.getAll()
        viewModel.phoneList.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        btn.setOnClickListener {
            viewModel.insert(PhoneNumberEntity(0,"+998990990998",contactEntity.id))
        }

        textName.text="${contactEntity.firstName} ${contactEntity.lastName}"
        image.setImageResource(contactEntity.imageView)
        backButton.setOnClickListener { viewModel.back() }

        viewModel.backLiveData.observe(viewLifecycleOwner, backObserver)


    }

    private val backObserver = Observer<Unit> {
        (activity as MainActivity).popUp()
    }
}