package id.aplikasi.skripsi.afandi1147050008.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.aplikasi.skripsi.afandi1147050008.R;
import id.aplikasi.skripsi.afandi1147050008.model.Hadist;

public class HadistAdapter extends RecyclerView.Adapter<HadistAdapter.ViewHolder> {

    private List<Hadist> hadistList;
    private Context context;

    public HadistAdapter(List<Hadist> hadistList, Context context) {
        this.hadistList = hadistList;
        this.context = context;
    }

    @NonNull
    @Override
    public HadistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_boyer_moore, parent, false);
        return new HadistAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HadistAdapter.ViewHolder holder, int position) {
        Hadist hadist = hadistList.get(position);

        holder.title.setText("Kitab: "+hadist.getKitab()+", No: "+hadist.getNo());
        holder.hadist.setText(hadist.getArab());
        holder.terjemahan.setText(hadist.getTerjemahan());

        holder.timeBrute.setText("Brute Force = " + hadist.getBruteTime()+" ns");
        holder.timeBoyer.setText("Boyer Moore = " + hadist.getBoyerTime()+" ns");
    }

    @Override
    public int getItemCount() {
        return hadistList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView hadist;
        private TextView terjemahan;
        private TextView timeBrute;
        private TextView timeBoyer;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvKitab);
            hadist = itemView.findViewById(R.id.tvHadist);
            terjemahan = itemView.findViewById(R.id.tvTerjemahan);
            timeBrute = itemView.findViewById(R.id.tvBrute);
            timeBoyer = itemView.findViewById(R.id.tvBoyer);
        }
    }
}
