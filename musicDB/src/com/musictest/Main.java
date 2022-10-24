package com.musictest;

import com.musictest.model.Albums;
import com.musictest.model.Artists;
import com.musictest.model.Datasource;
import com.musictest.model.Songs;

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
//        List<Artists> artists = datasource.queryArtists("ORDER_BY_DESC");


//        ArrayList<Artists> artists = datasource.queryArtists();

        // artists 출력이라 닫아 놓음
//        if(artists==null){
//            System.out.println("Can't find an artist");
//            return;
//        }
//
//        //for (자료형 변수명 : 배열명)
//        for (Artists artist : artists){
//            System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());
//        }

//        List<Albums> albums = datasource.queryArtistName("Pink Floyd");



//        if(albums==null){
//            System.out.println("Can't find an artist");
//            return;
//        }
//
//        //for (자료형 변수명 : 배열명)
//        for (Albums album : albums){
//            System.out.println("ArtistName = "+ Datasource.artistName + ", AlbumName = " + album.getName());
//        }

        List<Songs> songs = datasource.querySongsTitle("Go Your Own Way");

        if(songs==null){
            System.out.println("Can't find an artist");
            return;
        }

        //for (자료형 변수명 : 배열명)
        for (Songs song : songs){
            System.out.println("ArtistName = "+ Datasource.artistName + ", AlbumName = " + song.getTitle());
        }

        datasource.close();
    }
}