package com.example.ltnull.hur.JavaHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeHelper {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss aaa");
    long dateDeference;

    public Date getRightNowTime(){

   Date currentTime = Calendar.getInstance().getTime();
   return currentTime;
}

public long getTimeDeferencesNowToFrom(Date fromDate){

    return dateDeference;
}


}
