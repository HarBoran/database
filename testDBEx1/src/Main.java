import java.sql.*;
public class Main {

    public static final String DB_NAME = "testjava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\402-023\\Database\\" + DB_NAME;
    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";

    //개인정보 부분을 private class활용하여 보호하시오

    private static void insertContents(Statement statement,String name, int phone, String email) throws SQLException{
        statement.execute( "INSERT INTO " + TABLE_CONTACTS + " (" + COLUMN_NAME +", " + COLUMN_PHONE +", "  + COLUMN_EMAIL+") " +
                "VALUES('" + name + "'," + phone + ",'" + email + "')");
    }

    private static String insertValue(String name, int phone, String email){
        return  "INSERT INTO " + TABLE_CONTACTS + " (" + COLUMN_NAME +", " + COLUMN_PHONE +", "  + COLUMN_EMAIL+") " +
                " VALUES('" + name + "'," + phone + ",'" + email + "')";
    }

    public static void main(String[] args) {

        try {
            // 데이터 베이스와 연결하는 객체, 연결되지 않을 경우 오류
            //Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\402-023\\Database\\testjava.db");
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);

            //데이터 베이스로 SQL문을 보내기 위한 개체 생성
            Statement statement = conn.createStatement();

            //execute 메소드 매개변수로 전달받은 SQL문을 수행하는 매소드
            statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

            //데이터 테이블 만들기 명령어는 한번만 실행해야함, 중복되지 않음
//          statement.execute("CREATE TABLE contacts (name TEXT, phone INTEGER, email TEXT)");
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + " (" + COLUMN_NAME + " TEXT, '" +  COLUMN_PHONE + "' INTEGER, " + COLUMN_EMAIL + " TEXT)");

            //데이터 입력은 계속됨
            statement.execute("INSERT INTO contacts (name, phone, email)"+"VALUES('sam', 222, 'sam@email.com')");
            statement.execute("INSERT INTO " + TABLE_CONTACTS +" (" + COLUMN_NAME + ", " + COLUMN_PHONE + ", " + COLUMN_EMAIL+ ") VALUES('Lora', 333, 'lora@email.com')");
            statement.execute("INSERT INTO " + TABLE_CONTACTS +" VALUES('tim', 444, 'tim@email.com')");

            insertContents(statement, "john", 555, "john@email.com");
            statement.execute(insertValue("jain", 666, "jain@email.com"));

            statement.execute("UPDATE " + TABLE_CONTACTS + " SET " + COLUMN_PHONE + " = 111 WHERE " + COLUMN_NAME + " = 'jain'");

            statement.execute("DELETE FROM " + TABLE_CONTACTS + " WHERE " + COLUMN_NAME + " = 'john'");

            ResultSet result = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS);

            while(result.next()){
                System.out.println(result.getString(COLUMN_NAME) + " " +
                                   result.getInt(COLUMN_PHONE) + " " +
                                   result.getString(COLUMN_EMAIL));
            }
            result.close();

            statement.close();
            conn.close();

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Something went wrong.... " + e.getMessage());
        }
    }
}