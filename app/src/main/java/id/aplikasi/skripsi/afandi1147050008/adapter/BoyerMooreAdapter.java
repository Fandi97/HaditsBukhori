package id.aplikasi.skripsi.afandi1147050008.adapter;

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

public class BoyerMooreAdapter extends RecyclerView.Adapter<BoyerMooreAdapter.ViewHolder> {

    private List<Hadist> hadistList;
    private Context context;

    public BoyerMooreAdapter(List<Hadist> hadistList, Context context) {
        this.hadistList = hadistList;
        this.context = context;
    }

    @NonNull
    @Override
    public BoyerMooreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_boyer_moore, parent, false);
        return new BoyerMooreAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BoyerMooreAdapter.ViewHolder holder, int position) {
        Hadist hadist = hadistList.get(position);

        holder.title.setText(hadist.getKitab());
        holder.hadist.setText(hadist.getArab());
        holder.terjemahan.setText(hadist.getTerjemahan());
    }

    @Override
    public int getItemCount() {
        return hadistList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView hadist;
        private TextView terjemahan;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvKitab);
            hadist = itemView.findViewById(R.id.tvHadist);
            terjemahan = itemView.findViewById(R.id.tvTerjemahan);
        }
    }
}
