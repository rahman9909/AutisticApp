package saim.com.autisticapp.Model;

public class ModelRashed {
    public String id, name, relation, image, image_eye, sound;

    public ModelRashed(String id, String name, String relation, String image, String image_eye) {
        this.id = id;
        this.name = name;
        this.relation = relation;
        this.image = image;
        this.image_eye = image_eye;
    }

    public ModelRashed(String id, String name, String relation, String image, String image_eye, String sound) {
        this.id = id;
        this.name = name;
        this.relation = relation;
        this.image = image;
        this.image_eye = image_eye;
        this.sound = sound;
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

    public String getImage_eye() {
        return image_eye;
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

    public void setImage_eye(String image_eye) {
        this.image_eye = image_eye;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }
}
