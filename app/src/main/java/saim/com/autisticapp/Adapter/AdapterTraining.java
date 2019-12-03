package saim.com.autisticapp.Adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
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
import java.util.Locale;

import saim.com.autisticapp.Model.ModelFamily;
import saim.com.autisticapp.R;

public class AdapterTraining extends RecyclerView.Adapter<AdapterTraining.TrainingViewHolder> {

    ArrayList<ModelFamily> adapterList = new ArrayList<>();
    Context mContext;

    public static String post_id = "";

    public AdapterTraining(ArrayList<ModelFamily> adapterList) {
        this.adapterList = adapterList;
    }

    public AdapterTraining(ArrayList<ModelFamily> adapterList, Context mContext) {
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
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    try {
                        String s = getAdapterPosition() + " : " + v.getContext().getExternalCacheDir() + File.separator + adapterList.get(getAdapterPosition()).getSound();
                        Log.d("SAIM_LOG_TRAIN", s);
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

            listImgSound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    try {
                        Log.d("SAIM_LOG", v.getContext().getExternalCacheDir() + File.separator + adapterList.get(getAdapterPosition()).getSound());
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

        /*@Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), adapterList.get(getAdapterPosition()).name, Toast.LENGTH_SHORT).show();
        }*/
    }


    private TextToSpeech textToSpeech;

    private void actionEventSound(final ImageView button, final TextView textView, final int voicePosition) {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech = new TextToSpeech(button.getContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            int result = textToSpeech.setLanguage(Locale.US);
                            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                            } else {
                                textToSpeech.speak(textView.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                            }
                        } else {
                            Log.e("TTS", "Initilization Failed!");
                        }
                    }
                });

                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(button.getContext().getExternalCacheDir() + File.separator + adapterList.get(voicePosition).getSound());
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
