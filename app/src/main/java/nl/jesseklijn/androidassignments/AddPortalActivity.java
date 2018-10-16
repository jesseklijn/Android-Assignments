package nl.jesseklijn.androidassignments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import static nl.jesseklijn.androidassignments.PortalManager.portalManagerInstance;

public class AddPortalActivity extends AppCompatActivity {

    EditText urlText;
    EditText titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_portal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        urlText = findViewById(R.id.urlEditText);
        titleText = findViewById(R.id.titleEditText);
    }


    void createPortal(String url, String title){
        portalManagerInstance.addPortalItem(url, title);

    }

    public void OnClick(View v){
        if(urlText.getText().toString().isEmpty() == false && titleText.getText().toString().isEmpty() == false){
            createPortal(urlText.getText().toString(),titleText.getText().toString());

            transition();
        }
    }

    public void transition(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
