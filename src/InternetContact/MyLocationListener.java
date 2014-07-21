package InternetContact;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.BDNotifyListener;//假如用到位置提醒功能，需要import该类

public class MyLocationListener implements BDLocationListener {
    
    BDLocation mylocation;
    @Override
   public void onReceiveLocation(BDLocation location) {
      if (location == null)
          return ;
      mylocation=location;
      StringBuffer sb = new StringBuffer(256);
      sb.append("time : ");
      sb.append(location.getTime());
      sb.append("\nerror code : ");
      sb.append(location.getLocType());
      sb.append("\nlatitude : ");
      sb.append(location.getLatitude());
      sb.append("\nlontitude : ");
      sb.append(location.getLongitude());
      sb.append("\nradius : ");
      sb.append(location.getRadius());
      if (location.getLocType() == BDLocation.TypeGpsLocation){
           sb.append("\nspeed : ");
           sb.append(location.getSpeed());
           sb.append("\nsatellite : ");
           sb.append(location.getSatelliteNumber());
           } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
           sb.append("\naddr : ");
           sb.append(location.getAddrStr());
        } 
 
      
    }
    public void onReceivePoi(BDLocation poiLocation) {
    	
    	         if (poiLocation == null){
    	                return ;
    	          }
mylocation=poiLocation;
    	         StringBuffer sb = new StringBuffer(256);
    	          sb.append("Poi time : ");
    	          sb.append(poiLocation.getTime());
    	          sb.append("\nerror code : ");
    	          sb.append(poiLocation.getLocType());
    	          sb.append("\nlatitude : ");
    	          sb.append(poiLocation.getLatitude());
    	          sb.append("\nlontitude : ");
    	          sb.append(poiLocation.getLongitude());
    	          sb.append("\nradius : ");
    	          sb.append(poiLocation.getRadius());
    	          if (poiLocation.getLocType() == BDLocation.TypeNetWorkLocation){
    	              sb.append("\naddr : ");
    	              sb.append(poiLocation.getAddrStr());
    	         } 
    	          if(poiLocation.hasPoi()){
    	               sb.append("\nPoi:");
    	               sb.append(poiLocation.getPoi());
    	         }else{             
    	               sb.append("noPoi information");
    	          }
    	      
    	        }

}
