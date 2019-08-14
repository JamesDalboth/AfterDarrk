package com.impulse.afterdarrk.Utils;

import java.util.List;

public class InsertionSort<T extends Sortable> {
    public void insertionSort(List<T> sortables) {
        int n = sortables.size();
        for (int j = 1; j < n; j++) {
            T key = sortables.get(j);
            int i = j-1;
            while ( (i > -1) && ( sortables.get(i).getSortKey() > key.getSortKey() ) ) {
                sortables.set(i+1, sortables.get(i));
                i--;
            }
            sortables.set(i+1, key);
        }
    }
}

