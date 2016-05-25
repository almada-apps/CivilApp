package br.almadaapps.civilapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import br.almadaapps.civilapp.R;
import br.almadaapps.civilapp.adapters.MonografiasAdapter;
import br.almadaapps.civilapp.domain.Monografias;
import br.almadaapps.civilapp.extras.RecyclerViewOnClickListenerHack;

/**
 * Created by viniciusalmada on 22/05/2016.
 */

public class MonografiasFragments extends Fragment implements RecyclerViewOnClickListenerHack {

    private View view;
    private RecyclerView rv_monografias;
    private Spinner sp_order;
    private MonografiasAdapter adapter;
    private List<Monografias> list;
    private String[] links_monografias;

    private int[] links_monografia_titulo = {27, 26, 25, 43, 36, 11, 8, 23, 24, 10, 40, 34, 15, 3, 13, 42, 9, 12, 19, 4, 35, 38, 14, 37, 28, 31, 6, 30, 21, 16, 33, 5, 0, 41, 20, 29, 44, 7, 2, 22, 17, 18, 39, 32, 1};

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_monografias, container, false);
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Monografias");
        alert.setMessage("Esta seção exibe a lista de monografias já apresentadas ordenadas por autor ou título, ao clicar em um desses itens, você será direcionado para a página de downoad da monografia no formato PDF.");
        alert.setNeutralButton("Ok",null);
        alert.show();

        list = new ArrayList<>();

        rv_monografias = (RecyclerView) view.findViewById(R.id.rv_monografias);
        rv_monografias.setHasFixedSize(true);


        LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv_monografias.setLayoutManager(llm);
        rv_monografias.addOnItemTouchListener(new RecyclerViewTouchListener(getActivity(), rv_monografias, this));

        sp_order = (Spinner) view.findViewById(R.id.sp_frag_mono_order);
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(getActivity(), R.array.orderBy, R.layout.spinner_custom_view);
        adapterSpinner.setDropDownViewResource(R.layout.spinner_popup_custom_view);
        sp_order.setAdapter(adapterSpinner);

        sp_order.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected (AdapterView<?> parent, View view, int position, long id) {
                list = getList(position);
                adapter = new MonografiasAdapter(getActivity(), list);
                rv_monografias.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected (AdapterView<?> parent) {

            }
        });
        return view;
    }

    private List<Monografias> getList (int showBy) {
        String[] aux = new String[0];
        switch (showBy) {
            case 0:
                aux = getResources().getStringArray(R.array.lista_monografia_autor);
                break;
            case 1:
                aux = getResources().getStringArray(R.array.lista_monografia_titulo);
                break;
        }
        List<Monografias> listAux = new ArrayList<>();
        for (int i = 0; i < aux.length; i++) {
            Monografias m = new Monografias(aux[i]);
            listAux.add(m);
        }
        return listAux;
    }

    @Override
    public void onClickListener (View view, int position) throws InterruptedException {
        links_monografias = getResources().getStringArray(R.array.links_monografia);
        if (sp_order.getSelectedItemPosition() == 0) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(links_monografias[position]));
            startActivity(browserIntent);
        } else if (sp_order.getSelectedItemPosition() == 1){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(links_monografias[links_monografia_titulo[position]]));
            startActivity(browserIntent);
        }
    }

    private static class RecyclerViewTouchListener implements RecyclerView.OnItemTouchListener {
        private Context context;
        private GestureDetector gestureDetector;
        private RecyclerViewOnClickListenerHack recyclerViewOnClickListenerHack;

        public RecyclerViewTouchListener (Context context, final RecyclerView recyclerView, final RecyclerViewOnClickListenerHack recyclerViewOnClickListenerHack) {
            this.context = context;
            this.recyclerViewOnClickListenerHack = recyclerViewOnClickListenerHack;

            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp (MotionEvent e) {
                    View cv = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (cv != null && recyclerViewOnClickListenerHack != null) {
                        try {
                            recyclerViewOnClickListenerHack.onClickListener(cv, recyclerView.getChildPosition(cv));
                        } catch ( InterruptedException e1 ) {
                            e1.printStackTrace();
                        }
                    }
                    return true;
                }
            });

        }

        @Override
        public boolean onInterceptTouchEvent (RecyclerView rv, MotionEvent e) {
            gestureDetector.onTouchEvent(e);
            return false;
        }

        @Override
        public void onTouchEvent (RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent (boolean disallowIntercept) {

        }
    }
}
