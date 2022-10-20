package com.musictest.model;

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

//    public static final String ORDER_BY_ASC = "ORDER_BY_ASC";
//    public static final String ORDER_BY_DESC = "ORDER_BY_DESC";
//    public static final String ORDER_BY_NONE = "ORDER_BY_NONE";

    public static final int ORDER_BY_ASC = 1;
    public static final int ORDER_BY_DESC = 2;
    public static final int ORDER_BY_NONE = 3;

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

    public void close(){
        try{
            if(conn != null) {
                conn.close();
                System.out.println("Connection closed.");
            }
        } catch(SQLException e) {
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

    public List<Artists> queryArtists(int sortingOrder) {
        //SELECT * FROM artists ORDER BY name ASC;
        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_CONTACTS2);

        if(sortingOrder == ORDER_BY_ASC){
            sb.append(" ORDER BY ");
            sb.append(COLUMN_ARTISTS_NAME);
            sb.append(" ASC");
        } else if (sortingOrder == ORDER_BY_DESC) {
            sb.append(" ORDER BY ");
            sb.append(COLUMN_ARTISTS_NAME);
            sb.append(" DESC");
        }

//    public List<Artists> queryArtists(String sortingOrder) {
//        //SELECT * FROM artists ORDER BY name ASC;
//        StringBuilder sb = new StringBuilder("SELECT * FROM ");
//        sb.append(TABLE_CONTACTS2);
//
//        if(sortingOrder == "ORDER_BY_ASC"){
//            sb.append(" ORDER BY ");
//            sb.append(COLUMN_ARTISTS_NAME);
//            sb.append(" ASC");
//        } else if (sortingOrder == "ORDER_BY_DESC") {
//            sb.append(" ORDER BY ");
//            sb.append(COLUMN_ARTISTS_NAME);
//            sb.append(" DESC");
//        }

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())){
            System.out.println(sb.toString());

            List<Artists> artists = new ArrayList<>();

                while(results.next()) {
                    Artists artist = new Artists();
                    artist.setId(results.getInt(COLUMN_ARTISTS_ID));
                    artist.setName(results.getString(COLUMN_ARTISTS_NAME));
                    artists.add(artist);
//                  Artists i = new Artists();
//                  i.setId(results.getInt(COLUMN_ARTISTS_ID));
//                  i.setName(results.getString(COLUMN_ARTISTS_NAME));
//                  artists.add(i);
                }
            return artists;
        }catch (SQLException e){
            System.out.println("Query failed: " + e.getMessage());
            return null;

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
        }
    }
}