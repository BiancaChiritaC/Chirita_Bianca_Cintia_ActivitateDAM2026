package com.example.myapplication;

import java.net.URL;

public class ImaginiWeb {
    private String titlu;
    private String descriere;
    private String urlImagine;
    private String siteWeb;

    public ImaginiWeb(String titlu, String descriere,String urlImagine, String siteWeb){
        this.titlu=titlu;
        this.descriere=descriere;
        this.urlImagine=urlImagine;
        this.siteWeb=siteWeb;

    }

    public String getTitlu(){
        return this.titlu;
    }

    public String getDescriere(){
        return this.descriere;
    }

    public String getImagine(){
        return this.urlImagine;
    }

    public String getSiteWeb(){
        return this.siteWeb;
    }


}