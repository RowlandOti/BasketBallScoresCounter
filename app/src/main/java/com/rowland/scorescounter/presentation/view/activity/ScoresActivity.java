package com.rowland.scorescounter.presentation.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.rowland.scorescounter.R;
import com.rowland.scorescounter.presentation.view.fragment.ScoresFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ScoresActivity extends ABaseActivity implements ScoresFragment.onResetClickListener {

    // Logging Identifier for class
    private final String LOG_TAG = ScoresActivity.class.getSimpleName();

    @Bind(R.id.score_reset_btn)
    public Button mScoresResetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        ButterKnife.bind(this);
        setToolbar(false, true, R.drawable.ic_logo_48px);
        if (findViewById(R.id.fragment_scoresA_container) != null && findViewById(R.id.fragment_scoresB_container) != null) {
            if (savedInstanceState != null) {
                return;
            } else {
                Bundle argsA = new Bundle();
                argsA.putString(ScoresFragment.TEAM_NAME, "Team A");
                showScoresAFragment(argsA);

                Bundle argsB = new Bundle();
                argsB.putString(ScoresFragment.TEAM_NAME, "Team B");
                showScoresBFragment(argsB);
            }
        }

        mScoresResetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResetClicked();
            }
        });
    }

    private void showScoresAFragment(Bundle args) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ScoresFragment mainFragment = ScoresFragment.newInstance(args);
        // Prefer replace() over add() see <a>https://github.com/RowlandOti/PopularMovies/issues/1</a>
        ft.replace(R.id.fragment_scoresA_container, mainFragment);
        ft.commit();
    }

    private void showScoresBFragment(Bundle args) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ScoresFragment mainFragment = ScoresFragment.newInstance(args);
        // Prefer replace() over add() see <a>https://github.com/RowlandOti/PopularMovies/issues/1</a>
        ft.replace(R.id.fragment_scoresB_container, mainFragment);
        ft.commit();
    }

    @Override
    public void onResetClicked() {
        List<Fragment> visibleFragments = getVisibleFragments();
        for(Fragment s:visibleFragments){
            ((ScoresFragment)s).reset();
        }
    }
}
