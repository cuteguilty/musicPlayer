package com.example.myapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class MusicList  extends RecyclerView.Adapter<MusicList.ViewHolder>{

    interface OnStateClickListener{
        void onStateClick(Muzic music, int position);
    }

    private final OnStateClickListener onClickListener;

    private final LayoutInflater inflater;
    private final List<Muzic> musics;

    MusicList(Context context, List<Muzic> music, OnStateClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.musics = music;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public MusicList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MusicList.ViewHolder holder, int position) {
        Muzic state = musics.get(position);
        holder.albomView.setImageResource(state.getAlbom());
        holder.nameView.setText(state.getName());
        holder.isopView.setText(state.getIsop());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onClickListener.onStateClick(state, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return musics.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView albomView;
        final TextView nameView, isopView;
        ViewHolder(View view){
            super(view);
            albomView = view.findViewById(R.id.flag);
            nameView = view.findViewById(R.id.name);
            isopView = view.findViewById(R.id.capital);
        }
    }
}