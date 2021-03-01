package com.exvoto.qrapplication.views;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exvoto.qrapplication.Constants;
import com.exvoto.qrapplication.R;
import com.exvoto.qrapplication.model.FormData;

import java.util.List;

public class FormDataAdapter extends RecyclerView.Adapter<FormDataAdapter.ViewHolder> {
    private final String TAG = this.getClass().getName();
    private final List<FormData> localDataSet;

    public FormDataAdapter(List<FormData> dataSet) {
        this.localDataSet = dataSet;

        Log.e(TAG, localDataSet.toString());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_form_data_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FormData data = this.localDataSet.get(position);
        holder.tvFormId.setText(data.getId().toString());
        holder.tvFullName.setText(data.getName());
        holder.tvAddress.setText(data.getAddress());
        holder.tvDateCreated.setText(data.getCreatedAt().toString());
        holder.tvDateUpdated.setText(data.getUpdatedAt().toString());
    }

    @Override
    public int getItemCount() {
        return this.localDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = ViewHolder.class.getName() ;
        TextView tvFormId;
        TextView tvFullName;
        TextView tvAddress;
        TextView tvDateCreated;
        TextView tvDateUpdated;

        public ViewHolder(View itemView) {
            super(itemView);
            Context context = itemView.getContext();

            tvFormId = itemView.findViewById(R.id.tv_form_id);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvFullName = itemView.findViewById(R.id.tv_full_name);
            tvDateCreated = itemView.findViewById(R.id.tv_date_created);
            tvDateUpdated = itemView.findViewById(R.id.tv_date_updated);


            itemView.setOnClickListener(v -> showViewActivity(context));

        }

        public void showViewActivity( Context context) {
            String idStr =  tvFormId.getText().toString();

            Log.e(TAG, "ID in view: "+ idStr);

            try {
                int id = Integer.parseInt(idStr);

                Intent intent = new Intent(context, FormViewActivity.class);
                intent.putExtra(Constants.EXTRA_FORM_ID, id);
                Log.i(TAG, "Form ID: " + id);
                context.startActivity(intent);
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }

        }
    }
}
