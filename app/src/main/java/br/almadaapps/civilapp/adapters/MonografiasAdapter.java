package br.almadaapps.civilapp.adapters;

import android.content.Context;
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
import br.almadaapps.civilapp.domain.Monografias;

/**
 * Created by viniciusalmada on 22/05/2016.
 */

public class MonografiasAdapter extends RecyclerView.Adapter<MonografiasAdapter.MyViewHolder> {
    private List<Monografias> list;
    private LayoutInflater layoutInflater;
    private Context context;

    public MonografiasAdapter(Context context, List<Monografias> list){
        this.list = list;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.monografia_rv_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder, int position) {
        holder.tv_monografia_item.setText(list.get(position).getAuthorOrTitle());
        YoYo.with(Techniques.ZoomInDown)
                .duration(700)
                .playOn(holder.rl_monografias_container_list);
    }

    @Override
    public int getItemCount () {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_monografia_item;
        public RelativeLayout rl_monografias_container_list;
        public MyViewHolder (View itemView) {
            super(itemView);
            tv_monografia_item = (TextView) itemView.findViewById(R.id.tv_monografias_item);
            rl_monografias_container_list = (RelativeLayout) itemView.findViewById(R.id.rl_monografias_container_list);
        }
    }
}
