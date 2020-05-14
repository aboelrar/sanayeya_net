package www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_main_screen.Controller.Scenario_client_request.pattern;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_main_screen.Controller.Scenario_client_request.model.client_request_list;

public class client_request_adapter extends RecyclerView.Adapter<client_request_adapter.view_holder> {

    Context context;
    ArrayList<client_request_list> mylist;

    public client_request_adapter(Context context, ArrayList<client_request_list> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.client_request_item, parent, false);
        view_holder view_holder = new view_holder(view);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull view_holder holder, int position) {
        holder.request_id.setText(mylist.get(position).getRequest_id());
        holder.from.setText(mylist.get(position).getFrom());
        holder.to.setText(mylist.get(position).getTo());
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    class view_holder extends RecyclerView.ViewHolder {
        TextView request_id, from, to;

        public view_holder(@NonNull View itemView) {
            super(itemView);
            request_id = itemView.findViewById(R.id.request_id);
            from = itemView.findViewById(R.id.from);
            to = itemView.findViewById(R.id.to);
        }
    }
}
