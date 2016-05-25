package br.almadaapps.civilapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import br.almadaapps.civilapp.R;
import br.almadaapps.civilapp.fragments.EmentasFragment;
import br.almadaapps.civilapp.fragments.HorariosFragment;
import br.almadaapps.civilapp.fragments.MainPageFragments;
import br.almadaapps.civilapp.fragments.MonografiasFragments;

@SuppressWarnings("AccessStaticViaInstance")
public class MainActivity extends AppCompatActivity {
    public static String[] hor_int;
    public static SharedPreferences pref_horarios;
    private Toolbar tb_main;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref_horarios = getSharedPreferences("Prefs", getApplicationContext().MODE_PRIVATE);

        hor_int = getResources().getStringArray(R.array.horas);
        setContentView(R.layout.activity_main);

        tb_main = (Toolbar) findViewById(R.id.tb_main);
        //noinspection ConstantConditions
        tb_main.setTitleTextColor(Color.WHITE);
        tb_main.setTitle(R.string.app_name);
        setSupportActionBar(tb_main);

        if (savedInstanceState == null) {
            MainPageFragments mainPageFragments = new MainPageFragments();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.rl_container_fragment, mainPageFragments, "mpf").commit();
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, tb_main, R.string.openDrawer, R.string.closeDrawer) {
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

        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setCheckedItem(R.id.pagina_inicial);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected (MenuItem item) {
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);

                mDrawerLayout.closeDrawers();
                String subtitle = "";
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.ementas:
                        subtitle = "Ementas";
                        fragment = new EmentasFragment();
                        break;

                    case R.id.horarios:
                        subtitle = "Horários";
                        fragment = new HorariosFragment();
                        break;

                    case R.id.pagina_inicial:
                        subtitle = "";
                        fragment = new MainPageFragments();
                        break;

                    case R.id.proj_pedagogico:
                        Intent intent = new Intent(MainActivity.this,ProjetoPedagogicoActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.monografias:
                        subtitle="Monografias";
                        fragment = new MonografiasFragments();
                        break;
                    case R.id.sobre:
                        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                        alert.setTitle("Sobre o Civil App");
                        alert.setMessage("Aplicativo desenvolvido com o intuito de facilitar o acesso do corpo acadêmico, do curso de Bacharelado em Engenharia Civil do IFMA – Campus Monte Castelo, às informações essenciais do curso, como: projeto pedagógico, monografias apresentadas, horários, ementas e acesso a links institucionais. \n" +
                                "\n" +
                                "Desenvolvido pelo discente Vinicius da Silva Costa Almada\n\n" +
                                "Com a colaboração dos docentes: \n" +
                                "Prof. Dr. Antonio Jorge Parga da Silva e Prof. Dr. Rodrigo de Azevedo Neves\n" +
                                "\n" +
                                "E dos discentes:\n" +
                                "Abrahão Lima Castro e Mohara de Oliveira Nascimento. \n");
                        alert.setNeutralButton("Ok",null);
                        alert.show();
                        break;
                }

                if (fragment == null)
                    fragment = new MainPageFragments();
                mDrawerLayout.closeDrawers();

                tb_main.setSubtitle(subtitle);
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.rl_container_fragment,fragment)
                        .commit();

                return true;
            }
        });


    }

    @Override
    protected void onRestart () {
        super.onRestart();
        mNavigationView.setCheckedItem(R.id.pagina_inicial);
    }

    @Override
    public void onBackPressed () {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT))
            mDrawerLayout.closeDrawers();
        else
            super.onBackPressed();
    }
}
