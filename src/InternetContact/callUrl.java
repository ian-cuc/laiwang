package InternetContact;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class callUrl {
	
	 /**
     * @param path    请求的服务器URL地址
     * @param encode    编码格式
     * @param latitude   纬度
     * @param longitude  经度
     * @return    将服务器端返回的数据转换成String
     */
    public static String sendPostMessage(String path, String encode)
    {
        String result = "";
        //带参数的请求地址
        StringBuilder sb = new StringBuilder();

        HttpClient httpClient = new DefaultHttpClient();
        try
        {
            HttpGet httpGet = new HttpGet(path);
            ResponseHandler responseHandler = new BasicResponseHandler();
            String responseBody = httpClient.execute(httpGet, responseHandler);
            result=responseBody;
//            HttpResponse httpResponse = httpClient.execute(httpGet);
//            if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
//            {
//                HttpEntity httpEntity = httpResponse.getEntity();
//                if(httpEntity != null)
//                {
//                    result = EntityUtils.toString(httpEntity, encode);
//                }
//            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            httpClient.getConnectionManager().shutdown();
        }
        
        return result;
    }

}
