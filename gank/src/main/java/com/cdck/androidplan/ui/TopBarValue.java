package com.cdck.androidplan.ui;

/**
 * Created by xlk on 2019/2/25.
 */
public class TopBarValue {
    String title;
    int leftRes;
    int rightRes;
    boolean showLeft;
    boolean showRight;

    public TopBarValue(Builder builder) {
        this.title = builder.title;
        this.leftRes = builder.leftRes;
        this.rightRes = builder.rightRes;
        this.showLeft = builder.showLeft;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLeftRes() {
        return leftRes;
    }

    public void setLeftRes(int leftRes) {
        this.leftRes = leftRes;
    }

    public int getRightRes() {
        return rightRes;
    }

    public void setRightRes(int rightRes) {
        this.rightRes = rightRes;
    }

    public boolean isShowLeft() {
        return showLeft;
    }

    public void setShowLeft(boolean showLeft) {
        this.showLeft = showLeft;
    }

    public boolean isShowRight() {
        return showRight;
    }

    public void setShowRight(boolean showRight) {
        this.showRight = showRight;
    }

    public static class Builder {
        private String title;
        private int leftRes;
        private int rightRes;
        private boolean showLeft;
        private boolean showRight;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder leftRes(int leftRes) {
            this.leftRes = leftRes;
            return this;
        }

        public Builder rightRes(int rightRes) {
            this.rightRes = rightRes;
            return this;
        }

        public Builder showLeft(boolean showLeft) {
            this.showLeft = showLeft;
            return this;
        }

        public Builder showRight(boolean showRight) {
            this.showRight = showRight;
            return this;
        }

        public TopBarValue build() {
            return new TopBarValue(this);
        }
    }
}
