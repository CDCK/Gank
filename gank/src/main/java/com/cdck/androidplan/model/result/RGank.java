package com.cdck.androidplan.model.result;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xlk on 2019/2/27.
 */
public class RGank implements Serializable {
    public List<String> images;
    public float scale;

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "RGank{" +
                "images=" + images +
                '}';
    }
}
