package com.example.itunes.RestApi;

public class BaseManager {

    protected RestApi getRestApiClient()
    {
        RestApiClient restApiClient=new RestApiClient(BaseUrl.bilgiurl);
        return  restApiClient.getmRestAApi();
    }
}
