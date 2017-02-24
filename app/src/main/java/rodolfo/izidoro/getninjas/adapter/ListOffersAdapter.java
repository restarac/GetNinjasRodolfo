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
import rodolfo.izidoro.getninjas.model.leads.UserLeadsModel;
import rodolfo.izidoro.getninjas.model.offers.OffersModel;

/**
 * Created by Rocketz on 23/02/2017.
 */

public class ListOffersAdapter extends RecyclerView.Adapter<ListOffersAdapter.LeadsViewHolder>{

    private List<OffersModel> offers;
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
        View offerStatus;

        public LeadsViewHolder(View view) {
            super(view);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.rl);
            title = (TextView) view.findViewById(R.id.title);
            nome = (TextView) view.findViewById(R.id.user_name);
            date = (TextView) view.findViewById(R.id.date);
            location = (TextView) view.findViewById(R.id.location);
            offerStatus = view.findViewById(R.id.offer_status);

        }

    }

    public ListOffersAdapter(List<OffersModel> offers, int rowLayout, Context context) {

        this.offers = offers;
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
        OffersModel o = offers.get(position);

         holder.title.setText(o.get_embedded().getRequest().getTitle());
          holder.nome.setText(o.get_embedded().getRequest().get_embedded().getUser().getName());
        try {
            holder.date.setText(createDate(o.get_embedded().getRequest().getCreated_at()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.location.setText( o.get_embedded().getRequest().get_embedded().getAddress().getNeighborhood()  + " - " +  o.get_embedded().getRequest().get_embedded().getAddress().getCity());

        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            if(o.getState().equalsIgnoreCase("read"))
            holder.offerStatus.setBackgroundDrawable( context.getResources().getDrawable(R.drawable.read_indicator) );
            else
                holder.offerStatus.setBackgroundDrawable( context.getResources().getDrawable(R.drawable.unread_indicator) );
        } else {
            if(o.getState().equalsIgnoreCase("read"))
            holder.offerStatus.setBackground( context.getResources().getDrawable(R.drawable.read_indicator));
            else
                holder.offerStatus.setBackground( context.getResources().getDrawable(R.drawable.unread_indicator));

        }

//        address = embedded.getAddress();
//
//        holder.nome.setText(o.get_embedded().getRequest().getTitle());
//        try {
//            holder.date.setText(createDate(l.getCreated_at()));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        holder.location.setText(address.getNeighborhood() + "-" + address.getCity());
//
//
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    private String createDate(String formattedDate) throws ParseException {
        DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
        return outputDateFormat.format(inputDateFormat.parse(formattedDate));

    }

    public void clear() {
        offers.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<OffersModel> list) {
        offers.addAll(list);
        notifyDataSetChanged();
    }

}
