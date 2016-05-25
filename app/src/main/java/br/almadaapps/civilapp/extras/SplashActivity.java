package br.almadaapps.civilapp.extras;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import br.almadaapps.civilapp.MainActivity;
import br.almadaapps.civilapp.R;

/**
 * Created by viniciusalmada on 21/05/2016.
 */

public class SplashActivity extends AppCompatActivity {
    private RelativeLayout rl_splash_ifma;
    private RelativeLayout rl_splash_ec;
    private TextView tv12;

    private static int TEMPO_SPLASH = 3000;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_into_layout);
        rl_splash_ec = (RelativeLayout) findViewById(R.id.rl_splash_ec);
        rl_splash_ifma = (RelativeLayout) findViewById(R.id.rl_splash_ifma);
        tv12 = (TextView) findViewById(R.id.textView12);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run () {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        },TEMPO_SPLASH);
    }

    @Override
    protected void onResume () {
        super.onResume();
        /*YoYo.with(Techniques.BounceInLeft)
                .duration(2000)
                .playOn(rl_splash_ifma);

        YoYo.with(Techniques.BounceInRight)
                .duration(2000)
                .playOn(rl_splash_ec);

        YoYo.with(Techniques.BounceInDown)
                .duration(2000)
                .playOn(tv12);*/

        /*YoYo.with(Techniques.FadeIn)
                .duration(2000)
                .playOn(rl_splash_ifma);

        YoYo.with(Techniques.FadeIn)
                .duration(2000)
                .playOn(rl_splash_ec);

        YoYo.with(Techniques.FadeIn)
                .duration(2000)
                .playOn(tv12);*/
    }

    @Override
    public void onBackPressed () {
        super.onBackPressed();
        /*Intent i = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(i);
        finish();*/
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}
