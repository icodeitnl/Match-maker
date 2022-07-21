package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.*;

public class MuzikantDAO extends AbstractDAO {

	public MuzikantDAO(DBaccess dBaccess) {
		super(dBaccess);
	}

	// Muzikant toevoegen
	public void slaMuzikantOp (Muzikant mpMuzikant) {
		String sql = "INSERT INTO Muzikant(artistenaam,ervaring,instrument) VALUES (?,?,?)";
		//String artistenaam, int ervaring, String instrument
		try {
			setupPreparedStatement(sql);
			preparedStatement.setString(1, mpMuzikant.getArtistenaam());
			preparedStatement.setInt(2, mpMuzikant.getErvaring());
			preparedStatement.setString(3, mpMuzikant.getInstrument());
			executeManipulateStatement();
		} catch (SQLException sqlFout) {
			System.out.println("SQL fout " + sqlFout.getMessage());
		}
	}

    // Toon muzikanten
	public ArrayList<Muzikant> getMuzikanten() {
		ArrayList<Muzikant> resultaatLijst = new ArrayList<>();
		String sql = "SELECT * FROM Muzikant";
		Muzikant muzikant;
		try {
			setupPreparedStatement(sql);
			ResultSet resultSet = executeSelectStatement();
			while (resultSet.next()) {
				//int muzikantId,String artistenaam, int ervaring, String instrument
				String artistenaam = resultSet.getString("artistenaam");
				int ervaring = resultSet.getInt("ervaring");
				String instrument = resultSet.getString("instrument");
				muzikant = new Muzikant(artistenaam, ervaring, instrument);
				resultaatLijst.add(muzikant);
			}
		} catch (SQLException sqlFout) {
			System.out.println("SQL fout " + sqlFout.getMessage());
		}
		return resultaatLijst;
	}
	public ArrayList<Muzikant>  getMuzikantByInstrument(String instrument) {
		ArrayList<Muzikant> muzikantByInstrument = new ArrayList<>();
		String sql = "SELECT * FROM Muzikant WHERE instrument= ?";
		try {
			setupPreparedStatement(sql);
			preparedStatement.setString(1, instrument);
			ResultSet resultSet = executeSelectStatement();
			if (resultSet.next()) {
				String artistenaam = resultSet.getString("artistenaam");
				int ervaring = resultSet.getInt("ervaring");
				muzikantByInstrument.add(new Muzikant(artistenaam, ervaring, instrument));
			} else {
				System.out.println("Muzikant met dit Instrument bestaat niet");
			}
		} catch (SQLException sqlFout) {
			System.out.println("SQL fout " + sqlFout.getMessage());
		}
		return muzikantByInstrument;
	}

}
