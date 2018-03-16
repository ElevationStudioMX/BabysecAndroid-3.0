package com.example.desarrollo_elevation.babysec;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.example.desarrollo_elevation.babysec.tabs_infranter.Menu;
import com.example.desarrollo_elevation.babysec.tabs_infranter.favoritos;
import com.example.desarrollo_elevation.viveelite.R;
import com.example.desarrollo_elevation.babysec.tabs_infranter.home;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class Main_Activity_inicio extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    private static String contex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__inicio);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        viewPager = (ViewPager) findViewById(R.id.viewpagerinicio);
        viewPager.setAdapter(new CustomAdapter(getSupportFragmentManager(),getApplicationContext()));

        tabLayout = (TabLayout) findViewById(R.id.tabsinicio);
        tabLayout.setupWithViewPager(viewPager);


     //   View view2 = getLayoutInflater().inflate(R.layout.costume_view_imagen, null);
      //  view2.findViewById(R.id.icontab).setBackgroundResource(logo_elite);

        //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo_elite);

      /* // tabLayout.getTabAt(0).setIcon(R.mipmap.iconmenu);
        tabLayout.getTabAt(0).setIcon(R.mipmap.iconmenu);
        tabLayout.getTabAt(1).setIcon(R.mipmap.logo_elite_cuadradox5);
       // tabLayout.getTabAt(1).setIcon(new BitmapDrawable(bitmap));

//      tabLayout.getTabAt(1).setIcon(R.drawable.logo_elite);

        tabLayout.getTabAt(2).setIcon(R.mipmap.favoritos_menu_2);

      */View view1 = getLayoutInflater().inflate(R.layout.costume_view_imagen ,null);
        view1.findViewById(R.id.icontab).setBackgroundResource(R.mipmap.iconmenu);
        tabLayout.getTabAt(0).setCustomView(view1);

        View view2 = getLayoutInflater().inflate(R.layout.costume_view_imagen, null);
        view2.findViewById(R.id.icontab).setBackgroundResource(R.mipmap.logo_elitev1);
        //tabLayout.addTab(tabLayout.newTab().setCustomView(view2));
        tabLayout.getTabAt(1).setCustomView(view2);

        View view3 = getLayoutInflater().inflate(R.layout.costume_view_imagen, null);
        view3.findViewById(R.id.icontab).setBackgroundResource(R.mipmap.favoritos_menu_2);
        //tabLayout.addTab(tabLayout.newTab().setCustomView(view3));
        tabLayout.getTabAt(2).setCustomView(view3);


        viewPager.setCurrentItem(1);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });

        getDB();
    }


    private class CustomAdapter extends FragmentPagerAdapter {

        private String fragments [] = {"","", ""};

        public CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:







                    return new Menu();




                case 1:



                    return new home();


                case 2:



                    return new favoritos();

                default:
                    return null;
            }
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


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0)
        {
            return false;
        }


        return super.onKeyDown(keyCode, event);
    }

    public String conte(){
        contex = this.getClass().getSimpleName().toString();
        return contex;

    }

    public void getDB()
    {
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            Log.d("BASE DE DATOS", ""+sd.canWrite());
            if (sd.canWrite()) {
                String currentDBPath = "/data/data/" + getPackageName() + "/databases/elite_v15";
                String backupDBPath = "backupname.db";
                File currentDB = new File(currentDBPath);
                File backupDB = new File(sd, backupDBPath);
                Log.d("BASE DE DATOS", ""+currentDB.exists());
                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }
            }
        } catch (Exception e) {

        }
    }

}