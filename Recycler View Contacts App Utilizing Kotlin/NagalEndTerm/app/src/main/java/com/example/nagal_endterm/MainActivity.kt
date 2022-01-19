package com.example.nagal_endterm

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.ArrayList


class MainActivity : AppCompatActivity() {
    var arrContacts: ArrayList<ContactModel> = ArrayList<ContactModel>()
    var adapter: RecyclerContactAdapter? = null
    private lateinit var btnOpenDialog: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerContact)
        btnOpenDialog = findViewById(R.id.btnOpenDialog)
        btnOpenDialog.setOnClickListener(View.OnClickListener {
            val dialog = Dialog(this@MainActivity)
            dialog.setContentView(R.layout.add_update_lay)
            val edtName = dialog.findViewById<EditText>(R.id.edtName)
            val edtNumber = dialog.findViewById<EditText>(R.id.edtNumber)
            val btnAction = dialog.findViewById<Button>(R.id.btnAction)
            btnAction.setOnClickListener {
                var name = ""
                var number = ""
                if (edtName.text.toString() != "") {
                    name = edtName.text.toString()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Please Enter Contact Name!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                if (edtNumber.text.toString() != "") {
                    number = edtNumber.text.toString()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Please Enter Contact Number!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                arrContacts.add(ContactModel(R.drawable.male, name, number))
                adapter?.notifyItemInserted(arrContacts.size - 1)
                recyclerView.scrollToPosition(arrContacts.size - 1)
                dialog.dismiss()
            }
            dialog.show()
        })
        arrContacts.add(ContactModel(R.drawable.male, "Alvin", "09685768945"))
        arrContacts.add(ContactModel(R.drawable.female, "Beatrice", "09968457534"))
        arrContacts.add(ContactModel(R.drawable.male, "Charlie", "09078150733"))
        arrContacts.add(ContactModel(R.drawable.female, "Deborah", "09666999945"))
        arrContacts.add(ContactModel(R.drawable.male, "Elmo", "094575759394"))
        recyclerView.setLayoutManager(LinearLayoutManager(this))
        adapter = RecyclerContactAdapter(this, arrContacts)
        recyclerView.setAdapter(adapter)
    }
}
