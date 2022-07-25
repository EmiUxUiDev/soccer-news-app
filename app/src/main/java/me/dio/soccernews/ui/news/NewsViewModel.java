package me.dio.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import me.dio.soccernews.domain.News;

public class NewsViewModel extends ViewModel {


    private final MutableLiveData<List<News>> news;

    public NewsViewModel() {
        this.news = new MutableLiveData<>();

        //TODO Borrar mock de prueba e implementar el adecuado
        List<News> news = new ArrayList<>();
        news.add(new News( "Competencia de futbol femenino, Ferro - Palmeiras", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque nec suscipit sapien. Nullam tempus rutrum interdum. Nulla ipsum turpis, malesuada eu egestas."));
        news.add(new News( "Ferrito juega el Sabado", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc pretium, diam ac sodales finibus, mauris felis dignissim odio, eget tincidunt urna sem et lorem. Vivamus id metus sit amet nisl."));
        news.add(new News( "Ampliacion en CAPjr", "Consectetur adipiscing elit. Lorem ipsum dolor sit amet. Nunc pretium, diam ac sodales finibus, mauris felis , eget tincidunt urna sem et lorem. Vivamus id metus sit amet nisl."));

        this.news.setValue(news);
    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}
