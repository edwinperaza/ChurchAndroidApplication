package cl.moriahdp.church.baseclasses;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import cl.moriahdp.church.R;

public abstract class BaseFragment extends Fragment implements IBackPressedCallback {

    private BaseActivity mBaseActivity;
    protected BasePresenter mBasePresenter;
    protected ConnectivityManager mConnectivityManager;
    private View mLoadingOverlay;
    private View mErrorConectionFullScreenView;
    private View mErrorLoadingDataFullScreenView;
    private View mEmptyStateFullScreenView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_base, container, false);
        FrameLayout mMainContainer = rootView.findViewById(R.id.main_fragment_container);
        mLoadingOverlay = rootView.findViewById(R.id.pb_base);
        mErrorConectionFullScreenView = rootView.findViewById(R.id.error_connection);
        mErrorLoadingDataFullScreenView = rootView.findViewById(R.id.error_loading_data);
        mEmptyStateFullScreenView = rootView.findViewById(R.id.empty_state);
        View content = onCreateEventView(inflater, null, savedInstanceState);
        mMainContainer.addView(content);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            mBaseActivity = (BaseActivity) context;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mConnectivityManager = (ConnectivityManager) mBaseActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean isAirplaneModeOn(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.System.getInt(context.getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON, 0) != 0;
        } else {
            return Settings.Global.getInt(context.getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
        }
    }

    public static boolean isConnectionOff(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = mConnectivityManager.getActiveNetworkInfo();
        return info == null || !info.isConnectedOrConnecting();
    }

    public void hideEmptyStateLayout() {
        mEmptyStateFullScreenView.setVisibility(View.GONE);
    }

    public void showNoObjectiveFullScreen(final OnNoObjectiveCallback onNoObjectiveCallback) {
        mEmptyStateFullScreenView.setVisibility(View.VISIBLE);
        Button retryBtn = mEmptyStateFullScreenView.findViewById(R.id.btn_privacy_state);
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNoObjectiveCallback.onNoObjectiveCallback();
            }
        });
    }

    public void showErrorConectionFullScreen(final OnErrorConectionCallback onErrorConectionCallback) {
        mErrorConectionFullScreenView.setVisibility(View.VISIBLE);
        RelativeLayout retryBtn = (RelativeLayout) mErrorConectionFullScreenView.findViewById(R.id.rl_continue_btn_container);
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorConectionCallback.onErrorConectionRetry();
            }
        });
    }

    public void hideErrorConectionFullScreen() {
        mErrorConectionFullScreenView.setVisibility(View.GONE);
    }

    public void showErrorLoadingDataFullScreen(final OnErrorLoadingDataCallback onErrorLoadingDataCallback) {
        mErrorLoadingDataFullScreenView.setVisibility(View.VISIBLE);
        RelativeLayout retryBtn = (RelativeLayout) mErrorLoadingDataFullScreenView.findViewById(R.id.rl_retry_btn_container);
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorLoadingDataCallback.onErrorLadingDataRetry();
            }
        });
    }

    public void hideErrorLoadingDataFullScreen() {
        mErrorConectionFullScreenView.setVisibility(View.GONE);
    }


    public void setBasePresenter(BasePresenter basePresenter) {
        mBasePresenter = basePresenter;
    }

    public void showLoadingOverlay() {
        if (!isLoadingOverlayShowing()) {
            mLoadingOverlay.bringToFront();
            mLoadingOverlay.setVisibility(View.VISIBLE);
        }
    }

    public void hideLoadingOverlay() {
        if (isLoadingOverlayShowing()) {
            mLoadingOverlay.setVisibility(View.GONE);
        }
    }

    private boolean isLoadingOverlayShowing() {
        return mLoadingOverlay.getVisibility() == View.VISIBLE;
    }

    protected abstract View onCreateEventView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public interface OnErrorConectionCallback {
        void onErrorConectionRetry();
    }

    public interface OnNoObjectiveCallback {
        void onNoObjectiveCallback();
    }

    public interface OnErrorLoadingDataCallback {
        void onErrorLadingDataRetry();
    }

}
