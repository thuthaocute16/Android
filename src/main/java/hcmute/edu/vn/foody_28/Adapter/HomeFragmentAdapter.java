package hcmute.edu.vn.foody_28.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import hcmute.edu.vn.foody_28.Activity.Home.Fragment.BuyALotFragment;
import hcmute.edu.vn.foody_28.Activity.Home.Fragment.CloseToYouFragment;
import hcmute.edu.vn.foody_28.Activity.Home.Fragment.FastDeliveryFragment;
import hcmute.edu.vn.foody_28.Activity.Home.Fragment.RateFragment;

public class HomeFragmentAdapter extends FragmentStatePagerAdapter
{

    public HomeFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public HomeFragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
            {
                return new CloseToYouFragment();
            }
            case 1:
            {
                return new BuyALotFragment();
            }
            case 2:
            {
                return new FastDeliveryFragment();
            }
            case 3:
            {
                return new RateFragment();
            }
            default:
                return new CloseToYouFragment();
        }

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        String title = "";
        switch (position)
        {
            case 0:
            {
                title = "Close to you";
                break;
            }
            case 1:
            {
                title = "Buy a lot";
                break;
            }
            case 2:
            {
                title = "Fast Delivery";
                break;
            }
            case 3:
            {
                title = "Rate";
            }
        }
        return title;
    }

    @Override
    public int getCount() {
        return 4;
    }

}