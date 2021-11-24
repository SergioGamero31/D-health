package com.example.diabetes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;
    int images[]={
            R.drawable.ic_sugar_level,
            R.drawable.ic_working_out,
            R.drawable.ic_medical_care,
            R.drawable.ic_personal_goals
    };

    int headings[]={
            R.string.heading_1,
            R.string.heading_2,
            R.string.heading_3,
            R.string.heading_4
    };

    int desc[]={
            R.string.desc_1,
            R.string.desc_2,
            R.string.desc_3,
            R.string.desc_4
    };

    public ViewPagerAdapter(Context context){

        this.context = context;
    }

    @Override
    public int getCount() {

        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout, container,false);

        ImageView sliderImage = (ImageView) view.findViewById(R.id.sliderImage);
        TextView sliderTitle = (TextView) view.findViewById(R.id.sliderTitle);
        TextView sliderDesc = (TextView) view.findViewById(R.id.sliderDesc);

        sliderImage.setImageResource(images[position]);
        sliderTitle.setText(headings[position]);
        sliderDesc.setText(desc[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((LinearLayout)object);
    }
}
