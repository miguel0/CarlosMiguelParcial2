package com.example.parcial2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {

    private List<Person> mPeople;
    private Context context;

    public PeopleAdapter(List<Person> people, Context context) {
        mPeople = people;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View personView = inflater.inflate(R.layout.item_person, parent, false);

        ViewHolder viewHolder = new ViewHolder(personView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Person person = mPeople.get(position);

        holder.tvName.setText(context.getString(R.string.ob_name) + person.getName());
        holder.tvAge.setText(context.getString(R.string.ob_age) + person.getAge());
        holder.btToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,  context.getString(R.string.t_text) + person.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPeople.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvAge;
        private Button btToast;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            btToast = itemView.findViewById(R.id.btToast);
        }
    }
}
