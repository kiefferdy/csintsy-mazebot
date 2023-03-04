package ver2;

public class Coordinate {

    private int x;
    private int y;
    private boolean isVisited;
    private String data;
    private Coordinate parent;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
        this.isVisited = false;
        this.data = ".";
        this.parent = null;
    }

    public Coordinate(int x, int y, String data) {
        this.x = x;
        this.y = y;
        this.isVisited = false;
        this.data = data;
        this.parent = null;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean getIsVisited() {
        return this.isVisited;
    }

    //sets isVisited to true
    public void setIsVisited() {
        this.isVisited = true;
    }

    public String getData() {
        return this.data;
    }

    public Coordinate getParent() {
        return this.parent;
    }

    public void setParent(Coordinate parent) {
        this.parent = parent;
    }
}