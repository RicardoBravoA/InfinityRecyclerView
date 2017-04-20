package com.rba.demomvp.api;

import com.rba.demomvp.BuildConfig;
import com.rba.demomvp.model.response.PageResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Ricardo Bravo on 6/03/17.
 */

public interface DemoApi {

    @GET(BuildConfig.URL_PAGE)
    Call<PageResponse> page(@Path("page") int page);

}
