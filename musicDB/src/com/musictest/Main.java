package com.musictest;

import com.musictest.model.Artists;
import com.musictest.model.Datasource;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Datasource datasource = new Datasource();

        // 예외처리
        if(!datasource.open()) {
            System.out.println("Can't open datasource");
            return;
        }


        datasource.SqlReturn();


        ArrayList<Artists> artists = datasource.queryArtists();
        if(artists==null){
            System.out.println("Can't find an artist");
            return;
        }
        for (Artists artist : artists){
            System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());
        }

        datasource.close();

/*        try {
            // 데이터 베이스와 연결하는 객체
            //Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\402-023\\Database\\musicdb.db");
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);

            //데이터 베이스로 SQL문을 보내기 위한 개체 생성
            Statement statement = conn.createStatement();

            //execute 메소드 매개변수로 전달받은 SQL문을 수행하는 매소드
//          statement.execute("");

            ResultSet result1 = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS1 + " WHERE _id" + " BETWEEN 1 AND 10");
            while(result1.next()){
                System.out.println(result1.getInt(COLUMN_ALBUMS_ID) + " " +
                        result1.getString(COLUMN_ALBUMS_NAME) + " " +
                        result1.getInt(COLUMN_ALBUMS_ARTIST));
            }
            result1.close();

            ResultSet result2 = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS2 + " WHERE _id" + " BETWEEN 1 AND 10");
            while(result2.next()){
                System.out.println(result2.getInt(COLUMN_ARTISTS_ID) + " " +
                        result2.getString(COLUMN_ARTISTS_NAME));
            }
            result2.close();

            ResultSet result3 = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS3 + " WHERE _id" + " BETWEEN 1 AND 10");
            while(result3.next()){
                System.out.println(result3.getInt(COLUMN_SONGS_ID) + " " +
                        result3.getInt(COLUMN_SONGS_TRACK) + " " +
                        result3.getString(COLUMN_SONGS_TITLE) + " " +
                        result3.getInt(COLUMN_SONGS_ALBUM));
            }
            result3.close();

            statement.close();
            conn.close();

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Something went wrong.... " + e.getMessage());
        }
*/
    }
}