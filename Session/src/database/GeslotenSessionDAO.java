package database;

import model.*;
import java.sql.*;

public class GeslotenSessionDAO extends SessionDAO{

	public GeslotenSessionDAO(DBaccess dBaccess) {
		super(dBaccess);
	}

	public void slaGeslotenSessionOp (GeslotenSession mpGeslotensession) {
		int sessionId = super.slaSessionOp(mpGeslotensession);
		String sql = "INSERT INTO GeslotenSession(int sessionId, int maximumAantalMuzikanten,)"
				+ " VALUES(?,?)";
		try {
			setupPreparedStatement(sql);
			preparedStatement.setInt(1, sessionId);
			preparedStatement.setInt(2, mpGeslotensession.getAantalMuzikanten());
			executeManipulateStatement();
		} catch (SQLException exception) {
			System.out.println("SQL error: " + exception.getMessage());
		}
	}
}