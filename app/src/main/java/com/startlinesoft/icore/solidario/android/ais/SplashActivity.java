package com.startlinesoft.icore.solidario.android.ais;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.startlinesoft.icore.solidario.android.ais.utilidades.ICoreApiClient;
import com.startlinesoft.icore.solidario.android.ais.utilidades.ICoreAppCompatActivity;

public class SplashActivity extends ICoreAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(tarea, 1000);
    }

    private final Runnable tarea = () -> {
        verificarRed();

        if (isSetToken()) {
            ICoreApiClient.setToken(getToken());
            Intent i;
            if (ICoreApiClient.esTokenValido()) {
                //Sí es válido, ir a dashboard
                i = new Intent(getBaseContext(), DashBoardActivity.class);
            } else {
                //No es válido, ir al login
                i = new Intent(getBaseContext(), LoginActivity.class);
                removerToken();
            }
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        } else {
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            removerToken();
            startActivity(i);
        }
        finish();
    };
}