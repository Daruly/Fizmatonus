package company.hakatonus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarEventAdapter extends RecyclerView.Adapter<CalendarViewEventHolder>{

    private final Context context;
    private final String eventsTitle[],eventsBodies[];

    public CalendarEventAdapter(Context context, String []eventsTitle, String []eventsBodies){
        this.context = context;
        this.eventsTitle = eventsTitle;
        this.eventsBodies = eventsBodies;
    }

    @NonNull
    @Override
    public CalendarViewEventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_event_cell, parent, false);
        return new CalendarViewEventHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewEventHolder holder, int position) {
        holder.title.setText(eventsTitle[position]);
        holder.discriprion.setText(eventsBodies[position]);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public interface  OnItemListenerSecond
    {
        void onItemClick(int position, String dayText);
    }
}
