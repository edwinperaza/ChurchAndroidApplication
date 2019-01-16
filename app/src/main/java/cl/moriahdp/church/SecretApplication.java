package cl.moriahdp.church;

import android.app.Application;

import cl.moriahdp.church.utils.TinyDB;
import io.realm.Realm;
import io.realm.RealmConfiguration;


public class SecretApplication extends Application {

    public static final String PRIVACY_PASSWORD_ACTIVE_SESSION = "privacy_password_active_sesion";
    private static SecretApplication instance;

    private TinyDB mTinyDB;

    public SecretApplication() {
        instance = this;
    }

    public static SecretApplication getInstance() {
        if (instance == null) {
            instance = new SecretApplication();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mTinyDB = new TinyDB(this);
        initRealm();
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(config);
    }

    public TinyDB getTinyDB() {
        return mTinyDB;
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        SecretApplication.getInstance().getTinyDB().putBoolean(PRIVACY_PASSWORD_ACTIVE_SESSION, true);
    }
}
