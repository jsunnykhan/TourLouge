package com.example.ltnull.hur.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ltnull.hur.PojoClass.EventPojo;
import com.example.ltnull.hur.R;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {


    private Context context ;
    List <EventPojo> events;

    public EventAdapter(Context context, List<EventPojo> events) {
        this.context = context;
        this.events = events ;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.event_adapter_view , null);

        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        EventPojo eventFeed = events.get(position);

        holder.eventName.setText("Event Name : "+eventFeed.getName());
        holder.eventPlaces.setText("Event places : "+eventFeed.getPlaces());
        holder.eventDuration.setText("Event Duration : "+eventFeed.getDuration());
        holder.eventNumber.setText("Author Number : "+eventFeed.getNumber());
        holder.eventAuthor.setText("Author Name : "+eventFeed.getAuth());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }


    public class EventViewHolder extends RecyclerView.ViewHolder{

        TextView eventName ;
        TextView eventPlaces ;
        TextView eventDuration ;
        TextView eventNumber ;
        TextView eventAuthor ;

        public EventViewHolder(View itemView) {
            super(itemView);

            eventName = itemView.findViewById(R.id.eventName);
            eventPlaces = itemView.findViewById(R.id.EventPlace);
            eventDuration = itemView.findViewById(R.id.EventDuration);
            eventNumber = itemView.findViewById(R.id.EventNumber);
            eventAuthor = itemView.findViewById(R.id.eventAuth);
        }
    }
}
