package com.blackmill.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import android.content.Context;
import android.database.Cursor;
import android.os.Environment;

public class DB {
	
	public final static String TAG = DB.class.getCanonicalName();
	
	
	/**
	 * Takes a cursor and shows its content as log outut
	 * @param cursor
	 */
	public static void inspectCursor(Cursor cursor) {
		
		Log.d("CursorInspect", " --- inspecting a cursor now --- ");

		if(cursor != null && cursor.moveToFirst()) {

			int rowCount 	= cursor.getCount();
			int columCount	= cursor.getColumnCount();

		

			for(int i=0; i<rowCount; i++){ 

				cursor.moveToPosition(i);
				StringBuilder builder = new StringBuilder();

				for(int j=0; j<columCount; j++) {
					builder.append(cursor.getString(j));
					builder.append("  ");
				}

				Log.d("CursorInspect", builder.toString());

			}

		}
		else {
			Log.d("CursorInspect", " --- no rows to show ---");
		}
	}
	
	
	
	public static boolean copyToSd(String currentDBPath, Context c) {
		
		if(!Build.IN_PRODUCTION) {
			
			
			Log.d(TAG, "COPYTOSD FROM: " + currentDBPath);			
			
			try {
		        File sd = Environment.getExternalStorageDirectory();

		        if (sd.canWrite()) {
		            File currentDB = new File(currentDBPath);
		            File backupDB = new File(sd, currentDB.getName());

		            if (currentDB.exists()) {
		                FileChannel src = new FileInputStream(currentDB).getChannel();
		                FileChannel dst = new FileOutputStream(backupDB).getChannel();
		                dst.transferFrom(src, 0, src.size());
		                src.close();
		                dst.close();
		                return true;
		            }
		        }
		    } catch (Exception e) {		    }
			

			
			
		}	
			
		return false;			
			
	}
}
			
			
			
			
			
			
//			try {
//				
//				Log.d(TAG, "COPYTOSD FROM: " + filename);
//				
//				File sdpath	= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
//				
//				Log.d(TAG, "COPYTOSD TO: " + sdpath);
//				
//				
//				
//				File fin 	= new File(filename);
//			
//				File fout 	= new File(sdpath.toString() + "/" + fin.getName());
//				
//				
//				
//				FileInputStream in 		= new FileInputStream(fin);
//				FileOutputStream out 	= new FileOutputStream(fout);
//				
//				byte[] buffer = new byte[512];
//	
//				for(int size=1; size>0; size=in.read(buffer)) {		out.write(buffer);		}
//				
//				in.close();
//				out.flush();
//				out.close();
//				
//				return true;
//			}
//			catch (Exception e) {		e.printStackTrace();			}
//		}
//		
//		return false;
//	}


