package vedder.controllers;

import vedder.models.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

class MockQueries {
    public static final String tablesCreationSQL = "create table dieting_person\n" +
            "(\n" +
            "    name     varchar not null,\n" +
            "    id       uuid    not null\n" +
            "        constraint dieting_person_pk\n" +
            "            primary key,\n" +
            "    login    varchar not null,\n" +
            "    password varchar not null\n" +
            ");\n" +
            "\n" +
            "alter table dieting_person\n" +
            "    owner to postgres;\n" +
            "\n" +
            "create unique index \"dieting-person_id_uindex\"\n" +
            "    on dieting_person (id);\n" +
            "\n" +
            "create unique index \"dieting-person_login_uindex\"\n" +
            "    on dieting_person (login);\n" +
            "\t\n" +
            "----------------------------------------------------------------------\n" +
            "\t\n" +
            "create table dish\n" +
            "(\n" +
            "    name             varchar          not null\n" +
            "        constraint dish_pk\n" +
            "            primary key,\n" +
            "    calorie_per_100g double precision not null,\n" +
            "    mass_in_g        double precision\n" +
            ");\n" +
            "\n" +
            "alter table dish\n" +
            "    owner to postgres;\n" +
            "\n" +
            "create unique index dish_name_uindex\n" +
            "    on dish (name);\n" +
            "\t\n" +
            "----------------------------------------------------------------------\n" +
            "\t\n" +
            "create table ration\n" +
            "(\n" +
            "    id                timestamp not null\n" +
            "        constraint ration_pk\n" +
            "            primary key,\n" +
            "    dieting_person_id uuid\n" +
            "        constraint dieting_person_id_fk\n" +
            "            references dieting_person\n" +
            ");\n" +
            "\n" +
            "alter table ration\n" +
            "    owner to postgres;\n" +
            "\t\n" +
            "----------------------------------------------------------------------\n" +
            "\t\n" +
            "create table ration_dish\n" +
            "(\n" +
            "    ration_id timestamp    not null\n" +
            "        constraint ration_id_fk\n" +
            "            references ration,\n" +
            "    dish_name varchar not null\n" +
            "        constraint dish_name_fk\n" +
            "            references dish,\n" +
            "    constraint ration_dish_pk\n" +
            "        primary key (ration_id, dish_name)\n" +
            ");\n" +
            "\n" +
            "alter table ration_dish\n" +
            "    owner to postgres;";

