package com.blackmill.util.views;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class AdListAdapter extends BaseAdapter {


	private int factor;
	private int listSize 	= 0;
	private int dataSize 	= 0;

	public static final long AD_ID = -1000;

	protected List<?> realData;

	protected static View adView;
	protected Map<Integer, View> adViews = new HashMap<Integer, View>();
	protected Activity x;

	public AdListAdapter(Activity x, int factor, List<?> realData) {
		this.factor 	= factor;
		this.realData 	= realData;
		this.x			= x;
		
		computeSizes();			

	}
	
	
	
	@Override public void notifyDataSetChanged() {
		
		computeSizes();
		
		super.notifyDataSetChanged();
	}

	
	
	/**
	 * Assigns values to data and listSize
	 * dataSize is the size of the data underlying this adapter.
	 * listSize is the size of data + number of ads which have to be shown in listView
	 */
	private void computeSizes() {
		dataSize 	= realData.size();		
		
		listSize = dataSize;
		for(int i=0; i<listSize; i++) {
			if(i%factor == (factor-1)) {
				listSize++;
			}
		}
	}

	
	protected int correctPos(int pos) {
		int correction = (pos) / factor;		
		return pos - correction;
	}
	

	@Override public View getView(int pos, View v, ViewGroup parent) {

		if(showAd(pos)) {	
			return serveAd(pos);
		}
		else {
			return serveData(correctPos(pos));
		}
	}
	
	
	@Override public int getCount() {				return listSize;	}
	
	
	@Override public Object getItem(int pos) {		
		return realData.get(correctPos(pos));
	}

	
	/**
	 * Keep in mind that your app must handle clicks on ad items different from clicks on real items.
	 * So u probably want to return different IDs for ad items. Do so in your implementation. 
	 * U might want to use showAd to find out if pos is the position of an ad item or a real item.
	 */
	@Override public abstract long getItemId(int pos);



	/**
	 * This is called when adapter has to show ad item. 
	 * @param pos
	 * @return
	 */
	protected abstract View serveAd(int pos); 	
	
	
	/**
	 * This is called when adapter has to show real item.
	 * @param pos
	 * @return
	 */
	protected abstract View serveData(int pos);		

	
	
	/**
	 * Tells you if item at pos is real item or ad.
	 * @param pos
	 * @return
	 */
	protected boolean showAd(int pos) {
		int mod = pos % factor; 

		if(mod == (factor-1)) return true;
		return false;

	}
	
	
	public float getPx(int dip) {
		return TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 
				dip, 
				x.getResources().getDisplayMetrics()
				);
		
	}
		
}
