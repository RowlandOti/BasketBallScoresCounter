/*
 * Copyright 2015 Oti Rowland
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.rowland.scorescounter.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.rowland.scorescounter.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Oti Rowland on 12/20/2015.
 */
public class ABaseActivity extends AppCompatActivity {

    // Class Variables
    private final String LOG_TAG = ABaseActivity.class.getSimpleName();
    // ButterKnife injected Views
    // The inc_toolbar
    @Nullable
    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    // Should we show master-detail layout?
    protected boolean mIsTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Derived classes have access to this method
    protected void setToolbar(boolean showHomeUp, boolean showTitle, int iconResource) {
        setToolbar(mToolbar, showHomeUp, showTitle, iconResource);
    }

    // Derived methods have no direct access to this class
    public void setToolbar(Toolbar mToolbar, boolean isShowHomeUp, boolean isShowTitle, int iconResource) {
        // Does the inc_toolbar exist?
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            // Should we set up home-up button navigation?
            getSupportActionBar().setDisplayHomeAsUpEnabled(isShowHomeUp);
            // Should we display the title on the inc_toolbar?
            getSupportActionBar().setDisplayShowTitleEnabled(isShowTitle);
            // Should we set logo to appear in inc_toolbar?
            getSupportActionBar().setIcon(iconResource);
            //this.mToolbar.setLogo(R.drawable.ic_logo_48px);
        }
    }

    /**
     * Shows a {@link EditText} message.
     *
     * @param etToBeValidated An EditText whose data is to be validated.
     */
    private boolean isValidEditTextData(EditText etToBeValidated) {
        if (etToBeValidated.getText().toString().trim().length() == 0) {
            etToBeValidated.setError("Required");
            return false;
        }
        return true;
    }

    /**
     * Shows a {@link EditText} message.
     *
     * @param etToBeValidated An array of EditText whose data are to be validated.
     */
    protected boolean isValidEditTextData(EditText... etToBeValidated) {
        boolean isEtTextDataValid = true;
        int i = 0;
        while (i < etToBeValidated.length && isEtTextDataValid) {
            EditText et = etToBeValidated[i];
            isEtTextDataValid = isValidEditTextData(et);
            i++;
        }
        return isEtTextDataValid;
    }

    public List<Fragment> getVisibleFragments() {
        List<Fragment> allFragments = getSupportFragmentManager().getFragments();
        if (allFragments == null || allFragments.isEmpty()) {
            return Collections.emptyList();
        }

        List<Fragment> visibleFragments = new ArrayList<Fragment>();
        for (Fragment fragment : allFragments) {
            if (fragment.isVisible()) {
                visibleFragments.add(fragment);
            }
        }
        return visibleFragments;
    }
}
