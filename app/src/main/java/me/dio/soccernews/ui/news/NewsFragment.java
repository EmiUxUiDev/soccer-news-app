package me.dio.soccernews.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import me.dio.soccernews.databinding.FragmentNewsBinding;
import me.dio.soccernews.ui.adapter.NewsAdapter;


public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;
    private NewsViewModel newsViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));

        observeNewsApi();
        observeState();

        binding.srlNews.setOnRefreshListener(newsViewModel::getNewss);

        return root;
    }


    private void observeNewsApi() {
        newsViewModel.getNews().observe(getViewLifecycleOwner(), news -> {
            binding.rvNews.setAdapter(new NewsAdapter(news, newsViewModel::saveNews));
        });
    }

    private void observeState() {
        newsViewModel.getState().observe(getViewLifecycleOwner(), state -> {

            switch (state) {
                case DOING:
                    binding.srlNews.setRefreshing(true);
                    break;
                case DONE:
                    binding.srlNews.setRefreshing(false);
                    break;
                case ERROR:
                    binding.srlNews.setRefreshing(false);
                    Snackbar.make(binding.srlNews, "Error de red", Snackbar.LENGTH_SHORT)
                            .show();
                    break;
            }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}