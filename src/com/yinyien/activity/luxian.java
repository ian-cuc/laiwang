package com.yinyien.activity;

import android.app.Activity;  
import android.content.res.Configuration;  
import android.os.Bundle;  
import android.util.Log;
import android.view.Menu;  
import android.widget.FrameLayout;  
import android.widget.Toast;  

import com.baidu.mapapi.BMapManager;  
import com.baidu.mapapi.map.MKMapViewListener;  
import com.baidu.mapapi.map.MapController;  
import com.baidu.mapapi.map.MapPoi;  
import com.baidu.mapapi.map.MapView;  
import com.baidu.mapapi.map.RouteOverlay;
import com.baidu.mapapi.search.MKAddrInfo;
import com.baidu.mapapi.search.MKBusLineResult;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKPlanNode;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.mapapi.search.MKShareUrlResult;
import com.baidu.mapapi.search.MKSuggestionResult;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.baidu.mapapi.search.MKWalkingRouteResult;
import com.baidu.platform.comapi.basestruct.GeoPoint;  
import com.example.vdfbvfdvb.R;
import com.yinyien.fragment.FragmentOne;
/**
 * @author yinyien 
 */
public class luxian extends Activity{  
	BMapManager mBMapMan = null;  
	MapView mMapView = null; 
	MKSearch mMKSearch = null ; 

	 String Latitude;
     String longitude;
    double Latitude_start ;
    double longitude_start;
    double Latitude_end;
    double longitude_end; 
    MKPlanNode start=null;
    MKPlanNode end=null;
    MapController mMapController=null;
    Bundle bundle=null;
    int i=0;
    static int p=0;
        @Override  
        public void onCreate(Bundle savedInstanceState){  
        	super.onCreate(savedInstanceState);  
           	mBMapMan=new BMapManager(getApplication());  
        	mBMapMan.init(null);   
        	setContentView(R.layout.luxian);  

             Latitude_start =   FragmentOne.mylocation.getLatitude();
             longitude_start =  FragmentOne.mylocation.getLongitude();
             mMapView = (MapView) this.findViewById(R.id.bmapsView);  
             mMapView.setBuiltInZoomControls(true);  
      
             mMapController = mMapView.getController();  
             GeoPoint geoPoint = new GeoPoint((int) (Latitude_start * 1E6),  
                     (int) (longitude_start * 1E6));  
             mMapController.setCenter(geoPoint);  
             mMapController.setZoom(12);  
//             
//             bundle = getIntent().getExtras();
//             Latitude=bundle.getString("Latitude");
//             longitude=bundle.getString("longitude");
//             Log.v("onCreate",Latitude);
//
//             Latitude_end = Double.parseDouble(Latitude);  
//             longitude_end = Double.parseDouble(longitude);  

             //起点的经纬度；
        	   start = new MKPlanNode();  
              start.pt = new GeoPoint((int) (Latitude_start * 1E6),  
                      (int) (longitude_start * 1E6));  
              Log.v("onCreate","aaaa");
              Log.v("onCreate","aaa"+p);
              //终点的经纬度
//               end = new MKPlanNode();  
//              end.pt = new GeoPoint((int) (Latitude_end * 1E6), (int) (longitude_end * 1E6)); 
              
        	mMKSearch = new MKSearch();  
        	mMKSearch.init(mBMapMan, new MKSearchListener(){  
        		 
                @Override 
                public void onGetAddrResult(MKAddrInfo arg0, int arg1) {  
                    // TODO Auto-generated method stub  
                	Log.v("onGetAddrResult","aaaa");

     
                }  
     
                @Override 
                public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {  
                    // TODO Auto-generated method stub  
                	Log.v("onGetBusDetailResult","aaaa");

                }  
     
                @Override 
                public void onGetDrivingRouteResult(MKDrivingRouteResult result,  
                        int arg1) {  
                    if (result == null)  
                        return;  
     
                    RouteOverlay routeOverlay = new RouteOverlay(  
                            luxian.this, mMapView);  
                    routeOverlay.setData(result.getPlan(0).getRoute(0));  
                    mMapView.getOverlays().add(routeOverlay);  
                    mMapView.refresh();// 刷新地图  
                    Log.v("drivingRoute", "yeah"+i);
                    i++;
                }  
     
                @Override 
                public void onGetPoiDetailSearchResult(int arg0, int arg1) {  
                    // TODO Auto-generated method stub  
                	Log.v("onGetPoiDetailSearchResult", "yeah"+i);

                }  
     
                @Override 
                public void onGetPoiResult(MKPoiResult arg0, int arg1, int arg2) {  
                    // TODO Auto-generated method stub  
                	Log.v("onGetPoiResult", "yeah"+i);

     
                }  
     
                @Override 
                public void onGetSuggestionResult(MKSuggestionResult arg0, int arg1) {  
                    // TODO Auto-generated method stub  
                	Log.v("onGetSuggestionResult", "yeah"+i);
                }  
     
                @Override 
                public void onGetTransitRouteResult(MKTransitRouteResult result,  
                        int arg1) {  
                    RouteOverlay routeOverlay = new RouteOverlay(  
                            luxian.this, mMapView);  
                    routeOverlay.setData(result.getPlan(0).getRoute(0));  
                    mMapView.getOverlays().add(routeOverlay);  
                    mMapView.refresh();// 刷新地图  
     
                }  
     
                @Override 
                public void onGetWalkingRouteResult(MKWalkingRouteResult result,  
                        int arg1) {  
                    // TODO Auto-generated method stub  
                    RouteOverlay routeOverlay = new RouteOverlay(  
                            luxian.this, mMapView);  
                    routeOverlay.setData(result.getPlan(0).getRoute(0));  
                    mMapView.getOverlays().add(routeOverlay);  
                    mMapView.refresh();// 刷新地图  
                }

				@Override
				public void onGetShareUrlResult(MKShareUrlResult arg0,
						int arg1, int arg2) {
					// TODO Auto-generated method stub
					
				}  
            });  //注意，MKSearchListener只支持一个，以最后一次设置为准  
//            mMKSearch.walkingSearch(null, start, null, end);  

//        	mMKSearch.setDrivingPolicy(MKSearch.ECAR_TIME_FIRST);  
        	 mMKSearch.setDrivingPolicy(MKSearch.ECAR_TIME_FIRST);  
//             mMKSearch.drivingSearch(null, start, null, end);  
        }
        
        
         
