package nl.jesseklijn.androidassignments;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

class Game{

    String title = "";
    String platform = "";
    String notes = "";
    String dateCreated = "";
    String currentStatus;
    Game(String title, String platform, String notes, String status, String dateCreated){
        this.title = title;
        this.platform = platform;
        this.notes = notes;
        this.currentStatus = status;
        this.dateCreated = dateCreated;
    }
}


public class GameManager {

    public static final GameManager gameManagerInstance = new GameManager();

    ArrayList<Game> gameList;
    Game currentlyEditing;
    Boolean isEditing = false;
    public void init(Context context){
        gameList = loadManager(context);
        if(gameList == null){
            gameList = new ArrayList<Game>();
        }

    }

    public ArrayList<Game> loadManager(Context context){
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        Gson gson = new Gson();
        String json = appSharedPrefs.getString("MyObject", "");
        Type type = new TypeToken<List<Game>>(){}.getType();
       return gson.fromJson(json, type);
    }
    public void saveManager(Context context){
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(GameManager.gameManagerInstance.gameList);
        prefsEditor.putString("MyObject", json);
        prefsEditor.commit();
    }


    public void addGameItem(String title, String platform, String notes, String status, String dateCreated){
        gameList.add(new Game(title,platform,notes,status,dateCreated));
    }

    public void editGameItem(String newTitle, String newPlatform, String newNotes, String newStatus, String dateCreated, Game gameToEdit){
        gameList.set(gameList.indexOf(gameToEdit), new Game(newTitle,newPlatform,newNotes,newStatus,dateCreated));
    }

    public void deleteGameItem(String gameTitle){
        for(Game gameItem : gameList){
            if(gameItem.title == gameTitle){
                gameList.remove(gameItem);
                return;
            }
        }
    }

}
