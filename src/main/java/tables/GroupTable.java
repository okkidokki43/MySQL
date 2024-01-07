package tables;

import db.MySQLConnector;
import objects.Group;
import objects.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class GroupTable extends AbsTable{

    private final static String TABLE_NAME = "`group`";
    public GroupTable() {
        super(TABLE_NAME);
        columns = new HashMap<>();
        columns.put("id", "bigint PRIMARY KEY AUTO_INCREMENT");
        columns.put("name", "varchar(100)");
        columns.put("id_curator", "double");
        create();
    }
    public ArrayList<Group> selectAll(){
        String sqlQuery = String.format("SELECT * FROM %s", tableName);
        return selectByQuery(sqlQuery);
    }
    public void selectCurator() throws SQLException {
        String sqlQuery = "select group.name, curator.fio from `group`," +
                " curator where group.id_curator = curator.id;";
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        while (rs.next()) {
            System.out.println("Ведущий куратор группы - " + rs.getString(1) + " : "
                    + rs.getString(2));
        }
    }
    public void updateCurator(Group group){
        String sqlQuery = "update `group` set id_curator = 3 where id = '1';";
        db.executeRequest(sqlQuery);
        System.out.println("");
    }
    private ArrayList<Group> selectByQuery(String sqlQuery) {
        ArrayList<Group> groups = new ArrayList<>();
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        try {
            while (rs.next()) {
                groups.add(new Group(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getLong("id_curator")
                ));
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return groups;
    }
    public void insert(Group group){
        final String sqlRequest = String.format("insert into %s (id, name, id_curator) " +
                        "VALUES (%d, '%s', '%d');",
                tableName, group.getId(), group.getName(), group.getId_curator());
        db.executeRequest(sqlRequest);
    }
}
