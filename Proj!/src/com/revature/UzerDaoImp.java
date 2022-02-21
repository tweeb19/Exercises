package com.revature;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UzerDaoImp implements UzerDao{

    Connection connection;

    public  UzerDaoImp(){
        this.connection = ConnectionFactory.getConnection();
    }


    @Override
    public void addUzer(Bankuser uzers) throws SQLException {

        String sql = "insert into employee (name, email) values (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, uzers.getName());
        preparedStatement.setString(2, uzers.getEmail());
        int count = preparedStatement.executeUpdate();
        if(count > 0){
            System.out.println("Employee saved");
        }else{
            System.out.println("Oops!, something went wrong");;
        }

    }

    @Override
    public void updateUzer(Bankuser uzers) throws SQLException {

        Statement stmnt = connection.createStatement();
        String sqlupdate = "Update customer set cbalance=cbalance+100 WHERE accnt_id = 323";

        stmnt.executeUpdate(sqlupdate);
        System.out.println("\nDatabase updated successfully");

    }

    @Override
    public void deleteUzer(Bankuser uzers) throws SQLException {

        Statement stmnt = connection.createStatement();
        String sqlupdate = "Delete customer email = 'z@gmail.com' WHERE accnt_id = 1";

        stmnt.executeUpdate(sqlupdate);
        System.out.println("Database updated successfully");

    }

    @Override
    public List<Bankuser> getUzers() throws SQLException {

        String sql = "select * from customer";

        Statement stmnt = connection.createStatement();
        ResultSet result = stmnt.executeQuery(sql);

        List<Bankuser> allUzers = new ArrayList<Bankuser>();

        while(result.next()){

            int account_id = result.getInt("is");
            String name = result.getString("name");
            String email = result.getString("email");

            allUzers.add(new Bankuser(account_id, name, email));
        }

        allUzers.forEach(System.out::println);
        return allUzers;

    }
    @Override
    public Bankuser getUzerById(int accnt_id){

        return getUzerById(accnt_id);}
}
