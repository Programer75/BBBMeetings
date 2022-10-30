package com.example.bbbmeetings;

import android.app.Application;
import android.util.Log;

import com.example.bbbmeetings.net.bbbApi;

import org.apache.commons.codec.digest.DigestUtils;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class App extends Application {
    public static bbbApi api;
    private final String BASE_URL = "https://test-install.blindsidenetworks.com/bigbluebutton/api/";
    private final String API_KEY = "8cd8ef52e8e101574e400365b55e11a6";
    @Override
    public void onCreate() {
        super.onCreate();
        api = createRetrofit().create(bbbApi.class);
    }

    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getClient())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
    }

    private OkHttpClient getClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(new Cache(getApplicationContext().getCacheDir(), 10L * 1024 * 1024));

        Interceptor interceptor = chain -> {
            Request request = chain.request();
            String[] urlEl = request.url().url().toString().split("/");
            String url = urlEl[urlEl.length - 1];
            String[] urlT = url.split("\\?");
            if (urlT.length != 1){
                url = urlT[0]+urlT[1];
            }
            HttpUrl newUrl = request
                    .url()
                    .newBuilder()
                    .addQueryParameter("checksum", DigestUtils.sha1Hex(url + API_KEY))
                    .build();
            Log.d("OKHttpClient: ", newUrl.toString());
            return chain.proceed(request.newBuilder().url(newUrl).build());
        };
        return client.addInterceptor(interceptor).build();
    }
}
