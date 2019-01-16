package cl.moriahdp.church.utils.data;

public class ApiUtils {

    private ApiUtils() {}

//    public static final String BASE_URL = "http://10.0.2.2:8000/";
    public static final String BASE_URL = "http://142.93.50.15:8001/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
