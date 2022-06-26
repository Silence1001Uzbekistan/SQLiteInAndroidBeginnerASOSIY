package uz.silence.sqliteinandroidbeginnercontactapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import uz.silence.sqliteinandroidbeginnercontactapp.Adapters.ContactAdapter
import uz.silence.sqliteinandroidbeginnercontactapp.CLASS.Contact
import uz.silence.sqliteinandroidbeginnercontactapp.DB.MyDbHelper
import uz.silence.sqliteinandroidbeginnercontactapp.databinding.ActivityMainBinding
import uz.silence.sqliteinandroidbeginnercontactapp.databinding.MyDialogBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var myDbHelper: MyDbHelper
    lateinit var contactAdapter: ContactAdapter

    lateinit var list: ArrayList<Contact>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myDbHelper = MyDbHelper(this)

        list = myDbHelper.getAllContacts()
        contactAdapter = ContactAdapter(list, object : ContactAdapter.OnItemClickListener {
            override fun onItemClick(contact: Contact, position: Int, imageView: ImageView) {

                val popupMenu = PopupMenu(this@MainActivity, imageView)
                popupMenu.inflate(R.menu.popup_menu)

                popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                    override fun onMenuItemClick(p0: MenuItem?): Boolean {

                        val itemId = p0?.itemId

                        when (itemId) {

                            R.id.editId -> {

                                val dialog = AlertDialog.Builder(this@MainActivity)
                                val myDialogBinding =
                                    MyDialogBinding.inflate(layoutInflater, null, false)

                                myDialogBinding.nameEt.setText(contact.name)
                                myDialogBinding.phoneEt.setText(contact.phoneNumber)

                                dialog.setView(myDialogBinding.root)

                                dialog.setPositiveButton("Edit",
                                    object : DialogInterface.OnClickListener {
                                        override fun onClick(p0: DialogInterface?, p1: Int) {

                                            contact.name = myDialogBinding.nameEt.text.toString()
                                            contact.phoneNumber =
                                                myDialogBinding.phoneEt.text.toString()

                                            myDbHelper.updateContact(contact)
                                            list[position] = contact
                                            contactAdapter.notifyItemChanged(position)

                                        }

                                    })

                                dialog.show()


                            }

                            R.id.deleteId -> {

                                myDbHelper.deleteContact(contact)
                                list.remove(contact)
                                contactAdapter.notifyItemRemoved(list.size)
                                contactAdapter.notifyItemRangeChanged(position, list.size)

                            }

                        }

                        return true

                    }

                })

                popupMenu.show()

            }

            override fun onItemContactClick(contact: Contact) {

                val intent = Intent(this@MainActivity, SecondActivity::class.java)

                intent.putExtra("id", contact.id)

                startActivity(intent)

            }
        })
        binding.rv.adapter = contactAdapter


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.my_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val itemId = item.itemId

        when (itemId) {

            R.id.addId -> {

                val dialog = AlertDialog.Builder(this)
                val myDialogBinding = MyDialogBinding.inflate(layoutInflater, null, false)
                dialog.setView(myDialogBinding.root)

                dialog.setPositiveButton("Save", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {

                        val name = myDialogBinding.nameEt.text.toString()
                        val phone = myDialogBinding.phoneEt.text.toString()

                        val contact = Contact(name, phone)

                        myDbHelper.addContact(contact)
                        list.add(contact)
                        contactAdapter.notifyItemInserted(list.size)

                    }

                })

                dialog.show()

            }

        }

        return super.onOptionsItemSelected(item)
    }

}