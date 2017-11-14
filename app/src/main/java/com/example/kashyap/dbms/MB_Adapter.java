package com.example.kashyap.dbms;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kashyap on 12/11/17.
 */

public class MB_Adapter extends RecyclerView.Adapter<MB_Adapter.VH>{

    ArrayList<MBdata> AL;
    Myownclicklistener3 listener;

    public MB_Adapter(ArrayList<MBdata> AL, Myownclicklistener3 listener){
        this.AL = AL;
        this.listener = listener;
    }
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater LI = LayoutInflater.from(parent.getContext());
        View V = LI.inflate(R.layout.mb_adapter,parent,false);
        VH vh = new VH(V);
        return vh;
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        holder.MBmanudisp.setText(AL.get(position).Manufacturer);
        holder.MBstockdisp.setText(AL.get(position).Stock);
        holder.MBcostdisp.setText(AL.get(position).Cost);
        holder.MBIDdisp.setText(AL.get(position).MBno);

        holder.clicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemclick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return AL.size();
    }

    public interface Myownclicklistener3{
        void onItemclick(int position);
    }

    public class VH extends RecyclerView.ViewHolder{

        TextView mbidlabel, MBmanulabel, MBcostlabel, MBstocklabel, clicker;
        TextView MBIDdisp, MBmanudisp, MBcostdisp, MBstockdisp;
        public VH(View itemView) {
            super(itemView);
            mbidlabel = itemView.findViewById(R.id.MBID);
            MBmanulabel = itemView.findViewById(R.id.MBmanulabel);
            MBcostlabel = itemView.findViewById(R.id.MBcostlabel);
            MBstocklabel = itemView.findViewById(R.id.MBstocklabel);
            clicker = itemView.findViewById(R.id.mbclicker);

            MBIDdisp = itemView.findViewById(R.id.MBdisp);
            MBcostdisp = itemView.findViewById(R.id.MBcostdisp);
            MBstockdisp = itemView.findViewById(R.id.MBstockdisp);
            MBmanudisp = itemView.findViewById(R.id.MBmanudisp);
        }
    }
}
