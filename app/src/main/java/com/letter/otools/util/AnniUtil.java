package com.letter.otools.util;

import android.util.Log;

import com.letter.otools.Anniversary;

import org.litepal.LitePal;

import java.util.List;

public class AnniUtil {

    public static Anniversary getClosestAnni () {
        Anniversary closestAnni;
        List<Anniversary> anniversaryList = LitePal.findAll(Anniversary.class);
        if (anniversaryList == null) {
            return new Anniversary();
        }
        closestAnni = anniversaryList.get(0);
        for (Anniversary anni : anniversaryList) {
            Log.d("AnniUtil", String.valueOf(anni.getNextTime()));
            if ((anni.getNextTime() >= 0) &&
                    ((anni.getNextTime() < closestAnni.getNextTime()) || (closestAnni.getNextTime() < 0))) {
                closestAnni = anni;
            }
        }
        return closestAnni;
    }
}
