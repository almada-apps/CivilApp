package br.almadaapps.civilapp.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import br.almadaapps.civilapp.R;
import br.almadaapps.civilapp.dbCore.BancoController;

/**
 * Created by viniciusalmada on 21/04/2016.
 */
public class EmentasFragment extends Fragment {
    private String[] array_disciplinas;
    private int[] disciplinas_id = new int[76];

    private ScrollView sv_ementas_fragment;
    private View view;
    private Spinner sp_periodo_ementas_fragment;
    private Spinner sp_disciplina_ementas_fragment;
    private ArrayAdapter<CharSequence> adapter_disciplinas;
    private ArrayAdapter<CharSequence> adapter_periodos;
    private CardView[] cv_containers = new CardView[5];

    private TextView tv_ementas_cabecalho;
    private TextView tv_ementas_ementa;
    private TextView tv_ementas_objetivo;
    private TextView tv_ementas_conteudo;
    private TextView tv_ementas_referencias;

    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ementas, container, false);
        playBeginAnimation();

        array_disciplinas = getResources().getStringArray(R.array.all_chairs);
        for (int i = 0; i < disciplinas_id.length; i++)
            disciplinas_id[i] = i + 1;

        sp_periodo_ementas_fragment = (Spinner) view.findViewById(R.id.sp_periodo_ementas_fragment);
        sp_disciplina_ementas_fragment = (Spinner) view.findViewById(R.id.sp_disciplina_ementas_fragment);
        sv_ementas_fragment = (ScrollView) view.findViewById(R.id.sv_ementas_fragment);

        adapter_periodos = ArrayAdapter.createFromResource(getActivity(), R.array.periodos, R.layout.spinner_custom_view);
        adapter_periodos.setDropDownViewResource(R.layout.spinner_popup_custom_view);
        sp_periodo_ementas_fragment.setAdapter(adapter_periodos);

        cv_containers[0] = (CardView) view.findViewById(R.id.cd_cabecalho);
        cv_containers[1] = (CardView) view.findViewById(R.id.cd_ementa);
        cv_containers[2] = (CardView) view.findViewById(R.id.cd_obejetivos);
        cv_containers[3] = (CardView) view.findViewById(R.id.cd_conteudo);
        cv_containers[4] = (CardView) view.findViewById(R.id.cd_referencias);

        tv_ementas_cabecalho = (TextView) view.findViewById(R.id.tv_ementas_cabecalho);
        tv_ementas_ementa = (TextView) view.findViewById(R.id.tv_ementas_ementa);
        tv_ementas_objetivo = (TextView) view.findViewById(R.id.tv_ementas_objetivo);
        tv_ementas_conteudo = (TextView) view.findViewById(R.id.tv_ementas_conteudo);
        tv_ementas_referencias = (TextView) view.findViewById(R.id.tv_ementas_referencias);

        playAnimations();
        sp_periodo_ementas_fragment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected (AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        adapter_disciplinas = ArrayAdapter.createFromResource(getActivity(), R.array._1, R.layout.spinner_custom_view);
                        adapter_disciplinas.setDropDownViewResource(R.layout.spinner_popup_custom_view);
                        sp_disciplina_ementas_fragment.setAdapter(adapter_disciplinas);
                        break;
                    case 1:
                        adapter_disciplinas = ArrayAdapter.createFromResource(getActivity(), R.array._2, R.layout.spinner_custom_view);
                        adapter_disciplinas.setDropDownViewResource(R.layout.spinner_popup_custom_view);
                        sp_disciplina_ementas_fragment.setAdapter(adapter_disciplinas);
                        break;
                    case 2:
                        adapter_disciplinas = ArrayAdapter.createFromResource(getActivity(), R.array._3, R.layout.spinner_custom_view);
                        adapter_disciplinas.setDropDownViewResource(R.layout.spinner_popup_custom_view);
                        sp_disciplina_ementas_fragment.setAdapter(adapter_disciplinas);
                        break;
                    case 3:
                        adapter_disciplinas = ArrayAdapter.createFromResource(getActivity(), R.array._4, R.layout.spinner_custom_view);
                        adapter_disciplinas.setDropDownViewResource(R.layout.spinner_popup_custom_view);
                        sp_disciplina_ementas_fragment.setAdapter(adapter_disciplinas);
                        break;
                    case 4:
                        adapter_disciplinas = ArrayAdapter.createFromResource(getActivity(), R.array._5, R.layout.spinner_custom_view);
                        adapter_disciplinas.setDropDownViewResource(R.layout.spinner_popup_custom_view);
                        sp_disciplina_ementas_fragment.setAdapter(adapter_disciplinas);
                        break;
                    case 5:
                        adapter_disciplinas = ArrayAdapter.createFromResource(getActivity(), R.array._6, R.layout.spinner_custom_view);
                        adapter_disciplinas.setDropDownViewResource(R.layout.spinner_popup_custom_view);
                        sp_disciplina_ementas_fragment.setAdapter(adapter_disciplinas);
                        break;
                    case 6:
                        adapter_disciplinas = ArrayAdapter.createFromResource(getActivity(), R.array._7, R.layout.spinner_custom_view);
                        adapter_disciplinas.setDropDownViewResource(R.layout.spinner_popup_custom_view);
                        sp_disciplina_ementas_fragment.setAdapter(adapter_disciplinas);
                        break;
                    case 7:
                        adapter_disciplinas = ArrayAdapter.createFromResource(getActivity(), R.array._8, R.layout.spinner_custom_view);
                        adapter_disciplinas.setDropDownViewResource(R.layout.spinner_popup_custom_view);
                        sp_disciplina_ementas_fragment.setAdapter(adapter_disciplinas);
                        break;
                    case 8:
                        adapter_disciplinas = ArrayAdapter.createFromResource(getActivity(), R.array._9, R.layout.spinner_custom_view);
                        adapter_disciplinas.setDropDownViewResource(R.layout.spinner_popup_custom_view);
                        sp_disciplina_ementas_fragment.setAdapter(adapter_disciplinas);
                        break;
                    case 9:
                        adapter_disciplinas = ArrayAdapter.createFromResource(getActivity(), R.array._10, R.layout.spinner_custom_view);
                        adapter_disciplinas.setDropDownViewResource(R.layout.spinner_popup_custom_view);
                        sp_disciplina_ementas_fragment.setAdapter(adapter_disciplinas);
                        break;
                }
            }

            @Override
            public void onNothingSelected (AdapterView<?> parent) {

            }
        });

        sp_disciplina_ementas_fragment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            BancoController crud = new BancoController(getActivity());
            Cursor cursor;

            @Override
            public void onItemSelected (AdapterView<?> parent, View view, int position, long id) {
                String disc;
                if (view != null) {
                    TextView tv = (TextView) view;
                    disc = tv.getText().toString();
                } else
                    disc = array_disciplinas[0];
                int disc_selected = 0;
                int i = 0;
                while (i < array_disciplinas.length) {
                    if (disc.equals(array_disciplinas[i])) {
                        disc_selected = disciplinas_id[i];
                        break;
                    }
                    i++;
                }
                cursor = crud.getEmentas(String.valueOf(disc_selected));
                playAnimations();
                if (cursor.getCount() != 0) {
                    tv_ementas_cabecalho.setText(cursor.getString(0));
                    tv_ementas_ementa.setText(cursor.getString(1));
                    tv_ementas_objetivo.setText(cursor.getString(2));
                    tv_ementas_conteudo.setText(cursor.getString(3));
                    tv_ementas_referencias.setText(cursor.getString(4));
                }
            }

            @Override
            public void onNothingSelected (AdapterView<?> parent) {

            }
        });


        return view;
    }

    public void playAnimations () {
        sv_ementas_fragment.scrollTo(0,0);
        for (int i = 0; i < cv_containers.length; i++) {
            try {
                YoYo.with(Techniques.ZoomInDown)
                        .duration(700)
                        .playOn(cv_containers[i]);
            } catch ( Exception ignored ) {
            }
        }
    }

    private void playBeginAnimation () {
        YoYo.with(Techniques.ZoomInRight)
                .duration(700)
                .playOn(view);
    }
}
