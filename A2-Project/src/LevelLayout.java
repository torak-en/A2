import java.util.List;

public class LevelLayout {

    int spawnX;
    int spawnY;
    Tile[][] tileLayer;
    List<Item> itemList;
    List<Actor> actorList;

    private void setTile(int x, int y, Tile newTile) {
        tileLayer[y][x] = newTile;
    }
    private Tile getTile(int x, int y, Tile newTile) {
        return tileLayer[y][x] ;
    }
    private List<Item> getItems (){
        return this.itemList;
    }
    private void setItem(int x, Item item){
        itemList.add(x,item);
    }
    private List<Actor> getActors (){
        return this.actorList;
    }
    private void setActor(int x, Actor actor){
        actorList.add(x,actor);
    }
    private void createTileLayer(int x, int y){
        this.tileLayer = new Tile[y][x];
    }
    private void setSpawn (int x, int y){
        this.spawnX = x;
        this.spawnY = y;
    }
    private int getSpawnX(){
        return this.spawnX;
    }
    private int getSpawnY(){
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
