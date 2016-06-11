package com.silicon.rxjavaexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.silicon.rxjavaexample.model.Customer;
import com.silicon.rxjavaexample.model.Trust;

import rx.Observable;
import rx.functions.Func0;
import rx.functions.Func1;

public class OperatorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    private Observable<Customer> getCustomerFromLocal() {
        return null;
    }

    private Observable<Customer> getCustomerFromOnline() {
        return Observable.fromCallable(new Func0<Customer>() {
            @Override
            public Customer call() {
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

    private Observable<Customer> getFirstDemo() {
        return Observable.concat(getCustomerFromLocal(),
                getCustomerFromOnline()).flatMap(new Func1<Customer, Observable<? extends Customer>>() {
            @Override
            public Observable<? extends Customer> call(Customer customer) {
                return null;
            }
        }).first(new Func1<Customer, Boolean>() {
            @Override
            public Boolean call(Customer customer) {
                return customer != null;
            }
        });
    }
}
