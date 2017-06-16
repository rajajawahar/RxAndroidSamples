package com.silicon.rxjavaexample;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Func3;

import static android.text.TextUtils.isEmpty;

/**
 * A login screen that offers login via email/password.
 */
public class CombineLatestActivity extends AppCompatActivity {


    private static final String TAG = CombineLatestActivity.class.getSimpleName();
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.mobileno)
    EditText mobileno;
    @BindView(R.id.emailId)
    EditText emailId;
    @BindView(R.id.validate_btn)
    Button validateBtn;
    @BindView(R.id.email_login_form)
    LinearLayout emailLoginForm;


    private Observable<CharSequence> usernameObserver,
            mobilenoObserver, emailIdObserver;
    private Subscription subscription;
    public static final Pattern EMAIL_ADDRESS
            = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combine_latest);
        ButterKnife.bind(this);
        combineLatestEvents();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null)
            subscription.unsubscribe();
    }

    private void combineLatestEvents() {
        subscription = Observable.combineLatest(usernameObserver, mobilenoObserver, emailIdObserver,
            (newUserName, newMobileNo, newEmailId) -> {
                boolean usernameValid = !isEmpty(newUserName);
                if (!usernameValid) {
                    username.setError("Invalid UserName!");
                }

                boolean mobileNoValid = !isEmpty(newMobileNo);
                if (mobileNoValid) {
                    int num = Integer.parseInt(newMobileNo.toString());
                    mobileNoValid = num > 0 && num <= 100;
                }
                if (!mobileNoValid) {
                    mobileno.setError("Invalid MobileNo!");
                }

                boolean emailID = !isEmpty(newEmailId) &&
                        EMAIL_ADDRESS.matcher(newEmailId).matches();
                if (!emailID) {
                    emailId.setError("Invalid Email Address!");
                }

                return usernameValid && mobileNoValid && emailID;
            }).subscribe(booleanObserver);
    }

    Observer<Boolean> booleanObserver = new Observer<Boolean>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Boolean formValid) {


        }
    };


}

