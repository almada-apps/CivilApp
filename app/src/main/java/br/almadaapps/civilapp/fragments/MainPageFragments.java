package br.almadaapps.civilapp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import br.almadaapps.civilapp.R;

/**
 * Created by viniciusalmada on 21/05/2016.
 */

public class MainPageFragments extends Fragment {

    public static final String link_ifma = "http://portal.ifma.edu.br/";
    public static final String link_qacad = "https://qacad.ifma.edu.br/";
    public static final String link_library = "http://acervo.ifma.edu.br/sophia_web/";
    public static final String link_suap = "https://suap.ifma.edu.br/accounts/login/?next=/";
    private View view;
    private Button btn_ifma;
    private Button btn_qacad;
    private Button btn_library;
    private Button btn_suap;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_page, container, false);
        btn_ifma = (Button) view.findViewById(R.id.btn_ifmasite);
        btn_qacad = (Button) view.findViewById(R.id.btn_qacad);
        btn_library = (Button) view.findViewById(R.id.btn_biblioteca);
        btn_suap = (Button) view.findViewById(R.id.btn_suap);

        return view;
    }

    @Override
    public void onResume () {
        super.onResume();
        YoYo.with(Techniques.FadeIn)
                .duration(500)
                .playOn(view);
        YoYo.with(Techniques.ZoomInLeft)
                .duration(700)
                .playOn(btn_ifma);
        YoYo.with(Techniques.ZoomInLeft)
                .duration(700)
                .playOn(btn_qacad);
        YoYo.with(Techniques.ZoomInLeft)
                .duration(700)
                .playOn(btn_library);
        YoYo.with(Techniques.ZoomInLeft)
                .duration(700)
                .playOn(btn_suap);

        btn_ifma.setOnClickListener(goToSite(link_ifma));
        btn_qacad.setOnClickListener(goToSite(link_qacad));
        btn_library.setOnClickListener(goToSite(link_library));
        btn_suap.setOnClickListener(goToSite(link_suap));
    }

    public View.OnClickListener goToSite (final String url) {
        return new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        };
    }
}
