package saim.com.autisticapp.Adapter;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.Locale;

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

            actionEventSound(listImgSound, listTextName);

            listImgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new DBHelper(v.getContext()).deleteContact(Integer.parseInt(String.valueOf(listTextName.getHint())));

                    adapterList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(), adapterList.size());
                }
            });
        }

        /*@Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), adapterList.get(getAdapterPosition()).name, Toast.LENGTH_SHORT).show();
        }*/
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