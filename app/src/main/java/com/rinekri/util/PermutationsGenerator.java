package com.rinekri.util;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.Executor;

public class PermutationsGenerator {
    private static final String TAG = "PermutationsGenerator";
    private static final int ELEMENTS = 4;

    private static Thread sThread;
    private HashSet<ArrayList<String>> mCombinations;
    private int mCombinationsSize;

    public HashSet<ArrayList<String>> getCombinations(int size) {
        mCombinationsSize = size;
        mCombinations = new HashSet<ArrayList<String>>();

        GenerateExecutor mExecutor = new GenerateExecutor();
        mExecutor.execute(new RunnableForGenerateExecutor());

       while (true) {
//            Log.e(TAG, "Size of comnbinations"+mCombinations.size());
            if (mCombinations.size() >= 1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return mCombinations;
            }
        }
    }

    private class GenerateExecutor implements Executor {

        public void execute(Runnable r) {
            if (sThread != null) {
                sThread.interrupt();
//                Log.e(TAG, "Back generation of mCombinations was interrupted!");
            }
//            Log.e(TAG, "New generation of mCombinations!");
            sThread = new Thread(r);
            sThread.start();
        }
    }

    private class RunnableForGenerateExecutor implements Runnable {

        @Override
        public void run() {
            ArrayList<String> numbers = new ArrayList<String>();

            for (int i = 0; i < mCombinationsSize; i++) {
                numbers.add(Integer.toString(i));
//                Log.d(TAG, "Number: "+numbers.get(i).toString());
            }
            generate(numbers, 0, numbers.size(), mCombinations);
//            Log.d(TAG, "Combinations count: " + mCombinations.size());
        }
    }

    private static void generate(ArrayList<String> str, int k, int n, HashSet<ArrayList<String>> resultSet){
        for(int i = k; i < n; i++){

            ArrayList<String> temp = modifyString(str, i, k);

            ArrayList<String> result = new ArrayList<String>();

            for (int g = 0; g < ELEMENTS; g++) {
                String number = temp.get(g);
                result.add(number);
            }
//            Log.d(TAG,"Combination:"+result.toString());
            resultSet.add(result);
            generate(temp, k + 1, n, resultSet);
        }
    }

    private static ArrayList<String> modifyString(ArrayList<String> str, int x, int y){
        String[] arr = new String[str.size()];
        str.toArray(arr);

        String t =  arr[x];
        arr[x] = arr[y];
        arr[y] = t;

        ArrayList<String> temp = new ArrayList<String>();
//        Log.e(TAG,"Combinations for parsing:");
        for (int i = 0; i < arr.length; i++ ) {
            temp.add(arr[i]);
        }
        return temp;
    }
}
