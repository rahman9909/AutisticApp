package saim.com.autisticapp.Model;

public class ModelFamily {
    public String id, name, relation, image;

    public ModelFamily(String id, String name, String relation, String image) {
        this.id = id;
        this.name = name;
        this.relation = relation;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRelation() {
        return relation;
    }

    public String getImage() {
        return image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
