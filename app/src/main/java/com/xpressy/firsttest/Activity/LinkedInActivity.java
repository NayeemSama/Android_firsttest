package com.xpressy.firsttest.Activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.ssw.linkedinmanager.dto.LinkedInAccessToken;
import com.ssw.linkedinmanager.dto.LinkedInEmailAddress;
import com.ssw.linkedinmanager.dto.LinkedInUserProfile;
import com.ssw.linkedinmanager.events.LinkedInManagerResponse;
import com.ssw.linkedinmanager.events.LinkedInUserLoginDetailsResponse;
import com.ssw.linkedinmanager.events.LinkedInUserLoginValidationResponse;
import com.ssw.linkedinmanager.ui.LinkedInRequestManager;
import com.xpressy.firsttest.R;

public class LinkedInActivity extends BaseActivity{

    String CLIENT_ID = "78yny8mcsdsuxt";
    String CLIENT_SECRET = "MUGxn2RnbmjIN9Wj";
    String RIDIRECT_URL = "https://www.linkedin.com/developers/tools/oauth/redirect?code=AQUD8_3ItwPCSEMds0zlC13Dk4aTa2M3lovZpKQ24l23lbfVkFScZLJDJ4zs7iK8NFktVfrfBtn7J05k2OENgwe8S4X2kIYSnJ5iSSS81Rkjz1NjCk7AfEBlkVxxJB-B2OOout_bECtJhqFiVrmQSohkLIe8tWmjVErLWVPQwy59XM6PNyUJLGCPs0_X00ZCWEAm_XDjBmtszflpgFMlYiuKSaWgw7LzdWF2p7xjav8TRPrmGeMVMlzaP9q4dyjMVXEC7AzOL5NhteZT9wmdUrYoDcQi02hINAssxIaYhNi7JDRB7X0XdZn_SPpc9A_-oUQeY-hdfBoaa1t0bVhizQk-kaK5vA&state=abcd";
    String ACCESS_TOKEN = "AQUD8_3ItwPCSEMds0zlC13Dk4aTa2M3lovZpKQ24l23lbfVkFScZLJDJ4zs7iK8NFktVfrfBtn7J05k2OENgwe8S4X2kIYSnJ5iSSS81Rkjz1NjCk7AfEBlkVxxJB-B2OOout_bECtJhqFiVrmQSohkLIe8tWmjVErLWVPQwy59XM6PNyUJLGCPs0_X00ZCWEAm_XDjBmtszflpgFMlYiuKSaWgw7LzdWF2p7xjav8TRPrmGeMVMlzaP9q4dyjMVXEC7AzOL5NhteZT9wmdUrYoDcQi02hINAssxIaYhNi7JDRB7X0XdZn_SPpc9A_-oUQeY-hdfBoaa1t0bVhizQk-kaK5vA";
    String url = "https://www.linkedin.com/oauth/v2/authorization?response_type=code&client_id=78yny8mcsdsuxt&redirect_uri=https://www.linkedin.com/developers/tools/oauth/redirect&state=abcd&scope=r_emailaddress";
    //AQXgi7NhZe1oh0l-BNzqWtMY_QgGqTnwJmjlzGQ6M3lG_Hoy9AgttUZeE1dr4UeFW1AshvDCA2XqKQ3z_kNnxCYv265WuaLFu4ISoZ_7_6-GKcYVhV7RjCjmp1eWG9oxbli-D5w7XcIkVfaWfDExJQVOmIwtgBvHXmLGy5xuoZsxEUy4IBIDlMz7v0whj5GpqGTRDbLH2Zv2mWA9eno0wNOm8q3w0a2_kfioKp12lg9E-LHcYO_8sdQrR6KPu8VEjx91U_4f0_8qxUtuXdQqXQVIVpeFuMNRtSL_6OilRSTwyztZxfeiOMPNqbdKb9VXsMvLW48H42Bi15Z3o8Q5LSYsiyA33w

    LinkedInRequestManager linkedInRequestManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_linkedin);


        linkedInRequestManager = new LinkedInRequestManager(this, new LinkedInManagerResponse() {
            @Override
            public void onGetAccessTokenFailed() {
                Log.d("TOKEN", "FAIL");
            }

            @Override
            public void onGetAccessTokenSuccess(LinkedInAccessToken linkedInAccessToken) {
                Log.d("TOKEN", linkedInAccessToken.getAccess_token());
            }

            @Override
            public void onGetCodeFailed() {
                Log.d("CODE", "FAIL");
            }

            @Override
            public void onGetCodeSuccess(String code) {
                Log.d("CODE", code);
            }

            @Override
            public void onGetProfileDataFailed() {
                Log.d("PROFILE", "FAIL");
            }

            @Override
            public void onGetProfileDataSuccess(LinkedInUserProfile linkedInUserProfile) {
                Log.d("PROFILE", linkedInUserProfile.getUserName().toString());
            }

            @Override
            public void onGetEmailAddressFailed() {
                Log.d("EMAIL", "FAIL");
            }

            @Override
            public void onGetEmailAddressSuccess(LinkedInEmailAddress linkedInEmailAddress) {
                Log.d("EMAIL", linkedInEmailAddress.getEmailAddress());
            }
        } , CLIENT_ID, CLIENT_SECRET, RIDIRECT_URL, true);
        linkedInRequestManager.showAuthenticateView(1);

        linkedInRequestManager.isLoggedIn(new LinkedInUserLoginValidationResponse() {
            @Override
            public void activeLogin() {
                Log.d("ACTIVE", "ACTIVE");
                //Session token is active. can use to get data from linkedin
            }

            @Override
            public void tokenExpired() {
                Log.d("TOKEN", "EXPIRE");
                //token has been expired. need to obtain a new code
            }

            @Override
            public void notLogged() {
                Log.d("INACTIVE", "FAIL");

                //user is not logged into the application
            }
        });

        linkedInRequestManager.getLoggedRequestedMode(new LinkedInUserLoginDetailsResponse() {
            @Override
            public void loggedMode(int mode) {
                //user is already logged in. active token. mode is available
                switch (mode) {
                    case LinkedInRequestManager.MODE_LITE_PROFILE_ONLY:
                        Log.d("MODE", "PROFILE");
                        break;

                    case LinkedInRequestManager.MODE_EMAIL_ADDRESS_ONLY:
                        Log.d("MODE", "EMAIL");
                        break;

                    case LinkedInRequestManager.MODE_BOTH_OPTIONS:
                        Log.d("MODE", "BOTH");
                        break;
                }
            }

            @Override
            public void tokenExpired() {
                Log.d("TOKEN", "EXPIRE");
                //token has been expired. need to obtain a new code
            }

            @Override
            public void notLogged() {
                Log.d("INACTIVE", "FAIL");

                //user is not logged into the application
            }
        });

//        linkedInRequestManager.dismissAuthenticateView();
//        linkedInRequestManager.logout();

    }

    private void isLoggedin() {
        linkedInRequestManager.isLoggedIn(new LinkedInUserLoginValidationResponse() {
            @Override
            public void activeLogin() {
                Log.d("ACTIVE", "ACTIVE");
                //Session token is active. can use to get data from linkedin
            }

            @Override
            public void tokenExpired() {
                Log.d("TOKEN", "EXPIRE");
                //token has been expired. need to obtain a new code
            }

            @Override
            public void notLogged() {
                Log.d("INACTIVE", "FAIL");

                //user is not logged into the application
            }
        });
    }

}