        @Override 
        public void onConfigurationChanged(Configuration newConfig) {  
            super.onConfigurationChanged(newConfig);  
        }  
     
        @Override 
        protected void onSaveInstanceState(Bundle outState) {  
            super.onSaveInstanceState(outState);  
            mMapView.onSaveInstanceState(outState);  
        }  
     
        @Override 
        protected void onRestoreInstanceState(Bundle savedInstanceState) {  
            super.onRestoreInstanceState(savedInstanceState);  
            mMapView.onRestoreInstanceState(savedInstanceState);  
        }  
     
        @Override  
        protected void onDestroy(){  
                mMapView.destroy();
                mMapView=null;
                mMKSearch.destory();
                mMKSearch=null;
                if(mBMapMan!=null){  
                        mBMapMan.destroy();  
                        mBMapMan=null;  
                }  
                Log.v("onDestroy", "yeah"+i);
                super.onDestroy();  
        }  
        @Override  
        protected void onPause(){  
                mMapView.onPause();
                if(mBMapMan!=null){  
                       mBMapMan.stop();  
                }  
                Log.v("onPause", "yeah"+i);
                
                super.onPause();  
        }  
        @Override  
        protected void onResume(){              
             bundle = getIntent().getExtras();
               Latitude=bundle.getString("Latitude");
               longitude=bundle.getString("longitude");
               Log.v("onResume",Latitude);
               Latitude_end = Double.parseDouble(Latitude);  
               longitude_end = Double.parseDouble(longitude);  

                 //终点的经纬度
                end = new MKPlanNode();  
                end.pt = new GeoPoint((int) (Latitude_end * 1E6), (int) (longitude_end * 1E6)); 
                mMKSearch.drivingSearch(null, start, null, end);  
                if(mBMapMan!=null){  
                        mBMapMan.start();  
                }  

               super.onResume();  
               
               
}
        
}
