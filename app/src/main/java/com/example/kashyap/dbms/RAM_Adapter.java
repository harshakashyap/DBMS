package com.example.kashyap.dbms;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kashyap on 9/11/17.
 */

public class RAM_Adapter extends RecyclerView.Adapter<RAM_Adapter.VH> {

    ArrayList<RAMdata>  AL;
    Myownclicklistener listener;

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater LI = LayoutInflater.from(parent.getContext());
        View V = LI.inflate(R.layout.ram_adapter,parent,false);
        VH vh = new VH(V);
        return vh;
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        holder.ramstockdisp.setText(AL.get(position).Stock);
        holder.ramcostdisp.setText(AL.get(position).Cost);
        holder.ramsizedisp.setText(AL.get(position).Size);
        holder.rammanudisp.setText(AL.get(position).Manufacturer);
        holder.ramiddisp.setText(AL.get(position).RAMno);
        holder.ramtypedisp.setText(AL.get(position).Type);

        holder.clicker.setOnClickListener(new View.OnClickListener(){

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

    public interface Myownclicklistener{
        void onItemclick(int position);
    }

    public RAM_Adapter(ArrayList<RAMdata> AL, Myownclicklistener listener)
    {
        this.AL = AL;
        this.listener = listener;
    }

    public class VH extends RecyclerView.ViewHolder {

        TextView ramlabel, rammanulabel, ramcostlabel, ramstocklabel, ramtypelabel, ramsizelabel, clicker;
        TextView ramiddisp, rammanudisp, ramsizedisp, ramtypedisp, ramcostdisp, ramstockdisp;

        public VH(View itemView) {
            super(itemView);
            ramlabel = itemView.findViewById(R.id.ramlabel);
            rammanulabel = itemView.findViewById(R.id.rammanulabel);
            ramcostlabel = itemView.findViewById(R.id.ramcostlabel);
            ramstocklabel = itemView.findViewById(R.id.ramstocklabel);
            ramtypelabel = itemView.findViewById(R.id.ramtypelabel);
            ramsizelabel = itemView.findViewById(R.id.ramsizelabel);
            clicker = itemView.findViewById(R.id.clicker);

            ramiddisp = itemView.findViewById(R.id.ramiddisp);
            rammanudisp = itemView.findViewById(R.id.rammanudisp);
            ramsizedisp = itemView.findViewById(R.id.ramsizedisp);
            ramtypedisp = itemView.findViewById(R.id.ramtypedisp);
            ramcostdisp = itemView.findViewById(R.id.ramcostdisp);
            ramstockdisp = itemView.findViewById(R.id.ramstockdisp);

        }
    }
}
