package com.silicon.rxjavaexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.silicon.rxjavaexample.model.Customer;
import com.silicon.rxjavaexample.model.Trust;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class OperatorsActivity extends AppCompatActivity {

    private static final String TAG = OperatorsActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_operators);


    }


    private Observable<Customer> getFirstCustomer() {
        return Observable.fromCallable(new Func0<Customer>() {
            @Override
            public Customer call() {
                Log.d(TAG, "online call() called with: " + "");
                Customer customer = new Customer();
                customer.setAddress("First Address");
                customer.setEmailId("mohamedraja_77@yahoo.com");
                customer.setId(1);
                customer.setName("Raja Mohamed");
                customer.setPhoneNo("9994267918");
                Trust trust = new Trust();
                trust.setId(1);
                trust.setFacebookConnection("raja7g@hotmail.com");
                trust.setLinkedInConnection("athamraja77@gmail.com");
                trust.setOfficiaEmail("rj.orgware@gmail.com");
                customer.setTrust(trust);
                return customer;
            }
        });
    }


    private void concatOperations() {
        Observable.concat(getCustomerFromLocal(), getSecondCustomer())
                .first(new Func1<Customer, Boolean>() {
                    @Override
                    public Boolean call(Customer customer) {
                        return customer == null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Customer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError() called with: " + "e = [" + e + "]");
                    }

                    @Override
                    public void onNext(Customer customer) {
                        Log.d(TAG, "onNext() called with: " + "customer = [" + customer + "]");
                    }
                });
    }

    private void mergerOperations() {
        Observable.merge(getFirstCustomer(), getSecondCustomer(), getThirdCustomer())
                .toList().flatMap(new Func1<List<Customer>, Observable<List<Customer>>>() {
            @Override
            public Observable<List<Customer>> call(List<Customer> customers) {
                return getCustomerWithTrust(customers);
            }
        }).flatMapIterable(new Func1<List<Customer>, Iterable<Customer>>() {
            @Override
            public Iterable<Customer> call(List<Customer> customers) {
                return customers;
            }
        }).subscribe(new Action1<Customer>() {
            @Override
            public void call(Customer customer) {
                Log.d(TAG, "call: " + customer.getTrust().toString());

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.d(TAG, "call: " + throwable.getLocalizedMessage());

            }
        });

    }

    private Observable<Customer> getSecondCustomer() {
        return Observable.fromCallable(new Func0<Customer>() {
            @Override
            public Customer call() {
                Log.d(TAG, "online call() called with: " + "");
                Customer customer = new Customer();
                customer.setAddress("Second Address");
                customer.setEmailId("mohamedraja_77@yahoo.com");
                customer.setId(1);
                customer.setName("Raja Mohamed");
                customer.setPhoneNo("9994267918");
                Trust trust = new Trust();
                trust.setId(1);
                trust.setFacebookConnection("raja7g@hotmail.com");
                trust.setLinkedInConnection("athamraja77@gmail.com");
                trust.setOfficiaEmail("rj.orgware@gmail.com");
                customer.setTrust(trust);
                return customer;
            }
        });
    }

    private Observable<Customer> getThirdCustomer() {
        return Observable.fromCallable(new Func0<Customer>() {
            @Override
            public Customer call() {
                Log.d(TAG, "online call() called with: " + "");
                Customer customer = new Customer();
                customer.setAddress("Third Address");
                customer.setEmailId("athamraja77@gmail.com");
                customer.setId(1);
                customer.setName("Raja Mohamed");
                customer.setPhoneNo("9994267918");
                Trust trust = new Trust();
                trust.setId(1);
                trust.setFacebookConnection("raja7g@hotmail.com");
                trust.setLinkedInConnection("athamraja77@gmail.com");
                trust.setOfficiaEmail("rj.orgware@gmail.com");
                customer.setTrust(trust);
                return customer;
            }
        });
    }

    private Observable<Customer> getCustomerFromLocal() {
        return Observable.fromCallable(new Func0<Customer>() {
            @Override
            public Customer call() {
                Log.d(TAG, "local call() called with: " + "");
                return null;
            }
        });
    }


    private void getMultipleCalls() {
        Observable.concat(getFirstCustomer(), getSecondCustomer(), getThirdCustomer()).
                filter(new Func1<Customer, Boolean>() {
                    @Override
                    public Boolean call(Customer customer) {
                        return customer.getEmailId().equalsIgnoreCase("mohamedraja_77@yahoo.com") && customer.getId() == 4;
                    }
                })
                .toList()
                .subscribe(new Observer<List<Customer>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Customer> customers) {
                        Log.d(TAG, "onNext: " + customers.size());
                    }
                });
    }


    public Observable<List<Customer>> getCustomerWithTrust(final List<Customer> customers) {
        return Observable.fromCallable(new Func0<List<Customer>>() {
            @Override
            public List<Customer> call() {
                List<Customer> myCustomers1 = new ArrayList<Customer>(customers.size());
                for (Customer customer : customers) {
                    customer.getTrust().setFacebookConnection("200");
                    customer.getTrust().setLinkedInConnection("500");
                    customer.getTrust().setOfficiaEmail("rj.orgware@gmail.com");
                    customer.getTrust().setId(1);
                    myCustomers1.add(customer);

                }
                return myCustomers1;
            }
        });
    }


}
