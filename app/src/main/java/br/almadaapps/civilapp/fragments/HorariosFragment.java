package br.almadaapps.civilapp.fragments;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.almadaapps.civilapp.MainActivity;
import br.almadaapps.civilapp.R;
import br.almadaapps.civilapp.adapters.HorariosAdapter;
import br.almadaapps.civilapp.dbCore.BancoController;
import br.almadaapps.civilapp.domain.Horarios;

public class HorariosFragment extends Fragment {
    public static final String TAG = "LOG";
    //public static final String[] per_nome = {"1º", "3º", "5º", "7º", "9º"};
    private static final String[] dias_nome = {"SEG", "TER", "QUA", "QUI", "SEX", "SAB"};
    public static boolean whatAnimation = true;
    private static final int[] IC_PERIODOS = {R.drawable.ic_1_per,R.drawable.ic_3_per,R.drawable.ic_5_per,R.drawable.ic_7_per,R.drawable.ic_9_per};
    private View view;
    private TabLayout mTabLayoutTop;
    private TabLayout mTabLayoutBot;
    private RecyclerView rv_horarios;
    private HorariosAdapter adapter;
    private List<Horarios> list;
    private String periodo_selected;
    private String dia_selected;


    @Override
    public View onCreateView (final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_horarios, container, false);

        mTabLayoutTop = (TabLayout) view.findViewById(R.id.tl_top_horarios_frag);
        mTabLayoutBot = (TabLayout) view.findViewById(R.id.tl_bot_horarios_frag);
        rv_horarios = (RecyclerView) view.findViewById(R.id.rv_horarios);

        playBeginAnimation();
        rv_horarios.setHasFixedSize(true);
        list = new ArrayList<>();
        adapter = new HorariosAdapter(getActivity(), list);
        rv_horarios.setAdapter(adapter);

        final LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.setReverseLayout(false);
        rv_horarios.setLayoutManager(llm);

        View[] customTabView = new View[11];
        final TextView[] tv_customTabView = new TextView[11];
        for (int i = 0; i < customTabView.length; i++) {
            customTabView[i] = inflater.inflate(R.layout.custom_tab_view, null, false);
            tv_customTabView[i] = (TextView) customTabView[i].findViewById(R.id.textView2);
        }

        periodo_selected = MainActivity.pref_horarios.getString("periodo", "0");
        Log.i(TAG, "onCreateView: today " + getTodayForAplication());
        dia_selected = String.valueOf(getTodayForAplication());

        for (int i = 0; i < dias_nome.length; i++) {
            tv_customTabView[i].setText(dias_nome[i]);
            tv_customTabView[i].setTextSize(14);
            if (i != Integer.parseInt(dia_selected)) {
                mTabLayoutTop.addTab(mTabLayoutTop.newTab().setCustomView(customTabView[i]));
            } else
                mTabLayoutTop.addTab(mTabLayoutTop.newTab().setCustomView(customTabView[i]), i, true);
        }

        for (int i = 0; i < IC_PERIODOS.length; i++) {
            if (i != (Integer.parseInt(periodo_selected) / 2)) {
                mTabLayoutBot.addTab(mTabLayoutBot.newTab().setIcon(IC_PERIODOS[i]));
            } else
                mTabLayoutBot.addTab(mTabLayoutBot.newTab().setIcon(IC_PERIODOS[i]), i, true);
        }

        mTabLayoutTop.setSelectedTabIndicatorHeight(6);
        mTabLayoutBot.setSelectedTabIndicatorHeight(6);
        reloadRecyclerView();

        mTabLayoutBot.setSelectedTabIndicatorColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
        mTabLayoutTop.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayoutBot.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected (TabLayout.Tab tab) {
                whatAnimation = false;
                periodo_selected = String.valueOf(tab.getPosition() * 2);
                reloadRecyclerView();
            }

            @Override
            public void onTabUnselected (TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected (TabLayout.Tab tab) {
            }
        });

        mTabLayoutTop.setSelectedTabIndicatorColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
        mTabLayoutTop.setTabTextColors(Color.GRAY, Color.WHITE);
        mTabLayoutTop.setTabMode(TabLayout.MODE_FIXED);
        mTabLayoutTop.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected (TabLayout.Tab tab) {
                whatAnimation = true;
                dia_selected = String.valueOf(tab.getPosition());
                reloadRecyclerView();
            }

            @Override
            public void onTabUnselected (TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected (TabLayout.Tab tab) {
            }
        });
        return view;
    }

    public void reloadRecyclerView () {
        BancoController crud = new BancoController(getActivity());
        Cursor cursor = crud.getHorarios(periodo_selected, dia_selected);
        List<Horarios> list = new ArrayList<>();
        for (int i = 0; i < cursor.getCount(); i++) {
            Horarios h = new Horarios(cursor.getString(0), cursor.getString(1));
            list.add(i, h);
            cursor.moveToNext();
        }
        SharedPreferences.Editor ed = MainActivity.pref_horarios.edit();
        ed.putString("periodo", periodo_selected);
        ed.commit();
        adapter = new HorariosAdapter(getActivity(), list);
        rv_horarios.setAdapter(adapter);
    }

    private int getTodayForAplication () {
        Calendar today = Calendar.getInstance();
        int day = today.get(Calendar.DAY_OF_WEEK);
        if (day == 1)
            return 0;
        else
            return (day - 2);
    }

    private void playBeginAnimation () {
        YoYo.with(Techniques.Landing)
                .duration(700)
                .playOn(view);
    }
}
