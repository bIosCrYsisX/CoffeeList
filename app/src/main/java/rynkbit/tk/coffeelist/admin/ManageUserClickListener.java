package rynkbit.tk.coffeelist.admin;

import android.content.Intent;
import android.view.View;

import rynkbit.tk.coffeelist.admin.user.ManageUserActivity;

/**
 * Created by michael on 11/13/16.
 */
public class ManageUserClickListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), ManageUserActivity.class);
        view.getContext().startActivity(intent);
    }
}