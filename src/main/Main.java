package main;

import gui.GUI;
import util.Sort;
import util.Time;

import java.awt.EventQueue;
import java.util.HashMap;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.UINT_PTR;
import com.sun.jna.win32.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class Main {
	
   public static Boolean proceed = false;
   
   public static void main(String[] args) throws Exception {
      //supply your own path instead of using this one
	   
	   EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmLiveRedditWallpapers.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}); 
	   
   }
   
   
   public interface SPI extends StdCallLibrary {

      //from MSDN article
      long SPI_SETDESKWALLPAPER = 20;
      long SPIF_UPDATEINIFILE = 0x01;
      long SPIF_SENDWININICHANGE = 0x02;

      SPI INSTANCE = (SPI) Native.loadLibrary("user32", SPI.class, new HashMap<Object, Object>() {
         {
            put(OPTION_TYPE_MAPPER, W32APITypeMapper.UNICODE);
            put(OPTION_FUNCTION_MAPPER, W32APIFunctionMapper.UNICODE);
         }
      });

      boolean SystemParametersInfo(
          UINT_PTR uiAction,
          UINT_PTR uiParam,
          String pvParam,
          UINT_PTR fWinIni
        );
  }
}