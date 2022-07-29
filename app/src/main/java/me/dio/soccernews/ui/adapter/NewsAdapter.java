package me.dio.soccernews.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import me.dio.soccernews.R;
import me.dio.soccernews.databinding.NewsItemsBinding;
import me.dio.soccernews.domain.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    private List<News> news;
    private final NewsListener favoriteListener;

    public NewsAdapter(List<News> news, NewsListener favoriteListener ){
        this.news = news;
        this.favoriteListener = favoriteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NewsItemsBinding binding = NewsItemsBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();

        News news = this.news.get(position);
        holder.binding.tvTitle.setText(news.title);
        holder.binding.tvDescription.setText(news.description);
        Picasso.get()
                .load(news.image)
                .fit()
                .into(holder.binding.ivThumbnail);

        //Implementacion de link externo en boton
        holder.binding.btOpenLink.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(news.link));
            holder.itemView.getContext().startActivity(i);
        });

        //Implementacion de compartir articulo
        holder.binding.ivShare.setOnClickListener(view -> {
            Intent i = new Intent(); i.setAction(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_TEXT, news.link);
            holder.itemView.getContext().startActivity(Intent.createChooser(i, "Compartir"));
        });

        //Implementacion de accion favorito en articulo
        holder.binding.ivFavorite.setOnClickListener(view -> {
            news.favorite = !news.favorite;
            this.favoriteListener.onFavorite(news);
            notifyItemChanged(position);
        });

        int favoriteColor = news.favorite? R.color.favorite : R.color.default_grey;
        holder.binding.ivFavorite.setColorFilter(context.getResources().getColor(favoriteColor));
    }

    @Override
    public int getItemCount() {
        return this.news.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final NewsItemsBinding binding;

        public ViewHolder(NewsItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface NewsListener {
       void onFavorite(News news);
    }
}
