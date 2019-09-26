package com.demo.patientapp.UI.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.patientapp.R;
import com.demo.patientapp.UI.viewmodel.PatientViewModel;

import java.util.ArrayList;
import java.util.List;

public class PaginationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(PatientViewModel item);
    }

    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private List<PatientViewModel> patientViewModels;
    private Context context;
    private OnItemClickListener mListener;

    private boolean isLoadingAdded = false;

    public PaginationAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        patientViewModels = new ArrayList<>();
        mListener = listener;
    }

    public List<PatientViewModel> getPatientViewModels() {
        return patientViewModels;
    }

    public void setPatientViewModels(List<PatientViewModel> patientViewModels) {
        this.patientViewModels = patientViewModels;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.item_list, parent, false);
        viewHolder = new PatientVH(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        PatientViewModel patientViewModel = patientViewModels.get(position);

        switch (getItemViewType(position)) {
            case ITEM:
                PatientVH patientVH = (PatientVH) holder;
                patientVH.bind(patientViewModels.get(position), mListener);

                break;
            case LOADING:
//                Do nothing
                break;
        }
    }

    @Override
    public int getItemCount() {
        return patientViewModels == null ? 0 : patientViewModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == patientViewModels.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    /*
   Helpers
   _________________________________________________________________________________________________
    */

    public void add(PatientViewModel mc) {
        patientViewModels.add(mc);
        notifyItemInserted(patientViewModels.size() - 1);
    }

    public void addAll(List<PatientViewModel> mcList) {
        for (PatientViewModel mc : mcList) {
            add(mc);
        }
    }

    public void remove(PatientViewModel city) {
        int position = patientViewModels.indexOf(city);
        if (position > -1) {
            patientViewModels.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new PatientViewModel());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = patientViewModels.size() - 1;
        PatientViewModel item = getItem(position);

        if (item != null) {
            patientViewModels.remove(position);
            notifyItemRemoved(position);
        }
    }

    public PatientViewModel getItem(int position) {
        return patientViewModels.get(position);
    }


   /*
   View Holders
   _________________________________________________________________________________________________
    */

    /**
     * Main list's content ViewHolder
     */
    protected class PatientVH extends RecyclerView.ViewHolder {
        private TextView textView;
        private LinearLayout rootCell;

        public PatientVH(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.item_text);
            rootCell = itemView.findViewById(R.id.cell_root);
        }

        public void bind(PatientViewModel patientViewModel, OnItemClickListener listener) {
            // TODO Update Views here

            rootCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(patientViewModel);
                }
            });
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }



}