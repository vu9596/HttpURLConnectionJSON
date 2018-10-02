package vunt.com.vn.httpurlconnectionjson;

public interface OnLoadingApiListener {
    void onLoadingSuccess();
    void onReadApiFail(Exception e);
}
