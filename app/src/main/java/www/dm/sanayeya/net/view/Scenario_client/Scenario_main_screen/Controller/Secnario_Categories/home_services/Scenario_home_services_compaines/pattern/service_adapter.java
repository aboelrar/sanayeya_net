package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.home_services.Scenario_home_services_compaines.pattern;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.home_services.Scenario_home_services_compaines.model.service_list;

public class service_adapter extends RecyclerView.Adapter<service_adapter.view_holder> {

    Context context;
    ArrayList<service_list> mylist;

    public service_adapter(Context context, ArrayList<service_list> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.service_item, parent, false);
        view_holder view_holder = new view_holder(view);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull view_holder holder, int position) {
        holder.title.setText(mylist.get(position).getTitle());

        //SET SPERATOR VISABILITY IF POSITION EQUAL ZERO SET IT GONE
        if(position == 0)
        {
            holder.sperator.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    class view_holder extends RecyclerView.ViewHolder {
        TextView title, sperator;

        public view_holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.service);
            sperator = itemView.findViewById(R.id.sperator);
        }
    }
}
