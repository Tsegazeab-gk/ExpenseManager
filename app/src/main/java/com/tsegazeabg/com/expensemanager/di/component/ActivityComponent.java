package com.tsegazeabg.com.expensemanager.di.component;

import com.tsegazeabg.com.expensemanager.di.PerActivity;
import com.tsegazeabg.com.expensemanager.di.module.ActivityModule;
import com.tsegazeabg.com.expensemanager.ui.activities.HomeActivity;

import dagger.Component;

/**
 * Created by user on 11/25/2017.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {
    void inject(HomeActivity homeActivity);
    //Exposed to sub-graphs.
    //AppCompatActivity activity();
}
