package com.flutterwave.raveandroid;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.flutterwave.raveandroid.di.components.AppComponent;
import com.flutterwave.raveandroid.di.components.DaggerAppComponent;
import com.flutterwave.raveandroid.di.modules.AndroidModule;
import com.flutterwave.raveandroid.di.modules.NetworkModule;
import com.flutterwave.raveandroid.responses.SubAccount;

import org.parceler.Parcels;

import java.util.List;

import static com.flutterwave.raveandroid.RaveConstants.LIVE_URL;
import static com.flutterwave.raveandroid.RaveConstants.RAVEPAY;
import static com.flutterwave.raveandroid.RaveConstants.RAVE_PARAMS;
import static com.flutterwave.raveandroid.RaveConstants.RAVE_REQUEST_CODE;
import static com.flutterwave.raveandroid.RaveConstants.STAGING_URL;

public class RavePayManager {
    private String email;
    private double amount = -1;
    private String publicKey;
    private String encryptionKey;
    private String txRef;
    private String narration = "";
    private String currency = "NGN";
    private String country = "NG";
    private String fName = "";
    private String lName = "";
    private String meta = "";
    private String subAccounts = "";
    private String payment_plan;
    private Activity activity;
    boolean withCard = true;
    boolean withAccount = true;
    boolean withAch = false;
    boolean withMpesa = false;
    boolean withGHMobileMoney = false;
    boolean withUgMobileMoney = false;
    boolean withRwfMobileMoney = false;
    boolean withZmMobileMoney = false;
    boolean withUk = false;
    boolean withFrancMobileMoney = false;
    boolean withBankTransfer = false;
    private int theme = R.style.DefaultTheme;
    boolean staging = true;
    boolean allowSaveCard = true;
    boolean isPreAuth =  false;
    boolean showStagingLabel = true;
    boolean displayFee = true;
    private boolean isPermanent = false;
    private int duration = 0;
    private int frequency = 0;
    private boolean withUssd;

    public RavePayManager allowSaveCardFeature(boolean allowSaveCard) {
        this.allowSaveCard = allowSaveCard;
        return this;
    }

    public RavePayManager onStagingEnv(boolean staging) {
        this.staging = staging;
        return this;
    }

    public RavePayManager withTheme(int theme) {
        this.theme = theme;
        return this;
    }

    public RavePayManager(Activity activity) {
        this.activity = activity;
    }

    public RavePayManager acceptAchPayments(boolean withAch) {
        this.withAch = withAch;
        return this;
    }

    public RavePayManager acceptCardPayments(boolean withCard) {
        this.withCard = withCard;
        return this;
    }

    public RavePayManager acceptMpesaPayments(boolean withMpesa) {
        this.withMpesa = withMpesa;
        return this;
    }

    public RavePayManager acceptAccountPayments(boolean withAccount) {
        this.withAccount = withAccount;
        return this;
    }

    public RavePayManager acceptGHMobileMoneyPayments(boolean withGHMobileMoney) {
        this.withGHMobileMoney = withGHMobileMoney;
        return this;
    }

    public RavePayManager acceptUgMobileMoneyPayments(boolean withUgMobileMoney) {
        this.withUgMobileMoney = withUgMobileMoney;
        return this;
    }

    public RavePayManager acceptRwfMobileMoneyPayments(boolean withRwfMobileMoney) {
        this.withRwfMobileMoney = withRwfMobileMoney;
        return this;
    }

    public RavePayManager acceptZmMobileMoneyPayments(boolean withZmMobileMoney) {
        this.withZmMobileMoney = withZmMobileMoney;
        return this;
    }

    public RavePayManager acceptUkPayments(boolean withUk) {
        this.withUk = withUk;
        return this;
    }

    public RavePayManager acceptFrancMobileMoneyPayments(boolean withFrancMobileMoney) {
        this.withFrancMobileMoney = withFrancMobileMoney;
        return this;
    }

    public RavePayManager acceptBankTransferPayments(boolean withBankTransfer) {
        this.withBankTransfer = withBankTransfer;
        return this;
    }


