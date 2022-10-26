package com.musictest.model;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {
    public static final String DB_NAME = "music.db";
    //  public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\402-023\\Database\\musicDB\\" + DB_NAME;
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;

    public static final String TABLE_CONTACTS1 = "albums";
    public static final String COLUMN_ALBUMS_ID = "_id";
    public static final String COLUMN_ALBUMS_NAME = "name";
    public static final String COLUMN_ALBUMS_ARTIST = "artist";

    public static final String TABLE_CONTACTS2 = "artists";
    public static final String COLUMN_ARTISTS_ID = "_id";
    public static final String COLUMN_ARTISTS_NAME = "name";

    public static final String TABLE_CONTACTS3 = "songs";
    public static final String COLUMN_SONGS_ID = "_id";
    public static final String COLUMN_SONGS_TRACK = "track";
    public static final String COLUMN_SONGS_TITLE = "title";
    public static final String COLUMN_SONGS_ALBUM = "album";

//    public static final String ORDER_BY_ASC = "ORDER BY "  + COLUMN_ARTISTS_NAME + " ASC";
//    public static final String ORDER_BY_DESC = "ORDER BY "  + COLUMN_ARTISTS_NAME + " DESC";
//    public static final String ORDER_BY_NONE = "ORDER BY " + COLUMN_ARTISTS_NAME;

    public static final String ORDER_BY_ASC = "ORDER_BY_ASC";
    public static final String ORDER_BY_DESC = "ORDER_BY_DESC";
    public static final String ORDER_BY_NONE = "ORDER_BY_NONE";

//    public static final int ORDER_BY_ASC = 1;
//    public static final int ORDER_BY_DESC = 2;
//    public static final int ORDER_BY_NONE = 3;

    public static String artistName;
    public static String queryEx;
    private Connection conn;

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            System.out.println("It was connected");
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    //내가 만듬
    public void SqlReturn() {
        try {
            //데이터 베이스로 SQL문을 보내기 위한 개체 생성
            Statement statement = conn.createStatement();

            //ArrayList 선언
//          ArrayList<Integer> artists_id = new ArrayList();
//          ArrayList<String> artists_name = new ArrayList();
            ArrayList<Artists> artists = new ArrayList();

            //result2를 참조변수로 퀴리문을 통해 데이터 베이스를 불러옴
            ResultSet result2 = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS2);

            //선언된 Arraylist에 result2에 저장된 데이터를 저장함
            while (result2.next()) {
//              artists_id.add(result2.getInt(COLUMN_ARTISTS_ID));
//              artists_name.add(result2.getString(COLUMN_ARTISTS_NAME));
                Artists artist = new Artists();
                artist.setId(result2.getInt(COLUMN_ARTISTS_ID));
                artist.setName(result2.getString(COLUMN_ARTISTS_NAME));
                //System.out.println(artist.getId());
                //System.out.println(artist.getName());
                artists.add(artist);
            }
            result2.close();

            //ArrayList에 저장된 값을 출력함
//          for (int i : artists_id) {
//              System.out.println("ID : " + artists_id.get(i - 1) + ", Name = " + artists_name.get(i - 1));
//          }

//            for (Artists i : artists) {;
//                System.out.println("ID : " + artist.getId(i) + ", Name = " + artists.get());
//            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong.... " + e.getMessage());
        }
    }

    //int로 파라미터 값 받기
//    public List<Artists> queryArtists(int sortingOrder) {
//        //SELECT * FROM artists ORDER BY name ASC;
//        StringBuilder sb = new StringBuilder("SELECT * FROM ");
//        sb.append(TABLE_CONTACTS2);
//
//        if(sortingOrder == ORDER_BY_ASC){
//            sb.append(" ORDER BY ");
//            sb.append(COLUMN_ARTISTS_NAME);
//            sb.append(" ASC");
//        } else if (sortingOrder == ORDER_BY_DESC) {
//            sb.append(" ORDER BY ");
//            sb.append(COLUMN_ARTISTS_NAME);
//            sb.append(" DESC");
//        }

    //중복되어서 닫아 놓음
