package com.khudaleyeu.storage4

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class SortFragment : PreferenceFragmentCompat(){
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        return
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.sort)
    }

}