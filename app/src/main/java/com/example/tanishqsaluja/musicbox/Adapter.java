package com.example.tanishqsaluja.musicbox;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.text.LoginFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/**
 * Created by tanishqsaluja on 29/1/18.
 */
// DONT MAKE MEDIPLAYER GLOBAL OTHERWISE ON PAUSE OF ANY ONE ,THE OTHER PLAYER GETS STOPPED TOO
public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
    ArrayList<List> arrayList;
    Context c;
    Adapter(ArrayList<List> list, Context context){
        this.arrayList=list;
        this.c=context;
    }
    class Holder extends RecyclerView.ViewHolder {
        FloatingActionButton play,pause,stop;
        MediaPlayer mediaPlayer;
        public Holder(View itemView) {
            super(itemView);
            mediaPlayer=MediaPlayer.create(c,R.raw.coming);
            play=itemView.findViewById(R.id.fab1);
            pause=itemView.findViewById(R.id.fab2);
            stop=itemView.findViewById(R.id.fab3);
        }
    }

    @Override
    public Adapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false));
    }

    @Override
    public void onBindViewHolder(Adapter.Holder holder, int position) {
        List list=arrayList.get(position);
        final MediaPlayer mediaPlayer;
        mediaPlayer=MediaPlayer.create(c,list.getPlay());
        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });
        holder.pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });
        holder.stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(0);
                mediaPlayer.start();
            }
        });
       /* if (mediaPlayer==null){
            mediaPlayer.release();
            Log.e("TEST", "ALIVE");
        }
        else if(mediaPlayer!=null){
            Log.e("TEST", "notALIVE");
        }
        else{
            Log.e("TEST","Confused");
        }
        try {
            if (mediaPlayer != null) {
                if (mediaPlayer.isPlaying())
                    mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
