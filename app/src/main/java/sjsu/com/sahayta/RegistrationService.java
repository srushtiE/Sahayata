package sjsu.com.sahayta;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class RegistrationService extends FirebaseInstanceIdService {
    private volatile String registrationToken;
    private static final Object lock = new Object();
    @Override
    public void onTokenRefresh() {
        System.out.println("GRRRRRRGRRRRRRGRRRRRRRRGRRRRRRRGRRRRRRRGRRRRRGGGGRRRRRRR");
        registrationToken = FirebaseInstanceId.getInstance().getToken();
        System.out.println("Registration token: " + registrationToken);
//        HTTPRequests getReq = new HTTPRequests();
//        getReq.getRequest("http://ec2-52-53-234-187.us-west-1.compute.amazonaws.com:5000/");
        /*HTTPRequests postReq = new HTTPRequests();
        postReq.postRequest("/registerToken", registrationToken);*/
    }

    public String getRegistrationToken() {
        synchronized(lock) {
            return registrationToken;
        }
    }
}