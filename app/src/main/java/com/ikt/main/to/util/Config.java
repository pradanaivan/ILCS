package com.ikt.main.to.util;

import com.ikt.main.to.BuildConfig;

public class Config {

    public static final String TAG = "IKT APP#";
    public static final int GET = 1;
    public static final int POST = 2;
    public static final int UPLOAD = 3;
    public static final int DOWNLOAD = 4;
    public static final String CLIENT_ID = "948460267388";
    public static final String CLIENT_SECRET_GCM = "dokupass";
//    public static final String URL = "http://103.19.81.26:5180/cartos_api/index.php/";

    public static String getUrlCartos(String url) {
        return BuildConfig.ENDPOINT_CARTOS + url;
    }

    public static String getUrlReference(String url) {
        return BuildConfig.ENDPOINT_REFERENCE + url;
    }

    public static String getVersion(){
        return BuildConfig.VERSION_NAME;
    }

    public static String URL_CARTOS = getUrlCartos("");
    public static String URL_REFERENCE = getUrlReference("");
    public static String URL_LOGIN = URL_REFERENCE + "login/login";
    public static String URL_DRIVER = URL_REFERENCE + "Driver/driver";
    public static String URL_DELETE_DRIVER = URL_REFERENCE + "DeleteDriver";
	public static String URL_ADD_DRIVER = URL_REFERENCE+"AddDriver";
    public static String URL_EDIT_DRIVER = URL_REFERENCE+"EditDriver";
    public static String URL_GET_TICKET = URL_REFERENCE+"GetTicketList";
    public static String URL_GET_TICKET_DETAIL = URL_REFERENCE+"GetTicket";
    public static String URL_GET_STATUS = URL_REFERENCE+"GetStatus";
    public static String URL_GET_TRUCK_ACTIVITIES = URL_REFERENCE+"GetActivityList";
    public static String URL_GET_ACTIVITIES = URL_REFERENCE+"GetActivity";
    public static String URL_ASSIGN_DRIVER = URL_REFERENCE+"AssignDriver";
    public static String URL_CHANGE_PASSWORD = URL_REFERENCE + "ChangePassword";
    public static String URL_GCM = URL_REFERENCE+"Gcm/gcm";
    public static String URL_GCM_MSG_DETAIL = URL_REFERENCE+"GetMessageDetail";
    public static String URL_EDIT_VISIT_RETRIEVE = URL_CARTOS + "VisitEdit/retrieve";
    public static String URL_EDIT_VISIT_SAVE = URL_CARTOS + "VisitEdit/edit";
    public static String URL_EDIT_VIN_OUTGOING = URL_CARTOS + "VisitEdit/getvin";
    public static String URL_EDIT_TRIP_OUTGOING = URL_CARTOS + "VisitEdit/gettrip";
    public static String URL_EDIT_SAVE_VIN = URL_CARTOS + "VisitEdit/editvin";
    public static String URL_EDIT_SAVE_TRIP = URL_CARTOS + "VisitEdit/edittrip";
    public static String URL_CHECK_VIN = URL_CARTOS + "checkVinStatusAndHold";
    public static String URL_GET_VINS = URL_CARTOS + "Retrieve/listvin";
    public static String URL_GET_TRIPS = URL_CARTOS + "Retrieve/cmstrip";
    public static String URL_CHECK_VERSION = URL_CARTOS + "checkVersion";
    public static String URL_CHECK_VIN_SCAN = URL_CARTOS + "checkVinScanned";
    public static String URL_GET_SLOT = URL_CARTOS + "BookSlotOption";
    public static String URL_ANNOUNCE_VIN = URL_CARTOS + "AnnounceVin";



    public static String URL_TRUCK = URL_CARTOS + "GetTruck";
    public static String URL_TIMESLOT_DOMESTIC = URL_CARTOS + "bookSlotDomestic";
    public static String URL_TIMESLOT_INTERNATIONAL = URL_CARTOS + "bookSlotInternational";
    public static String URL_INCOMING = URL_CARTOS + "cartosApiIncoming";
    public static String URL_OUTGOING = URL_CARTOS + "cartosApiOutgoing";
    public static String URL_BACKLOAD = URL_CARTOS + "cartosApiBackLoad";
    public static String KEY_NAME = "NAME";
    public static String KEY_ID = "ID";
    public static String KEY_TYPE = "TYPE";
    public static String KEY_DISCOUNT = "DISCOUNT";
    public static String KEY_PRICE = "PRICE";
    public static String KEY_THUMB_URL = "THUMB_URL";
    public static String KEY_USER_ID = "user_id";
    public static String KEY_USERNAME = "username";
    public static String KEY_VISIT_ID = "visit_id";
    public static String KEY_ORG_ID = "org_id";
    public static String KEY_SESSION = "session_id";
    public static String KEY_LAST_ID = "last_id";
    public static String KEY_PROFILE = "profile";
    public static String IS_LOGIN = "login";
    public static String KEY_PRIVILEGE = "previlige";
    public static String HAVE_PIN = "pin";
    public static String IS_FIRST = "first";
    public static String TRUCK = "truck";
    public static int total = 0;
    public static int offer = 0;

    public static String keyLock = "arifin88";
    public static String DISPLAY_MESSAGE_ACTION = "act_msg";
    public static String EXTRA_MESSAGE = "msg";
}
