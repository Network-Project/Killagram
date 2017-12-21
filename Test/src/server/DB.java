package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

class DB {
	private final static DB db = new DB();
	private Connection con;

	public Connection getConnection() {
		return con;
	}

	private DB() {
		con = null;
		String url = "jdbc:mysql://localhost:3306/killagram";
		String user = "root";
		String pass = "12345";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DB Connect success");
	}

	public static DB getInstance() {
		return db;
	}

	public boolean checkID(String id) {
		String sql = "select * from 사용자 where id=?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet result = pstmt.executeQuery();
			if (result.next())
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public User checkUser(String id, String password) {
		String sql = "select * from 사용자 where id=? and password=?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			ResultSet result = pstmt.executeQuery();
			if (result.next())
				return new User(result.getString(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getString(5), result.getFloat(6), result.getFloat(7), result.getFloat(8));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean insertUser(User user) {
		String sql = "INSERT INTO 사용자 (id,password,name,age,gender,height,weight,goal_weight) VALUES"
				+ "(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setInt(4, user.getAge());
			pstmt.setString(5, user.getGender());
			pstmt.setFloat(6, user.getHeight());
			pstmt.setFloat(7, user.getWeight());
			pstmt.setFloat(8, user.getGoal_weight());
			int result = pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Vector<Food> getFoodList(String tableName) {
		String sql = "SELECT * FROM " + tableName;
		Vector<Food> foodList = new Vector<Food>();
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			while (result.next()) {
				foodList.add(new Food(tableName, result.getString(1), result.getString(2), result.getFloat(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return foodList;
	}

	public Vector<IntakeInfo> getIntakeInfoList(String id) {
		String sql = "SELECT * FROM 음식섭취현황 WHERE id=? and 날짜 = ?";
		Vector<IntakeInfo> infoList = new Vector<IntakeInfo>();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setDate(2, java.sql.Date.valueOf(Server.getNowDate()));
			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				infoList.add(new IntakeInfo(result.getString(1), result.getDate(2).toString(), result.getString(3),
						result.getString(4), result.getString(5), result.getInt(6), result.getFloat(7)));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return infoList;
	}

	public boolean insertIntakeInfo(IntakeInfo info) {
		String sql = "INSERT INTO 음식섭취현황 (id,날짜,음식종류,음식이름,단위,수량,섭취칼로리) VALUES" + "(?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, info.getId());
			pstmt.setDate(2, java.sql.Date.valueOf(info.getDate()));
			pstmt.setString(3, info.getType());
			pstmt.setString(4, info.getName());
			pstmt.setString(5, info.getUnit());
			pstmt.setInt(6, info.getQuantity());
			pstmt.setFloat(7, info.getKal());
			int result = pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Vector<Exercise> getExerciseList() {
		String sql = "SELECT * FROM 운동"; // 테이블이름
		Vector<Exercise> exerciseList = new Vector<Exercise>();
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			while (result.next()) {
				exerciseList.add(new Exercise(result.getString(1), result.getFloat(2)));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exerciseList;
	}

	public Vector<ExerciseInfo> getExerciseInfoList(String id) {
		String sql = "SELECT * FROM 운동현황 WHERE id=? and 날짜=? ";
		Vector<ExerciseInfo> infoList = new Vector<ExerciseInfo>();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setDate(2, java.sql.Date.valueOf(Server.getNowDate()));
			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				infoList.add(new ExerciseInfo(result.getString(1), result.getDate(2).toString(), result.getString(3),
						result.getFloat(4), result.getFloat(5)));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return infoList;
	}

	public boolean insertExerciseInfo(ExerciseInfo info) {
		String sql = "INSERT INTO 운동현황 (id,날짜,운동이름,시간,소모칼로리) VALUES" + "(?,?,?,?,?)";
		try {
			PreparedStatement pstmt;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, info.getId());
			pstmt.setDate(2, java.sql.Date.valueOf(info.getDate()));
			pstmt.setString(3, info.getName());
			pstmt.setFloat(4, info.getTime());
			pstmt.setFloat(5, info.getKal());
			int result = pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public float getMET(String name) {
		String sql = "SELECT MET FROM 운동 WHERE 이름 = ?";
		try {
			PreparedStatement pstmt;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet result = pstmt.executeQuery();
			while (result.next())
				return result.getFloat(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public float getKal(String name) {
		String sql = "SELECT MET FROM 운동 WHERE 이름 = ?";
		try {
			PreparedStatement pstmt;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet result = pstmt.executeQuery();
			while (result.next())
				return result.getFloat(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public float getWeight(String id) {
		String sql = "SELECT weight FROM 사용자 WHERE id=?";
		try {
			PreparedStatement pstmt;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet result = pstmt.executeQuery();
			while (result.next())
				return result.getFloat(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public HashMap<String, Float> getRank() {
		HashMap<String, Float> eatMap = getEatKal();
		HashMap<String, Float> exMap = getExerciseKal();
		for (Map.Entry<String, Float> map : exMap.entrySet()) {
			if (eatMap.containsKey(map.getKey())) {
				float b = eatMap.get(map.getKey());
				float a = exMap.get(map.getKey());
				eatMap.replace(map.getKey(), a + b);
			} else {
				eatMap.put(map.getKey(), map.getValue());
			}
		}
		return eatMap;
	}

	public HashMap<String, Float> getEatKal() {
		String sql = "SELECT id, sum(섭취칼로리) FROM 음식섭취현황 WHERE 날짜 = ? GROUP BY id";
		HashMap<String, Float> eatMap = new HashMap<String, Float>();
		try {
			PreparedStatement pstmt;
			pstmt = con.prepareStatement(sql);
			pstmt.setDate(1, java.sql.Date.valueOf(Server.getNowDate()));
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				System.out.println(result.getString(1) +" " +String.valueOf(-result.getFloat(2)));
				eatMap.put(result.getString(1), -result.getFloat(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eatMap;
	}

	public HashMap<String, Float> getExerciseKal() {
		String sql = "SELECT id, sum(소모칼로리) FROM 운동현황 WHERE 날짜 = ? GROUP BY id";
		HashMap<String, Float> exMap = new HashMap<String, Float>();
		try {
			PreparedStatement pstmt;
			pstmt = con.prepareStatement(sql);
			pstmt.setDate(1, java.sql.Date.valueOf(Server.getNowDate()));
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				System.out.println(result.getString(1) +" " +String.valueOf(result.getFloat(2)));
				exMap.put(result.getString(1), result.getFloat(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exMap;
	}
}