    public static final String tablesFillingSQL = "INSERT INTO web_lab_schema.dieting_person (name, id, login, password) VALUES ('Jo', 'a8c8bc36-ec0b-434c-a3b8-5695ab332afd', 'jojo', '123');\n" +
            "INSERT INTO web_lab_schema.dieting_person (name, id, login, password) VALUES ('Adam', '702e39f9-64bb-48a8-a4da-fec007c032d9', 'adamantium', '123');\n" +
            "INSERT INTO web_lab_schema.dieting_person (name, id, login, password) VALUES ('Jerry', '35a0f6b8-be03-4bf9-bd75-276f7c7dec14', 'jerk', '123');\n" +
            "INSERT INTO web_lab_schema.dieting_person (name, id, login, password) VALUES ('Russ', '6f0d311b-28fc-4935-b8e6-4e7eb53a6288', 'justruss', '123');\n" +
            "INSERT INTO web_lab_schema.dieting_person (name, id, login, password) VALUES ('Mary', '005d02cc-2ef9-4737-9b66-2a9a2d38452f', 'maryangel', '123');\n" +
            "\n" +
            "----------------------------------------------------------------------\n" +
            "\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('borsch', 200, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('borsch Ukrainian', 300, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('baked cabbage', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('cabbage soup', 300, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('cabbage soup with fish', 350, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('cabbage soup vegetarian', 150, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('pasta with shrooms', 300, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('pasta with chicken', 350, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('fish soup', 500, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('bisque', 300, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('cheese soup', 300, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('chicken kebab', 300, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('pig kebab', 500, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('cow kebab', 500, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('fish kebab', 500, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('chicken meatballs', 500, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('cow meatballs', 500, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('fish meatballs', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('steamed veggies', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('baked veggies', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('fries', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('chips', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('baked potato', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('soya meatballs', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('vatrushka', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('king vatrushka', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('Hungarian', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('Napoleon cake', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('Prague cake', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('chocolate cake', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('count ruins cake', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('chocolate muffins', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('pistachio cake', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('cheese pancakes', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('red caviar pancakes', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('black caviar pancakes', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('triple-layer cake', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('cabbage pie', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('apple pie', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('meat pie', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('cherry pie', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('snikers cake', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('tiramisu cake', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('caramel cheesecake', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('lemon cheesecake', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('chocolate cheesecake', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('omelette', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('lentils', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('rice', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('buckwheat', 400, 200);\n" +
            "\n" +
            "----------------------------------------------------------------------\n" +
            "\n" +
            "INSERT INTO web_lab_schema.ration (id, dieting_person_id) VALUES ('2020-03-20 20:58:45.000000', 'a8c8bc36-ec0b-434c-a3b8-5695ab332afd');\n" +
            "INSERT INTO web_lab_schema.ration (id, dieting_person_id) VALUES ('2020-03-20 20:58:46.000000', 'a8c8bc36-ec0b-434c-a3b8-5695ab332afd');\n" +
            "INSERT INTO web_lab_schema.ration (id, dieting_person_id) VALUES ('2020-03-20 20:58:47.000000', '702e39f9-64bb-48a8-a4da-fec007c032d9');\n" +
            "INSERT INTO web_lab_schema.ration (id, dieting_person_id) VALUES ('2020-03-20 20:58:48.000000', '702e39f9-64bb-48a8-a4da-fec007c032d9');\n" +
            "INSERT INTO web_lab_schema.ration (id, dieting_person_id) VALUES ('2020-03-20 20:58:49.000000', '35a0f6b8-be03-4bf9-bd75-276f7c7dec14');\n" +
            "INSERT INTO web_lab_schema.ration (id, dieting_person_id) VALUES ('2020-03-20 20:58:50.000000', '35a0f6b8-be03-4bf9-bd75-276f7c7dec14');\n" +
            "INSERT INTO web_lab_schema.ration (id, dieting_person_id) VALUES ('2020-03-20 20:58:51.000000', '6f0d311b-28fc-4935-b8e6-4e7eb53a6288');\n" +
            "INSERT INTO web_lab_schema.ration (id, dieting_person_id) VALUES ('2020-03-20 20:58:52.000000', '6f0d311b-28fc-4935-b8e6-4e7eb53a6288');\n" +
            "INSERT INTO web_lab_schema.ration (id, dieting_person_id) VALUES ('2020-03-20 20:58:53.000000', '005d02cc-2ef9-4737-9b66-2a9a2d38452f');\n" +
            "INSERT INTO web_lab_schema.ration (id, dieting_person_id) VALUES ('2020-03-20 20:58:54.000000', '005d02cc-2ef9-4737-9b66-2a9a2d38452f');\n" +
            "\n" +
            "----------------------------------------------------------------------\n" +
            "\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:45.000000', 'baked cabbage');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:47.000000', 'cow kebab');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:49.000000', 'chips');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:46.000000', 'cabbage soup vegetarian');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:45.000000', 'borsch');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:50.000000', 'Hungarian');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:47.000000', 'cheese soup');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:51.000000', 'red caviar pancakes');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:50.000000', 'Napoleon cake');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:48.000000', 'chicken meatballs');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:47.000000', 'fish kebab');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:52.000000', 'triple-layer cake');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:50.000000', 'Prague cake');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:48.000000', 'cow meatballs');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:50.000000', 'chocolate cake');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:53.000000', 'snikers cake');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:47.000000', 'pig kebab');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:53.000000', 'cherry pie');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:51.000000', 'count ruins cake');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:52.000000', 'black caviar pancakes');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:47.000000', 'chicken kebab');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:51.000000', 'chocolate muffins');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:51.000000', 'cheese pancakes');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:48.000000', 'baked veggies');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:53.000000', 'tiramisu cake');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:49.000000', 'fries');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:53.000000', 'lemon cheesecake');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:54.000000', 'chocolate cheesecake');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:46.000000', 'pasta with shrooms');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:48.000000', 'fish meatballs');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:54.000000', 'rice');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:46.000000', 'pasta with chicken');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:48.000000', 'steamed veggies');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:46.000000', 'fish soup');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:49.000000', 'vatrushka');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:52.000000', 'meat pie');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:46.000000', 'bisque');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:45.000000', 'cabbage soup');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:50.000000', 'king vatrushka');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:49.000000', 'soya meatballs');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:53.000000', 'caramel cheesecake');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:54.000000', 'omelette');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:45.000000', 'borsch Ukrainian');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:45.000000', 'cabbage soup with fish');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:51.000000', 'pistachio cake');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:54.000000', 'lentils');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:52.000000', 'apple pie');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:49.000000', 'baked potato');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:52.000000', 'cabbage pie');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:54.000000', 'buckwheat');";

}

