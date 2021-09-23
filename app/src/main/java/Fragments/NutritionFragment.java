package Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.diabetes.R;

import com.example.diabetes.Tips;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.protobuf.StringValue;


public class NutritionFragment extends Fragment {
    private RecyclerView recyclerTips;
    private DatabaseReference mDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nutrition, container, false);
        changeStatusBarColor();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Tips");

        recyclerTips = view.findViewById(R.id.reclyclerTips);
        recyclerTips.setHasFixedSize(true);
        recyclerTips.setLayoutManager(new LinearLayoutManager(getActivity()));

        mDatabase.keepSynced(true);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Tips,TipViewHolder>firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Tips, TipViewHolder>
                (Tips.class,R.layout.cardview_item,TipViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(TipViewHolder viewHolder, Tips model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setColor(model.getColor());
                viewHolder.setImage(getActivity().getApplicationContext(), model.getImage());
            }
        };

        recyclerTips.setAdapter(firebaseRecyclerAdapter);
    }

    public static class TipViewHolder extends RecyclerView.ViewHolder{
        View mview;
        public TipViewHolder(View itemView){
            super(itemView);
            mview = itemView;
        }
        public void setTitle(String title){
            TextView tipTitle = (TextView) mview.findViewById(R.id.tipText);
            tipTitle.setText(title);
        }
        public void setImage(Context ctx, String image){
            ImageView tipImage = (ImageView)  mview.findViewById(R.id.tipImage);
            Glide.with(ctx).load(image).into(tipImage);
        }
        public void setColor(String color){
            LinearLayout tipColor = (LinearLayout) mview.findViewById(R.id.tipBackground);
            String concat= "#" + color;
            int color2 = Color.parseColor(concat);
            tipColor.setBackgroundColor(color2);

            Log.d("nashe",String.valueOf(color));
        }
    }

    public void changeStatusBarColor(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}