package cl.moriahdp.church.login.view;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.squareup.otto.Bus;

import java.util.Objects;
import java.util.regex.Matcher;

import cl.moriahdp.church.BuildConfig;
import cl.moriahdp.church.R;
import cl.moriahdp.church.SecretApplication;
import cl.moriahdp.church.baseclasses.BaseActivity;
import cl.moriahdp.church.baseclasses.BaseActivityView;
import cl.moriahdp.church.login.activities.LoginActivity;
import cl.moriahdp.church.login.modelObject.LoginModelObject;
import cl.moriahdp.church.main.activities.DashboardActivity;
import cl.moriahdp.church.registry.activities.RegistryActivity;
import cl.moriahdp.church.utils.Constants;
import cl.moriahdp.church.utils.Logger;

import static cl.moriahdp.church.utils.Constants.PASSWORD_MIN;
import static cl.moriahdp.church.utils.Constants.VALID_EMAIL_ADDRESS_REGEX;

public class LoginView extends BaseActivityView {

    // UI references.
    private TextInputEditText mEmailEditText;
    private TextInputLayout mEmailInputLayout;
    private TextInputEditText mPasswordEditText;
    private TextInputLayout mPasswordInputLayout;
    private Button mLoginAppButton;
    private TextView mLoginErrorMessage;
    private TextView mLoginResetPassword;
    private TextView mLoginTermsAndConditions;
    private TextView mLoginRegistryTextView;
    private View mLoginFormView;
    private boolean emailValid = false;
    private boolean passwordValid = false;
    private boolean languageValid = true;
    private ImageView mLogo;
    private int mTapCount = 0;
    private BaseActivity activity;

    public LoginView(LoginActivity activity, Bus bus) {
        super(activity, bus);
        this.activity = activity;
        mEmailEditText = activity.findViewById(R.id.tie_login_email);
        mEmailInputLayout = activity.findViewById(R.id.til_login_email);
        mPasswordEditText = activity.findViewById(R.id.tie_login_password);
        mPasswordInputLayout = activity.findViewById(R.id.til_login_password);
        mLoginAppButton = activity.findViewById(R.id.btn_login_app);
        mLoginErrorMessage = activity.findViewById(R.id.tv_login_error_message);
        mLoginResetPassword = activity.findViewById(R.id.tv_login_forget_message);
        mLoginTermsAndConditions = activity.findViewById(R.id.tv_login_terms_and_conditions);
        mLoginRegistryTextView = activity.findViewById(R.id.tv_login_registry);
        mLoginFormView = activity.findViewById(R.id.login_form);
        mLogo = activity.findViewById(R.id.iv_login_logo);
    }

    public void onResumed() {
        hideKeyboard();
        setOnClickEvents();
        setOnFocusChangeListener();
        addTextChangedListener();
    }

