package InternetContact;

public class configure {
	private String indexUrl;
	private String indexUrlString;
	private String indexUrlJson;
	
	public int setindexUrl(String url)
	{
		indexUrl=new String(url);
		return 0;
	     
	}
	public String getindexUrl()
	{
		if(indexUrl.length()<=0)
		{
			return "";
		}
		return indexUrl;
	}
	
	public int setindexUrlString(String url)
	{
		indexUrl=new String(url);
		return 0;
	     
	}
	public String getindexUrlString()
	{
		if(indexUrlString.length()<=0)
		{
			return "";
		}
		return indexUrlString;
	}
	
	public int setindexUrlJson(String url)
	{
		indexUrlJson=new String(url);
		return 0;
	     
	}
	public String getindexUrlJson()
	{
		if(indexUrlJson.length()<=0)
		{
			return "";
		}
		return indexUrlJson;
	}
}
