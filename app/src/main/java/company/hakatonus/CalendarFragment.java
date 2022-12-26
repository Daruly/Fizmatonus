package company.hakatonus;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalendarFragment extends Fragment implements CalendarAdapter.OnItemListener{

    private Button first,second,third;
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView, calendarEventRecyclerView;
    private LocalDate selectedDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calendar, container, false);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        first = view.findViewById(R.id.first);
        second = view.findViewById(R.id.second);
        third = view.findViewById(R.id.third);

        initWidgets(view);
        selectedDate = LocalDate.now();
        setMonthView();
        String[]s = new String[]{"A!","A@","d3"};
        String[]s2 = new String[]{"A","A@","d3"};

        CalendarEventAdapter adapter = new CalendarEventAdapter(getContext(),s,s2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        calendarEventRecyclerView.setLayoutManager(layoutManager);
        calendarEventRecyclerView.setAdapter(adapter);

        view.findViewById(R.id.calendar_prew).setOnClickListener(view1 -> {
            selectedDate = selectedDate.minusMonths(1);
            setMonthView();
        });

        view.findViewById(R.id.calendar_next).setOnClickListener(view1 -> {
            selectedDate = selectedDate.plusMonths(1);
            setMonthView();
        });


//        first.setOnClickListener(view1 -> {
//            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
//            Fragment fragment = new LogsFragment();
//            fragmentTransaction.replace(R.id.frame_layout, fragment);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
//        });
//
//        third.setOnClickListener(view1 -> {
//            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
//            Fragment fragment = new LogsFragment();
//            fragmentTransaction.replace(R.id.frame_layout, fragment);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
//
//        });
    }

    private void initWidgets(View view)
    {
        System.out.println("Yes");
        calendarEventRecyclerView = view.findViewById(R.id.calendarEventsRecyclerView);
        calendarRecyclerView = view.findViewById(R.id.calendarRecyclerView);
        monthYearText = view.findViewById(R.id.monthYearTV);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView()
    {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<String> daysInMonthArray(LocalDate date)
    {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i = 1; i <= 42; i++)
        {
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek)
            {
                daysInMonthArray.add("");
            }
            else
            {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return  daysInMonthArray;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthYearFromDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public void previousMonthAction(View view)
//    {
//        selectedDate = selectedDate.minusMonths(1);
//        setMonthView();
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public void nextMonthAction(View view)
//    {
//        selectedDate = selectedDate.plusMonths(1);
//        setMonthView();
//    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, String dayText)
    {
        if(!dayText.equals(""))
        {
            String message = "Selected Date " + dayText + " " + monthYearFromDate(selectedDate);
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        }
    }
}