package dti.g25.tp1.presentation.presenteur;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

import dti.g25.tp1.ui.activite.ActiviteAfficherProjets;

public abstract class PresenteurBase {
    private Activity _activite;

    public PresenteurBase(Activity activite) {
        _activite = activite;
    }

    public void retourActivitePrincipale() {
        Intent intent = new Intent(_activite.getApplicationContext(), ActiviteAfficherProjets.class);

        _activite.startActivity(intent);
        _activite.finish();
    }
}