//    public List<Artists> queryArtists(String sortingOrder) {
//        //SELECT * FROM artists ORDER BY name ASC;
//        StringBuilder sb = new StringBuilder("SELECT * FROM ");
//        sb.append(TABLE_CONTACTS2);
//
//        if (sortingOrder == "ORDER_BY_ASC") {
//            sb.append(" ORDER BY ");
//            sb.append(COLUMN_ARTISTS_NAME);
//            sb.append(" ASC");
//        } else if (sortingOrder == "ORDER_BY_DESC") {
//            sb.append(" ORDER BY ");
//            sb.append(COLUMN_ARTISTS_NAME);
//            sb.append(" DESC");
//        }
//
//        try (Statement statement = conn.createStatement();
//             ResultSet results = statement.executeQuery(sb.toString())) {
//            System.out.println(sb.toString());
//
//            List<Artists> artists = new ArrayList<>();
//
//            while (results.next()) {
//                Artists artist = new Artists();
//                artist.setId(results.getInt(COLUMN_ARTISTS_ID));
//                artist.setName(results.getString(COLUMN_ARTISTS_NAME));
//                artists.add(artist);
////                  Artists i = new Artists();
////                  i.setId(results.getInt(COLUMN_ARTISTS_ID));
////                  i.setName(results.getString(COLUMN_ARTISTS_NAME));
////                  artists.add(i);
//            }
//            return artists;
//        } catch (SQLException e) {
//            System.out.println("Query failed: " + e.getMessage());
//            return null;
//        }

    //기존 queryArtists
//    public ArrayList<Artists> queryArtists() {
//
//        try (Statement statement = conn.createStatement();
//             ResultSet results = statement.executeQuery
//                     ("SELECT * FROM " + TABLE_CONTACTS2 +" "+ orderBy)){
//
//            ArrayList<Artists> artists = new ArrayList<>();
//
//            while(results.next()) {
//                Artists artist = new Artists();
//                artist.setId(results.getInt(COLUMN_ARTISTS_ID));
//                artist.setName(results.getString(COLUMN_ARTISTS_NAME));
//                artists.add(artist);
////                  Artists i = new Artists();
////                  i.setId(results.getInt(COLUMN_ARTISTS_ID));
////                  i.setName(results.getString(COLUMN_ARTISTS_NAME));
////                  artists.add(i);
//            }
//            return artists;
//        }catch (SQLException e){
//            System.out.println("Query failed: " + e.getMessage());
//            return null;

    //아티스트의 이름을 파라미터로 넣으면
    //알파벳 순서대로 앨범들이 나오도록 코드를 짜세요
