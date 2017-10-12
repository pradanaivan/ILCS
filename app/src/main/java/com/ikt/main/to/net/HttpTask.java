package com.ikt.main.to.net;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.ikt.main.to.R;
import com.ikt.main.to.controller.IHttpResponse;
import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.parser.DefaultParser;
import com.ikt.main.to.util.APPException;
import com.ikt.main.to.util.Config;

public class HttpTask implements Runnable {

	private static final int MSG_CB_STARTED = 0;
	private static final int MSG_CB_SUCCESS = 1;
	private static final int MSG_CB_FAILED = 3;
	private static final int MSG_CB_COMPLETED = 2;
	// private static final int READ_SIZE = 2048;
	private Context mContext;
	private int mPID, mType;
	private String processName;
	private String mUrl;
	private List<NameValuePair> postData = new ArrayList<NameValuePair>();
	private List<BasicHeader> headers = new ArrayList<BasicHeader>();
	private Thread mThreadThis;
	private Object mHttpInstance;
	private Handler mHandler;
	private boolean enabledProgressDialog = false;
	private boolean cancelableProgressDialog = false;
	private ProgressDialog mProgressDialog;
	// private boolean mDummyMode = false;
	// private String mDummyResonse = null;
	private IHttpResponse mCallback;
	private Class<?> mParser;

	public HttpTask(Context context, String url, int pid, int type,
			final IHttpResponse callback, Class<?> parser) {
		mContext = context;
		mUrl = url;
		mPID = pid;
		mType = type;
		mCallback = callback;
		setParser(parser);
		initTask();
	}

	private void initTask() {
		mHandler = new Handler(mContext.getMainLooper()) {
			@Override
			public void handleMessage(Message msg) {
				Log.e("handler", "WHAT : " + msg.what);
				super.handleMessage(msg);
				Object parserObj;
				switch (msg.what) {
				case MSG_CB_STARTED:
					if (isEnabledProgressDialog()) {
						if (null != mProgressDialog
								&& mProgressDialog.isShowing()) {
							mProgressDialog.dismiss();
							mProgressDialog = null;
						}
						mProgressDialog = new ProgressDialog(mContext);
						mProgressDialog.setMessage(getProcessName());
						mProgressDialog
								.setCancelable(isCancelableProgressDialog());
						mProgressDialog
								.setOnCancelListener(new ProgressDismissListener(
										HttpTask.this));
						mProgressDialog.show();
					}
					mCallback.onStarted(mContext, mPID);
					break;
				case MSG_CB_SUCCESS:
					parserObj = msg.obj;
					mCallback.onSuccess(mContext, mPID, parserObj);
					break;
				case MSG_CB_FAILED:
					parserObj = msg.obj;
					mCallback.onFailed(mContext, mPID, parserObj);
					break;
				case MSG_CB_COMPLETED:
				default:
					if (null != mProgressDialog && mProgressDialog.isShowing()) {
						mProgressDialog.dismiss();
						mProgressDialog = null;
					}
					mCallback.onCompleted(mContext, mPID);
					break;
				}
			}
		};
	}

