package com.dashin.dashindelivery.adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dashin.dashindelivery.Accept;
import com.dashin.dashindelivery.R;
import com.dashin.dashindelivery.dataclasses.OrderData;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;


public class OrdersAdapter extends FirestoreRecyclerAdapter<OrderData, OrdersAdapter.OrdersHolder> {
    public static OrderData current;
    public static String pos;
    int m;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onBindViewHolder(@NonNull final OrdersHolder holder, final int position, @NonNull final OrderData model) {
       // String date=model.getTIME().toDate().toLocaleString();
      //  holder.HoldMyDate.setText(date);
        holder.OrderNumber.setText("#PPNJS12345");
        holder.HoldMyFromAddress.setText(model.getBUSI_ADD());
        holder.HoldMyFromAddress.setText("Katraj Dairy, Raajiv Gandhi National Park, Katraj, Pune, 411042");
       // holder.HoldMyStatus.setText(String.valueOf(model.getSTATUS()));
        if(model.getSTATUS()==1) {
            holder.HoldMyStatus.setTextColor(holder.itemView.getResources().getColor(R.color.green));
            holder.HoldMyStatus.setText("PAID");
        }
        else
        {
            holder.HoldMyStatus.setTextColor(holder.itemView.getResources().getColor(R.color.bgcolor));
            holder.HoldMyStatus.setText("UNPAID");
        }
        holder.HoldMyLimit.setText("+35 MINS"); // Get limit
        holder.HoldMyToAddress.setText("Block-19, Surajnagar, Mahaveer Road, Dahipada, Pune, 411030"); // Get To address
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current=model;
                pos="ORDER-"+(m-position);
                holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), Accept.class));
            }
        });
    }

    @NonNull
    @Override
    public OrdersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_orders_display,parent,false);
        return new OrdersHolder(view);
    }

    class OrdersHolder extends RecyclerView.ViewHolder{
        TextView HoldMyDate, HoldMyFromAddress,HoldMyToAddress, HoldMyValues, HoldMyStatus ,HoldMyLimit,OrderNumber;
        public OrdersHolder(@NonNull final View itemView) {
            super(itemView);
            HoldMyStatus=itemView.findViewById(R.id.HoldMyStatus);
            HoldMyFromAddress=itemView.findViewById(R.id.HoldFromAddress);
            HoldMyDate=itemView.findViewById(R.id.HoldMyDate);
            HoldMyValues=itemView.findViewById(R.id.HoldMyOrder);
            HoldMyToAddress=itemView.findViewById(R.id.HoldToAddress);
            HoldMyLimit=itemView.findViewById(R.id.HoldMyLimit);
            OrderNumber=itemView.findViewById(R.id.HoldMyOrder);
        }
    }
    public OrdersAdapter(@NonNull FirestoreRecyclerOptions options) {
        super(options);
    }

}

