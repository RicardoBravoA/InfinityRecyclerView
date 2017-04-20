package com.rba.demomvp.model.response;

import java.util.List;

/**
 * Created by Ricardo Bravo on 30/01/17.
 */

public class PageResponse {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * product_id : 1
         * description : Producto 1
         */

        private int product_id;
        private String description;

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
