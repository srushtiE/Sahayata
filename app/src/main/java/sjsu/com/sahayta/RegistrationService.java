package sjsu.com.sahayta;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class RegistrationService extends FirebaseInstanceIdService {
    String registrationToken;
    @Override
    public void onTokenRefresh() {
        registrationToken = FirebaseInstanceId.getInstance().getToken();
        System.out.println("Registration token: " + registrationToken);
    }
}