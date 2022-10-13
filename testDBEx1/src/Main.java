import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try {
            // 데이터 베이스와 연결하는 객체, 연결되지 않을 경우 오류
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\402-023\\Database\\testjava.db");
            //데이터 베이스로 SQL문을 보내기 위한 개체 생성
            Statement statement = conn.createStatement();

            //execute 메소드 매개변수로 전달받은 SQL문을 수행하는 매소드
            //데이터 테이블 만들기 명령어는 한번만 실행해야함, 중복되지 않음
//          statement.execute("CREATE TABLE contacts (name TEXT, phone INTEGER, email TEXT)");

            //데이터 입력은 계속됨
//          statement.execute("INSERT INTO contacts (name, phone, email)"+"VALUES('sam', 222, 'sam@email.com')");
//          statement.execute("INSERT INTO contacts (name, phone, email)"+"VALUES('Lora', 333, 'lora@email.com')");
//          statement.execute("INSERT INTO contacts VALUES('tim', 444, 'tim@email.com')");

//          statement.execute("UPDATE contacts SET phone = 111 WHERE name = 'tim'");

//          statement.execute("DELETE FROM contacts WHERE name = 'tim'");

            execute e = new execute("john",123,"john@email.com");
            System.out.println(e.name);
            System.out.println(e.phone);
            System.out.println(e.email);

            ResultSet result = statement.executeQuery("SELECT * FROM contacts");
            while(result.next()){
                System.out.println(result.getString("name") + " " +
                                   result.getInt("phone") + " " +
                                   result.getString("email"));
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

//인서트문을 클래스로 변경하기
//private를 활용하여 개인정보를 보호하시오