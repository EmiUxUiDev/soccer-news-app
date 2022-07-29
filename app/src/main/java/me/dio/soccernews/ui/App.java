package me.dio.soccernews.ui;

import android.app.Application;

/** Implementação de App para centralizar o uso deo contex e instancias de dados, sem entrar
 * no tema de injeção de dependencias
 * Estudar ese tema para ganhar robustes na arquitetura do projeto*/

public class App extends Application {
    private  static App instance;

    public static  App getInstance(){ return instance;}

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
    }
}
