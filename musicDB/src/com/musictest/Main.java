package com.musictest;

import com.musictest.model.Artists;
import com.musictest.model.Datasource;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Datasource datasource = new Datasource();

        // 예외처리
        if(!datasource.open()) {
            System.out.println("Can't open datasource");
            return;
        }

        //내가 만듬
        datasource.SqlReturn();

//        List<Artists> artists = datasource.queryArtists(Datasource.ORDER_BY_ASC);
//        List<Artists> artists = datasource.queryArtists(Datasource.ORDER_BY_DESC);
//        List<Artists> artists = datasource.queryArtists(Datasource.ORDER_BY_NONE);

        List<Artists> artists = datasource.queryArtists(2);


//        ArrayList<Artists> artists = datasource.queryArtists();

        if(artists==null){
            System.out.println("Can't find an artist");
            return;
        }

        //for (자료형 변수명 : 배열명)
        for (Artists artist : artists){
            System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());
        }

        datasource.close();
    }
}