package uz.gita.contactappfragments.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.contactappfragments.R
import uz.gita.contactappfragments.appdatabase.entities.ContactEntity
import uz.gita.contactappfragments.appdatabase.entities.PhoneNumberEntity
import uz.gita.contactappfragments.utils.inflate

class PhoneAdapter : ListAdapter<PhoneNumberEntity, PhoneAdapter.Holder>(ContactItemCallback){

    private var listener: ((PhoneNumberEntity) -> Unit)? = null
    object ContactItemCallback : DiffUtil.ItemCallback<PhoneNumberEntity>() {
        override fun areItemsTheSame(oldItem: PhoneNumberEntity, newItem: PhoneNumberEntity): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: PhoneNumberEntity, newItem: PhoneNumberEntity): Boolean = oldItem.id == newItem.id
                && oldItem.number==newItem.number && oldItem.contactId==newItem.contactId
    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {

        init {
            itemView.setOnClickListener { listener?.invoke(getItem(adapterPosition)) }

        }

        fun bind() {
            val item = getItem(adapterPosition)
            val textTitle: TextView = itemView.findViewById(R.id.phone)


            textTitle.text = "${item.number}"

        }
    }



    fun setOnItemClickListener(block: (PhoneNumberEntity) -> Unit) {
        listener = block
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
      return  Holder(parent.inflate(R.layout.phone_item))
    }

    override fun onBindViewHolder(holder: Holder, position: Int)=holder.bind()

}