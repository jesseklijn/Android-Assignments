package nl.jesseklijn.androidassignments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyViewHolder>{

    //Creating a global variable to store the array of string containing data to be displayed
    private ArrayList<Portal> listOfItems;
    public interface ListItemClickListener{
        void onListItemClick(int position);
    }

    ListItemClickListener itemClickListener;

    public MyListAdapter(ArrayList<Portal> listOfItems, ListItemClickListener itemClickListener) {
        this.listOfItems = listOfItems;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Referencing the layout created for individual list item to get attached to RecyclerView
        Boolean attachViewImmediatelyToParent = false;
        View singleItemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item,parent,attachViewImmediatelyToParent);
        MyViewHolder myViewHolder = new MyViewHolder(singleItemLayout);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //Displaying list item to it correct position
        holder.textToShow.setText(listOfItems.get(position).portalTitle);
    }

    @Override
    public int getItemCount() {
        //Returning number of items in listOfItems
        return listOfItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textToShow;

        //Define a constructor taking a View as its parameter
        public MyViewHolder(View itemView) {
            super(itemView);
            textToShow = itemView.findViewById(R.id.portal_text_item);

            textToShow.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            //Call onListItemClick which will trigger the method present in MainActivity.java
            itemClickListener.onListItemClick(getAdapterPosition());
        }
    }

}
