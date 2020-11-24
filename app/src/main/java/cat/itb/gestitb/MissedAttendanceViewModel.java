package cat.itb.gestitb;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MissedAttendanceViewModel extends ViewModel {
    List<MissedAttendance> missedAttendances = new ArrayList<MissedAttendance>();

    public MissedAttendanceViewModel() {
        for (int i = 0; i < 100; i++) {
            MissedAttendance missedAttendance = new MissedAttendance();
            missedAttendance.setName("Ekisde " + i);
            int rand = (int) Math.floor(Math.random() * 6) + 1;
            switch (rand) {
                case 1:
                    missedAttendance.setSubject("M6 - Data access");
                    break;
                case 2:
                    missedAttendance.setSubject("M7 - Interface development");
                    break;
                case 3:
                    missedAttendance.setSubject("M8 - Mobile app development");
                    break;
                case 4:
                    missedAttendance.setSubject("M9 - Process and service programming");
                    break;
                case 5:
                    missedAttendance.setSubject("M15 - Complex environment");
                    break;
                case 6:
                    missedAttendance.setSubject("M16 - AI");
                    break;
                default:
                    break;
            }
            missedAttendance.setSubjNum(rand);
            if (Math.random()<0.33){
                missedAttendance.setJustified(true);
            }
            missedAttendances.add(missedAttendance);
        }
    }
}