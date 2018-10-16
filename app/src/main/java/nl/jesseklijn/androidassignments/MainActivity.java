package nl.jesseklijn.androidassignments;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static nl.jesseklijn.androidassignments.PortalManager.*;

public class MainActivity extends AppCompatActivity implements MyListAdapter.ListItemClickListener{


    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;


    ArrayList<Portal> portalDummies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(portalManagerInstance.portalList == null) {
        portalDummies = new ArrayList<Portal>();

        portalDummies.add(new Portal("https://vlo.informatica.hva.nl/","VLO"));
        portalDummies.add(new Portal("www.hva.nl","HVA"));
        portalDummies.add(new Portal("https://rooster.hva.nl/schedule?requireLogin=true","ROOSTERS"));
        portalDummies.add(new Portal("https://sis.hva.nl:8011/psp/S2PRD/?cmd=login&languageCd=ENG&","SIS"));
            portalManagerInstance.init();
            portalManagerInstance.fillInit(portalDummies);
        }
        recyclerView = (RecyclerView) findViewById(R.id.portalRecycler);

        //performance boost due to no change possible in layoutsize
        recyclerView.setHasFixedSize(true);

        //create linear layoutmanager
        layoutManager = new GridLayoutManager(this, 3);
        //create adapter
        final MyListAdapter webPortalListAdapter = new MyListAdapter(portalManagerInstance.portalList, this);


        //set adapter
        recyclerView.setAdapter(webPortalListAdapter);
        //Set layout
        recyclerView.setLayoutManager(layoutManager);

        FloatingActionButton myFab = this.findViewById(R.id.Add_Portal_FAB);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            goToPortalCreator();

            }
        });

    }
    @Override
    public void onListItemClick(int position) {
        //You will get position of the item clicked depending on your situation perform a desired action. We are simply Toasting.
        Toast.makeText(this,"Loading webview",Toast.LENGTH_LONG).show();
        portalManagerInstance.setCurrentViewed(portalManagerInstance.portalList.get(position).portalTitle);
        Intent intent = new Intent(this, WebPortalActivity.class);
        startActivity(intent);

    }

    public void goToPortalCreator(){
        Intent intent = new Intent(this, AddPortalActivity.class);
        startActivity(intent);

    }
}
