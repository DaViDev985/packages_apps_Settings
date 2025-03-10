/*
 * Copyright (C) 2024 DerpFest
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.settings.display;

import android.content.Context;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;

import androidx.preference.Preference;
import androidx.preference.TwoStatePreference;
import androidx.annotation.VisibleForTesting;

import com.android.settings.R;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.core.TogglePreferenceController;


public class LandscapeBootOrientationPreferenceController extends TogglePreferenceController 
    implements Preference.OnPreferenceChangeListener, PreferenceControllerMixin {

        public LandscapeBootOrientationPreferenceController(Context context, String preferenceKey) {
            super(context, preferenceKey);
        }

        private static final String LANDSCAPE_BOOT_ORIENTATION_KEY = "landscape_boot_orientation";

        @VisibleForTesting
        static final String LANDSCAPE_BOOT_ORIENTATION_PROPERTY = "persist.ro.bootanim.set_orientation_logical_0";

        @Override
        public String getPreferenceKey() {
            return LANDSCAPE_BOOT_ORIENTATION_KEY;
        }

        @Override
        public boolean isChecked() {
            return SystemProperties.get(LANDSCAPE_BOOT_ORIENTATION_PROPERTY).equals("ORIENTATION_90");
        }

        @Override
        public boolean setChecked(boolean isChecked) {
            SystemProperties.set(LANDSCAPE_BOOT_ORIENTATION_PROPERTY, isChecked ? "ORIENTATION_90" : "ORIENTATION_0");
            return true;
        }

        @Override
        public void updateState(Preference preference) {
            final String currentValue = SystemProperties.get(LANDSCAPE_BOOT_ORIENTATION_PROPERTY);
            ((TwoStatePreference) preference).setChecked(currentValue.equals("ORIENTATION_90"));
        }

        @Override
        public int getAvailabilityStatus() {
            return AVAILABLE;
        }

        @Override
        public int getSliceHighlightMenuRes() {
            return R.string.menu_key_display;
        }
    
}