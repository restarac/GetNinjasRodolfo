package rodolfo.izidoro.getninjas.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import rodolfo.izidoro.getninjas.fragments.Aceitos;
import rodolfo.izidoro.getninjas.fragments.Disponiveis;

/**
 * Created by Rocketz on 23/02/2017.
 */

public class CustomPageAdapter extends FragmentStatePagerAdapter {
    private String fragments[] = {"DISPONÍVEIS ", "ACEITOS"};

    public CustomPageAdapter(FragmentManager fm) {
        super(fm);
    }


    //Adapter para os Fragments, ViewPager

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = new Fragment();

        switch (position){
            case 0: fragment = new Disponiveis();
                break;

            case 1: fragment = new Aceitos();
                break;




        }
        return fragment;
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments[position];
    }
}