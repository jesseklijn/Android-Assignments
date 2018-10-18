package nl.jesseklijn.androidassignments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

public class AddGame extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText titleText;
    EditText platformText;
    EditText notesText;
    Spinner statusSpinner;
    String spinnerSelected = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Get fields
        titleText = findViewById(R.id.titleEdit);
        platformText = findViewById(R.id.platformEdit);
        notesText = findViewById(R.id.notesEdit);



        //Spinner requirements
        statusSpinner = findViewById(R.id.statusSpinner);
        statusSpinner.setOnItemSelectedListener(this);



        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Status_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        statusSpinner.setAdapter(adapter);

        if(GameManager.gameManagerInstance.isEditing == true) {
            titleText.setText(GameManager.gameManagerInstance.currentlyEditing.title);
            platformText.setText(GameManager.gameManagerInstance.currentlyEditing.platform);
            notesText.setText(GameManager.gameManagerInstance.currentlyEditing.notes);
            statusSpinner.setSelection(adapter.getPosition(GameManager.gameManagerInstance.currentlyEditing.currentStatus));
        }


        //On click event
        FloatingActionButton myFab = this.findViewById(R.id.SaveGameBtn);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                OnClick(v);

            }
        });
    }

    //Saves game on click method
    public void OnClick(View v) {
        if (titleText.getText().toString().isEmpty() == false && platformText.getText().toString().isEmpty() == false && notesText.getText().toString().isEmpty() == false && spinnerSelected.isEmpty() == false) {

            if (GameManager.gameManagerInstance.isEditing == true) {
                saveEdit();
                Toast.makeText(this,"Saving edit",Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this,"Added new item!",Toast.LENGTH_LONG).show();

                addEdit();
            }



            Intent intent = new Intent(this, MainActivity.class);
            this.finish();
            startActivity(intent);


        }
        else{
            Toast.makeText(this,"Please enter all textfields..",Toast.LENGTH_LONG).show();

        }
    }

    public void saveEdit(){
        GameManager.gameManagerInstance.editGameItem(titleText.getText().toString(),platformText.getText().toString(),notesText.getText().toString(),spinnerSelected,"19-10-2018", GameManager.gameManagerInstance.currentlyEditing);
        GameManager.gameManagerInstance.saveManager(this);
        Toast.makeText((this),"Saved..",Toast.LENGTH_LONG).show();

    }
    public void addEdit(){
        GameManager.gameManagerInstance.addGameItem(titleText.getText().toString(),platformText.getText().toString(),notesText.getText().toString(),spinnerSelected,"19-10-2018");
        GameManager.gameManagerInstance.saveManager(this);
        Toast.makeText((this),"Saved..",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    spinnerSelected =  (String) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
