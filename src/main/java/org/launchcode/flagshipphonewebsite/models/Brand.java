package org.launchcode.flagshipphonewebsite.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Brand extends AbstractEntity {

    private String image;

    @JoinColumn
    @OneToMany
    private List<Phone> phones = new ArrayList<Phone>();

    public Brand() {}

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;

    }
}
   
    