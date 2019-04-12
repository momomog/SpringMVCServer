package system.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dbactions.DbConnectData;
import dbactions.JsonParser.DataForServlet;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
@Repository
public class LastUsedDao implements DbConnectData {

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

    public String usedsUpdate() throws NullPointerException {
        try {
            //dfs.dataInitilization(data);
            PreparedStatement preparedStatement = connection.prepareStatement("select * from useds ORDER BY id");
            ResultSet resultSet = preparedStatement.executeQuery();
            sb.append("{\"useds\":[");
            while (resultSet.next()) {
                map.put("id", String.valueOf(resultSet.getInt("id")));
                map.put("name", resultSet.getString("name"));
                sb.append(mapper.writeValueAsString(map)).append(",");
                map.clear();
            }
            sb.setLength(sb.length() - 1);
            sb.append("], \"success\": true,\"message\": \"Данные обновлены!\" }");
            connection.close();
        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
        }
        if (sb.toString().contains("name")) {
            return sb.toString();
        } else {
            return "{\"success\": true,\"message\": \"Данные обновлены!\"}";
        }
    }

    public String addUsedToDB(String data) {
        try {
            dfs.dataInitilization(data);
            PreparedStatement preparedStatement = connection.prepareStatement("select name from useds");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String usedFromDB = resultSet.getString("name");
                if (dfs.getName().equals(usedFromDB)) {
                    return "{\"success\": false,\"message\": \"Данный интервал уже зарегестрирован!\"}";
                }
            }
            preparedStatement = connection.prepareStatement("insert into useds (name) VALUES (?)");
            preparedStatement.setString(1, dfs.getName());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "{\"success\": true,\"message\": \"Навык добавлен!\"}";
    }

    public String deleteUsedFromDB(String data) {
        try {
            dfs.dataInitilization(data);
            PreparedStatement preparedStatement = connection.prepareStatement("delete from useds * where id = ?");
            preparedStatement.setInt(1, dfs.getId());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            return "{\"success\": false,\"message\": \"Вы не можете удалить данный интервал, так как он используется в таблице знаний сотрудников!\"}";
        }
        return "{\"success\": true,\"message\": \"Интервал удален!\"}";
    }

    public String updateUseddataToDB(String data) {
        try {
            dfs.dataInitilization(data);
            PreparedStatement preparedStatement = connection.prepareStatement("update useds set name = ? where id = ?");
            preparedStatement.setString(1, dfs.getName());
            preparedStatement.setInt(2, dfs.getId());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "{\"success\": true,\"message\": \"Данные изменены!\"}";
    }
}
