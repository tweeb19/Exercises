package com.revature;

import java.util.List;
import java.sql.SQLException;

public interface UzerDao {


        void addUzer(Bankuser uzers) throws SQLException;
        void updateUzer(Bankuser uzers) throws SQLException;
        void deleteUzer(Bankuser uzers) throws SQLException;

        //collections list
        List<Bankuser> getUzers() throws SQLException;
        Bankuser getUzerById(int accnt_id) throws SQLException;
    }

