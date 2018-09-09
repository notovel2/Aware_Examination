package com.examination.aware_examination

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val data = ArrayList<String>()
    private val duration = Toast.LENGTH_SHORT


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewManager = LinearLayoutManager(this)
        viewAdapter = BlocklistAdapter(data,this)

        recyclerView = findViewById<RecyclerView>(R.id.blocklistRecycleView).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }

        addBtn.setOnClickListener {

            val phoneText = phoneEditText.text
            if(isValidPhoneNumber(phoneText.toString())) {

                    data.add(phoneEditText.text.toString())
                    phoneEditText.setText("")
                    viewAdapter.notifyDataSetChanged()

            }


        }
    }

    private fun isValidPhoneNumber(phoneText:String):Boolean{
        var textArray = ArrayList<String>()
        var isValid = true

        if(phoneText.isNullOrBlank() || phoneText.length != 10 ) {
            textArray.add("have 10 digits")
            isValid = false
        }

        if(!phoneText.startsWith("0")){
            textArray.add("start with 0")
            isValid = false

        }

        if(!isValid) {
            val text = textArray.joinToString(prefix = "Phone number must ", separator = " and ")
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
        }

        return isValid
    }
}