class MockValues {
    public static final Timestamp rationId = new Timestamp(new java.util.Date(1584723525000L).getTime()); // 1584723525000L == "2020-03-20 20:58:45.000000"
    public static final Ration ration = new Ration(rationId);
    public static final DietingPerson user = new DietingPerson(
            "jojo",
            "123",
            "Jo",
            UUID.fromString("a8c8bc36-ec0b-434c-a3b8-5695ab332afd")
    );
    public static final Dish dish = new Dish("buckwheat", 400, 200);
}

public class DAO {
    // also possible: statement.execute("SET CURRENT_SCHEMA=web-lab.web_lab_schema");
    private static final String url = "jdbc:postgresql:web-lab"; // ?currentSchema=web-lab.web_lab_schema";
    private static final String dbUsername = "postgres";
    private static final String dbPassword = "12345";


    private Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, dbUsername, dbPassword);
    }

    public static void main(String ...args) throws SQLException, ClassNotFoundException {
        DAO manipulator = new DAO();
        // manipulator.createTables();
        // manipulator.fillTables();
        // manipulator.deleteRation(MockValues.ration);
        // manipulator.deleteDish(MockValues.dish);
        DietingPerson user = manipulator.getUser(MockValues.user.getLogin(), MockValues.user.getPassword());
        Ration ration = manipulator.getRation(MockValues.rationId);
        List<Ration> rations = manipulator.getUsersRations(MockValues.user);
        List<Dish> dishes = manipulator.getDishesByCalorie(100, 200);
        double mass = manipulator.getMassOfAllRationDishes(MockValues.rationId);

        System.out.println(user);
        System.out.println(dishes);
        System.out.println(ration);
        System.out.println(rations);
        System.out.println(mass);
    }

    public void createTables() throws SQLException, ClassNotFoundException {
        Connection connection = this.connect();
        Statement statement = connection.createStatement();
        statement.execute(MockQueries.tablesCreationSQL);
        System.out.println("the tables have been created");
        statement.close();
        connection.close();
    }

    public void fillTables() throws SQLException, ClassNotFoundException {
        Connection connection = this.connect();
        Statement statement = connection.createStatement();
        statement.executeUpdate(MockQueries.tablesFillingSQL);
        System.out.println("the tables have been filled");
        statement.close();
        connection.close();
    }

    public void deleteRation(Ration ration) throws SQLException, ClassNotFoundException {
        Timestamp rationId = ration.getId();
        Connection connection = this.connect();
        connection.setAutoCommit(false);
        PreparedStatement statement = null;
        Savepoint savepoint = connection.setSavepoint();
        try {
            statement = connection.prepareStatement("DELETE FROM web_lab_schema.ration_dish WHERE ration_id = ?");
            statement.setTimestamp(1, rationId);
            statement.executeUpdate();
            statement = connection.prepareStatement("DELETE FROM web_lab_schema.ration WHERE id = ?");
            statement.setTimestamp(1, rationId);
            statement.executeUpdate();
            connection.commit();
            System.out.println("the ration has been deleted");
            statement.close();
            connection.releaseSavepoint(savepoint);
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback(savepoint);
        } finally {
            connection.close();
        }

    }

    public void addDish(Dish dish, Timestamp rationId) throws SQLException, ClassNotFoundException {
        Connection connection = this.connect();
        connection.setAutoCommit(false);
        PreparedStatement statement = null;
        Savepoint savepoint = connection.setSavepoint();
        try {
            statement = connection.prepareStatement("INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES (?, ?, ?)");
            statement.setString(1, dish.getName());
            statement.setDouble(2, dish.getCaloriePer100g());
            statement.setDouble(3, dish.getMassInG());
            statement.executeUpdate();

            statement = connection.prepareStatement("INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES (?, ?)");
            statement.setTimestamp(1, rationId);
            statement.setString(2, dish.getName());
            statement.executeUpdate();
            connection.commit();
            System.out.println("a new dish has been added");
            statement.close();
            connection.releaseSavepoint(savepoint);
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback(savepoint);
        } finally {
            connection.close();
        }
    }

    public void deleteDish(Dish dish) throws SQLException, ClassNotFoundException {
        String dishName = dish.getName();
        Connection connection = this.connect();
        connection.setAutoCommit(false);
        PreparedStatement statement = null;
        Savepoint savepoint = connection.setSavepoint();
        try {
            statement = connection.prepareStatement("DELETE FROM web_lab_schema.ration_dish WHERE dish_name LIKE ?");
            statement.setString(1, dishName);
            statement.executeUpdate();
            statement = connection.prepareStatement("DELETE FROM web_lab_schema.dish WHERE name LIKE ?");
            statement.setString(1, dishName);
            statement.executeUpdate();
            connection.commit();
            System.out.println("the dish has been deleted");
            statement.close();
            connection.releaseSavepoint(savepoint);
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback(savepoint);
        } finally {
            connection.close();
        }
    }

    public DietingPerson getUser(String login, String password) throws SQLException, ClassNotFoundException {
        Connection connection = this.connect();
        PreparedStatement statement = connection.prepareStatement("SELECT name, id FROM web_lab_schema.dieting_person WHERE login = ? AND password = ?");
        statement.setString(1, login);
        statement.setString(2, password);
        ResultSet userRough = statement.executeQuery();
        DietingPerson user = null;
        if (userRough.next()) {
            String name = userRough.getString(1);
            UUID id = (UUID)userRough.getObject(2);
            user = new DietingPerson(login, password, name, id);
        }
        statement.close();
        connection.close();
        return user;
    }

    @Deprecated
    public boolean checkUserExistence(String login, String password) throws SQLException, ClassNotFoundException {
        Connection connection = this.connect();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM web_lab_schema.dieting_person WHERE login = ? AND password = ?");
        statement.setString(1, login);
        statement.setString(2, password);
        ResultSet userExistRough = statement.executeQuery();
        boolean userExist = userExistRough.next();
        statement.close();
        connection.close();
        return userExist;
    }

    public Ration getRation(Timestamp rationId) throws SQLException, ClassNotFoundException {
        Connection connection = this.connect();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM web_lab_schema.dish WHERE name IN(SELECT dish_name FROM web_lab_schema.ration_dish WHERE ration_id = ? )");
        statement.setTimestamp(1, rationId);
        ResultSet dishesRough = statement.executeQuery();
        Ration ration = new Ration(rationId);
        while (dishesRough.next()) {
            String name = dishesRough.getString(1);
            double caloriePer100g = dishesRough.getDouble(2);
            double massInG = dishesRough.getDouble(3);
            ration.add(new Dish(name, caloriePer100g, massInG));
        }
        statement.close();
        connection.close();
        return ration;
    }

    public List<Ration> getUsersRations(DietingPerson user) throws SQLException, ClassNotFoundException {
        UUID userId = user.getId();
        Connection connection = this.connect();
        PreparedStatement statement = connection.prepareStatement("SELECT id FROM web_lab_schema.ration WHERE dieting_person_id = ?");
        statement.setObject(1, userId);
        ResultSet rationIdsRough = statement.executeQuery();
        List<Ration> rations = new LinkedList<>();
        while (rationIdsRough.next()) {
            rations.add(this.getRation(rationIdsRough.getTimestamp(1)));
        }
        statement.close();
        connection.close();
        return rations;
    }

    public List<Dish> getDishesByCalorie(double from, double to) throws SQLException, ClassNotFoundException {
        Connection connection = this.connect();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM web_lab_schema.dish WHERE calorie_per_100g BETWEEN ? AND ?");
        statement.setDouble(1, from);
        statement.setDouble(2, to);
        ResultSet dishesRough = statement.executeQuery();
        List<Dish> dishes = new LinkedList<>();
        while (dishesRough.next()) {
            String name = dishesRough.getString(1);
            double caloriePer100g = dishesRough.getDouble(2);
            double massInG = dishesRough.getDouble(3);
            dishes.add(new Dish(name, caloriePer100g, massInG));
        }
        statement.close();
        connection.close();
        return dishes;
    }

    public double getMassOfAllRationDishes(Timestamp rationId) throws SQLException, ClassNotFoundException {
        Connection connection = this.connect();
        PreparedStatement statement = connection.prepareStatement("SELECT SUM(mass_in_g) FROM web_lab_schema.dish WHERE name IN(SELECT dish_name FROM web_lab_schema.ration_dish WHERE ration_id = ? )");
        statement.setTimestamp(1, rationId);
        ResultSet sumRough = statement.executeQuery();
        double sum = sumRough.next() ? sumRough.getDouble(1) : null;
        statement.close();
        connection.close();
        return sum;
    }
}
