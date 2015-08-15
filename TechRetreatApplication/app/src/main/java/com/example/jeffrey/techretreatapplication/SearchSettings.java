package com.example.jeffrey.techretreatapplication;

/**
 * Created by Jeffrey on 2015-08-15.
 */
public class SearchSettings {

    public static SearchSettings instance;

    private boolean chinese;
    private boolean indian;
    private boolean japanese;
    private boolean korean;
    private boolean greek;
    private boolean italian;
    private boolean mediterranean;
    private boolean french;
    private boolean pub;
    private boolean seafood;
    private boolean barbeque;

    private int distance;

    private double lat,lng;

    public SearchSettings(){

    }


    public boolean isBarbeque() {
        return barbeque;
    }

    public void setBarbeque(boolean barbeque) {
        this.barbeque = barbeque;
    }

    public boolean isChinese() {
        return chinese;
    }

    public void setChinese(boolean chinese) {
        this.chinese = chinese;
    }

    public boolean isIndian() {
        return indian;
    }

    public void setIndian(boolean indian) {
        this.indian = indian;
    }

    public boolean isJapanese() {
        return japanese;
    }

    public void setJapanese(boolean japanese) {
        this.japanese = japanese;
    }

    public boolean isKorean() {
        return korean;
    }

    public void setKorean(boolean korean) {
        this.korean = korean;
    }

    public boolean isGreek() {
        return greek;
    }

    public void setGreek(boolean greek) {
        this.greek = greek;
    }

    public boolean isItalian() {
        return italian;
    }

    public void setItalian(boolean italian) {
        this.italian = italian;
    }

    public boolean isMediterranean() {
        return mediterranean;
    }

    public void setMediterranean(boolean mediterranean) {
        this.mediterranean = mediterranean;
    }

    public boolean isFrench() {
        return french;
    }

    public void setFrench(boolean french) {
        this.french = french;
    }

    public boolean isPub() {
        return pub;
    }

    public void setPub(boolean pub) {
        this.pub = pub;
    }

    public boolean isSeafood() {
        return seafood;
    }

    public void setSeafood(boolean seafood) {
        this.seafood = seafood;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }



    public static SearchSettings generateInstance(){
        instance = new SearchSettings();
        return instance;
    }

    public static SearchSettings getInstance(){
        return instance;
    }



}
