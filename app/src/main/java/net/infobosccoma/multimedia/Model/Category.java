package net.infobosccoma.multimedia.Model;

/**
 * Created by Maxi on 19/03/2015.
 */
public class Category {

    private String name;
    private int id;

    public Category(String name, int id){
        this.setName(name);
        this.setId(id);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
