package nl.jesseklijn.androidassignments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyViewHolder>{

    //Creating a global variable to store the array of string containing data to be displayed

    public interface ListItemClickListener{
        void onListItemClick(int position);
    }

    private ArrayList<Game> listOfItems;
    private final ListItemClickListener itemClickListener;

    public MyListAdapter(ArrayList<Game> listOfItems, ListItemClickListener itemClickListener) {
        this.listOfItems = listOfItems;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Referencing the layout created for individual list item to get attached to RecyclerView
        Boolean attachViewImmediatelyToParent = false;
        View singleItemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,attachViewImmediatelyToParent);
        MyViewHolder myViewHolder = new MyViewHolder(singleItemLayout);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //Displaying list item to it correct position
        holder.gameTitleText.setText(listOfItems.get(position).title);
        holder.platformText.setText(listOfItems.get(position).platform);
        holder.statusText.setText(listOfItems.get(position).currentStatus);
        holder.dateText.setText(listOfItems.get(position).dateCreated);
    }

    @Override
    public int getItemCount() {
        //Returning number of items in listOfItems
        return listOfItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView gameTitleText;
        TextView platformText;
        TextView statusText;
        TextView dateText;
        //Define a constructor taking a View as its parameter
        public MyViewHolder(final View itemView) {
            super(itemView);
            gameTitleText = itemView.findViewById(R.id.GameTitleLabel);
            platformText = itemView.findViewById(R.id.PlatformLabel);
            statusText = itemView.findViewById(R.id.StatusLabel);
            dateText = itemView.findViewById(R.id.dateLabel);
            itemView.setOnClickListener(this);

            itemView.setOnTouchListener(new OnSwipeTouchListener(itemView.getContext()) {
                public void onSwipeRight() {
                    swipe();
            }

                public void onSwipeLeft() {
                    Toast.makeText((itemView.getContext()),"Editing item!..",Toast.LENGTH_LONG).show();

                    itemClickListener.onListItemClick(getAdapterPosition());
                }


            });

        }


        public void swipe(){
            Log.d("myTag", "Deleting item..");
            Toast.makeText((itemView.getContext()),"Deleting item..",Toast.LENGTH_LONG).show();

            GameManager.gameManagerInstance.deleteGameItem(gameTitleText.getText().toString());
            GameManager.gameManagerInstance.saveManager(itemView.getContext());
            ((MainActivity)itemView.getContext()).finish();
            (itemView.getContext()).startActivity(((MainActivity) itemView.getContext()).getIntent());}

        @Override
        public void onClick(View v) {

                //Call onListItemClick which will trigger the method present in MainActivity.java



        }
    }

}
