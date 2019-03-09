package com.cdck.androidplan.model;

/**
 * Created by xlk on 2019/1/16.
 */
public class ModelManage {

    private static ModelManage instance;

    private ModelManage() {
    }

    public static ModelManage getInstance() {
        if (instance == null) {
            synchronized (ModelManage.class) {
                if (instance == null) {
                    instance = new ModelManage();
                }
            }
        }
        return instance;
    }
}
