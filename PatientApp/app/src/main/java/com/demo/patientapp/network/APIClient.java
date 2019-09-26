package com.demo.patientapp.network;

import android.text.TextUtils;

import com.demo.patientapp.common.Constants;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit mRetrofit = null;
    private static int REQUEST_TIMEOUT = 60;
    private static OkHttpClient mOkHttpClient;
    private LiveNetworkMonitor mLiveNetworkMonitor;

    private Retrofit retrofit;
    private static APIClient instance;


    public static APIClient getInstance(LiveNetworkMonitor liveNetworkMonitor) {
        if (instance == null) {
            instance = new APIClient();
            instance.mLiveNetworkMonitor = liveNetworkMonitor;
        }

        return instance;
    }

    public <T> T create(final Class<T> service) {
        if (retrofit == null) {
            retrofit = createRetrofit(null);
        }

        return retrofit.create(service);
    }

    public <T> T create(final Class<T> service, String token) {
        if (retrofit == null) {
            retrofit = createRetrofit(token);
        }

        return retrofit.create(service);
    }

    private Retrofit createRetrofit(String token) {

        if (mOkHttpClient == null)
            initOkHttp(token);

        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(mOkHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    private void initOkHttp(final String token) {
        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(interceptor);

        httpClient.addInterceptor(new NetworkMonitorInterceptor(mLiveNetworkMonitor));

        httpClient.addInterceptor(new ResponseCodeInterceptor());

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("version", "10")
                        .addHeader("Content-Type", "application/x-www-form-urlencoded");

                // Adding Authorization token (API Key)
                // Requests will be denied without API key
                if (!TextUtils.isEmpty(token)) {
                    requestBuilder.addHeader("token", token);
                }

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        mOkHttpClient = httpClient.build();
    }
}