package nl.jesseklijn.androidassignments;

import java.util.ArrayList;

class Portal{
    String url = "";
    String portalTitle = "";
    Portal(String url, String portalTitle){
        this.url = url;
        this.portalTitle = portalTitle;
    }
}

public class PortalManager {
    //singleton
    public static final PortalManager portalManagerInstance = new PortalManager();

    ArrayList<Portal> portalList;
    Portal currentViewed;

  public void init(){
      portalList = new ArrayList<Portal>();

    }

    public void fillInit(ArrayList<Portal> portalCollection){
        portalList = portalCollection;
    }
    //
    public void setCurrentViewed(String portalname){
        for(Portal portalItem : portalList){
        if(portalItem.portalTitle == portalname){
            currentViewed = portalItem;
            return;
        }
        }
    }

    public void addPortalItem(String url, String title){
      portalList.add(new Portal(url,title));
    }


}
