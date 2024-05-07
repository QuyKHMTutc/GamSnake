package snakegame;

import java.awt.Image;

/**
 *
 * @author Admin
 */
public class Amination {
    public Image[] images;
    public int n;
    public int currentImage;

    public Amination() {
        n = 0;
        currentImage = 0;
    }

    public void addImage(Image image) {
        Image[] ar = images;
        images = new Image[n + 1];

        for (int i = 0; i < n; i++) {
            images[i] = ar[i];
        }
        images[n] = image;
        n++;
    }

    public void update() {
        currentImage++;
        if (currentImage >= n)
            currentImage = 0;
    }

    public Image getCurrentImage() {
        return images[currentImage];
    }
}