package com.rba.demomvp.product;

import com.rba.demomvp.api.ErrorUtil;
import com.rba.demomvp.model.response.ErrorResponse;
import com.rba.demomvp.model.response.PageResponse;

import retrofit2.Response;

/**
 * Created by Ricardo Bravo on 7/03/17.
 */

public class ProductPresenter implements ProductCallback {

    private ProductView productView;

    public ProductPresenter(ProductView productView) {
        this.productView = productView;
    }

    public void page(int page){
        ProductInteractor.page(page, this);
    }

    @Override
    public void onPokemonResponse(Response<PageResponse> pokemonResponse) {
        if(pokemonResponse.isSuccessful()){
            productView.showResult(pokemonResponse.body());
        }else{
            ErrorResponse error = ErrorUtil.parseError(pokemonResponse);
            productView.showErrorMessage(error.getMessage());
        }
    }

    @Override
    public void onPokemonError(String error) {
        productView.showErrorMessage(error);
    }
}
