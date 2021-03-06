package me.crafter.android.zjsnviewer.ui.web;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.crafter.android.zjsnviewer.R;
import me.crafter.android.zjsnviewer.config.WebSite;

/**
 * @author traburiss
 * @date 2016/6/16
 * @info ZjsnViewer
 * @desc
 */

public class WebAdapter extends Adapter<WebAdapter.WebViewHolder>{

    private Context context;
    private ArrayList<HashMap<String, String>> list;

    public WebAdapter(Context context, ArrayList<HashMap<String, String>> list){

        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private String getName(int position){

        return list.get(position).get(WebSite.WEB_NAME);
    }

    private String getURL(int position){

        return list.get(position).get(WebSite.WEB_URL);
    }

    private String getIcon(int position){

        return list.get(position).get(WebSite.WEB_ICON);
    }

    @Override
    public WebViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new WebViewHolder(LayoutInflater.from(context).inflate(R.layout.item_web, parent, false));
    }

    @Override
    public void onBindViewHolder(WebViewHolder holder, final int position) {

        holder.tv_web_name.setText(getName(position));
        holder.rl_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse(getURL(position));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
            }
        });
        Glide.with(context).load(getIcon(position)).into(holder.im_web_icon);
    }

    public static class WebViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.rl_web)LinearLayout rl_web;
        @BindView(R.id.tv_web_name)TextView tv_web_name;
        @BindView(R.id.im_web_icon)ImageView im_web_icon;

        public WebViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
