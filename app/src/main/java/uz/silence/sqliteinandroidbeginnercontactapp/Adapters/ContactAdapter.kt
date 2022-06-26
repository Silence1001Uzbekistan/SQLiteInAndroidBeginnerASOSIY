package uz.silence.sqliteinandroidbeginnercontactapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import uz.silence.sqliteinandroidbeginnercontactapp.CLASS.Contact
import uz.silence.sqliteinandroidbeginnercontactapp.databinding.ItemContactBinding

class ContactAdapter(var list: ArrayList<Contact>, var onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<ContactAdapter.Vh>() {

    inner class Vh(var itemContactBinding: ItemContactBinding) :
        RecyclerView.ViewHolder(itemContactBinding.root) {

        fun onBind(contact: Contact, position: Int) {

            itemContactBinding.name.text = contact.name
            itemContactBinding.phone.text = contact.phoneNumber

            itemContactBinding.img.setOnClickListener {

                onItemClickListener.onItemClick(contact, position, itemContactBinding.img)

            }

            itemContactBinding.root.setOnClickListener {

                onItemClickListener.onItemContactClick(contact)

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickListener {

        fun onItemClick(contact: Contact, position: Int, imageView: ImageView)
        fun onItemContactClick(contact: Contact)

    }

}