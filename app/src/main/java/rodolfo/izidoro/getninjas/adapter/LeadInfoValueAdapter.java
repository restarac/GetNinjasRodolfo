package rodolfo.izidoro.getninjas.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import rodolfo.izidoro.getninjas.R;

/**
 * Created by Rocketz on 24/02/2017.
 */

public class LeadInfoValueAdapter extends RecyclerView.Adapter<LeadInfoValueAdapter.LeadViewHolder> {


    //Como tem casos que podem vir 2 informacoes de resposta no Info, foi necessario criar um adapte para o valor da resposta

    private List<String> values;
    private int rowLayout;
    private Context context;

    public class LeadViewHolder extends RecyclerView.ViewHolder {

        TextView value;

        public LeadViewHolder(View view) {
            super(view);
            value = (TextView) view.findViewById(R.id.value);
        }

    }
    public LeadInfoValueAdapter(List<String> values, int rowLayout, Context context) {
        this.values = values;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public LeadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new LeadInfoValueAdapter.LeadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LeadViewHolder holder, int position) {

        holder.value.setText(values.get(position));

    }

    @Override
    public int getItemCount() {
        return values.size();
    }


}
