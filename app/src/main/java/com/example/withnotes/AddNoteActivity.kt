package com.example.withnotes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.withnotes.Models.Note
import com.example.withnotes.databinding.ActivityAddNoteBinding
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date
import java.util.logging.SimpleFormatter

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding:ActivityAddNoteBinding
    private lateinit var note: Note
    private lateinit var old_note : Note
    var isUpdate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try{
            old_note = intent.getSerializableExtra("current_note") as Note
            binding.title.setText(old_note.title)
            binding.note.setText(old_note.note)
            isUpdate = true

        }catch (e : Exception){
            e.printStackTrace()
        }

        binding.check.setOnClickListener {
            val title = binding.title.text.toString()
            val note_desc = binding.note.text.toString()

            if(title.isNotEmpty() || note_desc.isNotEmpty()){
                val formatter  = SimpleDateFormat("EEE,d MMM yy")

                if(isUpdate){
                    note = Note(
                        old_note.id, title,note_desc,formatter.format(Date())
                    )
                }else{
                    note = Note(
                        null,title,note_desc,formatter.format(Date())
                    )
                }
                val intent = Intent()
                intent.putExtra("note",note)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }else{
                Toast.makeText(this@AddNoteActivity,"Please enter some data",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
        binding.imageButton.setOnClickListener{
            onBackPressed()
        }

    }
}