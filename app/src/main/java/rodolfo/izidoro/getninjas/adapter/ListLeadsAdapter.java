package rodolfo.izidoro.getninjas.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import rodolfo.izidoro.getninjas.R;
import rodolfo.izidoro.getninjas.model.AddressModel;
import rodolfo.izidoro.getninjas.model.leads.EmbeddedLeadsModel;
import rodolfo.izidoro.getninjas.model.leads.LeadsModel;
import rodolfo.izidoro.getninjas.model.leads.UserLeadsModel;

/**
 * Created by Rocketz on 23/02/2017.
 */

public class ListLeadsAdapter  extends RecyclerView.Adapter<ListLeadsAdapter.LeadsViewHolder>{

    private List<LeadsModel> leads;
    private int rowLayout;
    private Context context;

    private EmbeddedLeadsModel embedded;
    private UserLeadsModel user;
    private AddressModel address;


    public static class LeadsViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout relativeLayout;
        TextView title;
        TextView nome;
        TextView date;
        TextView location;

        public LeadsViewHolder(View view) {
            super(view);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.rl);
            title = (TextView) view.findViewById(R.id.title);
            nome = (TextView) view.findViewById(R.id.user_name);
            date = (TextView) view.findViewById(R.id.date);
            location = (TextView) view.findViewById(R.id.location);
        }

    }

    public void clear() {
        leads.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<LeadsModel> list) {
        leads.addAll(list);
        notifyDataSetChanged();
    }

    public ListLeadsAdapter(List<LeadsModel> leads, int rowLayout, Context context) {

        this.leads = leads;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public LeadsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new LeadsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LeadsViewHolder holder, int position) {
        LeadsModel l = leads.get(position);
        embedded = l.get_embedded();
//        user = embedded.getUser();
        address = embedded.getAddress();


        holder.nome.setText(l.get_embedded().getUser().getName());
        holder.title.setText(l.get_embedded().getRequest().getTitle());
        try {
            holder.date.setText(createDate(l.getCreated_at()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.location.setText(address.getNeighborhood() + " - " + address.getCity());
    }

    @Override
    public int getItemCount() {
        return leads.size();
    }

    private String createDate(String formattedDate) throws ParseException {
        DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
        return outputDateFormat.format(inputDateFormat.parse(formattedDate));

    }


}
