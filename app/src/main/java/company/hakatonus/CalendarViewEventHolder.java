package company.hakatonus;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarViewEventHolder extends RecyclerView.ViewHolder{
    public final TextView title;
    public final TextView discriprion;
    public CalendarViewEventHolder(@NonNull View itemView)
    {
        super(itemView);
        title = itemView.findViewById(R.id.cellEventTitleText);
        discriprion = itemView.findViewById(R.id.cellEventBodyText);
    }
}
