package com.example.administrator.xiaodemo3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiaodemo3.clazz.Mybean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6.
 */

public class MyrecyckerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Mybean.ResultBean.DataBean> data;
    private Context mContext;
    OncliclListener listener;

    public MyrecyckerAdapter(List<Mybean.ResultBean.DataBean> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {

        int type=0;
        if(!"".equals(data.get(position).getThumbnail_pic_s03())){
            type=3;
        }else if(!"".equals(data.get(position).getThumbnail_pic_s02())){
            type=2;
        }else {
            type=1;
        }



        return type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder=null;
        switch (viewType){
            case 1:
                View inflate = LayoutInflater.from(mContext).inflate(R.layout.iten_one, parent,false);
                holder = new OneViewHolder(inflate);
                break;

            case 2:
                View inflate1 = LayoutInflater.from(mContext).inflate(R.layout.iten_two, parent,false);
                holder = new TwoViewHolder(inflate1);
                break;

            case 3:
                View inflate2 = LayoutInflater.from(mContext).inflate(R.layout.iten_three, parent,false);
                holder = new ThreeViewHolder(inflate2);
                break;
        }


        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(!"".equals(data.get(position).getThumbnail_pic_s03())){

            ((ThreeViewHolder)holder).title.setText(data.get(position).getTitle());
            Picasso.with(mContext).load(data.get(position).getThumbnail_pic_s()).into(((ThreeViewHolder) holder).img);
            Picasso.with(mContext).load(data.get(position).getThumbnail_pic_s02()).into(((ThreeViewHolder) holder).img2);
            Picasso.with(mContext).load(data.get(position).getThumbnail_pic_s03()).into(((ThreeViewHolder) holder).img3);

        }else if(!"".equals(data.get(position).getThumbnail_pic_s02())){

            ((TwoViewHolder)holder).title.setText(data.get(position).getTitle());
            Picasso.with(mContext).load(data.get(position).getThumbnail_pic_s()).into(((TwoViewHolder) holder).img);
            Picasso.with(mContext).load(data.get(position).getThumbnail_pic_s02()).into(((TwoViewHolder) holder).img2);

        }else {

            ((OneViewHolder)holder).title.setText(data.get(position).getTitle());
            Picasso.with(mContext).load(data.get(position).getThumbnail_pic_s()).into(((OneViewHolder) holder).img);

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onclick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class OneViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView title;

        public OneViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.text_title);
        }
    }
    class TwoViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final ImageView img2;
        private final TextView title;

        public TwoViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            img2 = itemView.findViewById(R.id.img2);
            title = itemView.findViewById(R.id.text_title);
        }
    }
    class ThreeViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final ImageView img2;
        private final ImageView img3;
        private final TextView title;

        public ThreeViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            img2 = itemView.findViewById(R.id.img2);
            img3 = itemView.findViewById(R.id.img3);
            title = itemView.findViewById(R.id.text_title);
        }

    }
    public interface OncliclListener{
        void onclick(int position);
    }
    public void setOncliclListener(OncliclListener listener){
        this.listener=listener;
    }
}
