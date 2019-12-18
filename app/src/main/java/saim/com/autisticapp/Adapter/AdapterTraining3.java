package saim.com.autisticapp.Adapter;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

import saim.com.autisticapp.Model.ModelFamily;
import saim.com.autisticapp.R;
import saim.com.autisticapp.Util.SharedPrefDatabase;

public class AdapterTraining3 extends RecyclerView.Adapter<AdapterTraining3.TrainingViewHolder> {

    ArrayList<ModelFamily> adapterList = new ArrayList<>();
    Context mContext;

    public static String post_id = "";

    public AdapterTraining3(ArrayList<ModelFamily> adapterList) {
        this.adapterList = adapterList;
    }

    public AdapterTraining3(ArrayList<ModelFamily> adapterList, Context mContext) {
        this.adapterList = adapterList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public TrainingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_train, parent, false);
        TrainingViewHolder familyViewHolder = new TrainingViewHolder(view);
        return familyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingViewHolder holder, int position) {


        if (new SharedPrefDatabase(holder.listImage.getContext()).RetriveLanguage().equals("BN")) {

            if (adapterList.get(position).name.equals("Neutral")) {
                holder.listTextName.setText(R.string.expres_neutral);
            } else if (adapterList.get(position).name.equals("Happy")) {
                holder.listTextName.setText(R.string.expres_happy);
            } else if (adapterList.get(position).name.equals("Sad")) {
                holder.listTextName.setText(R.string.expres_sad);
            } else if (adapterList.get(position).name.equals("Surprised")) {
                holder.listTextName.setText(R.string.expres_surprised);
            } else if (adapterList.get(position).name.equals("Angry")) {
                holder.listTextName.setText(R.string.expres_angry);
            } else if (adapterList.get(position).name.equals("Disgusted")) {
                holder.listTextName.setText(R.string.expres_disgusted);
            } else if (adapterList.get(position).name.equals("Fear")) {
                holder.listTextName.setText(R.string.expres_fear);
            }
            holder.listTextName.setHint(adapterList.get(position).id);
            holder.listTextRelation.setText("");
        } else if (new SharedPrefDatabase(holder.listImage.getContext()).RetriveLanguage().equals("EN")) {
            holder.listTextName.setText(adapterList.get(position).name);
            holder.listTextName.setHint(adapterList.get(position).id);
            holder.listTextRelation.setText("");
        }


        //String path = mContext.getExternalCacheDir().getPath() + adapterList.get(position).image;
        int path2 = holder.listImage.getContext().getResources().getIdentifier(adapterList.get(position).image, "drawable", holder.listImage.getContext().getPackageName());


        holder.listImage.setImageResource(path2);

    }

    @Override
    public int getItemCount() {
        return adapterList.size();
    }


    public class TrainingViewHolder extends RecyclerView.ViewHolder {

        ImageView listImage, listImgSound;
        TextView listTextName, listTextRelation;

        public TrainingViewHolder(View itemView) {
            super(itemView);

            listImage = (ImageView) itemView.findViewById(R.id.listImage);
            listTextName = (TextView) itemView.findViewById(R.id.listTextName);
            listTextRelation = (TextView) itemView.findViewById(R.id.listTextRelation);
            listImgSound = (ImageView) itemView.findViewById(R.id.listImgSound);

            //actionEventSound(listImgSound, listTextName);


            listImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (new SharedPrefDatabase(v.getContext()).RetriveLanguage().equals("BN")) {

                        if (adapterList.get(getAdapterPosition()).name.equals("Neutral")) {
                            actionEventSoundNew(v.getContext(), "Natural_BN.mp3");
                        } else if (adapterList.get(getAdapterPosition()).name.equals("Happy")) {
                            actionEventSoundNew(v.getContext(), "happy_bn.mp3");
                        } else if (adapterList.get(getAdapterPosition()).name.equals("Sad")) {
                            actionEventSoundNew(v.getContext(), "sad_bn.mp3");
                        } else if (adapterList.get(getAdapterPosition()).name.equals("Surprised")) {
                            actionEventSoundNew(v.getContext(), "surprised_bn.mp3");
                        } else if (adapterList.get(getAdapterPosition()).name.equals("Angry")) {
                            actionEventSoundNew(v.getContext(), "angry_bn.mp3");
                        } else if (adapterList.get(getAdapterPosition()).name.equals("Disgusted")) {
                            actionEventSoundNew(v.getContext(), "disgusting_bn.mp3");
                        } else if (adapterList.get(getAdapterPosition()).name.equals("Fear")) {
                            actionEventSoundNew(v.getContext(), "fear_bn.mp3");
                        }

                    } else if (new SharedPrefDatabase(v.getContext()).RetriveLanguage().equals("EN")) {

                        if (adapterList.get(getAdapterPosition()).name.equals("Neutral")) {
                            actionEventSoundNew(v.getContext(), "Natural_EN.mp3");
                        } else if (adapterList.get(getAdapterPosition()).name.equals("Happy")) {
                            actionEventSoundNew(v.getContext(), "happy_en.mp3");
                        } else if (adapterList.get(getAdapterPosition()).name.equals("Sad")) {
                            actionEventSoundNew(v.getContext(), "sad_en.mp3");
                        } else if (adapterList.get(getAdapterPosition()).name.equals("Surprised")) {
                            actionEventSoundNew(v.getContext(), "surprise_en.mp3");
                        } else if (adapterList.get(getAdapterPosition()).name.equals("Angry")) {
                            actionEventSoundNew(v.getContext(), "angry_en.mp3");
                        } else if (adapterList.get(getAdapterPosition()).name.equals("Disgusted")) {
                            actionEventSoundNew(v.getContext(), "disgusting_en.mp3");
                        } else if (adapterList.get(getAdapterPosition()).name.equals("Fear")) {
                            actionEventSoundNew(v.getContext(), "fear_en.mp3");
                        }

                    }

                }
            });
            listImgSound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (new SharedPrefDatabase(v.getContext()).RetriveLanguage().equals("BN")) {

                        if (adapterList.get(getAdapterPosition()).name.equals("Neutral")) {
                            actionEventSoundNew(v.getContext(), "Natural_BN.mp3");
                        } else if (adapterList.get(getAdapterPosition()).name.equals("Happy")) {
                            actionEventSoundNew(v.getContext(), "happy_bn.mp3");
                        } else if (adapterList.get(getAdapterPosition()).name.equals("Sad")) {
                            actionEventSoundNew(v.getContext(), "sad_bn.mp3");
                        } else if (adapterList.get(getAdapterPosition()).name.equals("Surprised")) {
                            actionEventSoundNew(v.getContext(), "surprised_bn.mp3");
                        } else if (adapterList.get(getAdapterPosition()).name.equals("Angry")) {
                            actionEventSoundNew(v.getContext(), "angry_bn.mp3");
                        } else if (adapterList.get(getAdapterPosition()).name.equals("Disgusted")) {
                            actionEventSoundNew(v.getContext(), "disgusting_bn.mp3");
                        } else if (adapterList.get(getAdapterPosition()).name.equals("Fear")) {
                            actionEventSoundNew(v.getContext(), "fear_bn.mp3");
                        }

                    } else if (new SharedPrefDatabase(v.getContext()).RetriveLanguage().equals("EN")) {

                        if (adapterList.get(getAdapterPosition()).name.equals("Neutral")) {
                            actionEventSoundNew(v.getContext(), "Natural_EN.mp3");
                        } else if (adapterList.get(getAdapterPosition()).name.equals("Happy")) {
                            actionEventSoundNew(v.getContext(), "happy_en.mp3");
                        } else if (adapterList.get(getAdapterPosition()).name.equals("Sad")) {
                            actionEventSoundNew(v.getContext(), "sad_en.mp3");
                        } else if (adapterList.get(getAdapterPosition()).name.equals("Surprised")) {
                            actionEventSoundNew(v.getContext(), "surprise_en.mp3");
                        } else if (adapterList.get(getAdapterPosition()).name.equals("Angry")) {
                            actionEventSoundNew(v.getContext(), "angry_en.mp3");
                        } else if (adapterList.get(getAdapterPosition()).name.equals("Disgusted")) {
                            actionEventSoundNew(v.getContext(), "disgusting_en.mp3");
                        } else if (adapterList.get(getAdapterPosition()).name.equals("Fear")) {
                            actionEventSoundNew(v.getContext(), "fear_en.mp3");
                        }

                    }
                }
            });
        }


    }


    private void actionEventSoundNew(Context context, final String Sound_s) {

        MediaPlayer mediaPlayer = new MediaPlayer();
        try {

            AssetFileDescriptor descriptor = context.getAssets().openFd(Sound_s);
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();

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
}
