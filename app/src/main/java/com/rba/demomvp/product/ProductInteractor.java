package com.rba.demomvp.product;

import com.rba.demomvp.api.DemoApiManager;
import com.rba.demomvp.model.response.PageResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ricardo Bravo on 6/03/17.
 */

public class ProductInteractor {

    public static void page(int page, final ProductCallback callback){
        Call<PageResponse> call = DemoApiManager.apiManager().page(page);
        call.enqueue(new Callback<PageResponse>() {
            @Override
            public void onResponse(Call<PageResponse> call, Response<PageResponse> response) {
                callback.onPokemonResponse(response);
            }

            @Override
            public void onFailure(Call<PageResponse> call, Throwable t) {
                callback.onPokemonError(t.getMessage());
            }
        });
    }

}
