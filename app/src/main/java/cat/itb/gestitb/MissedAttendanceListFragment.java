package cat.itb.gestitb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MissedAttendanceListFragment extends Fragment {
    private MissedAttendanceViewModel missViewModel;
    private RecyclerView missedAttendanceList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        missViewModel = new ViewModelProvider(getActivity()).get(MissedAttendanceViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.missed_attendance_list_fragment, container, false);
        missedAttendanceList = v.findViewById(R.id.missed_attendance_list);
        missedAttendanceList.setLayoutManager(new LinearLayoutManager(getContext()));
        MissedAttendanceAdapter adapter = new MissedAttendanceAdapter(missViewModel.missedAttendances);
        missedAttendanceList.setAdapter(adapter);
        return v;
    }
}
