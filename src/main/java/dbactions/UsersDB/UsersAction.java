package UsersDB;

import dbactions.JsonParser.DataForServlet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
public class UsersAction {
    private String login = "postgres";
    private String password = "1234";
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private Connection connection = null;
    private StringBuilder sb = null;
    private DataForServlet dfs;
    private ObjectMapper mapper = null;
    private Map<String, String> map;

    {
        try {
            connection = DriverManager.getConnection(url, login, password);
            Class.forName("org.postgresql.Driver");
            dfs = new DataForServlet();
            mapper = new ObjectMapper();
            map = new HashMap<>();
            sb = new StringBuilder();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String usersUpdate(String data) throws NullPointerException {
        try {
            dfs.dataInitilization(data);
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users ORDER BY id");
            ResultSet resultSet = preparedStatement.executeQuery();
            sb.append("{\"users\":[");
            while (resultSet.next()) {
                map.put("id", String.valueOf(resultSet.getInt("id")));
                map.put("name", resultSet.getString("name"));
                map.put("email", resultSet.getString("email"));
                map.put("phone", resultSet.getString("phone"));
                sb.append(mapper.writeValueAsString(map)).append(",");
                map.clear();
            }
            sb.setLength(sb.length() - 1);
            sb.append("], \"success\": true,\"message\": \"Данные обновлены!\" }");
            connection.close();
        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
        }
        if (sb.toString().contains("id")) {
            return sb.toString();
        } else {
            return "{\"success\": true,\"message\": \"Данные обновлены!\"}";
        }
    }

    public String addUserToDB(String data) {
        try {
            dfs.dataInitilization(data);
            PreparedStatement preparedStatement = connection.prepareStatement("select email from users");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String emailFromDB = resultSet.getString("email");
                if (dfs.getEmail().equals(emailFromDB)) {
                    return "{\"success\": false,\"message\": \"Пользователь с данной почтой уже зарегестрирован!\"}";
                }
            }

            preparedStatement = connection.prepareStatement("select phone from users");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String phoneFromDB = resultSet.getString("phone");
                if (dfs.getPhone().equals(phoneFromDB)) {
                    return "{\"success\": false,\"message\": \"Пользователь с данным телефоном уже зарегестрирован!\"}";
                }
            }

            preparedStatement = connection.prepareStatement("insert into users (name, email, phone) VALUES (?, ?, ?)");
            preparedStatement.setString(1, dfs.getName());
            preparedStatement.setString(2, dfs.getEmail());
            preparedStatement.setString(3, dfs.getPhone());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "{\"success\": true,\"message\": \"Пользователь добавлен!\"}";
    }

    public String deleteUserFromDB(String data) {
        try {
            dfs.dataInitilization(data);
            PreparedStatement preparedStatement = connection.prepareStatement("delete from personal * where name = ?");
            preparedStatement.setInt(1, dfs.getId());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("delete from users * where id = ?");
            preparedStatement.setInt(1, dfs.getId());
            preparedStatement.executeUpdate();


            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "{\"success\": true,\"message\": \"Пользователь удален!\"}";
    }

    public String updateUserdataToDB(String data) {
        try {
            dfs.dataInitilization(data);
            PreparedStatement preparedStatement = connection.prepareStatement("update users set name = ?, email = ?, phone = ? where id = ?");
            preparedStatement.setString(1, dfs.getName());
            preparedStatement.setString(2, dfs.getEmail());
            preparedStatement.setString(3, dfs.getPhone());
            preparedStatement.setInt(4, dfs.getId());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "{\"success\": true,\"message\": \"Данные изменены!\"}";
    }
}
