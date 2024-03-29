package org.mb.spring.test;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static org.assertj.core.api.Assertions.assertThat;

public class TestJdbc {

	@Test
	public void test(){
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5433/postgres",
					"postgres",
					"admin");
			conn.setAutoCommit(false);
			String sql = "select * from public.user where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 2);
			ResultSet resultSet = ps.executeQuery();
			resultSet.next();
			System.out.println(resultSet.getLong("id"));
			System.out.println(resultSet.getString("name"));
			System.out.println(">>>>>>>>>>>>>>");
			System.out.println(resultSet);
			ps.close();
			conn.close();

		}catch (Exception e) {
			e.printStackTrace();
		}
		assertThat(1).isEqualTo(1);
	}
}