    private void setOnFocusChangeListener() {
        mEmailEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mEmailInputLayout.setHintTextAppearance(R.style.login_hint_appearance);
                    mLoginErrorMessage.setVisibility(View.INVISIBLE);
                } else {
                    validateEmailWhenHasNotFocused();
                }
            }
        });

        mPasswordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mPasswordInputLayout.setHintTextAppearance(R.style.login_hint_appearance);
                    mLoginErrorMessage.setVisibility(View.INVISIBLE);
                } else {
                    validatePasswordWhenHasNotFocused();
                }
            }
        });
    }

    private void addTextChangedListener() {
        mEmailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mEmailInputLayout.setHintTextAppearance(R.style.login_hint_appearance);
                mLoginErrorMessage.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPasswordInputLayout.setHintTextAppearance(R.style.login_hint_appearance);
                mLoginErrorMessage.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setOnClickEvents() {
        mPasswordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });


        mLoginAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();

            }
        });

        mLoginRegistryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RegistryActivity.class);
                getContext().startActivity(intent);
            }
        });

        mLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!BuildConfig.BUILD_TYPE.equals("release")) {
                    Logger.info("----- Init EasterEgg of the App -----");

                    mTapCount++;

                    final String versionName = getVersionNameApp();
                    if (mTapCount > 3) {
                        final String token = SecretApplication.getInstance().getTinyDB().getString(Constants.FIREBASE_TOKEN);
                        AlertDialog alertDialog = new AlertDialog.Builder(activityRef.get()).create();
                        alertDialog.setTitle(BuildConfig.FLAVOR + " El Secreto \n " + versionName + " " + BuildConfig.BUILD_TYPE);
                        alertDialog.setMessage(token);
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "COPIAR",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(getActivity().CLIPBOARD_SERVICE);
                                        ClipData clip = ClipData.newPlainText("label", token);
                                        clipboard.setPrimaryClip(clip);
                                    }
                                });
                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CERRAR",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                        mTapCount = 0;
                    }
                }
            }
        });

        mLoginResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateEmailWhenLoginButtonIsTapped();
                if (emailValid) {
                    showAlertDialogToResetPassword();
                } else {
                    Toast.makeText(
                            getContext(),
                            R.string.profile_reset_password_mandatory_message,
                            Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        mLoginTermsAndConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTermsAndConditions();
            }
        });
    }

    public void showTermsAndConditions(){
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(Objects.requireNonNull(getContext()), Uri.parse(getActivity().getString(R.string.profile_term_and_conditions_url)));
    }

    private void showAlertDialogToResetPassword() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle(getContext().getString(R.string.profile_reset_password))
                .setMessage(getContext().getString(R.string.profile_reset_password_message))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (emailValid) {
                            mBus.post(new ResetPasswordEvent(mEmailEditText.getText().toString()));
                        }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    public void resetSuccess() {
        Toast.makeText(getContext(), R.string.profile_reset_password_success_message, Toast.LENGTH_LONG).show();
    }

    public void resetFailure() {
        Toast.makeText(getContext(), R.string.profile_reset_password_failure_message, Toast.LENGTH_LONG).show();
    }

    private String getVersionNameApp() {
        String rval = "";
        try {
            PackageInfo packageInfo = activityRef.get().getPackageManager().getPackageInfo(activityRef.get()
                    .getPackageName(), PackageManager
                    .GET_CONFIGURATIONS);
            String appVersion = packageInfo.versionName;
            int appVersionCode = packageInfo.versionCode;
            rval = activityRef.get().getResources().getString(R.string.lbl_version, appVersion, appVersionCode);
        } catch (PackageManager.NameNotFoundException ignore) {
            // unable to obtain version number
        }
        return rval;
    }

    private void validateEmailWhenHasNotFocused() {
        String email = mEmailEditText.getText().toString();
        if (!TextUtils.isEmpty(email)) {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
            if (!matcher.find()) {
                mLoginErrorMessage.setVisibility(View.VISIBLE);
                mLoginErrorMessage.setText(R.string.error_invalid_email);
                emailValid = false;
            } else {
                emailValid = true;
            }
        } else {
            emailValid = false;
        }
    }

    private void validateEmailWhenLoginButtonIsTapped() {
        String email = mEmailEditText.getText().toString();
        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mLoginErrorMessage.setVisibility(View.VISIBLE);
            mLoginErrorMessage.setText(R.string.login_email_and_password_required);
            emailValid = false;
        } else {
            validateEmailWhenHasNotFocused();
        }
    }

    private void validatePasswordWhenHasNotFocused() {
        String password = mPasswordEditText.getText().toString();
        if (!TextUtils.isEmpty(password)) {
            if (password.length() < PASSWORD_MIN) {
                mLoginErrorMessage.setVisibility(View.VISIBLE);
                mLoginErrorMessage.setText(R.string.error_invalid_password);
                passwordValid = false;
            } else {
                passwordValid = true;
            }
        } else {
            passwordValid = false;
        }
    }

    private void validatePasswordWhenLoginButtonIsTapped() {
        String password = mPasswordEditText.getText().toString();
        // Check for a valid email address.
        if (TextUtils.isEmpty(password)) {
            mLoginErrorMessage.setVisibility(View.VISIBLE);
            mLoginErrorMessage.setText(R.string.login_email_and_password_required);
            passwordValid = false;
        } else {
            validatePasswordWhenHasNotFocused();
        }
    }

    private void attemptLogin() {
//        validateEmailWhenLoginButtonIsTapped();
//        validatePasswordWhenLoginButtonIsTapped();
//        if (emailValid && passwordValid) {
            hideKeyboard();
            mBus.post(new LoginOnClickEvent(
                    new LoginModelObject(
                            mEmailEditText.getText().toString(),
                            mPasswordEditText.getText().toString(),
                            SecretApplication.getInstance().getTinyDB().getString(Constants.FIREBASE_TOKEN, "Def"))));
//        }
    }


    public void loginSuccess() {
        Intent intent = new Intent(getContext(), DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        getContext().startActivity(intent);
    }

    public void loginFailure() {
        showTopSnackBar(R.string.login_failure_user_email);
    }

    private void showTopSnackBar(int message) {
        TSnackbar snackbar = TSnackbar.make(getActivity().findViewById(R.id.login_form), message, TSnackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.perry_winkle));
        TextView textView = snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }

    /**
     * Classes for bus
     */
    public class LoginOnClickEvent {

        private LoginModelObject loginModelObject;

        public LoginOnClickEvent(LoginModelObject loginModelObject) {
            this.loginModelObject = loginModelObject;
        }

        public LoginModelObject getLoginModelObject() {
            return loginModelObject;
        }
    }

    public class ResetPasswordEvent {
        String email;

        public ResetPasswordEvent(String email) {
            this.email = email;
        }

        public String getEmail() {
            return email;
        }
    }
}