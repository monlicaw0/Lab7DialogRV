package com.myweb.lab7dialogrv
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_dialog_layout.*
import kotlinx.android.synthetic.main.add_dialog_layout.view.*
import kotlinx.android.synthetic.main.std_item_layout.*

class MainActivity : AppCompatActivity() {
    val studentList  = arrayListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testStudentData()
        recycler_view.adapter = StudentsAdapter(this.studentList,applicationContext)
        recycler_view.layoutManager = LinearLayoutManager(applicationContext) as RecyclerView.LayoutManager?
        recycler_view.itemAnimator = DefaultItemAnimator() as RecyclerView.ItemAnimator?
    }

    fun addStudent(v: View) {

        val mDialogView = LayoutInflater.from(this).inflate(R.layout.add_dialog_layout, null)
        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder(this)
        mBuilder.setView(mDialogView)
        //show dialog
        val  mAlertDialog = mBuilder.show()

        mDialogView.btnAdd.setOnClickListener{
            studentList.add( Student( mDialogView.edt_id.text.toString(), mDialogView.edt_name.text.toString(),
                mDialogView.edt_age.text.toString().toInt()))
            recycler_view.adapter?.notifyDataSetChanged()
            Toast.makeText(applicationContext,"The student is added successfully", Toast.LENGTH_SHORT).show()
            mAlertDialog.dismiss()
        }

        mDialogView.btnCancel.setOnClickListener(){
            mAlertDialog.dismiss()
        }
    }

    fun testStudentData(){
        studentList.add(Student("62000002-1", "Alice", 20))
        studentList.add(Student("62000002-2", "Bob", 21))
    }
}
