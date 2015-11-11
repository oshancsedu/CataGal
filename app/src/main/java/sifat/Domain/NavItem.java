package sifat.Domain;

/**
 * Created by sifat on 11/10/2015.
 */
public class NavItem {

    String item;
    int image;

    public NavItem(int image, String item)
    {
        this.image=image;
        this.item=item;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
