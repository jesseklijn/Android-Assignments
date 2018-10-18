package nl.jesseklijn.androidassignments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements MyListAdapter.ListItemClickListener {

    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(GameManager.gameManagerInstance.gameList == null){


            GameManager.gameManagerInstance.init(this);

        }
        recyclerView = findViewById(R.id.gameRecycler);

        layoutManager = new LinearLayoutManager(this);

        //create adapter
        final MyListAdapter gameListAdapter = new MyListAdapter(GameManager.gameManagerInstance.gameList, this);

        //set adapter
        recyclerView.setAdapter(gameListAdapter);
        //Set layout
        recyclerView.setLayoutManager(layoutManager);

        FloatingActionButton myFab = this.findViewById(R.id.AddGameBtn);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              OnClick(v);

            }
        });

    }

    //Add game method fired when fab button is clicked
    public void OnClick(View v){
        Toast.makeText(this,"Lets add a game!",Toast.LENGTH_LONG).show();

        GameManager.gameManagerInstance.isEditing = false;
        Intent intent = new Intent(this, AddGame.class);
        startActivity(intent);
    }

    @Override
    public void onListItemClick(int position) {
        //You will get position of the item clicked depending on your situation perform a desired action. We are simply Toasting.

        Toast.makeText(this,"Opening Edit",Toast.LENGTH_LONG).show();
        GameManager.gameManagerInstance.currentlyEditing = GameManager.gameManagerInstance.gameList.get(position);
        GameManager.gameManagerInstance.isEditing = true;
        Intent intent = new Intent(this, AddGame.class);
        startActivity(intent);
    }
}
