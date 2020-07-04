package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_list.pattern;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.utils.utils_adapter;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_details.controller.industerial_details;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_list.model.industerial_model_list;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_list.model.service_list;

public class industerial_adapter extends RecyclerView.Adapter<industerial_adapter.view_holder>  {

    Context context;
    ArrayList<industerial_model_list> mylist;
    int pos;

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
        holder.desc.setText(mylist.get(position).getDesc());

        //SHIMMER FOR IMAGES
        Glide.with(context)
                .load(mylist.get(position).getImg())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.container.stopShimmerAnimation();
                        return false;
                    }
                })
                .into(holder.service_image);


        //SET ON CLICK LISTNERS
        holder.service_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, industerial_details.class);
                intent.putExtra("industerial_id", mylist.get(position).getId());
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }


    class view_holder extends RecyclerView.ViewHolder {
        TextView name, reviews;
        RatingBar ratingBar;
        TextView desc;
        LinearLayout service_item;
        ImageView service_image;
        ShimmerFrameLayout container;

        public view_holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.com_name);
            reviews = itemView.findViewById(R.id.review_num);
            ratingBar = itemView.findViewById(R.id.ratings);
            service_item = itemView.findViewById(R.id.service_item);
            desc = itemView.findViewById(R.id.description);
            service_image = itemView.findViewById(R.id.home_img);

            container = itemView.findViewById(R.id.shimmer_view_container);
            container.startShimmerAnimation();

        }
    }


}