//    public List<Albums> queryArtistName(String artistName){
//        this.artistName = artistName;
//
//        StringBuilder sb = new StringBuilder();
//        sb.append( "SELECT " + TABLE_CONTACTS1 + "."+ COLUMN_ALBUMS_NAME);
//        sb.append(" FROM " + TABLE_CONTACTS1);
//        sb.append(" INNER JOIN "+ TABLE_CONTACTS2);
//        sb.append(" ON " + TABLE_CONTACTS1 + "."+COLUMN_ALBUMS_ARTIST +" = " + TABLE_CONTACTS2 + "." + COLUMN_ARTISTS_ID);
//        sb.append(" WHERE "+ TABLE_CONTACTS2 + "." +COLUMN_ARTISTS_NAME + " = \"" + artistName + "\"");
//        sb.append(" ORDER BY " + TABLE_CONTACTS1 +"."+ COLUMN_ALBUMS_NAME +" ASC" );
//
//        queryEx =  sb.toString();
//        System.out.println(artistName);
//
//        try (Statement statement = conn.createStatement();
//             ResultSet results = statement.executeQuery(sb.toString())){
//
//            List<Albums> albums = new ArrayList<>();
//
//            while (results.next()) {
//                Albums album = new Albums();
//                album.setName(results.getString(COLUMN_ALBUMS_NAME));
//                albums.add(album);
//            }
//            return albums;
//        } catch (SQLException e) {
//            System.out.println("Query failed: " + e.getMessage());
//            return null;
//        }
//      }

    public List<SongsTitle> querySongsTitle(String songsTitle) {

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT " + TABLE_CONTACTS2 + "." + COLUMN_ARTISTS_NAME + ", " + TABLE_CONTACTS1 + "." + COLUMN_ALBUMS_NAME + " as albumname, " + TABLE_CONTACTS3 + "." + COLUMN_SONGS_TRACK + " FROM " + TABLE_CONTACTS3);
        sb.append(" INNER JOIN " + TABLE_CONTACTS2 + " ON " + TABLE_CONTACTS1 + "." + COLUMN_ALBUMS_ARTIST + " = " + TABLE_CONTACTS2 + "." + COLUMN_ARTISTS_ID);
        sb.append(" INNER JOIN " + TABLE_CONTACTS1 + " ON " + TABLE_CONTACTS3 + "." + COLUMN_SONGS_ALBUM + " = " + TABLE_CONTACTS1 + "." + COLUMN_ALBUMS_ID);
        sb.append(" WHERE " + TABLE_CONTACTS3 + "." + COLUMN_SONGS_TITLE + " = \"" + songsTitle + "\"");
        sb.append(" ORDER BY " + TABLE_CONTACTS1 + "." + COLUMN_ALBUMS_NAME + " ASC");

        System.out.println(sb.toString());

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<SongsTitle> titles = new ArrayList<>();

            while (results.next()) {
                SongsTitle title = new SongsTitle();
                title.setArtistsName(results.getString(1));
                title.setAlbumsName(results.getString("album" + COLUMN_ALBUMS_NAME));
                title.setSongsTrack(results.getInt(COLUMN_SONGS_TRACK));

                titles.add(title);
            }
            return titles;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public void querySongsMetaData() {
        String sql = "SELECT * FROM " + TABLE_CONTACTS3;

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sql)) {

            ResultSetMetaData meta = results.getMetaData();
            int numColumns = meta.getColumnCount();

            for (int i = 1; i <= numColumns; i++) {
                System.out.format("Column %d int the songs table is names %s\n", i, meta.getColumnName(i));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong.... " + e.getMessage());
        }
    }

    public void songCount() {
//        String sql = "SELECT COUNT(DISTINCT title) as title FROM " + TABLE_CONTACTS3;
        String sql = "SELECT DISTINCT title FROM " + TABLE_CONTACTS3;

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sql)) {

            List<String> sc = new ArrayList<>();

            while (results.next()) {
                sc.add(results.getString("title"));
            }

//      System.out.println(sc);
            System.out.println(sc.get(1000));

            System.out.println(sc.size());

//            for (int i = 0; i < sc.size(); i++) {
//                System.out.println(sc.get(i));
//            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong.... " + e.getMessage());
        }
    }

    public void TitleCount(){

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT " + TABLE_CONTACTS2+ "." + COLUMN_ARTISTS_NAME+", COUNT(*) FROM "+ TABLE_CONTACTS3);
        sb.append(" INNER JOIN "+TABLE_CONTACTS1+" ON "+TABLE_CONTACTS3+"."+COLUMN_SONGS_ALBUM+" = "+TABLE_CONTACTS1+"."+COLUMN_ALBUMS_ID);
        sb.append(" INNER JOIN "+ TABLE_CONTACTS2+" ON "+TABLE_CONTACTS1+"."+COLUMN_ALBUMS_ARTIST+" = "+TABLE_CONTACTS2+"."+COLUMN_ARTISTS_ID);
        sb.append(" GROUP BY "+TABLE_CONTACTS2+"."+COLUMN_ARTISTS_ID);
        sb.append(" ORDER BY COUNT(*) DESC;");

        System.out.println(sb);

        try(Statement statement = conn.createStatement();
        ResultSet results = statement.executeQuery(sb.toString())){


        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong.... " + e.getMessage());
        }

    }

}