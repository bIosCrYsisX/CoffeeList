package rynkbit.tk.coffeelist.admin;

import android.content.ContentResolver;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import rynkbit.tk.coffeelist.R;

/**
 * Created by michael on 1/11/17.
 */

public class AdminPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public AdminPagerAdapter(
            Context context,
            FragmentManager fragmentManager) {
        super(fragmentManager);

        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;

        switch (position){
            case 0:
                fragment = new UserManagementFragment();
                break;
            case 1:
                fragment = new SettingsManagementFragment();
                break;
            default:
                fragment = null;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return mContext.getString(R.string.user_management_section_title);
            case 1:
                return mContext.getString(R.string.settins_management_section_title);
            default:
                return "Shouldn't exist";
        }
    }
}
