package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_details.pattern;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_details.model.review_list;

public class reviews_adapter extends RecyclerView.Adapter<reviews_adapter.view_holder> {

    Context context;
    ArrayList<review_list> mylist;

    public reviews_adapter(Context context, ArrayList<review_list> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.review_item, parent, false);
        view_holder view_holder = new view_holder(view);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull view_holder holder, int position) {
       holder.name.setText(mylist.get(position).getName());
       holder.comment.setText(mylist.get(position).getComment());
       holder.time.setText(mylist.get(position).getTime());
       holder.ratingBar.setRating(mylist.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public class view_holder extends RecyclerView.ViewHolder {
        TextView name, comment, time;
        RatingBar ratingBar;
        public view_holder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            comment = itemView.findViewById(R.id.commment);
            time = itemView.findViewById(R.id.time);
            ratingBar = itemView.findViewById(R.id.ratings);
        }
    }
}
