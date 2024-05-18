package com.example.demo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Invoice;

@Repository
public class InvoiceDAO extends DAO{
	public Invoice createInvoice(int user_id) {
		Invoice invoice = new Invoice();
        String sql = "INSERT INTO invoice (user_id, status) " +
                "VALUES (?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, user_id);
            ps.setInt(2, 0);
            ps.execute();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    invoice.setId(id);
                    invoice.setStatus(0);
                    invoice.setUser_id(user_id);
                    return invoice;
                } else {
                    throw new SQLException("Không có ID được tạo ra.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}

	public boolean updateInvoice(int invoice_id, int status) {
        String sql = "UPDATE invoice " +
                "SET status = ? where id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, invoice_id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}


	public int checkInvoiceStatus(int invoice_id) {
        String sql = "SELECT * from invoice where id = ?";
        int status = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, invoice_id);
			ResultSet rs = ps.executeQuery();
            while(rs.next()) {
            	int tmp = rs.getInt("status");
            	if(tmp != 0) status = tmp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
	}

}
