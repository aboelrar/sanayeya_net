package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_list.pattern;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.utils.utils_adapter;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_details.controller.industerial_details;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_list.model.industerial_model_list;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_list.model.service_list;

public class industerial_adapter extends RecyclerView.Adapter<industerial_adapter.view_holder> implements View.OnClickListener {

    Context context;
    ArrayList<industerial_model_list> mylist;

    public industerial_adapter(Context context, ArrayList<industerial_model_list> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_industerial_item, parent, false);
        view_holder view_holder = new view_holder(view);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull view_holder holder, int position) {
        holder.name.setText(mylist.get(position).getName());
        holder.reviews.setText(mylist.get(position).getReviews());
        holder.ratingBar.setRating(mylist.get(position).getRating());

        //SET DATA IN RECYCLERVIEW
        servies_list(holder.service_list);

        //SET ON CLICK LISTNERS
        holder.service_item.setOnClickListener(this);

    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    @Override
    public void onClick(View view) {
         if(view.getId() == R.id.service_item)
         {
             context.startActivity(new Intent(context, industerial_details.class));
         }
    }

    class view_holder extends RecyclerView.ViewHolder {
        TextView name, reviews;
        RatingBar ratingBar;
        RecyclerView service_list;
        LinearLayout service_item;
        public view_holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.com_name);
            reviews = itemView.findViewById(R.id.review_num);
            ratingBar = itemView.findViewById(R.id.ratings);
            service_list = itemView.findViewById(R.id.service_list);
            service_item = itemView.findViewById(R.id.service_item);
        }
    }

    void servies_list(RecyclerView services_list)
    {
        ArrayList<service_list> arrayList = new ArrayList<>();

        arrayList.add(new service_list("1","Car electration"));
        arrayList.add(new service_list("1","Car wash"));
        arrayList.add(new service_list("1","The switch"));

        new utils_adapter().Horozintal(services_list, new service_adapter(context, arrayList), context);

    }

}
