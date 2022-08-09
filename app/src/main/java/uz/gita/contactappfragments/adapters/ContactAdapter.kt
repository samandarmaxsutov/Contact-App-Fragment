package uz.gita.contactappfragments.adapters

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.contactappfragments.R
import uz.gita.contactappfragments.appdatabase.entities.ContactEntity
import uz.gita.contactappfragments.utils.inflate


class ContactAdapter : ListAdapter<ContactEntity, ContactAdapter.Holder>(ContactItemCallback){


    private var isSelected=false




    private var itemSelectedStateChangeListener: ((ContactEntity) -> Unit)? = null


    fun setItemSelectedStateChangeListener(block: (ContactEntity) -> Unit) {
        itemSelectedStateChangeListener = block
    }


    private var checkListener:((ContactEntity)->Unit)?=null

    private var listener: ((ContactEntity) -> Unit)? = null
    object ContactItemCallback : DiffUtil.ItemCallback<ContactEntity>() {
        override fun areItemsTheSame(oldItem: ContactEntity, newItem: ContactEntity): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ContactEntity, newItem: ContactEntity): Boolean = oldItem.id == newItem.id
                && oldItem.firstName == newItem.firstName && oldItem.lastName == newItem.lastName

    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {

        val textTitle: TextView = itemView.findViewById(R.id.textFirstName)
        val textLastName: TextView = itemView.findViewById(R.id.textLastName)
        val image:ImageView=itemView.findViewById(R.id.imageS)
        val image1:ImageView=itemView.findViewById(R.id.checked)
        init {

            itemView.setOnLongClickListener{

                checkListener?.invoke(getItem(position))
                it.isLongClickable
            }

            image1.setOnClickListener {
                if (getItem(adapterPosition).isChecked) {
                    image1.setImageResource(R.drawable.ic_baseline_check_box_outline_blank_24)
                } else {
                    image1.setImageResource(R.drawable.ic_baseline_check_box_24)
                }
                getItem(adapterPosition).isChecked = !getItem(adapterPosition).isChecked
                itemSelectedStateChangeListener?.invoke(getItem(adapterPosition))
            }
        }

        fun bind() {
            val item = getItem(adapterPosition)

            if (!isSelected){
                itemView.setOnClickListener { listener?.invoke(getItem(adapterPosition)) }
            }

            textTitle.text = "${item.firstName}"
            textLastName.text="${item.lastName}"

            image.setImageResource(item.imageView)

            if (isSelected){
                image1.visibility=View.VISIBLE
                if (item.isChecked){
                    image1.setImageResource(R.drawable.ic_baseline_check_box_24)
                }
                else{
                    image1.setImageResource(R.drawable.ic_baseline_check_box_outline_blank_24)
                }
            }
            else{
                image1.visibility=View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapter.Holder = Holder(parent.inflate(R.layout.item_contacts))

    override fun onBindViewHolder(holder: ContactAdapter.Holder, position: Int) = holder.bind()

    fun setOnItemClickListener(block: (ContactEntity) -> Unit) {
        listener = block
    }
    fun onRowSelected(holder: Holder) {
       holder.itemView.setBackgroundColor(Color.GRAY)
    }

    fun onRowClear(holder: Holder) {
        holder.itemView.setBackgroundColor(Color.WHITE)
    }

    fun onCheceked(l:(ContactEntity)->Unit){
        checkListener=l
    }

    fun setSelectedState(state: Boolean) {
        isSelected = state
        notifyDataSetChanged()
    }
}