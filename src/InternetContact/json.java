package InternetContact;

import java.util.ArrayList;
import java.util.List;

import org.json.*;

import static com.yinyien.fragment.imagePath.IMAGES;
public class json {

	public static String[] addressName;
	public static String[] imageAdd;
	public static String[] addressIntroduction;
	public static String[] Latitude;
	public static String[] longitude;
	
	public static String[] jsonParse(String jsonString)
	{
        List<String> address = new ArrayList<String>();

		try {
			JSONObject jsonObject=new JSONObject(jsonString);//将String转化为json类型
			JSONArray jsonArray = jsonObject.getJSONArray("index");//获取jsonobject的数组
			imageAdd=new String[jsonArray.length()];
			addressName=new String[jsonArray.length()];
			addressIntroduction=new String[jsonArray.length()];
			Latitude=new String[jsonArray.length()];
			longitude=new String[jsonArray.length()];
            for(int i = 0; i < jsonArray.length(); i++)
            {
//                address.add(jsonArray.get(i).toString());//遍历数组，把每一条数据存入stringlist里面。
//                JSONObject addressaa=new JSONObject(address.get(i));
//                addressaa.toString();
//                imageAdd[i]=addressaa.getJSONObject("imageAdd").toString();
                
                imageAdd[i]= jsonArray.getJSONObject(i).getString("image_url");
                addressName[i]=jsonArray.getJSONObject(i).getString("address_name");
                addressIntroduction[i]=jsonArray.getJSONObject(i).getString("introduction");
                Latitude[i]=jsonArray.getJSONObject(i).getString("latitude");
    			longitude[i]=jsonArray.getJSONObject(i).getString("longitude");

            }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return imageAdd;
	}
} 
/*{“index”:[
{"address_id":2,"address_name":"中国传媒大学北门喷泉","image_url":"http://www.cuc.edu.cn/fengguang/images/2.jpg","introduction":"北门喷泉，有金鱼","latitude":39.91501998901367,"longitude":116.55581665039062}
......
//目前一共取20个，后期修改
]
}
*/