package com.example.MAIN;

import com.example.CONSTANT.DataSet;

public class MainClass {
    public static void main(String[] args) throws CloneNotSupportedException {
        //Öncelikle veri seti okunuyor ve çözülmesi sağlanıyor
        DataSet.fillData("genetic_dataset",0); // DON'T REMOVE IT
        Solution solution = new Solution();
        solution.solve();
    }
}




