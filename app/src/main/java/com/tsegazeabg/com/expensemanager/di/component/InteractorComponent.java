package com.tsegazeabg.com.expensemanager.di.component;

import com.tsegazeabg.com.expensemanager.di.module.InteractorModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by user on 11/27/2017.
 */
@Singleton
@Component(modules = {InteractorModule.class}) //, DataModule.class
public interface InteractorComponent {

    /*
     void inject(RequestTicketPresenter presenter);
    void inject(OrderConfirmationPresenter presenter);
    void inject(UserInteractor interactor);
     */

}
//Presenter

/*public class RequestTicketPresenter extends BasePresenter&amp;lt;RequestTicketView&amp;gt; { &amp;nbsp; &amp;nbsp;
    @Inject TicketInteractor interactor; &amp;nbsp; &amp;nbsp;
    Ticket ticket; &amp;nbsp;
&amp;nbsp;
    private RequestTicketPresenter() { &amp;nbsp; &amp;nbsp; &amp;nbsp; &amp;nbsp;
        Injector.getInteractorComponent().inject(this); &amp;nbsp; &amp;nbsp;
    } &amp;nbsp; &amp;nbsp;
    // presenter related methods
} */