package com.rba.demomvp.product;

import com.rba.demomvp.model.response.PageResponse;

import retrofit2.Response;

/**
 * Created by Ricardo Bravo on 6/03/17.
 */

public interface ProductCallback {

    void onPokemonResponse(Response<PageResponse> pokemonResponse);

    void onPokemonError(String error);

}
