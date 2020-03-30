import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

// TODO fill queries
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
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('борщ русский', 200, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('борщ украинский', 300, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('солянка', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('щи', 300, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('щи с рыбой', 350, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('щи вегетарианские', 150, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('лапша с грибами', 300, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('лапша с курицей', 350, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('уха', 500, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('суп-пюре картофельный', 300, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('сырный суп', 300, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('шашлык куриный', 300, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('шашлык свиной', 500, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('шашлык говяжий', 500, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('шашлык рыбный', 500, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('котлеты куриные', 500, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('котлеты говяжие', 500, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('котлеты рыбные', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('тушеные овощи', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('печеные овощи', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('картошка по-деревенски', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('картошка фри', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('картофель в мундире', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('котлеты соевые', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('ватрушка обыкновенная', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('королевская ватрушка', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('венгерка', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('торт наполеон', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('торт прага', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('муссовый шоколадный торт', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('торт графские развалины', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('шоколадные маффины', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('фисташковый торт', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('сырники', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('блины с красной икрой', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('блины с черной икрой', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('трехслойный пирог', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('пирог с капустой', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('пирог с красной рыбой', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('пирог с мясом', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('торт птичье молоко', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('торт сникерс', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('торт тирамису', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('чизкейк карамельный', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('чизкейк лимонный', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('чизкейк шоколадный', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('селедка под шубой', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('чечевица', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('рис', 400, 200);\n" +
            "INSERT INTO web_lab_schema.dish (name, calorie_per_100g, mass_in_g) VALUES ('гречка', 400, 200);\n" +
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
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:45.000000', 'борщ русский');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:45.000000', 'борщ украинский');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:45.000000', 'солянка');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:45.000000', 'щи');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:45.000000', 'щи с рыбой');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:46.000000', 'щи вегетарианские');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:46.000000', 'лапша с грибами');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:46.000000', 'лапша с курицей');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:46.000000', 'уха');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:46.000000', 'суп-пюре картофельный');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:47.000000', 'сырный суп');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:47.000000', 'шашлык куриный');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:47.000000', 'шашлык свиной');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:47.000000', 'шашлык говяжий');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:47.000000', 'шашлык рыбный');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:48.000000', 'котлеты куриные');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:48.000000', 'котлеты говяжие');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:48.000000', 'котлеты рыбные');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:48.000000', 'тушеные овощи');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:48.000000', 'печеные овощи');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:49.000000', 'картошка по-деревенски');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:49.000000', 'картошка фри');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:49.000000', 'картофель в мундире');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:49.000000', 'котлеты соевые');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:49.000000', 'ватрушка обыкновенная');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:50.000000', 'королевская ватрушка');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:50.000000', 'венгерка');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:50.000000', 'торт наполеон');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:50.000000', 'торт прага');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:50.000000', 'муссовый шоколадный торт');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:51.000000', 'торт графские развалины');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:51.000000', 'шоколадные маффины');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:51.000000', 'фисташковый торт');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:51.000000', 'сырники');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:51.000000', 'блины с красной икрой');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:52.000000', 'блины с черной икрой');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:52.000000', 'трехслойный пирог');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:52.000000', 'пирог с капустой');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:52.000000', 'пирог с красной рыбой');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:52.000000', 'пирог с мясом');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:53.000000', 'торт птичье молоко');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:53.000000', 'торт сникерс');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:53.000000', 'торт тирамису');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:53.000000', 'чизкейк карамельный');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:53.000000', 'чизкейк лимонный');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:54.000000', 'чизкейк шоколадный');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:54.000000', 'селедка под шубой');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:54.000000', 'чечевица');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:54.000000', 'рис');\n" +
            "INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES ('2020-03-20 20:58:54.000000', 'гречка');";

}

public class DBManipulator {
    // also possible: statement.execute("SET CURRENT_SCHEMA=web-lab.web_lab_schema");
    private static final String url = "jdbc:postgresql:web-lab?currentSchema=web-lab.web_lab_schema";
    private static final String user = "postgres";
    private static final String password = "12345";


    private Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, user, password);
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
        Date rationId = ration.getId();
        Connection connection = this.connect();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM web_lab_schema.ration_dish WHERE ration_id =" + rationId);
        statement.executeUpdate("DELETE FROM web_lab_schema.ration WHERE id =" + rationId);
        System.out.println("the ration has been deleted");
        statement.close();
        connection.close();
    }

    public void deleteDish(Dish dish) throws SQLException, ClassNotFoundException {
        String dishName = dish.getName();
        Connection connection = this.connect();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM web_lab_schema.ration_dish WHERE dish_name LIKE" + dishName);
        statement.executeUpdate("DELETE FROM web_lab_schema.dish WHERE name LIKE" + dishName);
        System.out.println("the dish has been deleted");
        statement.close();
        connection.close();
    }

    public boolean checkUserExistance(String login, String password) throws SQLException, ClassNotFoundException {
        Connection connection = this.connect();
        Statement statement = connection.createStatement();
        ResultSet userExist = statement.executeQuery("SELECT * FROM web_lab_schema.dieting_person WHERE login = " + login + " AND password = " + password);
        return userExist.next();
    }

    public Ration getRationById(Date rationId) throws SQLException, ClassNotFoundException {
        Connection connection = this.connect();
        Statement statement = connection.createStatement();
        ResultSet dishNamesRough = statement.executeQuery("SELECT dish_name FROM web_lab_schema.ration_dish WHERE ration_id = " + rationId);
        /*LinkedList<String> dishNames = new LinkedList<>();
        while(dishNamesRough.next()) {
            dishNames.add(dishNamesRough.getString(1));
        }*/
        ResultSet dishesRough = statement.executeQuery("SELECT * FROM web_lab_schema.dish WHERE name IN(" + dishNamesRough + ")");
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
        Statement statement = connection.createStatement();
        ResultSet rationIdsRough = statement.executeQuery("SELECT id FROM web_lab_schema.ration WHERE dieting_person_id = " + userId);
        List<Ration> rations = new LinkedList<>();
        while (rationIdsRough.next()) {
            rations.add(this.getRationById(rationIdsRough.getDate(1)));
        }
        statement.close();
        connection.close();
        return rations;
    }

    public List<Dish> getDishesByCalorie(double from, double to) throws SQLException, ClassNotFoundException {
        Connection connection = this.connect();
        Statement statement = connection.createStatement();
        ResultSet dishesRough = statement.executeQuery("SELECT * FROM web_lab_schema.dish WHERE calorie_per_100g BETWEEN " + from + " AND " + to);
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

    public double getMassOfAllRationDishes(Date rationId) throws SQLException, ClassNotFoundException {
        Connection connection = this.connect();
        Statement statement = connection.createStatement();
        ResultSet sumRough = statement.executeQuery("SELECT SUM(mass_in_g) FROM web_lab_schema.dish WHERE dish_name IN(\n" +
                "\tSELECT dish_name FROM web_lab_schema.ration_dish WHERE ration_id = " + rationId + ")");
        double sum = sumRough.next() ? sumRough.getDouble(1) : null;
        statement.close();
        connection.close();
        return sum;
    }
}
