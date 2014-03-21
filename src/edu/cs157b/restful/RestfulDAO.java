package edu.cs157b.restful;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class RestfulDAO {

    public List<Doctor> findAll() {
        List<Doctor> list = new ArrayList<Doctor>();
        Connection c = null;
    	String sql = "SELECT * FROM doctor_info ORDER BY name";
        try {
            c = ConnectionHelper.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return list;
    }

    
    public List<Doctor> findBySpecialty(String specialty) {
        List<Doctor> list = new ArrayList<Doctor>();
        Connection c = null;
    	String sql = "SELECT * FROM doctor_info as e " +
			"WHERE UPPER(specialty) LIKE ? " +	
			"ORDER BY name";
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, "%" + specialty.toUpperCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(processRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return list;
    }
    
    public Doctor findById(int id) {
    	String sql = "SELECT * FROM doctor_info WHERE id = ?";
    	Doctor doctor = null;
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                doctor = processRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return doctor;
    }

    public Doctor save(Doctor doctor)
	{
		return doctor.getId() > 0 ? update(doctor) : create(doctor);
	}    
    
    public Doctor create(Doctor doctor) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionHelper.getConnection();
            ps = c.prepareStatement("INSERT INTO doctor_info (name, specialty) VALUES (?, ?)",
                new String[] { "ID" });
            ps.setString(1, doctor.getName());
            ps.setString(2, doctor.getSpecialty());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            // Update the id in the returned object. This is important as this value must be returned to the client.
            int id = rs.getInt(1);
            doctor.setId(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return doctor;
    }

    public Doctor update(Doctor doctor) {
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("UPDATE doctor_info SET name=?, specialty=? WHERE id=?");
            ps.setString(1, doctor.getName());
            ps.setString(2, doctor.getSpecialty());
            ps.setInt(3, doctor.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return doctor;
    }

    public boolean remove(int id) {
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM doctor_info WHERE id=?");
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            return count == 1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
    }

    protected Doctor processRow(ResultSet rs) throws SQLException {
    	Doctor doctor = new Doctor();
    	doctor.setId(rs.getInt("id"));
    	doctor.setName(rs.getString("name"));
    	doctor.setSpecialty(rs.getString("specialty"));
        return doctor;
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public List<Patient> findAllP() {
        List<Patient> list = new ArrayList<Patient>();
        Connection c = null;
    	String sql = "SELECT * FROM patient_info ORDER BY name";
        try {
            c = ConnectionHelper.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processRowP(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return list;
    }

    
    public List<Patient> findByNameP(String name) {
        List<Patient> list = new ArrayList<Patient>();
        Connection c = null;
    	String sql = "SELECT * FROM patient_info as e " +
			"WHERE UPPER(name) LIKE ? " +	
			"ORDER BY name";
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, "%" + name.toUpperCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(processRowP(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return list;
    }
    
    public Patient findByIdP(int id) {
    	String sql = "SELECT * FROM patient_info WHERE id = ?";
    	Patient patient = null;
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	patient = processRowP(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return patient;
    }
    
    public List<Patient> findByRelation(String id) {
        List<Patient> list = new ArrayList<Patient>();
        Connection c = null;
    	String sql = "SELECT * FROM patient_info where d_id=? ORDER BY name";
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(processRowP(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return list;
    }

    public Patient saveP(Patient patient)
	{
		return patient.getId() > 0 ? updateP(patient) : createP(patient);
	}    
    
    public Patient createP(Patient patient) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionHelper.getConnection();
            ps = c.prepareStatement("INSERT INTO patient_info (name, age, pcondition, d_id) VALUES (?, ?, ?, ?)",
                new String[] { "ID" });
            ps.setString(1, patient.getName());
            ps.setInt(2, patient.getAge());
            ps.setString(3, patient.getCondition());
            ps.setInt(4, patient.getDID());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            // Update the id in the returned object. This is important as this value must be returned to the client.
            int id = rs.getInt(1);
            patient.setId(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return patient;
    }

    public Patient updateP(Patient patient) {
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("UPDATE patient_info SET name=?, age=?, pcondition=?, d_id=? WHERE id=?");
            ps.setString(1, patient.getName());
            ps.setInt(2, patient.getAge());
            ps.setString(3,  patient.getCondition());
            ps.setInt(4, patient.getDID());
            ps.setInt(5, patient.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return patient;
    }

    public boolean removeP(int id) {
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM patient_info WHERE id=?");
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            return count == 1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
    }

    protected Patient processRowP(ResultSet rs) throws SQLException {
    	Patient patient = new Patient();
    	patient.setId(rs.getInt("id"));
    	patient.setName(rs.getString("name"));
    	patient.setAge(rs.getInt("age"));
    	patient.setCondition(rs.getString("pcondition"));
    	patient.setDID(rs.getInt("d_id"));
        return patient;
    }
}
