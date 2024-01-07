import db.MySQLConnector;
import objects.Curator;
import objects.Group;
import objects.Student;
import tables.CuratorTable;
import tables.GroupTable;
import tables.StudentTable;
import java.sql.SQLException;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
      try {

            StudentTable studentTable = new StudentTable();
            ArrayList<Student> students = studentTable.selectAll();
            if (students.size() < 15) {

                studentTable.insert(new Student(1,"Ванечкин Сергей Иоганович", "М", 1));
                studentTable.insert(new Student(2,"Гуська Алла Вартановна", "Ж", 2));
                studentTable.insert(new Student(3,"Колесников Виктор Петрович", "М", 1));
                studentTable.insert(new Student(4,"Тупицина Ольга Ивановна", "Ж", 3));
                studentTable.insert(new Student(5 ,"Михайлов Степан Разболтович", "М", 2));
                studentTable.insert(new Student(6,"Петров Игорь Мухмутович", "М", 1));
                studentTable.insert(new Student(7,"Гематогенова Василиса Кондратьевна", "Ж", 2));
                studentTable.insert(new Student(8,"Перекуп Максим Максимович", "М", 1));
                studentTable.insert(new Student(9,"Курицын Сергей Витальевич", "М", 3));
                studentTable.insert(new Student(10,"Синицин Виктор Павлович", "М", 2));
                studentTable.insert(new Student(11,"Серьга Инга Мохнатовна", "Ж", 1));
                studentTable.insert(new Student(12,"Васильева Ирина Петровна", "Ж", 2));
                studentTable.insert(new Student(13,"Павлов Борис Базукович", "М", 1));
                studentTable.insert(new Student(14,"Дурында Виоллета Константиновна", "Ж", 3));
                studentTable.insert(new Student(15,"Романов Эдуард Тагирович ", "М", 2));
                students = studentTable.selectAll();
            }
            GroupTable groupTable = new GroupTable();
            ArrayList<Group> groups = groupTable.selectAll();
            if (groups.size() < 3) {
                groupTable.insert(new Group(1, "Кулибины", 1));
                groupTable.insert(new Group(2, "Сухаристы", 2));
                groupTable.insert(new Group(3, "Пивовары", 3));
                groupTable.insert(new Group(4, "Чипчисты", 4));
                groups = groupTable.selectAll();
            }

            CuratorTable curatorTable = new CuratorTable();
            ArrayList<Curator> curators = curatorTable.selectAll();
            if (curators.size() < 4) {
                curatorTable.insert(new Curator(1,"Дудин Егор Викторович"));
                curatorTable.insert(new Curator(2,"Курлыпа Тушёнка Малиновна"));
                curatorTable.insert(new Curator(3,"Федосов Иван Васильевич"));
                curatorTable.insert(new Curator(4,"Рогозина Светлана Владимировна"));
          }

            studentTable.selectAllStudent();

            studentTable.selectSumm();

            studentTable.selectWomen();

            groupTable.selectCurator();

            groups.get(2).setId_curator(3);
            groupTable.updateCurator(groups.get(2));

            studentTable.selectStudentGroup();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MySQLConnector.close();
        }
    }
}



