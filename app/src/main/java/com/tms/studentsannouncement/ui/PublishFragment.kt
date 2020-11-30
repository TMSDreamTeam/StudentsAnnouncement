package com.tms.studentsannouncement.ui

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.tms.studentsannouncement.*
import kotlinx.coroutines.channels.consumesAll
import java.util.*


class PublishFragment : Fragment() {
    private lateinit var textViewTitle:TextInputEditText
    private lateinit var textViewDescription:TextInputEditText
    private lateinit var textViewConstants:TextInputEditText
    private lateinit var textViewPrice:TextInputEditText
    private lateinit var inputLayoutTitle:TextInputLayout
    private lateinit var inputLayoutDescription:TextInputLayout
    private lateinit var inputLayoutConstants:TextInputLayout
    private lateinit var inputLayoutPrice:TextInputLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_publish, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        if (Repository.selectedAnnouncement!=null) {
            Repository.selectedAnnouncement?.let {
                textViewTitle.setText(it.title)
                textViewDescription.setText(it.description)
                textViewConstants.setText(it.contacts)
                textViewPrice.setText(it.price.toString())
            }
        }
            view.findViewById<Button>(R.id.fragment_publish_button_publish).setOnClickListener{
                if (!checkField())return@setOnClickListener
                val announcement=Announcement(
                    id=Repository.selectedAnnouncement?.id?: UUID.randomUUID().toString(),
                    title = textViewTitle.text.toString(),
                    description = textViewDescription.text.toString(),
                    price = textViewPrice.text.toString().toDouble(),
                    contacts = textViewConstants.text.toString()
                )
                if (Repository.selectedAnnouncement==null) {
                    Repository.writeNewAnnouncement(announcement)

                }else{
                    Repository.updateAnnouncement(announcement)

                }
                Toast.makeText(context, "Обьявление было опубликованно", Toast.LENGTH_SHORT).show()
                (context as Activity).finish()
            }
    }

    private fun initViews(view: View) {
        textViewTitle=view.findViewById(R.id.fragment_publish_title_edit_text)
        textViewTitle.doOnTextChanged() { _: CharSequence?, _: Int, _: Int, _: Int ->
            inputLayoutTitle.error=null
        }
        textViewDescription=view.findViewById(R.id.fragment_publish_description_edit_text)
        textViewDescription.doOnTextChanged() { _: CharSequence?, _: Int, _: Int, _: Int ->
            inputLayoutDescription.error=null
        }
        textViewConstants=view.findViewById(R.id.fragment_publish_contacts_edit_text)
        textViewConstants.doOnTextChanged {_: CharSequence?, _: Int, _: Int, _: Int ->
            inputLayoutConstants.error=null
        }
        textViewPrice=view.findViewById(R.id.fragment_publish_price_edit_text)
        textViewPrice.doOnTextChanged {_: CharSequence?, _: Int, _: Int, _: Int ->
            inputLayoutConstants.error=null
        }
        inputLayoutTitle=view.findViewById(R.id.fragment_publish_title_input_layout)
        inputLayoutDescription=view.findViewById(R.id.fragment_publish_description_input_layout)
        inputLayoutConstants=view.findViewById(R.id.fragment_publish_contacts_input_layout)
        inputLayoutPrice=view.findViewById(R.id.fragment_publish_price_input_layout)
    }

    private fun checkField():Boolean{
        var isFieldsAreCorrect=true
        if (textViewTitle.text.isNullOrBlank()){
            isFieldsAreCorrect=false
            inputLayoutTitle.error=getString(R.string.this_flield_must_not_be_empty)
        }
        if (textViewDescription.text.isNullOrBlank()){
            isFieldsAreCorrect=false
            inputLayoutDescription.error=getString(R.string.this_flield_must_not_be_empty)
        }
        if (textViewConstants.text.isNullOrBlank()){
            isFieldsAreCorrect=false
            inputLayoutConstants.error=getString(R.string.this_flield_must_not_be_empty)
        }
        if (textViewPrice.text.isNullOrBlank()){
            isFieldsAreCorrect=false
            inputLayoutPrice.error=getString(R.string.this_flield_must_not_be_empty)
        }
        return isFieldsAreCorrect
    }



}