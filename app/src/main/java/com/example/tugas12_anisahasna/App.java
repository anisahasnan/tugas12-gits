package com.example.tugas12_anisahasna;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.tugas12_anisahasna.database.FavMovieDatabase;
import com.facebook.stetho.DumperPluginsProvider;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.dumpapp.DumperPlugin;

import java.util.ArrayList;

public class App extends Application {

    public static FavMovieDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();

        final Context context = this;
        Stetho.initialize(
                Stetho.newInitializerBuilder(context)
                        .enableDumpapp(new SampleDumperPluginsProvider(context))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
                        .build());

        db = Room.databaseBuilder(getApplicationContext(), FavMovieDatabase.class, "favoriteMovie")
                .allowMainThreadQueries()
                .build();

    }

    private static class SampleDumperPluginsProvider implements DumperPluginsProvider{
        private final Context mContext;

        public SampleDumperPluginsProvider(Context context){
            mContext = context;
        }

        @Override
        public Iterable<DumperPlugin> get() {
            ArrayList<DumperPlugin> plugins = new ArrayList<>();
            for(DumperPlugin defaultPlugin: Stetho.defaultDumperPluginsProvider(mContext).get()){
                plugins.add(defaultPlugin);
            }
            return plugins;
        }
    }
}
