package edu.galileo.android.tipcalc.fragments;

import edu.galileo.android.tipcalc.model.TipRecord;

/**
 * Created by ykro.
 */
public interface TipHistoryListFragmentListener {
    void addToList(TipRecord record);
    void clearList();
}
