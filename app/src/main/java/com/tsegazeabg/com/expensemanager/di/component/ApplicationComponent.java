package com.tsegazeabg.com.expensemanager.di.component;

import com.tsegazeabg.com.expensemanager.ExpenseManagerApp;
import com.tsegazeabg.com.expensemanager.di.module.ApplicationModule;
import com.tsegazeabg.com.expensemanager.domain.repository.AccountRep;
import com.tsegazeabg.com.expensemanager.domain.repository.CategoryRepository;
import com.tsegazeabg.com.expensemanager.domain.repository.TransactionRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by user on 11/25/2017.
 */
@Singleton
@Component(modules ={ ApplicationModule.class})
public interface ApplicationComponent {

    void inject(ExpenseManagerApp myApp);

    //   void inject(BaseActivity baseActivity);
//
//   //Exposed to sub-graphs.
    //@ApplicationContext
   // Context context();

    //   ThreadExecutor threadExecutor();

    void inject(AccountRep accountRep);
    void inject(CategoryRepository categoryRepository);
    void inject(TransactionRepository transactionRepository);


}
