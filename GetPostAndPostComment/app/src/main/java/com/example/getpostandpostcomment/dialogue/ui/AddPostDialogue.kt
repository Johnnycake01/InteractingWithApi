package com.example.getpostandpostcomment.dialogue.ui


import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.getpostandpostcomment.R
import com.example.getpostandpostcomment.dialogue.action.DialogueListenerInterface

class AddPostDialogue: AppCompatDialogFragment() {
    private lateinit var title:EditText
    private lateinit var body:EditText
    private lateinit var listener:DialogueListenerInterface
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as DialogueListenerInterface
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
       val builder = activity?.let { AlertDialog.Builder(it) }
        val inflater = activity?.layoutInflater
        val view = inflater?.inflate(R.layout.open_dialoge_box,null)
        title = view?.findViewById(R.id.postTitle)!!
        body = view.findViewById(R.id.postBody)
        builder?.setView(view)
            ?.setTitle("Add New Post")
            ?.setNegativeButton("cancel"
            ) { _, _ ->

            }
            ?.setPositiveButton("add Post"
            ) { dialogueInterface, i ->
                val postTitle = title.text.toString()
                val postBody = body.text.toString()
                listener.onClickOfAddPostButton(postTitle,postBody)

            }
        return builder!!.create()
    }
}