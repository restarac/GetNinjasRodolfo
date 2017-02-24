package rodolfo.izidoro.getninjas.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import rodolfo.izidoro.getninjas.R;
import rodolfo.izidoro.getninjas.adapter.CustomPageAdapter;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //View pager
        viewPager = (ViewPager) findViewById(R.id.vpMain);
        viewPager.setAdapter(new CustomPageAdapter(getSupportFragmentManager()));

        tabLayout = (TabLayout) findViewById(R.id.tlMenu);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
 //    tabLayout.getTabAt(1).setIcon(tabIcons[1]);



    }
}
