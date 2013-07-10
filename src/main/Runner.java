package main;

import java.io.File;

import main.Main.SPI;

import org.json.JSONArray;
import org.json.JSONObject;

import util.Sort;
import util.Time;

import com.sun.jna.platform.win32.WinDef.UINT_PTR;

public class Runner implements Runnable{

	private String sub;
	private int n;
	private Sort s;
	private Time t;
	private String folder;
	private int interval;
	public Runner(String sub, int n, Sort s, Time t, String folder, int interval){
		this.sub = sub;
		this.n = n;
		this.s = s;
		this.t = t;
		this.folder = folder;
		this.interval = interval;
	}
	@Override	
	public void run(){
		   String after = "";
		   int j = 1;
		   while (j < n+1){
			   if (Main.proceed){
				   String afterString = "";
				   if (j!=1){
					   afterString = "&after="+after;
				   }
				   //can ignore trying to find a "after" value
				   try{
					   String subfull = "http://www.reddit.com/r/"+sub+"/"+s.toString().toLowerCase()+".json?t="+t.toString().toLowerCase()+"&limit=1"+afterString;
					   System.out.println(subfull);
					   JSONObject json = JSONReader.readJsonFromUrl("http://www.reddit.com/r/"+sub+"/"+s.toString().toLowerCase()+".json?t="+t.toString().toLowerCase()+"&limit=1"+afterString);
					   
					   JSONObject data = json.getJSONObject("data");
					   
					   after = data.getString("after");
					   System.out.println(after);
					   JSONArray children = data.getJSONArray("children");
					   if (!children.isNull(0)){
						   JSONObject value = children.getJSONObject(0).getJSONObject("data");
						   //after = (String) children.getJSONObject(0).getString("kind")+"_"+value.getString("id");
						   System.out.println("domain: "+value.getString("domain")+" url: "+value.getString("url"));
						   if ((value.getString("domain").equals("imgur.com")&& !value.getString("url").contains("/a/"))||value.getString("url").contains(".jpg")||value.getString("url").contains(".png")){
							   String image = value.getString("url");
							   if (value.getString("domain").equals("imgur.com")&& !image.contains(".jpg")){
								   if (image.contains(",")){
									   String[] parts = image.split(",");
									   image = parts[0];
								   }
								   if (image.contains("/a/")){
									   image = image + "#0";
								   }
								   image = image+".jpg";
							   }
							   ImageDownloader.getImage(image, folder, j);
						       String path = folder+"\\"+j+".jpg";
						       File theDir = new File(folder);  
						       if (!theDir.exists()){    
						       boolean result = theDir.mkdir();} 
						       System.out.println(path);
							    	  
						       SPI.INSTANCE.SystemParametersInfo(
						    		   new UINT_PTR(SPI.SPI_SETDESKWALLPAPER), 
						    	       new UINT_PTR(0), 
						    	       path, 
						    	       new UINT_PTR(SPI.SPIF_UPDATEINIFILE | SPI.SPIF_SENDWININICHANGE));
						       waiting(interval);
						       
						   }else{
							   System.out.println("Skipping not a jpg");
						   }
						   if (j == n||after.equals("null")){ j = 1; } else { j++; }
					   } else { j = 1; }
				       //System.out.println(after);
				   }catch (Exception e){
					   e.printStackTrace();
				   }
				   
			   }else{
				   j = n+1;
			   }
			   
			   
		   }
	   }
	
	   public static void waiting (int n){
	       
	       long t0, t1;

	       t0 =  System.currentTimeMillis();

	       do{
	           t1 = System.currentTimeMillis();
	       }
	       while ((t1 - t0) < (n * 1000));
	   }

}
