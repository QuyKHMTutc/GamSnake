package snakegame;

/**
 *
 * @author Admin
 */
public class User {
    private String name;
    private int level;
    private int score;

    public User(String name, int level, int score) {
        this.name = name;
        this.level = level;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String toString() {
        return name + " " + level + " " + score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}