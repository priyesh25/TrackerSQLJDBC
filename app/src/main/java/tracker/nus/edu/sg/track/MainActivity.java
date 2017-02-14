package tracker.nus.edu.sg.track;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.FragmentController;
import android.content.DialogInterface;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import layout.ControlFragment;
import layout.MainFragment;
import layout.SettingsFragment;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout tabLayout;
    private Fragment currFr, prevFr;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize tabLayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setOnTabSelectedListener(this);

        Toast.makeText(this, "Tab Count : " + tabLayout.getTabCount(), Toast.LENGTH_LONG).show();
        getFragmentManager().beginTransaction().replace(R.id.fragmentPlaceHolder, new MainFragment()).commit();
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Toast.makeText(this, "You select tab : " + tab.getText(), Toast.LENGTH_SHORT).show();
        selectFragment(tab);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    public void selectFragment(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                currFr = new MainFragment();
                break;
            case 1:
                currFr = new ControlFragment();
                break;
            case 2:
                currFr = new SettingsFragment();
                break;
            default:
                currFr = new MainFragment();
                break;
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentPlaceHolder, currFr);
        ft.commit();
    }

}
