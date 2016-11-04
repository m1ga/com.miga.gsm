package com.miga.gsm;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.telephony.gsm.GsmCellLocation;
import android.telephony.TelephonyManager;
import android.text.TextUtils ;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollFunction;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.titanium.TiApplication;

@Kroll.module(name="Gsm", id="com.miga.gsm")
public class GsmModule extends KrollModule{

	Context context;
	Activity activity;

	public GsmModule () {
		super();
		TiApplication appContext = TiApplication.getInstance();
		activity = appContext.getCurrentActivity();
		context=activity.getApplicationContext();
	}


	@Kroll.method
	public void getData(HashMap args){
	  KrollDict arg = new KrollDict(args);

	  TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
	  GsmCellLocation cellLocation = (GsmCellLocation)tm.getCellLocation();
	  String networkOperator = tm.getNetworkOperator();

	  String operatorname = tm.getNetworkOperatorName();
	  String simcountrycode = tm.getSimCountryIso();
	  String simoperator = tm.getSimOperatorName();
	  String phoneNumber = tm.getLine1Number();

	  KrollFunction success =(KrollFunction) arg.get("success");
	  int mcc = 0;
	  int mnc = 0;
	   if (networkOperator != null && !TextUtils.isEmpty(networkOperator)) {
	      mcc = Integer.parseInt(networkOperator.substring(0, 3));
	      mnc = Integer.parseInt(networkOperator.substring(3));
	  }
	  int cid = (cellLocation.getCid());
	  int lac = (cellLocation.getLac());

	  HashMap<String, Object> event = new HashMap<String, Object>();
	  event.put("cid",cid);
	  event.put("lac",lac);
	  event.put("mcc",mcc);
	  event.put("mnc",mnc);
	  event.put("operatorname",operatorname);
	  event.put("simcountrycode",simcountrycode);
	  event.put("phoneNumber",phoneNumber);

	  // Success-Callback
	  success.call(getKrollObject(), event);

	}
}
