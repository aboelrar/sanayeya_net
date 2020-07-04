package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.home_services.Scenario_home_service_details.pattern;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import de.hdodenhof.circleimageview.CircleImageView;
import www.dm.sanayeya.net.R;
import www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_workshop_details.model.review_list;

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
        ShimmerFrameLayout container;
        CircleImageView profile_image;


        public view_holder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            comment = itemView.findViewById(R.id.commment);
            time = itemView.findViewById(R.id.time);
            ratingBar = itemView.findViewById(R.id.ratings);
            profile_image = itemView.findViewById(R.id.profile_image);

        }
    }
}
