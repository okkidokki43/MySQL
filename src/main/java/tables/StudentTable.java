package tables;

import db.MySQLConnector;
import objects.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentTable extends AbsTable {
    private final static String TABLE_NAME = "student";
    public StudentTable() {
        super(TABLE_NAME);
        columns = new HashMap<>();
        columns.put("id", "bigint PRIMARY KEY AUTO_INCREMENT");
        columns.put("fio", "varchar(200)");
        columns.put("sex", "varchar(200)");
        columns.put("id_group", "bigint");
        create();
    }
    public void selectAllStudent() throws SQLException {
        String sqlQuery = "SELECT student.id, student.fio, group.name, " +
                "curator.fio from `group`, student," +
                " curator where student.id_group = group.id " +
                "and group.id_curator = curator.id;";
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        while (rs.next()) {
            System.out.println(
                    rs.getString(1) + " - " +
                            rs.getString(2) + " > " +
                            rs.getString(3) + " > Куратор: " +
                            rs.getString(4));
        }
    }
    public void selectSumm() throws SQLException {
        String sqlQuery = "SELECT count(*) FROM student;";
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        while (rs.next()) {
            System.out.println("");
            System.out.println("Общее количество студентов " + rs.getString(1));
        }
    }
    public void selectStudentGroup() throws SQLException {
        String sqlQuery = "select student.fio from student where" +
                " student.id_group = (select id from `group` where name = 'Кулибины');";
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
    }
    public void selectWomen() throws SQLException {
        String sqlQuery = "SELECT fio FROM student Where sex = 'Ж';";
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        while (rs.next()) {
            System.out.println("");
            System.out.println(rs.getString( 1));

        }
    }
    public ArrayList<Student> selectAll(){
        String sqlQuery = String.format("SELECT * FROM %s", tableName);
        return selectByQuery(sqlQuery);
    }
    private ArrayList<Student> selectByQuery(String sqlQuery) {
        ArrayList<Student> students = new ArrayList<>();
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        try {
            while (rs.next()) {
                students.add(new Student(
                        rs.getLong("id"),
                        rs.getString("fio"),
                        rs.getString("sex"),
                        rs.getLong("id_group")
                ));
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return students;
    }
    public void insert(Student student){
        final String sqlRequest = String.format("insert into %s (id, fio, sex, id_group) " +
                        "VALUES (%d, '%s', '%s', '%d');",
                tableName, student.getId(), student.getFio(), student.getSex(), student.getId_group());
        db.executeRequest(sqlRequest);
    }
}
