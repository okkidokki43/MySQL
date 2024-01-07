package tables;

import objects.Curator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class CuratorTable extends AbsTable{
    private final static String TABLE_NAME = "curator";
    public CuratorTable() {
        super(TABLE_NAME);
        columns = new HashMap<>();
        columns.put("id", "bigint PRIMARY KEY AUTO_INCREMENT");
        columns.put("fio", "varchar(200)");
        create();
    }
    public ArrayList<Curator> selectAll(){
        String sqlQuery = String.format("SELECT * FROM %s", tableName);
        return selectByQuery(sqlQuery);
    }
    private ArrayList<Curator> selectByQuery(String sqlQuery) {
        ArrayList<Curator> curators = new ArrayList<>();
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        try {
            while (rs.next()) {
                curators.add(new Curator(
                        rs.getLong("id"),
                        rs.getString("fio")
                ));
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return curators;
    }
    public void insert(Curator curator){
        final String sqlRequest = String.format("insert into %s " +
                        "(id, fio) VALUES (%d, '%s');",
                tableName, curator.geIid(), curator.getFio());
        db.executeRequest(sqlRequest);
    }
}
