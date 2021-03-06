package com.dashin.dashindelivery.dataclasses;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.GeoPoint;

public class OrderData {
    String BUSI_NAME,BUSI_ADD,D_R_INDEX,OFFER_CODE,TRAN_ID,METHOD,TYPE,FROM,TO;
    Timestamp TIME;
    GeoPoint BUSI_LOC,CUST_LOC;
    long AMOUNT,STATUS;
    boolean OFFER,LIKED;

    public OrderData() {
    }

    public OrderData(String BUSI_NAME, String BUSI_ADD, String d_R_INDEX, String OFFER_CODE, String TRAN_ID, String METHOD, String TYPE, String FROM, String TO, Timestamp TIME, GeoPoint BUSI_LOC, GeoPoint CUST_LOC, long AMOUNT, long STATUS, boolean OFFER, boolean LIKED) {
        this.BUSI_NAME = BUSI_NAME;
        this.BUSI_ADD = BUSI_ADD;
        this.D_R_INDEX = d_R_INDEX;
        this.OFFER_CODE = OFFER_CODE;
        this.TRAN_ID = TRAN_ID;
        this.METHOD = METHOD;
        this.TYPE = TYPE;
        this.FROM = FROM;
        this.TO = TO;
        this.TIME = TIME;
        this.BUSI_LOC = BUSI_LOC;
        this.CUST_LOC = CUST_LOC;
        this.AMOUNT = AMOUNT;
        this.STATUS = STATUS;
        this.OFFER = OFFER;
        this.LIKED = LIKED;
    }

    public String getBUSI_NAME() {
        return BUSI_NAME;
    }

    public void setBUSI_NAME(String BUSI_NAME) {
        this.BUSI_NAME = BUSI_NAME;
    }

    public String getBUSI_ADD() {
        return BUSI_ADD;
    }

    public void setBUSI_ADD(String BUSI_ADD) {
        this.BUSI_ADD = BUSI_ADD;
    }

    public String getD_R_INDEX() {
        return D_R_INDEX;
    }

    public void setD_R_INDEX(String d_R_INDEX) {
        D_R_INDEX = d_R_INDEX;
    }

    public String getOFFER_CODE() {
        return OFFER_CODE;
    }

    public void setOFFER_CODE(String OFFER_CODE) {
        this.OFFER_CODE = OFFER_CODE;
    }

    public String getTRAN_ID() {
        return TRAN_ID;
    }

    public void setTRAN_ID(String TRAN_ID) {
        this.TRAN_ID = TRAN_ID;
    }

    public String getMETHOD() {
        return METHOD;
    }

    public void setMETHOD(String METHOD) {
        this.METHOD = METHOD;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getFROM() {
        return FROM;
    }

    public void setFROM(String FROM) {
        this.FROM = FROM;
    }

    public String getTO() {
        return TO;
    }

    public void setTO(String TO) {
        this.TO = TO;
    }

    public Timestamp getTIME() {
        return TIME;
    }

    public void setTIME(Timestamp TIME) {
        this.TIME = TIME;
    }

    public GeoPoint getBUSI_LOC() {
        return BUSI_LOC;
    }

    public void setBUSI_LOC(GeoPoint BUSI_LOC) {
        this.BUSI_LOC = BUSI_LOC;
    }

    public GeoPoint getCUST_LOC() {
        return CUST_LOC;
    }

    public void setCUST_LOC(GeoPoint CUST_LOC) {
        this.CUST_LOC = CUST_LOC;
    }

    public long getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(long AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public long getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(long STATUS) {
        this.STATUS = STATUS;
    }

    public boolean isOFFER() {
        return OFFER;
    }

    public void setOFFER(boolean OFFER) {
        this.OFFER = OFFER;
    }

    public boolean isLIKED() {
        return LIKED;
    }

    public void setLIKED(boolean LIKED) {
        this.LIKED = LIKED;
    }
}
