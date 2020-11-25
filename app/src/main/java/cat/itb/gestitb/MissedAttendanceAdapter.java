package cat.itb.gestitb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MissedAttendanceAdapter extends RecyclerView.Adapter<MissedAttendanceAdapter.MissedAttendanceViewHolder> {
    List<MissedAttendance> missedAttendances;

    public MissedAttendanceAdapter(List<MissedAttendance> missedAttendances){
        this.missedAttendances = missedAttendances;
    }

    @NonNull
    @Override
    public MissedAttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.missed_attendance_list_item, parent, false);
        return new MissedAttendanceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MissedAttendanceViewHolder holder, int position) {
        holder.bindData(missedAttendances.get(position));
    }

    @Override
    public int getItemCount() {
        return missedAttendances.size();
    }

    class MissedAttendanceViewHolder extends  RecyclerView.ViewHolder{
        TextView studentName;
        TextView subjectName;
        TextView dateText;
        ImageView justifiedImage;

        public MissedAttendanceViewHolder(@NonNull View itemView) {
            super(itemView);

            studentName = itemView.findViewById(R.id.student_name_text);
            subjectName = itemView.findViewById(R.id.subject_name);
            dateText = itemView.findViewById(R.id.date);
            justifiedImage = itemView.findViewById(R.id.image_just);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavDirections listToFragmentDirections = MissedAttendanceListFragmentDirections.actionListToFragment(missedAttendances.get(getAdapterPosition()));
                    Navigation.findNavController(v).navigate(listToFragmentDirections);
                }
            });
        }

        public void bindData(MissedAttendance missedAttendance){
            studentName.setText(missedAttendance.getName());
            subjectName.setText(missedAttendance.getSubject());
            dateText.setText(missedAttendance.getDate().toString());
            if (missedAttendance.isJustified()){
                justifiedImage.setVisibility(View.VISIBLE);
                justifiedImage.setImageResource(R.drawable.ic_justificada);
            }else {
                justifiedImage.setVisibility(View.INVISIBLE);
            }
        }
    }
}
