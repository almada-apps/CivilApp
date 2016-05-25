package br.almadaapps.civilapp;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import br.almadaapps.civilapp.R;
import br.almadaapps.civilapp.dbCore.BancoController;
import br.almadaapps.civilapp.extras.TouchImageView;

/**
 * Created by viniciusalmada on 22/05/2016.
 */

public class ProjetoPedagogicoActivity extends AppCompatActivity {
    private Toolbar tb_main;
    private NavigationView navigationView;
    private DrawerLayout mDrawerLayout;
    private TextView tv_container;
    private ActionBarDrawerToggle drawerToggle;
    private TouchImageView touchImageView;
    private ScrollView pp_scroll;
    private String[] conteudo0;
    private String[] subtitles;
    /*private String[] conteudo1;
    private String[] conteudo2;
    private String[] conteudo3;
    private String[] conteudo4;
    private String[] conteudo5;
*/
    private int[] menuItemMain = {R.id.pp_i2, R.id.pp_i3, R.id.pp_i4, R.id.pp_c1, R.id.pp_c2, R.id.pp_c3, R.id.pp_c4, R.id.pp_c5, R.id.pp_c6, R.id.pp_c7, R.id.pp_c8, R.id.pp_c9, R.id.pp_c10, R.id.pp_c11, R.id.pp_c12, R.id.pp_c13, R.id.pp_c14, R.id.pp_c15, R.id.pp_c16, R.id.pp_c17, R.id.pp_c18, R.id.pp_c19, R.id.pp_c20, R.id.pp_c21, R.id.pp_c22, R.id.pp_c23};
    //private int[] menuItemA2 = {R.id.pp_a2_1, R.id.pp_a2_2, R.id.pp_a2_3, R.id.pp_a2_4, R.id.pp_a2_5, R.id.pp_a2_6, R.id.pp_a2_7, R.id.pp_a2_8, R.id.pp_a2_9, R.id.pp_a2_10, R.id.pp_a2_11};
    //private int[] menuItemA3 = {R.id.pp_a3_1, R.id.pp_a3_2, R.id.pp_a3_3, R.id.pp_a3_4, R.id.pp_a3_5, R.id.pp_a3_6, R.id.pp_a3_7, R.id.pp_a3_8, R.id.pp_a3_9, R.id.pp_a3_10, R.id.pp_a3_11, R.id.pp_a3_12, R.id.pp_a3_13, R.id.pp_a3_14, R.id.pp_a3_15};
    //private int[] menuItemA4 = {R.id.pp_a4_1, R.id.pp_a4_2, R.id.pp_a4_3, R.id.pp_a4_4, R.id.pp_a4_5};
    //private int[] menuItemA5 = {R.id.pp_a5_1, R.id.pp_a5_2, R.id.pp_a5_3, R.id.pp_a5_4, R.id.pp_a5_5, R.id.pp_a5_6, R.id.pp_a5_7, R.id.pp_a5_8, R.id.pp_a5_9};
    //private int[] menuItemA6 = {R.id.pp_a6_1, R.id.pp_a6_2, R.id.pp_a6_3, R.id.pp_a6_4, R.id.pp_a6_5, R.id.pp_a6_6, R.id.pp_a6_7};

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.projeto_pedagogico);
        BancoController crud = new BancoController(getBaseContext());
        Cursor[] cursor = crud.getProjetoPedagogico();

        conteudo0 = new String[cursor[0].getColumnCount()];
        for (int i = 0; i < cursor[0].getColumnCount(); i++) {
            conteudo0[i] = cursor[0].getString(i);
        }
        /*conteudo1 = new String[cursor[1].getColumnCount()];
        for (int i = 0; i < cursor[1].getColumnCount(); i++) {
            conteudo1[i] = cursor[1].getString(i);
        }
        conteudo2 = new String[cursor[2].getColumnCount()];
        for (int i = 0; i < cursor[2].getColumnCount(); i++) {
            conteudo2[i] = cursor[2].getString(i);
        }
        conteudo3 = new String[cursor[3].getColumnCount()];
        for (int i = 0; i < cursor[3].getColumnCount(); i++) {
            conteudo3[i] = cursor[3].getString(i);
        }
        conteudo4 = new String[cursor[4].getColumnCount()];
        for (int i = 0; i < cursor[4].getColumnCount(); i++) {
            conteudo4[i] = cursor[4].getString(i);
        }
        conteudo5 = new String[cursor[5].getColumnCount()];
        for (int i = 0; i < cursor[5].getColumnCount(); i++) {
            conteudo5[i] = cursor[5].getString(i);
        }*/

        subtitles = getResources().getStringArray(R.array.pp_sumario);


        pp_scroll = (ScrollView) findViewById(R.id.pp_scroll);

        touchImageView = (TouchImageView) findViewById(R.id.pp_tim);

        tv_container = (TextView) findViewById(R.id.tv_container);
        tv_container.setText(conteudo0[0]);


        tb_main = (Toolbar) findViewById(R.id.pp_tb_main);
        tb_main.setTitleTextColor(Color.WHITE);
        tb_main.setTitle("Projeto Pedagógico");
        setSupportActionBar(tb_main);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.pp_drawer);
        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, tb_main, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerClosed (View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened (View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.pp_navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected (MenuItem item) {
                boolean i = false;
                int j1 = 0;
                /*int j2 = 0;
                int j3 = 0;
                int j4 = 0;
                int j5 = 0;
                int j6 = 0;*/
                while (!i) {
                    if (item.getItemId() == menuItemMain[j1] && item.getItemId() != R.id.pp_a1) {
                        touchImageView.setVisibility(View.INVISIBLE);
                        tv_container.setVisibility(View.VISIBLE);
                        pp_scroll.scrollTo(0, 0);
                        YoYo.with(Techniques.ZoomInDown)
                                .duration(700)
                                .playOn(tv_container);
                        tv_container.setGravity(Gravity.LEFT);
                        tv_container.setText(conteudo0[j1 + 1]);
                        tb_main.setSubtitle(subtitles[j1]);
                        i = true;
                    }
                        j1++;

                    /*if (item.getItemId() == menuItemA2[j2] && item.getItemId() != R.id.pp_a1) {
                        touchImageView.setVisibility(View.INVISIBLE);
                        tv_container.setVisibility(View.VISIBLE);
                        pp_scroll.scrollTo(0, 0);
                        tv_container.setGravity(Gravity.LEFT);
                        tv_container.setText(conteudo1[j2 + 1]);
                        i = true;
                        break;
                    }else
                        j2++;

                    if (item.getItemId() == menuItemA3[j3] && item.getItemId() != R.id.pp_a1) {
                        touchImageView.setVisibility(View.INVISIBLE);
                        tv_container.setVisibility(View.VISIBLE);
                        pp_scroll.scrollTo(0, 0);
                        tv_container.setGravity(Gravity.LEFT);
                        tv_container.setText(conteudo2[j3 + 1]);
                        i = true;
                        break;
                    }else
                        j3++;

                    if (item.getItemId() == menuItemA4[j4] && item.getItemId() != R.id.pp_a1) {
                        touchImageView.setVisibility(View.INVISIBLE);
                        tv_container.setVisibility(View.VISIBLE);
                        pp_scroll.scrollTo(0, 0);
                        tv_container.setGravity(Gravity.LEFT);
                        tv_container.setText(conteudo3[j4 + 1]);
                        i = true;
                        break;
                    }else
                        j4++;

                    if (item.getItemId() == menuItemA5[j5] && item.getItemId() != R.id.pp_a1) {
                        touchImageView.setVisibility(View.INVISIBLE);
                        tv_container.setVisibility(View.VISIBLE);
                        pp_scroll.scrollTo(0, 0);
                        tv_container.setGravity(Gravity.LEFT);
                        tv_container.setText(conteudo4[j5 + 1]);
                        i = true;
                        break;
                    }else
                        j5++;

                    if (item.getItemId() == menuItemA6[j6] && item.getItemId() != R.id.pp_a1) {
                        touchImageView.setVisibility(View.INVISIBLE);
                        tv_container.setVisibility(View.VISIBLE);
                        pp_scroll.scrollTo(0, 0);
                        tv_container.setGravity(Gravity.LEFT);
                        tv_container.setText(conteudo5[j6 + 1]);
                        i = true;
                        break;
                    } else
                        j6++;*/

                    if (item.getItemId() == R.id.pp_a1) {
                        tv_container.setVisibility(View.INVISIBLE);
                        touchImageView.setVisibility(View.VISIBLE);
                        tb_main.setSubtitle("Fluxograma do Curso");
                        i = true;
                        break;
                    }
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });


    }

    @Override
    public void onBackPressed () {
        super.onBackPressed();
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT))
            mDrawerLayout.closeDrawers();
    }
}
