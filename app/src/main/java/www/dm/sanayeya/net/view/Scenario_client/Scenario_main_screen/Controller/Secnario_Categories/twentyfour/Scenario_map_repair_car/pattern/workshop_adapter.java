package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_map_repair_car.pattern;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_map_repair_car.model.workshop_list;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_workshop_details.controller.workshop_details;

public class workshop_adapter extends RecyclerView.Adapter<workshop_adapter.workshop_holder> {

    Context context;
    ArrayList<workshop_list> mylist;

    public workshop_adapter(Context context, ArrayList<workshop_list> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public workshop_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.workshop_item, parent, false);
        workshop_holder workshop_holder = new workshop_holder(view);
        return workshop_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull workshop_holder holder, int position) {
        holder.name.setText(mylist.get(position).getName());
        holder.address.setText(mylist.get(position).getAddress());
        holder.review.setText(mylist.get(position).getReview());
        holder.ratingBar.setRating(mylist.get(position).getNum());
        Glide.with(context).load(mylist.get(position).getImage()).into(holder.workshop_img);

        //SET ON CLICK LISTNERS
        holder.workshop_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, workshop_details.class);
                intent.putExtra("shop_id",mylist.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }


    class workshop_holder extends RecyclerView.ViewHolder {
        LinearLayout workshop_item;
        TextView name, address, review;
        RatingBar ratingBar;
        ImageView workshop_img;

        public workshop_holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            review = itemView.findViewById(R.id.review_num);
            ratingBar = itemView.findViewById(R.id.ratings);
            workshop_item = itemView.findViewById(R.id.workshop_item);
            workshop_img = itemView.findViewById(R.id.workshop_img);
        }
    }
}
