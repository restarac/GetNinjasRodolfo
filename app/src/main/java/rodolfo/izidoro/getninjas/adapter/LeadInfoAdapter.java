package rodolfo.izidoro.getninjas.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rodolfo.izidoro.getninjas.R;
import rodolfo.izidoro.getninjas.model.InfoModel;
import rodolfo.izidoro.getninjas.model.leads.UserLeadsModel;
import rodolfo.izidoro.getninjas.model.AddressModel;
import rodolfo.izidoro.getninjas.model.leads.EmbeddedLeadsModel;

/**
 * Created by Rocketz on 24/02/2017.
 */

public class LeadInfoAdapter extends RecyclerView.Adapter<LeadInfoAdapter.LeadViewHolder> {

    private List<InfoModel> infos;
    private int rowLayout;
    private Context context;




    private EmbeddedLeadsModel embedded;
    private UserLeadsModel user;
    private AddressModel address;


    //Adapter para pegar as Infos,


    public LeadInfoAdapter(List<InfoModel> infos, int rowLayout, Context context) {
        this.infos = infos;
        this.rowLayout = rowLayout;
        this.context = context;
    }
    public static class LeadViewHolder extends RecyclerView.ViewHolder {

        TextView label;
        RecyclerView recyclerView;

        public LeadViewHolder(View view) {
            super(view);
            label = (TextView) view.findViewById(R.id.label);

            recyclerView= (RecyclerView) view.findViewById(R.id.values);


        }

    }
    @Override
    public LeadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new LeadInfoAdapter.LeadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LeadViewHolder holder, int position) {

        InfoModel info = infos.get(position);
        List<String> lstValues = new ArrayList<>();

        holder.label.setText(info.getLabel());


        //Como o json pode vir String ou List<string> foi necessario utilizar o object e fazer o parse na mão.
        if(info.getValue() instanceof String)
            lstValues.add((String) info.getValue());
        else
        lstValues = (List<String>) info.getValue();

        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerView.setAdapter(new LeadInfoValueAdapter(lstValues,R.layout.info_value_lead,context));

    }

    @Override
    public int getItemCount() {
        return infos.size();
    }


}
