package sg.edu.rp.c346.id22012867.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner spnAddRemove;
    Button btnAdd, btnRemove, btnClear, btnUpdate;
    ListView lvTask;
    EditText etTasks, etPos;
    List<String> taskList;
    ArrayAdapter<String> adapterTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("My To-Do List");

        etTasks = findViewById(R.id.editTasks);
        etPos = findViewById(R.id.editTaskPos);
        spnAddRemove = findViewById(R.id.spinner);
        btnAdd = findViewById(R.id.buttonAddTask);
        btnRemove = findViewById(R.id.buttonRemoveTask);
        btnClear = findViewById(R.id.buttonClearTask);
        btnUpdate = findViewById(R.id.buttonUpdateTask);
        lvTask = findViewById(R.id.listTasks);

        taskList = new ArrayList<>();
        adapterTask = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        lvTask.setAdapter(adapterTask);

        spnAddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        etTasks.setHint("Enter New Task To Be Added");
                        etPos.setHint("");
                        etPos.setEnabled(false);
                        btnAdd.setEnabled(true);
                        btnRemove.setEnabled(false);
                        btnClear.setEnabled(true);
                        btnUpdate.setEnabled(false);
                        break;
                    case 1:
                        etTasks.setHint("");
                        etPos.setHint("Enter The Task Position To Remove");
                        etTasks.setEnabled(false);
                        etPos.setEnabled(true);
                        btnAdd.setEnabled(false);
                        btnRemove.setEnabled(true);
                        btnClear.setEnabled(true);
                        btnUpdate.setEnabled(false);
                        break;
                    case 2:
                        etTasks.setHint("Enter Updated Text");
                        etPos.setHint("Enter position number");
                        etTasks.setEnabled(true);
                        etPos.setEnabled(true);
                        btnAdd.setEnabled(false);
                        btnRemove.setEnabled(false);
                        btnClear.setEnabled(true);
                        btnUpdate.setEnabled(true);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTask = etTasks.getText().toString();
                int position = 0;
                if (!newTask.isEmpty()) {
                    taskList.add(position, newTask);
                    etTasks.setText("");
                    adapterTask.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(MainActivity.this, "There Is No Task To Be Added", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(etPos.getText().toString());
                if (taskList.isEmpty()) {
                    Toast.makeText(MainActivity.this, "You Don't Have Any Tasks To Be Removed", Toast.LENGTH_SHORT).show();
                    
                } else if (position > taskList.size()){
                    Toast.makeText(MainActivity.this, "Wrong Index Number!", Toast.LENGTH_SHORT).show();

                } else {
                    taskList.remove(position - 1);
                    etPos.setText("");
                    adapterTask.notifyDataSetChanged();
                }

            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updateTask = etTasks.getText().toString();
                int position = Integer.parseInt(etPos.getText().toString());
                if (taskList.isEmpty()) {
                    Toast.makeText(MainActivity.this, "You Don't Have Any Tasks To Be Updated", Toast.LENGTH_SHORT).show();

                } else if (position > taskList.size()) {
                    Toast.makeText(MainActivity.this, "Wrong Index Number!", Toast.LENGTH_SHORT).show();

                }else if (updateTask.isEmpty()) {
                    Toast.makeText(MainActivity.this, "There Is No Updated Text", Toast.LENGTH_SHORT).show();
                } else {
                    taskList.set(position - 1, updateTask);
                    etTasks.setText("");
                    etPos.setText("");
                    adapterTask.notifyDataSetChanged();
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskList.clear();
                etTasks.setText("");
                etPos.setText("");
                spnAddRemove.setSelection(0);
                adapterTask.notifyDataSetChanged();
            }
        });
        lvTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String task = taskList.get(position);
                Toast.makeText(MainActivity.this, task, Toast.LENGTH_SHORT).show();
            }
        });


    }
}