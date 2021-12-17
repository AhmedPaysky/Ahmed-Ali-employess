package com.paysky.readtextfile.utils;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.paysky.readtextfile.models.PairEmployees;
import com.paysky.readtextfile.models.ProjectModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class EmployeeFileUtils {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<ProjectModel> readFromFile(String filePath) {
        ArrayList<ProjectModel> projectModels = new ArrayList<>();
        FileInputStream fIN = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            File file = new File(filePath);
            fIN = new FileInputStream(file);
            isr = new InputStreamReader(fIN, "UTF-8");
            input = new BufferedReader(isr);
            String line = "";
            while ((line = input.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length == 4) {
                    long workDuration = calcWorkDuration(p[2].trim(), p[3].trim());
                    ProjectModel projectModel = new ProjectModel(p[0].trim(), p[1].trim(), p[2].trim(), p[3].trim(), workDuration);
                    projectModels.add(projectModel);
                    Log.d("File1", "ResultLine " + line);
                }
            }
            Log.d("File1", "ResultTotal " + projectModels.toString());
        } catch (Exception e) {
            e.getMessage();
            Log.d("File1", "Exception " + e.getMessage());
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fIN != null)
                    fIN.close();
                if (input != null)
                    input.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        return projectModels;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static long calcWorkDuration(String startDate, String endDate) {
        Date startDateObj = stringToDate(startDate);
        Date endDateObj = stringToDate(endDate);
        long diff = endDateObj.getTime() - startDateObj.getTime();
        Log.d("File1", "Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private static Date stringToDate(String aDate) {
        if (aDate == null) return null;
        if (aDate.equalsIgnoreCase("null")) {
            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd", Locale.getDefault());
            aDate = df.format(c);
            Log.d("File1", "Current time => " + aDate);
        }
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-mm-dd", Locale.US);
        return simpledateformat.parse(aDate, pos);

    }

    public static List<PairEmployees> getPairEmployeesForSameProject(List<ProjectModel> allProjectModels) {
        if (allProjectModels == null || allProjectModels.size() == 0) return null;
        Collections.sort(allProjectModels, (o1, o2) -> Integer.parseInt(o1.getProjId()) - Integer.parseInt(o2.getProjId()));
        Log.d("File1", "Data Sorted by ProjId " + allProjectModels.toString());

        ArrayList<ProjectModel> projectModelsForSameProject = new ArrayList<>();
        ArrayList<PairEmployees> pairEmployeesList = new ArrayList<>();
        String currProject = allProjectModels.get(0).getProjId();
        /*
        add every employee works for a project to projectModelsForSameProject
        sort projectModelsForSameProject
        add first two employees to pairEmployeesList
        clear projectModelsForSameProject and do same algorithm again
        */

        for (int i = 0; i < allProjectModels.size(); i++) {
            if (currProject.equals(allProjectModels.get(i).getProjId())) {
                projectModelsForSameProject.add(allProjectModels.get(i));
                if (allProjectModels.size() - 1 == i) {
                    //if one employee works on project ignore adding
                    if (projectModelsForSameProject.size() < 2) continue;
                    Collections.sort(projectModelsForSameProject, (o2, o1) -> (int) ((o2.getWorkDuration()) - (o1.getWorkDuration())));
                    Collections.reverse(projectModelsForSameProject);
                    Log.d("File1", "Data sorted by WorkDuration" + projectModelsForSameProject.toString());
                    PairEmployees pairEmployees = new PairEmployees(
                            projectModelsForSameProject.get(0).getEmpId(), projectModelsForSameProject.get(1).getEmpId(),
                            projectModelsForSameProject.get(0).getProjId(), String.valueOf(projectModelsForSameProject.get(1).getWorkDuration()));
                    pairEmployeesList.add(pairEmployees);
                    projectModelsForSameProject.clear();
                    currProject = allProjectModels.get(i).getProjId();
                    projectModelsForSameProject.add(allProjectModels.get(i));
                }
            } else {
                //if one employee works on project ignore adding
                if (projectModelsForSameProject.size() < 2) {
                    currProject = allProjectModels.get(i).getProjId();
                    continue;
                }
                Collections.sort(projectModelsForSameProject, (o1, o2) -> (int) ((o1.getWorkDuration()) - (o2.getWorkDuration())));
                Collections.reverse(projectModelsForSameProject);
                Log.d("File1", "Data sorted by WorkDuration" + projectModelsForSameProject.toString());
                PairEmployees pairEmployees = new PairEmployees(
                        projectModelsForSameProject.get(0).getEmpId(), projectModelsForSameProject.get(1).getEmpId(),
                        projectModelsForSameProject.get(0).getProjId(), String.valueOf(projectModelsForSameProject.get(1).getWorkDuration()));
                pairEmployeesList.add(pairEmployees);
                projectModelsForSameProject.clear();
                currProject = allProjectModels.get(i).getProjId();
                projectModelsForSameProject.add(allProjectModels.get(i));
            }
        }
        Log.d("File1", "PairEmployees" + pairEmployeesList.toString());
        return pairEmployeesList;
    }
}
