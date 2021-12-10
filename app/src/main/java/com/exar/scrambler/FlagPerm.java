package com.exar.scrambler;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class FlagPerm {

    static List<String> differentFlagPermutations(int x, String[] arr) {
        List<String> res = new ArrayList<String>();
        String [] ml = arr;

        for(int z = 0; z < x - 1; z++) {
            Vector<String> temp = new Vector<String>();

            for(int i = 0; i < arr.length; i++) {
                for (int k = 0; k < ml.length; k++) {
                    if (arr[i] != ml[k]) {
                        temp.add(ml[k] + arr[i]);
                    }
                }
            }

            for(int i = 0; i < temp.size(); i++) {
                res.add(temp.get(i));
            }

            ml = temp.toArray(new String[temp.size()]);
        }
        return res;
    }

}
