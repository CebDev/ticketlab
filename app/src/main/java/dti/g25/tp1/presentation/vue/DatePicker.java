package dti.g25.tp1.presentation.vue;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePicker extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int annee = c.get(Calendar.YEAR);
        int mois = c.get(Calendar.MONTH);
        int jour = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getContext(), AlertDialog.THEME_DEVICE_DEFAULT_DARK, (DatePickerDialog.OnDateSetListener) getActivity(), annee, mois, jour);
    }
}
