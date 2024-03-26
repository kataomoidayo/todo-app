package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID
import com.google.android.material.textfield.TextInputEditText

class DetailTaskActivity : AppCompatActivity() {
    private lateinit var detailTaskViewModel: DetailTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action
        val detailEdTitle = findViewById<TextInputEditText>(R.id.detail_ed_title)
        val detailEdDescription = findViewById<TextInputEditText>(R.id.detail_ed_description)
        val detailEdDueDate = findViewById<TextInputEditText>(R.id.detail_ed_due_date)
        val buttonDeleteTask = findViewById<Button>(R.id.btn_delete_task)

        val factory = ViewModelFactory.getInstance(this)
        detailTaskViewModel = ViewModelProvider(this, factory)[DetailTaskViewModel::class.java]

        val extra = intent.extras
        if (extra != null) {
            val taskId = extra.getInt(TASK_ID)
            detailTaskViewModel.setTaskId(taskId)
            detailTaskViewModel.task.observe(this) { task ->
                if (task != null) {
                    detailEdTitle.setText(task.title)
                    detailEdDescription.setText(task.description)
                    detailEdDueDate.setText(DateConverter.convertMillisToString(task.dueDateMillis))
                }

                buttonDeleteTask.setOnClickListener {
                    detailTaskViewModel.deleteTask()
                    finish()
                }
            }
        }
    }
}