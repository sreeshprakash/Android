/*package com.example.cloudpress;

import com.owncloud.android.lib.common.OwnCloudClient;
import com.owncloud.android.lib.common.OwnCloudClientFactory;
import com.owncloud.android.lib.common.network.OnDatatransferProgressListener;
import com.owncloud.android.lib.common.operations.OnRemoteOperationListener;
import com.owncloud.android.lib.common.operations.RemoteOperation;
import com.owncloud.android.lib.common.operations.RemoteOperationResult;
import com.owncloud.android.lib.resources.files.CreateRemoteFolderOperation;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

public class OwnCloud extends Activity
implements  OnRemoteOperationListener,
            OnDatatransferProgressListener {
	
	private OwnCloudClient mClient;
	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		Uri serverUri = Uri.parse(getString(R.string.server_base_url));
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_own_cloud);
		
		mClient = OwnCloudClientFactory.createOwnCloudClient(
	            serverUri,
	            this,
	            // Activity or Service context
	            true);
	}
	
	
	private void startFolderCreation(String newFolderPath) {
		  CreateRemoteFolderOperation createOperation = new CreateRemoteFolderOperation(newFolderPath, false);
		  createOperation.execute( mClient , this , mHandler);
		}
	@Override
	public void onRemoteOperationFinish(RemoteOperation operation, RemoteOperationResult result) {
	  if (operation instanceof CreateRemoteFolderOperation) {
	    if (result.isSuccess()) {
	    // do your stuff here
	    }
	  }
	 
	}
	
//need to check download
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.own_cloud, menu);
		return true; 
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTransferProgress(long progressRate,
			long totalTransferredSoFar, long totalToTransfer,
			String fileAbsoluteName) {
		// TODO Auto-generated method stub
		
	}
}*/
