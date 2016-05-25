package br.almadaapps.civilapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

import br.almadaapps.civilapp.R;
import br.almadaapps.civilapp.domain.Horarios;
import br.almadaapps.civilapp.fragments.HorariosFragment;

/**
 * Created by viniciusalmada on 20/04/2016.
 */
public class HorariosAdapter extends RecyclerView.Adapter<HorariosAdapter.MyViewHolder> {
    private static List<Horarios> list;
    private LayoutInflater inflater;
    private Context context;

    public HorariosAdapter (Context context, List<Horarios> list) {
        this.context = context;
        HorariosAdapter.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.horarios_rv_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder, int position) {
        holder.tv_intervalos.setText(list.get(position).getIntervalo());
        holder.tv_disciplina.setText(list.get(position).getDisciplina());
        if (position % 2 == 0)
            holder.rl_item_rv_horarios.setBackgroundColor(Color.LTGRAY);
        else
            holder.rl_item_rv_horarios.setBackgroundColor(ContextCompat.getColor(context,R.color.bg_light_style));
        if (HorariosFragment.whatAnimation){
            playAnimationByDay(holder.itemView);
        } else
            playAnimationBySemester(holder.itemView);

    }

    @Override
    public int getItemCount () {
        return list.size();
    }

    public void playAnimationByDay(View v){
        try {
            YoYo.with(Techniques.FlipInX)
                    .duration(700)
                    .playOn(v);
        } catch ( Exception ignored ) {
        }
    }

    public void playAnimationBySemester(View v){
        try {
            YoYo.with(Techniques.FlipInX)
                    .duration(700)
                    .playOn(v);
        } catch ( Exception ignored ) {
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout rl_item_rv_horarios;
        public TextView tv_intervalos;
        public TextView tv_disciplina;

        public MyViewHolder (View itemView) {
            super(itemView);
            rl_item_rv_horarios = (RelativeLayout) itemView.findViewById(R.id.rl_item_rv_horarios);
            tv_disciplina = (TextView) itemView.findViewById(R.id.rv_item_disciplina);
            tv_intervalos = (TextView) itemView.findViewById(R.id.rv_item_horas);
        }
    }
}