	public void setParser(Class<?> mParser) {
		this.mParser = mParser;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getProcessName() {
		return null != processName ? processName : "Progressing...";
	}

	public void setCancelableProgressDialog(boolean cancelableProgressDialog) {
		this.cancelableProgressDialog = cancelableProgressDialog;
	}

	public boolean isCancelableProgressDialog() {
		return cancelableProgressDialog;
	}

	public void setEnabledProgressDialog(boolean enabledProgressDialog) {
		this.enabledProgressDialog = enabledProgressDialog;
	}

	public boolean isEnabledProgressDialog() {
		return enabledProgressDialog;
	}

	public void setPostData(List<NameValuePair> postData) {
		this.postData = postData;
	}

	public List<NameValuePair> getPostData() {
		return postData;
	}

	public void setHeaders(List<BasicHeader> headers) {
		this.headers = headers;
	}

	public List<BasicHeader> getHeaders() {
		return headers;
	}

	@Override
	public void run() {
		this.mThreadThis = Thread.currentThread();
		android.os.Process
				.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
		Message completeMessage = new Message();
		Object parserObj = new DefaultParser();
		try {
			completeMessage = mHandler.obtainMessage(MSG_CB_STARTED, mContext
					.getResources().getString(R.string.request_aborted));
			completeMessage.sendToTarget();
			parserObj = getParserInstance();

			switch (mType) {
			case Config.POST:
			case Config.GET:
				String result = processDataRequest(mContext, mUrl, mType);
				((IParser) parserObj).parser(mContext, this.mPID, result);
//				mCallback.onSuccess(mContext, mPID, result);
				break;
			case Config.UPLOAD:
				String uploadResult = processDataRequest(mContext, mUrl, mType);
				((IParser) parserObj).parser(mContext, this.mPID, uploadResult);
//				mCallback.onSuccess(mContext, mPID, uploadResult);
				break;
			// case Config.DOWNLOAD:
			// parserObj = processDownload(mUrl);
			// break;
			default:
				break;
			}
			if (Thread.interrupted()) {

				((IParser) parserObj).setError(mContext.getResources()
						.getString(R.string.request_aborted));
				completeMessage = mHandler.obtainMessage(MSG_CB_FAILED,
						parserObj);
			} else if (Config.DOWNLOAD == mType) {
				((IParser) parserObj).setError(mContext.getResources()
						.getString(R.string.download_failed));
				completeMessage = mHandler.obtainMessage(MSG_CB_FAILED,
						parserObj);
			} else {
				if (((IParser) parserObj).isError()) {
					completeMessage = mHandler.obtainMessage(MSG_CB_FAILED,parserObj);
				} else {
					completeMessage = mHandler.obtainMessage(MSG_CB_SUCCESS,parserObj);
				}
			}

			mHandler.obtainMessage(MSG_CB_COMPLETED).sendToTarget();
			completeMessage.sendToTarget();
		}  catch (Exception e) {
			if (Thread.interrupted()) {
				Log.e("http_task",
						String.format(mContext.getResources().getString(
								R.string.request_aborted)));
				((IParser) parserObj).setError(mContext.getResources()
						.getString(R.string.request_aborted));
				completeMessage = mHandler.obtainMessage(MSG_CB_FAILED,
						parserObj);
			} else {
				Log.e("http_task", String.format("error : %s", e.toString()));
				((IParser) parserObj).setError(e.getMessage());
				completeMessage = mHandler.obtainMessage(MSG_CB_FAILED,
						parserObj);
			}
			mHandler.obtainMessage(MSG_CB_COMPLETED).sendToTarget();
			completeMessage.sendToTarget();

		} finally {
			mThreadThis.interrupt();
			Thread.interrupted();
			mThreadThis = null;
		}
	}

	@SuppressWarnings("rawtypes")
	private Object getParserInstance() {
		try {
			if (null == mParser) {
				return new DefaultParser();
			}
			Log.d("parse", mParser.getName());
			Class clazz = Class.forName(mParser.getName());
			Object o = (Object) clazz.newInstance();
			return (o);
		} catch (Exception e) {
			Log.e("parse", e.getMessage());
		}
		return new DefaultParser();
	}

	private String processDataRequest(Context context, String url, int type)
			throws APPException {
		Log.d("http_request", String.format("url : %s", url));
		// if (Utils.getAppMode() == Constants.MODE_STAGING && isDummyMode()) {
		// if (isDummyResonse()) {
		// Log.d("http_request",
		// String.format("In Dummy Mode & Dummy Response"));
		// return getDummyResonse();
		// } else {
		// Log.d("http_request", String.format("In Dummy Mode"));
		// return "IN DUMMY MODE";
		// }
		// }
		boolean success = false;
		String result = context.getResources().getString(
				R.string.network_timeout);
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams,
				HttpManager.CONNECT_TIME_OUT);
		HttpConnectionParams
				.setSoTimeout(httpParams, HttpManager.READ_TIME_OUT);
		
		HttpClient client = getNewHttpClient(httpParams);
		try {
			HttpResponse response = null;
			if (type == Config.POST) {
				HttpPost post = new HttpPost(url);
				if (headers.size() != 0) {
					BasicHeader[] hd = new BasicHeader[headers.size()];
					headers.toArray(hd);
					post.setHeaders(hd);
				}
				if (postData != null) {
					for (NameValuePair itm : postData) {
						Log.d("POST", itm.getName() + " : " + itm.getValue());
					}
					post.setEntity(new UrlEncodedFormEntity(postData));
				}
				mHttpInstance = post;
				response = client.execute(post);
			} else if (type == Config.GET) {
				HttpGet get = new HttpGet(url);
				if (headers.size() != 0) {
					BasicHeader[] hd = new BasicHeader[headers.size()];
					headers.toArray(hd);
					get.setHeaders(hd);
				}
				mHttpInstance = get;
				response = client.execute(get);
			} else {
				throw new APPException("Unknown Type of HTTP Request");
			}
			int httpCode = response.getStatusLine().getStatusCode();
			result = EntityUtils.toString(response.getEntity());
			success = true;
			Log.d("http_request", "Response => " + result + " HTTP CODE : "
					+ httpCode);
		} catch (Exception e) {
			Log.d("http_request", "AsyncPost Exception => " + e.toString());
			e.printStackTrace();
		} finally {
			client.getConnectionManager().shutdown();
			Log.d("http_request", "Http Complete");
		}
		if (!success) {
			throw new APPException(result);
		}
		return result;
	}
	
	private HttpClient getNewHttpClient(HttpParams httpParams) {
	     try {
	         KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
	         trustStore.load(null, null);

	         SSLSocketFactory sf = new SSLConFactory(trustStore);
	         sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

//	         HttpParams params = new BasicHttpParams();
	         HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
	         HttpProtocolParams.setContentCharset(httpParams, HTTP.UTF_8);

	         SchemeRegistry registry = new SchemeRegistry();
	         registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
	         registry.register(new Scheme("https", sf, 443));

	         ClientConnectionManager ccm = new ThreadSafeClientConnManager(httpParams, registry);

	         return new DefaultHttpClient(ccm, httpParams);
	     } catch (Exception e) {
	         return new DefaultHttpClient();
	     }
	}

	class ProgressDismissListener implements DialogInterface.OnCancelListener {
		private HttpTask mTask;

		public ProgressDismissListener(HttpTask mTask) {
			super();
			this.mTask = mTask;
		}

		@Override
		public void onCancel(DialogInterface dialog) {
			mTask.cancelTask();
		}
	}

	public void cancelTask() {
		if (null != mThreadThis) {
			Log.d("cancel_task", "pre");
			mThreadThis.interrupt();
			Log.d("cancel_task", "interuppted : " + Thread.interrupted());
			if (mHttpInstance instanceof HttpPost) {
				HttpPost http = (HttpPost) mHttpInstance;
				http.abort();
				Log.d("cancel_task", "Post Aborted");
			} else if (mHttpInstance instanceof HttpGet) {
				HttpGet http = (HttpGet) mHttpInstance;
				http.abort();
				Log.d("cancel_task", "GET Aborted");
			}
		}
	}
}
