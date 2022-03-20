package com.example.hellowrod;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

//import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
//import com.bumptech.glide.request.RequestOptions;
import com.example.hellowrod.open.source.*;

import java.util.ArrayList;
import java.util.List;
//import com.bumptech.glide.Glide;


public class MainActivity extends AppCompatActivity {

    public boolean requestFinished = true;

    private Banner banner;

    public static final int[] IMAGES = {
            R.mipmap.image1,
            R.mipmap.image2,
            R.mipmap.image3,
            R.mipmap.image4
    };

    public static List<Integer> getImage(int count) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(IMAGES[i]);
        }
        return list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView searchTextView = findViewById(R.id.dpp_cc4_search);
        Drawable drawable = getResources().getDrawable(R.drawable.test);
        drawable.setBounds(0,0,40,40);
        searchTextView.setCompoundDrawables(drawable,null,null,null);

        initBanner();

        LinearLayout bottomContainer = findViewById(R.id.dpp_cc4_bottom_container);
        String[] strings = {"Test1","Text2","text3","text4","text5"};
        for (String item:strings){
            TextView textView = createBottomItem(this,item,R.drawable.test);
            bottomContainer.addView(textView);
        }

    }

    private void initBanner(){
        Banner banner = findViewById(R.id.dpp_cc4_list_banner);
        banner.setIndicator(new IndicatorView(this)
                .setIndicatorColor(Color.GRAY)
                .setIndicatorSelectorColor(Color.WHITE))
                .setOuterPageChangeListener(
                        new ViewPager2.OnPageChangeCallback(){
                            @Override
                            public void onPageSelected(int position) {
                                super.onPageSelected(position);
                                //
                            }
                        }
                ).setAdapter(new ImageAdapter(getImage(IMAGES.length)));
    }

    private TextView createBottomItem(Context context,String title,int resourceId) {
        Drawable drawable = getResources().getDrawable(resourceId);
        drawable.setBounds(0,8,40,40);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.weight = 1;

        TextView textView = new TextView(context);
        textView.setTag(0);
        textView.setLayoutParams(layoutParams);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setPaddingRelative(0,8,0,0);
        textView.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
        textView.setText(title);
        textView.setCompoundDrawables(null,drawable,null,null);
        return textView;
    }

    static class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<Integer> items;

        ImageAdapter(List<Integer> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner_imager, parent, false);
            return new ImageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ImageViewHolder imageViewHolder = (ImageViewHolder) holder;

//            Glide.with(imageViewHolder.image)
//                    .load(items.get(position))
//                    .apply(new RequestOptions()
//                            .transform(new RoundedCorners(10)))
//                    .into(imageViewHolder.image);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;

        ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img);
        }
    }
}