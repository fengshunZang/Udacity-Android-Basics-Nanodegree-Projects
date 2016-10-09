package com.zangfengshun.reportcard;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Zang on 2016-07-07.
 * All the grades are shown in hundred-mark system.
 */
public class ReportCard {
    private String name;
    private Date examdate;
    private int englishGrade;
    private int mathGrade;
    private int geographyGrade;
    private int historyGrade;
    private int musicGrade;
    private int technologyGrade;
    private int socialStudiesGrade;
    private ArrayList<Integer> grades;

    public ReportCard(String name, Date examdate, int englishGrade,int mathGrade,
                      int geographyGrade, int historyGrade, int musicGrade,
                      int technologyGrade, int socialStudiesGrade ) {
        this.name = name;
        this.examdate = examdate;
        this.englishGrade = englishGrade;
        grades.add(englishGrade);
        this.geographyGrade = geographyGrade;
        grades.add(geographyGrade);
        this.mathGrade = mathGrade;
        grades.add(mathGrade);
        this.historyGrade = historyGrade;
        grades.add(historyGrade);
        this.musicGrade = musicGrade;
        grades.add(musicGrade);
        this.technologyGrade = technologyGrade;
        grades.add(technologyGrade);
        this.socialStudiesGrade = socialStudiesGrade;
        grades.add(socialStudiesGrade);
    }

    public int getEnglishGrade() {
        return englishGrade;
    }

    public int getMathGrade() {
        return mathGrade;
    }

    public int getGeographyGrade() {
        return geographyGrade;
    }

    public int getHistoryGrade() {
        return historyGrade;
    }

    public int getMusicGrade() {
        return musicGrade;
    }

    public int getTechnologyGrade() {
        return technologyGrade;
    }

    public int getSocialStudiesGrade() {
        return socialStudiesGrade;
    }

    public void setEnglishGrade(int englishGrade) {
        this.englishGrade = englishGrade;
    }

    public void setMathGrade(int mathGrade) {
        this.mathGrade = mathGrade;
    }

    public void setGeographyGrade(int geographyGrade) {
        this.geographyGrade = geographyGrade;
    }

    public void setHistoryGrade(int historyGrade) {
        this.historyGrade = historyGrade;
    }

    public void setMusicGrade(int musicGrade) {
        this.musicGrade = musicGrade;
    }

    public void setTechnologyGrade(int technologyGrade) {
        this.technologyGrade = technologyGrade;
    }

    public void setSocialStudiesGrade(int socialStudiesGrade) {
        this.socialStudiesGrade = socialStudiesGrade;
    }

    public int sumGrades() {
        int sum = 0;
        for (int i = 0; i < grades.size(); i++) {
            sum += grades.get(i);
        }
        return sum;
    }

    public double averageGrades() {
        double result = 0;
        int sum = 0;
        for (int i = 0; i < grades.size(); i++) {
            sum += grades.get(i);
        }
        result = (double) sum / 7;
        return result;
    }

    @Override
    public String toString() {
        String message = "Name: " + name +
                " Date: " + examdate +
                " Grades{" +
                "englishGrade = " + englishGrade +
                ", mathGrade = " + mathGrade +
                ", geographyGrade = " + geographyGrade +
                ", historyGrade = " + historyGrade +
                ", musicGrade = " + musicGrade +
                ", technologyGrade = " + technologyGrade +
                ", socialStudiesGrade = " + socialStudiesGrade +
                "}";
        return message;
    }
}
