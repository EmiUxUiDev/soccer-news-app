package me.dio.soccernews.data.all.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import me.dio.soccernews.domain.News;

@Database(entities = {News.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract newsDao newsDao();
}
