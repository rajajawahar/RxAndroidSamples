package com.silicon.rxjavaexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.silicon.rxjavaexample.model.Customer;
import com.silicon.rxjavaexample.model.Trust;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

public class OperatorsActivity extends AppCompatActivity {

    private static final String TAG = OperatorsActivity.class.getSimpleName();

    private String thefirstcalculation;


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

    private void getIterList(Observable<List<Customer>> customerList) {
        customerList.flatMapIterable(new Func1<List<Customer>, Iterable<Customer>>() {
            @Override
            public Iterable<Customer> call(List<Customer> customers) {
                return customers;
            }
        }).map(new Func1<Customer, Trust>() {
            @Override
            public Trust call(Customer customer) {
                return customer.getTrust();
            }
        }).toList().subscribe(trusts -> {

        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });
    }


    private Observable<List<Customer>> getCustomerList() {

        return Observable.fromCallable((Func0<List<Customer>>) () -> {

            Trust trust = new Trust();
            trust.setFacebookConnection("gagag");
            trust.setId(1);
            trust.setLinkedInConnection("gagag");
            trust.setOfficiaEmail("gaga");
            List<Customer> customers = new ArrayList<Customer>();
            customers.add(new Customer(1, "name", "rajajawahar77@gmail.com", "9994267918", "klanngag", trust));
            customers.add(new Customer(1, "name", "rajajawahar77@gmail.com", "9994267918", "klanngag", trust));
            customers.add(new Customer(1, "name", "rajajawahar77@gmail.com", "9994267918", "klanngag", trust));
            customers.add(new Customer(1, "name", "rajajawahar77@gmail.com", "9994267918", "klanngag", trust));
            customers.add(new Customer(1, "name", "rajajawahar77@gmail.com", "9994267918", "klanngag", trust));
            return customers;
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
//        Observable.concat(getFirstCustomer(), getSecondCustomer(), getThirdCustomer()).filter(new Predicate<Customer>() {
//            @Override
//            public boolean test(Customer customer) throws Exception {
//                return customer.getEmailId().equalsIgnoreCase("mohamedraja_77@yahoo.com") && customer.getId() == 4;
//            }
//        }).toList().subscribe(customers ->
//
//                        Log.d(TAG, "onNext: " + customers.size()),
//
//                throwable -> {
//
//                });

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


//    basic difference between Concat and Flatmap is ordering the Elements

    private Observable<List<Customer>> getCustomerFromFlatMap() {


        return Observable.fromCallable(new Callable<List<Customer>>() {
            @Override
            public List<Customer> call() throws Exception {
                List<Customer> customers = new ArrayList<Customer>();
                return customers;
            }
        }).flatMapIterable(new Func1<List<Customer>, Iterable<Customer>>() {
            @Override
            public Iterable<Customer> call(List<Customer> customers) {
                return customers;
            }
        }).filter(new Func1<Customer, Boolean>() {
            @Override
            public Boolean call(Customer customer) {
                return customer.address != null;
            }
        }).toList();
    }


    private Observable<List<Customer>> getCustomerFromConcatMap() {


        return Observable.fromCallable(new Callable<List<Customer>>() {
            @Override
            public List<Customer> call() throws Exception {
                List<Customer> customers = new ArrayList<Customer>();
                return customers;
            }
        }).concatMapIterable(new Func1<List<Customer>, Iterable<Customer>>() {
            @Override
            public Iterable<Customer> call(List<Customer> customers) {
                return customers;
            }
        }).filter(new Func1<Customer, Boolean>() {
            @Override
            public Boolean call(Customer customer) {
                return customer.address != null;
            }
        }).toList();
    }


}
