package Utils.ArithmeticResultGenerator;

import Utils.DBConnector;

import java.sql.SQLException;
import java.util.List;

public abstract class ArithmeticResultGenerator extends DBConnector {

    public abstract List<Object> generateResults() throws SQLException;

}
