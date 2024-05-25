package com.rinekri.model;

import android.content.Context;
import android.util.Log;

import com.rinekri.util.PermutationsGenerator;

import java.util.ArrayList;
import java.util.HashSet;

public class InstagramCollageFactory {
    public static final String TAG = "InstagramCollageFactory";
    private static InstagramCollageFactory sInstagramCollageFactory;

    private Context mAppContext;
    private HashSet<ArrayList<String>> sInstagramImgsCombinations;
    private int sCurrentInstagramImgsCombinationSize;
    private int sCurrentInstagramImgsCombination;

    private InstagramCollageFactory(Context c) {
        mAppContext = c;
    }

    public static InstagramCollageFactory getFactory(Context c) {

        if (sInstagramCollageFactory == null) {
            sInstagramCollageFactory = new InstagramCollageFactory(c);
        }
        return sInstagramCollageFactory;
    }


    public int[] getCombinationImages(int size) {

        if ((sInstagramImgsCombinations == null)) {
            sCurrentInstagramImgsCombinationSize = size;
            generateCombinations(sCurrentInstagramImgsCombinationSize);
            resetCurrentCombination();

        } else if ((sInstagramImgsCombinations != null) && (sCurrentInstagramImgsCombinationSize != size)) {
            sCurrentInstagramImgsCombinationSize = size;
            generateCombinations(sCurrentInstagramImgsCombinationSize);
            resetCurrentCombination();

        } else if ((sInstagramImgsCombinations != null) && (sCurrentInstagramImgsCombinationSize == size)) {

            if (sCurrentInstagramImgsCombination != (sInstagramImgsCombinations.size()-1)) {
                sCurrentInstagramImgsCombination++;
            } else {
                resetCurrentCombination();
            }

        }

//        Log.e(TAG, "Current instagram combination " + sCurrentInstagramImgsCombination);

        return parseCharToNumbers(getCombination());
    }

    public int[] getFirstCombinationImages(int size) {

        if ((sInstagramImgsCombinations == null)) {
            sCurrentInstagramImgsCombinationSize = size;
            generateCombinations(sCurrentInstagramImgsCombinationSize);

        } else if ((sInstagramImgsCombinations != null) && (sCurrentInstagramImgsCombinationSize != size)) {
            sCurrentInstagramImgsCombinationSize = size;
            generateCombinations(sCurrentInstagramImgsCombinationSize);

        }

        resetCurrentCombination();


        return parseCharToNumbers(getCombination());
    }

    public void resetCurrentCombination() {
        sCurrentInstagramImgsCombination = 0;
    }

    private void generateCombinations(int size) {
        PermutationsGenerator permutationsGenerator = new PermutationsGenerator();
        sInstagramImgsCombinations = permutationsGenerator.getCombinations(size);
    }

    private int[] parseCharToNumbers(ArrayList<String> character) {

        int[] numbers = new int[character.size()];

        for (int i = 0; i < character.size(); i++) {
            numbers[i] = Integer.parseInt(character.get(i));
        }
        return numbers;
    }

    private ArrayList<String> getCombination() {

        int i = 0;
        for (ArrayList<String> s : sInstagramImgsCombinations) {
            if (sCurrentInstagramImgsCombination == i) {
                return s;
            }
            i++;
        }
        return null;
    }
}
