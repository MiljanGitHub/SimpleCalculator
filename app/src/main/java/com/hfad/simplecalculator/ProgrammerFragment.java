package com.hfad.simplecalculator;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProgrammerFragment extends Fragment {

    RadioGroup rg;
    public ProgrammerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_programmer, container, false);
        rg = root.findViewById(R.id.radio_group);

        return root;
        //return inflater.inflate(R.layout.fragment_programmer, container, false);
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null){
            switch (savedInstanceState.getInt("button")){
                case R.id.bin_id:
                    //disableForBin();
                    break;
                case R.id.oct_id:
                    //disableForOctal();
                    break;
                case R.id.hex_id:
                    //disableForHexa();
                    break;
            }
        } else {
            //root.findViewById(R.id.radio_group).check(R.id.dec_id);
            rg.check(R.id.dec_id);
            //disableForDecimal();
        }


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = (RadioButton)getView().findViewById(checkedId);
                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked && R.id.bin_id == checkedId)
                {


                } else if (isChecked && R.id.dec_id == checkedId){
                   // disableForDecimal();
                } else if (isChecked && R.id.oct_id == checkedId){
                    //disableForOctal();
                } else if (isChecked && R.id.hex_id == checkedId){
                    //disableForHexa();
                }
            }
        });



    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("button", rg.getCheckedRadioButtonId());


    }

    private void disableForDecimal(){
        getView().findViewById(R.id.A_id).setEnabled(false);
        getView().findViewById(R.id.B_id).setEnabled(false);
        getView().findViewById(R.id.C_id).setEnabled(false);
        getView().findViewById(R.id.D_id).setEnabled(false);
        getView().findViewById(R.id.E_id).setEnabled(false);
        getView().findViewById(R.id.F_id).setEnabled(false);
    }

}
