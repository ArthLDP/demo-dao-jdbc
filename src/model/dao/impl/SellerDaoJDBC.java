package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {
    Connection connection;

    public SellerDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Seller seller) {

    }

    @Override
    public void update(Seller seller) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT seller.*,department.Name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id " +
                    "WHERE seller.Id = ?"
            );

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Department department = instanciateDepartment(resultSet);
                return instanciateSeller(resultSet, department);
            }
            return null;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResource(preparedStatement);
            DB.closeResource(resultSet);
        }
    }

    @Override
    public List<Seller> findAll() {
        return List.of();
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Seller> sellers = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT seller.*, department.name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON department.id = DepartmentId " +
                    "WHERE department.id = ? " +
                    "ORDER BY Name"
            );

            preparedStatement.setInt(1, department.getId());
            resultSet = preparedStatement.executeQuery();
            Department departmentFromResultSet = null;

            while (resultSet.next()) {
                if (departmentFromResultSet == null) {
                    departmentFromResultSet = instanciateDepartment(resultSet);
                }

                sellers.add(instanciateSeller(resultSet, departmentFromResultSet));
            }
            return sellers;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResource(preparedStatement);
            DB.closeResource(resultSet);
        }
    }

    private Department instanciateDepartment(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getInt("DepartmentId"));
        department.setName(resultSet.getString("DepName"));
        return department;
    }

    private Seller instanciateSeller(ResultSet resultSet, Department department) throws SQLException {
        Seller seller = new Seller();
        seller.setId(resultSet.getInt("Id"));
        seller.setName(resultSet.getString("Name"));
        seller.setBaseSalary(resultSet.getDouble("BaseSalary"));
        seller.setEmail(resultSet.getString("Email"));
        seller.setBirthdate(resultSet.getObject("BirthDate", LocalDateTime.class));
        seller.setDepartment(department);
        return seller;
    }


}
