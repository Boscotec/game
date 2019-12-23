package com.boscotec.game.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.boscotec.game.R;
import com.boscotec.game.interfaces.Listener;

public class FragmentCalculate extends Fragment implements View.OnClickListener {

    RadioGroup mHomeFirstGame, mHomeSecondGame, mHomeThirdGame, mHomeFourthGame, mHomeFifthGame;
    RadioGroup mAwayFirstGame, mAwaySecondGame, mAwayThirdGame, mAwayFourthGame, mAwayFifthGame;
    private static int HR1,HR2,HR3,HR4,HR5;
    private static int AR1,AR2,AR3,AR4,AR5;
    private TextView mValue;
    private Listener callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (Listener) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calculate, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        mHomeFirstGame = view.findViewById(R.id.mHomeFirstGame);
        mHomeSecondGame = view.findViewById(R.id.mHomeSecondGame);
        mHomeThirdGame = view.findViewById(R.id.mHomeThirdGame);
        mHomeFourthGame = view.findViewById(R.id.mHomeFourthGame);
        mHomeFifthGame = view.findViewById(R.id.mHomeFifthGame);

        mAwayFirstGame = view.findViewById(R.id.mAwayFirstGame);
        mAwaySecondGame = view.findViewById(R.id.mAwaySecondGame);
        mAwayThirdGame = view.findViewById(R.id.mAwayThirdGame);
        mAwayFourthGame = view.findViewById(R.id.mAwayFourthGame);
        mAwayFifthGame = view.findViewById(R.id.mAwayFifthGame);

        mValue = view.findViewById(R.id.mValue);
        Button mButton = view.findViewById(R.id.mButton);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.mButton){
           calculate();
        }
    }

    private void calculate(){
        if(!isRadiosChecked()) {
            Toast.makeText(getContext(),"Tick all options", Toast.LENGTH_SHORT).show();
            return;
        }

        int HomeTotal = HR1 + HR2 + HR3 + HR4 + HR5;
        int AwayTotal = AR1 + AR2 + AR3 + AR4 + AR5;

        float home = (HomeTotal*100)/15;
        float away = (AwayTotal*100)/15;

        float FinalAnswer = (home + 100 - away) / 2; // (home + 100.00 - away)/2.0;

        mValue.setText(String.format(getString(R.string.value), Float.toString(FinalAnswer)));
    }

    private boolean isRadiosChecked(){
       switch (mHomeFirstGame.getCheckedRadioButtonId()){
           case R.id.H13: HR1 = 3; break;
           case R.id.H11: HR1 = 1; break;
           case R.id.H10: HR1 = 0; break;
           default:return false;
       }

        switch (mHomeSecondGame.getCheckedRadioButtonId()){
            case R.id.H23: HR2 = 3; break;
            case R.id.H21: HR2 = 1; break;
            case R.id.H20: HR3 = 0; break;
            default:return false;
        }

        switch (mHomeThirdGame.getCheckedRadioButtonId()){
            case R.id.H33: HR3 = 3; break;
            case R.id.H31: HR3 = 1; break;
            case R.id.H30: HR3 = 0; break;
            default:return false;
        }

        switch (mHomeFourthGame.getCheckedRadioButtonId()){
            case R.id.H43: HR4 = 3; break;
            case R.id.H41: HR4 = 1; break;
            case R.id.H40: HR4 = 0; break;
            default:return false;
        }

        switch (mHomeFifthGame.getCheckedRadioButtonId()){
            case R.id.H53: HR5 = 3; break;
            case R.id.H51: HR5 = 1; break;
            case R.id.H50: HR5 = 0; break;
            default:return false;
        }

        switch (mAwayFirstGame.getCheckedRadioButtonId()){
            case R.id.A13: AR1 = 3; break;
            case R.id.A11: AR1 = 1; break;
            case R.id.A10: AR1 = 0; break;
            default:return false;
        }

        switch (mAwaySecondGame.getCheckedRadioButtonId()){
            case R.id.A23: AR2 = 3; break;
            case R.id.A21: AR2 = 1; break;
            case R.id.A20: AR2 = 0; break;
            default:return false;
        }

        switch (mAwayThirdGame.getCheckedRadioButtonId()){
            case R.id.A33: AR3 = 3; break;
            case R.id.A31: AR3 = 1; break;
            case R.id.A30: AR3 = 0; break;
            default:return false;
        }

        switch (mAwayFourthGame.getCheckedRadioButtonId()){
            case R.id.A43: AR4 = 3; break;
            case R.id.A41: AR4 = 1; break;
            case R.id.A40: AR4 = 0; break;
            default:return false;
        }

        switch (mAwayFifthGame.getCheckedRadioButtonId()){
            case R.id.A53: AR5 = 3; break;
            case R.id.A51: AR5 = 1; break;
            case R.id.A50: AR5 = 0; break;
            default:return false;
        }

        return true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }
}
