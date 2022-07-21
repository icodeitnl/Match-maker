package database;

import model.Session;
import java.sql.SQLException;

public class SessionDAO extends AbstractDAO {
    public SessionDAO(DBaccess dBaccess) {
        super(dBaccess);
    }

    public int slaSessionOp(Session session) {
        String sql = "INSERT INTO session (organisator, datum, tijdsduur, opname) VALUES (?, ?, ?, ?)";
        try {
            setupPreparedStatementWithKey(sql);
            preparedStatement.setString(1, session.getOrganisator().getArtistenaam());
            preparedStatement.setString(2, session.getDatum().toString());
            preparedStatement.setDouble(3, session.getDuur());
            preparedStatement.setBoolean(4, session.isOpgenomen());
            int id = executeInsertStatementWithKey();
            return id;
        } catch (SQLException e) {
            System.out.println("SQL fout: " + e.getMessage());
        }
        return 0;
    }
}
