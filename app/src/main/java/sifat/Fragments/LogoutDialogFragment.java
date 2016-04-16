package sifat.Fragments;


import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import sifat.catagal.R;

/**
 * Created by Md. Sifat-Ul Haque on 4/8/2016.
 */
public class LogoutDialogFragment extends DialogFragment implements View.OnClickListener {

    Button btnYes,btnNo;
    Communicator communicator;

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        this.communicator= (Communicator) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logout,null);

        btnYes = (Button) view.findViewById(R.id.btnYes);
        btnYes.setOnClickListener(this);
        btnNo = (Button) view.findViewById(R.id.btnNo);
        btnNo.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        dismiss();
        if(v.getId()==R.id.btnYes)
        {
            communicator.getMessage("Yes");
        }
        else
        {
            communicator.getMessage("No");
        }
    }

    public interface Communicator {
        public void getMessage(String message);
    }

}
