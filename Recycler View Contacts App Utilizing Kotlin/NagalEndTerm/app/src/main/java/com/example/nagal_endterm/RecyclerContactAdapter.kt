package com.example.nagal_endterm

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList


class RecyclerContactAdapter internal constructor(
    var context: Context,
    var arrContacts: ArrayList<ContactModel>
) :
    RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.contact_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val model = arrContacts[position]
        holder.imgContact.setImageResource(model.img)
        holder.txtName.text = model.name
        holder.txtNumber.text = model.number
        holder.llRow.setOnClickListener {
            val dialog = Dialog(context)
            dialog.setContentView(R.layout.add_update_lay)
            val edtName = dialog.findViewById<EditText>(R.id.edtName)
            val edtNumber = dialog.findViewById<EditText>(R.id.edtNumber)
            val btnAction = dialog.findViewById<Button>(R.id.btnAction)
            val txtTitle = dialog.findViewById<TextView>(R.id.txtTitle)
            txtTitle.text = "Update Content"
            btnAction.text = "Update"
            edtName.setText(arrContacts[position].name)
            edtNumber.setText(arrContacts[position].number)
            btnAction.setOnClickListener {
                var name = ""
                var number = ""
                if (edtName.text.toString() != "") {
                    name = edtName.text.toString()
                } else {
                    Toast.makeText(context, "Please Enter Contact Name!", Toast.LENGTH_SHORT)
                        .show()
                }
                if (edtNumber.text.toString() != "") {
                    number = edtNumber.text.toString()
                } else {
                    Toast.makeText(context, "Please Enter Contact Number!", Toast.LENGTH_SHORT)
                        .show()
                }
                arrContacts[position] = ContactModel(
                    arrContacts[position].img,
                    name, number
                )
                notifyItemChanged(position)
                dialog.dismiss()
            }
            dialog.show()
        }
        holder.llRow.setOnLongClickListener {
            val builder = AlertDialog.Builder(
                context
            )
                .setTitle("Delete Contact?")
                .setMessage("Are you sure you want to delete this Contact?")
                .setIcon(R.drawable.ic_baseline_delete_forever_24)
                .setPositiveButton(
                    "Yes"
                ) { dialog, which ->
                    arrContacts.removeAt(position)
                    notifyItemRemoved(position)
                }
                .setNegativeButton(
                    "No"
                ) { dialog, which -> }
            builder.show()
            true
        }
    }

    override fun getItemCount(): Int {
        return arrContacts.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtName: TextView
        var txtNumber: TextView
        var imgContact: ImageView
        var llRow: LinearLayout

        init {
            txtName = itemView.findViewById(R.id.txtName)
            txtNumber = itemView.findViewById(R.id.txtNumber)
            imgContact = itemView.findViewById(R.id.imgContact)
            llRow = itemView.findViewById(R.id.llRow)
        }
    }
}
