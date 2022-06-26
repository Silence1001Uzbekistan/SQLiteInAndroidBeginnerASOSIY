package uz.silence.sqliteinandroidbeginnercontactapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.silence.sqliteinandroidbeginnercontactapp.DB.MyDbHelper
import uz.silence.sqliteinandroidbeginnercontactapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    lateinit var myDbHelper: MyDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myDbHelper = MyDbHelper(this)

        val idS = intent.getIntExtra("id", 0)
        val contact = myDbHelper.getContactById(idS)

        binding.nameTv.text = contact.name
        binding.phoneTv.text = contact.phoneNumber


    }
}