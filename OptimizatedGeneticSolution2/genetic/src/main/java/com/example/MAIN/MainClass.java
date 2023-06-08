package com.example.MAIN;

import com.example.CONSTANT.DataSet;
import com.example.CONSTANT.StringConstants;

public class MainClass {
    public static void main(String[] args) throws CloneNotSupportedException {
        //Öncelikle veri seti okunuyor ve çözülmesi sağlanıyor
        DataSet.fillData("genetic_dataset",0); // DON'T REMOVE IT
        Solution solution = new Solution();
        solution.solve(true);


//        solveTenHundredTimes(); // NEURAL E VERI OLUSTURMAK ICIN KULLANDIK
    }


    public static void solveTenHundredTimes() throws CloneNotSupportedException {
        for(int x = 0 ; x < 10000; x++){
            DataSet.fillData(StringConstants.SERIALSOLVEFILENAME.getValue(), x);
            Solution solution = new Solution();
            solution.solve(false);
        }
    }
}




