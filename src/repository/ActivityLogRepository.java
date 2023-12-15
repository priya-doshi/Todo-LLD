package repository;

import entities.ActivityLog;
import entities.Task;
import enums.Action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ActivityLogRepository {


    public List<ActivityLog> addActivityLog(Action action, List<ActivityLog> activityLogs){
        ActivityLog activityLog = new ActivityLog();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        activityLog.setAction(action);
        if(Action.CREATED.equals(action)){
            activityLog.setCreatedAt(date);
        }
        else if(Action.MODIEFIED.equals(action)){
            activityLog.setModifiedAt(date);
        }
        else if(Action.REMOVED.equals(action)){
            activityLog.setRemovedAt(date);
        }

        activityLogs.add(activityLog);
        return activityLogs;
    }
}
