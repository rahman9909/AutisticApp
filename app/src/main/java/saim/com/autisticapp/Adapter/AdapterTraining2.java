package saim.com.autisticapp.Adapter;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

import saim.com.autisticapp.Model.ModelFamily;
import saim.com.autisticapp.R;

public class AdapterTraining2 extends RecyclerView.Adapter<AdapterTraining2.TrainingViewHolder> {

    ArrayList<ModelFamily> adapterList = new ArrayList<>();
    Context mContext;

    public static String post_id = "";

    public AdapterTraining2(ArrayList<ModelFamily> adapterList) {
        this.adapterList = adapterList;
    }

    public AdapterTraining2(ArrayList<ModelFamily> adapterList, Context mContext) {
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
        holder.listTextRelation.setText("");

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

            actionEventSound(listImgSound, listTextName);

            listImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(v.getContext(), adapterList.get(getAdapterPosition()).getImage() + " " + v.getContext().getPackageName(), Toast.LENGTH_SHORT).show();
                }
            });
        }


    }


    private TextToSpeech textToSpeech;

    private void actionEventSound(final ImageView button, final TextView textView) {

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
            }
        });

    }
}
