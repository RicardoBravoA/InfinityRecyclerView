package com.rba.demomvp.product;

import com.rba.demomvp.model.response.PageResponse;

/**
 * Created by Ricardo Bravo on 6/03/17.
 */

public interface ProductView {

    void init();

    void showErrorMessage(String message);

    void showResult(PageResponse pageResponse);

    void showMessage(String message);

    void showRefresh();

    void hideRefresh();

}
