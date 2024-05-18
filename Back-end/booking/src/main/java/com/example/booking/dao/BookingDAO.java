package com.example.booking.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

@Repository
public class BookingDAO extends DAO{
	public void createBookingState(long request_id) {
		String sql = "INSERT INTO bookingstate(request_id, step, state) VALUES(?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, request_id);
			ps.setInt(3, 0);
				for(int i = 1; i <= 8; i++) {
					ps.setInt(2, i);
					ps.execute();
				}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void updateBookingState(long request_id, int step, int state) {
		String sql = "UPDATE bookingstate SET state = ? WHERE request_id = ? and step = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, state);
			ps.setLong(2, request_id);
			ps.setInt(3, step);
			ps.execute();
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public ArrayList<Integer> getBookingState(long request_id){
		ArrayList<Integer> result = new ArrayList<>();
		String sql = "SELECT state FROM bookingstate WHERE request_id = ? ORDER BY step";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, request_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				result.add(rs.getInt(1));
			}
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
}
