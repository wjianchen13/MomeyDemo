package com.example.momeydemo.tab;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.momeydemo.R;


/**
 * 底部tab切换Fragment
 */
public class TabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
    }

    /**
     * Fragment1
     * @param v
     */
    public void onTest1(View v) {
        switchFragment(1);
    }

    /**
     * Fragment2
     * @param v
     */
    public void onTest2(View v) {
        switchFragment(2);
    }

    /**
     * Fragment3
     * @param v
     */
    public void onTest3(View v) {
        switchFragment(3);
    }

    /**
     * Fragment4
     * @param v
     */
    public void onTest4(View v) {
        switchFragment(4);
    }

    /**
     * Fragment5
     * @param v
     */
    public void onTest5(View v) {
        switchFragment(5);
    }

    private void switchFragment(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment1 = fragmentManager.findFragmentByTag(TabFragment1.TAG);
        Fragment fragment2 = fragmentManager.findFragmentByTag(TabFragment2.TAG);
        Fragment fragment3 = fragmentManager.findFragmentByTag(TabFragment3.TAG);
        Fragment fragment4 = fragmentManager.findFragmentByTag(TabFragment4.TAG);
        Fragment fragment5 = fragmentManager.findFragmentByTag(TabFragment5.TAG);

        //先隐藏所有的
        if (fragment1 != null && fragment1.isVisible()) {
            fragmentTransaction.hide(fragment1);
        }
        if (fragment2 != null && fragment2.isVisible()) {
            fragmentTransaction.hide(fragment2);
        }
        if (fragment3 != null && fragment3.isVisible()) {
            fragmentTransaction.hide(fragment3);
        }
        if (fragment4 != null && fragment4.isVisible()) {
            fragmentTransaction.hide(fragment4);
        }
        if (fragment5 != null && fragment5.isVisible()) {
            fragmentTransaction.hide(fragment5);
        }

        if (position == 1) {
            if (fragment1 == null) {
                fragment1 = new TabFragment1();
                fragmentTransaction.add(R.id.flyt_fragment, fragment1, TabFragment1.TAG);
            }
            fragmentTransaction.show(fragment1);
        } else if (position == 2) {
            if (fragment2 == null) {
                fragment2 = new TabFragment2();
                fragmentTransaction.add(R.id.flyt_fragment, fragment2, TabFragment2.TAG);
            }
            fragmentTransaction.show(fragment2);

        } else if (position == 3) {
            if (fragment3 == null) {
                fragment3 = new TabFragment3();
                fragmentTransaction.add(R.id.flyt_fragment, fragment3, TabFragment3.TAG);
            }
            fragmentTransaction.show(fragment3);
        } else if (position == 4) {
            if (fragment4 == null) {
                fragment4 = new TabFragment4();
                fragmentTransaction.add(R.id.flyt_fragment, fragment4, TabFragment4.TAG);
            }
            fragmentTransaction.show(fragment4);
        } else if (position == 5) {
            if (fragment5 == null) {
                fragment5 = new TabFragment5();
                fragmentTransaction.add(R.id.flyt_fragment, fragment5, TabFragment5.TAG);
            }
            fragmentTransaction.show(fragment5);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

}