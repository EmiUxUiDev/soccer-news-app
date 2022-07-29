package me.dio.soccernews.ui.favorites;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import me.dio.soccernews.data.all.remote.SoccerNewsRepository;
import me.dio.soccernews.domain.News;

public class FavoritesViewModel extends ViewModel {

    public FavoritesViewModel() {

    }

    public LiveData<List<News>> loadFavoritesNews() {
        return SoccerNewsRepository.getInstance().getLocalDb().newsDao().loadFavoriteNews();
    }

    public void saveNews(News news) {
        AsyncTask.execute(()-> SoccerNewsRepository.getInstance().getLocalDb().newsDao().save(news));
    }

}