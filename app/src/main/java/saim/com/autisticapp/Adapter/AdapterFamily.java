package saim.com.autisticapp.Adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import saim.com.autisticapp.Model.ModelFamily;
import saim.com.autisticapp.R;
import saim.com.autisticapp.Util.DBHelper;

public class AdapterFamily extends RecyclerView.Adapter<AdapterFamily.FamilyViewHolder> {

    ArrayList<ModelFamily> adapterList = new ArrayList<>();
    Context mContext;

    public static String post_id = "";

    public AdapterFamily(ArrayList<ModelFamily> adapterList) {
        this.adapterList = adapterList;
    }

    public AdapterFamily(ArrayList<ModelFamily> adapterList, Context mContext) {
        this.adapterList = adapterList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public FamilyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        FamilyViewHolder familyViewHolder = new FamilyViewHolder(view);
        return familyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FamilyViewHolder holder, int position) {

        holder.listTextName.setText(adapterList.get(position).name);
        holder.listTextName.setHint(adapterList.get(position).id);
        holder.listTextRelation.setText(adapterList.get(position).relation);

        //String path = mContext.getExternalCacheDir().getPath() + adapterList.get(position).image;
        String path2 = holder.listImgSound.getContext().getExternalCacheDir().getPath() + "/" + adapterList.get(position).image + ".jpg";
        holder.listImage.setImageURI(Uri.parse(path2));

    }

    @Override
    public int getItemCount() {
        return adapterList.size();
    }


    public class FamilyViewHolder extends RecyclerView.ViewHolder {

        ImageView listImage, listImgSound, listImgDelete;
        TextView listTextName, listTextRelation;

        public FamilyViewHolder(View itemView) {
            super(itemView);

            listImage = (ImageView) itemView.findViewById(R.id.listImage);
            listTextName = (TextView) itemView.findViewById(R.id.listTextName);
            listTextRelation = (TextView) itemView.findViewById(R.id.listTextRelation);
            listImgSound = (ImageView) itemView.findViewById(R.id.listImgSound);
            listImgDelete = (ImageView) itemView.findViewById(R.id.listImgDelete);


            listImgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new DBHelper(v.getContext()).deleteContact(Integer.parseInt(String.valueOf(listTextName.getHint())));

                    adapterList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(), adapterList.size());
                }
            });


            listImgSound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    try {
                        Log.d("SAIM_LOG_FAMILY", v.getContext().getExternalCacheDir() + File.separator + adapterList.get(getAdapterPosition()).getSound());
                        mediaPlayer.setDataSource(v.getContext().getExternalCacheDir() + File.separator + adapterList.get(getAdapterPosition()).getSound());
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.stop();
                            mp.release();
                        }
                    });
                }
            });

        }

    }



}