    public RavePayManager acceptBankTransferPayments(boolean withBankTransfer, boolean isPermanent) {
        this.withBankTransfer = withBankTransfer;
        this.isPermanent = isPermanent;
        return this;
    }

    public RavePayManager acceptBankTransferPayments(boolean withBankTransfer, int duration, int frequency) {
        this.withBankTransfer = withBankTransfer;
        this.duration = duration;
        this.frequency = frequency;
        return this;
    }

    public RavePayManager acceptUssdPayments(boolean withUssd) {
        this.withUssd = withUssd;
        return this;
    }

    public RavePayManager isPreAuth(boolean isPreAuth){
        this.isPreAuth = isPreAuth;
        return this;
    }

    public RavePayManager setMeta(List<Meta> meta) {
        this.meta = Utils.stringifyMeta(meta);
        return this;
    }

    public RavePayManager setSubAccounts(List<SubAccount> subAccounts){
        this.subAccounts = Utils.stringifySubaccounts(subAccounts);
        return this;
    }

    public RavePayManager showStagingLabel(boolean showStagingLabel) {
        this.showStagingLabel = showStagingLabel;
        return this;
    }


    public RavePayManager setEmail(String email) {
        this.email = email;
        return this;
    }

    public RavePayManager setAmount(double amount) {
        if (amount != 0) {
            this.amount = amount;
        }
        return this;
    }

    public RavePayManager setPublicKey(String publicKey) {
        this.publicKey = publicKey;
        return this;
    }

    public RavePayManager setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
        return this;
    }

    public RavePayManager setTxRef(String txRef) {
        this.txRef = txRef;
        return this;
    }

    public RavePayManager setNarration(String narration) {
        this.narration = narration;
        return this;
    }

    public RavePayManager setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public RavePayManager setCountry(String country) {
        this.country = country;
        return this;
    }

    public RavePayManager setfName(String fName) {
        this.fName = fName;
        return this;
    }

    public RavePayManager setlName(String lName) {
        this.lName = lName;
        return this;
    }

    public RavePayManager setPaymentPlan(String payment_plan) {
        this.payment_plan = payment_plan;
        return this;
    }

    public void initialize() {

        if (activity != null) {

            Intent intent = new Intent(activity, NewRavePayActivity.class);
            intent.putExtra(RAVE_PARAMS, Parcels.wrap(createRavePayInitializer()));
            activity.startActivityForResult(intent, RAVE_REQUEST_CODE) ;
        } else {
            Log.d(RAVEPAY, "Context is required!");
        }

    }

    public Raver initializeNoUi() {

        RavePayInitializer ravePayInitializer = createRavePayInitializer();
        AppComponent component = setUpGraph();

        return new Raver(ravePayInitializer, component);

    }

    public RavePayManager shouldDisplayFee(boolean displayFee) {
        this.displayFee = displayFee;
        return this;
    }

    public RavePayInitializer createRavePayInitializer() {

        // Todo: Add payment type order

        return new RavePayInitializer(
                email,
                amount,
                publicKey,
                encryptionKey,
                txRef,
                narration,
                currency,
                country,
                fName,
                lName,
                withCard,
                withAccount,
                withMpesa,
                withGHMobileMoney,
                withUgMobileMoney,
                withRwfMobileMoney,
                withZmMobileMoney,
                withAch,
                withBankTransfer,
                withUk,
                withUssd,
                withFrancMobileMoney,
                theme,
                isPermanent,
                duration,
                frequency,
                staging,
                meta,
                subAccounts,
                payment_plan,
                isPreAuth,
                showStagingLabel,
                displayFee);
    }

    private AppComponent setUpGraph() {


        String baseUrl;

        if (staging) {
            baseUrl = STAGING_URL;
        } else {
            baseUrl = LIVE_URL;
        }

        return DaggerAppComponent.builder()
                .androidModule(new AndroidModule(activity))
                .networkModule(new NetworkModule(baseUrl))
                .build();


    }
}