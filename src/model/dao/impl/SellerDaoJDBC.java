package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
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
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO seller " +
                        "(Name, Email, BirthDate, BaseSalary, DepartmentId) " +
                        "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            setInsertSellerValues(preparedStatement, seller);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected < 1) {
                throw new DbException("Seller insert error, No rows affected");
            }

            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                seller.setId(id);
            }
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
                return instanciateSeller(resultSet, instanciateDepartment(resultSet));
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
        ResultSet resultSet = null;
        Statement statement = null;
        List<Seller> sellers = new ArrayList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "SELECT seller.*,department.Name as DepName " +
                        "FROM seller INNER JOIN department " +
                        "ON seller.DepartmentId = department.Id"
            );

            while (resultSet.next()) {
                sellers.add(instanciateSeller(resultSet, instanciateDepartment(resultSet)));
            }

            return sellers;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResource(statement);
            DB.closeResource(resultSet);
        }
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

    private void setInsertSellerValues(PreparedStatement preparedStatement, Seller seller) throws SQLException {
        preparedStatement.setString(1, seller.getName());
        preparedStatement.setString(2, seller.getEmail());
        preparedStatement.setObject(3, seller.getBirthdate());
        preparedStatement.setDouble(4, seller.getBaseSalary());
        preparedStatement.setInt(5, seller.getDepartment().getId());
    }
}
