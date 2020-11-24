package cat.itb.gestitb;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Date;

public class MissedAttendanceFragment extends Fragment {
    private EditText nameText;
    private CheckBox isJustified;
    private Spinner moduleSpinner;
    private Button dateButton;
    private Button addButton;
    private MissedAttendance missedAttendance;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        missedAttendance = new MissedAttendance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.missed_attendance_fragment, container, false);

        nameText = view.findViewById(R.id.name_text);
        isJustified = view.findViewById(R.id.is_justified);
        moduleSpinner = view.findViewById(R.id.spinner);
        dateButton = view.findViewById(R.id.date_button);
        addButton = view.findViewById(R.id.add_button);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.modules, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        moduleSpinner.setAdapter(adapter);
        dateButton.setEnabled(false);
        dateButton.setText((new Date()).toString());


        if (getArguments() != null) missedAttendance = getArguments().getParcelable("missedAttendance");
        if (missedAttendance != null){
            nameText.setText(missedAttendance.getName());
            moduleSpinner.setSelection(0);

        }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Missed attendance created");
                String msg = "The student " + missedAttendance.name;
                msg += " has missed " + missedAttendance.subject;
                msg += " on " + missedAttendance.date;
                msg += missedAttendance.isJustified() ? " with justification" : " without justification";
                builder.setMessage(msg);
                builder.show();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Set the new window details
        if (missedAttendance.isJustified()){isJustified.setChecked(true);}
        moduleSpinner.setSelection(missedAttendance.getSubjNum()-1);

        nameText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                missedAttendance.name = nameText.getText().toString();
                return false;
            }
        });


        moduleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                missedAttendance.subject = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                missedAttendance.subject = "";
            }
        });

        isJustified.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                missedAttendance.justified = isChecked;
            }
        });


    }
}
