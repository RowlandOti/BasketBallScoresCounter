package com.rowland.scorescounter.presentation.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rowland.scorescounter.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ScoresFragment extends ABaseFragment {

    // Logging Identifier for class
    private final String LOG_TAG = ScoresFragment.class.getSimpleName();

    public static final String TEAM_NAME = "team_name";
    private static final String SCORE_KEY = "previous_score";
    private String mTeamName;

    private int mScoreValue = 0;

    @Bind(R.id.scores_team_txt)
    TextView mScoresTeamTxt;
    @Bind(R.id.scores_value)
    TextView mScoresTxt;
    @Bind(R.id.score_1_btn)
    Button mScores1Btn;
    @Bind(R.id.score_2_btn)
    Button mScores2Btn;
    @Bind(R.id.score_3_btn)
    Button mScores3Btn;

    public void reset() {
        mScoreValue = 0;
        mScoresTxt.setText(String.valueOf(mScoreValue));
    }

    public interface onResetClickListener {
        void onResetClicked();
    }

    public ScoresFragment() {
    }

    public static ScoresFragment newInstance(Bundle args) {
        ScoresFragment fragmentInstance = new ScoresFragment();
        if (args != null) {
            fragmentInstance.setArguments(args);
        }
        return fragmentInstance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) {
            mTeamName = getArguments().getString(ScoresFragment.TEAM_NAME);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mScoreValue = savedInstanceState.getInt(ScoresFragment.SCORE_KEY);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ScoresFragment.SCORE_KEY, mScoreValue);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_scores, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mScoresTeamTxt.setText(mTeamName);
        mScores1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScoreValue = mScoreValue + 1;
                mScoresTxt.setText(String.valueOf(mScoreValue));
            }
        });
        mScores2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScoreValue = mScoreValue + 2;
                mScoresTxt.setText(String.valueOf(mScoreValue));
            }
        });
        mScores3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScoreValue = mScoreValue + 3;
                mScoresTxt.setText(String.valueOf(mScoreValue));
            }
        });
    }

    public void setmScoreValue(int mScoreValue) {
        this.mScoreValue = mScoreValue;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
