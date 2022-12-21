package com.team.restaurant_admin_panel.models.plat;

import com.team.restaurant_admin_panel.models.plat.Plat;
import javafx.scene.image.ImageView;

public class CustomPlat {
    private Plat plat;
    private ImageView imageView;

    public CustomPlat(Plat plat, ImageView imageView) {
        this.plat = plat;
        this.imageView = imageView;
    }

    public Plat getPlat() {
        return plat;
    }

    public void setPlat(Plat plat) {
        this.plat = plat;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
