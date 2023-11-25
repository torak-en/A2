public class Level {

    LevelLayout currentLevel;
    int timer;
    String levelName;
    int maxLevelAchieved;
    LevelHandler levelLoader;

    public Level () {
    }

    public LevelLayout getLevelLayout() {
        return currentLevel;
    }

    public int getTimer() {
        return timer;
    }
    public void tick(){
        // add a tick
    }

}
