package me.dio.soccernews.ui.news;

import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import me.dio.soccernews.data.all.remote.SoccerNewsRepository;
import me.dio.soccernews.domain.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsViewModel extends ViewModel {

    public enum State {DOING, DONE, ERROR;}

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();
    private final MutableLiveData<State> state = new MutableLiveData<>();


    public NewsViewModel() {
        this.getNewss();
    }

    public void getNewss() {
        state.setValue(State.DOING);
        SoccerNewsRepository.getInstance().getRemoteApi().findNews().enqueue(new Callback<List<News>>() {

            @Override
            public void onResponse(@androidx.annotation.NonNull Call<List<News>> call, @NonNull Response<List<News>> response) {
                if (response.isSuccessful()) {
                    news.setValue(response.body());
                    state.setValue(State.DONE);
                } else {
                    state.setValue(State.ERROR);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<News>> call, @NonNull Throwable error) {
                //Sacar el printStackTrace() cuando mejoremos la app.
                error.printStackTrace();
                state.setValue(State.ERROR);
            }
        });
    }

    public void saveNews(News news) {
        AsyncTask.execute(()-> SoccerNewsRepository.getInstance().getLocalDb().newsDao().save(news));
    }

    public LiveData<List<News>> getNews() {
        return news;
    }

    public LiveData<State> getState() {
        return this.state;
    }
}
