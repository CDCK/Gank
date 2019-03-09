package com.cdck.androidplan.constnt;

/**
 * Created by Administrator on 2018/1/24.
 */

public class EventMessage {
    private final int key;
    private final Object[] values;

    private EventMessage(Builder builder) {
        this.key = builder.key;
        this.values = builder.values;
    }

    public int getKey() {
        return key;
    }

    public Object[] getValues() {
        return values;
    }

    public static class Builder {
        private final int key;
        private Object[] values;

        public Builder(int key) {
            this.key = key;
        }

        public Builder value(Object... values) {
            this.values = values;
            return this;
        }

        public EventMessage build() {
            return new EventMessage(this);
        }
    }
}
