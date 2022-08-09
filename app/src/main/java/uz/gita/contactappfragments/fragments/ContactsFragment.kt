package uz.gita.contactappfragments.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import me.zhanghai.android.fastscroll.FastScrollerBuilder
import uz.gita.contactappfragments.MainActivity
import uz.gita.contactappfragments.R
import uz.gita.contactappfragments.adapters.ContactAdapter
import uz.gita.contactappfragments.appdatabase.entities.ContactEntity
import uz.gita.contactappfragments.viewmodels.ContactViewModel
import uz.gita.contactappfragments.viewmodels.impl.ContactViewModelImpl

class ContactsFragment:Fragment(R.layout.fragment_contacts) {
    private val viewModel: ContactViewModel by lazy { ViewModelProvider(this)[ContactViewModelImpl::class.java] }
    private val adapter: ContactAdapter by lazy { ContactAdapter() }

    private var deleteList:ArrayList<ContactEntity> = ArrayList()
    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchView:View=view.findViewById(R.id.search_View)
        val cancelBtn:TextView=view.findViewById(R.id.cancel_button)
        val list = view.findViewById<RecyclerView>(R.id.list_item)
        val btnAdd: FloatingActionButton = view.findViewById(R.id.btnAdd)
        val btnDelete: FloatingActionButton = view.findViewById(R.id.btndelete)

        btnDelete.setOnClickListener {
            if (deleteList.size>0){
                viewModel.delete(deleteList)
                adapter.setSelectedState(false)

                btnAdd.visibility=View.VISIBLE
                btnDelete.visibility=View.GONE
                cancelBtn.visibility=View.GONE
                searchView.visibility=View.VISIBLE
            }
        }

        adapter.setItemSelectedStateChangeListener {
            if (it.isChecked) {
                deleteList.add(it)
            } else {
                deleteList.remove(it)
            }

        }

        cancelBtn.setOnClickListener {


            deleteList.forEach {
                it.isChecked = false
            }
            adapter.setSelectedState(false)
            btnAdd.visibility=View.VISIBLE
            btnDelete.visibility=View.GONE
            deleteList.clear()
            cancelBtn.visibility=View.GONE
            searchView.visibility=View.VISIBLE
        }
        list.adapter = adapter

        adapter.onCheceked {
            adapter.setSelectedState(true)
            btnAdd.visibility=View.GONE
            btnDelete.visibility=View.VISIBLE
            searchView.visibility=View.GONE
            cancelBtn.visibility=View.VISIBLE
        }
        FastScrollerBuilder(list).build();
        btnAdd.setOnClickListener {
            dialogAdd(view.context)
       }
        viewModel.contactsLiveData.observe(viewLifecycleOwner, contactsObserver)
        viewModel.openDetailLiveData.observe(this, openDetailObserver)
        adapter.setOnItemClickListener { viewModel.openDetail(it) }
    }

    private val openDetailObserver = Observer<ContactEntity> {
        val aboutContactFragment = AboutContactFragment()
        aboutContactFragment.arguments = Bundle().apply { putSerializable("contact", it) }
        (activity as MainActivity).openFragmentWithBackStack(aboutContactFragment)
    }
    private val contactsObserver = Observer<List<ContactEntity>> { adapter.submitList(it) }


    fun dialogAdd(context: Context){

        val builder = AlertDialog.Builder(context,R.style.CustomAlertDialog).create()
        val view: View = layoutInflater.inflate(R.layout.add_contact,null)
        val inputTitle = view.findViewById<EditText>(R.id.title_add)
        val inputNumber = view.findViewById<EditText>(R.id.textLastName)
        val imageView: ImageView = view.findViewById(R.id.img_dilag)
        val radioGroup: RadioGroup = view.findViewById(R.id.radio_group)
        var res=0
        radioGroup.setOnCheckedChangeListener { g, i ->
            when (i) {
                R.id.male->{
                    res=R.drawable.erkak
                    imageView.setImageResource(res)
                }
                R.id.female->{
                    res=R.drawable.ayol
                    imageView.setImageResource(res)
                }
            }
        }




        val  saqlash=view.findViewById<TextView>(R.id.saqlash)
        val  bekor_qilish=view.findViewById<TextView>(R.id.bekor_qilish)
        builder.setView(view)

        saqlash.setOnClickListener {

            val title=inputTitle.text.toString()
            val number=inputNumber.text.toString()
            if (res>0&&title!=""&&number!=""){
                viewModel.addContact(ContactEntity(0,title,number,res,false))
                builder.cancel()
            }
            else{
                Toast.makeText(context," !!!!", Toast.LENGTH_SHORT).show()
            }


        }
        bekor_qilish.setOnClickListener {

            builder.cancel()
        }

        builder.show()


    }
    fun dialogUpdate(context: Context, data: ContactEntity){

        val builder = AlertDialog.Builder(context,R.style.CustomAlertDialog).create()
        val view: View = layoutInflater.inflate(R.layout.add_contact,null)
        val inputTitle = view.findViewById<EditText>(R.id.title_add)
        val inputNumber = view.findViewById<EditText>(R.id.textLastName)
        val imageView: ImageView = view.findViewById(R.id.img_dilag)
        val radioGroup: RadioGroup = view.findViewById(R.id.radio_group)
        var res=0

        imageView.setImageResource(data.imageView)
        inputTitle.setText(data.firstName)
        inputNumber.setText(data.lastName)

        radioGroup.setOnCheckedChangeListener { g, i ->
            when (i) {
                R.id.male->{
                    res=R.drawable.erkak
                    imageView.setImageResource(res)
                }
                R.id.female->{
                    res=R.drawable.ayol
                    imageView.setImageResource(res)
                }
            }
        }




        val  saqlash=view.findViewById<TextView>(R.id.saqlash)
        val  bekor_qilish=view.findViewById<TextView>(R.id.bekor_qilish)
        builder.setView(view)

        saqlash.setOnClickListener {

            val title=inputTitle.text.toString()
            val number=inputNumber.text.toString()
            if (res>0&&title!=""&&number!=""){
                viewModel.addContact(ContactEntity(data.id,title,number,res,false))
                builder.cancel()
            }
            else{
                Toast.makeText(context," !!!!", Toast.LENGTH_SHORT).show()
            }


        }
        bekor_qilish.setOnClickListener {

            builder.cancel()
        }

        builder.show()


    }
}