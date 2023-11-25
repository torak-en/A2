import Entities.Actors.Actor;
import Entities.Items.Item;
import Entities.Tiles.Tile;

import java.util.List;

public class LevelLayout {

    int spawnX;
    int spawnY;
    Tile[][] tileLayer;
    List<Item> itemList;
    List<Actor> actorList;

    public void setTile(int x, int y, Tile newTile) {
        tileLayer[y][x] = newTile;
    }
    public Tile getTile(int x, int y, Tile newTile) {
        return tileLayer[y][x] ;
    }
    public List<Item> getItems (){
        return this.itemList;
    }
    public void setItem(int x, Item item){
        itemList.add(x,item);
    }
    public List<Actor> getActors (){
        return this.actorList;
    }
    public void setActor(int x, Actor actor){
        actorList.add(x,actor);
    }
    public void createTileLayer(int x, int y){
        this.tileLayer = new Tile[y][x];
    }
    public void setSpawn (int x, int y){
        this.spawnX = x;
        this.spawnY = y;
    }
    public int getSpawnX(){
        return this.spawnX;
    }
    public int getSpawnY(){
        return this.spawnY;
    }

    /* method to automatically add a tile to next available space, null may need to be
    replaced with placeholder tile, E.g this.tileLayer[x][y].isPlaceholder().
    */

    private void addTile (Tile newTile){
        boolean tileAdded = false;
        for(int y = this.tileLayer.length; y < this.tileLayer.length;y++){
            for(int x = this.tileLayer[y].length; x < this.tileLayer[x].length; x++){
                if(!tileAdded && this.tileLayer[y][x]==null){
                    this.tileLayer[y][x] = newTile;
                    tileAdded = true;
                }
            }
        }
    }
}
