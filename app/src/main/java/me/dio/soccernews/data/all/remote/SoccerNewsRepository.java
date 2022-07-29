package me.dio.soccernews.data.all.remote;

import androidx.room.Room;

import me.dio.soccernews.data.all.local.AppDatabase;
import me.dio.soccernews.ui.App;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SoccerNewsRepository {

    //region Constantes
    private static final String REMOTE_API_URL = "https://emiuxuidev.github.io/soccer-news-api/";
    private static final String LOCAL_DB_NAME = "soccer-news-db";
    //endregion -------


    //region Atributos: Atributos ,accesos a la API, y BD local.
    private SoccerNewsApi remoteApi;
    private AppDatabase localDb;

    public SoccerNewsApi getRemoteApi() {
        return remoteApi;
    }

    public AppDatabase getLocalDb() {
        return localDb;
    }
    //endregion -------


    //region Singleton - garantiza una instancia unica de los atributos relacionados al Retrofit e ROOM
    private SoccerNewsRepository() {
        remoteApi = new Retrofit.Builder()
                .baseUrl(REMOTE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SoccerNewsApi.class);

        localDb = Room.databaseBuilder(App.getInstance(), AppDatabase.class, LOCAL_DB_NAME)
                .build();
    }
    public static SoccerNewsRepository getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final SoccerNewsRepository INSTANCE = new SoccerNewsRepository();
    }
    //endregion
}
